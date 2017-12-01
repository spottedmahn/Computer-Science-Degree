// Arup Guha
// July 1, 2002
// Stores node for an incomplete binary search tree class.
import java.io.*;

public class BinTreeNode {

    public int data; 
    public BinTreeNode left;
    public BinTreeNode right;

    // Creates a default node.
    public BinTreeNode() {
	data = 0;
	left = null;
	right = null;
    }

    // Creates a single node storing x.
    public BinTreeNode(int x) {
	data = x;
	left = null;
	right = null;
	//System.out.println("Here int constructor");
    }

    // Accessor methods.
    public int getData() {
      return data;
    }

    public BinTreeNode getLeft() {
      return left;
    }

    public BinTreeNode getRight() {
      return right;
    }

    // Returns the number of integers stored in the binary tree with
    // the current object as the root.
    public int size() {

      int val = 1;
      if (left !=null)
        val+=left.size();
      if (right !=null)
        val+=right.size();

      return val;
    }

    // Returns the height of the binary tree with the current object as
    // the root.
    public int height() {

      if ((left != null) && (right !=null))
        return 1+max(left.height(),right.height());
      else if (left != null)
        return 1 + left.height();
      else if (right != null)
        return 1 + right.height();
      else
        return 0;
    }

    // Returns a reference to a node in the tree storing the value x.
    // Null is returned if no such node is found.
    public BinTreeNode searchNode(int x) {

      if (x == data)
        return this;
      if ((left != null) && (x < data))
        return left.searchNode(x);
      if ((right != null) && (x > data))
        return right.searchNode(x);

      return null;
    }

    // Returns a reference to the parent of the node that x references.
    // If x does not have a parent in the tree, then null is returned.
    public BinTreeNode parent(BinTreeNode x) {

      // Case of one node.
      if ((left == null) && (right == null))
        return null;

      // Case that this is the parent.
      if ((left == x) || (right == x))
        return this;

      // Recursively determine parent.
      else if ((left != null) && (x.data < this.data))
        return left.parent(x);
      else if ((right != null) && (x.data > this.data))
        return right.parent(x);
      else
        return null;
    }


    // Returns the minimum value stored in the tree.
    public int minVal() {

      if (left == null)
        return data;
      else
        return left.minVal();
    }

    // Returns the maximum value stored int he tree.
    public int maxVal() {

      if (right == null)
        return data;
      else
        return right.maxVal();
    }

    // Returns true if a node in the tree contains the value x, and
    // returns false otherwise.
    public boolean search(int x) {

      if (x == data)
        return true;
      if ((left != null) && (x < data))
        return left.search(x);
      if ((right != null) && (x > data))
        return right.search(x);
      return false;
    }

    // Returns true if the current node is a leaf node.
    public boolean isLeafNode() {
      return ((left == null) && (right == null));
    }

    // Returns true if the current node has exactly one child node.
    public boolean hasOneChild() {
      return (!isLeafNode() && ((left == null) || (right == null)));
    }
	 //insert recursive method
    public void insertrec(int x) {
	 	System.out.println("Here");
      BinTreeNode tmp = new BinTreeNode(x);
		BinTreeNode cur = this;
		
		if(cur.data >= x){
			//if cur.left is not null traverse down tree
			if(cur.left != null){
				cur.left.insert(x);
			}
			else{
				cur.left = tmp;
			}
		}
		else{
			//if cur.right is not null traverse down tree
			if(cur.right != null){
				cur.right.insert(x);
			}
			else{
				cur.right = tmp;
			}
		}
    }

    // Inserts a new node storing the value x into the binary search tree
    // with the root as the current object.
    public void insert(int x) {

	// Create new node to insert
	BinTreeNode temp = new BinTreeNode(x);

	BinTreeNode cur = this; // temp node to traverse tree.
	boolean done = false;

	while (!done) {

	    // Decide if node should be inserted on right or left subtree.
	    if (cur.data >= x) {
		// Only traverse down tree if left pointer isn't null.
		if (cur.left != null)
		    cur = cur.left;
		// Otherwise, insert temp node in the correct location.
		else {
		    cur.left = temp;
		    done = true;
		}
	    }
	    else {
		// Only traverse down tree if the right pointer isn't null.
		if (cur.right != null)
		    cur = cur.right;
		// Otherwise, insert temp node in the correct location.
		else {
		    cur.right = temp;
		    done = true;
		}
	    }
	}
	
    }

    // Delete a node in the tree that stores the value x. If the deletion
    // is successful, true is returned, otherwise false is returned and
    // the tree is left unchanged.
    public boolean delete(int x) {

      // Checks if the node is in the tree.
      BinTreeNode temp = searchNode(x);
      if (temp == null)
        return false;

      // Takes care of deleting a leaf node.
      if (temp.isLeafNode()) {
        BinTreeNode theparent = parent(temp);
        if ((theparent.left !=null) && (theparent.left.data == x))
          theparent.left = null;
        else
          theparent.right = null;
      }

      // Takes care of deleting a node with one child node.
      else if (temp.hasOneChild()) {
        BinTreeNode theparent = parent(temp);

        if (theparent.left!=null && theparent.left.data == x) {
          if (temp.left == null)
            theparent.left = temp.right;
          else
            theparent.left = temp.left;
        }
        else {
          if (temp.left == null)
            theparent.right = temp.right;
          else
            theparent.right = temp.left;
        }
      }
      // Deletes node with two children 
      else {
        int newval = temp.right.minVal();
        delete(newval);
        temp.data = newval;
      }
      return true;
    }

    // Finds the kth smallest item in the binary tree and returns it.
    // If no such item exists, -1 is returned.
    public int kthSmallest(int k) {
      
      // Deals with the invalid case of k being too big.
      if (k > size() || k < 1)
        return -1;
    
      // calculate number of nodes on the left side of the tree.
      int leftside = 0;
      if (left != null) 
        leftside = left.size();

      // Recursively determine the kth smallest value.
      if (k <= leftside)
        return left.kthSmallest(k);
      else if (k == (leftside+1))
        return data;
      else
        return right.kthSmallest(k-leftside-1);

    }

    // Prints out a preorder traversal of the binary tree with the current
    // object as the root.
    public void Preorder() {
	System.out.print(data+" ");
	if (left != null)
	    left.Preorder();
	if (right != null)
	    right.Preorder();
    }

    // Prints out a inorder traversal of the binary tree with the current
    // object as the root.
    public void Inorder() {
	if (left != null)
	    left.Inorder();
	System.out.print(data+" ");

	if (right != null)
	    right.Inorder();
    }

    // Prints out a postorder traversal of the binary tree with the
    // current object as the root.
    public void Postorder() {
	if (left != null)
	    left.Postorder();
	if (right != null)
	    right.Postorder();
	System.out.print(data+" ");
    }

   // Returns the greater of a and b.
   public static int max(int a, int b) {
     if (a > b) 
       return a;
     else
       return b;
    }
}

