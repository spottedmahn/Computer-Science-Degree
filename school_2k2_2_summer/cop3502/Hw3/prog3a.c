/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** Assignment:3 

*****************************************************/

/*****************Program Description ******************
*
* This program will read in up to 20 numbers and will
* print out the number of distinct elements in the array.
* It will also print in descending order. 
*
**************End Program Description *****************/

#include<stdio.h>

void read(int a[], int *size_ptr);
void distinct(int a[], int *size_ptr);
//void print(int a[]);
void BubbleSort(int L[], int n);
void swap(int *a, int *b);

int main(void){
	int a[20];
	int size;
	int *size_ptr;
	int i;
	
	size_ptr=&size;

	read(a, size_ptr);
	BubbleSort(a, size);
	distinct(a, size_ptr);
//	print(a);
}
void read(int a[], int *size_ptr){
	
	int i;

	printf("Please enter the # of elements in your array (1-20): ");
	scanf("%d",size_ptr);
	for(i=0;i < *size_ptr;i++){
		printf("Enter Number %d: ",++i);
		--i;
		scanf("%d",&a[i]);
	}
		
}
void distinct(int a[], int *size_ptr){
	
	struct p{
		int num;
		int freq; /* number of occurences */
	}b[20];
	int i;
	int j=0;
	int k;

	/* fill struc array frequency with 1's and num with 9999 */
	for(i=0;i < *size_ptr;i++){
		b[i].freq=0;
		b[i].num=9999;
	}
	i=0;

	while(j < *size_ptr){
		b[i].num = a[j];
		
		while(b[i].num == a[j]){
			b[i].freq += 1;
			++j;
		}
           	
		i++;
	}

	for(k=0;k < i;k++){
		printf("the number %d appeared %d times\n",b[k].num,b[k].freq);

	}
}

//void print(struct p b[]){
	/*
	for(i=0;i<*size_ptr;i++){
		printf("the number %d appeared %d times", b[i].num, b[i].freq);
	}
	*/
//}
void BubbleSort (int a[ ], int n){

	    int i,j;

	    for (i=0; i < n - 1;i++)
		for(j=n-1;i < j;--j)
			if(a[j-1] > a[j])
				swap(&a[j-1], &a[j]);

}
void swap(int *a, int *b){
		int tmp;
		tmp=*a;
		*a=*b;
		*b=tmp;
}
