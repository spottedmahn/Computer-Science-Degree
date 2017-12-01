/***************************************************************

** Michael DePouw

** Date:10/13/03

***************************************************************/

/****************Class Description***************************\

**

**************************************************************/

import java.io.*;
import java.util.*;

public class sortStrings{
	
	public sortStrings(){
	
	}
	
	public void bubbleSort(String[] sIn){
		
		if(sIn == null){
			
			return;
		}
		
		String bubble, tmpS;
		
		for(int i=0;i < sIn.length;i++){
			
			bubble = new String(sIn[sIn.length - 1]);
			
			for(int j=sIn.length -1;j > i;j--){
				
				tmpS = new String(sIn[j-1]);
				
				int result = compare(bubble, tmpS);
				
				if(result == 0){
					
					swap(sIn, j, j-1);
				}
				else{
					
					bubble = new String(tmpS);
				}
			}
		}
	}
	
	public void swap(String[] sIn, int j, int i){
		
		String tmpS = sIn[j];
		
		sIn[j] = sIn[i];
		sIn[i] = tmpS;
	}
	
	//returns 0 is s1 is before s2
	public int compare(String s1, String s2){
		
		String s1Upper = s1.toUpperCase();
		String s2Upper = s2.toUpperCase();

		if(s1.length() < s2.length()){
			
			for(int i=0;i < s1.length();i++){
				
				if(s1Upper.charAt(i) < s2Upper.charAt(i)){
					
					return 0;
				}
				else if(s1Upper.charAt(i) > s2Upper.charAt(i)){
			
					return 1;
				}
			}
			return 0;
		}
		else{
			
				for(int i=0;i < s2.length();i++){
				
				if(s1Upper.charAt(i) < s2Upper.charAt(i)){
					
					return 0;
				}
				else if(s1Upper.charAt(i) > s2Upper.charAt(i)){
			
					return 1;
				}
			}
			return 1;
		}
	}
	
	public static void main(String[] args){
		
		String[] test = new String[5];
		test[0] = new String("This_As-Test");
		test[1] = new String("this_As-Test2");
		test[2] = new String("AThis_As-Test");
		test[3] = new String("zThis_As-Testd");
		test[4] = new String("zThis_As-Test");
		
		//bubbleSort(test);
		
		for(int i=0;i < test.length;i++){
			
			System.out.println("test["+i+"] == "+test[i]);
		}
		
	}
}