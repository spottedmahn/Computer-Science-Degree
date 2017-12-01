
#include "INCLUDES.h"

#include "agent.h"

Agent::Agent(){

	name="";
	age=0;
}
	
Agent::Agent(Tokenizer& tok){

	name=tok.Scan();

	age=atoi(tok.Scan().c_str());
}

/*
Agent::Agent(StringTokenizer& strtok){

	Agent();
}
*/

ofstream& operator<<(ofstream &fout, Agent &agt){
	
	agt.Insert(fout); 
	
	return fout;
}

ifstream& operator>>(ifstream &fin, Agent &agt){

	agt.Extract(fin);
	
	return fin;
}

void Agent::Insert(ofstream &fout){

	theIOMgr.pushMargin(fout);
	theIOMgr.advToMargin(fout);
	
	fout << "Agent{ ";
	fout.flush();
	
	Put(fout);
	
	theIOMgr.advToMargin(fout);
	
	fout << "}Agent ";
	fout.flush();
	
	theIOMgr.popMargin();
}

void Agent::Extract(ifstream& fin) throw(AppError){

	string token;

	fin >> token;
	
	if (strcmp(token.c_str(), "Agent{") != 0){
	
		throw AppError(string("Bad open token."), string("Agent::Extract()"));
	}

	Get(fin);

	fin >> token;

	if (token != "}Agent"){
		
		throw AppError(string("Bad close token."), string("Agent::Extract()")); 
	}
}

void Agent::Put(ofstream &fout){
	
	theIOMgr.pushMargin(fout);
	
	fout << " name:" << name;
	fout.flush();

	theIOMgr.advToMargin(fout);
	
	fout << " age: " << age << ' ';
	fout.flush();
	
	theIOMgr.popMargin();
}

void Agent::Get(ifstream& fin) throw(AppError){
	
	string token;

	fin >> token;

	if ( token != "name:" ){
	
		throw AppError(string("Bad token."), string("Agent::Get()"));
	}
	
	fin >> name;

	fin >> token;
	
	if ( token != "age:" ){
		throw AppError(string("Bad token."), string("Agent::Get()"));
	}
	fin >> age;
} 

string Agent::GetName(){
	
	return name;
}

int Agent::GetAge(){
	
	return age;
}
