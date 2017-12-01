
import java.io.*;

public class AllCreator{
	
	public String[] filesALBUMS;
	public String[] filesALBUMS_Car;
	public String[] filesMV;
	public String[] filesMV_Car;
	private String[] filesMOVIES;
	private String[] filesMOVIES_Car;
	
	public AllCreator(){
		
		filesALBUMS = new String[5];
		
		filesALBUMS[0] = "\\\\musicfactory\\network_website\\albums_#-c.htm";
		filesALBUMS[1] = "\\\\musicfactory\\network_website\\albums_d-h.htm";
		filesALBUMS[2] = "\\\\musicfactory\\network_website\\albums_i-m.htm";
		filesALBUMS[3] = "\\\\musicfactory\\network_website\\albums_n-s.htm";
		filesALBUMS[4] = "\\\\musicfactory\\network_website\\albums_t-z.htm";
		
		filesALBUMS_Car = new String[5];
		
		filesALBUMS_Car[0] = "\\\\musicfactory\\network_website\\albums_#-c_car.htm";
		filesALBUMS_Car[1] = "\\\\musicfactory\\network_website\\albums_d-h_car.htm";
		filesALBUMS_Car[2] = "\\\\musicfactory\\network_website\\albums_i-m_car.htm";
		filesALBUMS_Car[3] = "\\\\musicfactory\\network_website\\albums_n-s_car.htm";
		filesALBUMS_Car[4] = "\\\\musicfactory\\network_website\\albums_t-z_car.htm";	
		
		filesMV = new String[5];
		
		filesMV[0] = "\\\\musicfactory\\network_website\\music_videos_#-c.htm";
		filesMV[1] = "\\\\musicfactory\\network_website\\music_videos_d-h.htm";
		filesMV[2] = "\\\\musicfactory\\network_website\\music_videos_i-m.htm";
		filesMV[3] = "\\\\musicfactory\\network_website\\music_videos_n-s.htm";
		filesMV[4] = "\\\\musicfactory\\network_website\\music_videos_t-z.htm";
		
		filesMV_Car = new String[5];
		
		filesMV_Car[0] = "\\\\musicfactory\\network_website\\music_videos_#-c_car.htm";
		filesMV_Car[1] = "\\\\musicfactory\\network_website\\music_videos_d-h_car.htm";
		filesMV_Car[2] = "\\\\musicfactory\\network_website\\music_videos_i-m_car.htm";
		filesMV_Car[3] = "\\\\musicfactory\\network_website\\music_videos_n-s_car.htm";
		filesMV_Car[4] = "\\\\musicfactory\\network_website\\music_videos_t-z_car.htm";		

		filesMOVIES = new String[5];
		
		filesMOVIES[0] = "\\\\musicfactory\\network_website\\movies_#-c.htm";
		filesMOVIES[1] = "\\\\musicfactory\\network_website\\movies_d-h.htm";
		filesMOVIES[2] = "\\\\musicfactory\\network_website\\movies_i-m.htm";
		filesMOVIES[3] = "\\\\musicfactory\\network_website\\movies_n-s.htm";
		filesMOVIES[4] = "\\\\musicfactory\\network_website\\movies_t-z.htm";
		
		filesMOVIES_Car = new String[5];
		
		filesMOVIES_Car[0] = "\\\\musicfactory\\network_website\\movies_#-c_car.htm";
		filesMOVIES_Car[1] = "\\\\musicfactory\\network_website\\movies_d-h_car.htm";
		filesMOVIES_Car[2] = "\\\\musicfactory\\network_website\\movies_i-m_car.htm";
		filesMOVIES_Car[3] = "\\\\musicfactory\\network_website\\movies_n-s_car.htm";
		filesMOVIES_Car[4] = "\\\\musicfactory\\network_website\\movies_t-z_car.htm";		
	}
	
	public void createALBUMSAll(){
		
		try{
			String tmpS = new String();
			//creating albums_all.htm
			PrintWriter pwriter = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\albums_all.htm")));
	
			pwriter.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesALBUMS.length;i++){
			
				BufferedReader fileReader = new BufferedReader(new FileReader(filesALBUMS[i]));
				
				while(fileReader.ready()){
					
					tmpS = fileReader.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReader.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriter.println(tmpS);
							tmpS = fileReader.readLine().trim();
						}
					}
				}
			}
			pwriter.println("</body>\n</html>");
			pwriter.close();
			
			//creating albums_all_car.htm
			PrintWriter pwriterCar = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\albums_all_car.htm")));
	
			pwriterCar.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesALBUMS_Car.length;i++){
			
				BufferedReader fileReaderCar = new BufferedReader(new FileReader(filesALBUMS_Car[i]));
				
				while(fileReaderCar.ready()){
					
					tmpS = fileReaderCar.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReaderCar.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriterCar.println(tmpS);
							tmpS = fileReaderCar.readLine().trim();
						}
					}
				}
			}
			
			pwriterCar.println("</body>\n</html>");
			pwriterCar.close();		
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());		
		}		
	}
	//creating music_videos_all.htm
	public void createMVAll(){
		
		try{
			String tmpS = new String();
			
			PrintWriter pwriterMV = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\music_videos_all.htm")));
	
			pwriterMV.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesMV.length;i++){
			
				BufferedReader fileReaderCar = new BufferedReader(new FileReader(filesMV[i]));
				
				while(fileReaderCar.ready()){
					
					tmpS = fileReaderCar.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReaderCar.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriterMV.println(tmpS);
							tmpS = fileReaderCar.readLine().trim();
						}
					}
				}
			}
			
			pwriterMV.println("</body>\n</html>");
			pwriterMV.close();		
	
			//creating music_videos_all_car.htm
			PrintWriter pwriterMV_Car = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\music_videos_all_car.htm")));
	
			pwriterMV_Car.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesMV_Car.length;i++){
			
				BufferedReader fileReaderCar = new BufferedReader(new FileReader(filesMV_Car[i]));
				
				while(fileReaderCar.ready()){
					
					tmpS = fileReaderCar.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReaderCar.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriterMV_Car.println(tmpS);
							tmpS = fileReaderCar.readLine().trim();
						}
					}
				}
			}
			
			pwriterMV_Car.println("</body>\n</html>");
			pwriterMV_Car.close();		
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());		
		}		
	}
	//creating movies_all.htm
	public void createMOVIESAll(){
		
		try{
		
			String tmpS = new String();
			
			PrintWriter pwriterMV = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\movies_all.htm")));
	
			pwriterMV.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesMOVIES.length;i++){
			
				BufferedReader fileReaderCar = new BufferedReader(new FileReader(filesMOVIES[i]));
				
				while(fileReaderCar.ready()){
					
					tmpS = fileReaderCar.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReaderCar.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriterMV.println(tmpS);
							tmpS = fileReaderCar.readLine().trim();
						}
					}
				}
			}
			
			pwriterMV.println("</body>\n</html>");
			pwriterMV.close();		
	
			//creating music_videos_all_car.htm
			PrintWriter pwriterMV_Car = new PrintWriter(new FileWriter(new File("\\\\musicfactory\\network_website\\movies_all_car.htm")));
	
			pwriterMV_Car.println("<html>\n<head>\n<link REL=\"STYLESHEET\" HREF=\"style.css\" TYPE=\"text/css\">\n</head>\n<body background=\"images/t079big.jpg\" bgproperties=\"fixed\">");
			
			for(int i=0;i<filesMOVIES_Car.length;i++){
			
				BufferedReader fileReaderCar = new BufferedReader(new FileReader(filesMOVIES_Car[i]));
				
				while(fileReaderCar.ready()){
					
					tmpS = fileReaderCar.readLine().trim();
					
					if(tmpS.compareTo("<!--StartAppend-->") == 0){
					
						tmpS = fileReaderCar.readLine().trim();
						
						while(tmpS.compareTo("<!--EndAppend-->") != 0){
							
							pwriterMV_Car.println(tmpS);
							tmpS = fileReaderCar.readLine().trim();
						}
					}
				}
			}
			
			pwriterMV_Car.println("</body>\n</html>");
			pwriterMV_Car.close();	
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());		
		}		
	}
	public void menu(){
		
		try{
			System.out.println("1 for Albums All creation");
			System.out.println("2 for Music Videos All creation");
			System.out.println("3 for Movies All creation");
			
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			int tmpI = Integer.parseInt(stdin.readLine());
			
			if(tmpI == 1){
				createALBUMSAll();
			}
			else if(tmpI == 2){
				createMVAll();
			}
			else if(tmpI == 3){
				createMOVIESAll();
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());		
		}
	}
}