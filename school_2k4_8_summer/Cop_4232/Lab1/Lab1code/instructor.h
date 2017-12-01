#ifndef _INSTRUCTOR
#define _INSTRUCTOR
#include <string>
using namespace std;

#include "iomgmt.h"
using namespace iomgmt;

#include "agent.h"

class Instructor: public Agent {
      private:
          string officehrs;
      public:
          Instructor();
          Instructor(Tokenizer& tok); 
		  Instructor(StringTokenizer& strtok);
	  protected:		  
		  void Put (ofstream& fout);
		  void Get (ifstream& fin);
		  virtual void Extract (ifstream& fin);
		  virtual void Insert (ofstream& fout);
};
#endif