/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 4

** Date:10/25/02

***************************************************************/

/****************Program Description***************************

** Matrix addition, multiplication, scalar. 
**
**
**

**************************************************************/


import java.io.*;
import java.util.*;

public class HW4{


	public static void main(String[] args) throws IOException{
//	System.out.println("Here");
		boolean repeat = true;
		boolean t = true;
		boolean flag = false;
		String input = new String();
		String tmpS = new String();
		int Vpos = 0, N = 0, M = 0, i, j, tmpI = 0;
		Matrix matrix;
		Vector matrices = new Vector();
		//input file
		File Fin = new File(args[0]);
		FileReader Freader = new FileReader(Fin);
		BufferedReader in = new BufferedReader(Freader);
		//output file
		File Fout = new File(args[1]);
		FileOutputStream FOutStream = new FileOutputStream(Fout);
		PrintWriter pWriter = new PrintWriter(FOutStream, t);
		//menu		
		while(repeat){
			//reading input
			input = in.readLine();
			System.out.println(input);
			//processing input
			StringTokenizer token = new StringTokenizer(input);
			tmpS = token.nextToken();
			
			//matrix command
			if(tmpS.compareTo("matrix") == 0){
				N = Integer.parseInt(token.nextToken());
				M = Integer.parseInt(token.nextToken());
				Matrix mat = new Matrix(N , M);
				//filling in array row by row
				for(i=0;i < N;i++){
					input = in.readLine();
					StringTokenizer token2 = new StringTokenizer(input);				
					for(j=0;j < M;j++){
						mat.setMatrix(i, j, Double.parseDouble(token2.nextToken()));
					}
				}//end for loop for filling of matrix
				matrices.addElement(mat);
				//System.out.println("V.capacity "+matrices.capacity());
				//Vpos++;
			}
			//add command
			else if(tmpS.compareTo("add") == 0){
					boolean fail = false;
					N = Integer.parseInt(token.nextToken());
					M = Integer.parseInt(token.nextToken());
					Matrix q = new Matrix(3, 3);
					Matrix p = q.add((Matrix) matrices.elementAt(N),(Matrix) matrices.elementAt(M));
					System.out.println("printing addition");
										
					 
					if(p == null){
						System.out.println("Null");
						pWriter.println("Add operation failed");
						fail = true;
					}
					else{ 
						while(token.hasMoreTokens()){
							N = Integer.parseInt(token.nextToken());
							Matrix temp = (Matrix)matrices.elementAt(N);
							temp.Print();
							p.Print();
							p = q.add((Matrix) matrices.elementAt(N), p);
							
							if(p == null){
								System.out.println("Null");
								fail = true;
							}

						}
					}
				   if ( fail == false) {p.Print();}
					if(!fail){

						matrices.addElement(p);
					   System.out.println("we are adding "+matrices.size());						
					}
					//System.out.println("V.capacity "+matrices.capacity());
			}
			//multilply command
			else if(tmpS.compareTo("mult") == 0){
				System.out.println("Here in mult");
				boolean fail = false;
				N = Integer.parseInt(token.nextToken());
				M = Integer.parseInt(token.nextToken());
				Matrix q = new Matrix(3, 3);
				
				System.out.println("before multiply");
				Matrix p = q.multiply((Matrix) matrices.elementAt(N),(Matrix) matrices.elementAt(M));
				
				if(p == null){
				  fail = true;
				System.out.println("Null");
					pWriter.println("Multiply operation failed");
				}
				else{
					while(token.hasMoreTokens()){
						N = Integer.parseInt(token.nextToken());
						p = q.multiply(p,(Matrix) matrices.elementAt(N));
						if(p == null){
						System.out.println("Null");
							fail = true;
						}

					}	
				}
				System.out.println("after multiply");
				if(!fail){			
					matrices.addElement(p);
				}
					System.out.println("flag = "+flag);
				//System.out.println("V.capacity "+matrices.capacity());
				//Vpos++;				
			}
			//scalar command
			else if(tmpS.compareTo("scalar") == 0){
				M = Integer.parseInt(token.nextToken());
				System.out.println("M = "+M);
				Matrix q = new Matrix(3, 3);
				//System.out.println("V.capacity "+matrices.capacity());
				matrices.addElement(q.scalar((Matrix) matrices.elementAt(M), Double.parseDouble(token.nextToken())));
				//Vpos++;
			}			
			//print command			
			else if(tmpS.compareTo("print") == 0){
				tmpI = Integer.parseInt(token.nextToken());
				matrix = (Matrix) matrices.elementAt(tmpI);
				pWriter.println("***** Matrix "+tmpI+" ******");
				for(i=0;i < matrix.a.length;i++){
					for(j=0;j < matrix.a[0].length;j++){
						pWriter.print(matrix.a[i][j] +" ");
					}
					pWriter.println();
					
				}
			}			
			else if(tmpS.compareTo("quit") ==0){
				repeat = false;
				break;
			}
			
		}//end while loop
	}//end main
}