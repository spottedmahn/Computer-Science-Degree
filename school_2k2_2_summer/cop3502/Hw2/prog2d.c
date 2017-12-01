/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** Assignment: 2 

*****************************************************/

/*****************Program Description ******************
*
* this program is basically a cash register. it takes in
* the amount due and the amount paid. it thens calculates
* change due.
*
**************End Program Description *****************/

#include<stdio.h>

void change(float paid, float due);

int main(void){
	float paid;
	float due;
	
	printf("Please enter amount of the transaction: ");
	scanf("%f",&due);
	printf("Please enter amount paid: ");
	scanf("%f",&paid);
	
	if (due > paid){
		printf("\nYou did not give me enough money, please try again\n");
	}
	if (due <= paid){
		change(paid, due);
	}
	return 0;
}

void change(float paid, float due){
	
	float dollars=0;
	float quaters=0;
	float dimes=0;
	float nickels=0;
	float pennies=0;	
	float change_current=0;
	
	change_current = paid - due;
	
	while(change_current >= 1){
		++dollars;
		--change_current;
	}
	while(change_current >= .25){
		++ quaters;
		change_current -= .25;
	}
	while(change_current >= .10){
		++dimes;
		change_current -= .10;
	}
	while(change_current >= .05){
		++nickels;
		change_current -= .05;
	}
	while(change_current >= .01){
		++pennies;
		change_current -= .01;
	}
	printf("%1.0f dollars %1.0f quaters %1.0f dimes %1.0f nickels %1.0f pennies\n", dollars, quaters, dimes, nickels, pennies);
}
