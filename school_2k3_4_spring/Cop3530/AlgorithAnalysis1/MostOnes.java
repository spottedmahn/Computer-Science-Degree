/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 1

** Date:1/14/03

***************************************************************/
/****************Program Description***************************

** This static method takes in a two by two square array filled
** with 0's and 1's.  On each row, the 1's must come before the
** 0's. The method will return the row with the most number of 1's
** This method will run in O(n) time.

**************************************************************/

public class MostOnes{

	public static int MostOnes(int [][] binaryTable){
		
		int maxRow=-1,i=0,max=0;
		
		for(int j=0;j<binaryTable.length;j++){
		
			if(binaryTable[i][j] == 0){
				if((i+1) == binaryTable.length){
					return maxRow;
				}
				else{
					i++;
					j++;
				}
			}
			else{
				if((j+1) > max){
					maxRow = i;
					max = j + 1;
				}
			}
		}
		return maxRow;	
	}	
}