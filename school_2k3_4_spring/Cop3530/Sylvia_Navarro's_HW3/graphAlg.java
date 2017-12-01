//Sylvia Navarro
//458859158
//4-17-03
import java.io.*;
import java.util.*;

public class graphAlg
{
	static graph InputGraph;
	
	public graphAlg(){
		InputGraph = new graph();
	}
	
	public static void main(String[] args) throws IOException{
		//initializations
		boolean another=true;
		int choice=0;
		String input="";
		graphAlg TGraph = new graphAlg();
		
		while (another)
		{
			//show menu
			menu();
			//read choice
			BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
			choice = Integer.parseInt(stdin.readLine());
			//switch on choice
			switch(choice){
				//1-read file, create graph object
				case 1:
				{
					System.out.println("Name of graph file.");
					readGraph(stdin.readLine());
					break;
				}
				//2-run FW
				case 2:
				{	
					InputGraph.FSW();
					break;
				}
				//3-run P
				case 3:
				{
					InputGraph.PA();				
					break;
				}
				//4-run BF
				case 4:
				{
					System.out.println("From which source vertex.");
					InputGraph.BFA(stdin.readLine());				
					break;				
				}
				//5-quit
				case 5:
					another=false;
					break;			
			}//end of switch
		}//end of while (continueMenu=true)
		System.exit(0);
	}//end of public static void main
	
	public static void menu (){
		//output menu
		System.out.println("1 - Enter name of graph file");
		System.out.println("2 - Run Floyd-Warshall's Algorithm");
		System.out.println("3 - Run Prim's Algorithm");
		System.out.println("4 - Run the Bellman-Ford Algorithm");
		System.out.println("5 - Quit");
	}
	
	public static void readGraph(String fname) throws IOException {
		BufferedReader fin = new BufferedReader (new FileReader (new File(fname)));
		while (fin.ready())
		{
			StringTokenizer tokenizer = new StringTokenizer(fin.readLine());
			String city1 = new String(tokenizer.nextToken()), 
				   city2 = new String(tokenizer.nextToken());
			InputGraph.insertCity(city1);
			InputGraph.insertCity(city2);
			
			int weight = Integer.parseInt(tokenizer.nextToken());
			InputGraph.insertEdge(city1, city2, weight);
		}//end of while
		
	}//end of public static void readGraph
}