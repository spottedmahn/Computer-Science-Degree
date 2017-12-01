/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 3

** Date:10/14/02

***************************************************************/

import java.io.*;


public class HW3_COP3330_BigNumber{
	String [] number;
	public HW3_COP3330_BigNumber(int size){
		
		number = new String[size];
			
	}
	public String add(String N1, String N2){
		String sum = new String();
		String tmpS;
		char [] N3;
		char [] N4;
		char [] Ans = new char[max(N1, N2) + 1];
		char tmpC = 'a';
		//fill answer array with a's
		for(int j = 0;j<Ans.length;j++)
			Ans[j] = 'a';
		N3 = N1.toCharArray();
		N4 = N2.toCharArray();
		
		int i,tmpI = 0, carry=0;
		//start at the back of the two strings and start adding
		for(i=0;i < N1.length();i++){
			//adding n3 and n4
			if(i < N2.length()){
				tmpI = N3[N1.length() - i - 1] + N4[N2.length() - i - 1] - 96 + carry;
				if(tmpI > 9){
					carry = 1;
					tmpI -= 10;
				}
				else {
					carry = 0;
				}
				
				//tmpC = (char) (48+tmpI);
				//System.out.println("tmpC :"+tmpC+":"+"tmpI :"+tmpI);
				Ans[N1.length() - i - 1] = (char) (48+tmpI);
				//System.out.println("ans " + Ans[N1.length() - i - 1]);
				//System.out.println("tmpI "+tmpI);
			}
			//else just add n3
			else{
				tmpI = N3[N1.length() - i - 1] - 48 + carry;
				if(tmpI > 9){
					carry = 1;
					tmpI -= 10;
				}
				else 
					carry = 0;
				Ans[N1.length() - i - 1] = (char) (48+tmpI);
			}
		}
		if(carry == 1)
			Ans[N1.length() - i] = 49;
		//puting answer char array back into a string
		for(i=0;i<Ans.length;i++)
			if(Ans[i]!='a')
				sum+=Ans[i];
		return sum;
	}//end add method
	
	public String subtract(String N1, String N2){
		int i, tmpI = 0, startI, currentI;
		String difference = new String();
		String tmpS;
		boolean padd = false;
		char [] N3;
		char [] N4;
		char [] Ans = new char[max(N1, N2) + 1];
		char tmpC = 'a';
		//fill answer array with a's
		for(int j = 0;j<Ans.length;j++)
			Ans[j] = 'a';
		N3 = N1.toCharArray();
		N4 = N2.toCharArray();
		
		for(i=0;i<N2.length();i++){
			
			tmpI = N3[N1.length() - i - 1] - N4[N2.length() - i - 1];
			//have to borrow case
			if(tmpI < 0){
				startI = N1.length() - i - 1;
				currentI = N1.length() - i - 2;
				tmpI = N3[currentI] - 48;
				//stepping left until value is > 0
				while(tmpI < 0){
					tmpI = N3[currentI] - 48;
					currentI--;
				}
				N3[currentI]-= 1;
				//going right and adding borrow
				currentI++;
				while(currentI < startI){
					N3[currentI]+= 9;
					currentI++;
				}
				tmpI = N3[N1.length() - i - 1] - N4[N2.length() - i - 1] + 10;
				Ans[N1.length() - i - 1] = (char) (48+tmpI);
			}
			else{
				tmpI = N3[N1.length() - i - 1] - N4[N2.length() - i - 1];
				Ans[N1.length() - i - 1] = (char) (48+tmpI);
			}
		}
		//tmpI = n1.length - n2.length gives me the number of digits from n1 that have not be subtracted
		tmpI = N1.length() - N2.length();
		//cleaning up carry
		for(i=0;i<tmpI;i++){
			//taking care of numbers starting with 0's ex. 0100 will be 100
			if(N3[i] != '0'){
				padd = true;
			}
			if(padd){
				difference+=N3[i];
			}
		}
		for(i=0;i<Ans.length;i++)
			if(Ans[i]!='a')
				difference+=Ans[i];
		return difference;
	}
	public String multiply(String N1, String N2){
		int i, j, tmpI = 0, carry = 0;
		String result = new String();
		String tmpS1 = new String();
		String tmpS2 = new String();
		//boolean padd = false;
		char [] N3;
		char [] N4;
		char [] Ans = new char[N1.length() + N2.length()];
		char [][] tmpC1 = new char[N2.length()][N1.length() + N2.length()];
		//fill tmpC1 array with 0's
		for(i = 0;i<N2.length();i++)
			for(j=0;j<N1.length() + N2.length();j++)
				tmpC1[i][j] = 'a';

		N3 = N1.toCharArray();
		N4 = N2.toCharArray();		
		//filling in char[][] with correct values
		for(i=0;i<N2.length();i++){
			for(j=0;j<N1.length();j++){
				tmpI = (N3[N1.length() - j - 1]-48)*(N4[N2.length() - i - 1]-48) + carry;
				if(tmpI > 9){
					carry = tmpI/10;
				}
				else{
					carry = 0;
				}
				tmpI = tmpI - carry * 10;
				tmpC1[i][N1.length() - j - 1] = (char) (tmpI+48);
				//System.out.println("tmpC1 ["+i+"]["+(N1.length() - j -1)+"] = "+tmpC1[i][N1.length() - j - 1]);
			}
			//if(i != 0){
				//tmpC1[i][N1.length() - i - 1] = '0';	
				//System.out.println("tmpC1 ["+i+"]["+(N1.length() - i -1)+"] = "+tmpC1[i][N1.length() - i - 1]);
			//}
			if(carry > 0)
				tmpC1[i][N1.length()] = (char) (carry + 48);				
		}
		for(i=1;i<N2.length();i++)
			for(j=N2.length() - i;j < N2.length();j++){
				tmpC1[i][j + 1] = '0';
				System.out.println("tmpC1["+i+"]["+(j + 1)+"] = "+tmpC1[i][j + 1]);
			}
		if(carry > 0)
			tmpC1[N2.length()][0] = (char) (carry + 48);		
		//sum tmpC1 array
		if(N1.length() == 1 && N2.length() == 1){
			result+= tmpC1[0][0];
			
		}
		else{
			//System.out.println("Here");
			for(i=0;i<N2.length();i++){
				for(j=0;j  < N1.length() + N2.length();j++){
					if(i%2 == 0){
						//System.out.println("tmpC1["+i+"]["+j+"] = "+tmpC1[i][j]);
						if(tmpC1[i][j] != 'a')
						tmpS1+=tmpC1[i][j];
					}
					else{
						if(tmpC1[i][j] != 'a')
						tmpS2+=tmpC1[i][j];
						//System.out.println("tmpC1["+i+"]["+j+"] = "+tmpC1[i][j]);
					}
				}
				//System.out.println("N2.length = "+N2.length());
				if(N2.length() == 1){
					//System.out.println("Here");
					result += tmpS1;				

				}
				else{
					//System.out.println("Here");
					if(i%2 == 1)
						System.out.println("tmpS1 = "+tmpS1+" tmpS2 "+tmpS2);
						result += add(tmpS2, tmpS1);
				}
				
			}
		}
		return result;
	}//end multiply
	public int max(String N3, String N4){
		if(N3.length() > N4.length())
			return N3.length();
		else
			return N4.length();
	}//end max method
												   
}//end class
