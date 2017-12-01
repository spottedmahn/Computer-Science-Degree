/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 1

** Date:2-13-03

***************************************************************/

/****************Class Description***************************

** BinTreeNode class contains methods for inserting, deleting
** and searching for nodes in a binary search tree.

**************************************************************/

import java.io.*;

public class BinTreeNode{
	//references for right and left child
	public BinTreeNode left,right;
	//actual value stored a that particular node
	public int data;
	
	public BinTreeNode(int x){
		left = null;
		right = null;
		data = x;
	}
	//recursive insert method
	public void insert(int x){
		if(data > x){
			if(left != null){
				left.insert(x);
			}
			else{
				left = new BinTreeNode(x);
				
			}
		}
		else{
			if(right != null){
				right.insert(x);
			}
			else{
				right = new BinTreeNode(x);
			}
		}	
	}
	//delete method, returns a BinTreeNode for Splay Tree
	public BinTreeNode delete(int x){
		BinTreeNode helper = searchNode(x);
		BinTreeNode parentNode = parent(x);
		if(helper == null){
			//System.out.println("helper null");
			return null;
		}
		//node to delete is a leaf node
		if(helper.isLeafNode()){
			if(parentNode.data < x){
				parentNode.right = null;
			}
			else{
				parentNode.left = null;
			}
			//returning parent of actual node deleted for splay tree
			return parentNode;
		}
		else{
			//node has one child
			if(helper.hasOneChild()){
				if(helper.right == null){
					if(parentNode.data < x){
						parentNode.right = helper.left;
					}
					else{
						parentNode.left = helper.left;
					}
				}
				else{
					if(parentNode.data < x){
						parentNode.right = helper.right;
					}
					else{
						parentNode.left = helper.right;
					}
				}
				//returning parent of actual node deleted for splay tree
				return parentNode;
			}
			//node has two children
			else{
				int newValue = helper.right.minVal();
				BinTreeNode parentNode2 = parent(x);
				delete(newValue);
				helper.data = newValue;
				return parentNode2;
			}
		}
		//return null;
	}
	//recursive search method
	public boolean search(int x){
		if(data == x){
			return true;
		}
		else{
			if(data > x){
				if(left != null){
					left.search(x);
				}
				else{
					return false;
				}
			}
			else{
				if(right != null){
					return right.search(x);
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	//recursive search node method, returns reference to node that was searched for
	public BinTreeNode searchNode(int x){
		if(data == x){
			return this;
		}
		else{
			if(data < x){
				if(right != null){
					return right.searchNode(x);
				}
			}
			else{
				if(left != null)
					return left.searchNode(x);
			}
		}
		return null;
	}
	//recursive search for splay tree method, returns a reference to a BinTreeNode for splaying
	public BinTreeNode searchSplay(int x){
		if(data == x)
			return this;
		else{
			if(x < data){
				if(left != null){
					return left.searchSplay(x);
				}
				else{
					return this;
				}
			}
			else{
				if(right != null){
					return right.searchSplay(x);
				}
				else
					return this;
			}
		}
	}
	//parent method, returns reference to parent
	public BinTreeNode parent(int x){
		if(left == null && right == null)
			return null;
		if(left != null && left.data == x)
			return this;
		if(right != null && right.data == x)
			return this;
		if(left != null && data > x)
			return left.parent(x);
		else if(right != null && data < x)
			return right.parent(x);
		return null;
	}
	//grandparent method
	public BinTreeNode grandparent(int x){
		if((right == null) && (left == null))
			return null;
		if(left != null){
			if((left.left != null) && (left.left.data == x))
				return this;
			else if((left.right !=null) && (left.right.data == x))
				return this;
		}
		if(right != null){
			//System.out.println("Gpa Method: right !null");
			if((right.right != null) && (right.right.data == x)){
				//System.out.println("Gpa Method: right.right.data == x");
				return this;
			}
			else if((right.left != null) && (right.left.data == x))
				return this;
		}
		if((right != null) && (x > data))
			return this.right.grandparent(x);
		if((left != null) && (x < data))
			return this.left.grandparent(x);
		return null;
	}
	//is Leaf Node method	
	public boolean isLeafNode(){
		return(right == null && left == null);
	}
	//has one child method
	public boolean hasOneChild(){
		if(right == null && left != null)
			return true;
		else if(left == null && right != null)
			return true;
		else
			return false;
	}
	//minVal
	public int minVal(){
		if(left == null)
			return data;
		else 
			return left.minVal();
	}
	//maxVal
	public int maxVal(){
		if(right == null)
			return data;
		else 
			return right.maxVal();
	}
	//print heap index's
	public void print(int x){
		if(left != null)
			left.print(2*x);
		System.out.println(data + "\t "+ x + " ");
		if(right != null)
			right.print(2*x + 1);
	}
}

