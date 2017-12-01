/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 3

** Date:10/8/02

***************************************************************/

import java.io.*;

public class MIS{
	
	public static void main(String[] args) throws IOException{
		int [] test = {3, 3, 5, 3, 7, 9, 4, 3, 76};
		int answer = maxdy(test);
		System.out.println("Answer " + answer);
		boolean repeat = true;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String repeat2;						;
		while(repeat){
			repeat2 = in.readLine();
			if(repeat2.compareTo("quit") == 0){
				repeat = false;
				break;
			}
		}
		
	}

	public static int maxincseq(int [] numbers, int index, int min){
		if(index == (numbers.length - 1)){
			if(numbers[index] >= min){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			if(numbers[index] >= min){
				return 1 + maxincseq(numbers, index + 1, numbers[index]);
			}
			else{
				return maxincseq(numbers, index + 1, min);
			}
		}	
	}
	public static int maxdy(int [] numbers){
		int[] s = new int [numbers.length];
		int j, i;
		for(i = 0; i < s.length;i++){
			s[i] = 1;
		}
		for(i = 0;i<s.length;i++){
			if(i==0){
				s[i] = 1;
			}
			else{
				for(j=i-1; j > 0;j--){
					if(numbers[i] >= numbers[j]){
						if(s[i] >= s[j])
							s[i] = s[j] + 1;
						}
					else
						s[i] = s[j];
					
				}
			}
		}
		int max = 1;
		for(i=0;i<s.length;i++){
			System.out.println("S["+i+"] = "+s[i]);
			if(s[i] > max)
				max = s[i];
		}
		return max;
	}
}
