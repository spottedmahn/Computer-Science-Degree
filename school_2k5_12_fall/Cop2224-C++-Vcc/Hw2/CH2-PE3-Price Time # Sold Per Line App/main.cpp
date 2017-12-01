/************************************************
**  Name: HW2 Programming Excercise #3
**  Copyright: 
**  Author: Mike DePouw
**  Date: 2005/09/24
**  Description: Implementation of HW2 Programming Excercise #3.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

#define DEBUG 0
#define PRECISION 2

//assumes 2 command line arguments; 1st is input file name, 2nd is output file name
int main(int argc, char *argv[]){

	string tmpS;
	char tmpC;
	
	float ticketsSold = 0.0;
	float totSale = 0.0;
	
	if(DEBUG){
		cout << "argc == " << argc << endl;
		cout << "argv[0] == " << argv[0] << " " << "argv[1] == " <<  argv[1] << " " <<  "argv[2] == " << argv[2] << endl;
	}
	if(argc != 3){
		cout << "Please run with 2 command line parameters; fileIn and fileOut" << endl;
		cout << "BYE";
		cin >> tmpC;
		return 0;
	}
	
	ifstream fileIn;
	ofstream fileOut;
	
	fil
	fileIn.open(argv[1]);
	fileOut.open(argv[2]);
	
	fileOut << fixed << showpoint << setprecision(PRECISION);
	cout << fixed << showpoint << setprecision(PRECISION);	
	
	int tmpI = 0;
	float tmpF, tmpF2;
	
	//looping thru all lines in the text file
	//assumes to floats per line
	while((fileIn >> tmpF) && (fileIn >> tmpF2)){
		
		totSale += tmpF * tmpF2;
		ticketsSold += tmpF2;
	}
	
	if(DEBUG){
		cout << "Total Sales: $" << totSale << "\t" <<  "Total Tickets Sold: " << ticketsSold;
	}
	fileOut << "Total Sales: $" << totSale << "\t" << noshowpoint << setprecision(0) <<  "Total Tickets Sold: " << ticketsSold;
	fileOut.flush();
	fileIn.close();
	fileOut.close();
	return 0;
}