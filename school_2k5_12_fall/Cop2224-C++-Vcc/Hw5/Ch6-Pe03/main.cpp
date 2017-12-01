/************************************************
**  Name: HW5 Programming Excercise #3
**  Author: Mike DePouw
**  Date: 2005/10/17
**  Description: Implementation of HW5 Programming Excercise #3.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/


#include <iostream>
#include <cctype>

bool isVowel(char);

using namespace std;

int main(int argc, char* argv[]){

	string tmpS;
	
	cin >> tmpS;
	
	int vowelCount = 0;
	
	for(int i=0;i < tmpS.length();i++){
		
		if(isVowel(tmpS[i]))
			vowelCount++;
	}
	
	cout << "# of vowels is " << vowelCount;
	return 0;
}


bool isVowel(char tmpC){
	
	tmpC = toupper(tmpC);

	if((tmpC == 'A') ||  (tmpC == 'E') || (tmpC == 'I') || (tmpC == 'O') || (tmpC == 'U'))
		return true;
	
	return false;
}