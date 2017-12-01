	Instructor::Instructor():Agent()
	{
	}

	Instructor::Instructor(ifstream& fin) throw(AppError)
	{
		Extract(fin);
	}

	Instructor::~Instructor()
	{
	}


	void Instructor::Extract(ifstream& fin) throw(AppError)
	{
			string token;

			// Parse opening token
			fin >> token;
			if ( token != "Instructor{" ) 
			    throw AppError(string("Incorrect Token, '" + token + "', excpected 'Instructor{' !"),
                               string("Instructor::Extract()"));
			
			// Parse data members
			Get(fin);

			// Parse closing token
			fin >> token; 
			if ( token != "}Instructor" ) 
				throw AppError(string("Incorrect Token '" + token + "', excpected token '}Instructor' !"),
                               string("Instructor::Get()"));
	}

	void Instructor::Get(ifstream& fin)throw(AppError)
	{
		string token;

		// parse inherited members 
		Agent::Get(fin); 
		
		// parse token "QuestDelay"
		fin >> token;
		if ( token != "questdelay:" ) 
		   throw AppError(string("Incorrect Token '" + token + "', excpected 'questdelay:' !"),
                          string("Instructor::Get()"));

		// Parse question delay
		fin >> token;
		questDelay = atoi(token.c_str());  
		
		// parse token "AnsrDelay"
		fin >> token; 
		if ( token != "ansrdelay:" ) 
		throw AppError(string("Incorrect Token '" + token + "', excpected 'ansrdelay:' !"),
                          string("Instructor::Get()"));

		// Parse answer delay
		fin >> token;
		ansrDelay = atoi(token.c_str());  
	}

	void Instructor::Insert(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		theIOMgr.advanceMargin(fout);
		fout << "Instructor{ ";
		Put(fout);
		theIOMgr.advanceMargin(fout);
		fout << "}Instructor ";
		theIOMgr.popMargin();
	}

	void Instructor::Put(ofstream &fout)
	{
		theIOMgr.pushMargin(fout);
		Agent::Put(fout);
		theIOMgr.advanceMargin(fout);
		fout << " questdelay: " << questDelay;
		theIOMgr.advanceMargin(fout);
		fout << " ansrdelay: "  << ansrDelay;
		theIOMgr.popMargin();
	}


	void Instructor::Initialize(MSGPTR players)
	{
		// Obtaining player handles

		me  = (Instructor *)((Players *)players)->getAgent(NameOf());
		you = (Instructor *)((Players *)players)->getOther(NameOf());
       
		// Construct new message
		SpeakMsg *strMsg = you->AcceptQuestion("What is your name?");
 
		// Construct new Event
		Event e( questDelay , me , you , strMsg );

		// Post Event
		theEventMgr.postEvent(e);

	}

	 void Instructor::Dispatch  (MSGPTR msg)
	{
		int h          = msg->getHandler();
        string content = ((SpeakMsg *)msg)->getSpeak();
		
		if ( h == 1 ) doQuestions(content);
		if ( h == 2 ) doAnswers(content);
	}

	SpeakMsg *Instructor::AcceptQuestion(string aquestion)
	{
		return new SpeakMsg(1,"question",aquestion);
	}

	SpeakMsg *Instructor::AcceptAnswer(string response)
	{
		return new SpeakMsg(2,"answer",response);
	}

	void Instructor::doQuestions(string question)
	{
		int time = theEventMgr.clock() + ansrDelay ;
		SpeakMsg *strMsg = AcceptAnswer("My name is:" + NameOf() + "!" );

		Event e( time , me , you , strMsg );
		
		theEventMgr.postEvent(e);
	}


	void Instructor::doAnswers  (string answer)
	{
		theIOMgr.pushMargin(simlog);
		theIOMgr.advanceMargin(simlog);
		simlog << "At time: " << theEventMgr.clock();
		simlog << ", Instructor: " + NameOf() + " is terminating!";
		theIOMgr.advanceMargin(simlog);
		simlog << *me;
		theIOMgr.popMargin();
	}