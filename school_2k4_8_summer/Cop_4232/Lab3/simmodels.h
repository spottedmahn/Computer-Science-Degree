#ifndef _SIMMODELS
#define _SIMMODELS

#include "simmgmt.h"
using namespace simmgmt;

namespace simmodels 
{
  
extern ofstream simlog;

class SpeakMsg : public Message {  // Inherits from Message
   public:
      SpeakMsg(int Handler, string Description, string Speak);
      ~SpeakMsg() { }
      string getSpeak() const { return speak; }
   protected:
	  //Inherited as virtual and must be redefined:
	  virtual void Insert(ofstream &fout);
      virtual void Put(ofstream &fout);
   private:
      string speak;
};  //SpeakMsg

class Players : public Message {  // Inherits from Message
   public:
      Players(int MaxAgents);
      ~Players();
      AGENTPTR  getAgent(string playerid);
      AGENTPTR  getOther(string playerid);
      void      setAgent(string playerid, AGENTPTR agent);
   protected:
      //Inherited as virtual and must be redefined:
      virtual void Insert(ofstream &fout);
      virtual void Put(ofstream &fout);
   private:
      int lastAgent;
      AGENTPTR *agents;
      string   *agentNames;
}; //Players

class Student : public Agent {  // Inherits from Agent
   public:
      Student();
      Student(ifstream& fin) throw(AppError);
      ~Student();
      void Initialize(MSGPTR players);
      void Dispatch  (MSGPTR msg);
      SpeakMsg *AcceptQuestion(string aquestion);
      SpeakMsg *AcceptAnswer  (string response);
      virtual void Extract(ifstream& fin) throw(AppError);
      virtual void Insert(ofstream &fout);
   protected:
	  virtual void Get(ifstream& fin)     throw(AppError);
      virtual void Put(ofstream &fout);
   private:
      void doQuestions(string question);
      void doAnswers  (string answer);
      Student *me;
      Student *you;
      int ansrDelay;
      int questDelay;
}; //Student

class Conversation {  //Virtual World
   public:
      Conversation();
      ~Conversation();
      void Initialize();
      void Simulate();
      void WrapUp();
      void PutState();
   private:
	  int       numStudents;
      int	   numEvents;
	  int	   lastEvent;	
	  AGENTPTR  *students;
};//Conversation

}//simmodels

#endif
