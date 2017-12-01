
#include "INCLUDES.h"

#include "student.h"

using namespace iomgmt;

Student::Student(){
	
	Agent::Agent();
	
	pid="";
	
	course="";
}
	
Student::Student(Tokenizer& tok){
	
	Agent::Agent(tok);
	
	pid=tok.Scan();
	
	course=tok.Scan();
}
/*
Student::Student(StringTokenizer& strtok){

	Student();
}
*/
void Student::Insert(ofstream &fout){
	
	theIOMgr.pushMargin(fout);
	theIOMgr.advToMargin(fout);
	
	fout << "Student{ ";
	fout.flush();
	
	Put(fout);
	
	theIOMgr.advToMargin(fout);
	
	fout << "}Student ";
	fout.flush();
	
	theIOMgr.popMargin();
}

void Student::Put(ofstream &fout){
	
	theIOMgr.pushMargin(fout);
	
	Agent::Put(fout);
	
	theIOMgr.advToMargin(fout);
	
	fout << " PID: " << pid;
	fout.flush();
	
	theIOMgr.advToMargin(fout);
	
	fout << " course: " << course << ' ';
	fout.flush();
	
	theIOMgr.popMargin();
}

void Student::Extract(ifstream& fin) throw(AppError){
	
	string token;

	fin >> token;

	if (strcmp(token.c_str(), "Student{") != 0){
		
		throw AppError(string("Bad open token."), string("Student::Extract()"));
	}

	Get(fin);

	fin >> token;
	
	if (strcmp(token.c_str(), "}Student") != 0){
		
		throw AppError(string("Bad close token"), string("Student::Extract()"));
	}
}

void Student::Get(ifstream& fin) throw(AppError){
	
	string token;

	Agent::Get(fin); 

	fin >> token;
	
	if (strcmp(token.c_str(), "PID:") != 0){
		
		throw AppError(string("Bad token."), string("Student::Get()"));
	}

	fin >> pid;

	fin >> token;
	
	if (strcmp(token.c_str(), "course:") != 0){
		
		throw AppError(string("Bad token."), string("Student::Get()"));
	}

	fin >> course;
} 
