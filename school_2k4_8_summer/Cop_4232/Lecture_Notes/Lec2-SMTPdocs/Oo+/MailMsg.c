#include <stdio.h>
#include <stdlib.h>
#include "MailMsg.h"
#include "Socket.h"

//constructor
//returns a pointer to a new message
//port is assumed to be 25 (this can be expanded at a later time)
MESSAGE MailMsg()
{
	MESSAGE msg = (MESSAGE)calloc(1,sizeof(struct MessageType));
	msg->client = msg->from = msg->server = msg->subject = msg ->body = msg->to = NULL;
	msg->flags = 0;
	return msg;
}

//destructor
void Destroy(MESSAGE msg)
{
	if( msg == NULL ) return;
	free(msg->client);
	free(msg->from);
	free(msg->server);
	free(msg->subject);
	free(msg->body);
	free(msg->to);
	msg->client = msg->from = msg->server = msg->subject = msg ->body = msg->to = NULL;
	free((void *)msg);
}

//set the client field for passed in message
void setClient(MESSAGE msg, char *clienthost)
{
	//parameters are not null
	if(clienthost && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->client) free(msg->client);
		msg->client = clienthost;
		msg->flags |= 0x01;
   }
}

//set the from field for passed in message
void setFrom(MESSAGE msg, char *fromaddr)
{
	//parameters are not null
	if(fromaddr && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->from) free(msg->from);
		msg->from = fromaddr;
		msg->flags |= 0x02;
	}
}

//set the server field for passed in message
void setServer(MESSAGE msg, char *serverhost)
{
	//parameters are not null
	if( serverhost && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->server) free(msg->server);
		msg->server = serverhost;
		msg->flags |= 0x04;
	}
}

//set the subject field for passed in message
void setSubject(MESSAGE msg, char *subj )
{
	//parameters are not null
	if( subj && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->subject) free(msg->subject);
		msg->subject = subj;
		msg->flags |= 0x08;
	}
}

//set the to field for passed in message
void setTo(MESSAGE msg, char *toaddr )
{
	//parameters are not null
	if( toaddr && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->to) free(msg->to);
		msg->to = toaddr;
		msg->flags |= 0x10;
	}
}

//set the text field for passed in message
void setText(MESSAGE msg, char *bdy )
{
	//parameters are not null
	if( bdy && msg)
	{
		//if this field has already been set, delete the allocated memory
		if(msg->body) free(msg->body);
		msg->body = bdy;
		msg->flags |= 0x20;
	}
}

//send the message that is stored in parameter msg
int sendMessage(MESSAGE msg)
{	SOCKET socket;
	if(msg) //better be passing in something other than null
	{
	
	    //  Note: 00111111(base 2) = 3F(base 16) = 63(base 10)
		//  Instead of (flags < 63), we could have written ( flags ^ 0x3F )
	    //  This is saying, flags xor 0x3F.  Any bits that are different
		//  between flags and the mask (0x3F) will result in a non-zero 
	    //  value - non-zero in C is treated as "true"
		printf("sendMessage:servername = %s\n",msg->server);
		printf("sendMessage:clientname = %s\n",msg->client);
		printf("sendMessage:from addr  = %s\n",msg->from);
		printf("sendMessage:to   addr  = %s\n",msg->to);
		printf("sendMessage:subject    = %s\n",msg->subject);
		printf("sendMessage:body       = %s\n",msg->body);
		if( msg->flags < 63 ) goto err;
		if( (socket = OpenSocket(msg->server, 25))==NULL) goto err;
     
	    if (!recvReply(socket, "220")) goto err;

	    sendCommand(socket, "HELO %s\n",msg->client);   // greeting
		if (!recvReply(socket, "250")) goto err;

		sendCommand(socket, "MAIL FROM: %s\n", msg->from);  // from
		if (!recvReply(socket, "250")) goto err;
    
		sendCommand(socket, "RCPT TO: %s\n",   msg->to);    // to
		if (!recvReply(socket, "250")) goto err;
    
		sendCommand(socket, "DATA\n",NULL);      // begin data
		if (!recvReply(socket, "354")) goto err;

		// next comes mail headers
		sendCommand(socket, "From: %s\n",      msg->from);    //from:
	    sendCommand(socket, "To: %s\n",        msg->to);      //to:
		sendCommand(socket, "Subject: %s\n",   msg->subject); //subject:
	    sendCommand(socket, "\n",              NULL);
		sendCommand(socket, "%s\n",            msg->body);    // data
	    sendCommand(socket, ".\n",             NULL);         // end data
		if (!recvReply(socket, "250")) goto err;
    
	    sendCommand(socket, "QUIT\n",          NULL);    // terminate
		if (!recvReply(socket, "221")) goto err;

	    CloseSocket(socket);
		return 0;
	err:
		CloseSocket(socket);
		return 1;
	}
	CloseSocket(socket);
	return 1;
}/*sendMessage*/



