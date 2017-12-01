/***************************************************************

** Michael DePouw

** COP3303

** Assignment Number: 1

** Date:9/19/02

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


public class Class1
{
	
	public static void main (String[] args) throws IOException
	{	String user_in;
		int a, i, j, factorial=1, how_many, user_in_int;
		boolean repeat = true;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		how_many = Integer.parseInt(args[0]);
		
		for(j=1;j<=how_many;j++){
			a = Integer.parseInt(in.readLine());
		
			for(i=a;i>0;i--){
				factorial = factorial * i;
				}
			System.out.println(factorial+" ");
			factorial = 1;
		}
		
		System.out.println("Second Part");
		while(repeat){
			user_in = in.readLine();
			if(user_in.compareTo("quit") == 0){
				repeat = false;
			}
			else{
				//user_in_int = user_in.compareto(user_in);	
			}
		}
	}
}
