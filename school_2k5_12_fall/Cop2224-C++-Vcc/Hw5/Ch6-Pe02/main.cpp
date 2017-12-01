/************************************************
**  Name: HW5 Programming Excercise #2
**  Author: Mike DePouw
**  Date: 2005/09/22
**  Description: Implementation of HW5 Programming Excercise #2.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/


#include <iostream>
#include <cctype>

bool isVowel(char);

#define FALSE 0
#define TRUE 1

using namespace std;

int main(int argc, char* argv[]){

	
	char tmpC;
	
	cout << "Input character: ";
	cin >> tmpC;
	
	bool vowel = isVowel(tmpC);
	
	if(vowel)
		cout << tmpC << " is a vowel" << endl;
	else
		cout << tmpC << " is not a vowel" << endl;
	
	system("PAUSE");	
	return 0;
}

bool isVowel(char tmpC){
	
	tmpC = toupper(tmpC);

	if((tmpC == 'A') ||  (tmpC == 'E') || (tmpC == 'I') || (tmpC == 'O') || (tmpC == 'U'))
		return true;
	
	return false;
}
