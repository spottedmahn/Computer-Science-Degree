#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MailMsg.h"

char *remLF( char *buff )
{
      int i;
      if( buff == NULL ) return NULL;
      i = strlen(buff)-1;
      if(i >= 0 && buff[i] == '\n') buff[i] = '\0';
      return buff;
}/*remLF*/

int main()
{
	char  buff[2048];
	char *client, *server, *to, *from, *subject, *text;
	MESSAGE m = MailMsg();	//create a new MailMessage object
	client = server = to = from = subject = text = NULL;

	//initialize pointers to null as a default
	//depending on the compiler environment, the null keyword could be different
	//read in various fields

	printf( "Enter Client host: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	client = (char *)malloc( strlen(buff)+ 1);
	strcpy(client, buff);
	printf( " Client: %s\n", client);
	setClient(m, client);
	client = NULL;

	printf( "Enter Server host: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	server = (char *)malloc( strlen(buff)+ 1);
	strcpy(server, buff);
	printf( " Server: %s\n", server);
	setServer(m, server);
	server = NULL;

	printf( "Enter TO: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	to = (char *)malloc( strlen(buff)+ 1);
	strcpy(to, buff);
	printf( " TO: %s\n", to);
	setTo(m, to);
	to = NULL;

	printf( "Enter FROM: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	from = (char *)malloc( strlen(buff)+ 1);
	strcpy(from, buff);
	printf( " FROM: %s\n", from);
	setFrom(m, from);
	from = NULL;

	printf( "Enter SUBJECT: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	subject = (char *)malloc( strlen(buff)+ 1);
	strcpy(subject, buff);
	setSubject(m, subject);
	printf( " SUBJECT: %s\n", subject);
	subject = NULL;


	printf( "Enter TEXT: ");
	fgets(buff,sizeof(buff),stdin);
	remLF(buff);
	text = (char *)malloc( strlen(buff)+ 1);
	strcpy(text, buff);
	setText(m, text);
	printf( " TEXT: %s\n", text);
	text = NULL;

	if( sendMessage(m) == 0 )
		printf("Message was sent.");
	else
		printf("Message could no be sent, please try again.");

	//free allocated memory for the message object
	Destroy(m);
	return 0;
}