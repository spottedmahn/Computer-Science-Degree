/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: Final Project

** Date:4-18-03

***************************************************************/

/****************Class Description***************************\

** 

**************************************************************/


import java.io.*;
import java.util.*;

public class asxCreator{
	
	public String directory;
	
	public asxCreator(){
		directory = null;
	}
	
	public asxCreator(String in){
		directory = in;
	}
	
	public void setDirectory(String in){
		directory = in;
	}
	public void createASXFiles() throws IOException{
		
		String[] tmpDirOfMovies;
		String[] movies;
		String currentD = new String();
		
		File directoryIn = new File(directory);

		movies = directoryIn.list();
		
		for(int i=0;i<movies.length;i++){
			
			currentD = directory + "\\" + movies[i];

			File testIsFile = new File(currentD);

			if(!testIsFile.isFile()){
				
				
				File currentFiles = new File(currentD);
				
				tmpDirOfMovies = currentFiles.list();

				for(int j=0;j<tmpDirOfMovies.length;j++){
					
					if(tmpDirOfMovies[j].endsWith(".avi") || tmpDirOfMovies[j].endsWith(".mpg")){
						
						try{
							PrintWriter pwriter = new PrintWriter(new FileWriter(new File(currentD + "\\" + movies[i] + ".asx")), true);
							
							pwriter.println("<ASX Version = \"3.0\">");
							pwriter.println("\t<ENTRY>");
							
							if(tmpDirOfMovies[j].charAt(0) == '0' || tmpDirOfMovies[j].charAt(0) == '1' || tmpDirOfMovies[j].charAt(0) == '2' || 
								tmpDirOfMovies[j].charAt(0) == '3' || tmpDirOfMovies[j].charAt(0) == '4' || tmpDirOfMovies[j].charAt(0) == '5' || 
								tmpDirOfMovies[j].charAt(0) == '6' || tmpDirOfMovies[j].charAt(0) == '7' || tmpDirOfMovies[j].charAt(0) == '8' || 
								tmpDirOfMovies[j].charAt(0) == '9' || tmpDirOfMovies[j].charAt(0) == 'a' || tmpDirOfMovies[j].charAt(0) == 'A' || 
								tmpDirOfMovies[j].charAt(0) == 'b' || tmpDirOfMovies[j].charAt(0) == 'B' || tmpDirOfMovies[j].charAt(0) == 'c' || 
								tmpDirOfMovies[j].charAt(0) == 'C'){
								
								pwriter.println("\t\t<REF HREF=\"\\\\videofactory\\movies_#-c\\"+movies[i]+"\\"+tmpDirOfMovies[j]+"\" />");
		
							}
							else if(tmpDirOfMovies[j].charAt(0) == 'd' || tmpDirOfMovies[j].charAt(0) == 'D' || tmpDirOfMovies[j].charAt(0) == 'e' || 
										tmpDirOfMovies[j].charAt(0) == 'E' || tmpDirOfMovies[j].charAt(0) == 'f' || tmpDirOfMovies[j].charAt(0) == 'F' || 
										tmpDirOfMovies[j].charAt(0) == 'g' || tmpDirOfMovies[j].charAt(0) == 'G' || tmpDirOfMovies[j].charAt(0) == 'h' || 
										tmpDirOfMovies[j].charAt(0) == 'H'){
									
								pwriter.println("\t\t<REF HREF=\"\\\\videofactory\\movies_d-h\\"+movies[i]+"\\"+tmpDirOfMovies[j]+"\" />");		
							}
							else if(tmpDirOfMovies[j].charAt(0) == 'i' || tmpDirOfMovies[j].charAt(0) == 'I' || tmpDirOfMovies[j].charAt(0) == 'j' || 
										tmpDirOfMovies[j].charAt(0) == 'J' || tmpDirOfMovies[j].charAt(0) == 'k' || tmpDirOfMovies[j].charAt(0) == 'K' || 
										tmpDirOfMovies[j].charAt(0) == 'l' || tmpDirOfMovies[j].charAt(0) == 'L' || tmpDirOfMovies[j].charAt(0) == 'm' || 
										tmpDirOfMovies[j].charAt(0) == 'M'){
									
								pwriter.println("\t\t<REF HREF=\"\\\\videofactory\\movies_i-m\\"+movies[i]+"\\"+tmpDirOfMovies[j]+"\" />");		
							}						
							else if(tmpDirOfMovies[j].charAt(0) == 'n' || tmpDirOfMovies[j].charAt(0) == 'N' || tmpDirOfMovies[j].charAt(0) == 'o' || 
										tmpDirOfMovies[j].charAt(0) == 'O' || tmpDirOfMovies[j].charAt(0) == 'p' || tmpDirOfMovies[j].charAt(0) == 'P' || 
										tmpDirOfMovies[j].charAt(0) == 'q' || tmpDirOfMovies[j].charAt(0) == 'Q' || tmpDirOfMovies[j].charAt(0) == 'r' || 
										tmpDirOfMovies[j].charAt(0) == 'R' || tmpDirOfMovies[j].charAt(0) == 's' || tmpDirOfMovies[j].charAt(0) == 'S'){
									
								pwriter.println("\t\t<REF HREF=\"\\\\videofactory\\movies_n-s\\"+movies[i]+"\\"+tmpDirOfMovies[j]+"\" />");		
							}						
							else if(tmpDirOfMovies[j].charAt(0) == 't' || tmpDirOfMovies[j].charAt(0) == 'T' || tmpDirOfMovies[j].charAt(0) == 'u' || 
										tmpDirOfMovies[j].charAt(0) == 'U' || tmpDirOfMovies[j].charAt(0) == 'v' || tmpDirOfMovies[j].charAt(0) == 'V' || 
										tmpDirOfMovies[j].charAt(0) == 'w' || tmpDirOfMovies[j].charAt(0) == 'W' || tmpDirOfMovies[j].charAt(0) == 'x' || 
										tmpDirOfMovies[j].charAt(0) == 'X' || tmpDirOfMovies[j].charAt(0) == 'y' || tmpDirOfMovies[j].charAt(0) == 'Y' ||
										tmpDirOfMovies[j].charAt(0) == 'z' || tmpDirOfMovies[j].charAt(0) == 'Z'){
									
								pwriter.println("\t\t<REF HREF=\"\\\\videofactory\\movies_t-z\\"+movies[i]+"\\"+tmpDirOfMovies[j]+"\" />");		
							}					
									pwriter.println("\t</ENTRY>");
							pwriter.println("</ASX>");
							pwriter.close();	
						}
						catch (IOException e){
							System.out.println(e.getMessage());
						}
					}
					else if(tmpDirOfMovies[j].endsWith(".jpg")){
					
						try{

							File tmpF = new File(currentD + "\\" + tmpDirOfMovies[j]);
							File jpg = new File(currentD + "\\" + movies[i] + ".jpg");
							tmpF.renameTo(jpg);
							File length = new File(currentD + "\\" + movies[i] + ".jpg");
							byte[] b = new byte[(int) length.length()];
							//tmpF.close();
							FileInputStream fIn = new FileInputStream(currentD + "\\"  + movies[i] + ".jpg");
							fIn.read(b);
							FileOutputStream fOut = new FileOutputStream("c:\\jewel\\"+movies[i]+".jpg");
							fOut.write(b);
							
							fIn.close();
							fOut.close();
						}
						catch(IOException e){
							System.out.println(e.getMessage());	
						}
					}
					else if(tmpDirOfMovies[j].endsWith(".gif")){
						
							File tmpF = new File(currentD + "\\" + tmpDirOfMovies[j]);
							File jpg = new File(currentD + "\\" + movies[i] + ".gif");
							tmpF.renameTo(jpg);

							byte[] b = new byte[(int) tmpF.length()];

							FileInputStream fIn = new FileInputStream(currentD + "\\"  + movies[i] + ".gif");
							fIn.read(b);
							FileOutputStream fOut = new FileOutputStream("c:\\jewel\\"+movies[i]+".gif");
							fOut.write(b);
							
							fIn.close();
							fOut.close();					
				
					}	
				}
			}
		}
	}
	
	public void menu(){
		
		String tmpS = new String();
		
		System.out.println("1 for movies_#-c");
		System.out.println("2 for movies_d-h");
		System.out.println("3 for movies_i-m");
		System.out.println("4 for movies_n-s");
		System.out.println("5 for movies_t-z");
		System.out.println("6 for NewMovies_#-H");
		System.out.println("7 for NewMovies_I-Z");
		System.out.println("You may enter 1 3 4 and that will run m3uCreation on all three");
		try{		
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
			StringTokenizer tokens = new StringTokenizer(stdin.readLine());
		
			while(tokens.hasMoreTokens()){
				
				int option = Integer.parseInt(tokens.nextToken());
				
				if(option == 1){
					setDirectory("\\\\videofactory\\movies_#-c");
				}
				else if(option == 2){
					setDirectory("\\\\videofactory\\movies_d-h");
				}
				else if(option == 3){
					setDirectory("\\\\videofactory\\movies_i-m");
				}
				else if(option == 4){
					setDirectory("\\\\videofactory\\movies_n-s");
				}
				else if(option == 5){
					setDirectory("\\\\videofactory\\movies_t-z");
				}	
				else if(option == 6){
					setDirectory("\\\\videofactory\\NewMovies_#-H\\");
				}
				else if(option == 7){
					setDirectory("\\\\videofactory\\NewMovies_I-Z");
				}						
				createASXFiles();											
			}
		}
		catch(NumberFormatException e){
		
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}		
	}
}