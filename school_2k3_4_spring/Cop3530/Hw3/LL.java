/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 3

** Date:4-11-03

***************************************************************/

/****************Class Description***************************

** Linked List class for graph algorithms.  LL stores nodes.
** Nodes store references to an edge or a vertex. This class
** contains methods for finding and inserting a vertex and an edge. 

**************************************************************/

public class LL{
	
	Node head;
	
	public LL(){
		
		head = null;	
	}
	
	public LL(Node headIn){
		
		head = headIn;	
	}	
	
	public void insert(Vertex VIn){
		
		Node helper = head;
		
		if(helper == null){
			
			head = new Node(VIn);		
		}
		else{
			
			while(helper.next != null){
				
				helper  = helper.next;	
			}	
			
			helper.next = new Node(VIn);
		}	
	}
	
	public void insert(Edge EIn){
		
		Node helper = head;
		
		if(helper == null){
			
			head = new Node(EIn);		
		}
		else{
			
			while(helper.next != null){
				
				helper  = helper.next;	
			}	
			
			helper.next = new Node(EIn);
		}	
	}
	
	public Vertex findVertex(String nameIn){
		
		Node helper = head;
		
		while(helper != null){
			
			Vertex tmpV = helper.refV;
			
			if(tmpV.city.compareTo(nameIn) == 0){
				
				return tmpV;	
			}	
			
			helper = helper.next;
		}
		return null;	
	}	
	
	public Vertex findVertex(int index){
		
		Node helper = head;
		
		while(helper != null){
			
			Vertex tmpV = helper.refV;
			
			if(tmpV.index == index){
				
				return tmpV;
			}
			helper = helper.next;
		}
		return null;
	}
	public Edge findEdge(String r, String s){
		
		Node helper = head;
		
		while(helper != null){
			
			Edge tmpE = helper.refE;
			
			if(tmpE.name.compareTo(r + " " + s) == 0){
				
				return tmpE;	
			}	
			
			helper = helper.next;
		}
		return null;	
	}
	
	public int Size(){
	
		int size = 0;
		
		Node helper = head;
		
		while(helper != null){
			size++;
			helper = helper.next;	
		}	
		
		return size;
	}	
}