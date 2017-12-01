#include "instructor.h"

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
	fout << " officeHrs: " << officehrs << ' ';
	theIOMgr.popMargin();
}

Instructor::Instructor()
{
	Agent::Agent();
	officehrs="";
}
	
Instructor::Instructor(Tokenizer& tok)
{
	Agent::Agent(tok);
	officehrs=tok.Scan();
}

Instructor::Instructor(StringTokenizer& strtok)
{
	// StringTokenizer class not implemented in IOMgmt.cpp
	Instructor();
}

void Instructor::Extract(ifstream& fin) throw(AppError)
{
	string token;

	// Parse opening token "Instructor{"
	fin >> token;
	if ( token != "Instructor{" ) 
		throw AppError(string("Unrecognized open token," + token + ", 'Instructor{' excpected!"),
                        string("Instructor::Extract()"));
	Get(fin);

	// Parse closing token "}Instructor"
	fin >> token;
	if ( token != "}Instructor" ) 
		throw AppError(string("Unrecognized close token," + token + ", '}Instructor' expected!"),
                        string("Instructor::Extract()")); 
}

void Instructor::Get(ifstream& fin) throw(AppError)
{
	string token;

	Agent::Get(fin);

	fin >> token;
	if ( token != "officeHrs:" ) 
		throw AppError(string("Unrecognizable data token," + token + ",'officeHrs:' excpected!"),
                        string("Instructor::Get()"));
	fin >> officehrs;
} 
