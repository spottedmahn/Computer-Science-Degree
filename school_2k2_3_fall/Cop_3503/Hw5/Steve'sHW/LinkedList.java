/****************************************/
/*		Name: Steve Leonard				*/
/*		Course: COP 3503				*/
/*		Assignment Title: Assignment 4	*/
/*		Compiler Used: Visual J++		*/
/*		Date: 7-1-02					*/
/****************************************/


import java.io.*;

public class LinkedList 
{
	private Node front;
	
// creates a black linked list with the front = null	
	public LinkedList() 
	{
		front = null;
	}

// inserts a word into the linked list	
	public void insert(String w)
	{
		Node temp = new Node(w);
		
// if used to check if the linked list is null, if so it intialized with the word given		
		if (front == null)
		{
			front = temp;
			front.next=null;		 
		}
		
// else it is used to insert a new word into the linked list		
		else 
		{
			temp.next = front;
			front = temp;
		}
	}
	
	
// Print function used for testing purposes
	public void print() 
	{
		Node temp = front;
		while (temp != null) 
		{
			System.out.println("The word : "+temp.word);
			temp=temp.next;
		}
	}
	
//	search used to find if the word is in the linked list
	public boolean search(String w) 
	{
		Node temp=front;

// while continues to check the linked list until the word is found or the linked list is out of values		
		while (temp!=null)
		{
	
// if used to compare the word to the word stored in the linked list location			
			if (temp.word.compareTo(w)==0)
				return true;
			
// else moves the front to the next front			
			else
				temp=temp.next;
		}
		
// if it is not found then false is returned		
		return false;
	}
}
