/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** Assignment: 3 

*****************************************************/

/*****************Program Description ******************
*
* This program will read in a string of characters up to
* 20 characters long, reverse the string and tell u if
* it is a palidrome.
*
**************End Program Description *****************/

#include<stdio.h>

void ispal_func(char [], char[], int *, int *, int *);
void palindrome (char [], char[], int *, int *);

void palindrome (char org[], char rev[], int *n_ptr, int *rec_call_ptr){
	if(*n_ptr == 0){
		
		rev[*rec_call_ptr] = org[*n_ptr];	

	}
	else{
		rev[*rec_call_ptr] = org[*n_ptr];
		*n_ptr -= 1;
		*rec_call_ptr += 1;
		palindrome(org, rev, n_ptr, rec_call_ptr);
		
	}
	return;
}/*end palindrome */

int main ( )
{	int n;
	int *n_ptr;
	int i;
	char org[20];   //original string entered
	char rev[20]; //reversed string
	int ispal=1;    // is a palindrome
	int *ispal_ptr;
	int rec_call=0; // recursive calls
	int *rec_call_ptr;

	n_ptr=&n;
	ispal_ptr=&ispal;
	rec_call_ptr=&rec_call;

	printf("Enter the number of character in your string: ");
	scanf("%d",&n);
	
	if(n){
	}
	else
		printf("\nYou enter a wrong value for n");
	n-=1;
	
	printf("Enter your string: ");
	scanf("%s", org);

	palindrome(org, rev, n_ptr, rec_call_ptr);
	
	ispal_func(org, rev, n_ptr, rec_call_ptr, ispal_ptr);
	
	printf("You entered the sting: ");
	for(i=0; i <= rec_call; i++){
		printf("%c",org[i]);
	}
	
	printf("\nYour string reversed: ");
	for(i=0; i <= rec_call; i++){
		printf("%c",rev[i]);
	}
	
	if(ispal){
		printf("\nYour string is a palindrome.\n");
	}
	else{
		printf("\nYour string is not a palindrome.\n");
	} 
}

void ispal_func(char org[], char rev[], int *n_ptr, int *rec_call_ptr, int *ispal_ptr){
	
	int i=0;
	
	while(*ispal_ptr && i <= *rec_call_ptr){
		
		if(org[i] == rev[i]){
			*ispal_ptr=1;
		}
		else{
			*ispal_ptr=0;
		}
		++i;
	}
}
