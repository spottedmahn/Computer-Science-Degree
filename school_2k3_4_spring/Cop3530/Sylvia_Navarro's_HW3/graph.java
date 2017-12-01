//Sylvia Navarro
//458859158
//4-17-03

import java.util.*;
import java.io.*;

public class graph {
	
	//declare variables
	String [] cities;//cities array
	int cityCount;		//count of cities
	int inf;				//number used as infinity
	int [] [] adjMatrix;
	
	//allocate space
	public graph (){
		//initializations
		cities = new String [100];	
		cityCount=0;
		inf = 99999;

		adjMatrix = new int [100][100];
		for (int i=0; i<100; i++)
		{
			for (int j=0; j<100; j++)
			{
				if (i==j)adjMatrix[i][j]=0;//take care of diagnols
				else adjMatrix[i][j] = inf;
			}
		}
	}//end of public graph	
	
	//insert cities
	public void insertCity(String cityToInsert){
		boolean isThere=false;
		for (int i=0; i<cities.length;i++)
		{
			if (cities[i] != null)
			{
				if (cities[i].compareTo(cityToInsert)==0)
					isThere=true;
			}
		}
		if (!isThere)
		{
			int i=0;
			while (cities[i] != null)
			{
				i++;
			}
			cities[i]=new String(cityToInsert);
			cityCount++;
		}
	}//end of public void insertCity
	
	//find cities index
	public int findIndex(String cityToFind){
		for (int i=0; i<cities.length;i++)
		{
			if (cities[i] != null)
			{
				if (cities[i].compareTo(cityToFind) == 0)
				return i;
			}
		}
		return -1;
	}//end of public int findIndex
	
	//insert adjMatrix (egde)
	public void insertEdge(String city1, String city2, int weight){
		int city1Index=findIndex(city1);
		int city2Index=findIndex(city2);		
		adjMatrix[city1Index][city2Index]=weight;
		adjMatrix[city2Index][city1Index]=weight;	//need to insert at inverse b/c undirected
	}//end of public void insertEdge
	
	//copy adj matrix for use in FWA
	public int [][] copyMatrix(){
		int [][] copy = new int[adjMatrix.length][adjMatrix[0].length];	
		for (int i=0; i<cityCount; i++)
		{
			for (int j=0; j<cityCount; j++)
			{
				copy[i][j] = adjMatrix[i][j];
			}
		}
		return copy;
	}//end of public int [][[] copyMatrix

	//print graph
	public void printGraph() throws IOException{
		
		PrintWriter pwriter = new PrintWriter (new FileWriter (new File ("outputgraph.txt")), true);
		
		for (int i=0;i<cityCount;i++)
		{
			for (int j=0;j<cityCount;j++)
			{
				if (adjMatrix[i][j] < inf)
				{
					pwriter.print(adjMatrix[i][j]+" ");
					System.out.print(adjMatrix[i][j]+" ");
				}
				else
				{
					pwriter.print("* ");
					System.out.print("* ");
				}
			}
			pwriter.println();
			System.out.println();
		}
		pwriter.close();
	}//end of public void printGraph
	//print graph
	public void printGraphMatrixStyle(int [][] graph) throws IOException{
		
		PrintWriter pwriter = new PrintWriter (new FileWriter (new File ("matrixstyle.txt")), true);
		
		for (int i=0;i<cityCount;i++)
		{
			for (int j=0;j<cityCount;j++)
			{
				if (graph[i][j] < inf)
				{
					pwriter.print(graph[i][j]+" ");
					//System.out.print(graphMatrix[i][j]+" ");
				}
				else
				{
					pwriter.print("* ");
					//System.out.print("* ");
				}
			}
			pwriter.println();
			//System.out.println();
		}
		pwriter.close();
	}//end of public void printGraphMatrixStyle
	
	
	//perform FWA & print
	public void FSW()throws IOException{
		int [][] graph = copyMatrix();
		int newDist=0;
		PrintWriter pwriter = new PrintWriter (new FileWriter (new File("FW.txt")),true);
		//perform algorithm
		for (int k=0; k<cityCount; k++)
		{
			for (int i=0; i<cityCount; i++)
			{
				for (int j=0; j<cityCount; j++)
				{
					newDist = graph[i][k] + graph[k][j];
					if (newDist<graph[i][j])
					{
						graph[i][j] = newDist;
					}
				}
			}
		}
		//print new FWA graph - just upperhalf
		int i=0;
		while (i<cityCount)
		{
			for (int j=i; j<cityCount; j++)
			{
				if ((graph[i][j] != inf) && (graph[i][j] != 0))
				{
					pwriter.println(cities[i]+"	"+ cities[j]+ "	"+ graph[i][j]);
					System.out.println(cities[i]+"	"+ cities[j]+ "	"+ graph[i][j]);
				}
			}
			i++;
		}
		pwriter.close();
	}//end of public void FSW
		
	//perform BFA & print
	public void BFA(String vertexCity)throws IOException{
	
		int [][]graph = copyMatrix();
		int [] distance = new int [graph.length];
		
		PrintWriter pwriter = new PrintWriter (new FileWriter (new File ("bf.txt")), true);
	
		int vertex = findIndex(vertexCity);
		//run algorithm
		for (int i=0; i<cityCount; i++)
		{
			if (i == vertex)
				distance[i] = 0;
			else
			   distance[i] = inf;
		}
		for (int i=0; i<cityCount; i++)
		{
			for (int j=0; j<cityCount;j++)
			{
				if (distance[j] < inf)
				{
					for (int k=0; k<cityCount;k++)
					{
						if (distance[j] + graph[j][k] < distance[k])
							distance[k] = distance[j] + graph[j][k];
					}
				}
			}
		}
		//print vertex city		
		pwriter.println("Source is " + cities[vertex]);
		System.out.println("Source is " + cities[vertex]);
		for (int j=0; j<cityCount; j++)
		{
			if ((distance[j] != inf) && (distance[j] != 0))
			{
				if (j != vertex)
						pwriter.println(cities[j]+"	"+ distance[j]);
						System.out.println(cities[j]+"	"+ distance[j]);
			}
		}
		pwriter.close();
	}//end of public void BFA	
			
	//perform PA & print
	public void PA() throws IOException{
	
		int [][] graph = copyMatrix();
		int [] distance = new int [graph.length];
		boolean [] visited = new boolean [distance.length];
		PrintWriter pwriter = new PrintWriter (new FileWriter (new File ("PA.txt")), true);
		
		for (int i=0; i<distance.length; i++){
			distance[i] = inf;
			visited[i] = false;
		}//end of for
		
		//start with first vertex read in (i = 0)
		int i=0;
		distance[i]=0;
		visited[i]=true;
		
		//run algorithm
		while (!allVisited(visited)) 
		{	
	 		//examine vertex u (where i=0)
			for (int j=0; j<cityCount; j++)
			{
					if (graph[i][j] != inf)//		for each v - examining edge (u,v)
					{
						for (int k=0; k<cityCount; k++)
						{
							if (graph[j][k] < distance[k])
							{																	
								distance[k] = graph[j][k];		//edge (u,v) relaxed											
								if (visited[k] ==  false) 
								{
									visited[k] = true;
									j=k;
								}
							}
						}
					}
			}
			visited[i] = true;
		}	
		printGraphMatrixStyle(graph);		
		//print MST
		int mst=0;
		for (i=0; i<cityCount; i++){
			pwriter.println("Distance"+i+"="+distance[i]);		
			System.out.println("Distance"+i+"="+distance[i]);
			mst = mst + distance[i];
		}
		System.out.println("MST Weight = " + mst);
		pwriter.close();
	}//end of public void PA
	
	public boolean allVisited(boolean [] visit){
		for (int i=0; i<cityCount;i++)
		{
			if (visit[i] == false)
				return false;
		}
		return true;		
	}//end of public boolean allVisited
		
}