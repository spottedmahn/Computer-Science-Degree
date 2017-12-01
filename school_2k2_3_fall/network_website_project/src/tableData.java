/***************************************************************
 ** Michael DePouw
 ** Date:9 19 02
 ***************************************************************/

/****************Class Description***************************
 ** This class is for my intranet site.  It will generate the
 ** html code for a single ablum.
 **************************************************************/

import java.io.*;
import java.util.*;

public class tableData {

	private String inputDirectory;

	private boolean absolutePath = false;

	public tableData() {

	}

	public void setOwnPathFlag() {
		absolutePath = true;
	}

	public void setDirect(String in) {
		inputDirectory = in;
	}

	public void musicTDC() {
		try {

			String directoryTemp, titleTemp, tmpS;

			menuForMusicTDC();

			TitCreator titleCreater = new TitCreator();

			titleCreater.setDirect(inputDirectory);
			titleCreater.setAorM("AUTO");

			titleCreater.create();

			if (titleCreater.ContainsHypen()) {

				createDirectoryList dl = new createDirectoryList(inputDirectory);
				dl.create();

				//Reading in the current count
				BufferedReader numFileReader = new BufferedReader(new FileReader("numFile.txt"));
				int count = Integer.parseInt(numFileReader.readLine());
				numFileReader.close();

				//filereader for input
				BufferedReader dirTitInput = new BufferedReader(new FileReader("input_for_directory_and_title.txt"));

				// Output.txt file setup
				PrintWriter printWriter = new PrintWriter(new FileWriter(new File("output_table_data.txt")), true);

				while (dirTitInput.ready()) {

					// Reading in directory
					directoryTemp = dirTitInput.readLine();

					// Reading in title
					titleTemp = dirTitInput.readLine();

					//absolutePath
					if (absolutePath) {

						System.out.println("Input final destination directory (the location where the music will be stored)");
						BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
						String destination = stdin.readLine();

						printWriter.println("<td><a href=\"" + destination + "\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"" + destination + "\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
					}
					//collaborations
					else if (inputDirectory.compareTo("\\\\musicfactory\\collaborations\\new") == 0) {
						printWriter.println("<td><a href=\"\\\\Musicfactory\\collaborations" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\collaborations\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
					}
					//dj freckles
					else if (inputDirectory.compareTo("\\\\mikescomputer\\dj_freckles\\new") == 0) {
						printWriter.println("<td><a href=\"\\\\mikescomputer\\dj_freckles\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\mikescomputer\\dj_freckles\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
					}
					//instrumentals
					else if (inputDirectory.compareTo("\\\\musicfactory\\instrumentals\\new") == 0) {
						printWriter.println("<td><a href=\"\\\\Musicfactory\\instrumentals\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\instrumentals\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
					}
					//albums #-z
					else if (inputDirectory.compareTo("\\\\musicfactory\\new_music") == 0) {

						if (directoryTemp.charAt(0) == '0' || directoryTemp.charAt(0) == '1' || directoryTemp.charAt(0) == '2' || directoryTemp.charAt(0) == '3' || directoryTemp.charAt(0) == '4' || directoryTemp.charAt(0) == '5' || directoryTemp.charAt(0) == '6' || directoryTemp.charAt(0) == '7' || directoryTemp.charAt(0) == '8' || directoryTemp.charAt(0) == '9' || directoryTemp.charAt(0) == 'a' || directoryTemp.charAt(0) == 'A' || directoryTemp.charAt(0) == 'b' || directoryTemp.charAt(0) == 'B' || directoryTemp.charAt(0) == 'c' || directoryTemp.charAt(0) == 'C') {
							printWriter.println("<td><a href=\"\\\\Musicfactory\\albums_#-c\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\albums_#-c\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
						}
						else if (directoryTemp.charAt(0) == 'd' || directoryTemp.charAt(0) == 'D' || directoryTemp.charAt(0) == 'e' || directoryTemp.charAt(0) == 'E' || directoryTemp.charAt(0) == 'f' || directoryTemp.charAt(0) == 'F' || directoryTemp.charAt(0) == 'g' || directoryTemp.charAt(0) == 'G' || directoryTemp.charAt(0) == 'h' || directoryTemp.charAt(0) == 'H') {
							printWriter.println("<td><a href=\"\\\\Musicfactory\\albums_d-h\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\albums_d-h\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
						}
						else if (directoryTemp.charAt(0) == 'i' || directoryTemp.charAt(0) == 'I' || directoryTemp.charAt(0) == 'j' || directoryTemp.charAt(0) == 'J' || directoryTemp.charAt(0) == 'k' || directoryTemp.charAt(0) == 'K' || directoryTemp.charAt(0) == 'l' || directoryTemp.charAt(0) == 'L' || directoryTemp.charAt(0) == 'm' || directoryTemp.charAt(0) == 'M') {
							printWriter.println("<td><a href=\"\\\\Musicfactory\\albums_i-m\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\albums_i-m\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
						}
						else if (directoryTemp.charAt(0) == 'n' || directoryTemp.charAt(0) == 'N' || directoryTemp.charAt(0) == 'o' || directoryTemp.charAt(0) == 'O' || directoryTemp.charAt(0) == 'p' || directoryTemp.charAt(0) == 'P' || directoryTemp.charAt(0) == 'q' || directoryTemp.charAt(0) == 'Q' || directoryTemp.charAt(0) == 'r' || directoryTemp.charAt(0) == 'R' || directoryTemp.charAt(0) == 's' || directoryTemp.charAt(0) == 'S') {
							printWriter.println("<td><a href=\"\\\\Musicfactory\\albums_n-s\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\albums_n-s\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
						}
						else if (directoryTemp.charAt(0) == 't' || directoryTemp.charAt(0) == 'T' || directoryTemp.charAt(0) == 'u' || directoryTemp.charAt(0) == 'U' || directoryTemp.charAt(0) == 'v' || directoryTemp.charAt(0) == 'V' || directoryTemp.charAt(0) == 'w' || directoryTemp.charAt(0) == 'W' || directoryTemp.charAt(0) == 'x' || directoryTemp.charAt(0) == 'X' || directoryTemp.charAt(0) == 'y' || directoryTemp.charAt(0) == 'Y' || directoryTemp.charAt(0) == 'z' || directoryTemp.charAt(0) == 'Z') {
							printWriter.println("<td><a href=\"\\\\Musicfactory\\albums_t-z\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\Musicfactory\\albums_t-z\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");
						}
					}
					else if(inputDirectory.compareTo("\\\\mikescomputer\\booksOnCD\\new") == 0){
						printWriter.println("<td><a href=\"\\\\mikescomputer\\booksOnCD\\" + directoryTemp + "\" onmouseover=\"window.status='" + titleTemp + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + directoryTemp + ".jpg\" height=\"138\" width=\"138\" alt=\"" + titleTemp + "\" border=\"0\"></a><br><a href=\"\\\\mikescomputer\\booksOnCD\\" + directoryTemp + "\\" + directoryTemp + ".m3u\" onMouseOver=\"if(document.images) document.play" + count + ".src='images/play_138_on.jpg'; window.status='Click Here To Add " + titleTemp + " To Winamp';return true\" onMouseOut=\"if(document.images) document.play" + count + ".src='images/play_138_off.jpg'; window.status='';return true\"><img src=\"images/play_138_off.jpg\" border=\"0\" alt=\"Click Here To Add " + titleTemp + " To Winamp\" name=\"play" + count + "\"></a><br><img src=\"images/new_album.jpg\"></td>");					
					}
					count++;
				} // end while

				// updating numFile.txt
				PrintWriter printWriterCount = new PrintWriter(new FileWriter("numFile.txt"), true);
				printWriterCount.println(count);
				printWriterCount.close();

				//inserting
				BufferedReader fIn = new BufferedReader(new FileReader("output_table_data.txt"));
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
				//albums #-z
				if (inputDirectory.compareTo("\\\\musicfactory\\new_music") == 0) {
	
					while (fIn.ready()) {
	
						tmpS = fIn.readLine();
						
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\");
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
	
						String tmpS2 = tokens.nextToken();
						tokens = new StringTokenizer(tmpS2, "-\"");
						
						StringTokenizer tokensSD = new StringTokenizer(tmpS, "\"");
						tokensSD.nextToken();
						
						if(tokensSD.nextToken().endsWith("_SD")){
							System.out.println(tokens.nextToken() +" "+ tokens.nextToken() +" SD");
						}
						else{
							System.out.println(tokens.nextToken() +" "+ tokens.nextToken());
						}
						System.out.println("New? 1 for yes 2 for no");
	
						int tmpI = Integer.parseInt(stdin.readLine());
	
						if (tmpI == 1) {
							
							insertMusic(tmpS, false, true, false, false, false, false);
							
							//sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "new", false, false, false, false));
							//sortRows.sortIt();
							
							System.out.println("Car? 1 for yes 2 for No");
	
							tmpI = Integer.parseInt(stdin.readLine());
	
							if (tmpI == 1) {
								insertMusic(tmpS, false, true, true, false, false, false);
								//sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "new", true, false, false, false));
								//sortRowsCar.sortIt();							
							}
						}
						else {
							
							insertMusic(tmpS, true, false, false, false, false, false);
	
							//sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "old", false, false, false, false));
							//sortRows.sortIt();												
	
							System.out.println("Car? 1 for yes 2 for No");
	
							tmpI = Integer.parseInt(stdin.readLine());
	
							if (tmpI == 1) {
								insertMusic(tmpS, true, false, true, false, false, false);
								
								//sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "old", true, false, false, false));
								//sortRowsCar.sortIt();							
							}
						}
					}
				}
	
				//collaborations
				if (inputDirectory.compareTo("\\\\musicfactory\\collaborations\\new") == 0) {
	
					while (fIn.ready()) {
						
						tmpS = fIn.readLine();
						
						insertMusic(tmpS, false, false, false, true, false, false);
	
						sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, true, false, false));
						sortRows.sortIt();					
	
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\");
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
	
						String tmpS2 = tokens.nextToken();
						tokens = new StringTokenizer(tmpS2, "-\"");
						System.out.println(tokens.nextToken() +" "+ tokens.nextToken());
	
						System.out.println("Car? 1 for yes 2 for No");
	
						int tmpI = Integer.parseInt(stdin.readLine());
	
						if (tmpI == 1) {
							insertMusic(tmpS, false, false, true, true, false, false);
							
							sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "notAnAlbum", true, true, false, false));
							sortRowsCar.sortIt();							
						}
					}
	
				}
				//dj freckles
				else if (inputDirectory.compareTo("\\\\mikescomputer\\dj_freckles\\new") == 0) {
	
					while (fIn.ready()) {
							
						tmpS = fIn.readLine();
	
						insertMusic(tmpS, false, false, false, true, false, false);
						
						sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, true, false, false));
						sortRows.sortIt();
						
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\");
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
	
						String tmpS2 = tokens.nextToken();
						tokens = new StringTokenizer(tmpS2, "-\"");
						
						if(tmpS.endsWith("_SD")){
							System.out.println(tokens.nextToken() +" "+ tokens.nextToken() +" SD");
						}
						else{
							System.out.println(tokens.nextToken() +" "+ tokens.nextToken());
						}
	
						System.out.println("Car? 1 for yes 2 for No");
	
						int tmpI = Integer.parseInt(stdin.readLine());
	
						if (tmpI == 1) {
							insertMusic(tmpS, false, false, true, true, false, false);
							
							sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, true, false, false));
							sortRowsCar.sortIt();						
						}
					}
				}
				//instrumentals
				else if (inputDirectory.compareTo("\\\\musicfactory\\instrumentals\\new") == 0) {
	
					while (fIn.ready()) {
	
						tmpS = fIn.readLine();
						
						insertMusic(tmpS, false, false, false, false, true, false);
	
						sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, false, true, false));
						sortRows.sortIt();
												
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\");
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
	
						String tmpS2 = tokens.nextToken();
						tokens = new StringTokenizer(tmpS2, "-\"");
						System.out.println(tokens.nextToken() +" "+ tokens.nextToken());
	
						System.out.println("Car? 1 for yes 2 for No");
	
						int tmpI = Integer.parseInt(stdin.readLine());
	
						if (tmpI == 1) {
							insertMusic(tmpS, false, false, true, false, true, false);
							
							sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, false, true, false));
							sortRowsCar.sortIt();						
						}
					}
	
				}
				//books on cd
				else if (inputDirectory.compareTo("\\\\mikescomputer\\booksOnCD\\new") == 0) {
	
					while (fIn.ready()) {
	
						tmpS = fIn.readLine();
						
						insertMusic(tmpS, false, false, false, false, false, true);
						
						sortTableRows sortRows = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, false, false, true));
						sortRows.sortIt();
												
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\");
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
	
						String tmpS2 = tokens.nextToken();
						tokens = new StringTokenizer(tmpS2, "-\"");
						System.out.println(tokens.nextToken() +" "+ tokens.nextToken());
	
						System.out.println("Car? 1 for yes 2 for No");
	
						int tmpI = Integer.parseInt(stdin.readLine());
	
						if (tmpI == 1) {
							insertMusic(tmpS, false, false, true, false, false, true);
			
							sortTableRows sortRowsCar = new sortTableRows(determineFile(tmpS, "notAnAlbum", false, false, false, true));
							sortRowsCar.sortIt();						
						}
					}
	
				}			
			}
		}
		catch (IOException e) {
			System.out.println("Error Message = " + e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertMusic(String sIn, boolean album, boolean newAlbum, boolean car, boolean customCDZ, boolean instrumentals, boolean booksOnCD) {
		
		try {
			
			
			
			String tmpS = new String(), tmpSUpper = new String(), sInUpper = sIn.toUpperCase(), insert;

			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader fIn;

			if (newAlbum) {
				fIn = new BufferedReader(new FileReader(determineFile(sIn, "new", car, customCDZ, instrumentals, booksOnCD)));
			}
			else if (album) {
				fIn = new BufferedReader(new FileReader(determineFile(sIn, "old", car, customCDZ, instrumentals, booksOnCD)));
			}
			else {
				fIn = new BufferedReader(new FileReader(determineFile(sIn, "notAnAlbum", car, customCDZ, instrumentals, booksOnCD)));
			}
			
			File tempFile = new File("tempFile.txt");
			
			PrintWriter pwriter = new PrintWriter(new FileWriter(tempFile), true);
			boolean doneFindingInsertEntry = false;

			insert = determineInsertLocation(sIn, album, newAlbum, customCDZ, instrumentals, booksOnCD);
			//System.out.println("Insert Location == "+insert);
			while (!doneFindingInsertEntry) {

				tmpS = fIn.readLine();

				pwriter.println(tmpS);

				if (tmpS.compareTo(insert) == 0) {

					doneFindingInsertEntry = true;
				}
			}
			
			//System.out.println("done file location: "+tmpS);
			boolean doneInserting = false;

			while (!doneInserting) {

				tmpS = fIn.readLine();
				//System.out.println(tmpS +"!doneInserting");
				
				if(tmpS.length() == 0){
					pwriter.println(tmpS);
					continue;					
				}
				else if (tmpS.compareTo("<!--EndSort-->") == 0) {

					pwriter.println("<tr>");
					pwriter.println(sIn);
					pwriter.println("</tr>");
					pwriter.println(tmpS);

					//doneInserting = true;
					break;
				}
				else if((tmpS.compareTo("<tr>") == 0) || (tmpS.compareTo("</tr>") == 0)){
					
					pwriter.println(tmpS);
					continue;
				}
				tmpSUpper = tmpS.toUpperCase();
				boolean doneComparingStrings = false;
				int i = 0;

				while (!doneComparingStrings) {

					if (tmpSUpper.charAt(i) > sInUpper.charAt(i)) {

						doneInserting = true;
						doneComparingStrings = true;

						pwriter.println(sIn);
					}
					else if (tmpSUpper.charAt(i) < sInUpper.charAt(i)) {

						doneComparingStrings = true;
					}

					i++;
				}

				pwriter.println(tmpS);
			}
			while (fIn.ready()) {

				pwriter.println(fIn.readLine());
			}
			pwriter.close();
			//System.out.println("Insertion complete, copying over now");
			if (newAlbum) {
				copyOver(determineFile(sIn, "new", car, customCDZ, instrumentals, booksOnCD), tempFile);
			}
			else if (album) {
				copyOver(determineFile(sIn, "old", car, customCDZ, instrumentals, booksOnCD), tempFile);
			}
			else {
				copyOver(determineFile(sIn, "notAnAlbum", car, customCDZ, instrumentals, booksOnCD), tempFile);
			}			
			
		}
		catch (IOException e) {

			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {

			System.out.println(e.getMessage());
		}

	}

	public String determineInsertLocation(String sIn, boolean album, boolean newAlbum, boolean customCDZ, boolean instrumentals, boolean booksOnCD) {

		try{	
			if (customCDZ) {
	
				StringTokenizer tokens = new StringTokenizer(sIn, "\\");
				tokens.nextToken();
				tokens.nextToken();
				//tokens.nextToken();
	
				String tmpS = tokens.nextToken();
				//System.out.println(tmpS +" customCDZ");
				if (tmpS.compareTo("dj_freckles") == 0) {
	
					System.out.println("1 for Rap, 2 for Greatest Hits, 3 for Which Joint Is Hotter, 4 for Da Best Of, 5 for Edition Of, 6 for R&B");
					BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
					
					int tmpI = Integer.parseInt(stdin.readLine());
					
					if(tmpI == 1){
						return ("<!--Insert_dj_freckles_rap-->");
					}
					else if(tmpI == 2){
						return ("<!--Insert_dj_freckles_gh-->");
					}
					else if(tmpI == 3){
						return ("<!--Insert_dj_freckles_which_joint_is_hotter-->");	
					}
					else if(tmpI == 4){
						return ("<!--Insert_dj_freckles_da_best_of-->");
					}
					else if(tmpI == 5){
						return ("<!--Insert_dj_freckles_edition_of-->");
					}
					else if(tmpI == 6){
						return ("<!--Insert_dj_freckles_r&b-->");
					}
					else {
						System.out.println("Invalid #");
					}
	
				}
				else if (tmpS.compareTo("tyron") == 0) {
					return ("<!--Insert_tyron-->");
				}
				else if (tmpS.compareTo("collaborations") == 0) {
					return ("<!--Insert_collaborations-->");
				}
	
			}
			else if (instrumentals) {
				return ("<!--Insert_instrumentals-->");
			}
			else if(booksOnCD){
				return ("<!--Insert_booksOnCD-->");			
			}
			else if (album || newAlbum) {
	
				StringTokenizer tokens = new StringTokenizer(sIn, "\\");
				tokens.nextToken();
				tokens.nextToken();
				tokens.nextToken();
	
				String tmpS = tokens.nextToken();
				//System.out.println(tmpS);
				tokens = new StringTokenizer(tmpS, "-");
				String dst = tokens.nextToken();
	
				//System.out.println(dst);
	
				if (dst.charAt(0) == '0' || dst.charAt(0) == '1' || dst.charAt(0) == '2' || dst.charAt(0) == '3' || dst.charAt(0) == '4' || dst.charAt(0) == '5' || dst.charAt(0) == '6' || dst.charAt(0) == '7' || dst.charAt(0) == '8' || dst.charAt(0) == '9') {
					return ("<!--Insert_#-->");
				}
				else if (dst.charAt(0) == 'a' || dst.charAt(0) == 'A') {
					return ("<!--Insert_a-->");
				}
				else if (dst.charAt(0) == 'b' || dst.charAt(0) == 'B') {
					return ("<!--Insert_b-->");
				}
				else if (dst.charAt(0) == 'c' || dst.charAt(0) == 'C') {
					return ("<!--Insert_c-->");
				}
				else if (dst.charAt(0) == 'd' || dst.charAt(0) == 'D') {
					return ("<!--Insert_d-->");
				}
				else if (dst.charAt(0) == 'e' || dst.charAt(0) == 'E') {
					return ("<!--Insert_e-->");
				}
				else if (dst.charAt(0) == 'f' || dst.charAt(0) == 'F') {
					return ("<!--Insert_f-->");
				}
				else if (dst.charAt(0) == 'g' || dst.charAt(0) == 'G') {
					return ("<!--Insert_g-->");
				}
				else if (dst.charAt(0) == 'h' || dst.charAt(0) == 'H') {
					return ("<!--Insert_h-->");
				}
				else if (dst.charAt(0) == 'i' || dst.charAt(0) == 'I') {
					return ("<!--Insert_i-->");
				}
				else if (dst.charAt(0) == 'j' || dst.charAt(0) == 'J') {
					return ("<!--Insert_j-->");
				}
				else if (dst.charAt(0) == 'k' || dst.charAt(0) == 'K') {
					return ("<!--Insert_k-->");
				}
				else if (dst.charAt(0) == 'l' || dst.charAt(0) == 'L') {
					return ("<!--Insert_l-->");
				}
				else if (dst.charAt(0) == 'm' || dst.charAt(0) == 'M') {
					return ("<!--Insert_m-->");
				}
				else if (dst.charAt(0) == 'n' || dst.charAt(0) == 'N') {
					return ("<!--Insert_n-->");
				}
				else if (dst.charAt(0) == 'o' || dst.charAt(0) == 'O') {
					return ("<!--Insert_o-->");
				}
				else if (dst.charAt(0) == 'p' || dst.charAt(0) == 'P') {
					return ("<!--Insert_p-->");
				}
				else if (dst.charAt(0) == 'q' || dst.charAt(0) == 'Q') {
					return ("<!--Insert_q-->");
				}
				else if (dst.charAt(0) == 'r' || dst.charAt(0) == 'R') {
					return ("<!--Insert_r-->");
				}
				else if (dst.charAt(0) == 's' || dst.charAt(0) == 'S') {
					return ("<!--Insert_s-->");
				}
				else if (dst.charAt(0) == 't' || dst.charAt(0) == 'T') {
					return ("<!--Insert_t-->");
				}
				else if (dst.charAt(0) == 'u' || dst.charAt(0) == 'U') {
					return ("<!--Insert_u-->");
				}
				else if (dst.charAt(0) == 'v' || dst.charAt(0) == 'V') {
					return ("<!--Insert_v-->");
				}
				else if (dst.charAt(0) == 'w' || dst.charAt(0) == 'W') {
					return ("<!--Insert_w-->");
				}
				else if (dst.charAt(0) == 'x' || dst.charAt(0) == 'X') {
					return ("<!--Insert_x-->");
				}
				else if (dst.charAt(0) == 'y' || dst.charAt(0) == 'Y') {
					return ("<!--Insert_y-->");
				}
				else if (dst.charAt(0) == 'z' || dst.charAt(0) == 'Z') {
					return ("<!--Insert_z-->");
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public File determineFile(String sIn, String newORold, boolean car, boolean customCDZ, boolean instrumentals, boolean booksOnCD) {
		
		//System.out.println("newORold == "+newORold+" car == "+car+" customCDZ == "+customCDZ+" instrumentals == "+instrumentals);
		
		if ( (newORold.compareTo("new") == 0) && car) {
			//System.out.println("determine file == albums_new_car.htm");
			return (new File("\\\\musicfactory\\network_website\\albums_new_car.htm"));
		}
		else if ( (newORold.compareTo("new") == 0) && !car) {
			//System.out.println("determine file ==  albums_new.htm");
			return (new File("\\\\musicfactory\\network_website\\albums_new.htm"));
		}
		else if ((newORold.compareTo("old") == 0) && (car)) {

			StringTokenizer tokens = new StringTokenizer(sIn, "\\");
			tokens.nextToken();
			tokens.nextToken();
			//tokens.nextToken();

			String tmpS = tokens.nextToken();

			//System.out.println(tmpS);

			if (tmpS.compareTo("albums_#-c") == 0) {
				//System.out.println("returning albums_#-c_car");
				return (new File("\\\\musicfactory\\network_website\\albums_#-c_car.htm"));
			}
			else if (tmpS.compareTo("albums_d-h") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_d-h_car.htm"));
			}
			else if (tmpS.compareTo("albums_i-m") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_i-m_car.htm"));
			}
			else if (tmpS.compareTo("albums_n-s") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_n-s_car.htm"));
			}
			else if (tmpS.compareTo("albums_t-z") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_t-z_car.htm"));
			}
			else if (customCDZ) {
				return (new File("\\\\musicfactory\\network_website\\custom_cdz_car.htm"));
			}
			else if (instrumentals) {
				return (new File("\\\\musicfactory\\network_website\\instrumentals_car.htm"));
			}
		}
		else if ((newORold.compareTo("old") == 0) && (!car)) {

			StringTokenizer tokens = new StringTokenizer(sIn, "\\");
			tokens.nextToken();
			tokens.nextToken();
			//tokens.nextToken();

			String tmpS = tokens.nextToken();
	
			//System.out.println(tmpS);
			
			if (tmpS.compareTo("albums_#-c") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_#-c.htm"));
			}
			else if (tmpS.compareTo("albums_d-h") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_d-h.htm"));
			}
			else if (tmpS.compareTo("albums_i-m") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_i-m.htm"));
			}
			else if (tmpS.compareTo("albums_n-s") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_n-s.htm"));
			}
			else if (tmpS.compareTo("albums_t-z") == 0) {
				return (new File("\\\\musicfactory\\network_website\\albums_t-z.htm"));
			}
		}
		else if (customCDZ && car) {
			return (new File("\\\\musicfactory\\network_website\\custom_cdz_car.htm"));
		}
		else if (customCDZ && !car) {
			return (new File("\\\\musicfactory\\network_website\\custom_cdz.htm"));
		}
		else if (instrumentals && car) {
			return (new File("\\\\musicfactory\\network_website\\instrumentals_car.htm"));
		}
		else if (instrumentals && !car) {
			return (new File("\\\\musicfactory\\network_website\\instrumentals.htm"));
		}
		else if(booksOnCD && car){
			return (new File("\\\\musicfactory\\network_website\\booksOnCD.html"));
		}
		else if(booksOnCD && !car){
			return (new File("\\\\musicfactory\\network_website\\booksOnCD_car.html"));
		}
		return null;
	}
	
	public void copyOver(File over, File in){
		
		try{
			BufferedReader fIn = new BufferedReader(new FileReader(in));
			PrintWriter fOut = new PrintWriter(new FileWriter(over), true);
			
			while(fIn.ready()){
				
				fOut.println(fIn.readLine());
			}
			fOut.close();
			fIn.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	//menu for Music Table Data Creation
	public void menuForMusicTDC() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println();
			System.out.println("1 for new music");
			System.out.println("2 for new dj freckles");
			System.out.println("3 for new collaborations");
			System.out.println("4 for new instrumentals");
			System.out.println("5 for your own path");
			System.out.println("6 for books on cd");
			System.out.println();
			int tmpI = Integer.parseInt(stdin.readLine());
			if (tmpI == 1) {
				inputDirectory = "\\\\musicfactory\\new_music";
			}
			else if (tmpI == 2) {
				inputDirectory = "\\\\mikescomputer\\dj_freckles\\new";
			}
			else if (tmpI == 3) {
				inputDirectory = "\\\\musicfactory\\collaborations\\new";
			}
			else if (tmpI == 4) {
				inputDirectory = "\\\\musicfactory\\instrumentals\\new";
			}
			else if (tmpI == 5) {
				setOwnPathFlag();
				printOwnPathMessage();
				inputDirectory = stdin.readLine();
			}
			else if (tmpI == 6) {
				inputDirectory = "\\\\mikescomputer\\booksOnCD\\new";
			}			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}

	}

	//movie table data creation
	public void movieTDC() {

		menuForMovieTDC();
		try {

			File moviesIn = new File(inputDirectory);
			String[] movies = moviesIn.list();

			PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_td_movies.txt")), true);

			for (int i = 0; i < movies.length; i++) {
				//if movie starts with a digit or a-c or A-C
				if ( ( (movies[i].charAt(0) > 47) && (movies[i].charAt(0) < 58)) || ((movies[i].charAt(0) > 96) && (movies[i].charAt(0) < 100)) || ((movies[i].charAt(0) > 64) && (movies[i].charAt(0) < 68))) {

					pwriter.println("<td><a href=\"\\\\videofactory\\movies_#-c\\" + movies[i] + "\\" + movies[i] + ".asx" + "\" onmouseover=\"window.status='" + movies[i] + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + movies[i] + ".jpg\" height=\"242\" width=\"170\" alt=\"" + movies[i] + "\" border=\"0\"></a><br><img src=\"images/new_movie.jpg\"></td>");
				}
				else if (movies[i].charAt(0) == 'd' || movies[i].charAt(0) == 'D' || movies[i].charAt(0) == 'e' || movies[i].charAt(0) == 'E' || movies[i].charAt(0) == 'f' || movies[i].charAt(0) == 'F' || movies[i].charAt(0) == 'g' || movies[i].charAt(0) == 'G' || movies[i].charAt(0) == 'h' || movies[i].charAt(0) == 'H') {

					pwriter.println("<td><a href=\"\\\\videofactory\\movies_d-h\\" + movies[i] + "\\" + movies[i] + ".asx" + "\" onmouseover=\"window.status='" + movies[i] + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + movies[i] + ".jpg\" height=\"242\" width=\"170\" alt=\"" + movies[i] + "\" border=\"0\"></a><br><img src=\"images/new_movie.jpg\"></td>");
				}
				else if (movies[i].charAt(0) == 'i' || movies[i].charAt(0) == 'I' || movies[i].charAt(0) == 'j' || movies[i].charAt(0) == 'J' || movies[i].charAt(0) == 'k' || movies[i].charAt(0) == 'K' || movies[i].charAt(0) == 'l' || movies[i].charAt(0) == 'L' || movies[i].charAt(0) == 'm' || movies[i].charAt(0) == 'M') {

					pwriter.println("<td><a href=\"\\\\videofactory\\movies_i-m\\" + movies[i] + "\\" + movies[i] + ".asx" + "\" onmouseover=\"window.status='" + movies[i] + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + movies[i] + ".jpg\" height=\"242\" width=\"170\" alt=\"" + movies[i] + "\" border=\"0\"></a><br><img src=\"images/new_movie.jpg\"></td>");
				}
				else if (movies[i].charAt(0) == 'n' || movies[i].charAt(0) == 'N' || movies[i].charAt(0) == 'o' || movies[i].charAt(0) == 'O' || movies[i].charAt(0) == 'p' || movies[i].charAt(0) == 'P' || movies[i].charAt(0) == 'q' || movies[i].charAt(0) == 'Q' || movies[i].charAt(0) == 'r' || movies[i].charAt(0) == 'R' || movies[i].charAt(0) == 's' || movies[i].charAt(0) == 'S') {

					pwriter.println("<td><a href=\"\\\\videofactory\\movies_n-s\\" + movies[i] + "\\" + movies[i] + ".asx" + "\" onmouseover=\"window.status='" + movies[i] + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + movies[i] + ".jpg\" height=\"242\" width=\"170\" alt=\"" + movies[i] + "\" border=\"0\"></a><br><img src=\"images/new_movie.jpg\"></td>");
				}
				else if (movies[i].charAt(0) == 't' || movies[i].charAt(0) == 'T' || movies[i].charAt(0) == 'u' || movies[i].charAt(0) == 'U' || movies[i].charAt(0) == 'v' || movies[i].charAt(0) == 'V' || movies[i].charAt(0) == 'w' || movies[i].charAt(0) == 'W' || movies[i].charAt(0) == 'x' || movies[i].charAt(0) == 'X' || movies[i].charAt(0) == 'y' || movies[i].charAt(0) == 'Y' || movies[i].charAt(0) == 'z' || movies[i].charAt(0) == 'Z') {

					pwriter.println("<td><a href=\"\\\\videofactory\\movies_t-z\\" + movies[i] + "\\" + movies[i] + ".asx" + "\" onmouseover=\"window.status='" + movies[i] + "';return true\" onmouseout=\"window.status='';return true\"><img src=\"C:/jewel/" + movies[i] + ".jpg\" height=\"242\" width=\"170\" alt=\"" + movies[i] + "\" border=\"0\"></a><br><img src=\"images/new_movie.jpg\"></td>");
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void menuForMovieTDC() {

		try {
			System.out.println("1 for new_movies");
			System.out.println("2 for own location");

			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			int option = Integer.parseInt(stdin.readLine());

			if (option == 1) {
				inputDirectory = "\\\\videofactory\\new_movies";
			}
			else if (option == 2) {
				printOwnPathMessage();
				inputDirectory = stdin.readLine();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
	}

	public void musicVideoTDC() {

		inputDirectory = "\\\\Mikescomputer\\Music_Videos_need_to_be_posted";
		try {
			PrintWriter pwriter = new PrintWriter(new FileWriter(new File("output_MusicVideosTableRows.txt")));

			File directoryIn = new File(inputDirectory);
			String[] folders = directoryIn.list();

			for (int i = 0; i < folders.length; i++) {

				File tmpF = new File(inputDirectory + "\\" + folders[i]);

				if (!tmpF.isFile()) {

					BufferedReader breader = new BufferedReader(new FileReader(new File(inputDirectory + "\\" + folders[i] + "\\" + folders[i] + ".tit")));
					String title = breader.readLine();

					pwriter.println("<tr><td><img src=\"images/image6.gif\" name=\"ball_1000\">&nbsp;<a href=\"\\\\mikescomputer\\Music_Videos\\" + folders[i] + "\\" + folders[i] + ".pls" +
								 "\" onmouseover=\"window.status='" + title + "';return true\" onmouseout=\"window.status='';return true\">" + title +
								 "</a><br><img src=\"images/new_shit_music_videos.jpg\"></td></tr>");

				}
			}
			pwriter.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void menu() {
		try {
			System.out.println("1 for music");
			System.out.println("2 for movies");
			System.out.println("3 for music videos");

			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			int tmpI = Integer.parseInt(stdin.readLine());

			if (tmpI == 1) {
				musicTDC();
			}
			else if (tmpI == 2) {
				movieTDC();
			}
			else if (tmpI == 3) {
				musicVideoTDC();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
	}

	public void printOwnPathMessage() {
		System.out.println("Please enter an absolute path. example c:\\my_music or \\\\musicfactory\\music");
	}
}
