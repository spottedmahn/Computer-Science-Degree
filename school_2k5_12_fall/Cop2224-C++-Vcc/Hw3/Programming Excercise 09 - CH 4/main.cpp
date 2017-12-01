/************************************************
**  Name: HW3 Programming Excercise #9
**  Author: Mike DePouw
**  Date: 2005/09/27
**  Description: Implementation of HW3 Programming Excercise #9.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <fstream>
#include <iomanip>

#define OUTPUT_COUT 1
#define PRECISION 2

using namespace std;

int main(int argc, char *argv[]){
	
	if(argc < 2 || argc > 3){
		
		cout << "Please enter at least one command line parameter.  The first parameter is input file name.  The second is optional and is the output file.  If no value is specified out is made to out.txt"  << endl;
	}
	
	ifstream fin;
	ofstream fout;
	
	fin.open(argv[1]);
	
	if(argc > 2){
		fout.open(argv[2]);
	}
	else{
		fout.open("out.txt");
	}
	
	float f1 = 0.0, f2 = 0.0, tmpF = 0.0;
	string tmpS;
	int lineCount = 0;
	
	cout << showpoint << fixed << setprecision(PRECISION);
	fout << showpoint << fixed << setprecision(PRECISION);
	
	while((fin >> f1) && (fin >> tmpS) && (fin >> f2)){
	
		lineCount++;
	
		if(tmpS == "*"){
			tmpF = f1 * f2;
		}
		else if(tmpS == "+"){
			tmpF = f1 + f2;
		}
		else if(tmpS == "-"){
			tmpF = f1 - f2;
		}
		else if(tmpS == "/"){
			tmpF = f1 / f2;
		}
		else{
			cout << "Invalid operator.  Input line #" << lineCount << ".  Input read in was " << f1 << " " << tmpS << " " << f2 << endl;
			continue;
		}
		if(OUTPUT_COUT){
			cout << f1 << " " << tmpS << " " << f2 << " = " << tmpF << endl;
		}
		
		fout << f1 << " " << tmpS << " " << f2 << " = " << tmpF << endl;
	}
	system("pause");
	return 0;
}