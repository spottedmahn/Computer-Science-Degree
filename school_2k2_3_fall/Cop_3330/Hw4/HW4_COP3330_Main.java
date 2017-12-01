/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 4

** Date:10/25/02

***************************************************************/

/****************Program Description***************************

** Matrix addition, multiplication, scalar. Two command line
** arguments, input file and output file.

**************************************************************/


import java.io.*;
import java.util.*;

public class HW4_COP3330_Main{


	public static void main(String[] args) throws IOException{
		boolean repeat = true;//main menu variable
		boolean t = true;
		boolean flag = false;//operations that fail
		String input = new String();//input from file
		String tmpS = new String();//temp string
		int Vpos = 0, N = 0, M = 0, i, j, tmpI = 0;
		HW4_COP3330_Matrix matrix;
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
		try{		
		while(repeat){
			//reading input
			input = in.readLine();;
			//EOF
			if(input == null){
				repeat = false;
				break;
			}
			//processing input
			StringTokenizer token = new StringTokenizer(input);
			tmpS = token.nextToken();
			//matrix command
			if(tmpS.compareTo("matrix") == 0){
				N = Integer.parseInt(token.nextToken());
				M = Integer.parseInt(token.nextToken());
				HW4_COP3330_Matrix mat = new HW4_COP3330_Matrix(N , M);
				//filling in array row by row
				for(i=0;i < N;i++){
					input = in.readLine();
					StringTokenizer token2 = new StringTokenizer(input);				
					for(j=0;j < M;j++){
						mat.setPosition(i, j, Double.parseDouble(token2.nextToken()));
					}
				}//end for loop for filling of matrix
				matrices.addElement(mat);
			}
			//add command
			else if(tmpS.compareTo("add") == 0){
					boolean fail = false;
					N = Integer.parseInt(token.nextToken());
					M = Integer.parseInt(token.nextToken());
					HW4_COP3330_Matrix q = new HW4_COP3330_Matrix(3, 3);
					HW4_COP3330_Matrix p = q.add((HW4_COP3330_Matrix) matrices.elementAt(N),(HW4_COP3330_Matrix) matrices.elementAt(M));
					if(p == null){
						pWriter.println("Add operation failed");
						fail = true;
					}
					else{ 
						while(token.hasMoreTokens()){
							N = Integer.parseInt(token.nextToken());
							HW4_COP3330_Matrix temp = (HW4_COP3330_Matrix)matrices.elementAt(N);
							p = q.add(p, (HW4_COP3330_Matrix) matrices.elementAt(N));
							if(p == null){
								fail = true;
							}
						}
					}
					if(!fail){
						matrices.addElement(p);
					}
			}
			//multilply command
			else if(tmpS.compareTo("mult") == 0){
				boolean fail = false;
				N = Integer.parseInt(token.nextToken());
				M = Integer.parseInt(token.nextToken());
				HW4_COP3330_Matrix q = new HW4_COP3330_Matrix(3, 3);
				HW4_COP3330_Matrix p = q.multiply((HW4_COP3330_Matrix) matrices.elementAt(N),(HW4_COP3330_Matrix) matrices.elementAt(M));
				if(p == null){
				 	fail = true;
					pWriter.println("Multiply operation failed");
				}
				else{
					while(token.hasMoreTokens() && !fail){
						
						N = Integer.parseInt(token.nextToken());
						p = q.multiply(p,(HW4_COP3330_Matrix) matrices.elementAt(N));
						if(p == null){
							pWriter.println("Multiply operation failed");
							fail = true;
						}
					}	
				}
				if(!fail){			
					matrices.addElement(p);
				}
			}
			//scalar command
			else if(tmpS.compareTo("scalar") == 0){
				M = Integer.parseInt(token.nextToken());
				HW4_COP3330_Matrix q = new HW4_COP3330_Matrix(3, 3);
				matrices.addElement(q.scalar((HW4_COP3330_Matrix) matrices.elementAt(M), Double.parseDouble(token.nextToken())));
			}			
			//print command			
			else if(tmpS.compareTo("print") == 0){
				tmpI = Integer.parseInt(token.nextToken());
				matrix = (HW4_COP3330_Matrix) matrices.elementAt(tmpI);
				pWriter.println("***** Matrix "+tmpI+" ******");
				for(i=0;i < matrix.getRows();i++){
					for(j=0;j < matrix.getCols();j++){
						pWriter.print(matrix.getPosition(i, j) +" ");
					}
					pWriter.println();
				}
			}			
			else if(tmpS.compareTo("quit") ==0){
				repeat = false;
				break;
			}
		}//end while loop
		}
		catch (EOFException e){
		
		}
	}//end main
}