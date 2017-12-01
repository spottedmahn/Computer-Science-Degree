/************************************************
**  Name: HW8 Programming Excercise #02 & 07
**  Author: Mike DePouw
**  Date: 2005/12/01
**  Description: Implementation of HW8 Programming Excercise #02 & 07.
**  Complied With: CodeWarrior 4.9.9.2
************************************************/

#include <cstdlib>
#include <iostream>
#include <ctype.h>
//#include <string.h>

using namespace std;

int main(int argc, char *argv[]){
    
    char* tmpS;
    tmpS = new char[1000];
    cout << "Please enter a string less than 1000 chars in length" << endl;
    cin >> tmpS;
    cout << strupr(tmpS);
    system("PAUSE");
    return EXIT_SUCCESS;
}
