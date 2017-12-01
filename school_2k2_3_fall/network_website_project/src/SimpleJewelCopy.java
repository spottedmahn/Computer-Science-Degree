/***************************************************************

** Michael DePouw

** Date:12-06-02

***************************************************************/

/****************Class Description***************************

**
**
**

**************************************************************/
import java.io.*;
import java.util.*;

public class SimpleJewelCopy{
		//computers t copy the images to
		private String[] computerToCopyTo;
		private int MaxNumberOfComputers;
		private int numberOfComputersToCopyTo;
		private boolean directoryFlag;
		private String directory;
		
		//default constructor
		public SimpleJewelCopy(){
			numberOfComputersToCopyTo = 0;
			MaxNumberOfComputers = 8;
			computerToCopyTo = new String[MaxNumberOfComputers];
		}
		public SimpleJewelCopy(String[] in){
			computerToCopyTo = in;
		}
		//copying images method
		public void copy(){
			//timing total copy time
			long totalStart = System.currentTimeMillis(), totalEnd;
			
			//temp string
			String tmpS = new String();
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

			//listing all files in c:\jewel
			File jewels = new File("\\\\mikescomputer\\jewel");
			String[] allFiles = jewels.list();
			
			if(directoryFlag){
				File files = new File(directory);
				String[] someFiles = files.list();
				String[] missingFiles = missingFiles(allFiles, someFiles, MaxNumberOfComputers + 1);								
				for(int i=0;i<missingFiles.length;i++){
					try {
						File fIn = new File("\\\\mikescomputer\\jewel\\" + missingFiles[i]);
						byte[] byteIn = new byte[(int)fIn.length()];
						FileInputStream reader = new FileInputStream("\\\\mikescomputer\\jewel\\" + missingFiles[i]);
						int p = reader.read(byteIn);
						FileOutputStream writer = new FileOutputStream(directory + "\\" + missingFiles[i]);
						writer.write(byteIn);
						reader.close();
						writer.close();
					}
					catch( IOException ioe ) {
						ioe.printStackTrace();
					}				
				}					
			}
			else{
				//stepping thru every computer
				for(int k=0;k<numberOfComputersToCopyTo;k++){
					try{
						long listingStart = System.currentTimeMillis(), listingEnd;
						
						//listing files on the computerToCopyTo
						File computer = new File("\\\\"+computerToCopyTo[k]+"\\jewel");
						String[] someFiles = computer.list();
						listingEnd = System.currentTimeMillis();
						System.out.println("Time for listing files on "+computerToCopyTo[k]+" is: "+(listingEnd - listingStart)/1000+" seconds");
						System.out.println("Allfiles.length = "+allFiles.length+" someFiles.length = "+someFiles.length);

						//getting missing files
						String[] missingFiles = missingFiles(allFiles, someFiles, k);
						long copyStart = System.currentTimeMillis(), copyEnd;
						//copying all missing files
						for(int i=0;i<missingFiles.length;i++){
							try {
								File fIn = new File("\\\\mikescomputer\\jewel\\" + missingFiles[i]);
								//System.out.println(missingFiles[i]+" length is "+fIn.length()+" casted as an int: "+((int)fIn.length()));
								byte[] byteIn = new byte[(int)fIn.length()];
								FileInputStream reader = new FileInputStream("\\\\mikescomputer\\jewel\\" + missingFiles[i]);
								int p = reader.read(byteIn);
								FileOutputStream writer = new FileOutputStream("\\\\" + computerToCopyTo[k] + "\\jewel\\" + missingFiles[i]);
								writer.write(byteIn);
								reader.close();
								writer.close();
							}
							catch( IOException ioe ) {
								ioe.printStackTrace();
							}				
						}
						copyEnd = System.currentTimeMillis();
						System.out.println("Copying time for "+computerToCopyTo[k]+" is : "+(copyEnd - copyStart)/1000+" seconds");
						System.out.println("");
					}
					catch (Exception e){
						System.out.println(computerToCopyTo[k]+" not running???");
						System.out.println("");
						//System.out.println(e.getMessage());
					}				
				}
			}
			totalEnd = System.currentTimeMillis();
			System.out.println("The total time for the copy method in the SimpleJewelCopy class is "+(totalEnd - totalStart)/1000+" seconds");
		}
		public String[] missingFiles(String[] all, String[] some, int computerToCopyToInt){
			
			long startMissing = System.currentTimeMillis(), endMissing;
			boolean isThere = false;
			int missingCount = 0;
			String[] missing = new String[all.length];
			
			for(int i=0;i<all.length;i++){
				
				File size1 = new File("\\\\mikescomputer\\jewel\\"+all[i]);
				if(size1.isFile()){
				
					//if i is greater than some.length null pointer will be thrown
					if(i < some.length){
					
						if(computerToCopyToInt > MaxNumberOfComputers){
							File size2 = new File(directory + "\\" + some[i]);
							//testing if the files are in the same array index
							if(all[i].compareTo(some[i]) == 0 && (size2.length() == size1.length())){
								isThere = true;
							}							
						}
						else{
							//size files are to compare the size of each file and make sure they are the same
							File size2 = new File("\\\\"+computerToCopyTo[computerToCopyToInt]+"\\jewel\\"+some[i]);
							//testing if the files are in the same array index
							if(all[i].compareTo(some[i]) == 0 && (size2.length() == size1.length())){
								isThere = true;
							}							
						}
					
						//if file is not in  same position checking the rest of some
						if(!isThere){
							for(int j=0;j<some.length;j++){
								if(all[i].compareTo(some[j]) == 0){
									if(computerToCopyToInt > MaxNumberOfComputers){
										File size3 = new File(directory + "\\" + some[j]);
										if(size1.length() == size3.length()){
											isThere = true;
										}
									}
									else{
										File size3 = new File("\\\\"+computerToCopyTo[computerToCopyToInt]+"\\jewel\\"+some[j]);							
										if(size1.length() == size3.length()){
											isThere = true;
										}										
									}
								}
							}
						}
						if(!isThere){
							for(int j=0;j<some.length;j++){
								if(all[i].compareTo(some[j]) == 0){
									if(computerToCopyToInt > MaxNumberOfComputers){
										File size4 = new File(directory + "\\" + some[j]);
										if(size1.length() == size4.length()){
											isThere = true;
										}									
									}
									else{
										File size4 = new File("\\\\"+computerToCopyTo[computerToCopyToInt]+"\\jewel\\"+some[j]);								
										if(size1.length() == size4.length()){
											isThere = true;
										}									
									}
								}
							}					
						}
					}
					if(!isThere){
						missing[missingCount] = new String(all[i]);
						missingCount++;
						//System.out.println("Missing file: "+all[i]+" i = "+i);
					}
				}
				isThere = false;
			}
			//copying over missing to missing2 so the method will only return a string all with the length - 1 files missing
			String[] missing2 = new String[missingCount];
			for(int i=0;i<missingCount;i++){
				missing2[i] = new String(missing[i]);
			}
			endMissing = System.currentTimeMillis();
			System.out.println("Total time for missingFiles method is "+((endMissing - startMissing)/1000)+" seconds");
			System.out.println("Missing count = "+missingCount);
			
			return missing2;
		}
	public void menu(){
		try{
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("");
			System.out.println("1 to copy to videofactory");
			System.out.println("2 to copy to edsfactory");
			System.out.println("3 to copy to mikes-laptop");
			System.out.println("4 to copy to winston");
			System.out.println("5 to copy to musicfactory");
			System.out.println("6 to manual enter a computer name");
			System.out.println("7 to enter a local directory");
			System.out.println("You may enter 1 3 4 and that will run m3uCreation on all three");
			System.out.println("");
			String tmpS = stdin.readLine();
			StringTokenizer token = new StringTokenizer(tmpS);
			while(token.hasMoreTokens()){
				tmpS = token.nextToken();				
				if(tmpS.compareTo("1") == 0){
					computerToCopyTo[numberOfComputersToCopyTo] = new String("videofactory");
					numberOfComputersToCopyTo++;
				}
				else if(tmpS.compareTo("2") == 0){
					computerToCopyTo[numberOfComputersToCopyTo] = new String("edscomputer");
					numberOfComputersToCopyTo++;
				}
				else if(tmpS.compareTo("3") == 0){
					computerToCopyTo[numberOfComputersToCopyTo] = new String("mikeslaptop");
					numberOfComputersToCopyTo++;
				}	
				else if(tmpS.compareTo("4") == 0){
					computerToCopyTo[numberOfComputersToCopyTo] = new String("winston");
					numberOfComputersToCopyTo++;
				}
				else if(tmpS.compareTo("5") == 0){
					computerToCopyTo[numberOfComputersToCopyTo] = new String("musicfactory");
					numberOfComputersToCopyTo++;
				}				
				else if(tmpS.compareTo("6") == 0){
					System.out.println("Please enter the name.");
					computerToCopyTo[numberOfComputersToCopyTo] = new String(stdin.readLine());
					numberOfComputersToCopyTo++;
				}
				else if(tmpS.compareTo("7") == 0){
					directoryFlag = true;
					System.out.println("Please enter the absolute path.");
					directory = new String(stdin.readLine());
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage()+"Here");
		}
		System.out.println("");	
	}
}