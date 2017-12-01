/************************************************
**  Name: HW2 Programming Excercise #4
**  Copyright: 
**  Author: Mike DePouw
**  Date: 2005/09/22
**  Description: Implementation of HW2 Programming Excercise #4.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <cstdlib>
#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

//no data validation
//input comes from cin
//writes output to out.txt
int main(int argc, char *argv[])
{
    float assessedIn = 0.0;
    
    cout << "Enter the assessed value of the property: ";
    cin >> assessedIn;

    ofstream fOut;
    fOut.open("out.txt");
    
    fOut << fixed << showpoint << setprecision(2);
    
    fOut << setw(30) << left << "Assessed Value:"  << setw(20) << right << assessedIn << endl;
    fOut << setw(30) << left << "Taxalbe Amount:" << setw(20) << right << assessedIn * .92 << endl;
    fOut << setw(30) << left << "Tax Rate for each $100.00:" << setw(20) << right << "1.05" << endl;
    fOut << setw(30) << left << "Property Tax:" << setw(20) << right << (assessedIn * .92)*.05 << endl;
    //system("PAUSE");
    return EXIT_SUCCESS;
}
