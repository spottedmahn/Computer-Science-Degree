/***************************************************************

** Michael DePouw

** Date:6-19-03

***************************************************************/


import java.io.*;
import java.util.*;

public class plsCreator{
	
	String directory;
	
	public plsCreator(){
		
		directory = null;
	}
	public plsCreator(String direcIn){
		
		directory = direcIn;
	}
	
	public void create(String direcIn){
		
		try{
			directory = direcIn;
			
			File videos = new File (directory);
			String[] folders = videos.list();
			
			sortStrings sort = new sortStrings();
		 	sort.bubbleSort(folders);
		  
			for(int i=0;i < folders.length;i++){
				
				File folderIn = new File(directory + "\\" + folders[i]);
				
				if(folderIn.isDirectory()){
				
					String[] files = folderIn.list();
					
					PrintWriter pWriter = new PrintWriter(new FileWriter(new File(directory + "\\" + folders[i] + "\\" + folders[i] + ".pls")));
					
					for(int j=0;j < files.length;j++){
						
						if(files[j].endsWith(".m2v")){
							
							pWriter.println("\\\\mikescomputer\\music_videos\\" + folders[i] + "\\" + files[j]);
						}
						else if(files[j].endsWith(".mpg")){
							pWriter.println("\\\\mikescomputer\\music_videos\\" + folders[i] + "\\" + files[j]);
						}
					}
					
					pWriter.close();
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void menu(){
		
		try{
			System.out.println("1 for need_to_post");
			System.out.println("2 for music_videos");
			System.out.println("3 for your own path");
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			
			int tmpI = Integer.parseInt(stdin.readLine());
			
			if(tmpI == 1){
				
				create("\\\\Mikescomputer\\Music_Videos_need_to_be_posted");
			}
			else if(tmpI == 2){
				create("\\\\Mikescomputer\\Music_Videos");
			}
			else if(tmpI == 3){
				System.out.println("Please enter absolute path");
				create(stdin.readLine());
			}
			else{
				System.out.println("Invalid number");
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e){
			System.out.println("You didn't enter a number");
		}
	}
}