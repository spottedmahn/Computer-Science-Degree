#ifndef _AGENT
#define _AGENT
#include <iostream>
#include <fstream>
#include <string>
#include "IOMgmt.h"
using namespace std;


class Agent {
      public:
              Agent();
              Agent(iomgmt::Tokenizer t);
              Agent(iomgmt::StringTokenizer st);

              string getName();
              int     getAge();
			 
              virtual void  Extract(ifstream& fin );
              virtual void  Insert( ofstream& fout );
      protected:
              virtual void Get( ifstream& fin);
              virtual void Put( ofstream& fout);
      private:
              string name;
              int    age;        // unit cost in cents
};

#endif