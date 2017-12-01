/*************************************************

** Michael DePouw

** COP3502 Summmer 2k2

** ass. 2

*************************************************/

/**************Program Description********************************
*                                                                *
* This program will take in a number between 0 and 100,          *
* if the number is greater than 0 it will then determine if      *
* it is a odd or even.  It will then icrement a odd or even      *
* counter. Then add that number to total of all numbers entered  *
*                                                                *
***********End Program Description*******************************/

#include<stdio.h>

int main(void){
	int x;
	int cnt_odd=0;
	int cnt_even=0;
	int total=0;
	printf("\nPlease enter a # between 0 and 100: ");
	scanf("%d",&x);
	
	while(x > 0){
		if(x % 2){
			++cnt_odd;
			total+=x;
		}
		else{
			++cnt_even;
			total+=x;
		}
		printf("\nPlease enter another # between 0 and 100: ");
		scanf("%d",&x);
		
	}
	printf("\nThe count of even is: %d, the count of odd is %d, the 
total of all numbers is %d.\n",cnt_odd, cnt_even, total);
}
