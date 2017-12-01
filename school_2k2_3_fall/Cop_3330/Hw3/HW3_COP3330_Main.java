/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 3

** Date:10/14/02

***************************************************************/

/****************Program Description***************************

** This program will add 2 big numbers. It will also subtract 2 
** big numbers (doesn't work completely). I'm having a problem with
** borrowing. It will multiply 2 numbers (doesn't work completely).
** It will find a big number in the array that stores the
** results of add, subtract, and multiply.  The program expects
** one command line parameter, the number of big numbers. 

**************************************************************/


import java.io.*;
import java.util.*;

public class HW3_COP3330_Main{
	
	
	public static void main(String[] args) throws IOException{
		int arrayPos = 0, tmpI, i;
		boolean repeat = true;
		String tmpS, input;
		String tmpS2 = new String();
		
		HW3_COP3330_BigNumber a = new HW3_COP3330_BigNumber(Integer.parseInt(args[0]));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//menu loop
		while(repeat){
			input = in.readLine();
			StringTokenizer token = new StringTokenizer(input);
			//putting command into a tmp string
			tmpS = token.nextToken();
			//add command
			if(tmpS.compareTo("add") == 0){
				a.number[arrayPos] = a.add(token.nextToken(), token.nextToken());
				System.out.println("number["+arrayPos+"] = "+a.number[arrayPos]);
				arrayPos ++;
			}
			//subtract command
			else if(tmpS.compareTo("subtract") == 0){
				a.number[arrayPos] = a.subtract(token.nextToken(), token.nextToken());
				System.out.println("number["+arrayPos+"] = "+a.number[arrayPos]);
				arrayPos ++;
				
			}
			//multiply command
			else if(tmpS.compareTo("multiply") == 0){
				a.number[arrayPos] = a.multiply(token.nextToken(), token.nextToken());
				System.out.println("number["+arrayPos+"] = "+a.number[arrayPos]);
				arrayPos ++;
			}
			//print command
			else if(tmpS.compareTo("print") == 0){
					tmpI = Integer.parseInt(token.nextToken());
					System.out.println(a.number[tmpI]);
			}
			//find command
			else if(tmpS.compareTo("find") == 0){
				tmpS = token.nextToken();
				for(i = 0; i < a.number.length; i++){
					if(a.number[i] != null)
						if(a.number[i].compareTo(tmpS) == 0)
							System.out.println("index :"+i);
				}
				
				
			}
			else if(tmpS.compareTo("factorial") == 0){
				
			}
			//quit command
			else if(tmpS.compareTo("quit") == 0){
				repeat = false;
				break;
			}
			else{
				
			}
			
		}//end while
	}
}
