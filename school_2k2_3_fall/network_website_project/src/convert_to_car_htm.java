/***************************************************************

** Michael DePouw

** Date:7-27-03

***************************************************************/

import java.io.*;
import java.util.*;

public class convert_to_car_htm{

	public String[] filesMV;
	public String[] filesMV_Car;

	public convert_to_car_htm(){


		filesMV = new String[5];

		filesMV[0] = "\\\\musicfactory\\network_website\\music_videos_#-c.htm";
		filesMV[1] = "\\\\musicfactory\\network_website\\music_videos_d-h.htm";
		filesMV[2] = "\\\\musicfactory\\network_website\\music_videos_i-m.htm";
		filesMV[3] = "\\\\musicfactory\\network_website\\music_videos_n-s.htm";
		filesMV[4] = "\\\\musicfactory\\network_website\\music_videos_t-z.htm";
		filesMV[5] = "\\\\musicfactory\\network_website\\music_videos_new.htm";

		filesMV_Car = new String[5];

		filesMV_Car[0] = "\\\\musicfactory\\network_website\\music_videos_#-c_car.htm";
		filesMV_Car[1] = "\\\\musicfactory\\network_website\\music_videos_d-h_car.htm";
		filesMV_Car[2] = "\\\\musicfactory\\network_website\\music_videos_i-m_car.htm";
		filesMV_Car[3] = "\\\\musicfactory\\network_website\\music_videos_n-s_car.htm";
		filesMV_Car[4] = "\\\\musicfactory\\network_website\\music_videos_t-z_car.htm";
		filesMV_Car[5] = "\\\\musicfactory\\network_website\\music_videos_new_car.htm";
	}

	public void convert(){

		try {
			for (int i = 0; i < filesMV.length; i++) {

				BufferedReader currentFile = new BufferedReader(new FileReader(new File(filesMV[i])));

				PrintWriter currentFile_Car = new PrintWriter(new FileWriter(new File(filesMV_Car[i])));

				while (currentFile.ready()) {

				}

			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void menu(){

		convert();
	}
}