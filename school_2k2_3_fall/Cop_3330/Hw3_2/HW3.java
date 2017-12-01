/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 3

** Date:10/21/02

***************************************************************/

/****************Program Description***************************

**
**
**
**
**
**
**
**

**************************************************************/
import java.io.*;
import java.util.*;
import BigNumber.*;

public class HW3{
	
	public static void main(String[] args){
		String tmpS = "a";
		Int arrayPos = 0;
		int[][] number = new int[Integer.parseInt(args[0])][];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BigNumber a = new BigNumber();
		
		while(repeat){
			input = in.readLine();
			StringTokenizer token = new StringTokenizer(input);
			tmpS = token.nextToken();
				
			if(tmpS.compareTo("add") == 0){
				n1 = token.nextToken();
				n2 = token.nextToken();
				
				number[arrayPos][0] = a.add(token.nextToken(), token.nextToken());
				//System.out.println("Input :" + input);
				//a.number[arrayPos] = a.add(token.nextToken(), token.nextToken());
			}
			else if(tmpS.compareTo("subtract") == 0){
				
			}
			else if(tmpS.compareTo("multiply") == 0){
				
			}
			else if(tmpS.compareTo("print") == 0){
					tmpI = Integer.parseInt(token.nextToken());
					System.out.println(a.number[tmpI]);
					//System.out.println("Here");
			}
			else if(tmpS.compareTo("find") == 0){
				
			}
			else if(tmpS.compareTo("factorial") == 0){
				
			}
			else if(tmpS.compareTo("quit") == 0){
				repeat = false;
				break;
			}
			else{
				
			}
			arrayPos++;
		}
		
	}
}
