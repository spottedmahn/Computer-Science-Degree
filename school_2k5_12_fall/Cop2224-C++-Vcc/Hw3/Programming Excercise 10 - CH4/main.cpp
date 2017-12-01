/************************************************
**  Name: HW3 Programming Excercise #10
**  Author: Mike DePouw
**  Date: 2005/09/27
**  Description: Implementation of HW3 Programming Excercise #10.
**  Complied With: CodeWarrior 4.2.5.766
************************************************/

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

#define SAVINGS_ACCT_FEE "$10.00"
#define CHECKING_ACCT_FEE "$25.00"

#define SAVINGS_INTEREST 0.04
#define CHECKING_INTEREST_UPPER 0.05
#define CHECKING_INTEREST_LOWER 0.03

struct account{
	
	//account nbr
	string acctNbr; 
	//account type
	char aType;
	//account balance
	float bal;
	//minimum balance required for account
	float minBal;
}tmpAcct;

//helper function to read a line of input
account readLine(ifstream *);

int main( void )
{
	ifstream fIn;
	ofstream fOut;
	
	fIn.open("in.txt");
	fOut.open("out.txt");
	 
	fOut << left;
	
	//headings
	fOut << setw(20) << "Account #" << setw(17) << "Account Type" << setw(20) << "Current Balance" << setw(50) << "Message" << endl;

	//forever
	for(;;){
		
		tmpAcct = readLine(&fIn);
		
		if(tmpAcct.acctNbr == "-1"){
			break;
		}
		//account earns interest
		if(tmpAcct.bal >= tmpAcct.minBal){
			
			//checking account
			if(tmpAcct.aType == 'C'){
				
				//account earns 5%
				if(tmpAcct.bal > (5000 + tmpAcct.minBal)){
					
					fOut << setw(20) << tmpAcct.acctNbr << setw(17) << tmpAcct.aType  << setw(20) << tmpAcct.bal << setw(50) << "Interest Earned: " << CHECKING_INTEREST_UPPER * tmpAcct.bal << endl;
				}
				//account earns 3%
				else{
					
					fOut << setw(20) << tmpAcct.acctNbr << setw(17) << tmpAcct.aType  << setw(20) << tmpAcct.bal << setw(50) << "Interest Earned: " << CHECKING_INTEREST_LOWER * tmpAcct.bal << endl;	
				}
			}
			//savings account
			else{
				
				fOut << setw(20) << tmpAcct.acctNbr << setw(17) << tmpAcct.aType  << setw(20) << tmpAcct.bal << setw(50) << "Interest Earned: " << SAVINGS_INTEREST * tmpAcct.bal << endl;	
			}
		}
		//account does not earns interest and gets charged a fee
		else{
			
			//checking account
			if(tmpAcct.aType == 'C'){
				
				fOut << setw(20) << tmpAcct.acctNbr << setw(17) << tmpAcct.aType  << setw(20) << tmpAcct.bal << setw(50) << "Fee Charged: " << CHECKING_ACCT_FEE << endl;	
			}
			//savings account
			else{
				
				fOut << setw(20) << tmpAcct.acctNbr << setw(17) << tmpAcct.aType  << setw(20) << tmpAcct.bal << setw(50) << "Fee Charged: " << SAVINGS_ACCT_FEE << endl;	
			}
		}
		fOut.flush();
	}
	
	fOut.close();
	fIn.close();
	
	return 0;
}

account readLine(ifstream *fIn){
	
	account oneLine;
	
	if(!(*fIn >> oneLine.acctNbr >> oneLine.aType >> oneLine.minBal >> oneLine.bal)){
		
		oneLine.acctNbr = "-1";
	}
	
	return oneLine;
}