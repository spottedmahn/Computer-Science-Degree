/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 2

** Date: 9/10/02

***************************************************************/
/**************************************************************
**When entering suit please enter heart not hearts
**************************************************************/

import java.io.*;
import Card.*;
import Nodel.*;
public class Main {
    

    public Main() {
    }
    

    public static void main(String[] args) throws IOException {
			String user_in, suit_in, kind_in = new String();
			Card temp_card;
			boolean test;
			Nodel current, head, tmp = new Nodel();
			Nodel Node_List = new Nodel();
			current = null;
			tmp = null;
			head = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			boolean repeat = true;
			while(repeat){

				System.out.println("Here are your choices:");
				System.out.println("1) Add a card to your hand");
				System.out.println("2) Delete a card from your hand");
				System.out.println("3) Print out a list of all of your cards, in order.");
				System.out.println("4) Print out the score of your hand.");
				System.out.println("5) Quit");
				//user input
				user_in = in.readLine();
				
				if(user_in.equals("5"))
						repeat = false;
				else{
					if(user_in.equals("1")){
						System.out.println("What is the suit of the card to be added?");
						suit_in = in.readLine();
						System.out.println("Whate kind is the card to be added?");
						kind_in = in.readLine();
						temp_card = new Card(suit_in, kind_in);
						Node_List.insert(temp_card);
					}
					else if(user_in.equals("2")){
						System.out.println("What is the suit of the card to be removed?");
						suit_in = in.readLine();
						System.out.println("What kind is the card to be removed?");
						kind_in = in.readLine();
						temp_card = new Card(suit_in, kind_in);
						//System.out.println(temp_card.faceValue+" "+temp_card.suit+" "+temp_card.value);
						test = Node_List.remove(temp_card);
					}
					else if(user_in.equals("3")){
						Node_List.printlist();
					}
					else if(user_in.equals("4")){
						System.out.println("Your score is 30");
					}
				}
			}	
	}
    
}


