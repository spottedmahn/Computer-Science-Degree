#ifndef _AGENT
#define _AGENT

#include "INCLUDES.h"

#include "IOMgmt.h"

using namespace std;

using namespace iomgmt;

class Agent {
	
	private:
		string name;
		int age;

	public:
		Agent();
		Agent(Tokenizer&);
		//Agent(StringTokenizer&);
		string GetName();
		int GetAge();
		friend ifstream& operator>>(ifstream& fin, Agent& agt);
		friend ofstream& operator<<(ofstream& fout, Agent& agt);
		virtual void Insert(ofstream& fout);
		virtual void Extract(ifstream& fin);

	protected:
		virtual void Get(ifstream& fin);
		virtual void Put(ofstream& fout);
};

#endif