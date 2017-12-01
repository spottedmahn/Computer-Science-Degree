#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Socket.h"

SOCKET OpenSocket(const char * servername, const int Port ){
       SOCKET s = (SOCKET)calloc(1,sizeof(struct SocketType));
       /* initialize encapsulated data */
       s->sockid = socket(AF_INET, SOCK_STREAM, 0);
	   if( !(s->server = gethostbyname(servername)) ) goto err;
       s->saddr_in.sin_family      = AF_INET;
       s->saddr_in.sin_port        = htons((u_short)Port);
       s->saddr_in.sin_addr.s_addr = 0;
       memcpy((char*)&(s->saddr_in.sin_addr), s->server->h_addr, s->server->h_length);
       if( connect(s->sockid, (struct sockaddr*)&(s->saddr_in), sizeof(s->saddr_in)) != -1) return s;
err:   free((void *)s);
	   return NULL;
}/*OpenSocket*/


void CloseSocket(SOCKET s){
    if( s == NULL ) return;
    close(s->sockid);
    free((void *)s);   
}/*CloseSocket*/


void sendCommand(SOCKET s, const char *str, const char *arg  ) {
    char buf[4096];

    if( s == NULL ) return;

    if (arg != NULL) {
          snprintf(buf, sizeof(buf), str, arg);        
	  printf("SEND: "); printf(str,arg);    /* echo to screen */
    } else {
          snprintf(buf, sizeof(buf), str);
	  printf("SEND: "); printf("%s\n",str); /* echo to screen */
    }/*else*/
    
    send(s->sockid, buf, strlen(buf), 0);    

}/*sendCommand*/

int recvReply(SOCKET s, const char *reply ) {

	char rbuff[4096];
	int  i;
        
    if( s == NULL ) return;

	i = recv ( s->sockid, rbuff, sizeof(rbuff) , 0);
	if (i != -1) {
		rbuff[i] = 0; /* insert null byte at end of reply string */
		printf("(Looking For: %s) Received: %s\n",reply, rbuff);
		if (strncmp(reply, rbuff, strlen(reply)) == 0)
			return 1;  /* reply code is correct              */
		else
			return 0;  /* expected reply code does not match */
	} else {
		printf("Receive Error!\n");
		return 0;
	}
}/*recvReply*/







