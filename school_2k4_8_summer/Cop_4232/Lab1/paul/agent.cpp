#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstdio>

using namespace std;

#include "Agent.h"
#include "IOMgmt.h"

	
//Agent methods
    Agent::Agent()
	{
		name = "";
		age = 0;

	}

    Agent::Agent(iomgmt::Tokenizer){}

	Agent::Agent(iomgmt::StringTokenizer){}

	string Agent::getName(){return name;}

	int Agent::getAge(){return age;}
   
    void operator <<(ifstream&, Agent&);

	void operator >>(ofstream&, Agent&);


	void  Agent::Extract(ifstream& fin ) 
	{
           string openTkn, closeTkn, name;
		   int age;
           fin >> openTkn;
		   fin>> name;
		   fin>> age;
           //error check: opentkn == "Agent{"
           Get( fin );
           fin >> closeTkn;
           //error check: closetkn == "}"
    }

    void  Agent::Insert( ofstream& fout )
	{
           fout << endl;
           fout << " Agent{ ";
           Put( fout );
           fout << " }Agent ";
    }

    void Agent::Get( ifstream& fin) 
	{
           string label1, label2, label3;
           fin  >> label1 >> name
                >> label2 >> age;
                
           //error checks: label1 == "name:" &&
           //              label2 == "age:" &&
           
    }



    void  Agent::Put ( ofstream& fout)
	{
           fout << " name: " << name
                << " age: " << age;
    }

