

import java.io.*;
import java.util.*;

public class BondCalc{
	private int numYears;//number of years
	private double yield;//% per year
	private double amount;//intial investment
	
	public BondCalc(int numyear, double yieldIn, double amountIn){
		numYears = numyear;
		yield = yieldIn;
		amount = amountIn;
	}
	//computing the total amount of money after numYears
	public double compute(){
		double total = amount;
		for(int i=1; i <= numYears;i++){
			total += total*yield;
		}
		return total;
	}
	//percent made method
	public double percentMade(){
		return (compute()/amount)*100;
	}
	public double getAmount(){
		return amount;
	}
	public int getYears(){
		return numYears;
	}
	//main
	public static void main(String[] args) throws IOException{
		boolean done = false;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String tmpS = new String();
		int tmpI;
		boolean repeat = true;
		//menu
		while(repeat){
			while(!done){
				try{
					System.out.print("Please enter issue (# of years): ");
					tmpI = Integer.parseInt(stdin.readLine());
					System.out.print("Please enter yield (example: enter 4 for 4%): ");
					double tmpD = Double.parseDouble(stdin.readLine())/100;
					System.out.print("Please enter bond amount ($): ");
					BondCalc calc = new BondCalc(tmpI, tmpD, Double.parseDouble(stdin.readLine()));
					System.out.println("Estimate expected worth in "+calc.getYears()+" years is: "+calc.compute());
					System.out.println("Gross profit: "+(calc.compute() - calc.getAmount()));
					System.out.println((int)calc.percentMade()+"% gain");
					done = true;
				}
				catch(NumberFormatException e){
					System.out.println("Either your issue was entered in the wrong format, your yield was wrong format, or your amount was entered in the wrong format");
					
				}
				catch(IOException e){
					System.out.println(e.getMessage());
				}
			}
			System.out.println("Would you like to try again? (y for yes and n for no)");
			tmpS = stdin.readLine();
			if(tmpS.compareTo("y") != 0){
				repeat = false;
				break;
			}
			done = false;
		}//end menu
	}
}