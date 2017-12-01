
/************************************************
**  Name: HW8 Programming Excercise #02 & 07
**  Author: Mike DePouw
**  Date: 2005/12/01
**  Description: Implementation of HW8 Programming Excercise #02 & 07.
**  Complied With: CodeWarrior 4.9.9.2
************************************************/

//assumptions
//1)input comes from a file
//2)input file name is passed as a command line parameter

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cctype>
#include <cstdlib>
#include <string>
#include "dateType.h"

#define PRECISION 2

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;

//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();

using namespace std;

int main(int argc, char *argv[]){
	
	bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	string command;
	dateType tmpDate;
	
	while(fin >> command){
          
          char* p = new char [command.length() + 1];
          p = (char *) command.c_str();
          command = strupr(p);
             
          if(command.compare("NEW") == 0){
               tmpDate.setDate(0, 0, 0);
          }
          else if(command.compare("SET") == 0){
                int i, j, k;
                fin >> i;
                fin >> j;
                fin >> k;
                tmpDate.setDate(i, j, k);                    
          }
          else if(command.compare("PRINT") == 0){
               tmpDate.printDateTwo(fout);
          }
          else if(command.compare("ISVALIDDATE") == 0){
               
               if(tmpDate.isValidDate(tmpDate.getMonth(), tmpDate.getDay(), tmpDate.getYear())){
                        fout << "The current date is valid" << endl;                                           
               }
               else{
                    fout << "The current date is not valid" << endl;     
               }     
          }                 
          else if(command.compare("ISLEAPYEAR") == 0){
               
               if(tmpDate.isLeapYear()){
                        fout << "The current date is a leap year" << endl;                                           
               }
               else{
                    fout << "The current date is not a leap year" << endl;     
               }    
          }
          else if(command.compare("ADD") == 0){
               int tmpI;
               fin >> tmpI;
               tmpDate.addDays(tmpI);
               fout << "The new date is: ";
               tmpDate.printDateTwo(fout);
               //fout << endl;
          }     
          else if(command.compare("DAYSINMONTH") == 0){
               int tmpI;
               fin >> tmpI;
               tmpI = tmpDate.daysInMonth(tmpI);
               fout << "There are: " << tmpI << " days in that month" << endl; 
          }
          else if(command.compare("DAYSPASSED") == 0){
               
               fout << tmpDate.daysPassed() << " days have passed." << endl;
          }
          else if(command.compare("DAYSREMAINING") == 0){
               
               fout << tmpDate.daysRemaining() << " days have passed." << endl;
          }          
    }

	cleanUp();
	
    system("PAUSE");
    return EXIT_SUCCESS;	
}

bool init(int *argc, char *argv[]){

	bool result = processArgs(argc, argv);
	
	if(!result){
		return false;
	}
	
	fout << fixed << showpoint << setprecision(PRECISION);
	
	return true;
}

bool cleanUp(){
	
	fin.close();
	fout.close();
	
	return true;
}
bool processArgs(int *argc, char *argv[]){
	
	if(*argc < 2 || *argc > 3){
		
		cout << "Please enter at least one command line parameter and no more than 2.  The first parameter is input file name.  The second is optional and is the output file.  If no value is specified out is made to out.txt"  << endl;
	}
	
	if(*argc >= 2){
			
		fin.open(argv[1]);	
	}
	else{
		return false;
	}

	if(*argc >= 3){
		fout.open(argv[2]);
	}
	else{
		fout.open("out.txt");
	}
	
	return true;
}





