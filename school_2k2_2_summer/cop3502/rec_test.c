#include<stdio.h>

void palindrome (int n)
{
    char next;
    if (n <= 1)   /* base case */
    {    scanf("%c", &next);
         printf("%c", next);
    }
    else
    {
        scanf("%c", &next);
        palindrome(n-1);
        printf("%c", next);
     }
    return;
}/*end palindrome */

int main ( )
{
     printf("Enter a string: ");
     palindrome(5);  /*assume 5 character strings */
     printf("\n"); 
}

