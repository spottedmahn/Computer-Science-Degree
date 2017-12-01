/*********************************************
** Michael DePouw
** 9/3/02
** Hw 1
*********************************************/
/**************Program Description***********
** This method will take in an int array and a
** target and will the value
*********************************************/

import java.io.*;

public class BinIntSearch {

    public BinIntSearch(int[] values, int target) {
        int high=values.length - 1;
	int low=0;
	int calc_index; //interpolation value
	double i;

	if(values[low]==target)
        	System.out.println("The array index where target is stored is " + low);

        while(low < high){
      		i=(double)target/(values[low] + values[high]);
                calc_index = (int) (low + (high - low)*i);
                if(values[calc_index]== target){
                	System.out.println("The array index where target is stored is " + calc_index);
                     	break;
                }
                else{
			if(values[calc_index] > target){
				high=calc_index - 1;
			}
                        else
                            low=calc_index + 1;
                }
        }//end while
    }

}
