/***************************************************************

** Michael DePouw

** COP3303

** Assignment Number: 1

** Date:9/18/02

***************************************************************/

/****************Program Description***************************

** This program will do the factorial of a number if there
** is an integer argument for the number of numbers that the 
** factorial needs to be done on. If there is no argument then  
** the program will proceed to wait for input strings and replying it's
** a valid word if string's ascII value is divisble by 3,5,7,11,
** or 13.

**************************************************************/
import java.io.*;


public class HW1_COP3330_Main
{
	
	public static void main (String[] args) throws IOException{
		String user_in;
		int numIn; //the number that the user want factorialized
		int how_many; // how many numbers the user will enter
		int srcBegin = 0; // string to char conversion
		int srcEnd; // string to char conversion
		int dstBegin = 0; // string to char conversion
		int sum = 0; // to see if the word is valid
		int j;
		double i;
		double factorial = 1;
		boolean repeat = true; // for while user is entering words
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(args.length == 1){   // testing for agruments or not
			how_many = Integer.parseInt(args[0]);
		
			for(j=1;j<=how_many;j++){
				numIn = Integer.parseInt(in.readLine());
		
			for(i=(double) numIn;i>0;i--){	// calculating the factorial
				factorial = factorial * i;
				}
			System.out.println(factorial+" ");
			factorial = 1;
			}
		}
		// test print
		// System.out.println("Second Part");
		while(repeat){
			user_in = in.readLine();
			srcEnd = user_in.length();
			char [] dst = new char[user_in.length()]; // destination char for conversion from a string
			if(user_in.compareTo("quit") == 0){
				repeat = false;
			}
			else{
				user_in.getChars(srcBegin, srcEnd, dst, dstBegin);
				for(j=0;j < dst.length; j++){
					// testing print
					// System.out.println("Sum: before "+sum +" array "+ dst[j]); 
					sum += dst[j];
					// testing print
					// System.out.println("Sum: afterwords "+sum);
				}
				if(sum % 3 == 0 || sum % 5 == 0 || sum %7 == 0 || sum % 11 == 0 || sum % 13 == 0){
					System.out.println("Word is valid");
					sum = 0;
				}
				else{
					System.out.println("Not Valid");
					sum = 0;
				}			 //user_in_int = user_in.compareto(user_in);	
			}
		}// end while
	}
}
