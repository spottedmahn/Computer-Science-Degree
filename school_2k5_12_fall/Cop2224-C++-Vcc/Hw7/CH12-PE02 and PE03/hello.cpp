/************************************************
**  Name: HW6 Chapter 12 Programming Excercise #2
**  Author: Mike DePouw
**  Date: 2005/10/20
**  Description: Implementation of HW6 Chapter 12 Programming Excercise #2.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

//assumptions
//1)input comes from a file
//2)input file name is passed as a command line parameter

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cctype>

using namespace std;

//Global Variables
ifstream fin;
ofstream fout;

//helper functions
bool init(int*, char*[]);
bool processArgs(int*, char*[]);
bool cleanUp();

const int PRECISION = 3;

enum days {Sun, Mon, Tue, Wed, Thu, Fri, Sat};

class dayType{
	
	public:
		dayType(days dayIn = Sun);
		void setDay(days);
		days getDay();
		void printDay();
		days nextDay();
		days prevDay();
		days calcDay(int iIn);
	private:
		days day;
		
};

dayType::dayType(days dayIn){
	day = dayIn;	
}

void dayType::setDay(days dayIn){
	day = dayIn;
}

days dayType::getDay(){
	return day;
}

days dayType::nextDay(){
	if(day == Sat){
		day = Sun;
	}
	else{
		day = static_cast<days>(day + 1);		
	}
	return day;
}
days dayType::prevDay(){
	if(day == Sun){
		day = Sat;
	}
	else{
		day = static_cast<days>(day - 1);		
	}
	return day;
}
 
days dayType::calcDay(int i){
	
	int tmpI = i % 7;
		
	if(tmpI == 0){				
		return day;
	}
	//if wrap around	
	else if((day + tmpI) > 6){
		tmpI = day + tmpI - 7;
		return static_cast<days>(Sun + tmpI);
	}
	else {
		return static_cast<days>(day + tmpI);
	}
}

void dayType::printDay(){
	
	fout << "Current day is: ";
	
	switch(day){
		
		case Sun:
			fout << "Sunday";
			break;
		case Mon:
			fout << "Monday";
			break;
		case Tue:
			fout << "Tuesday";
			break;
		case Wed:
			fout << "Wednesday";
			break;
		case Thu:
			fout << "Thursday";
			break;
		case Fri:
			fout << "Friday";
			break;
		case Sat:
			fout << "Saturday";
			break;
	}
	
	fout << endl;
}
int main(int argc, char* argv[]){

	bool result = init(&argc, argv);
	
	if(!result){
		
		return -1;	
	}
	
	dayType tmpDayType;
	
	string tmpS;
	int tmpI;
	
	while(fin >> tmpS){
	
		if(tmpS.compare("set") == 0){

			fin >> tmpS;

			if(tmpS.compare("Sun") == 0){
				tmpDayType.setDay(Sun);
			}
			else if(tmpS.compare("Mon") == 0){
				tmpDayType.setDay(Mon);
			}
			else if(tmpS.compare("Tue") == 0){
				tmpDayType.setDay(Tue);
			}
			else if(tmpS.compare("Wed") == 0){
				tmpDayType.setDay(Wed);
			}		
			else if(tmpS.compare("Thur") == 0){
				tmpDayType.setDay(Thu);
			}
			else if(tmpS.compare("Fri") == 0){
				tmpDayType.setDay(Fri);
			}
			else if(tmpS.compare("Sat") == 0){
				tmpDayType.setDay(Sat);
			}
			tmpDayType.printDay();
		}
		else if(tmpS.compare("next") == 0){		

			tmpDayType.nextDay();
			tmpDayType.printDay();
		}
		else if(tmpS.compare("prev") == 0){		

			tmpDayType.prevDay();
			tmpDayType.printDay();
		}
		else if(tmpS.compare("calc") == 0){
			
			days tmpDay;
			
			fin >> tmpI;
			
			tmpDay = tmpDayType.calcDay(tmpI);
			
			tmpDayType.setDay(tmpDay);
			tmpDayType.printDay();
		}
	}

	//system("PAUSE");
	return 0;
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
