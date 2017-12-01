// Arup Guha
// July 1, 2002
import java.io.*;

public class BinTree {

    private BinTreeNode root;

    // Default constructor.
    public BinTree() {
	root = null;
    }

    // Creates a binary tree storing the value x.
    public BinTree(int x) {
        root = new BinTreeNode(x);
    }

    // Returns the number of elements stored in the binary tree.
    public int size() {
      if (root == null)
        return 0;
      else
        return root.size();
    }

    // Returns the height of the binary tree.
    public int height() {
      if (root == null)
        return -1;
      else
        return root.height();
    }

    // Returns true if x is stored in the binary tree, false otherwise.
    public boolean search(int x) {
      if (root == null)
        return false;
      else
        return root.search(x);
    }

    // Returns a reference to the node storing x in the binary tree.
    public BinTreeNode searchNode(int x) {
      if (root == null)
        return null;
      else
        return root.searchNode(x);
    }

    public void leftrotate() {
	 	//BinTreeNode cur = this;
		BinTree tree2 = new BinTree(tree.root.left.data);
		tree2.root.left = tree.root.left.left;
		tree2.root.right = tree.root;
		tree2.root.right.left = tree.root.left.rigth;
		tree = tree2;
      // Add your code here.
    }

    public void rightrotate() {
      // Add your code here.
    }

    public void insertrec(int x) {
      if (root == null)
			root = new BinTreeNode(x);
		else
			root.insertrec(x);
    }

    // Inserts a node containing x into the binary tree.
    public void insert(int x) {
      if (root == null)
        root = new BinTreeNode(x);
      else
        root.insert(x);
    }

    // Deletes a node containing x from the binary tree. The method 
    // returns true if the deletion was done successfully.
    public boolean delete(int x) {
      
      // Can't delete from an empty tree.
      if (root == null)
        return false;
     
      // Deleting root node.
      if (root.getData() == x) {

        // Take care of each case individually
        if (root.isLeafNode()) 
          root = null;
        else if (root.hasOneChild()) {
          if (root.getLeft() != null)
            root = root.getLeft();
          else
            root = root.getRight();          
        }
        // Two Child BinTreeNode method works fine since the actual
        // reference root is not being moved.
        else
          return root.delete(x);

        return true;
      }
      // BinTreeNode method works in all other cases.
      else
        return root.delete(x);
    }

    // Finds the kth smallest element in the binary tree and returns it's
    // value. If there is not a kth smallest item, -1 is returned.
    public int kthSmallest(int k) {
      
      if (root == null)
        return -1;
      else
        return root.kthSmallest(k);
    }

    // Prints out a preorder traversal of the binary tree/
    public void Preorder() {
	if (root != null)
          root.Preorder();
    }

    // Prints out a postorder traversal of the binary tree/
    public void Postorder() {
	if (root != null)
          root.Postorder();
    }


    // Prints out a inorder traversal of the binary tree/
    public void Inorder() {
	if (root != null)
          root.Inorder();
    }

    public static void main(String[] args) throws IOException {
	
        BufferedReader stdin = new BufferedReader
				(new InputStreamReader(System.in));

	BinTree tree = new BinTree(); // Tree to test.

        // Runs menu driven program.
        int ans = menu();
        while (ans != 9) {
 
          // Choices coincide with the menu.
          if (ans == 1) {
            System.out.println("What value do you want to insert?");
            int val = Integer.parseInt(stdin.readLine());
            tree.insertrec(val);
          } 
          else if (ans == 2) {
            System.out.println("What value do you want to delete?");
            int val = Integer.parseInt(stdin.readLine());
            if (tree.delete(val))
              System.out.println("The value was deleted successfully.");
            else
	      System.out.println("The value is not in the tree.");
          }
          else if (ans == 3) {
            tree.Preorder();
            System.out.println();
          }
          else if (ans == 4) {
            tree.Inorder();
            System.out.println();
          }
          else if (ans == 5) {
            tree.Postorder();
            System.out.println();
          }
          else if (ans == 6) {
            System.out.println("Enter k.");
            int k = Integer.parseInt(stdin.readLine());
            int value = tree.kthSmallest(k); 
            if (value != -1)
              System.out.println("The "+k+"th smallest item is "+value+".");
            else
              System.out.println("Not a valid value for k.");
          }
          else if (ans == 7)
            tree.leftrotate();
          else if (ans == 8)
            tree.rightrotate();
          ans = menu();        
        }        
        System.out.println("done.");
    }

    // Prompts the user with the menu and returns their menu choice.
    public static int menu() throws IOException {
      BufferedReader stdin = new BufferedReader
				(new InputStreamReader(System.in));
      int choice=0;
      while (choice < 1 || choice > 9) {

        System.out.println("Which choice would you like?");
        System.out.println("1. Insert a value.");
        System.out.println("2. Delete a value.");
        System.out.println("3. Print a preorder traversal.");
        System.out.println("4. Print a inorder traversal.");
        System.out.println("5. Print a postorder traversal.");
        System.out.println("6. Find the kth smallest value.");
        System.out.println("7. Do left rotation.");
        System.out.println("8. Do right rotation.");
        System.out.println("9. Quit.");
        choice = Integer.parseInt(stdin.readLine());
        if (choice < 1 || choice > 9)
          System.out.println("Sorry, invalid choice. Try again.");
      }
      return choice;
    }
}

