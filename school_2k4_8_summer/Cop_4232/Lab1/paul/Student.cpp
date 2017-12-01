#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstdio>

using namespace std;

#include "Student.h"
#include "IOMgmt.h"

	
//Student methods
    Student::Student()
	{
		pid = "";
		course = "";

	}

    Student::Student(iomgmt::Tokenizer){}

	Student::Student(iomgmt::StringTokenizer){}

	string Student::getPID(){return pid;}

	string Student::getCourse(){return course;}


	void  Student::Extract(ifstream& fin ) 
	{
           string openTkn, closeTkn, pid, course;
           fin >> openTkn;
		   fin>> pid;
		   fin>> course;
           //error check: opentkn == "Agent{"
           Get( fin );
           fin >> closeTkn;
           //error check: closetkn == "}"
    }

    void  Student::Insert( ofstream& fout )
	{
           fout << endl;
           fout << " Student{ ";
           Put( fout );
           fout << " }Student ";
    }

    void Student::Get( ifstream& fin) 
	{
           string label1, label2;
           fin  >> label1 >> pid
                >> label2 >> course;
                
           //error checks: label1 == "PID:" &&
           //              label2 == "course:" &&
           
    }



    void  Student::Put ( ofstream& fout)
	{
           fout << " PID: " << pid
                << " course: " << course;
    }

