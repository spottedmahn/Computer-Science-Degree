

import java.io.*;

public class BondCalc{
	private int numYears;
	private double yield;
	
	public BondCalc(int numyear, int yieldIn){
		numYears = numyear;
		yield = yieldIn;
	}
	//main
	public static void main(String[] args){
		boolean done = false;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String tmpS = new String();
		int tmpI;
		
		while(!done){
			try{
				System.out.print("Please enter issue (year)");
				tmpI = Integer.parseInt(stdin.readLine());
				System.out.print("Please enter yield");
				BondCalc calc = new BondCalc(tmpI, Double.parseDouble(stdin.readLine());
			}
			catch(NumberFormatException e){
				System.out.println("Either you issue was wrong or your yield was wrong");
				
			}
	}
}