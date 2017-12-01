/***************************************************************

** Michael DePouw

** Date:9 25 02

***************************************************************/



import java.io.*;

public class mainProject{
	
	public static void main(String[] args) throws IOException {
	
		boolean repeatMenu = true;
		int tmpI = 0;
		
 		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(repeatMenu){
			try{
				printOptions();
				
				int option = Integer.parseInt(in.readLine());			

				//table data creation 			
				if(option == 1){
					tableData table = new tableData();
					table.menu();
				}
				//organzing td into seven td(s) in one tr
				else if(option == 2){
					sortTableRows sort = new sortTableRows();				
					sort.menu();
				}
				//all albums creation
				else if(option == 3){
					AllCreator aCreator = new AllCreator();
					aCreator.menu();
				}
				//m3u creation
 	  			else if(option == 4){
					m3uCreator m3u = new m3uCreator();
					m3u.menu();
				}
				//asx creation
				else if(option == 5){
					asxCreator asx = new asxCreator();
					asx.menu();
				}				
				//pls creation
				else if(option == 6){
					plsCreator pls = new plsCreator();
					pls.menu();
				}
				//title creation
				else if(option == 7){
					TitCreator tit = new TitCreator();
					tit.menu();
				}				
				//Image Copying
				else if(option == 8){
					Synchronize sync = new Synchronize();
					sync.menu();
				}				
				//quit option                        
	         else if(option == 9){
					repeatMenu = false;
					break;
				}				
			}
 			catch (NumberFormatException e){
				System.out.println("Error: The input was invalid. ie. you didn't enter a proper integer value");
			}	
		}//end while
	}//main
	public static void printOptions(){
		System.out.println();
		
		System.out.println("HTML Assistors");
			System.out.println("    1 to create html for music, movies and music videos");		
			System.out.println("    2 to sort table rows");
			System.out.println("    3 to create all files");
									
		System.out.println("M3U, ASX, PLS, TIT creators");
		  	System.out.println("    4 to create .m3u files");
			System.out.println("    5 to create .asx files");
			System.out.println("    6 to create .pls files");
			System.out.println("    7 to create .tit files");		
			
		System.out.println("MISC.");
			System.out.println("    8 to synchronize");
		
		System.out.println();
		System.out.println("9 to quit");
		System.out.println();
	}
}
