#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstdio>

using namespace std;

#include "Instructor.h"
#include "IOMgmt.h"
//#include "Instructor.h"
//#include "Student.h"

	
//Instructor methods
    Instructor::Instructor(){ofcHours = "";	}

    Instructor::Instructor(iomgmt::Tokenizer){}

	Instructor::Instructor(iomgmt::StringTokenizer){}

	string Instructor::getOfficeHours(){return ofcHours;}


	void  Instructor::Extract(ifstream& fin ) //throw (IOError) 
	{
           string openTkn, closeTkn, ofcHours;
		  
           fin >> openTkn;
		   fin>> ofcHours;
		  
           //error check: opentkn == "Agent{"
           Get( fin );
           fin >> closeTkn;
           //error check: closetkn == "}"
    }

    void  Instructor::Insert( ofstream& fout )
	{
           fout << endl;
           fout << " Instructor{ ";
           Put( fout );
           fout << " }Instructor ";
    }

    void Instructor::Get( ifstream& fin) 
	{

		Agent::Get(fin);

        string label1;
           
		fin  >> label1 >> ofcHours;
                
                
           //error checks: label1 == "officeHrs:" &&
           
    }



    void  Instructor::Put ( ofstream& fout)
	{
           fout << " officeHrs: " << ofcHours;
              
    }

