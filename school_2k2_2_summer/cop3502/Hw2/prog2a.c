/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** ass. 2

*****************************************************/

/*****************Program Description ******************
*
* This program will take in 3 integers and sort them for 
* you.
*
**************End Program Description *****************/

#include<stdio.h>

int swap(int *,int *);

int main(void){
	int x;
	int y;
	int z;
	
	printf("Please enter 3 integers: ");
	scanf("%d%d%d",&x,&y,&z);
	
	if(y < z){
		swap(&y, &z);
	}
	if(x < y){
		swap(&x, &y);
	}
	if(y < z){		/* testing again in the case where first # enter is the larger than third # entered */
		swap(&y, &z);
	}
	printf("\nThe numbers, in order, are: %d %d %d\n",x,y,z);
}

int swap(int *p, int *q){
	int tmp;
	
	tmp=*p;
	*p=*q;
	*q=tmp;
}
