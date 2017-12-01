/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 3

** Date:4-11-03

***************************************************************/

/****************Class Description***************************

** Stores references to an edge or a vertex plus a reference to
** the next node in the linked list. 

**************************************************************/
public class Node{
	
	Vertex refV;
	Edge refE;
	Node next;
	
	public Node(Vertex refVIn){
		
		refV = refVIn;
		refE = null;
		next = null;	
	}	
	
	public Node(Edge refEIn){
		
		refV = null;
		refE = refEIn;	
		next = null;
	}
}