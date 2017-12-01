#ifndef _SOCKET
#define _SOCKET
/*  This implementation is designed for a Linux/Unix environment */
#include <netdb.h>         /* gethostbyname  */
#include <netinet/in.h>    /* htons          */
#include <sys/socket.h>*/

typedef  struct SocketType {
         int sockid;
         int port;
         struct hostent      *server;   
         struct sockaddr_in  saddr_in;
         }   *SOCKET;      
/*SOCKET is a pointer to a struct SocketType */


extern SOCKET  OpenSocket(const char*, const int ); 
     /* OpenSocket: This is the class constructor.  It creates(allocates) a new Socket instance
        and initializes it.  It returns NULL if the new instance could not be successfully
        created and initialized.  Otherwise it returns a pointer to a SocketType object.
        Parameters: The first parameter is the server name, as before.  The second parameter
        is the port to use for the new Socket instance.
     */

extern void CloseSocket(SOCKET);            
     /* CloseSocket: terminates a socket connection defined by the parameter, and destroys
        or reclaims the memory for that SocketType instance.
     */

extern void sendCommand(SOCKET, const char *, const char * );  
     /* Sends a command string over the socket defined by the first parameter.
     */

extern int  recvReply  (SOCKET, const char * );                
     /* Checks server reply (for the Socket designated by the first parameter) to see if it
        matches the expected response defined by the second parameter; returns 1 if the
        reply matches the expected response.
     */

#endif


