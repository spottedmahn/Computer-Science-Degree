/***************************************************************
 ** Michael DePouw
 ** COP3503
 ** Assignment Number: Final Project
 ** Date:10/10/02
 ***************************************************************/

/****************Class Description***************************\
 ** This class will create a file with the directory name on a
 ** line and the title on the next line. It will get the title
 ** from the tit file in the directory.
 **************************************************************/

import java.io.*;
import java.util.*;

public class createDirectoryList {
	//the root directory that contains all the directories to be listed
	private String direct;

	public createDirectoryList() {

	}

	public createDirectoryList(String in) {
		direct = in;
	}

	public void setDirect(String in) {
		direct = in;
	}

	public void create() {

		try {
			String[] directories;

			//input directory
			File fileIn = new File(direct);
			//listing all files and directories
			directories = fileIn.list();

			sortStrings sort = new sortStrings();
			sort.bubbleSort(directories);

			//setup for text output file
			FileOutputStream fout = new FileOutputStream("input_for_directory_and_title.txt");
			PrintWriter pwriter = new PrintWriter(fout, true);

			//stepping thru directory array printing directory and then title
			for (int i = 0; i < directories.length; i++) {

				//printing only directories
				File testIsFile = new File(direct + "\\" + directories[i]);

				if (!testIsFile.isFile()) {

					pwriter.println(directories[i]);

					try {
						//reading in title from .tit file
						BufferedReader fIn = new BufferedReader(new FileReader(direct + "\\" + directories[i] + "\\" + directories[i] + ".tit"));
						pwriter.println(fIn.readLine());

					}
					catch (FileNotFoundException e) {
						System.out.println("No .tit file in directory " + directories[i]);
						System.out.println("You will need to add your own title");
					}
				}
			}
			pwriter.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} //end create list method
}
