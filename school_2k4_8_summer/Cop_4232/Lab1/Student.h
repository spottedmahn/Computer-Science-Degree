#ifndef _STUDENT
#define _STUDENT

#include "INCLUDES.h"

using namespace std;

#include "agent.h"
#include "IOMgmt.h"

using namespace iomgmt;

class Student: public Agent {
	
	private:
		string course;
		string pid;

	public:
		Student();
		Student(Tokenizer& tok);
		//Student(StringTokenizer& stok);
		virtual void Insert(ofstream& fout);
		virtual void Extract(ifstream& fin);

	protected:
		virtual void Get(ifstream& fin);
		virtual void Put(ofstream& fout);
};

#endif