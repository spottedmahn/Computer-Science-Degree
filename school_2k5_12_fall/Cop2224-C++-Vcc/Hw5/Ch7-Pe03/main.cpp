//Program: Classify Numbers
// This program counts the number of zeros, odd, and even numbers 

#include <iostream>
#include <iomanip>
#include <fstream>

#define PRECISION 2

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;
const int N = 20;  

//function prototypes
void initialize(int& zeroCount, int& oddCount, int& evenCount);
bool getNumber(int& num);
void classifyNumber(int num, int& zeroCount, int& oddCount, int& evenCount);
void printResults(int zeroCount, int oddCount, int evenCount, int&, int&);
//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();

int main (int argc, char* argv[])
{
		//variable declaration
	int counter; //loop control variable 
	int number;  //variable to store the new number 
	int zeros;   //variable to store the number of zeros 
	int odds;    //variable to store the number of odd integers 
	int evens;   //variable to store the number of even integers 

    bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	initialize(zeros, odds, evens);						//Step 1

	//cout << "Please enter " << N << " integers."  << endl;										//Step 2
	//cout << "The numbers you entered are: " << endl;

	//for (counter = 1; counter <= N; counter++)			//Step 3
	int count = 0;
	int sum = 0;
	int numCount = 0;
	while(getNumber(number))
    {
		//;
        numCount++;
        sum += number;	
        if(count == 10){
                 count = 0;
                 fout << endl;         
        }
        count ++;							//Step 3a
		fout  <<  number  <<  " ";						//Step 3b
		classifyNumber(number, zeros, odds, evens); 	//Step 3c
	}// end for loop 

	fout << endl;
	printResults(zeros, odds, evens, sum, numCount);					//Step 4
  system("PAUSE");
	return 0;
}

void initialize(int& zeroCount, int& oddCount, int& evenCount)
{
	zeroCount = 0;
	oddCount = 0;
	evenCount = 0;
}

bool getNumber(int& num)
{
	if(fin >> num)
           return true;
    else
        return false;
}

void classifyNumber(int num, int& zeroCount, int& oddCount,
			        int& evenCount)
{
   switch (num % 2)
   {
   case 0: evenCount++;  
	       if (num == 0)			 
	 			zeroCount++;  
		   break;
   case 1: 
   case -1: oddCount++;	
   } //end switch
}

void printResults(int zeroCount, int oddCount, int evenCount, int& sum, int& count)
{ 
    fout << "There are " << evenCount << " evens, " << "which also includes " << zeroCount << " zeros" << endl;	

    fout << "The number of odd numbers is: " << oddCount << endl;
    
    fout << "Sum is " << sum << endl;
    
    fout << "Average is " << sum/count << endl;
}

bool init(int *argc, char *argv[]){

	bool result = processArgs(argc, argv);
	
	if(!result){
		return false;
	}
	
	fout << fixed << showpoint << setprecision(PRECISION);
	
	return true;
}

bool cleanUp(){
	
	fin.close();
	fout.close();
	
	return true;
}

bool processArgs(int *argc, char *argv[]){
	
	if(*argc < 2 || *argc > 3){
		
		cout << "Please enter at least one command line parameter and no more than 2.  The first parameter is input file name.  The second is optional and is the output file.  If no value is specified out is made to out.txt"  << endl;
	}
	
	if(*argc >= 2){
			
		fin.open(argv[1]);	
	}
	else{
		return false;
	}

	if(*argc >= 3){
		fout.open(argv[2]);
	}
	else{
		fout.open("out.txt");
	}
	
	return true;
}
