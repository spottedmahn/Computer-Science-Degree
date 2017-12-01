// Arup Guha
// 9/9/02
// This class implements a doubly linked list, using a single DLLNode
// object as an instance variable. The class supports inserting and
// removing nodes. A sample test of the methods is included in the main
// method, but this test is not comprehensive.

import java.util.Random;

public class DLL {
  
  private DLLNode front;

  // Initializes the DLL object to be empty.
  public DLL() {
    front = null;
  }

  // Initializes the DLL object to reference the DLLNode passed to the
  // method.
  public DLL(DLLNode one) {
    front = one;
    front.prev = null;
  }

  public boolean isEmpty() { 
    return (front == null);
  }

  public void makeEmpty() { 
    front = null;
  }

  // Creates a node with the value x and inserts it into the DLL object.
  public void insert(int x) {

    // System.out.println("insert "+x); // Good for debugging.

    DLLNode temp = new DLLNode(x); // Create a node with the value x.

    // Take care of the case where the DLL object is empty.
    if (front == null) 
      front = temp;
    
    // Deal with the standard case.
    else {
  
      // Insertion into the front of the DLL object.
      if (front.data > x) {
        temp.next = front;
        front.prev = temp;
        front = temp;
      } 

      else {

        // Set up helper reference to refer to the node that the inserted
        // node should be inserted after.
        DLLNode helper = front;   
        while ((helper.next != null) && (helper.next.data < x)) 
          helper = helper.next;

        // Insert into the end of the list.
        if (helper.next == null) {
          temp.prev = helper;
          helper.next = temp;
        }
        // Insert into the middle of the list.
        else {
          temp.next = helper.next;
          helper.next.prev = temp;
          temp.prev = helper;
          helper.next = temp;
        }

      }

    }
  }

  // Removes a node storing the value x. If no such node exists, nothing
  // is done and the method returns false. Otherwise, the node is removed
  // and true is returned.
  public boolean remove(int x) {

    // System.out.println("remove "+x); // Good for debugging.

    // Can't remove anything from an empty list.
    if (front == null)
      return false;
   
    // Remove the first element, if necessary.
    if (front.data == x) {

      // Two cases: One node in the list, or more.
      if (front.next == null) 
        front = null;
      else { 
        front = front.next;
        front.prev = null;
      }
      return true;
    }

    // Set up helper reference to refer to the node right before the node
    // to be deleted would be stored.

    DLLNode helper = front;   
    while ((helper.next != null) && (helper.next.data < x)) 
      helper = helper.next;

    // x is larger than the last node in the list.
    if (helper.next == null) 
      return false;

    // x is contained in the list.
    if (helper.next.data == x) {

      // x is stored in the last node of the list.
      if (helper.next.next == null) 
        helper.next = null;

      // at least one value is stored in the list after x.
      else {
        helper = helper.next;
        helper.next.prev = helper.prev;
        helper.prev.next = helper.next;
      }
      return true;
    }

    return false; // Case if x isn't in the list.
  }


  // Prints out each item stored in the DLL object, in order.
  public void printlist() {

    DLLNode temp = front;
    while (temp != null) {
      System.out.print(temp.data+" ");
      temp = temp.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {

    DLL list = new DLL();
    Random ran = new Random();

    // Randomly chooses to either insert or delete 30 elements from a
    // newly created DLL object.
    for (int i=0; i<30;i++) {
      int temp = ran.nextInt()%8;
      if (temp >= 0)
        list.insert(temp);
      else
        list.remove(-temp);
    }
    list.printlist();

  }  
}
