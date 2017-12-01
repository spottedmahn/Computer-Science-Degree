/****************************************/
/*		Name: Steve Leonard				*/
/*		Course: COP 3503				*/
/*		Assignment Title: Assignment 4	*/
/*		Compiler Used: Visual J++		*/
/*		Date: 7-1-02					*/
/****************************************/



import java.io.*;


public class HashTable 
{
	private LinkedList[] table;
	
// Creates a new hash table and initializes it to 32771 array spaces	
	public HashTable() 
	{
		table= new LinkedList[32771];
	}
	
// Inserts a new word into the hash table by first checking if the array location is there, and then calling linked insert	
	public void insert(String w) 
	{

// if used if there is not a word in the array location		
		if (table[hashFunction(w)]==null)
		{
			table[hashFunction(w)]=new LinkedList();
			table[hashFunction(w)].insert(w);			 	
		}
		
// else used if there is no value in the array location		
		else
			table[hashFunction(w)].insert(w);
	}
	
// used to get the hash number of a word through multiplication and modulus operators	
	private int hashFunction(String w) 
	{
		int size=(w.length()), product=0;
		
// for loop runs to get the ascii value of each letter at different locations in the word		
		for (int x=0; x<size; x++)
		{
			char a=w.charAt(x);
			int b=a;
			
// if used on the first loop of the word			
			if (product==0)
				product=b;

// else compounds the products together			
			else
				product*=b;
			
// if checks the size of product to protect against a overflow			
			if (product>32771)
				product%=32771;
		}
		
// returns the value for the hash location		
		return product;
	}

// boolean returns true if the word is in the hash table and false if it is not	
	public boolean search(String w) 
	{
		
// if checks the first array list table		
		if (table[hashFunction(w)]!=null) 
		{
			
// second if checks to see if the word is in the linked list at the table array location			
			if(table[hashFunction(w)].search(w))
				return true;
		}
// if not found returns false		
		return false;
	}
	
// main used for the user menu and the command calls	
	public static void main(String[] args) throws IOException 
	{
		int exit=2;
		String yes="yes", no="no";
		BufferedReader stdin=new BufferedReader(new InputStreamReader (System.in));

		HashTable newhash= new HashTable();
		
		System.out.print("\n\tWelcome to the Spell Checker!\n\nEnter the name of your dictionary file: ");
		BufferedReader fin = new BufferedReader(new FileReader(stdin.readLine()));
		
// while inserts the file given into the hash table		
		while(fin.ready())
		{
			String newword=fin.readLine();
			newhash.insert(newword);
		}
		
		fin.close(); 		

		System.out.println("\nGreat, your dictionary has been stored in memory.");

// while used for the checking of different words in the dictionary		
		while (exit==2)
		{
			System.out.print("\nWhat word would you like to check? ");
			String testword=(stdin.readLine());
			
// if the word is in the linked list at the array location then the word is valid
			if (newhash.search(testword))
				System.out.println("\n"+testword+" is a valid word in the dictionary. ");
			
// else it is not			
			else
				System.out.println("\n"+testword+" is not a valid word ");

			System.out.print("\nWould you like to check another word? ");
			
// if used to see if the user wishes to continue searching for another word			
			if ((yes.compareTo(stdin.readLine()))!=0)
				exit=0;
		}
		
		System.out.println("\nThank you for using the Spell Checker!");
	}
}
