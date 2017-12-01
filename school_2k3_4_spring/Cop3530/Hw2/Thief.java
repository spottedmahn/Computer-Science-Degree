/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 2

** Date:3-13-03

***************************************************************/

/****************Program Description***************************

** Dynamic Programming Algorithm
** Please see COP3530Hmk02.doc for what the algorithm does.

**************************************************************/

import java.io.*;
import java.util.*;

public class Thief {
	
	public int weight, value;
	public String item;
	
	public Thief (String item_in,int weight_in, int value_in){
		weight = weight_in;
		value = value_in;
		item = item_in;
	}
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("Please enter name for input file");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader fin = new BufferedReader(new FileReader(stdin.readLine()));
		//max weight
		int[] B = new int[Integer.parseInt(fin.readLine())];
		for(int i=0;i<B.length;i++){
			B[i] = 0;
		}
		//number of items
		int numOfItems = Integer.parseInt(fin.readLine());
		Thief[] items = new Thief[numOfItems];
		//reading items in
		for(int i=0;i<numOfItems;i++){
			StringTokenizer token = new StringTokenizer(fin.readLine());
			items[i] = new Thief(token.nextToken(), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
		}
		//determing max value thief can hold
		for(int k=0;k<numOfItems;k++){
			for(int w = B.length - 1;w > items[k].weight;w--){
				if(B[w - items[k].weight] + items[k].value > B[w]){
					B[w] = B[w - items[k].weight] + items[k].value;
					//System.out.println("Update B["+w+"] to "+B[w - items[k].weight] + items[k].value);
				}
			}
		}
		System.out.println("The maximum value of loot for the "+B.length+" pound knapsack is "+B[B.length-1]);

	}	
}