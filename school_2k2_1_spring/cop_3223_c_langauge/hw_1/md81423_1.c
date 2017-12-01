/*********************************************************

** COP 3223 Spring 2k2

** Assignment: 1

** Student: Michael DePouw

** olympus id: md81423

*********************************************************/

#include<stdio.h>

int main(void)

{
	const float multiplicand = 4.1;
	int mike_int;
	float mike_float;
	double mike_double;
	
	printf("Please enter an integer:\n");
	scanf("&d", &mike_int);

	mike_float=multiplicand*7-2;

	printf("\n\nThe result of multiplying multiplicand by 7 and subtracting 2 is:%f\n", mike_float);
	
	mike_int=multiplicand*7-2;
	
	printf("The result of multiplying your integer by 7 and	subtracting 2 is:%d\n\n",mike_int);
	
	mike_double=52.4;
	
	printf("The result you obtain when you increment the double and	divide the incremented result by the float is:%f\n\n",++mike_double/mike_float);
	
return 0;
}
