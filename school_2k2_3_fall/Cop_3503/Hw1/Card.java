/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 2

** Date: 9/10/02

***************************************************************/

import java.io.*;

public class Card {
    
    public int value, ValueIn; //cards 1-52
    public String faceValue; //2-10, jack, queen, king, ace
    public String suit; // spades, hearts, diamonds, clubs
    
    public Card() {
        ValueIn = 0;
        faceValue = "none";
        suit = "none";
    }
	public Card(int ValueIn2, String l, String m){
		ValueIn = ValueIn2;
		suit = l;
		faceValue = m;
		
	}
    public Card(String y, String z) {
		suit = y;        
		faceValue = z;
        if((z.compareTo("two") == 0))
			value = 2;
		else if(z.compareTo("three") == 0)
			value = 3;
		else if(z.compareTo("four") == 0)
			value = 4;
		else if(z.compareTo("five") == 0)
			value = 5;
		else if(z.compareTo("six") == 0)
			value = 6;
		else if(z.compareTo("seven") == 0)
			value = 7;
		else if(z.compareTo("eight") == 0)
			value = 8;
		else if(z.compareTo("nine") == 0)
			value = 9;
		else if(z.compareTo("ten") == 0)
			value = 10;
		else if(z.compareTo("jack") == 0)
			value = 11;
		else if(z.compareTo("queen") == 0)
			value = 12;
		else if(z.compareTo("king") == 0)
			value = 13;
		else if(z.compareTo("ace") == 0)
			value = 14;
    }
}

