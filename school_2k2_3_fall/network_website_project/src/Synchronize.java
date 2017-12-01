import java.io.*;
import java.util.*;

public class Synchronize{
	
	public Synchronize(){
	
	}
	
	public void synchMusic(String sIn, boolean musicTOcar){
		
		try{
			if(musicTOcar){
			
				if(sIn.endsWith("#-c")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\albums_#-c\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\albums_#-c\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\albums_#-c\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_#-c\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_#-c\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("d-h")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\albums_d-h\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\albums_d-h\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\albums_d-h\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_d-h\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_d-h\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}						
				else if(sIn.endsWith("i-m")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\albums_i-m\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\albums_i-m\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\albums_i-m\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_i-m\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_i-m\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("n-s")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\albums_n-s\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\albums_n-s\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\albums_n-s\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_n-s\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_n-s\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("t-z")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\albums_t-z\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\albums_t-z\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\albums_t-z\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_t-z\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\albums_t-z\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("freckles")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\dj_freckles\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\dj_freckles\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\mikescomputer\\dj_freckles\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\dj_freckles\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\dj_freckles\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("cd")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\books_on_cd\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\books_on_cd\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\mikescomputer\\books_on_cd\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\books_on_cd\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\books_on_cd\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("instrumentals")){
					
					String[] albums = determineAlbums(sIn);
					sortStrings sort = new sortStrings();
					sort.bubbleSort(albums);
					
					File currentAlbumsDirectory = new File("\\\\mikescomputer\\virtual\\instrumentals\\");
					String[] currentAlbums = currentAlbumsDirectory.list();
					sort.bubbleSort(currentAlbums);
					
					for(int i=0;i < albums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(albums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\mikescomputer\\virtual\\instrumentals\\" + albums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + albums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\instrumentals\\" + albums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\instrumentals\\" + albums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\instrumentals\\" + albums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
			}
			else{
				
				if(sIn.endsWith("#-c")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\albums_#-c\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\albums_#-c\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\albums_#-c\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\albums_#-c\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_#-c\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_#-c\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("d-h")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\albums_d-h\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\albums_d-h\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\albums_d-h\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\albums_d-h\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_d-h\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_d-h\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("i-m")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\albums_i-m\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\albums_i-m\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\albums_i-m\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\albums_i-m\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_i-m\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_i-m\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("n-s")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\albums_n-s\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\albums_n-s\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\albums_n-s\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\albums_n-s\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_n-s\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_n-s\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("t-z")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\albums_t-z\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\albums_t-z\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\albums_t-z\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\albums_t-z\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_t-z\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\albums_t-z\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("cd")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\books_on_cd\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\books_on_cd\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\books_on_cd\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\books_on_cd\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								int fileLength = (int) dirContents[j].length();
								
								if(fileLength > 10000){
									
									byte[] data = new byte[10000];
	
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\books_on_cd\\" + currentFWalbums[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 10000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\books_on_cd\\" + currentFWalbums[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("freckles")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\dj_freckles\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\dj_freckles\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\dj_freckles\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\dj_freckles\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\dj_freckles\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\dj_freckles\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}
				else if(sIn.endsWith("instrumentals")){
					
					File currentAlbumsFile = new File("\\\\musicfactory\\instrumentals\\");
					String[] currentAlbums = currentAlbumsFile.list();
					sortStrings sort = new sortStrings();
					sort.bubbleSort(currentAlbums);
					
					File currentFWalbumsFile = new File("\\\\musicfactory\\virtual\\instrumentals\\");
					String[] currentFWalbums = currentFWalbumsFile.list();
					sort.bubbleSort(currentFWalbums);
					
					for(int i=0;i < currentFWalbums.length;i++){
						
						boolean inCurrentAlbums = determineInCurrentAlbums(currentFWalbums[i], currentAlbums);
						
						if(!inCurrentAlbums){
							
							File dir = new File("\\\\musicfactory\\instrumentals\\" + currentFWalbums[i]);
							dir.mkdir();
							
							System.out.println("Copying " + currentFWalbums[i]);
							
							File needToCopy = new File("\\\\musicfactory\\virtual\\instrumentals\\" + currentFWalbums[i] + "\\");
							File[] dirContents = needToCopy.listFiles();
							
							for(int j=0;j < dirContents.length;j++){
								
								if(dirContents[j].isFile()){
									
									int fileLength = (int) dirContents[j].length();
									
									if(fileLength > 10000){
										
										byte[] data = new byte[10000];
		
										String tmpS = new String(dirContents[j].getName());
										
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\instrumentals\\" + currentFWalbums[i] + "\\" + tmpS));
										
										int tmpI;
										
										boolean done = false;
																												
										while(fIn.available() > 10000){
											
											fIn.read(data);
											fOut.write(data);
										}
										
										if(fIn.available() > 0){
											
											byte[] smallData = new byte[fIn.available()];
											fIn.read(smallData);
											fOut.write(data);
										}
										
										fOut.close();
									}
									else{
										byte[] data = new byte[fileLength];
										FileInputStream fIn = new FileInputStream(dirContents[j]);
										fIn.read(data);
										
										String tmpS = new String(dirContents[j].getName());
			
										FileOutputStream fOut = new FileOutputStream(new File("\\\\musicfactory\\virtual\\instrumentals\\" + currentFWalbums[i] + "\\" + tmpS));
										fOut.write(data);
										fOut.close();
									}
								}
							}
							
						}
					}
				}																								
			}																		
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
		}		 	
	}
	
	public String[] determineAlbums(String sIn){
		
		try{
			String[] results = new String[500];
			int currentPos = 0;
			
			BufferedReader html, htmlNew = null;
			
			if(sIn.endsWith("_#-c")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_#-c_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_new_car.htm")));	
			}
			else if(sIn.endsWith("_d-h")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_d-h_car.htm")));	
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_new_car.htm")));
			}
			else if(sIn.endsWith("_i-m")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_i-m_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_new_car.htm")));
			}
			else if(sIn.endsWith("_n-s")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_n-s_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_new_car.htm")));
			}
			else if(sIn.endsWith("_t-z")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_t-z_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\albums_new_car.htm")));
			}
			else if(sIn.endsWith("cd")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\booksOnCD_car.html")));
			}
			else if(sIn.endsWith("freckles")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\custom_cdz_car.htm")));
			}
			else if(sIn.endsWith("instrumentals")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\instrumentals_car.htm")));
			}
			else{
				System.out.println("Error in determineAlbums, couldn't determine which file to open");
				return null;
			}																				
						
			while(html.ready()){

				String tmpS = html.readLine();
				
				while((html.ready()) && (!tmpS.startsWith("<!--Insert"))){
					
					tmpS = html.readLine();
				}
				
				if(html.ready()){
					tmpS = html.readLine();
				}
				
				while((html.ready()) && (!tmpS.startsWith("<!--EndSort"))){
					
					if(tmpS.startsWith("<td>")){
						
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
						
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
						
						results[currentPos] = new String(tokens.nextToken());
						
						currentPos++;
					}
					
					tmpS = html.readLine();
				}
			}
			
			if(htmlNew != null){
				
				if(sIn.endsWith("_#-c")){
					
					String tmpS;
					boolean done = false;
					
					while(!done){
						
						tmpS = htmlNew.readLine();
						
						while(!tmpS.startsWith("<!--Insert")){
							
							tmpS = htmlNew.readLine();
						}	
						
						if(tmpS.startsWith("<!--Insert_d")){
								done = true;
								break;
						}
						
						while(!tmpS.startsWith("<!--EndSort")){
							
							if(tmpS.startsWith("<td>")){
								
								StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
								
								tokens.nextToken();
								tokens.nextToken();
								tokens.nextToken();
								
								results[currentPos] = new String(tokens.nextToken());
								//System.out.println("results[currentPos] == "+results[currentPos]);
								currentPos++;
							}
							
							tmpS = htmlNew.readLine();
						}									
					}
				}
				else if(sIn.endsWith("_d-h")){
					
					String tmpS;
					boolean done = false, inDH = false;
					
	outer:		while(!done){
						
						tmpS = htmlNew.readLine();
						
						if(!inDH){
							
							while(!tmpS.startsWith("<!--Insert_d")){
								
								tmpS = htmlNew.readLine();
							}

							inDH = true;
						}
						else{
							while(!tmpS.startsWith("<!--Insert")){
														
								tmpS = htmlNew.readLine();
							}	
						}
						
						if(tmpS.startsWith("<!--Insert_i")){

							done = true;
							break outer;
						}
						while(!tmpS.startsWith("<!--EndSort")){
							
							if(tmpS.startsWith("<td>")){
								
								StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
								
								tokens.nextToken();
								tokens.nextToken();
								tokens.nextToken();
								
								results[currentPos] = new String(tokens.nextToken());
								//System.out.println("results[currentPos] == "+results[currentPos]);
								currentPos++;
							}
							
							tmpS = htmlNew.readLine();
						}									
					}
				}
				else if(sIn.endsWith("_i-m")){
					
					String tmpS;
					boolean done = false, inIM = false;
					
	outer:		while(!done){
						
						tmpS = htmlNew.readLine();
						
						if(!inIM){
							
							while(!tmpS.startsWith("<!--Insert_i")){
								
								tmpS = htmlNew.readLine();
							}

							inIM = true;
						}
						else{
							while(!tmpS.startsWith("<!--Insert")){	
														
								tmpS = htmlNew.readLine();
							}	
						}
						
								
						if(tmpS.startsWith("<!--Insert_n")){
								done = true;
								break outer;
						}						
						
						while(!tmpS.startsWith("<!--EndSort")){
							
							if(tmpS.startsWith("<td>")){
								
								StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
								
								tokens.nextToken();
								tokens.nextToken();
								tokens.nextToken();
								
								results[currentPos] = new String(tokens.nextToken());
								//System.out.println("results[currentPos] == "+results[currentPos]);
								currentPos++;
							}
							
							tmpS = htmlNew.readLine();
						}									
					}
				}								
				else if(sIn.endsWith("_n-s")){
					
					String tmpS;
					boolean done = false, inNS = false;
					
	outer:		while(!done){
						
						tmpS = htmlNew.readLine();
						
						if(!inNS){
							
							while(!tmpS.startsWith("<!--Insert_n")){
								
								tmpS = htmlNew.readLine();
							}

							inNS = true;
						}
						else{
							while(!tmpS.startsWith("<!--Insert")){
														
								tmpS = htmlNew.readLine();
							}	
						}

						if(tmpS.startsWith("<!--Insert_t")){
								done = true;
								break outer;
						}	
														
						while(!tmpS.startsWith("<!--EndSort")){
							
							if(tmpS.startsWith("<td>")){
								
								StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
								
								tokens.nextToken();
								tokens.nextToken();
								tokens.nextToken();
								
								results[currentPos] = new String(tokens.nextToken());
								//System.out.println("results[currentPos] == "+results[currentPos]);
								currentPos++;
							}
							
							tmpS = htmlNew.readLine();
						}									
					}
				}
				else if(sIn.endsWith("_t-z")){
					
					String tmpS;
					boolean done = false, inTZ = false;
					
	outer:		while(!done){
						
						tmpS = htmlNew.readLine();
						
						if(!inTZ){
							
							while(!tmpS.startsWith("<!--Insert_t")){
								
								tmpS = htmlNew.readLine();
							}

							inTZ = true;
						}
						else{
							while(!tmpS.startsWith("<!--Insert")){
								
								if(tmpS.startsWith("</html>")){
										done = true;
										break outer;
								}	
														
								tmpS = htmlNew.readLine();
							}	
						}
							
						while(!tmpS.startsWith("<!--EndSort")){
							
							if(tmpS.startsWith("<td>")){
								
								StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
								
								tokens.nextToken();
								tokens.nextToken();
								tokens.nextToken();
								
								results[currentPos] = new String(tokens.nextToken());
								//System.out.println("results[currentPos] == "+results[currentPos]);
								currentPos++;
							}
							
							tmpS = htmlNew.readLine();
						}
					}
				}							
			}
						
			String[] resultsCopy = new String[currentPos];
			
			for(int i=0;i < currentPos;i++){
				
				resultsCopy[i] = new String(results[i]);
			}
			
			return resultsCopy;
		}
		catch(IOException e){
		
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean determineInCurrentAlbums(String sIn, String[] sInA){
		
		for(int i=0;i < sInA.length;i++){
			
			if(sIn.compareTo(sInA[i]) == 0){
				
				return true;
			}
		}
		
		return false;
	}
	
	public void synchMovies(String sIn){
		
		try{
			if(sIn.endsWith("#-c")){
				
				String[] movies = determineMovies(sIn);
				sortStrings sort = new sortStrings();
				sort.bubbleSort(movies);
				
				File currentMoviesDirectory = new File("\\\\mikescomputer\\virtual\\movies_#-c\\");
				String[] currentMovies = currentMoviesDirectory.list();
				sort.bubbleSort(currentMovies);
				
				for(int i=0;i < movies.length;i++){
					
					boolean inCurrentMovies = determineInCurrentMovies(movies[i], currentMovies);
					
					if(!inCurrentMovies){
						
						File dir = new File("\\\\mikescomputer\\virtual\\movies_#-c\\" + movies[i]);
						dir.mkdir();
						
						System.out.println("Copying " + movies[i]);
						
						File needToCopy = new File("\\\\videofactory\\movies_#-c\\" + movies[i] + "\\");
						File[] dirContents = needToCopy.listFiles();
						
						for(int j=0;j < dirContents.length;j++){
							
							if(dirContents[j].isFile()){
	
								int fileLength = (int) dirContents[j].length();
										
								if(fileLength > 65000){									
								
									byte[] data = new byte[65000];
			
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_#-c\\" + movies[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 65000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
								
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_#-c\\" + movies[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
						}
					}
				}
			}
			else if(sIn.endsWith("d-h")){
				
				String[] movies = determineMovies(sIn);
				sortStrings sort = new sortStrings();
				sort.bubbleSort(movies);
				
				File currentMoviesDirectory = new File("\\\\mikescomputer\\virtual\\movies_d-h\\");
				String[] currentMovies = currentMoviesDirectory.list();
				sort.bubbleSort(currentMovies);
				
				for(int i=0;i < movies.length;i++){
					
					boolean inCurrentMovies = determineInCurrentMovies(movies[i], currentMovies);
					
					if(!inCurrentMovies){
						
						File dir = new File("\\\\mikescomputer\\virtual\\movies_d-h\\" + movies[i]);
						dir.mkdir();
						
						System.out.println("Copying " + movies[i]);
						
						File needToCopy = new File("\\\\videofactory\\movies_d-h\\" + movies[i] + "\\");
						File[] dirContents = needToCopy.listFiles();
						
						for(int j=0;j < dirContents.length;j++){
							
							if(dirContents[j].isFile()){
	
								int fileLength = (int) dirContents[j].length();
										
								if(fileLength > 65000){									
								
									byte[] data = new byte[65000];
			
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_d-h\\" + movies[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 65000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
								
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_d-h\\" + movies[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
						}
					}
				}
			}
			else if(sIn.endsWith("i-m")){
				
				String[] movies = determineMovies(sIn);
				sortStrings sort = new sortStrings();
				sort.bubbleSort(movies);
				
				File currentMoviesDirectory = new File("\\\\mikescomputer\\virtual\\movies_i-m\\");
				String[] currentMovies = currentMoviesDirectory.list();
				sort.bubbleSort(currentMovies);
				
				for(int i=0;i < movies.length;i++){
					
					boolean inCurrentMovies = determineInCurrentMovies(movies[i], currentMovies);
					
					if(!inCurrentMovies){
						
						File dir = new File("\\\\mikescomputer\\virtual\\movies_i-m\\" + movies[i]);
						dir.mkdir();
						
						System.out.println("Copying " + movies[i]);
						
						File needToCopy = new File("\\\\videofactory\\movies_i-m\\" + movies[i] + "\\");
						File[] dirContents = needToCopy.listFiles();
						
						for(int j=0;j < dirContents.length;j++){
							
							if(dirContents[j].isFile()){
	
								int fileLength = (int) dirContents[j].length();
										
								if(fileLength > 65000){									
								
									byte[] data = new byte[65000];
			
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_i-m\\" + movies[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 65000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
								
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_i-m\\" + movies[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
						}
					}
				}
			}
			else if(sIn.endsWith("n-s")){
				
				String[] movies = determineMovies(sIn);
				sortStrings sort = new sortStrings();
				sort.bubbleSort(movies);
				
				File currentMoviesDirectory = new File("\\\\mikescomputer\\virtual\\movies_n-s\\");
				String[] currentMovies = currentMoviesDirectory.list();
				sort.bubbleSort(currentMovies);
				
				for(int i=0;i < movies.length;i++){
					
					boolean inCurrentMovies = determineInCurrentMovies(movies[i], currentMovies);
					
					if(!inCurrentMovies){
						
						File dir = new File("\\\\mikescomputer\\virtual\\movies_n-s\\" + movies[i]);
						dir.mkdir();
						
						System.out.println("Copying " + movies[i]);
						
						File needToCopy = new File("\\\\videofactory\\movies_n-s\\" + movies[i] + "\\");
						File[] dirContents = needToCopy.listFiles();
						
						for(int j=0;j < dirContents.length;j++){
							
							if(dirContents[j].isFile()){
	
								int fileLength = (int) dirContents[j].length();
										
								if(fileLength > 65000){									
								
									byte[] data = new byte[65000];
			
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_n-s\\" + movies[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 65000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
								
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_n-s\\" + movies[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
						}
					}
				}
			}
			else if(sIn.endsWith("t-z")){
				
				String[] movies = determineMovies(sIn);
				sortStrings sort = new sortStrings();
				sort.bubbleSort(movies);
				
				File currentMoviesDirectory = new File("\\\\mikescomputer\\virtual\\movies_t-z\\");
				String[] currentMovies = currentMoviesDirectory.list();
				sort.bubbleSort(currentMovies);
				
				for(int i=0;i < movies.length;i++){
					
					boolean inCurrentMovies = determineInCurrentMovies(movies[i], currentMovies);
					
					if(!inCurrentMovies){
						
						File dir = new File("\\\\mikescomputer\\virtual\\movies_t-z\\" + movies[i]);
						dir.mkdir();
						
						System.out.println("Copying " + movies[i]);
						
						File needToCopy = new File("\\\\videofactory2\\movies_t-z\\" + movies[i] + "\\");
						File[] dirContents = needToCopy.listFiles();
						
						for(int j=0;j < dirContents.length;j++){
							
							if(dirContents[j].isFile()){
	
								int fileLength = (int) dirContents[j].length();
										
								if(fileLength > 65000){									
								
									byte[] data = new byte[65000];
			
									String tmpS = new String(dirContents[j].getName());
									
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_t-z\\" + movies[i] + "\\" + tmpS));
									
									int tmpI;
									
									boolean done = false;
																											
									while(fIn.available() > 65000){
										
										fIn.read(data);
										fOut.write(data);
									}
									
									if(fIn.available() > 0){
										
										byte[] smallData = new byte[fIn.available()];
										fIn.read(smallData);
										fOut.write(data);
									}
									
									fOut.close();
								}
								else{
								
									byte[] data = new byte[fileLength];
									FileInputStream fIn = new FileInputStream(dirContents[j]);
									fIn.read(data);
									
									String tmpS = new String(dirContents[j].getName());
		
									FileOutputStream fOut = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\movies_t-z\\" + movies[i] + "\\" + tmpS));
									fOut.write(data);
									fOut.close();
								}
							}
						}
					}
				}
			}												
		}
		catch(IOException e){
			
			System.out.println(e.getMessage());
		}
	}

	public String[] determineMovies(String sIn){
		
		try{
			String[] results = new String[500];
			int currentPos = 0;
			
			BufferedReader html, htmlNew;
			
			if(sIn.endsWith("_#-c")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_#-c_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_new_car.htm")));
			}
			else if(sIn.endsWith("_d-h")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_d-h_car.htm")));	
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_new_car.htm")));
			}
			else if(sIn.endsWith("_i-m")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_i-m_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_new_car.htm")));
			}
			else if(sIn.endsWith("_n-s")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_n-s_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_new_car.htm")));
			}
			else if(sIn.endsWith("_t-z")){
				
				html = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_t-z_car.htm")));
				htmlNew = new BufferedReader(new FileReader(new File("\\\\musicfactory\\network_website\\movies_new_car.htm")));
			}
			else{
				System.out.println("Error in determineMovies, couldn't determine which file to open");
				return null;
			}																				
						
			while(html.ready()){

				String tmpS = html.readLine();
				
				while((html.ready()) && (!tmpS.startsWith("<!--Insert"))){
					
					tmpS = html.readLine();
				}
				
				if(html.ready()){
					tmpS = html.readLine();
				}
				
				while((html.ready()) && (!tmpS.startsWith("<!--EndSort"))){
					
					if(tmpS.startsWith("<td>")){
						
						StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
						
						tokens.nextToken();
						tokens.nextToken();
						tokens.nextToken();
						
						results[currentPos] = new String(tokens.nextToken());
						
						currentPos++;
					}
					
					tmpS = html.readLine();
				}
			}
			if(sIn.endsWith("_#-c")){
				
				String tmpS;
				boolean done = false;
				
				while(!done){
					
					tmpS = htmlNew.readLine();
					
					while(!tmpS.startsWith("<!--Insert")){
						
						tmpS = htmlNew.readLine();
					}	
					
					if(tmpS.startsWith("<!--Insert_d")){
							done = true;
							break;
					}
					
					while(!tmpS.startsWith("<!--EndSort")){
						
						if(tmpS.startsWith("<td>")){
							
							StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
							
							tokens.nextToken();
							tokens.nextToken();
							tokens.nextToken();
							
							results[currentPos] = new String(tokens.nextToken());
							//System.out.println("results[currentPos] == "+results[currentPos]);
							currentPos++;
						}
						
						tmpS = htmlNew.readLine();
					}									
				}
			}
			else if(sIn.endsWith("_d-h")){
				
				String tmpS;
				boolean done = false, inDH = false;
				
outer:		while(!done){
					
					tmpS = htmlNew.readLine();
					
					if(!inDH){
						
						while(!tmpS.startsWith("<!--Insert_d")){
							
							tmpS = htmlNew.readLine();
						}

						inDH = true;
					}
					else{
						while(!tmpS.startsWith("<!--Insert")){
													
							tmpS = htmlNew.readLine();
						}	
					}
					
					if(tmpS.startsWith("<!--Insert_i")){

						done = true;
						break outer;
					}
					while(!tmpS.startsWith("<!--EndSort")){
						
						if(tmpS.startsWith("<td>")){
							
							StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
							
							tokens.nextToken();
							tokens.nextToken();
							tokens.nextToken();
							
							results[currentPos] = new String(tokens.nextToken());
							//System.out.println("results[currentPos] == "+results[currentPos]);
							currentPos++;
						}
						
						tmpS = htmlNew.readLine();
					}									
				}
			}
			else if(sIn.endsWith("_i-m")){
				
				String tmpS;
				boolean done = false, inIM = false;
				
outer:		while(!done){
					
					tmpS = htmlNew.readLine();
					
					if(!inIM){
						
						while(!tmpS.startsWith("<!--Insert_i")){
							
							tmpS = htmlNew.readLine();
						}

						inIM = true;
					}
					else{
						while(!tmpS.startsWith("<!--Insert")){	
													
							tmpS = htmlNew.readLine();
						}	
					}
					
							
					if(tmpS.startsWith("<!--Insert_n")){
							done = true;
							break outer;
					}						
					
					while(!tmpS.startsWith("<!--EndSort")){
						
						if(tmpS.startsWith("<td>")){
							
							StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
							
							tokens.nextToken();
							tokens.nextToken();
							tokens.nextToken();
							
							results[currentPos] = new String(tokens.nextToken());
							//System.out.println("results[currentPos] == "+results[currentPos]);
							currentPos++;
						}
						
						tmpS = htmlNew.readLine();
					}									
				}
			}								
			else if(sIn.endsWith("_n-s")){
				
				String tmpS;
				boolean done = false, inNS = false;
				
outer:		while(!done){
					
					tmpS = htmlNew.readLine();
					
					if(!inNS){
						
						while(!tmpS.startsWith("<!--Insert_n")){
							
							tmpS = htmlNew.readLine();
						}

						inNS = true;
					}
					else{
						while(!tmpS.startsWith("<!--Insert")){
													
							tmpS = htmlNew.readLine();
						}	
					}

					if(tmpS.startsWith("<!--Insert_t")){
							done = true;
							break outer;
					}	
													
					while(!tmpS.startsWith("<!--EndSort")){
						
						if(tmpS.startsWith("<td>")){
							
							StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
							
							tokens.nextToken();
							tokens.nextToken();
							tokens.nextToken();
							
							results[currentPos] = new String(tokens.nextToken());
							//System.out.println("results[currentPos] == "+results[currentPos]);
							currentPos++;
						}
						
						tmpS = htmlNew.readLine();
					}									
				}
			}
			else if(sIn.endsWith("_t-z")){
				
				String tmpS;
				boolean done = false, inTZ = false;
				
outer:		while(!done){
					
					tmpS = htmlNew.readLine();
					
					if(!inTZ){
						
						while(!tmpS.startsWith("<!--Insert_t")){
							
							tmpS = htmlNew.readLine();
						}

						inTZ = true;
					}
					else{
						while(!tmpS.startsWith("<!--Insert")){
							
							if(tmpS.startsWith("</html>")){
									done = true;
									break outer;
							}	
													
							tmpS = htmlNew.readLine();
						}	
					}
						
					while(!tmpS.startsWith("<!--EndSort")){
						
						if(tmpS.startsWith("<td>")){
							
							StringTokenizer tokens = new StringTokenizer(tmpS, "\\\"");
							
							tokens.nextToken();
							tokens.nextToken();
							tokens.nextToken();
							
							results[currentPos] = new String(tokens.nextToken());
							//System.out.println("results[currentPos] == "+results[currentPos]);
							currentPos++;
						}
						
						tmpS = htmlNew.readLine();
					}
				}
			}													
			String[] resultsCopy = new String[currentPos];
			
			for(int i=0;i < currentPos;i++){
				
				resultsCopy[i] = new String(results[i]);
			}
			
			return resultsCopy;
		}
		catch(IOException e){
		
			System.out.println(e.getMessage());
		}
		return null;
	}	
	public boolean determineInCurrentMovies(String sIn, String[] sInA){
		
		for(int i=0;i < sInA.length;i++){
			
			if(sIn.compareTo(sInA[i]) == 0){
				
				return true;
			}
		}
		
		return false;
	}
	
	public void synchJewelCaseCovers(boolean musicTOfire){
		
		try{
			
			if(musicTOfire){
							
				File inputDir = new File("\\\\mikescomputer\\jewel");
				File[] covers = inputDir.listFiles();
				
				for(int i=0;i < covers.length;i++){
					
					if(covers[i].isFile()){

						File exists = new File("\\\\mikescomputer\\virtual\\jewel\\" + covers[i].getName());
						
						if(exists.exists()){
					
							if(exists.length() != covers[i].length()){
								
								System.out.println("Copying " + covers[i].getName());
								
								FileOutputStream output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\jewel\\" + covers[i].getName()));
								
								byte[] data = new byte[(int) covers[i].length()];
								
								FileInputStream input = new FileInputStream(covers[i]);
								
								input.read(data);
								
								output.write(data);
							}
						}
						else{
							
							System.out.println("Copying " + covers[i].getName());
							
							FileOutputStream output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\jewel\\" + covers[i].getName()));
							
							byte[] data = new byte[(int) covers[i].length()];
							
							FileInputStream input = new FileInputStream(covers[i]);
							
							input.read(data);
							
							output.write(data);							
						}						
					}
				}
			}
			else{
				
				File inputDir = new File("\\\\musicfactory\\virtual\\jewel");
				File[] covers = inputDir.listFiles();
				
				for(int i=0;i < covers.length;i++){
					
					if(covers[i].isFile()){

						File exists = new File("\\\\musicfactory\\jewel\\" + covers[i].getName());
						
						if(exists.exists()){
					
							if(exists.length() != covers[i].length()){
								
								System.out.println("Copying " + covers[i].getName());
								
								FileOutputStream output = new FileOutputStream(new File("\\\\musicfactory\\jewel\\" + covers[i].getName()));
								
								byte[] data = new byte[(int) covers[i].length()];
								
								FileInputStream input = new FileInputStream(covers[i]);
								
								input.read(data);
								
								output.write(data);
							}
						}
						else{
							
							System.out.println("Copying " + covers[i].getName());
							
							FileOutputStream output = new FileOutputStream(new File("\\\\musicfactory\\jewel\\" + covers[i].getName()));
							
							byte[] data = new byte[(int) covers[i].length()];
							
							FileInputStream input = new FileInputStream(covers[i]);
							
							input.read(data);
							
							output.write(data);							
						}						
					}
				}				
			}
		}
		catch(IOException e){
			
			System.out.println(e.getMessage());
		}
	}	
	
	public void synchNetworkWebsite(boolean musicTOfire, File dir, String subDir){
		
		try{
			if(musicTOfire){
				
				File[] files = dir.listFiles();
				
				for(int i=0;i < files.length;i++){
					
					if(files[i].isFile()){
						
						File exists;
						
						if(subDir == null){
							exists = new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + files[i].getName());
						}
						else{
							exists = new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + subDir + "\\" + files[i].getName());
						}
						
						if(exists.exists()){
							
							if(exists.length() != files[i].length()){
								
								FileInputStream input = new FileInputStream(files[i]);
								FileOutputStream output;
								
								if(subDir == null){
									output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + files[i].getName()));
								}
								else{
									output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + subDir + "\\" + files[i].getName()));
								}
								
								byte[] data = new byte[(int) files[i].length()];
								
								input.read(data);
								output.write(data);							
							}
						}
						else{
						
							FileInputStream input = new FileInputStream(files[i]);
							FileOutputStream output;
							
							if(subDir == null){
								output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + files[i].getName()));
							}
							else{
								output = new FileOutputStream(new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + subDir + "\\" + files[i].getName()));
							}
							
							byte[] data = new byte[(int) files[i].length()];
							
							input.read(data);
							output.write(data);						
						}
					}
					else{

						File makeDir;

						if(subDir == null){
							makeDir = new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + files[i].getName());
						}
						else{
							makeDir = new File("\\\\mikescomputer\\virtual\\websites\\network_website\\" + subDir + "\\" + files[i].getName());
						}
						
						makeDir.mkdirs();
						
						if(subDir == null){
							synchNetworkWebsite(musicTOfire, files[i], files[i].getName());
						}
						else{
							synchNetworkWebsite(musicTOfire, files[i], subDir + "\\" + files[i].getName());
						}
					}
				}
			}
			else{
				
				File[] files = dir.listFiles();
				
				for(int i=0;i < files.length;i++){
					
					if(files[i].isFile()){
						
						File exists;
						
						if(subDir == null){
							exists = new File("\\\\musicfactory\\websites\\network_website\\" + files[i].getName());
						}
						else{
							exists = new File("\\\\musicfactory\\websites\\network_website\\" + subDir + "\\" + files[i].getName());
						}
						
						if(exists.exists()){
							
							if(exists.length() != files[i].length()){
								
								FileInputStream input = new FileInputStream(files[i]);
								FileOutputStream output;
								
								if(subDir == null){
									output = new FileOutputStream(new File("\\\\musicfactory\\websites\\network_website\\" + files[i].getName()));
								}
								else{
									output = new FileOutputStream(new File("\\\\musicfactory\\websites\\network_website\\" + subDir + "\\" + files[i].getName()));
								}
								
								byte[] data = new byte[(int) files[i].length()];
								
								input.read(data);
								output.write(data);							
							}
						}
						else{
						
							FileInputStream input = new FileInputStream(files[i]);
							FileOutputStream output;
							
							if(subDir == null){
								output = new FileOutputStream(new File("\\\\musicfactory\\websites\\network_website\\" + files[i].getName()));
							}
							else{
								output = new FileOutputStream(new File("\\\\musicfactory\\websites\\network_website\\" + subDir + "\\" + files[i].getName()));
							}
							
							byte[] data = new byte[(int) files[i].length()];
							
							input.read(data);
							output.write(data);						
						}
					}
					else{

						File makeDir;

						if(subDir == null){
							makeDir = new File("\\\\musicfactory\\websites\\network_website\\" + files[i].getName());
						}
						else{
							makeDir = new File("\\\\musicfactory\\websites\\network_website\\" + subDir + "\\" + files[i].getName());
						}
						
						makeDir.mkdirs();
						
						if(subDir == null){
							synchNetworkWebsite(musicTOfire, files[i], files[i].getName());
						}
						else{
							synchNetworkWebsite(musicTOfire, files[i], subDir + "\\" + files[i].getName());
						}
					}
				}			
			}
		}
		catch(IOException e){
			
			System.out.println(e.getMessage());
		}
	}
	
	public void menu(){
		
		try{
			System.out.println("1 for from musicfactory to firewire drive");
			System.out.println("2 for from firewire drive to mikescomputer\\virtual");
			
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			
			int option = Integer.parseInt(stdin.readLine());
						
			boolean musicTOfire;
			
			if(option == 1){
				
				musicTOfire = true;
			}
			else{
				
				musicTOfire = false;
			}
			
			System.out.println("1 for albums_#-c");
			System.out.println("2 for albums_d-h");
			System.out.println("3 for albums_i-m");
			System.out.println("4 for albums_n-s");
			System.out.println("5 for albums_t-z");
			System.out.println("6 for DJ Freckles");
			System.out.println("7 for Instrumentals");
			System.out.println("8 for Books On CD");
			System.out.println("9 for movies_#-c");
			System.out.println("10 for movies_d-h");
			System.out.println("11 for movies_i-m");
			System.out.println("12 for movies_n-s");
			System.out.println("13 for movies_t-z");	
			System.out.println("14 for Music Videos");
			System.out.println("15 for Network Website");
			System.out.println("16 for Jewel Case Covers");
			System.out.println("You may enter 1 3 4 and that will run synch on all three");
				
			StringTokenizer tokens = new StringTokenizer(stdin.readLine());
				
			while(tokens.hasMoreTokens()){
									
				int option2 = Integer.parseInt(tokens.nextToken());
				
				if(option2 == 1){
					
					synchMusic("albums_#-c", musicTOfire);
				}
				else if(option2 == 2){
					
					synchMusic("albums_d-h", musicTOfire);
				}
				else if(option2 == 3){
					
					synchMusic("albums_i-m", musicTOfire);
				}
				else if(option2 == 4){
					
					synchMusic("albums_n-s", musicTOfire);
				}
				else if(option2 == 5){
					
					synchMusic("albums_t-z", musicTOfire);
				}
				else if(option2 == 6){
					
					synchMusic("dj_freckles", musicTOfire);
				}
				else if(option2 == 7){
					
					synchMusic("instrumentals", musicTOfire);
				}
				else if(option2 == 8){
					
					synchMusic("books_on_cd", musicTOfire);
				}																										
				else if(option2 == 9){
					
					synchMovies("movies_#-c");
				}
				else if(option2 == 10){
					
					synchMovies("movies_d-h");
				}
				else if(option2 == 11){
					
					synchMovies("movies_i-m");
				}
				else if(option2 == 12){
					
					synchMovies("movies_n-s");
				}
				else if(option2 == 13){
					
					synchMovies("movies_t-z");
				}
				else if(option2 == 15){
					
					File network_website_dir;
					
					if(musicTOfire){
						network_website_dir = new File("\\\\musicfactory\\network_website");
					}
					else{
						network_website_dir = new File("\\\\musicfactory\\virtual\\network_website");
					}
					
					synchNetworkWebsite(musicTOfire, network_website_dir, null);
				}
				else if(option2 == 16){
				
					synchJewelCaseCovers(musicTOfire);
				}														
			}
		}
		catch(IOException e){
		
			System.out.println(e.getMessage());
		}
	}
}