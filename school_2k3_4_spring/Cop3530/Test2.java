



public class Test2{
	public static int[] A = new int[6];
	public static int[] B = new int[6];


		
	
	public static void main(String[] args){

	Test2 check = new Test2();
	
	check.A[0] = 8;
	check.A[1]= 3;
	check.A[2]= 12;
	check.A[3]= 2;
	check.A[4]= 1;
	check.A[5]= 7;
	check.B[0]= 3;
	check.B[1]= 13;
	check.B[2]= 12;
	check.B[3]= 8;
	check.B[4]= 7;
	check.B[5]= 1;
	int i = 0;
	int j = 0;
	int maxval = 0;
	
	maxval = find(i, j);
	
	System.out.println("maxuim value = " + maxval);	
	
}

public static int find(int i, int j){
	int maxval = 0;
	int prevj = j;
	int f = 0;
	int p = 0;
	
		for(p=i; p<6; p++){
			for(f=j; f<6; f++){
						
				if(Test2.A[p] == Test2.B[f]){
					maxval =max(maxval,max(find(p+1, prevj), Test2.A[p]+find(p+1,f+1)));
					}
			}
		}
		return maxval;
}


public static int max(int x, int y){

	if (x > y){
		return x;}
	else{
		return y;}

	}
}
