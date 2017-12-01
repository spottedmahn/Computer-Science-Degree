#ifndef _AGENT
#define _AGENT
#include <string>
using namespace std;

#include "iomgmt.h"
using namespace iomgmt;

class Agent {
      private:
          string name;
          int age;
      public:
          Agent();
          Agent(Tokenizer& tok); 
		  Agent(StringTokenizer& strtok);
		  friend ofstream& operator<<( ofstream& fout, Agent& agnt);
		  friend ifstream& operator>>( ifstream& fin, Agent& agnt);
		  string GetName();
		  int GetAge();
		  void SetName(string name);
		  void SetAge(int age);
	  protected:		  
		  virtual void Put (ofstream& fout);
		  virtual void Get (ifstream& fin);
		  virtual void Extract (ifstream& fin);
		  virtual void Insert (ofstream& fout);
};
#endif