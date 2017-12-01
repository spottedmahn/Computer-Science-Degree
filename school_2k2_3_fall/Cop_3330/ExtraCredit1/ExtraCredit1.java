/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 

** Date:

***************************************************************/

/****************Program Description***************************

** This program implements bubble sort.  It will sort a unsorted,
** sorted, and reversed array using bubble sort and print out the
** times.  The quick sort part i was unable to get to work.

**************************************************************/

import java.io.*;
import java.util.*;

public class ExtraCredit1{
	
	private int[] values;
		
	public ExtraCredit1(int N){
		values = new int[N];
	}
	//fill unsorted method
	public void fillUnsorted(){
		Random r = new Random();
		for(int i=0;i<values.length;i++){
			values[i] = Math.abs(r.nextInt()%100000);	
		}
	}
	//fill sorted method
	public void fillSorted(){
		for(int i=0;i<values.length;i++){
			values[i] = i;
		}
	}
	//fill reverse method
	public void fillReverse(){
		for(int i=0;i<values.length;i++){
			values[i] = values.length - i;
		}
	}
	//bubble sort method
	public void BubbleSort(){
		int pass, index, temp;
	   boolean exchange = true;
 	   pass = 0;
   	// make up to values.length-1 passes through the array.
    	// exit early if no exchanges are made on previous pass.
	    while ((pass < values.length-1) && exchange){
			exchange = false;
			pass++;
			for (index = 0; index < values.length-pass; index++){
				if (values[index] > values[index+1]){
					temp = values[index];
					values[index] = values[index+1];
					values[index+1] = temp;
					exchange = true;
				}
			}
		}//end while
	}
	//quick sort methods
	/*
	public int Partition(int start, int end) {
	    int i = start;
       int j = end;
       while (i < j) {
            while (i <= end && values[i] <= values[start])
                 i++;
            while (values[j] > values[start])
                 j--;
            if (i < j) 
                 swap(i,j);
        }
        swap(start, j);
        return j;
 	}
	public void QuickSort(){
		int j = Partition(0, values.length - 1);
		
		Partition(0, j - 1);
		Partition(j + 1, values.length - 1);
	}
 	public void swap(int i, int j) {
         int temp = values[i];
         values[i] = values[j];
         values[j] = temp;
  	}
	*/  
	//main
	public static void main(String[] args) throws IOException{
		int NUMBEROFVALUES = 10;
		System.out.println("Number of values is "+NUMBEROFVALUES);
		ExtraCredit1 test = new ExtraCredit1(NUMBEROFVALUES);
		//bubble unsorted time
		test.fillUnsorted();
		long a = System.currentTimeMillis();
		test.BubbleSort();
		long b = System.currentTimeMillis();
		long unsorted = b - a;
		System.out.println("BubbleSort on unsorted list time: "+unsorted);
		//bubble sorted time
		test.fillSorted();
		a = System.currentTimeMillis();
		test.BubbleSort();
		b = System.currentTimeMillis();
		long sorted = b - a;
		System.out.println("BubbleSort on sorted list time: "+sorted);
		//bubble reverse time
		test.fillReverse();
		a = System.currentTimeMillis();
		test.BubbleSort();
		b = System.currentTimeMillis();
		long reversed = b - a;
		System.out.println("BubbleSort on reveresed list time: "+reversed);
		//quicksort unsoreted time
		/*
		test.fillUnsorted();
		a = System.currentTimeMillis();
		test.QuickSort(0, NUMBEROFVALUES-1);
		b = System.currentTimeMillis();
		unsorted = b - a;
		System.out.println("QuickSort on unsorted list time: "+unsorted);
		*/
	}
}