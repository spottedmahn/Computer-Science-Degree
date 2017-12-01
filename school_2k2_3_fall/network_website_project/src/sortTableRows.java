/***************************************************************

** Michael DePouw

** Date:9 19 02

***************************************************************/
/****************Program Description***************************

** This class was design to organize table data for html. It
** will read in the file and output the organized file. Once it
** sees <!--StartSort--> it will output 1 <tr> 7 strings that begin
** with <td> and then 1 </tr> until it sees <!--EndSort-->.
** At the end of the fill input file there must be the string
** quit or </html>.

**************************************************************/
import java.io.*;
import java.util.*;

public class sortTableRows {

	private String direct;
	private File fIn2;
	public int MAX_TD;

	public sortTableRows(){

	}
	public sortTableRows(File in){
		
		fIn2 = in;
		direct = null;
		MAX_TD = 5;
	}
	public void setDirect(String in){
		direct = in;
	}
	public void setMAX_TD(int in){
		MAX_TD = in;
	}
	public void sortIt(){
		boolean  repeat = true, sorting = true,	printing = true, insertFlag = false;

		String tmpS = new String();
		//temp string not trimmed
		String tmpSNT = new String();
		String insert = new String();

		try{
			//setup file input
			BufferedReader fIn;
			if(fIn2 != null){
				fIn = new BufferedReader(new FileReader(fIn2));
			}
			else{
				fIn = new BufferedReader(new FileReader(new File(direct)));
			}
			//setup file output
			PrintWriter printwriterOS = new PrintWriter(new FileWriter(new File("output_sorted.txt")), true);
			//setup file temp td output
			PrintWriter printwriterTMP = new PrintWriter(new FileWriter(new File("output_tmp.txt")), true);
			//reading tmp file back in
			BufferedReader fTMP = new BufferedReader(new FileReader(new File("output_tmp.txt")));

			while(fIn.ready()){
				try{

					tmpSNT = fIn.readLine();
					tmpS = tmpSNT.trim();
					insertFlag = false;

					if(tmpS.compareTo("<!--StartSort-->") == 0){

						sorting = true;

						while(sorting){

							tmpS = fIn.readLine().trim();
							
							if(tmpS.startsWith("<td")){
								//printing table data that needs to be organized and sorted to a tmp file
								printwriterTMP.println(tmpS);
							}
							else if(tmpS.compareTo("<!--EndSort-->") == 0){
									// need to know when to stop reading for putting td in a tr
									printwriterTMP.println("quit");
									sorting = false;
							}
							else if(tmpS.startsWith("<!--Insert")){

								insert = new String(tmpS);
								insertFlag = true;
							}
						}

						printwriterOS.println("<!--StartSort-->");
						if(insertFlag){
							printwriterOS.println(insert);
						}
						printwriterOS.println("<tr>");
						printing = true;

				outer:
						while(printing){

							for(int i=1;i <= MAX_TD;i++){
								
								tmpS = fTMP.readLine();
								
								if(tmpS.compareTo("quit") == 0){
									printwriterOS.println("</tr>");
									printwriterOS.println("<!--EndSort-->");
									printing = false;
									break outer;
								}
								else {
									printwriterOS.println(tmpS);
									}
								if(i==MAX_TD){
									printwriterOS.println("</tr>");
									printwriterOS.println("<tr>");
								}
							}
						}
					}//end if(input compareTo <!--startSort-->)
					else{
						printwriterOS.println(tmpSNT);
					}
				}
				catch(EOFException e){
					System.out.println(e.getMessage());
				}
			} //endwhile

		printwriterOS.close();
		printwriterTMP.close();

		//copying over original file with sorted file
		copyOver();
		}//end try
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}//end method sortIt

	//copying sorted file over orginal file
	public void copyOver() throws IOException{

		BufferedReader fIn = new BufferedReader(new FileReader(new File("output_sorted.txt")));

		PrintWriter printWriter;
		
		if(fIn2 != null){
			//System.out.println("fIn2 != null");
			printWriter = new PrintWriter(new FileWriter(fIn2), true);
		}
		else{
			printWriter = new PrintWriter(new FileWriter(new File(direct)), true);
		}

		while(fIn.ready()){
					//System.out.println("
					printWriter.println(fIn.readLine());
		}
		printWriter.close();
	}

	public void menu(){
	 	int tmpI = 0;
		String tmpS = new String("");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("1 for albums_#-c.htm");
		System.out.println("2 for albums_d-h.htm");
		System.out.println("3 for albums_i-m.htm");
		System.out.println("4 for albums_n-s.htm");
		System.out.println("5 for albums_t-z.htm");
		System.out.println("6 for albums_new.htm");
		System.out.println("7 for custom_cdz.htm");
		System.out.println("8 for instrumentals.htm");
		System.out.println("9 for your own path");
		System.out.println("10 for bookOnCD.html");
		System.out.println("11 for movies_#-c.htm");
		System.out.println("12 for movies_d-h.htm");
		System.out.println("13 for movies_i-m.htm");
		System.out.println("14 for movies_n-s.htm");
		System.out.println("15 for movies_t-z.htm");
		System.out.println("16 for movies_new.htm");
		System.out.println("You may enter 1 3 4 and that will run Sort Table Data on all three");
		System.out.println();
		try{
			tmpS = in.readLine();
			StringTokenizer token = new StringTokenizer(tmpS);
			while(token.hasMoreTokens()){
				tmpI = Integer.parseInt(token.nextToken());
				if(tmpI == 1){
					direct = new String("\\\\musicfactory\\network_website\\albums_#-c.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_#-c_car.htm");
					sortIt();
				}
				else if(tmpI == 2){
					direct = new String("\\\\musicfactory\\network_website\\albums_d-h.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_d-h_car.htm");
					sortIt();
				}
				else if(tmpI == 3){
					direct = new String("\\\\musicfactory\\network_website\\albums_i-m.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_i-m_car.htm");
					sortIt();
				}
				else if(tmpI == 4){
					direct = new String("\\\\musicfactory\\network_website\\albums_n-s.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_n-s_car.htm");
					sortIt();
				}
				else if(tmpI == 5){
					direct = new String("\\\\musicfactory\\network_website\\albums_t-z.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_t-z_car.htm");
					sortIt();
				}
				else if(tmpI == 6){
					direct = new String("\\\\musicfactory\\network_website\\albums_new.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\albums_new_car.htm");
					sortIt();
				}
				else if(tmpI == 7){
					direct = new String("\\\\musicfactory\\network_website\\custom_cdz.htm");
					System.out.println("tmpI == 7");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\custom_cdz_car.htm");
					sortIt();
				}
				else if(tmpI == 8){
					direct = new String("\\\\musicfactory\\network_website\\instrumentals.htm");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\instrumentals_car.htm");
					sortIt();
				}
				else if(tmpI == 9){
					System.out.println("Please enter an absolute path. example c:\\my_music");
					System.out.println();
					String titS = new String();
					titS = in.readLine();
					direct = new String(titS);
					MAX_TD = 5;
					sortIt();
				}
				else if(tmpI == 10){
					direct = new String("\\\\musicfactory\\network_website\\booksOnCD.html");
					MAX_TD = 5;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\booksOnCD_car.html");
					sortIt();
				}	
				else if(tmpI == 11){
					direct = new String("\\\\musicfactory\\network_website\\movies_#-c.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_#-c_car.htm");
					sortIt();
				}
				else if(tmpI == 12){
					direct = new String("\\\\musicfactory\\network_website\\movies_d-h.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_d-h_car.htm");
					sortIt();
				}
				else if(tmpI == 13){
					direct = new String("\\\\musicfactory\\network_website\\movies_i-m.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_i-m_car.htm");
					sortIt();
				}
				else if(tmpI == 14){
					direct = new String("\\\\musicfactory\\network_website\\movies_n-s.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_n-s_car.htm");
					sortIt();
				}															
				else if(tmpI == 15){
					direct = new String("\\\\musicfactory\\network_website\\movies_t-z.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_t-z_car.htm");
					sortIt();
				}				
				else if(tmpI == 16){
					direct = new String("\\\\musicfactory\\network_website\\movies_new.htm");
					MAX_TD = 4;
					sortIt();
					direct = new String("\\\\musicfactory\\network_website\\movies_new_car.htm");
					sortIt();
				}			
			}//end while
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
