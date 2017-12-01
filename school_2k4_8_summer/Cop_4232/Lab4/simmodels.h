#ifndef _SIMMODELS
#define _SIMMODELS

#include "simmgmt.h"
using namespace simmgmt;

namespace simmodels {

extern ofstream simlog;

class Concept {
   public:
      Concept(){}
      Concept(ifstream& fin);
      ~Concept();
      void setName(string Name) {name = Name;}
      void setDifficulty(double Difficulty) {difficulty = Difficulty;}
      void setFact(Fact* f) {facts[numFacts++] = f; }
      string getName() {return name;}
      double getDifficulty() {return difficulty;}
      int getDiscussion() {return discussion;}
      int getNumFacts() {return numFacts;}
      Fact* getFact(string factName) throw (AppError);
      void Insert(ofstream &fout);
      void Extract(ifstream& fin)throw(AppError);
      void Get(ifstream& fin)throw(AppError);
      void Put(ofstream &fout);
      friend  ofstream& operator<<(ofstream &fout, Concept &anyConcept);
      friend  ifstream& operator>>(ifstream &fout, Concept &anyConcept);
   private;
      string name;
      double difficulty;
      int discussion;
      int numFacts;
      //Fact* *facts;  /// class does not exist yet
};

class Lecture {
   public:
      Lecture();
      ~Lecture();
      void setConcept(Concept* c) {concepts[numConcepts++] = c;}
      int getNumConcepts(){return numConcepts;}
      Concept* getConcept(string conceptName) throw (AppError);
      void Insert(ofstream &fout);
      void Extract(ifstream& fin) throw(AppError);
      void Get(ifstream& fin)t hrow(AppError);
      void Put(ofstream &fout);
      friend  ofstream& operator<<(ofstream &fout, Lecture &anyLecture);
      friend  ifstream& operator>>(ifstream &fout, Lecture &anyLecture);
   private:
      int numConcepts;
      Concept* *concepts;
};

class Fact {
   public:
      Fact();
      ~Fact();
      void setID(string sIn) {ID = sIn;}
      void setNumExamples(int intIn){numExamples = intIn;}
	  void setExDelay(int intIn){exDelay = intIn;}
      int getNumExamples(){return numExamples;}
	  int getExDelay(){return exDelay;}
	  stirng getID(){return ID;}
	  void Insert(ofstream &fout);
      void Extract(ifstream& fin) throw(AppError);
      void Get(ifstream& fin)t hrow(AppError);
      void Put(ofstream &fout);
      friend  ofstream& operator<<(ofstream &fout, Fact &anyFact);
      friend  ifstream& operator>>(ifstream &fout, Fact &anyFact);
   private:
      string ID;
      int numExamples;
      int exDelay;
};

class Quiz {
   public:
	  Quiz(){};
      ~Quiz();
      void setStudentName(string sIn) {studentName = sIn;}
      void setNumQuestions(int intIn){numQuestions = intIn;}
	  void setScore(double dIn){score = dIn;}
	  string getStudentName(){return studentName;}
	  int getNumQuestions(){return numQuestions;}
	  double getScore(){return score;}
	  void Insert(ofstream &fout);
      void Extract(ifstream& fin) throw(AppError);
      void Get(ifstream& fin)t hrow(AppError);
      void Put(ofstream &fout);
      friend  ofstream& operator<<(ofstream &fout, Quiz &anyQuiz);
      friend  ifstream& operator>>(ifstream &fout, Quiz &anyQuiz);
   private:
	   string studentName;
	   int numQuestions;
	   Question* *questions;
	   double score;
};

class Question {
   public:
	  Question(){correct = false;};
      ~Question();
      void setFactName(string sIn) {factName = sIn;}
	  void setAnswer(string sIn){ answer = sIn;}
	  void setCorrect(bool bIn){ correct = bIn;}
	  string getFactName(){return factName;}
      string getAnswer(){return answer;}
	  bool getCorrect(){return correct;}
	  void Insert(ofstream &fout);
      void Extract(ifstream& fin) throw(AppError);
      void Get(ifstream& fin)t hrow(AppError);
      void Put(ofstream &fout);
      friend  ofstream& operator<<(ofstream &fout, Question &anyQuestion);
      friend  ifstream& operator>>(ifstream &fout, Question &anyQuestion);
   private:
      string factName;
      string answer; //concept associated with the fact
      bool correct; // default : false 
};


class Players : public Message {  // test
   public:
      Players(int MaxAgents);
      ~Players();
      AGENTPTR  getAgent(string playerid);
      Instructor* getInstructor();
      void setAgent(AGENTPTR agent);
      void setInstructor(Instructor* i);
   protected:
      virtual void Insert(ofstream &fout);
      virtual void Put(ofstream &fout);
   private:
      int lastAgent;
      AGENTPTR *agents;
      Instructor* instr;
}; //Players







class Student : public Agent { 
   public:
      Student();
      Student(ifstream& fin) throw(AppError);
      ~Student();
      void Initialize(MSGPTR players);     ///// Not done yet
      void Dispatch  (MSGPTR msg);         ///// Not Done yet
      virtual void Extract(ifstream& fin) throw(AppError);
      virtual void Insert(ofstream &fout);
   protected:
      virtual void Get(ifstream& fin)     throw(AppError);
      virtual void Put(ofstream &fout);
   private:
      Instructor *you;
      int TOA;
      double score;
      int ansrDelay;
      double comprehension;
}; //Student



class Instructor : public Agent{
   public:
      Instructor();
      Instructor(ifstream& fin) throw(AppError);
      ~Instructor();
      void Initialize(MSGPTR players);     ///// Not done yet
      void Dispatch  (MSGPTR msg);         ///// Not Done yet
      virtual void Extract(ifstream& fin) throw(AppError);
      virtual void Insert(ofstream &fout);
   protected:
      virtual void Get(ifstream& fin)     throw(AppError);
      virtual void Put(ofstream &fout);
   private:
	   int prepDelay;
	   int gradingDelay;
	   Lecture lec;
	   Quiz quiz;
	   Bool questionAsked;
	   Quiz* gradeQ;
	   Student* *s;
};


class Classroom{  //test
   public:
      //Classroom();      
      ~Classroom();
      void Initialize();
      void Simulate();
      void WrapUp();
      void PutState();  
   private:
      int numStudents;
      int numEvents;
      int lastEvent;
      int rows;
      int columns;
      int classLength;
      //Instructor* instr;      // No class yet
      AGENTPTR  *students;
};

}//simmodels

#endif















       ///// For copying purposes


/*
class SpeakMsg : public Message {  // Inherits from Message
   public:
      SpeakMsg(int Handler, string Description, string Speak);
      ~SpeakMsg() { }
      string getSpeak() const { return speak; }
   protected:
      virtual void Insert(ofstream &fout);
      virtual void Put(ofstream &fout);
   private:
      string speak;
};  //SpeakMsg
*/


/*
SpeakMsg::SpeakMsg(int Handler, string Description, string Speak) :Message(Handler,Description){speak = Speak;}

void SpeakMsg::Insert(ofstream &fout) {
   int delta = 10;
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "SpeakMsg{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}SpeakMsg ";
   theIOMgr.popMargin();
}

void SpeakMsg::Put(ofstream &fout){
   theIOMgr.pushMargin(fout);
   Message::Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << " Speak: " << speak;
   theIOMgr.popMargin();
}
*/

