/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 1

** Date:2-13-03

***************************************************************/

/****************Class Description***************************

** Splay Tree class inherits from BinTree class.  Splay Tree
** Class contains methods for inserting, deleteing, and search
** nodes in a binary search tree.  It also contains methods for
** splaying the node after a insert, delete, or searching.

**************************************************************/

import java.io.*;
import java.util.*;

public class SplayTree extends BinTree{

	public SplayTree(){
		super();
	}
	//insert a node method, returns the node to be splayed
	public void insert(int x){
		if(root == null){
			root = new BinTreeNode(x);
		}
		else{
			super.insert(x);
			splay(x);
		}
	}
	//search for a node, returns the node to be splayed
	public BinTreeNode searchSplay(int x){
		if(root == null)
			return (new BinTreeNode(-1));
		BinTreeNode y = root.searchSplay(x);
		splay(y.data);
		return y;
	}
	//delete a node method, returns the node to be splayed
	public BinTreeNode delete(int x){
		if(root == null)
			return null;
		//node to delete is root
		if(root.data == x){
			if(root.isLeafNode()){
				root = null;
				return (new BinTreeNode(-1));
			}
			else if(root.hasOneChild()){
				if(root.left == null){
					root = root.right;
				}
				else{
					root = root.left;
				}
				return (new BinTreeNode(-1));
			}
			//deleting root with two children
			else{
				BinTreeNode y = super.delete(x);
				splay(y.data);
				System.out.println("The parent of the node truely deleted : "+ y.data);
				return y;
			}
		}
		//node to delete is not the root
		else{
			BinTreeNode y = super.delete(x);
			splay(y.data);
			System.out.println("The parent of the node truely deleted : "+ y.data);
			return y;
		}
	}
	//Splay method
	public void splay(int x){
		boolean done=false, rootCase = false;
		int stop = 0;
		//while value being splayed is not at the root
outer:while(!done){
			BinTreeNode helper = root.searchNode(x);
			BinTreeNode gpa = root.grandparent(x);
			BinTreeNode pa = root.parent(x);
			//if gpa & pa are null can't splay
			if((gpa == null) && (pa == null)){
				done = true;
				break outer;
			}
			/*if(gpa == null)
				System.out.println("Gpa is null");
			else
				System.out.println("Gpa.data = "+gpa.data);
			if(pa == null)
				System.out.println("pa is null");
			else
				System.out.println("pa.data = "+pa.data);
			if(helper == null)
				System.out.println("helper is null");
			else
				System.out.println("helper.data = "+helper.data);
			*/
			if(gpa != null){
				if(isZigZig(gpa, pa, x)){
					//right Zig Zig
					if(isRightZigZig(gpa, pa, x)){
						System.out.println("RightZigZig");
						//gpa is root
						if(root.data == gpa.data){
							//System.out.println("RootCaseRightZigZig");
							rootCase = true;
						}
						//gpa is not root, need to move Great Gpa right or left reference to helper
						else{
							BinTreeNode Ggpa = root.grandparent(pa.data);
							if(Ggpa.right != null)
								if(Ggpa.right.data == gpa.data)
									Ggpa.right = helper;
							if(Ggpa.left != null)
								if(Ggpa.left.data == gpa.data)
									Ggpa.left = helper;
						}
						//splay
						gpa.right = pa.left;
						pa.left = gpa;
						pa.right = helper.left;
						helper.left = pa;
						if(rootCase){
							root = helper;
							done = true;
							break outer;
						}
					}	
					//left Zig Zig
					else{
						System.out.println("LeftZigZig");
						//gpa is root
						if(root.data == gpa.data){
							//System.out.println("RootCaseLeftZigZig");
							rootCase = true;
						}
						//gpa is not root, need to move Great Gpa right or left reference to helper
						else{
							BinTreeNode Ggpa = root.grandparent(pa.data);
							if(Ggpa.left != null)
								if(Ggpa.left.data == gpa.data)
									Ggpa.left = helper;
							if(Ggpa.right != null)
								if(Ggpa.right.data == gpa.data)
									Ggpa.right = helper;
						}
						//splay
						gpa.left = pa.right;
						pa.right = gpa;
						pa.left = helper.right;
						helper.right = pa;
						if(rootCase){
							root = helper;
							done = true;
							break outer;
						}
					}	
				}
			}
			if(gpa != null){
				if(isZigZag(gpa, pa, x)){
					System.out.println("ZigZag");
					//right Zig Zag
					if(isRightZigZag(gpa, pa, x)){
						System.out.println("RightZigZag");
						//gpa is root
						if(root.data == gpa.data){
							System.out.println("RootCaseRightZigZag");
							rootCase = true;
						}
						//gpa is not root, need to move Great Gpa right or left reference to helper
						else{
							BinTreeNode Ggpa = root.grandparent(pa.data);
							if(Ggpa.right != null)
								if(Ggpa.right.data == gpa.data)
									Ggpa.right = helper;
							if(Ggpa.left != null)
								if(Ggpa.left.data == gpa.data)
									Ggpa.left = helper;
						}
						//splay
						pa.left = helper.right;
						helper.right = pa;
						gpa.right = helper.left;
						helper.left = gpa;
						if(rootCase){
							root = helper;
							done = true;
							break outer;
						}
					}
					//left Zig Zag
					else{
						System.out.println("LeftZigZag");
						//gpa is root
						if(root.data == gpa.data){
							//System.out.println("RootCaseLeftZigZag");
							rootCase = true;
						}
						//gpa is not root, need to move Great Gpa right or left reference to helper
						else{
							BinTreeNode Ggpa = root.grandparent(pa.data);
							if(Ggpa.left != null)
								if(Ggpa.left.data == gpa.data)
									Ggpa.left = helper;
							if(Ggpa.right != null)
								if(Ggpa.right.data == gpa.data)
									Ggpa.right = helper;
						}
						//splay
						pa.right = helper.left;
						helper.left = pa;
						gpa.left = helper.right;
						helper.right = gpa;
						if(rootCase){
							root = helper;
							done = true;
						}					
					}
				}
			}
			if(isZig(pa, x)){
				//System.out.println("Zig");
				if(isRightZig(pa, x)){
					System.out.println("RightZig");
					pa.right = helper.left;
					helper.left = pa;
					root = helper;
				}
				else{
					System.out.println("LeftZig");
					pa.left = helper.right;
					helper.right = pa;
					root = helper;
				}
				done = true;	
			}
			stop++;
			if(stop>15)
				done=true;
		}//end while
	}
	//is Zig Zig method, return true if it is
	public boolean isZigZig(BinTreeNode gpa, BinTreeNode pa, int x){
		if((gpa == null) || (pa == null))
			return false;
		if((gpa.right != null) && (pa.right != null)){
			if((gpa.right.data == pa.data) && (pa.right.data == x))
				return true;
		}
		if((gpa.left != null) && (pa.left != null)){
			if((gpa.left.data == pa.data) && (pa.left.data == x))
				return true;
		}
		return false;
	}
	//is Right Zig Zig method, return true if it is	
	public boolean isRightZigZig(BinTreeNode gpa, BinTreeNode pa, int x){
		if((gpa == null) || (pa == null))
			return false;
		if((gpa.right != null) && (pa.right != null))
			return ((gpa.right.data == pa.data) && (pa.right.data == x));
		return false;
	}
	//is Zig Zag method
	public boolean isZigZag(BinTreeNode gpa, BinTreeNode pa, int x){
		if((gpa == null) || (pa == null))
			return false;
		if((gpa.right != null) && (pa.left != null)){
			if((gpa.right.data == pa.data) && (pa.left.data == x))
				return true;
		}
		if((gpa.left !=null) && (pa.right != null)){
			if((gpa.left.data == pa.data) && (pa.right.data == x))
				return true;
		}
		return false;
	}
	//is Right Zig Zag mehtod
	public boolean isRightZigZag(BinTreeNode gpa, BinTreeNode pa, int x){
		if((gpa != null) && (pa != null))
			if((gpa.right !=null) && (pa.left != null))
				return((gpa.right.data == pa.data) && (pa.left.data == x));
		return false;
	}
	//is Zig method
	public boolean isZig(BinTreeNode pa, int x){
		if(pa != null)
			if((pa.right != null) && (pa.right.data == x))
				return true;
		if(pa != null)
			if((pa.left != null) && (pa.left.data == x))
			return true;
		return false;
	}
	//is right Zig
	public boolean isRightZig(BinTreeNode pa, int x){
		if(pa.right != null)
			return (pa.right.data == x);
		return false;
	}
	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		SplayTree tree = new SplayTree(); // Tree to test.
	
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
    			boolean done = false;			
				while(!done){  		
					try{     
				   	System.out.println("What value do you want to delete?");	       
						int val = Integer.parseInt(stdin.readLine());
						done=true;
		            BinTreeNode y = tree.delete(val);	
						if(y == null){
							System.out.println("Deleted failed (value is not the tree)");
						}
    		       	else{
	     			 		System.out.println("Deleted successfully");
						}
					}
					catch (Exception e){
						done=false;
					}
				}

			}
			 else if( ans == 3){
			 	boolean done = false;	
				while(!done){
					try{
						System.out.println("What value do you want to search for?");
						int val = Integer.parseInt(stdin.readLine());
						done = true;
						BinTreeNode y = tree.searchSplay(val);
						if(y.data != -1){
							System.out.println("The value is in the tree.");
						}
						else{
							System.out.println("The value is not in the tree.");
						}
					}
					catch (Exception e){
						done = false;
					}
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
	
}