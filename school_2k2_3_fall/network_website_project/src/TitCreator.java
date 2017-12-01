/***************************************************************
 ** Michael DePouw
 ** Date:
 ***************************************************************/

import java.io.*;
import java.util.*;

public class TitCreator {

	private String inputDirectory;

	private String prefix;

	//auto == 1 manual == 2
	private String autoORmanual;

	private boolean containsHypen;

	public TitCreator() {

		autoORmanual = null;
		prefix = "NOPREFIX";
		containsHypen = false;
	}

	public void setDirect(String dIn) {
		inputDirectory = dIn;
	}

	public void setPrefix(String preIn) {
		prefix = preIn;
	}

	public void setAorM(String in) {
		autoORmanual = in;
	}

	public boolean ContainsHypen() {
		return containsHypen;
	}

	public void create() {

		try {

			String tmpS = new String();

			File fileIn = new File(inputDirectory);

			//listing directories
			String[] directories = fileIn.list();

			sortStrings sort = new sortStrings();
			sort.bubbleSort(directories);

			if (directories != null) {

				for (int i = 0; i < directories.length; i++) {

					File testIsDirectory = new File(inputDirectory + "\\" + directories[i]);

					if (testIsDirectory.isDirectory()) {

						File test = new File(inputDirectory + "\\" + directories[i] + "\\" + directories[i] + ".tit");

						//making sure the tit file doesn't all ready exist under the directory name
						if (!test.exists()) {

							if (autoORmanual.compareTo("AUTO") == 0) {

								containsHypen = false;

								for (int j = 0; j < directories[i].length(); j++) {

									if (directories[i].charAt(j) == '-') {

										containsHypen = true;
									}
								}
								if (containsHypen) {

									StringTokenizer tokens = new StringTokenizer(directories[i], "-");
									String artist = new String(tokens.nextToken());
									String albumTitle = new String(tokens.nextToken());
									String containsSD = new String(testIsDirectory.getAbsolutePath());
									
									if(containsSD.endsWith("_SD")){
										tmpS = new String(artist + " - " + albumTitle + " SD");
									}
									else{
										tmpS = new String(artist + " - " + albumTitle);
									}
								}
								else {

									System.out.println("\n*****Error*****");
									System.out.println("Please add a hypen to " + directories[i]);
									containsHypen = false;
									return;
								}
							}
							else if (autoORmanual.compareTo("MANUAL") == 0) {

								System.out.println("Directory: " + directories[i]);

								BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
								tmpS = in.readLine();
							}
							else {
								System.out.println("Invalid number.");
							}

							String formatedString = new String();

							//getting rid of ' and replacing with &rsquo;
							for (int j = 0; j < tmpS.length(); j++) {

								if (tmpS.charAt(j) == '\'') {
									formatedString += "&rsquo;";
								}
								else if (tmpS.charAt(j) == '_') {
									formatedString += " ";
								}
								else {
									formatedString += tmpS.charAt(j);
								}
							}

							//printing title to a file
							PrintWriter pWriter = new PrintWriter(new FileOutputStream(inputDirectory + "\\" + directories[i] + "\\" + directories[i] + ".tit"), true);

							//if there is a prefix print it else just print temp string 2
							if (prefix.compareTo("NOPREFIX") != 0) {
								pWriter.println(prefix + " " + formatedString);
							}
							else {
								pWriter.println(formatedString);
							}
							pWriter.close();
						}
						else {

							containsHypen = true;
						}
					}
				} //end for loop
			}
			else {

				System.out.println("Tit.create(): Directory == NULL");
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public void menu() {

		try {
			System.out.println();
			System.out.println("1 for new music");
			System.out.println("2 for new dj freckles");
			System.out.println("3 for new collaborations");
			System.out.println("4 for new instrumentals");
			System.out.println("5 for music videos that need to be posted");
			System.out.println("6 for your own path");
			System.out.println();

			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
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
				inputDirectory = "\\\\Mikescomputer\\Music_Videos_Need_To_Be_Posted";
			}
			else if (tmpI == 6) {

				System.out.println("Please enter an absolute path. example c:\\my_music or \\\\musicfactory\\music");
				System.out.println();

				inputDirectory = stdin.readLine();
			}
			System.out.println("Would you like a prefix? 1 for yes anything else for no");
			String tmpS = stdin.readLine();
			if (tmpS.compareTo("1") == 0) {
				System.out.println("Please enter your prefix in the format: prefix -");
				prefix = stdin.readLine();
			}
			else {
				prefix = "NOPREFIX";
			}

			System.out.println("1 for automatic tit creation using a hypen as the delimiter");
			System.out.println("2 for manual enetering of artist and album title");

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			tmpS = in.readLine();

			try {

				int tmpI2 = Integer.parseInt(tmpS);

				if (tmpI == 1) {
					autoORmanual = "auto";
				}
				else {
					autoORmanual = "manual";
				}
			}
			catch (NumberFormatException e) {

				System.out.println(e.getMessage());
			}

			create();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
	}
}