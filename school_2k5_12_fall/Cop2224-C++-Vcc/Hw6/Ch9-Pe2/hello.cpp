/************************************************
**  Name: HW6 Chapter 9 Programming Excercise #2
**  Author: Mike DePouw
**  Date: 2005/10/19
**  Description: Implementation of HW6 Chapter 9 Programming Excercise #2.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>

using namespace std;

int smallestIndex(int[],int);

int main(int argc, char* argv[]){

	
	int test[10] = {10,32,545,43,30,84,3,1,4,8};
	
	cout << "Smallest index is: " << smallestIndex(test, 10);
	
	system("PAUSE");
	return 0;
}

int smallestIndex(int arrayIn[], int sizeIn){
	
	int smallestIndexValue = arrayIn[0];
	int smallestIndex = 0;
	
	for(int i=1;i < sizeIn;i++){
		
		if(smallestIndexValue > arrayIn[i]){
			
			smallestIndexValue = arrayIn[i];
			smallestIndex = i;
		}
	}
	
	return smallestIndex;
}