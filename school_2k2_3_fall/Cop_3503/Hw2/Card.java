/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 2

** Date: 9/10/02

***************************************************************/

import java.io.*;

public class Card {
    
    public int value; //cards 2-14
    public String faceValue; //2-10, jack, queen, king, ace
    public String suit; // spades, hearts, diamonds, clubs
    public int suitInt;
    public Card() {
        value = 0;
        faceValue = "none";
        suit = "none";
		suitInt = 0;
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
		if(y.compareTo("spade") == 0)
			suitInt = 1;
		else if(y.compareTo("heart") == 0)
			suitInt = 2;
		else if(y.compareTo("diamond") == 0)
			suitInt = 3;
		else if(y.compareTo("club") == 0)
			suitInt = 4;

    }
}
