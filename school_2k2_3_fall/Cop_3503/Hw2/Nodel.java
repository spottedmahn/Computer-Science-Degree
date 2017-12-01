/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 2

** Date: 9/10/02

***************************************************************/
import java.io.*;

public class Nodel {
	public Nodel head;
     public Card data;
     public Nodel next;

     
     public Nodel() {
          //data = 0;
          next = null;
     }

     public Nodel(Card x) {
          data = x;
          next = null;
     }
public void insert(Card x) {

    Nodel temp = new Nodel(x); // Create a Nodel with the value x.

    // Take care of the case where the LL object is empty.
    if (head == null)
      head = temp;


    // Deal with the standard case.
    else {
  
      // Insertion into the front of the LL object.
      if ((head.data.value < x.value) && (head.data.suitInt <= x.suitInt)) {
        temp.next = head;
        head = temp;
      } 

      else {

        // Set up helper reference to refer to the Nodel that the inserted
        // Nodel should be inserted after.
        Nodel helper = head;   
        while ((helper.next != null) && (helper.next.data.value > x.value) && (helper.data.suitInt > x.suitInt)) 
          helper = helper.next;

        // Adjust necessary references.
        temp.next = helper.next;
        helper.next = temp;
      }
    }
  }
  public boolean remove(Card x) {

    // Can't remove anything from an empty list.
    if (head == null)
      return false;
   
    // Remove the first element, if necessary.
    if (head.data.value == x.value && !(head.data.suit.compareTo("x.suit") == 0)) {
      head = head.next;
      return true;
    }

    // Set up helper reference to refer to the Nodel right before the Nodel
    // to be deleted would be stored.
    Nodel helper = head;   
    while ((helper.next != null) && (helper.next.data.value < x.value)) 
      helper = helper.next;

    // If x was too big to be on the list, simply return false.
    if (helper.next == null)
      return false;

    // Only if the appropriate Nodel stores x should it be deleted.
    if (helper.next.data.value == x.value) {
      helper.next = helper.next.next;
      return true;
    }   

    return false; // Case where x is not found.
  }
  public void printlist() {

    Nodel temp = head;
    while (temp != null) {
      System.out.println(temp.data.faceValue+" of "+temp.data.suit);
      temp = temp.next;    }
  }

}