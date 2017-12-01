#include "student.h"

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
	fout << " PID: " << pid;
	theIOMgr.advanceMargin(fout);
	fout << " course: " << course << ' ';
	theIOMgr.popMargin();
}

Student::Student()
{
	Agent::Agent();
	pid="";
	course="";
}
	
Student::Student(Tokenizer& tok)
{
	Agent::Agent(tok);
	pid=tok.Scan();
	course=tok.Scan();
}

Student::Student(StringTokenizer& strtok)
{
	// StringTokenizer class not implemented in IOMgmt.cpp
	Student();
}

void Student::Extract(ifstream& fin) throw(AppError)
{
	string token;

	// Parse opening token "Student{"
	fin >> token;
	if ( token != "Student{" ) 
		throw AppError(string("Unrecognized open token," + token + ", 'Student{' excpected!"),
                        string("Student::Extract()"));
	Get(fin);

	// Parse closing token "}Student"
	fin >> token;
	if ( token != "}Student" ) 
		throw AppError(string("Unrecognized close token," + token + ", '}Student' expected!"),
                        string("Student::Extract()")); 
}

void Student::Get(ifstream& fin) throw(AppError)
{
	string token;

	Agent::Get(fin); 

	fin >> token;
	if ( token != "PID:" ) 
		throw AppError(string("Unrecognizable data token," + token + ",'PID:' excpected!"),
                        string("Student::Get()"));
	fin >> pid;

	fin >> token;
	if ( token != "course:" ) 
		throw AppError(string("Unrecognizable data token," + token + ",'course:' excpected!"),
                        string("Student::Get()"));
	fin >> course;
} 
