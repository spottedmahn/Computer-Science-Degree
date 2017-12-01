/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 3

** Date:4-11-03

***************************************************************/

/****************Class Description***************************

**  

**************************************************************/

public class Edge{
	Vertex x, y;
	String name;
	int weight;
	
	public Edge(Vertex xIn, Vertex yIn, String nameIn, int weightIn){
		
		x = xIn;
		y = yIn;
		name = nameIn;
		weight = weightIn;	
	}
}	