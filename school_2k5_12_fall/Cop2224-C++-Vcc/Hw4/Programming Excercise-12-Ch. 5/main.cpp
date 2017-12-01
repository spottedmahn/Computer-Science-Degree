/************************************************
**  Name: HW4 Programming Excercise #12
**  Author: Mike DePouw
**  Date: 2005/09/22
**  Description: Implementation of HW4 Programming Excercise #12.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

//assumptions
//1)input comes from a file
//2)input file name is passed as a command line parameter

#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <sstream>

#define FALSE 0
#define TRUE 1

#define PRECISION 2

#define INTEREST 0.10

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;

//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();

int main(int argc, char* argv[]){
	
	bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	float initialInvest = 0.0;
	float compoundedAmt = 0.0;
	
	fin >> initialInvest;
	
	compoundedAmt = initialInvest;
	//calc amount after 60 years
	for(int i=1;i <= 60;i++){
		
		compoundedAmt *= (1 + INTEREST);
	}
	 
	fout << "The initial investment was $" << initialInvest << ".  The total amount accumulated after " << "60" << " years, if $"  << initialInvest << " is allowed to compound with an interest of " << 100*INTEREST << "%, comes to $" << compoundedAmt << endl;
	
	compoundedAmt *= (1 + INTEREST);
	
	fout << "The total amount accumulated after " << "61" << " years, if $"  << initialInvest << " is allowed to compound with an interest of " << 100*INTEREST << "%, comes to $" << compoundedAmt << endl;
	
	cleanUp();
	
	return 0;
}

bool init(int *argc, char *argv[]){

	bool result = processArgs(argc, argv);
	
	if(!result){
		return FALSE;
	}
	
	fout << fixed << showpoint << setprecision(PRECISION);
	
	return TRUE;
}

bool cleanUp(){
	
	fin.close();
	fout.close();
	
	return TRUE;
}

bool processArgs(int *argc, char *argv[]){
	
	if(*argc < 2 || *argc > 3){
		
		cout << "Please enter at least one command line parameter and no more than 2.  The first parameter is input file name.  The second is optional and is the output file.  If no value is specified out is made to out.txt"  << endl;
	}
	
	if(*argc >= 2){
			
		fin.open(argv[1]);	
	}
	else{
		return FALSE;
	}

	if(*argc >= 3){
		fout.open(argv[2]);
	}
	else{
		fout.open("out.txt");
	}
	
	return TRUE;
}