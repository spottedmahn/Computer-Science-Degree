#ifndef _SIMMGMT
#define _SIMMGMT

#include "IOMgmt.h"
using namespace iomgmt;

#include <list>
using namespace std;

namespace simmgmt {

class Message {
   public:
      Message(int Handler,string Description);
      virtual ~Message() { }
      int getHandler() const { return handler;}
      friend ofstream& operator<<(ofstream &fout,Message &anyMsg);
   protected:
      virtual void Insert(ofstream &fout);
      virtual void Put   (ofstream &fout);
   private:
      int handler;
      string description;
};//Message

class Agent { //abstract base class
   public:
      Agent(){};                                    //default constructor
      Agent(ifstream& fin) throw(AppError);         //parameterized constructor
      virtual ~Agent() {}                           //virtual destructor
      string  NameOf() const { return agentid; }    //inspector
      virtual void Initialize(Message *anyMsg)=0;   //pure virtual method
      virtual void Dispatch  (Message *anyMsg)=0;   //pure virtual method
      friend  ofstream& operator<<(ofstream &fout,Agent &anyAgent); //boundary: Insert
      friend  ifstream& operator>>(ifstream &fout, Agent &anyAgent); //boundary: Extract
      virtual void Extract(ifstream &fin ) throw(AppError);//virtual method
      virtual void Insert (ofstream &fout);                //virtual method
	  void setName(string name) {agentid = name;}
   protected:
      virtual void Get(ifstream  &fin)     throw(AppError);//virtual method
      virtual void Put(ofstream  &fout);                   //virtual method
   private:
      string agentid;  
};//Agent

class Event { //Single base class
   public:
      Event(){}                               //default constructor
      Event( int    Time,  Agent*   Sendr, Agent* Recvr, Message* Msg );
      ~Event(){}                               //destructor
      Agent*     getSendr() {return sendr;}     //inspector
      Agent*     getRecvr() {return recvr;}     //inspector
      int        getTime()  {return simtime;}   //inspector
      Message*   getMsg()   {return msg;}       //inspector
      bool       operator> (Event e2);          //predicate
      bool       operator<=(Event e2);          //predicate
      bool       operator< (Event e2);          //predicate
      bool       operator>=(Event e2);
      friend ofstream&  operator<<(ofstream &fout,Event e);   //boundary:Insert
   private:
      int      simtime;
      Agent*   sendr;
	  Agent*   recvr;
	  Message* msg;
};//Event

class EventMgr {  //Single base class
   public:
      EventMgr();
      ~EventMgr(){}
      bool	   moreEvents() {return !eventQ.empty();}
      int	   clock()      {return currTime; }
      Agent*  getSendr()   {return currSendr;}
      Agent*  getRecvr()   {return currRecvr;}
      void	   postEvent(Event e);
      Event   getNextEvent() throw(AppError);
      friend ofstream& operator<<(ofstream &fout, EventMgr Mgr);
   private:
      std::list<Event> eventQ;
      int    currTime;	    //time of last Event removed from eventQ
      Agent *currSendr;	//last Sendr agent
      Agent *currRecvr;	//last Recvr agent
};//EventMgr

}//simmgmt

typedef simmgmt::Agent&		AGENTREF;
typedef simmgmt::Agent*		AGENTPTR;
typedef simmgmt::Message&	MSGREF;
typedef simmgmt::Message*	MSGPTR;
typedef simmgmt::EventMgr&	EMGRREF;
typedef simmgmt::EventMgr*	EMGRPTR;

#endif
