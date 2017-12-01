/************************************************
**  Name: HW4 Programming Excercise #11
**  Author: Mike DePouw
**  Date: 2005/10/03
**  Description: Implementation of HW4 Programming Excercise #11.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

//assumptions
//1)input comes from a file
//2)input file name is passed as a command line parameter

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cctype>

typedef char gender;
typedef float gpa;

#define FALSE 0
#define TRUE 1

#define PRECISION 2

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;

//data structure to represent one student	
struct student{
	
	gender GENDER;
	gpa GPA;
	
}*tmpStudent;

//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();
student* readLine();


int main(int argc, char *argv[]){
	
	bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	int maleCount = 0;
	float maleSumGPA = 0.0;
	
	int femaleCount = 0;
	float femaleSumGPA = 0.0;
	
	//forever
	for(;;){
	
		//readline one line of input
		tmpStudent = readLine();
		
		//if EOF
		if(tmpStudent->GPA == -1.0){
			delete tmpStudent;
			break;
		}
		
		//if male student
		if(toupper(tmpStudent->GENDER) == 'M'){
		
			maleCount++;
			maleSumGPA += tmpStudent->GPA;
		}
		//otherwise must be a female
		else{
			
			femaleCount++;
			femaleSumGPA += tmpStudent->GPA;
		}
		
		delete tmpStudent;
	}
	
	//output male average avoid division by zero
	if(maleCount){
	
		fout << "Male GPA Average: " << maleSumGPA/maleCount << endl;
	}
	
	//output female average avoid division by zero
	if(femaleCount){
	
		fout << "Female GPA Average: " << femaleSumGPA/femaleCount << endl;
	}
	
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

student* readLine(){
	
	student *oneLine = new student;
	
	if(!(fin >> oneLine->GENDER >> oneLine->GPA)){
		
		oneLine->GPA = -1.0;
	}
	
	return oneLine;
}