/**********************************

** Michael DePouw

** Cop 3223 Spring 2k2

** ass. 3

** md81423

**********************************/

#include<stdio.h>

int power(int base, int exponent);

void binaryrep(int z);

int main(void){

	int deci_in;

	printf("Please input a decimal number to be converted to binary: ");
	scanf("%d", &deci_in);
	
	printf("  %d    ",deci_in);
	
	binaryrep(deci_in);

return 0;
}
int power(int base, int exponent){
	
	int current=1, count=0;
	
	while(count < exponent){
		current *= base;
		++count;
	}
	return current;

}	

void binaryrep(int z){
	int y, base, exponent, first_power;

	first_power = 13;
	
	while(first_power >= 0){

		y = power(2, first_power);

		if(y <= z){
			printf("1");
			z = z - y;
		}
		else
			printf("0");

		first_power = first_power - 1;
	}
	printf("\n");
}
