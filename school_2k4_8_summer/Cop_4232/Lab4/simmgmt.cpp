#include "simmgmt.h"
using namespace simmgmt;

namespace simmgmt {  

Message::Message(int Handler,string Description) { 
   handler = Handler;
   description = Description;
}//constructor
         
ofstream& operator<<(ofstream &fout,Message &anyMsg) {
   anyMsg.Insert(fout);
   return fout;
}
 
void Message::Insert(ofstream &fout) { //Inserts an instance of this class into ouput file stream
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Message{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Message ";
   theIOMgr.popMargin();
}

void Message::Put(ofstream &fout){   // Puts all fields in the class into output file stream
   theIOMgr.pushMargin(fout);
   fout << " Handler: " << handler; 
   theIOMgr.advanceMargin(fout);
   fout << " Description: " << description;
   theIOMgr.popMargin();
}
                               
Agent::Agent(ifstream& fin) throw(AppError) {Extract(fin);}  // Constructor for reading in an instance
                                                             // of class Agent
ofstream& operator<<(ofstream &fout,Agent &anyAgent) {
   anyAgent.Insert(fout);
   return fout;
}

ifstream& operator>>(ifstream &fin, Agent &anyAgent) {
   anyAgent.Extract(fin);
   return fin;
}

void Agent::Extract(ifstream &fin ) throw(AppError) {// Reads in an instance of Agent from file input stream
   string token;
   fin >> token;
   if (token != "Agent{") {throw AppError(string("Unrecognized open token," + token + ", 'Agent{' expected!"), string("Agent::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Agent" ) {throw AppError(string("Unrecognized close token," + token + ", '}Agent' expected!"), string("Agent::Extract()"));}
}

void Agent::Insert (ofstream &fout){// Inserts an instance of Agent into the output file
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Agent{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Agent ";
   theIOMgr.popMargin();
}

void Agent::Get(ifstream  &fin) throw(AppError) {//Gets field agentid from the file
   string token;
   fin >> token;
   if ( token != "agentid:" ) throw AppError(string("Unrecognizable data token," + token + ",'name:' excpected!"),string("Agent::Get()"));
   fin >> agentid;
}

void Agent::Put(ofstream  &fout) {fout << "agentid: " << agentid;} //Puts the field agentid into file
           
Event::Event(int Time, Agent* Sendr, Agent* Recvr, Message* Msg)  {//Constructor
   simtime = Time;
   sendr = Sendr;
   recvr = Recvr;
   msg = Msg;
}

bool Event::operator> (Event e2) {     //  These functions are used to compare simulation time between events
   if((*this).getTime() > e2.getTime()){return true;}
   return false;
}

bool Event::operator<=(Event e2) {
   if((*this).getTime() <= e2.getTime()){return true;}
   return false;
}

bool Event::operator>=(Event e2) {
   if((*this).getTime() >= e2.getTime()){return true;}
   return false;
}

bool Event::operator< (Event e2) {
   if((*this).getTime() < e2.getTime()){return true;}
   return false;
}

ofstream&  operator<<(ofstream &fout,Event e) { 
   theIOMgr.newLine(fout);
   fout << "Time: " << e.simtime << " Sender: " << e.sendr->NameOf() << " Receiver: " << e.recvr->NameOf() << " ";
   fout << *(e.getMsg());
   return fout;
}

EventMgr::EventMgr() {// Constructor
   currTime = 0;
   currSendr = NULL;
   currRecvr = NULL;
}

void EventMgr::postEvent(Event e) {//inserts an event into the eventQ based on its simulation time
   Event temp;
   list<Event>::iterator position;
   if (eventQ.empty()) {eventQ.push_front(e);}
   else if (e >= eventQ.back()) {eventQ.push_back(e);}
   else {
      for(position = eventQ.begin(); position != eventQ.end(); position++) {
		  if (e < *position) {eventQ.insert(position, e);}
	  }
   }
}

Event EventMgr::getNextEvent() throw (AppError){//Retrieves the event at the front of the eventQ
   Event temp;
   if (moreEvents()) {
      temp = eventQ.front();
      eventQ.pop_front();
      currTime = temp.getTime();
      currSendr = temp.getSendr();
      currRecvr = temp.getRecvr();
   }
   else {throw AppError(string("No Events reside in the queue!"), string("EventMgr::getNextEvent()"));}
   return temp;
}

ofstream& operator<<(ofstream &fout, EventMgr Mgr) { 
   list<Event>::iterator i;
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "EventMgr{ ";
   theIOMgr.pushMargin(fout);
   for(i = Mgr.eventQ.begin(); i != Mgr.eventQ.end(); i++) { 
      fout << *i;
      theIOMgr.advanceMargin(fout);
   }
   theIOMgr.popMargin();
   theIOMgr.advanceMargin(fout);
   fout << "}EventMgr ";
   theIOMgr.popMargin();
   return fout;
}

};
      














