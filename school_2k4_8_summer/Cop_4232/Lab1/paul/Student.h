#ifndef _STUDENT
#define _STUDENT
#include <iostream>
#include <fstream>
#include <string>

#include "Agent.h"
#include "IOMgmt.h"

using namespace std;


class Student : public Agent {
      public:
              Student();
              Student(iomgmt::Tokenizer t);
              Student(iomgmt::StringTokenizer st);

              string	getPID();
              string	getCourse();
			 
              virtual void  Extract(ifstream& fin );
              virtual void  Insert( ofstream& fout );
      protected:
              virtual void Get( ifstream& fin);
              virtual void Put( ofstream& fout);
      private:
              string pid;
              string course;        // unit cost in cents
};

#endif