/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: 2

** Date: 9/10/02

***************************************************************/
import java.io.*;

public class Node {
	public Node head;
    public Card data;
    public Node next;

     
    public Node() {
		//data = 0;
        next = null;
    }

    public Node(Card x) {
		data = x;
        next = null;
    }

	public void insert(Card x) {

		Node temp = new Node(x); // Create a Node with the ValueIn x.

		// Take care of the case where the LL object is empty.
		if (head == null)
			head = temp;


		// Deal with the standard case.
		else {
  
			// Insertion into the front of the LL object.
		if (head.data.ValueIn > x.ValueIn) {
			temp.next = head;
			head = temp;
		} 

		else {

			// Set up helper reference to refer to the Node that the inserted
			// Node should be inserted after.
			Node helper = head;   
			while ((helper.next != null) && (helper.next.data.ValueIn < x.ValueIn)) 
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
    if (head.data.ValueIn == x.ValueIn && !(head.data.suit.compareTo("x.suit") == 0)) {
      head = head.next;
      return true;
    }

    // Set up helper reference to refer to the Node right before the Node
    // to be deleted would be stored.
    Node helper = head;   
    while ((helper.next != null) && (helper.next.data.ValueIn < x.ValueIn)) 
      helper = helper.next;

    // If x was too big to be on the list, simply return false.
    if (helper.next == null)
      return false;

    // Only if the appropriate Node stores x should it be deleted.
    if (helper.next.data.ValueIn == x.ValueIn) {
      helper.next = helper.next.next;
      return true;
    }   

    return false; // Case where x is not found.
  }
  public void printlist() {

    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data.ValueIn+" of "+temp.data.suit);
      temp = temp.next;    }
  }

}
