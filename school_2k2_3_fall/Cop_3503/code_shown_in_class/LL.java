// Arup Guha
// 6/24/02, edited 9/3/02
// Portion of a linked list class, using only one instance variable.
import java.util.Random;

public class LL {
  
  private Node head;

  // Initializes the LL object to be empty.
  public LL() {
    head = null;
  }

  public LL(Node front) {
    head = front;
  }

  public boolean isEmpty() { 
    return (head == null);
  }

  public void makeEmpty() { 
    head = null;
  }

  // Creates a node with the value x and inserts it into the LL object.
  public void insert(int x) {

    Node temp = new Node(x); // Create a node with the value x.

    // Take care of the case where the LL object is empty.
    if (head == null)
      head = temp;

    // Deal with the standard case.
    else {
  
      // Insertion into the front of the LL object.
      if (head.data > x) {
        temp.next = head;
        head = temp;
      } 

      else {

        // Set up helper reference to refer to the node that the inserted
        // node should be inserted after.
        Node helper = head;   
        while ((helper.next != null) && (helper.next.data < x)) 
          helper = helper.next;

        // Adjust necessary references.
        temp.next = helper.next;
        helper.next = temp;
      }
    }
  }

  // Removes a node storing the value x. If no such node exists, nothing
  // is done and the method returns false. Otherwise, the node is removed
  // and true is returned.
  public boolean remove(int x) {

    // Can't remove anything from an empty list.
    if (head == null)
      return false;
   
    // Remove the first element, if necessary.
    if (head.data == x) {
      head = head.next;
      return true;
    }

    // Set up helper reference to refer to the node right before the node
    // to be deleted would be stored.
    Node helper = head;   
    while ((helper.next != null) && (helper.next.data < x)) 
      helper = helper.next;

    // If x was too big to be on the list, simply return false.
    if (helper.next == null)
      return false;

    // Only if the appropriate node stores x should it be deleted.
    if (helper.next.data == x) {
      helper.next = helper.next.next;
      return true;
    }   

    return true; // Never gets here, compiler complains w/o this.
  }

  public LL copy() {

    LL cp = new LL();
    Node temp = head;
    while (temp != null) {
      cp.addToEnd(new Node(temp.data));
      temp = temp.next;
    }
    return cp;
  }

  public LL reverse() {


    LL new_list = new LL();
    while (head != null) {
      Node last = findLast();
      new_list.addToEnd(last);
      CutLast();
      new_list.append(reverse());
    }
    return new_list;
  }

  public void addToEnd(Node x) {
    x.next = null;
    Node temp = findLast();
    if (temp == null)
      head = x;
    else
      temp.next = x;
  }

  public Node findLast() {

    if (head == null)
      return null;

    Node temp = head;
    while (temp.next != null)
      temp = temp.next;
    return temp;
  }

  private void CutLast() {

    if (head.next == null)
      head = null;
    else {
      Node end = findLast();
      Node temp = head;
      while (temp.next != end)
        temp = temp.next;
      temp.next = null;
     }
  }


  public void append(LL second) {
    Node temp = findLast();
    temp.next = second.head;
  }

  public void printlist() {

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data+" ");
      temp = temp.next;
    }
  }

  public static void main(String[] args) {

    LL list = new LL();
    Random ran = new Random();

    for (int i=0; i<20;i++) {
      int temp = ran.nextInt()%8;
      if (temp >= 0)
        list.insert(temp);
      else
        list.remove(-temp);
    }

    list.printlist();
    LL back = list.reverse();
    System.out.println();
    back.printlist();
    System.out.println();
    list.printlist();
  }  
}
