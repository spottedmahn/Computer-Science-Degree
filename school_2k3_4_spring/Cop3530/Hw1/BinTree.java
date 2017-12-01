/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 1

** Date:2/11/03

***************************************************************/

/****************Class Description***************************

** BinTree class contains methods for insert, delete, search,
** empty, and print hash index's.

**************************************************************/

import java.io.*;

public class BinTree{
	//reference to the root of the tree
	protected BinTreeNode root;
	
	public BinTree(){
		root=null;
	}
	public BinTree(BinTreeNode node){
		root = node;
	}
	public BinTree(int x){
		root = new BinTreeNode(x);
	}
	//insert method, calls BinTreeNode insert if not inserting into root
	public void insert(int x){
		if(root==null){
			root = new BinTreeNode(x);
		}
		else{
			root.insert(x);
		}	
	}
	//delete method, calls BinTreeNode delete if deleting root with two childern or not deleting the root
	public BinTreeNode delete(int x){
		if(root.data == x){
			if(root.isLeafNode()){
				root = null;
				return null;
			}
			else{
				if(root.hasOneChild()){
					if(root.left == null){
						root = root.right;
					}
					else{
						root = root.left;
					}
					return null;
				}
				else{
					return (root.delete(x));
				}
			}
		}
		else{
			return (root.delete(x));
		}
	}
	//search method, calls BinTreeNode search if x is not the root
	public boolean search(int x){
		if(root.data == x){
			return true;
		}
		else{
			return root.search(x);
		}
	}
	//is empty method
	public boolean empty(){
		if(root == null){
			return true;
		}
		else
			 return false;
	}
	//print heap index's
	public void print(){
		System.out.println("Value \t Index");
		if(root != null){
			root.print(1);
		}
	}
	//main for BinTree
	/*
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		BinTree tree = new BinTree(); // Tree to test.
	
 		 // Runs menu driven program.
   	int ans = menu();
    	while (ans != 6) {
 
          // Choices coincide with the menu.
          if (ans == 1) {
            System.out.println("What value do you want to insert?");
            int val = Integer.parseInt(stdin.readLine());
            tree.insert(val);
          } 
          else if (ans == 2) {
            System.out.println("What value do you want to delete?");
            int val = Integer.parseInt(stdin.readLine());
            BinTreeNode y = tree.delete(val);
				if(y != null)
					System.out.println("The parent of the node truely deleted : "+ y.data);
            else
	      		System.out.println("Null returned");
			}
			 else if( ans == 3){
			 	System.out.println("What value do you want to search for?");
				int val = Integer.parseInt(stdin.readLine());
				if(tree.search(val)){
					System.out.println("The value is in the tree.");
				}
				else{
					System.out.println("The value is not in the tree.");
				}
			 
			 }
			 else if(ans == 4){
			 	if(tree.empty())
					System.out.println("The tree is empty");
				else
					System.out.println("The tree is not empty");
			 }
			 else if(ans == 5){
			 	tree.print();
			 }
          ans = menu();        
        }        
        System.out.println("done.");
    }

    // Prompts the user with the menu and returns their menu choice.
    public static int menu() throws IOException {
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      int choice=0;
      while (choice < 1 || choice > 6) {

        System.out.println("Which choice would you like?");
        System.out.println("1. Insert a value.");
        System.out.println("2. Delete a value.");
		  System.out.println("3. Search a value.");
		  System.out.println("4. Is empty.");
		  System.out.println("5. To Print.");
        System.out.println("6. Quit.");
        choice = Integer.parseInt(stdin.readLine());
        if (choice < 1 || choice > 6)
          System.out.println("Sorry, invalid choice. Try again.");
      }
      return choice;
    }
	*/
}