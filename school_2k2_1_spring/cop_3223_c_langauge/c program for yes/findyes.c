/* 
** COP3223
** Jim Scanlon
** Test Program
** jms61992
*/

#include <stdio.h>

int main(void) {

	int   seen_a_y_and_an_e = 0;
	int   count = 0;
	int   seen_a_y = 0;
	char	x;
	int	scanf_result;
	
	printf ("Enter some text\n");
	printf("EOF is %d\n",EOF);
	while (1){
		scanf_result = scanf("%c",&x);

		if (scanf_result <= 0)		/* No char read gives 0, End of file -1 */
			break;
		printf ("%d %c %d \n", scanf_result, x, x);
		if (x == 's'  || x == 'S' ){
			if (seen_a_y_and_an_e){
				++count;
				seen_a_y_and_an_e = 0;
			}
			else {
				seen_a_y = 0;
			}
		}
		else if (x == 'e'){
			if (seen_a_y){
				seen_a_y_and_an_e = 1;
				seen_a_y = 0;
			}
			else{
				seen_a_y = 0;
				seen_a_y_and_an_e = 0;
			}
		}
		else{ 
			seen_a_y_and_an_e = 0;
			if (x == 'y')
				seen_a_y = 1;
			else 
				seen_a_y = 0;
		}
		printf("%c",x);
	}
	printf ("yes occurs %d times in this text\n",count);
	return 0;
}
	

		

			


				
	

