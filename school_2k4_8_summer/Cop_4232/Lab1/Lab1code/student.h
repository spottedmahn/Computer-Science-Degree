#ifndef _STUDENT
#define _STUDENT
#include <string>
using namespace std;

#include "iomgmt.h"
using namespace iomgmt;

#include "agent.h"

class Student: public Agent {
      private:
          string course;
          string pid;
      public:
          Student();
          Student(Tokenizer& tok); 
		  Student(StringTokenizer& strtok);
	  protected:	  
		  virtual void Put (ofstream& fout);
		  virtual void Get (ifstream& fin);
		  virtual void Extract (ifstream& fin);
		  virtual void Insert (ofstream& fout);
};
#endif