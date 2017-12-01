/************************************************
**  Name: HW2 Programming Excercise #2
**  Copyright: 
**  Author: Mike DePouw
**  Date: 2005/09/24
**  Description: Implementation of HW2 Programming Excercise #2.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

//assumes input is a valid a floating point #  	
int main( void ){
	
	float tmpF = 1;
	cout << "Please enter a decimal #: ";
	cin >> tmpF;
	
	cout << fixed << showpoint << setprecision(2);
	
	cout << endl << tmpF << endl;
	
	return 0;
}