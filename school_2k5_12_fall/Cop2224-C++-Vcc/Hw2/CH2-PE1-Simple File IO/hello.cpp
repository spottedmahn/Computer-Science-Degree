/************************************************
**  Name: HW2 Programming Excercise #1
**  Copyright: 
**  Author: Mike DePouw
**  Date: 2005/09/22
**  Description: Implementation of HW2 Programming Excercise #1.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <fstream>

using namespace std;

//Assumes input comes from a file called 'in.txt' and alwasy outputs to 'out.txt'
//Also no error handling or data validation
int main(){

	ifstream fileIn;
	ofstream fileOut;
	
	fileIn.open("in.txt");
	fileOut.open("out.txt");
	
	int tmpI, tmpI2;
	char tmpC;
	
	fileIn >> tmpI >> tmpI2;
	fileOut << "Sum of " << tmpI << " and " << tmpI2 << " = " << tmpI + tmpI2 << "." << endl;
	fileOut.flush();
	
	fileIn >> tmpC;
	fileOut << "The character that comes after " << tmpC << " in the ASCII set is " << (char) (tmpC + 1) << "." << endl;
	fileOut.flush();
	
	fileIn >> tmpI >> tmpI2;
	fileOut << "The product of " << tmpI << " and  " << tmpI2 << " = " << tmpI * tmpI2 << "." << endl;
	fileOut.flush();
	
	fileIn.close();
	fileOut.close();
	
	return 0;
}