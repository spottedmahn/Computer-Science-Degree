/***************************************************************

** Michael DePouw

** COP3503

** Assignment Number: Final Project 

** Date:10/25/02

***************************************************************/

/****************Class Description***************************

** This class does multiply things. The main thing it does is
** create m3u files.  It will also rename any jpg's in the
** directory to the name of the directory in which the jpg
** is in, then it will copy them to c:\jewel. It will also
** rename any psd files or doc files or tit files to the 
** directory name. It will also delete any files without
** a three or four character file extension. It will also 
** delete sfv files.

**************************************************************/
import java.io.*;
import java.util.*;

public class m3uCreator {
    //directory
    private String direct;
	 private boolean ImageCopyFlag;
	 
    public m3uCreator() {
    }
	 public void setDirect(String in){
	 	direct = in;
	 }
	 public void setImageCopyFlag(boolean tORf){
	 	ImageCopyFlag = tORf;
	 }
    public void createM3uFiles() throws IOException{
        
		  int i, j;
 
        String [] directories;
		  //temp string array
   
	     String [] tmpSA;
		  //temp string
     
	     String tmpS = new String();
        //location of directories
		  
		  File fileIn = new File(direct);
        //putting all directories into directories string array
        
		  directories = fileIn.list();
		  
		  sortStrings sort = new sortStrings();
		  sort.bubbleSort(directories);
		  
		  //stepping thru ever directory
        for(i=0;i<directories.length;i++){
			
			File testIsFile = new File(direct+"\\"+directories[i]);
			
			if(!testIsFile.isFile()){                   
   
	         tmpS = direct + "\\" + directories[i];
				//current directory
 
            File currentD = new File(tmpS);
            //m3u file
 
            FileOutputStream fout = new FileOutputStream(tmpS + "\\" + directories[i] + ".m3u");
            PrintWriter pwriter = new PrintWriter(fout, true);
 
            //listing all files in  directory
            tmpSA = currentD.list();

		      sort.bubbleSort(tmpSA);
       
		      //adding mp3 files to m3u file, renaming jpg, and deleting sfv file
            for(j=0;j<tmpSA.length;j++){
 
                if(tmpSA[j].endsWith(".mp3") || tmpSA[j].endsWith(".MP3") || tmpSA[j].endsWith(".Mp3") || tmpSA[j].endsWith(".mP3")){
                    pwriter.println(tmpSA[j]);
                }
					 //deleting sfv files
                else if(tmpSA[j].endsWith(".sfv")){
					 	
                    File del = new File(tmpS + "\\" + tmpSA[j]);
                    del.delete();
					}
					 //deleting any previous m3u files
					 else if(tmpSA[j].endsWith(".m3u") || tmpSA[j].endsWith(".M3U")){
					 		//System.out.println("In m3u deletion");
					 		File del = new File(tmpS + "\\" + tmpSA[j]);
							del.delete();
					}
					//deleting files with out extensions
					/*
					else if(tmpSA[j].length() > 3){
						if(tmpSA[j].charAt(tmpSA[j].length() - 4) != '.'){
							File del = new File(tmpS + "\\" + tmpSA[j]);
							//testing if file is a directory bc don't want to delete a directory
							if(!del.isDirectory()){
								//special case, file with a four character file extension
								if(!tmpSA[j].endsWith(".html")){
									del.delete();
								}
							}
						}	
					}
					*/
					//renaming doc files to directory name
					else if(tmpSA[j].endsWith(".doc") || tmpSA[j].endsWith(".DOC")){
						//System.out.println("In doc rename");
						File doc = new File(tmpS + "\\" + tmpSA[j]);
						File tmpF = new File(tmpS + "\\" + directories[i] + ".doc");
						doc.renameTo(tmpF);
					}
					//renaming tit files to directory name
					else if(tmpSA[j].endsWith(".tit")){
						//System.out.println("In tit rename");
						File tit = new File(tmpS + "\\" + tmpSA[j]);
						File tmpF = new File(tmpS + "\\" + directories[i] + ".tit");
						tit.renameTo(tmpF);
					}
					//renaming psd files to directory name
					else if(tmpSA[j].endsWith(".psd")){
						//System.out.println("In psd rename");
						File psd = new File(tmpS + "\\" + tmpSA[j]);
						File tmpF = new File(tmpS + "\\" + directories[i] + ".psd");
						psd.renameTo(tmpF);
					}
					if(ImageCopyFlag){
						 //renaming jpg's to directory name and copying to c:\jewel (zzzz is for file saved from amazon.com
 	 	             if(tmpSA[j].endsWith(".jpg") || tmpSA[j].endsWith("ZZZZ") || tmpSA[j].endsWith(".JPG")){
  	      	           File jpg = new File(tmpS + "\\" + tmpSA[j]);
    	   	           File tmpF = new File(tmpS + "\\" + directories[i] + ".jpg");
     	 	              jpg.renameTo(tmpF);
							  try {
									//long start, end;
									//start = System.currentTimeMillis();
									File fileinputfor = new File(tmpS + "\\" + directories[i] + ".jpg");
									byte[] byteIn = new byte[(int)fileinputfor.length()];
									FileInputStream reader = new FileInputStream(tmpS + "\\" + directories[i] + ".jpg");
									int p = reader.read(byteIn);
									FileOutputStream writer = new FileOutputStream( "c:\\jewel\\" + directories[i] + ".jpg");
									writer.write(byteIn);
									reader.close();
									writer.close();
									//end = System.currentTimeMillis();
									//System.out.println("Time for copying an image to c:\\jewel: "+(end - start)/1000);
								}
									
							  catch( IOException ioe ) {
						 	 		ioe.printStackTrace();
							}
						
	                }
						//renaming gif's to directory name and copying to c:\jewel 
                	else if(tmpSA[j].endsWith(".gif")){
                    File jpg = new File(tmpS + "\\" + tmpSA[j]);
                    File tmpF = new File(tmpS + "\\" + directories[i] + ".gif");
                    jpg.renameTo(tmpF);
						  try {
									File fileinputfor = new File(tmpS + "\\" + directories[i] + ".gif");
									byte[] byteIn = new byte[(int)fileinputfor.length()];
									FileInputStream reader = new FileInputStream(tmpS + "\\" + directories[i] + ".gif");
									int p = reader.read(byteIn);
									FileOutputStream writer = new FileOutputStream( "c:\\jewel\\" + directories[i] + ".gif");
									writer.write(byteIn);
									reader.close();
									writer.close();
						  }
						  catch( IOException ioe ) {
						  		ioe.printStackTrace();
						  }
                }						
					}
				}
				pwriter.close();
        }//ends big for loop
      }
    }//end createM3uFiles   
	 public void menu() throws IOException{
	 	int tmpI = 0;
		String tmpS = new String("");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("");
		System.out.println("Copy images to c:\\jewel ?");
		System.out.println("1 for yes");
		System.out.println("2 for no");
		tmpS = in.readLine();
		if(tmpS.compareTo("1") == 0){
			ImageCopyFlag = true;
		}
		else{
			ImageCopyFlag = false;
		}
		System.out.println("");
		System.out.println("1 for new music");
		System.out.println("2 for dj freckles");
		System.out.println("3 for collaborations");
		System.out.println("4 for instrumentals");
		System.out.println("5 for albums_#-c");
		System.out.println("6 for albums_d-h");
		System.out.println("7 for albums_i-m");
		System.out.println("8 for albums_n-s");
		System.out.println("9 for albums_t-z");
		System.out.println("10 for your own path");
		System.out.println("You may enter 1 3 4 and that will run m3uCreation on all three");
		System.out.println();
		try{
			tmpS = in.readLine();
			StringTokenizer token = new StringTokenizer(tmpS);
			while(token.hasMoreTokens()){
				tmpI = Integer.parseInt(token.nextToken());
				if(tmpI == 1){
					direct = new String("\\\\musicfactory\\new_music");
				}
				else if(tmpI == 2){
					direct = new String("\\\\mikescomputer\\dj_freckles");
				}
				else if(tmpI == 3){
					direct = new String("\\\\musicfactory\\collaborations");
				}
				else if(tmpI == 4){
					direct = new String("\\\\musicfactory\\instrumentals");
				}	
				else if(tmpI == 5){
					direct = new String("\\\\musicfactory\\albums_#-c");
				}
				else if(tmpI == 6){
					direct = new String("\\\\musicfactory\\albums_d-h");
				}
				else if(tmpI == 7){
					direct = new String("\\\\musicfactory\\albums_i-m");
				}
				else if(tmpI == 8){
					direct = new String("\\\\musicfactory\\albums_n-s");
				}	
				else if(tmpI == 9){
					direct = new String("\\\\musicfactory\\albums_t-z");
				}																																						
				else if(tmpI == 10){
					System.out.println("Please enter an absolute path. example c:\\my_music");
					String titS = new String();
					titS = in.readLine();
					direct = new String(titS);
				}
				
				System.out.println("Creating m3u's for " + direc);
				createM3uFiles();
			}//end hasMoreTokens while loop				
		}
		catch(NumberFormatException e){
		}	
		catch(IOException e){
			System.out.println(e.getMessage());
		}

	 }
}
