/************************************************
**  Name: HW2 Programming Excercise #5
**  Copyright: 
**  Author: Mike DePouw
**  Date: 23/09/05 15:35
**  Description: Implementation of HW2 Programming Excercise #5.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <cstdlib>
#include <iostream>

using namespace std;


//Simple main; assumes input is an int
int main(int argc, char *argv[]){

    int temperatureFIn;
    int temperatureFOut;
    
    cout << "Please enter the temperature in Fahrenheit as in integer: ";
    cin >> temperatureFIn;
    
    temperatureFOut = ((5.0/9.0) * (temperatureFIn - 32));
    
    cout << "Temperature in Fahrenheit: " << temperatureFIn << endl;
    cout << "Temperature in Centigrade: " << temperatureFOut << endl;   
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
