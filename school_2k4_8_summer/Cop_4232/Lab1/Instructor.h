#ifndef _INSTRUCTOR
#define _INSTRUCTOR

#include "INCLUDES.h"

#include "agent.h"
#include "IOMgmt.h"

using namespace std;

using namespace iomgmt;

class Instructor: public Agent{
	
	private:
		string officehrs;
	
	public:
		Instructor();
		Instructor(Tokenizer& tok);
		//Instructor(StringTokenizer& stok);
		virtual void Insert(ofstream& fout);
		virtual void Extract(ifstream& fin);
	
	protected:
		virtual void Get(ifstream& fin);
		virtual void Put(ofstream& fout);
};

#endif