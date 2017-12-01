
#include "INCLUDES.h"

#include "Instructor.h"

using namespace iomgmt;

Instructor::Instructor(){
	
	Agent::Agent();
	
	officehrs = "";
}

/*
Instructor::Instructor(StringTokenizer& stok){
	
	Instructor();
}
*/
Instructor::Instructor(Tokenizer& tok){
	
	Agent::Agent(tok);
	
	officehrs = tok.Scan();
}

void Instructor::Insert(ofstream& fout){
	
	theIOMgr.pushMargin(fout);
	theIOMgr.advToMargin(fout);

	fout << "Instructor {";
	fout.flush();
	
	Put(fout);
	
	theIOMgr.advToMargin(fout);
	
	fout << "}Instructor";
	fout.flush();
	
	theIOMgr.popMargin();
}

void Instructor::Extract(ifstream& fin) throw(AppError){
	
	string token;

	fin >> token;

	if(token != "Instructor{"){
		
		throw AppError(string("Bad open token"), string("Instructor::Extract()"));
	}

	Get(fin);

	fin >> token;

	if(token != "}Instructor"){
		
		throw AppError(string("Bad close token"), string("Instructor::Extract()"));
	}
}

void Instructor::Put(ofstream& fout){
	
	theIOMgr.pushMargin(fout);

	Agent::Put(fout);

	theIOMgr.advToMargin(fout);

	fout << " officehrs: " << officehrs << " ";
	fout.flush();

	theIOMgr.popMargin();
}

void Instructor::Get(ifstream& fin) throw (AppError){
	
	string token;

	Agent::Get(fin);

	fin >> token;

	if(token != "officeHrs:"){
	
		throw AppError(string("Bad token"), string("Instructor::Get()"));
	}

	fin >> officehrs;
}
