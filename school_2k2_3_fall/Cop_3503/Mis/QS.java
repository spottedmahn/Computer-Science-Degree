/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 3

** Date:10/9/02

***************************************************************/


import java.io.*;


public class QS
{

	
  public static int Partition(int[] numbers, int start, int end) {

       int i = start;
       int j = end;

       while (i < j) {

            while (i <= end && numbers[i] <= numbers[start])
                 i++;
            while (numbers[j] > numbers[start])
                 j--;
            if (i < j) 
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;

        }
        //swap(start, j);
		int temp = numbers[start];
		numbers[start] = numbers[j];
		numbers[j] = temp;		
        return j;
    }

}
