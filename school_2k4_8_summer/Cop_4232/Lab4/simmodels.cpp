#include "simmodels.h"
using namespace simmodels;

#include <ctime>
#include <iomanip>
using namespace std;

namespace simmodels {

EventMgr theEventMgr;
ofstream simlog;

void putHeader( ofstream &fout ){
   time_t curr = time(0);
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Date and time of run : " << ctime(&curr) << "Project : Lab #4" << endl << "Michael DePouw" << endl << "Matt Gordon";
   theIOMgr.newLine(fout);
   theIOMgr.popMargin();
}//putHeader




/////////////////////////////////////////////////////////////// Players



Players::Players(int MaxAgents):Message(0,"Players"){
   agents = new AGENTPTR[MaxAgents];
   lastAgent  = -1;
}

Players::~Players(){
   for ( int i = 0 ; i <= lastAgent ; i++ ) {delete agents[i]; }
   delete[] agents;
   delete instr;
}

AGENTPTR Players::getAgent(string playerid) {   ///////// Not Tested
   int i = 0;
   for(; i <= lastAgent ; i++){
      if((agents[i])->NameOf() == playerid ) {break;}
   }
   if ( i > lastAgent ) {throw AppError(string("Cannot Find Agent !"), string("Players::getAgent(" + playerid + ")"));}
   else return agents[i];
}

Instructor* Players::getInstructor() {return instr;}  /////

void Players::setAgent(AGENTPTR agent){     ///////// Not Tested
   lastAgent++;
   agents[lastAgent] = agent;
}

void Players::setInstructor(Instructor* i) {instr = i;}

void Players::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Players{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Players ";
   theIOMgr.popMargin();
}

void Players::Put(ofstream &fout){   ///////// Not Tested
   theIOMgr.pushMargin(fout);
   for(int i = 0; i <= lastAgent; i++ ){
      if( !((i+1) % 10) ){theIOMgr.advanceMargin(fout);}
      fout << " agent[ " << i << " ] = " << (agents[i])->NameOf();
   }
   theIOMgr.advanceMargin(fout);
   fout << "Instructor = " << instr->NameOf();
   theIOMgr.popMargin();
}




 /////////////////////////////////////////////////////////////// Student




Student::Student():Agent(){score = -1;}
Student::Student(ifstream& fin) throw(AppError){
   score = -1;
   Extract(fin);
}
Student::~Student(){}

void Student::Extract(ifstream& fin) throw(AppError){ 
   string token;
   fin >> token;
   if ( token != "Student{" ) {throw AppError(string("Incorrect Token, '" + token + "', excpected 'Student{' !"),string("Student::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Student" ) {throw AppError(string("Incorrect Token '" + token + "', excpected token '}Student' !"),string("Student::Get()")); }
}

void Student::Get(ifstream& fin)throw(AppError){ 
   string token;
   Agent::Get(fin);
   fin >> token;
   if ( token != "TOA:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'questdelay:' !"),string("Student::Get()")); }
   fin >> token;
   TOA = atoi(token.c_str());
   fin >> token;
   if ( token != "ansDelay:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'ansrdelay:' !"),string("Student::Get()"));}
   fin >> token;
   ansrDelay = atoi(token.c_str());
   fin >> token;
   if ( token != "comprehension:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'Comprehension:' !"),string("Student::Get()"));}
   fin >> token;
   comprehension = atof(token.c_str()); 
}

void Student::Insert(ofstream &fout) { 
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Student{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Student ";
   theIOMgr.popMargin();
}

void Student::Put(ofstream &fout) {
   theIOMgr.pushMargin(fout);
   Agent::Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "TOA: " << TOA;
   theIOMgr.advanceMargin(fout);
   fout << "ansDelay: "  << ansrDelay;
   theIOMgr.advanceMargin(fout);
   fout << "comprehension: "  << comprehension;
   theIOMgr.advanceMargin(fout);
   fout << "score: ";
   if (score == -1) {fout << "Quiz not taken!";}
   else {fout << score;}
   theIOMgr.popMargin();
}

void Student::Initialize(MSGPTR players){
   you = (Instructor *)((Players *)players)->getInstructor();
  // SpeakMsg *strMsg = you->AcceptQuestion("What is your name?");     // Construct new message
   //Event e( questDelay , me , you , strMsg );               // Construct new Event
   //theEventMgr.postEvent(e);
}

void Student::Dispatch  (MSGPTR msg){
   //int h = msg->getHandler();
  // string content = ((SpeakMsg *)msg)->getSpeak();
   //if ( h == 1 ) doQuestions(content);
   //if ( h == 2 ) doAnswers(content);
}

////////////////////////////////////////////////////////////////Instructor

Instructor::Instructor():Agent(){}
Instructor::Instructor(ifstream& fin) throw(AppError){
   Extract(fin);
}
Instructor::~Instructor(){}

void Instructor::Extract(ifstream& fin) throw(AppError){ 
   string token;
   fin >> token;
   if ( token != "Instructor{" ) {throw AppError(string("Incorrect Token, '" + token + "', excpected 'Instructor{' !"),string("Instructor::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Instructor" ) {throw AppError(string("Incorrect Token '" + token + "', excpected token '}Instructor' !"),string("Instructor::Get()")); }
}

void Instructor::Get(ifstream& fin)throw(AppError){ 
   string token;
   Agent::Get(fin);
   fin >> token;
   if ( token != "prepDelay:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'questdelay:' !"),string("Instructor::Get()")); }
   fin >> token;
   TOA = atoi(token.c_str());
   fin >> token;
   if ( token != "gradingDelay:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'ansrdelay:' !"),string("Instructor::Get()"));}
   fin >> token;
   ansrDelay = atoi(token.c_str());
   lec = new Lecture(fin);
}

void Instructor::Insert(ofstream &fout) { 
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Instructor{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Instructor ";
   theIOMgr.popMargin();
}

void Instructor::Put(ofstream &fout) {
   theIOMgr.pushMargin(fout);
   Agent::Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "prepDelay: " << prepDelay;
   theIOMgr.advanceMargin(fout);
   fout << "gradingDelay: "  << gradingDelay;
   theIOMgr.advanceMargin(fout);
   fout << lec;
   theIOMgr.popMargin();
}

void Instructor::Initialize(MSGPTR players){//not complete
   you = (Instructor *)((Players *)players)->getInstructor();
  // SpeakMsg *strMsg = you->AcceptQuestion("What is your name?");     // Construct new message
   //Event e( questDelay , me , you , strMsg );               // Construct new Event
   //theEventMgr.postEvent(e);
}

void Instructor::Dispatch  (MSGPTR msg){//not complete
   //int h = msg->getHandler();
  // string content = ((SpeakMsg *)msg)->getSpeak();
   //if ( h == 1 ) doQuestions(content);
   //if ( h == 2 ) doAnswers(content);
}

/////////////////////////////////////////////////////////////// Concept


Concept::Concept(ifstream& fin) {Extract(fin);}

Concept::~Concept() {
   for (int i = 0; i < numFacts ; i++ ) {delete facts[i]; }
   delete[] facts;
}

Fact* Concept::getFact(string factName) throw (AppError); ///////// Not tested
   int i;
   for(i = 0; i < numFacts; i++){
      if((facts[i])->getID() == factName) {break;}   //// name of function unknown
   }
   if (i >= numFacts) {throw AppError(string("Cannot Find Fact!"), string("Concept::getFact(" + factName + ")"));}
   else return fact[i];
}

void Concept::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Concept{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Concept ";
   theIOMgr.popMargin();
}

void Concept::Extract(ifstream& fin) throw(AppError){     ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Concept{" ) {throw AppError(string("Incorrect Token, '" + token + "', expected 'Concept{' !"),string("Concept::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Concept" ) {throw AppError(string("Incorrect Token '" + token + "', expected token '}Concept' !"),string("Concept::Get()")); }
}



void Concept::Get(ifstream& fin) throw(AppError){   ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "name:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'name:' !"),string("Concept::Get()")); }
   fin >> token;
   name = token;
   fin >> token;
   if ( token != "difficulty:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'difficulty:' !"),string("Concept::Get()"));}
   fin >> token;
   difficulty = atof(token.c_str()); 
   fin >> token;
   if ( token != "q&aPeriod:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'q&aPeriod:' !"),string("Concept::Get()"));}
   fin >> token;
   discussion = atof(token.c_str()); 
   fin >> token;
   if ( token != "numFacts:" ){throw AppError(string("Incorrect Token '" + token + "', expected 'numFacts:' !"),string("Concept::Get()")); }
   fin >> token;
   numFacts = atoi(token.c_str());
   facts = new Fact*[numFacts];
   for(int i = 0; i < numFacts; i++) {facts[i] = new Fact(fin);}
}

void Concept::Put(ofstream &fout) {  ///////// Not Tested
   theIOMgr.pushMargin(fout);
   fout << "name: " << name;
   theIOMgr.advanceMargin(fout);
   fout << "difficulty: " << difficulty;
   theIOMgr.advanceMargin(fout);
   fout << "q&aPeriod: " << discussion;
   theIOMgr.advanceMargin(fout);
   fout << "numFacts: " << numFacts;
   for(int i = 0; i < numFacts; i++) {
      theIOMgr.advanceMargin(fout);
      fout << *(facts[i]);
   }
   theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Concept &anyConcept) {
   anyConcept.Insert(fout);
   return fout;
}

ifstream& operator>>(ofstream &fin, Concept &anyConcept) {
   anyConcept.Extract(fin);
   return fin;
}


////////////////////////////////////////////////////////////////Fact


Fact::Fact(ifstream& fin) {Extract(fin);}

Fact::~Fact() {
   for (int i = 0; i < numFacts ; i++ ) {delete facts[i]; }
   delete[] facts;
}

void Fact::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Fact{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Fact ";
   theIOMgr.popMargin();
}

void Fact::Extract(ifstream& fin) throw(AppError){     ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Fact{" ) {throw AppError(string("Incorrect Token, '" + token + "', expected 'Fact{' !"),string("Fact::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Fact" ) {throw AppError(string("Incorrect Token '" + token + "', expected token '}Fact' !"),string("Fact::Get()")); }
}



void Fact::Get(ifstream& fin) throw(AppError){   ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "id:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'name:' !"),string("Fact::Get()")); }
   fin >> token;
   ID = token;
   fin >> token;
   if ( token != "numexamples:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'difficulty:' !"),string("Fact::Get()"));}
   fin >> token;
   numExamples = atof(token.c_str()); 
   fin >> token;
   if ( token != "exDelay:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'q&aPeriod:' !"),string("Fact::Get()"));}
   fin >> token;
   exDelay = atof(token.c_str()); 
}

void Fact::Put(ofstream &fout) {  ///////// Not Tested
   theIOMgr.pushMargin(fout);
   fout << "id: " << ID;
   theIOMgr.advanceMargin(fout);
   fout << "numExamples: " << numExamples;
   theIOMgr.advanceMargin(fout);
   fout << "exDelay: " << exDelay;
   theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Fact &anyFact) {
   anyFact.Insert(fout);
   return fout;
}

ifstream& operator>>(ifstream &fin, Fact &anyFact) {
   anyFact.Extract(fin);
   return fin;
}

///////////////////////////////////////////////////////////////Quiz

Quiz::Quiz(ifstream& fin) {Extract(fin);}

Quiz::~Quiz() {
   for (int i = 0; i < numQuestions ; i++ ) {delete questions[i]; }
   delete[] questions;
}

void Quiz::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Quiz{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Quiz ";
   theIOMgr.popMargin();
}

void Quiz::Extract(ifstream& fin) throw(AppError){     ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Quiz{" ) {throw AppError(string("Incorrect Token, '" + token + "', expected 'Quiz{' !"),string("Quiz::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Quiz" ) {throw AppError(string("Incorrect Token '" + token + "', expected token '}Quiz' !"),string("Quiz::Get()")); }
}



void Quiz::Get(ifstream& fin) throw(AppError){   ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "numQuestions:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'name:' !"),string("Quiz::Get()")); }
   fin >> token;
   numQuestions = atoi(token.c_str());;
   for(int i=0; i < numQuestions;i++){
       questions[i] = new Question(fin);
   }
}

void Quiz::Put(ofstream &fout) {  ///////// Not Tested
   theIOMgr.pushMargin(fout);
   fout << "numQuestions: " << numQuestions;
   for(int i=0; i < numQuestions;i++){
      theIOMgr.advanceMargin(fout);
      fout << questions[i];
   }
   theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Quiz &anyQuiz) {
   anyQuiz.Insert(fout);
   return fout;
}

ifstream& operator>>(ifstream &fin, Quiz &anyQuiz) {
   anyQuiz.Extract(fin);
   return fin;
}

///////////////////////////////////////////////////////////////Question

Question::Question(ifstream& fin) {Extract(fin);}

Question::~Question() {
}

void Question::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Question{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Question ";
   theIOMgr.popMargin();
}

void Question::Extract(ifstream& fin) throw(AppError){     ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Question{" ) {throw AppError(string("Incorrect Token, '" + token + "', expected 'Question{' !"),string("Question::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Question" ) {throw AppError(string("Incorrect Token '" + token + "', expected token '}Question' !"),string("Question::Get()")); }
}


void Question::Get(ifstream& fin) throw(AppError){   ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Fact:" ){throw AppError(string("Incorrect Token '" + token + "', excpected 'name:' !"),string("Question::Get()")); }
   fin >> factName;
}

void Question::Put(ofstream &fout) {  ///////// Not Tested
   theIOMgr.pushMargin(fout);
   fout << "Fact: " << factName;
   theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Question &anyQuestion) {
   anyQuestion.Insert(fout);
   return fout;
}

ifstream& operator>>(ifstream &fin, Question &anyQuestion) {
   anyQuestion.Extract(fin);
   return fin;
}

/////////////////////////////////////////////////////////////// Lecture

 
Lecture::Lecture() {numConcepts = 0;}

Lecture::~Lecture() {
   for (int i = 0; i < numConcepts ; i++ ) {delete concepts[i]; }
   delete[] concepts;
}


Concept* Lecture::getConcept(string conceptName) throw (AppError) {   ///////// Not tested
   int i;
   for(i = 0; i < numConcepts; i++){
      if((concepts[i])->getName() == conceptName) {break;}
   }
   if ( i >= numConcepts ) {throw AppError(string("Cannot Find Concept!"), string("Lecture::getConcept(" + conceptName + ")"));}
   else return concept[i];
}

void Lecture::Insert(ofstream &fout){      ///////// Not Tested
   theIOMgr.pushMargin(fout);
   theIOMgr.advanceMargin(fout);
   fout << "Lecture{ ";
   Put(fout);
   theIOMgr.advanceMargin(fout);
   fout << "}Lecture ";
   theIOMgr.popMargin();
}

void Lecture::Extract(ifstream& fin) throw(AppError){     ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "Lecture{" ) {throw AppError(string("Incorrect Token, '" + token + "', expected 'Lecture{' !"),string("Lecture::Extract()"));}
   Get(fin);
   fin >> token;
   if ( token != "}Lecture" ) {throw AppError(string("Incorrect Token '" + token + "', expected token '}Lecture' !"),string("Lecture::Get()")); }
}

void Lecture::Get(ifstream& fin) throw(AppError){   ///////// Not Tested
   string token;
   fin >> token;
   if ( token != "numConcepts:" ){throw AppError(string("Incorrect Token '" + token + "', expected 'numConcepts:' !"),string("Lecture::Get()")); }
   fin >> token;
   numConcepts = atoi(token.c_str());
   concepts = new Concept*[numConcepts];
   for(int i = 0; i < numConcepts; i++) {concepts[i] = new Concept(fin);}
}

void Lecture::Put(ofstream &fout) {  ///////// Not Tested
   theIOMgr.pushMargin(fout);
   fout << "numConcepts: " << numConcepts;
   for(int i = 0; i < numConcepts; i++) {
      theIOMgr.advanceMargin(fout);
      fout << *(concepts[i]);
   }
   theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Lecture &anyLecture) {
   anyLecture.Insert(fout);
   return fout;
}

ifstream& operator>>(ofstream &fout, Lecture &anyLecture) {
   anyLecture.Extract(fin);
   return fin;
}







 /////////////////////////////////////////////////////////////// Classroom












  
/*
Classroom::Classroom() {
   ifstream  fin;
   time_t curr = time(0);
   cout << "Date and time of run :" << ctime(&curr) << "Project : Lab #4" << endl << "Michael DePouw" << endl << "Matt Gordon" << "\n\n";
   theIOMgr.getFileName("Enter input file name: ");
   theIOMgr.openFile(fin);
   theIOMgr.getFileName("Enter the simlog file name : ");
   theIOMgr.openFile(simlog);
   string token;
   fin >> token;
   if ( token != "Conversation{" ) {throw AppError(string("Incorrect Token '" + token + "', excpected token 'Conversation{' !"),string("Conversation::Conversation()")); }
   fin >> token;
   if ( token != "#students:" ) {throw AppError(string("Incorrect Token '" + token + "', excpected token '#students:' !"),string("Conversation::Conversation()")); }
   fin >> token;
   numStudents = atoi(token.c_str());
   if( numStudents <= 0 ) {throw AppError(string("Number of students is not positive!"),string("Conversation::Conversation()"));}
   students = new AGENTPTR[numStudents];
   for(int i = 0; i < numStudents; i++) {students[i] = new Student(fin); }
   fin >> token;
   if ( token != "}Conversation" ) {throw AppError(string("Incorrect Token '" + token + "', excpected token '}Conversation' !"),string("Conversation::Conversation()"));}
   numEvents = 0;
   putHeader(simlog);
}
*/
Classroom::~Classroom() {
   for ( int i = 0 ; i < numStudents ; i++ ) {delete students[i]; }
   delete[] students;
   delete instr;
}

void Classroom::Initialize() {   /////////////// Not Tested
   Players *players = new Players(numStudents);
   players->setInstructor(instr)
   for(int i = 0; i < numStudents); i++) {
      players->setAgent(students[i]);
      students[i]->Initialize(players);
   }
   instr->Initialize(players);
   simlog << *players;
   delete players;
}

void Classroom::Simulate(){
   Event    e;
   Message *msg;
   string   token;
   while( theEventMgr.moreEvents() ){
      e   = theEventMgr.getNextEvent();
      msg = e.getMsg();
      simlog << e;
      e.getRecvr()->Dispatch( msg );
      delete msg;
      lastEvent = e.getTime();
      numEvents++;
   }
}

void Classroom::PutState() {  /////////////// Not Tested
   theIOMgr.newLine(simlog);
   theIOMgr.pushMargin(simlog);
   simlog << "Classroom{ ";
   theIOMgr.pushMargin(simlog);
   simlog << "rows: " << rows;
   theIOMgr.advanceMargin(simlog);
   simlog << "cols: " << columns;
   theIOMgr.advanceMargin(simlog);
   simlog << "students: " << numStudents;
   theIOMgr.advanceMargin(simlog);
   simlog << "classLength: " << classLength;
   theIOMgr.advanceMargin(simlog);
   simlog << *instr;
   for (int i = 0 ; i < numStudents ; i++ ){
      theIOMgr.advanceMargin(simlog);
      simlog << *((Student *)students[i]);
   }
   theIOMgr.popMargin();
   theIOMgr.advanceMargin(simlog);
   simlog << "}Classroom ";
   theIOMgr.popMargin();
}

void Classroom::WrapUp(){
   theIOMgr.newLine(simlog);
   theIOMgr.pushMargin(simlog);
   theIOMgr.advanceMargin(simlog);
   simlog << "Simulation Statistics:";
   theIOMgr.advanceMargin(simlog);
   simlog << "Number of events : " << numEvents;
   theIOMgr.advanceMargin(simlog);
   simlog << "Time of Last event : " <<  lastEvent;
   theIOMgr.advanceMargin(simlog);
   simlog << "Total simulation time : " << theEventMgr.clock();
   theIOMgr.advanceMargin(simlog);
   theIOMgr.popMargin();
}

}//simmgmt