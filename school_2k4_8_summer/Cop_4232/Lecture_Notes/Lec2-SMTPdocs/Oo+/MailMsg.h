#ifndef _MAILMSG
#define _MAILMSG

typedef struct MessageType
{
	char* client;
	char* server;
	char* to;
	char* from;
	char* subject;
	char* body;
	char  flags;
}   *MESSAGE;


extern MESSAGE MailMsg();
extern void Destroy(MESSAGE msg);
extern void setClient (MESSAGE, char *);
extern void setFrom   (MESSAGE, char *);
extern void setServer (MESSAGE, char *);
extern void setSubject(MESSAGE, char *);
extern void setTo     (MESSAGE, char *);
extern void setText   (MESSAGE, char *);
extern int  sendMessage(MESSAGE);

#endif


