/*****************************************************

** Michael DePouw

** COP3502 

** Summer 2k2

** Assignment: 3 

*****************************************************/

/*****************Program Description ******************
*
*  
* 
*
**************End Program Description *****************/

#include<stdio.h>
#include<stdlib.h>
	
#define maxrows 8
#define maxcols 8

int main(void){

	int A[maxrows][maxcols];
	int B[maxrows][maxcols];
	int C[maxrows][maxcols];
	int i, j, k;
	int a_rows, a_cols;
	int b_rows, b_cols;
	int temp;



	for(i=0; i < maxrows; i++){	// randomly filling matrix A
		for(j=0; j< maxcols; j++){
			A[i][j] = rand()%20 + 1;
		}
	}
	for(i=0; i < maxrows; i++){	// randomly filling matrix B
		for(j=0; j < maxcols; j++){
			B[i][j] = rand()%20 + 1;
		}
	}
	

	printf("Enter the row dimension of matrix A: ");
	scanf("%d", &a_rows);
	printf("Enter the column dimension of Matrix A: ");
	scanf("%d", &a_cols);
	printf("Enter the row dimension of matrix B: ");
	scanf("%d", &b_rows);
	printf("Enter the column dimension of matrix B: ");
	scanf("%d", &b_cols);
	
	printf("Matrix A is: \n");
	for(i=0; i < a_rows; i++){	// printing matrix A
		for(j=0; j < a_cols; j++){
			printf("%d ", A[i][j]);
			if(j == a_cols - 1)
				printf("\n");
		}
	}
	printf("Matrix B is: \n");
	for(i=0; i < b_rows; i++){	// printing matrix B
		for(j=0; j < b_cols; j++){
			printf("%d ", B[i][j]);
			if(j == b_cols - 1)
				printf("\n");
		}
	}
	if(a_rows == b_rows && a_cols == b_cols){

		for(i=0; i < a_rows; i++){	// adding matrices
			for(j=0; j < a_cols; j++){
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		printf("Sum of Matrix A + B is: \n");
		for(i=0; i < a_rows; i++){	// printing sum of matrices
			for(j=0; j < a_cols; j++){
				printf("%d ", C[i][j]);
				if(j == a_cols - 1)
				printf("\n");
			}
		}

	}
	for(i=0; i < a_cols; i++){ 	// multipling matrices
		for(j=0; j < b_rows; j++){
			for(k=C[i][j]=0; k < a_cols;k++){
				C[i][j]+= A[i][k]*B[k][j];
			}
		
		}
	
	}

	printf("Product of Matrix A x B is: \n");
	for(i=0; i < a_cols; i++){	// printing product of matrices
		for(j=0; j < b_rows; j++){
			printf("%d ", C[i][j]);
			if(j == b_rows - 1)
				printf("\n");
		}
	}
	for(i=0; i < a_rows - 1; i++){ //transpose of matrix A
		for(j=0; j < a_cols; j++){
			temp = A[i][j];
			A[i][j] = A[j][i];
			A[j][i] = temp;
		}
	}
	printf("Transpose of Matrix A is: \n");
	for(i=0; i < a_rows; i++){	// printing transpose of matrix A
		for(j=0; j < a_cols; j++){
			printf("%d ", A[i][j]);
			if(j == a_cols - 1)
				printf("\n");
		}
	}
}
