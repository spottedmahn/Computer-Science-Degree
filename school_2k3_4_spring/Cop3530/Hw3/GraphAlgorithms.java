/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 3

** Date:4-11-03

***************************************************************/

/****************Program Description***************************

** Please see COP3530Hmk03.doc 

**************************************************************/

import java.io.*;
import java.util.*;

public class GraphAlgorithms{
	
	static LL VLL, ELL;
	static String fName;
	
	public GraphAlgorithms(){
		
		VLL = new LL();
		ELL = new LL();	
		fName = new String();
	}
	//read graph from file method
	public static void readGraph() throws IOException{
		
		//input format: v1 v2 weight
		
		int Vindex = 0;
		
		String tmpS = new String();
		BufferedReader fIn = new BufferedReader(new FileReader(new File(fName)));
		
		while(fIn.ready()){
			
			StringTokenizer tokens = new StringTokenizer(fIn.readLine());
			String V1 = new String(tokens.nextToken());
			String V2 = new String(tokens.nextToken());
			//if not V1 is not is VLL insert
			if(VLL.findVertex(V1) == null){
				
				VLL.insert(new Vertex(V1, 0, Vindex));
				++Vindex;
			}
			//if not V2 is not is VLL insert
			if(VLL.findVertex(V2) == null){
				
				VLL.insert(new Vertex(V2 , 0, Vindex));
				++Vindex;
			}
			
			ELL.insert(new Edge(VLL.findVertex(V1), VLL.findVertex(V2), V1 + " " + V2, Integer.parseInt(tokens.nextToken())));
			//incrementing Incident Edges (never used in program, the book showed the edge list structure having it)
			Vertex tmpV = VLL.findVertex(V1);
			tmpV.numIncidentEdges++;
			tmpV = VLL.findVertex(V2);
			tmpV.numIncidentEdges++;
		}
		fIn.close();
	}
	//Prim's Algorithm
	public static void Prims() throws IOException{
		
		int startVertexIndex = 0;
		
		String[] S = new String[VLL.Size()];
		
		LL edges = new LL();
		
		Vertex tmpV = VLL.findVertex(startVertexIndex);
		
		S[0] = new String(tmpV.city);
		
		int i=1;
		while(S[VLL.Size() - 1] == null){
			//finding minimum incident edge & vertex	
			Vertex minV = findMinIncidentVertex(S);
			Edge minE = findMinIncidentEdge(S);
			//adding it to S
			S[i] = new String(minV.city);
			//adding edge to MST
			if(minE != null){

				edges.insert(new Edge(minE.x, minE.y, minV.city, minE.weight));
			}
			i++;
		}
		//printing results of algorithm
		PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_Prims_from_"+fName)), true);
		
		int mstWeight = 0;
		
		Node helper = edges.head;
		while(helper != null){

			Edge tmpE = helper.refE;
			
			mstWeight += tmpE.weight;
			
			pwriter.println(tmpE.x.city +"\t"+tmpE.y.city+"\t"+tmpE.weight);
			
			helper = helper.next;
		}
		
		pwriter.println("MST Weight == "+mstWeight);
		pwriter.close();
		System.out.println("output can be found in file output_Prims_from_"+fName);
	}
	//Find Minimum Incident Vertex
	public static Vertex findMinIncidentVertex(String[] S){
		
		int[][] graph = copyEdgeListToAdjacencyMatrix();
		
		int i = 0, minWeight = 1000;
		
		Vertex minV = null, tmpV;
		
		while(S[i] != null){
			
			for(int j=0;j<VLL.Size();j++){
				
				if(graph[i][j] < minWeight){
					
					tmpV = VLL.findVertex(j);
					
					if(!inS(S, tmpV.city)){
						
						minWeight = graph[i][j];
						minV = VLL.findVertex(j);
					}
				}
			}
			i++;
		}	
		return minV;
	}
	//Find Minimum Incident Edge
	public static Edge findMinIncidentEdge(String[] S){
		
		int[][] graph = copyEdgeListToAdjacencyMatrix();
		
		int i = 0, minWeight = 1000;
		
		Edge minE = null, tmpE;
		Vertex tmpV1, tmpV2;
		
		while(S[i] != null){
			
			tmpV1 = VLL.findVertex(S[i]);
			
			for(int j=0;j<VLL.Size();j++){
								
				if(graph[tmpV1.index][j] < minWeight){
					
					tmpV2 = VLL.findVertex(j);
						
					if(!inS(S, tmpV2.city)){
						
						minWeight = graph[tmpV1.index][j];
						minE = ELL.findEdge(tmpV1.city, tmpV2.city);
						if(minE == null)
							minE = ELL.findEdge(tmpV2.city, tmpV1.city);
					}
				}
			}
			i++;
		}
		return minE;
	}	
	//inS
	public static boolean inS(String[] S, String in){
		
		int i=0;
		while(S[i] != null){
			if(S[i].compareTo(in) == 0){
				return true;
			}
			i++;
		}
		return false;
	}
	//Bellman-Ford using adjacency matrix data structure
	public static void BellmanFordAM(Vertex startV) throws IOException{
		
		int[][] graph = copyEdgeListToAdjacencyMatrix();
		
		int[] D = new int[VLL.Size()];
			
		D[startV.index] = 0;
		
		for(int i=0;i < VLL.Size();i++){
			if(i != startV.index)
				D[i] = 1000;
		}			
		
		for(int i=0;i<VLL.Size();i++){
			for(int j=0;j<VLL.Size();j++){
				if(D[j] < 1000){
					for(int k=0;k<VLL.Size();k++){
						
						if(D[j] + graph[j][k] < D[k]){

							D[k] = D[j] + graph[j][k];
						}
					}
				}
			}
		}
		
		PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_Bellman-Ford_Starting_from_"+startV.city+"_input_from_"+fName)), true);
		
		for(int i=0;i<D.length;i++){
			if(D[i] < 1000){
				if(i != startV.index){
					Vertex tmpV = VLL.findVertex(i);
					pwriter.println(tmpV.city +"\t"+D[i]);
				}
			}
		}
		pwriter.close();
		System.out.println("output can be found in file output_Bellman-Ford_Starting_from_"+startV.city+"_input_from_"+fName);
	}
	//Bellman-Ford using edge list structure
	public static void BellmanFordEL(Vertex startV) throws IOException{
		
		int[] D = new int[VLL.Size()];
		
		D[startV.index] = 0;
		
		for(int i=0;i < VLL.Size();i++){
			if(i != startV.index)
				D[i] = 1000;
		}
		//traversing thru all vertices
		Node helper = VLL.head;
		while(helper != null){
			//getting vertex from VLL
			Vertex tmpV = helper.refV;
			//if vertex is start vertex move to next node
			if(tmpV.city.compareTo(startV.city) == 0){
				helper = helper.next;
			}
			//else relax edges
			else{
				
				Node EdgeHelper = ELL.head;
				//traversing thru all edges
				while(EdgeHelper != null){
					//getting edge from ELL
					Edge edgeUZ = EdgeHelper.refE;
					
					StringTokenizer tokens = new StringTokenizer(edgeUZ.name);
					String U = tokens.nextToken();
					String Z = tokens.nextToken();
					
					Vertex tmpU = VLL.findVertex(U);
					Vertex tmpZ = VLL.findVertex(Z);
					
					if((D[tmpU.index] + edgeUZ.weight) < D[tmpZ.index]){
						D[tmpZ.index] = D[tmpU.index] + edgeUZ.weight;
					}	
					else if((D[tmpZ.index] + edgeUZ.weight) < D[tmpU.index]){
						D[tmpU.index] = D[tmpZ.index] + edgeUZ.weight;
					}
					EdgeHelper = EdgeHelper.next;
				}
				helper = helper.next;
			}
		}
		//Printing results of algorithm
		PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_Bellman-Ford_Starting_from_"+startV.city+"_input_from_"+fName)));
		pwriter.println("Source is "+startV.city);
		//traversing thru vertices
		helper = VLL.head;
		while(helper != null){
		
			Vertex tmpV = helper.refV;
			//if tmpV == startV don't print move to next vertex
			if(tmpV.city.compareTo(startV.city) == 0){
				helper = helper.next;
			}
			else{
				if(D[tmpV.index] < 999){
					pwriter.println(tmpV.city + "\t" + D[tmpV.index]);	
				}
				helper = helper.next;
			}
		}	
		pwriter.close();
		System.out.println("output can be found in file output_Bellman-Ford_Starting_from_"+startV.city+"_input_from_"+fName);
	}
	//Floyd Warshall's algorithm
	public static void FloydWarshall() throws IOException{
		
		int[][] D = copyEdgeListToAdjacencyMatrix();
		
		for(int k=0;k<D.length;k++){
			for(int i=0;i<D.length;i++){
				for(int j=0;j<D.length;j++){
					if(i != j){
						if(D[i][j] > (D[i][k] + D[k][j])){
							D[i][j] = D[i][k] + D[k][j];
						}
					}
				}
			}
		}
		//Printing results of algorithm
		PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_FloydWarshall_from_"+fName)));
		Node h1 = VLL.head;
		while(h1 != null){
			
			Vertex tmpU = h1.refV;
			
			Node h2 = VLL.head;
			while(h2 != null){
			
				Vertex tmpZ = h2.refV;
				
				if(tmpZ.city.compareTo(tmpU.city) != 0){
				
					if(D[tmpU.index][tmpZ.index] < 999){
					
						if(tmpU.index >= tmpZ.index){
					
							pwriter.println(tmpU.city + "\t" + tmpZ.city + "\t" + D[tmpU.index][tmpZ.index]);
						}
					}
				}
				h2 = h2.next;
			}
			h1 = h1.next;
		}
		pwriter.close();	
		System.out.println("output can be found in file output_FloyWarshall_from_"+fName);
	}	
	//Copy Edge List to Adjacency Matrix
	public static int[][] copyEdgeListToAdjacencyMatrix(){
		
		int[][] D = new int[VLL.Size()][VLL.Size()];
		
		Node helper = VLL.head;
		
		while(helper != null){
			
			Vertex tmpU = helper.refV;
			
			Node helper2 = VLL.head;
			
			while(helper2 != null){
				
				Vertex tmpZ = helper2.refV;
				
				Edge EdgeUZ = ELL.findEdge(tmpU.city, tmpZ.city);
				Edge EdgeZU = ELL.findEdge(tmpZ.city, tmpU.city);				
				
				if((EdgeUZ == null) && (EdgeZU == null)){
					D[tmpU.index][tmpZ.index] = 1000;
					D[tmpZ.index][tmpU.index] = 1000;
				}
				else if(EdgeUZ != null){
					
					D[tmpU.index][tmpZ.index] = EdgeUZ.weight;
					D[tmpZ.index][tmpU.index] = EdgeUZ.weight;
				}
				else if(EdgeZU != null){
				
					D[tmpZ.index][tmpU.index] = EdgeZU.weight;
					D[tmpU.index][tmpZ.index] = EdgeZU.weight;
				}				
				
				helper2 = helper2.next;
			}
			helper = helper.next;
		}
		return D;
	}
	public static void print(){
		
		Node helper = VLL.head;
		
		while(helper != null){
		
			Vertex tmpV = helper.refV;
			System.out.println("tmpV.city == " + tmpV.city + " tmpV.numIncidentEdges == " + tmpV.numIncidentEdges);
			helper = helper.next;
		}
		
		helper = ELL.head;
		
		while(helper != null){
			
			Edge tmpE = helper.refE;
			System.out.println("tmpE.name == " + tmpE.name + " tmpE.weight == " + tmpE.weight);
			helper = helper.next;
		}	
	}
	public static void main(String[] args) throws IOException{

		GraphAlgorithms ga = new GraphAlgorithms();
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String tmpS = new String();
		
		boolean done = false;
		
		while(!done){
			
			menu();
			try{
				int option = Integer.parseInt(stdin.readLine());
				
				if(option == 1){
					
					System.out.println("Please enter file name");
					
					fName = stdin.readLine();
					
					ga.readGraph();

				}
				else if(option == 2){
					FloydWarshall();
					
				}
				else if(option == 3){
					Prims();
				}
				else if(option == 4){
					System.out.println("Please enter starting vertex");
					Vertex tmpV = VLL.findVertex(stdin.readLine());
					if(tmpV != null){
						BellmanFordEL(tmpV);
					}
					else{
						System.out.println("Starting vertex not found");
					}
				}
				else if(option == 5){
					
					done = true;
					break;	
				}
			}
			catch (NumberFormatException e){
				System.out.println("Invalid input, please enter a number between 1 & 5");
			}
		}
	}
	
	public static void menu(){

		System.out.println("1) Enter name of graph file");
		System.out.println("2) Run Floyd-Warshall's Algorithm");
		System.out.println("3) Run Prim's Algorithm");
		System.out.println("4) Run Bellman-Ford Algorithm");
		System.out.println("5) Quit");
	}	
}