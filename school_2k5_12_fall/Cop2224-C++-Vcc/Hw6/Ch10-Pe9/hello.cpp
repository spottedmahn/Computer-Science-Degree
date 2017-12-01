/************************************************
**  Name: HW6 Chapter 9 Programming Excercise #2
**  Author: Mike DePouw
**  Date: 2005/10/19
**  Description: Implementation of HW6 Chapter 9 Programming Excercise #2.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <vector>

using namespace std;

void bubbleSort(vector<string>&);

int main(int argc, char* argv[]){

	vector<string> tmpVS(10);
	tmpVS[0] = "MIKE";
	tmpVS[1] = "mike";
	tmpVS[2] = "spottedmahn";
	tmpVS[3] = "ray";
	tmpVS[4] = "RAY";
	tmpVS[5] = "BILL";
	tmpVS[6] = "smith";
	tmpVS[7] = "jim";
	tmpVS[8] = "andrea";
	tmpVS[9] = "barb";
	
	bubbleSort(tmpVS);
	
	for(int i = 0;i < tmpVS.size();i++){	
		cout << "tmpVS[" << i << "] == " << tmpVS[i] << endl;
	}
	system("PAUSE");
	return 0;
}

void bubbleSort(vector<string> &tmpVS){
	
	string tmpS;
	int counter, index;
	
	for(counter=0;counter < tmpVS.size() - 1;counter++){
		
		for(index=0;index < tmpVS.size() - 1;index++){
			
			if(tmpVS[index] > tmpVS[index + 1]){
				
				tmpS = tmpVS[index];
				tmpVS[index] = tmpVS[index + 1];
				tmpVS[index + 1] = tmpS;
			}
		}
	}
}