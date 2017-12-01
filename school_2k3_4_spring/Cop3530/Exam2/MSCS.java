import java.io.*;
import java.util.*;

public class MSCS{
	
	public int sum;
	public String MSCS;
	
	public MSCS(int sumIn, String MSCSIn){
		sum = sumIn;
		MSCS = MSCSIn;
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader fin = new BufferedReader(new FileReader(new File("in.txt")));
		String tmpS = fin.readLine();
		StringTokenizer token = new StringTokenizer(tmpS);
		int[] subOne = new int[token.countTokens()];
		for(int i=0;i<subOne.length;i++){
			subOne[i] = Integer.parseInt(token.nextToken());
		}
		tmpS = fin.readLine();
		token = new StringTokenizer(tmpS);
		int[] subTwo = new int[token.countTokens()];
		for(int i=0;i<subTwo.length;i++){
			subTwo[i] = Integer.parseInt(token.nextToken());
		}
		/*
		System.out.print("Subone = ");
		for(int i=0;i<subOne.length;i++){
			System.out.print(" "+subOne[i]);
		}
		System.out.println("");
		System.out.print("Subtwo = ");
		for(int i=0;i<subTwo.length;i++){
			System.out.print(" "+subTwo[i]);
		}
		System.out.println("");	
		*/	
		//MSCS answer = findMSCS(subOne, subTwo);
		/*String[] ss = findAllSubSequences(subOne, subTwo);
		for(int i=0;i < ss.length;i++){
			if(ss[i] != null){
				System.out.println("ss["+i+"] == "+ss[i]);
			}
		}
		*/
		//System.out.println("The MSCS is "+answer.sum+", the subsequence for the MSCS is "+answer.MSCS);
	}
	public static String[] findAllSubSequences(int[] subOne, int[] subTwo){
		String[] ss = new String[50];
		int numSS = -1;
		int sStart;
		boolean updateSS, newSS, toConcat;
		for(int k=0;k < subOne.length;k++){
			sStart = 0;
			updateSS = true;
			toConcat = false;
			for(int i=k;i < subOne.length; i++){
				newSS = true;
				for(int j=sStart;j < subTwo.length;j++){
					if(subOne[i] == subTwo[j]){
						if(newSS){
							numSS++;
							newSS = false;
							if(!updateSS){
								ss[numSS] = new String(subTwo[sStart -1] + " " + subOne[i]);
								//System.out.println("adding newSS with sStart: "+subTwo[sStart -1]+" "+subOne[i]);
							}
							else{
								ss[numSS] = new String();
								ss[numSS] += subOne[i];
								//System.out.println("adding newSS without sStart: "+subOne[i]);
							}
						}
						else{
								ss[numSS] += " " + subOne[i];
								System.out.println("concatinating: "+ss[numSS]);
						}
						if(updateSS){
							updateSS = false;
							toConcat = true;
							sStart = j + 1;
						}
						if(j != (subTwo.length - 1)){
							i++;
						}
						if(i == subOne.length)
							j = subTwo.length;
					}
				}				
			}
		}
		return ss;
	}
/*	
	public static MSCS findMSCS(int[]subOne, int[]subTwo){
		int tmpMax = 0, mscs = 0;
		String tmpMaxS = new String(), mscsS = new String();
		
		for(int k=0;k < subOne.length;k++){
			int sequenceStart = 0;
			//update sequence start
			boolean updateSS = true;
			for(int i=k;i < subOne.length;i++){
				for(int j=sequenceStart;j < subTwo.length;j++){
					if(subOne[i] == subTwo[j]){
						tmpMax += subOne[i];
						tmpMaxS += " " + subOne[i];
						System.out.println("k == "+k+" subOne["+i+"] == subTwo["+j+"] == "+subOne[i]+" tmpMax == "+tmpMax+" tmpMaxS == "+tmpMaxS);
						if(updateSS){
							sequenceStart = j + 1;
							updateSS = false;
							System.out.println("Updated SequenceStart to "+sequenceStart);
						}
						if(j != (subTwo.length - 1))
							i++;
						if(tmpMax > mscs){
							mscs = tmpMax;
							mscsS = tmpMaxS.toString();
							System.out.println("Updated mscs to "+mscs+" & updated mscsS to "+mscsS);
						}
						if(i == subOne.length)
							j = subTwo.length;
					}
				}
				if(sequenceStart != 0){
					tmpMax = subTwo[sequenceStart - 1];
					tmpMaxS = "";
					tmpMaxS += subTwo[sequenceStart - 1];
				}
			}
			tmpMax = 0;
			tmpMaxS = "";
			updateSS = true;
		}
		MSCS answer = new MSCS(mscs, mscsS);
		return answer;
	}*/
}