/************************************************
**  Name: HW6 Chapter 12 Programming Excercise #1
**  Author: Mike DePouw
**  Date: 2005/10/20
**  Description: Implementation of HW6 Chapter 12 Programming Excercise #1.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

//assumptions
//1)input comes from a file
//2)input file name is passed as a command line parameter

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cctype>

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;

//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();

const int PRECISION = 3;

class romanType{
	
	public:
		
		romanType(string sIn =  "");
		string getRoman();
		void setRoman(string sIn);
		int getDecimal();
		
	private:
		
		void calculateDecimal();
		string roman;
		int decimal;
};

romanType::romanType(string sIn){
	roman = sIn;
}
string romanType::getRoman(){
	return roman;
}

int romanType::getDecimal(){
	return decimal;
}

void romanType::setRoman(string sIn){
	roman = sIn;
	calculateDecimal();
}

void romanType::calculateDecimal(){
	
	decimal = 0;
	
	for(int i=0;i < roman.length();i++){
		
		switch(roman.at(i)){
			
			case 'M':
				decimal += 1000;
				break;
			case 'D':
				decimal += 500;
				break;
			case 'C':
				decimal += 100;
				break;
			case 'L':
				decimal += 50;
				break;
			case 'X':
				decimal += 10;
				break;
			case 'V':
				decimal += 5;
				break;
			case 'I':
				decimal += 1;
				break;
			default:
				break;
		}
	}

}

int main(int argc, char* argv[]){

	bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	romanType rm;
	
	string tmpS;
	
	while(fin >> tmpS){
	
		rm.setRoman(tmpS);
		
		fout << rm.getRoman() << " is equal to " << rm.getDecimal() << " in decimal" << endl;
	}

	//system("PAUSE");
	return 0;
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
