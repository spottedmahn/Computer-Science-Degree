#ifndef _INSTURCTOR
#define _INSTURCTOR
#include <iostream>
#include <fstream>
#include <string>

#include "Agent.h"
#include "IOMgmt.h"

using namespace std;


class Instructor : public Agent{
      public:
              Instructor();
              Instructor(iomgmt::Tokenizer t);
              Instructor(iomgmt::StringTokenizer st);

              string getOfficeHours();
              
              virtual void  Extract(ifstream& fin );
              virtual void  Insert( ofstream& fout );
      protected:
              virtual void Get( ifstream& fin);
              virtual void Put( ofstream& fout);
      private:
              string ofcHours;
             
};

#endif