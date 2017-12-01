/*************************************************

** Michael DePouw

** Assignment 1

** Summer 2k2

** Lab Section 13

** mi348225

*************************************************/

/**************Program Description**************/

/* this program will take in a intial height and tell you if the object has hit the */

/* ground after every second starting at 1 and going to 10. */

/**********End of program description**********/


#include<stdio.h>

float distance(int secs); /* function to calculate distance after every second */

int main(void){

	int i=1;
	float fallen;    /* the distance after every second */
	float intial_distance; 
	
	printf("Please enter initial height of the object: ");
	scanf("%f",&intial_distance);
	
	fallen = distance(i); /* for the first sec it will print second instead of seconds */
	if(fallen < intial_distance){
		printf("After %d second, the object has fallen %.2f feet\n",i,fallen);
	}
	else{
		printf("After %d second, the object is on the ground\n",i);
	}

	for(i=2;i<11;i++){	/* start testing every sec starting at 2 going to 10 */
		fallen = distance(i);
		if(fallen < intial_distance){
			printf("After %d seconds, the object has fallen %.2f feet\n",i,fallen);
		}
		else{
			printf("After %d seconds, the object is on the ground\n",i);
		}
	}
	return 0;
}

float distance(int secs){
	float fallen;


	fallen = 16*(secs*secs);

	return fallen;
}