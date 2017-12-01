
#include "simmodels.h"
using namespace simmodels;

#include <ctime>
#include <iomanip>
using namespace std;

namespace simmodels {

 EventMgr theEventMgr;
 ofstream simlog;

/////////////////////////////////////////////////////////////////// putHeader()

void putHeader( ofstream &fout ){
	// Run header
	time_t curr = time(0);

	theIOMgr.pushMargin(fout);
	theIOMgr.advanceMargin(fout);
	fout << "Date and time of run : " << ctime(&curr) << "Project : Lab #3" << endl << "Michael DePouw" << endl << "Matt Gordon";
	theIOMgr.newLine(fout);
	theIOMgr.popMargin();

}//putHeader


 /////////////////////////////////////////////////////////////////////SpeakMsg

	SpeakMsg::SpeakMsg(int Handler, string Description, string Speak)
		     :Message(Handler,Description)
	{
		speak = Speak;
	}

   	void SpeakMsg::Insert(ofstream &fout)
	{
		int delta = 10;

        theIOMgr.pushMargin(fout);
		theIOMgr.advanceMargin(fout);
		fout << "SpeakMsg{ ";

		Put(fout);

		theIOMgr.advanceMargin(fout);
		fout << "}SpeakMsg ";
		theIOMgr.popMargin();

	}
    
    void SpeakMsg::Put(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		Message::Put(fout);
		theIOMgr.advanceMargin(fout);
		fout << " Speak: " << speak;
		theIOMgr.popMargin();
	}


/////////////////////////////////////////////////////////////////////// Players

	Players::Players(int MaxAgents):Message(0,"Players")
	{   
		agents     = new AGENTPTR[MaxAgents];
	    agentNames = new string[MaxAgents];
		lastAgent  = -1;
	}

	Players::~Players()
	{
		delete[] agents; 
		delete[] agentNames;
	}

    AGENTPTR Players::getAgent(string playerid)
	{
		int i = 0;
        for(; i <= lastAgent ; i++){
			if( agentNames[i] == playerid ) break;
		}

		if ( i > lastAgent ) 
			throw AppError(string("Cannot Find Agent !"),
                           string("Players::getAgent(" + playerid + ")"));
		else return agents[i];
	}

	AGENTPTR Players::getOther(string playerid)
	{
		int i = 0;
		for(; i <= lastAgent ; i++){
			if( agentNames[i] == playerid ) break;
        }

		if ( i > lastAgent )
				throw AppError(string("Cannot Find other player !"),
                               string("Players::getOther("+ playerid + ")"));

		if ( i == lastAgent ) return agents[0];
		else return agents[i+1];
	}

    void Players::setAgent(string playerid, AGENTPTR agent)
	{   
		lastAgent++;
		agentNames[lastAgent] = playerid;
		agents[lastAgent]     = agent; 
	}

    void Players::Insert(ofstream &fout)
	{    
		theIOMgr.pushMargin(fout);
		theIOMgr.advanceMargin(fout);	
		 fout << "Players{ ";
		 Put(fout);
		 theIOMgr.advanceMargin(fout);
		 fout << "}Players ";
		 theIOMgr.popMargin();
	}

    void Players::Put(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		for(int i = 0; i <= lastAgent; i++ )
		{   
			if( !((i+1) % 10) ){
				theIOMgr.advanceMargin(fout);
			}//if
			fout << " agent[ " << i << " ] = " << agentNames[i];
		}//for
        theIOMgr.popMargin();
	}


	
////////////////////////////////////////////////////////////////////// Student

	Student::Student():Agent()
	{
	}

	Student::Student(ifstream& fin) throw(AppError)
	{
		Extract(fin);
	}

	Student::~Student()
	{
	}


	void Student::Extract(ifstream& fin) throw(AppError)
	{
			string token;

			// Parse opening token
			fin >> token;
			if ( token != "Student{" ) 
			    throw AppError(string("Incorrect Token, '" + token + "', excpected 'Student{' !"),
                               string("Student::Extract()"));
			
			// Parse data members
			Get(fin);

			// Parse closing token
			fin >> token; 
			if ( token != "}Student" ) 
				throw AppError(string("Incorrect Token '" + token + "', excpected token '}Student' !"),
                               string("Student::Get()"));
	}

	void Student::Get(ifstream& fin)throw(AppError)
	{
		string token;

		// parse inherited members 
		Agent::Get(fin); 
		
		// parse token "QuestDelay"
		fin >> token;
		if ( token != "questdelay:" ) 
		   throw AppError(string("Incorrect Token '" + token + "', excpected 'questdelay:' !"),
                          string("Student::Get()"));

		// Parse question delay
		fin >> token;
		questDelay = atoi(token.c_str());  
		
		// parse token "AnsrDelay"
		fin >> token; 
		if ( token != "ansrdelay:" ) 
		throw AppError(string("Incorrect Token '" + token + "', excpected 'ansrdelay:' !"),
                          string("Student::Get()"));

		// Parse answer delay
		fin >> token;
		ansrDelay = atoi(token.c_str());  
	}

	void Student::Insert(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		theIOMgr.advanceMargin(fout);
		fout << "Student{ ";
		Put(fout);
		theIOMgr.advanceMargin(fout);
		fout << "}Student ";
		theIOMgr.popMargin();
	}

	void Student::Put(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		Agent::Put(fout);
		theIOMgr.advanceMargin(fout);
		fout << " questdelay: " << questDelay;
		theIOMgr.advanceMargin(fout);
		fout << " ansrdelay: "  << ansrDelay;
		theIOMgr.popMargin();
	}


	void Student::Initialize(MSGPTR players)
	{
		// Obtaining player handles

		me  = (Student *)((Players *)players)->getAgent(NameOf());
		you = (Student *)((Players *)players)->getOther(NameOf());
       
		// Construct new message
		SpeakMsg *strMsg = you->AcceptQuestion("What is your name?");
 
		// Construct new Event
		Event e( questDelay , me , you , strMsg );

		// Post Event
		theEventMgr.postEvent(e);

	}

	 void Student::Dispatch  (MSGPTR msg)
	{
		int h          = msg->getHandler();
        string content = ((SpeakMsg *)msg)->getSpeak();
		
		if ( h == 1 ) doQuestions(content);
		if ( h == 2 ) doAnswers(content);
	}

	SpeakMsg *Student::AcceptQuestion(string aquestion)
	{
		return new SpeakMsg(1,"question",aquestion);
	}

	SpeakMsg *Student::AcceptAnswer(string response)
	{
		return new SpeakMsg(2,"answer",response);
	}

	void Student::doQuestions(string question)
	{
		int time = theEventMgr.clock() + ansrDelay ;
		SpeakMsg *strMsg = AcceptAnswer("My name is:" + NameOf() + "!" );

		Event e( time , me , you , strMsg );
		
		theEventMgr.postEvent(e);
	}


	void Student::doAnswers  (string answer)
	{
		theIOMgr.newLine(simlog);
		theIOMgr.pushMargin(simlog);
		theIOMgr.advanceMargin(simlog);
		simlog << "At time: " << theEventMgr.clock();
		simlog << ", Student: " + NameOf() + " is terminating!";
		theIOMgr.advanceMargin(simlog);
		simlog << *me;
		theIOMgr.popMargin();
		theIOMgr.newLine(simlog);
	}




////////////////////////////////////////////////////////////////////////Conversation

	Conversation::Conversation()
	{
	    ifstream  fin;

		//Open input file
		theIOMgr.getFileName("Enter input file name: ");
		theIOMgr.openFile(fin);
		
		//Create simulation log file
		theIOMgr.getFileName("Enter the simlog file name : ");
		theIOMgr.openFile(simlog);

		string token;
		// Parse opening token
		fin >> token;
		if ( token != "Conversation{" ) 
		   throw AppError(string("Incorrect Token '" + token + "', excpected token 'Conversation{' !"),
                          string("Conversation::Conversation()"));

		// parse data member # of students

		fin >> token;
		if ( token != "#students:" ) 
		     throw AppError(string("Incorrect Token '" + token + "', excpected token '#students:' !"),
                            string("Conversation::Conversation()"));
        fin >> token;
		numStudents = atoi(token.c_str());
		if( numStudents <= 0 )
            throw AppError(string("Number of students is not positive!"),
                            string("Conversation::Conversation()"));
        students    = new AGENTPTR[numStudents];
		// Construct Student instances
        for(int i = 0; i < numStudents; i++) students[i] = new Student(fin);

		// Parse closing token
		fin >> token;
		if ( token != "}Conversation" ) 
			throw AppError(string("Incorrect Token '" + token + "', excpected token '}Conversation' !"),
                          string("Conversation::Conversation()"));

		numEvents = 0;

		putHeader(simlog);

	}

	Conversation::~Conversation()
	{
		for ( int i = 0 ; i < numStudents ; i++ ) delete students[i];
		delete[] students;
	}


	void Conversation::Initialize()
	{
		// Construct new Players object
		Players *players = new Players(2);

		// Set students
		players->setAgent( students[0]->NameOf() , students[0] );  
		players->setAgent( students[1]->NameOf() , students[1] );  
	
		// Initialize students
		students[0]->Initialize(players);
		students[1]->Initialize(players);

		simlog << *players; 
  
		delete players;
	}

	void Conversation::Simulate()
	{
		Event    e;
        Message *msg;
		string   token;

		while( theEventMgr.moreEvents() )
		{
			//retrive next event and message
		//	theIOMgr.newLine(simlog);
			//simlog << theEventMgr;
			e   = theEventMgr.getNextEvent();
            msg = e.getMsg();
		
			// Output to simlog
			simlog << e;
			// Dispatch
			e.getRecvr()->Dispatch( msg );  
			
            // destruct message
			delete msg; 

			// Update statistical data
			lastEvent = e.getTime(); 
			numEvents++;
		}
	}

	void Conversation::PutState()
	{


		theIOMgr.newLine(simlog);
	    theIOMgr.pushMargin(simlog);
		simlog << "Conversation{ ";
		theIOMgr.pushMargin(simlog);
		simlog << "#students: " << numStudents;


		for (int i = 0 ; i < numStudents ; i++ ) 
		{
			theIOMgr.advanceMargin(simlog);
			simlog << *((Student *)students[i]);
		}
		theIOMgr.popMargin();
		theIOMgr.advanceMargin(simlog);
		simlog << "}Conversation ";
		theIOMgr.popMargin();
	}

	void Conversation::WrapUp()
	{

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