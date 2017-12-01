/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** Assignment: 

*****************************************************/

/*****************Program Description ******************
* this program takes input from the user and tells
* the user if the input is a math operator or not
* 
*
**************End Program Description *****************/

#include<stdio.h>

char Is_Math(char);

int main(void){
	char c,a;
	
	printf("\nPlease enter a math operator: ");
	scanf("%c",&c);
	a = Is_Math(c);
	if(a == 't')
		printf("True\n");
	else
		printf("False\n");
}

char Is_Math(char a){
	char t;
	if(a == '+')
		t = 't';
	else if(a == '/')
		t = 't';
	else if(a == '*')
		t = 't';
	else if(a == '-')
		t = 't';
	else 
		t = 'f';
	return t;
}
