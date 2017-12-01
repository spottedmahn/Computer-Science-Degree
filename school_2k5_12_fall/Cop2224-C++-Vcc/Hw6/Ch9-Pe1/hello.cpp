/************************************************
**  Name: HW6 Chapter 9 Programming Excercise #1
**  Author: Mike DePouw
**  Date: 2005/10/19
**  Description: Implementation of HW6 Chapter 9 Programming Excercise #1.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>

using namespace std;

int main(int argc, char* argv[]){

	double alpha[50];
	
	int i;
	
	for(i=0;i < 25;i++){		
		alpha[i] = i*i;
	}
	
	for(;i < 50;i++){		
		alpha[i] = 3*i;
	}
	
	for(i=1;i < 51;i++){
		
		cout << alpha[i-1] << " ";
		
		if(i % 10 == 0){
			cout << endl;
		}
	}	
	system("PAUSE");
	return 0;
}