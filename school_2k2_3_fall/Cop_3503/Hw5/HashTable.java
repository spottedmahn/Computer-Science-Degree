/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 5

** Date:11-26-02

***************************************************************/

/****************Program Description***************************

** This program is a very simple spell checker that uses a hash table
** to store the dictionary.  It will read in the dictionary from
** a text file. Then the user can test and see if a word is in
** the dictionary. This is a case sensitive spell checker.

**************************************************************/
import java.io.*;

public class HashTable{

	private String[] table;
	private int numEnteries;
	
	public HashTable(){
		table = new String [59999];
		numEnteries = 0;
	}
	//insert method
	public void insert(String W){
		int tmpI = hashFunction(W);
		//if there is a word already there then i use quadratic probing to store the string
		if(table[tmpI] != null){
			int i = 1;
			while(table[tmpI] != null){
				tmpI = (tmpI + i*i) % 59999;
				i++;
			}
			table[tmpI] = W;
		}
		else{
			table[tmpI] = W;
		}
		numEnteries++;
	}
	//hashFunction method
	public int hashFunction(String W){
		int tmpI = 1;
		for(int i = 0;i < W.length();i++){
			tmpI = (tmpI * W.charAt(i)) % 59999;
		}
		return tmpI;
	}
	//search method
	public boolean search(String W){
		int tmpI = hashFunction(W);
		if(table[tmpI] != null){
			if(W.compareTo(table[tmpI]) == 0){
				return true;
			}
			//using quadratic probing to determine if word is in hash table
			else{
				int i = 1;
				int count = 0;
				while(table[tmpI] != null && (count < 59999)){
					tmpI = (tmpI + i*i) % 59999;
					if(table[tmpI] != null){
						if(W.compareTo(table[tmpI]) == 0){
							return true;
						}
					}
					i++;
					count++;
				}
			}
		}
		return false;
	}
	//main
	public static void main(String[] args) throws IOException{
		HashTable hash = new HashTable();
		boolean  repeat = true;
		String tmpS = new String();
 		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	 	System.out.println("Please enter the file name containing the dictionary file");
		tmpS = stdin.readLine();
		//creating bufferedreader for file
		BufferedReader readWords = new BufferedReader(new FileReader(tmpS));
	 	//reading in words
		while(readWords.ready()){
			tmpS = readWords.readLine().trim();
			hash.insert(tmpS);
		}
		System.out.println("Type quit1 to quit");
		//menu
		while(repeat){
			System.out.println("What word would you like to check?");
			tmpS = stdin.readLine().trim();
			if(tmpS.compareTo("quit1") == 0){
				repeat = false;
				break;
			}
			else{
				boolean tmpB = hash.search(tmpS);
				if(tmpB){
					System.out.println(tmpS + " is a valid word");
				}
				else{
					System.out.println(tmpS + " is not a valid word");
				}
			}
		}//end while
	}
}