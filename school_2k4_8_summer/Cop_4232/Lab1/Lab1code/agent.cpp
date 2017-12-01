#include "agent.h"

void Agent::Insert(ofstream &fout)
{
	theIOMgr.pushMargin(fout);
	theIOMgr.advanceMargin(fout);
	fout << "Agent{ ";
	Put(fout);
	theIOMgr.advanceMargin(fout);
	fout << "}Agent ";
	theIOMgr.popMargin();
}

void Agent::Put(ofstream &fout)
{
	theIOMgr.pushMargin(fout);
	fout << " Name: " << name;
	theIOMgr.advanceMargin(fout);
	fout << " Age: " << age << ' ';
	theIOMgr.popMargin();
}

ofstream& operator<<(ofstream &fout, Agent &agnt)
{
	agnt.Insert(fout); 
	return fout;
}

Agent::Agent()
{
	name="";
	age=0;
}
	
Agent::Agent(Tokenizer& tok)
{
	name=tok.Scan();
	age=atoi(tok.Scan().c_str());
}

Agent::Agent(StringTokenizer& strtok)
{
	// StringTokenizer class not implemented in IOMgmt.cpp
	Agent();
}

void Agent::Extract(ifstream& fin) throw(AppError)
{
	string token;

	// Parse opening token "Agent{"
	fin >> token;
	if ( token != "Agent{" ) 
		throw AppError(string("Unrecognized open token," + token + ", 'Agent{' excpected!"),
                        string("Agent::Extract()"));
	Get(fin);

	// Parse closing token "}Agent"
	fin >> token;
	if ( token != "}Agent" ) 
		throw AppError(string("Unrecognized close token," + token + ", '}Agent' expected!"),
                        string("Agent::Extract()")); 
}

void Agent::Get(ifstream& fin) throw(AppError)
{
	string token;

	fin >> token;
	if ( token != "name:" ) 
		throw AppError(string("Unrecognizable data token," + token + ",'name:' excpected!"),
                        string("Agent::Get()"));
	fin >> name;

	fin >> token;
	if ( token != "age:" ) 
		throw AppError(string("Unrecognizable data token," + token + ",'age:' excpected!"),
                        string("Agent::Get()"));
	fin >> age;
} 

ifstream& operator>>(ifstream &fin, Agent &agnt)
{
	agnt.Extract(fin);
	return fin;
}

string Agent::GetName()
{
	return name;
}

int Agent::GetAge()
{
	return age;
}

void Agent::SetName(string newname)
{
	name=newname;
}

void Agent::SetAge(int newage)
{
	age=newage;
}
