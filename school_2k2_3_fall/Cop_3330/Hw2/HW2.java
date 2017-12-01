/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 2

** Date:10/2/02

***************************************************************/

/****************Program Description: 3 Ant simulation***************************

** This program must be run with a command line argument. That argument will
** be the distance for the ants to travel.
** The program will expect three lines of integers with 3 integers per line.
** The metablolism is the first int, the agility is the second int,
** the stamina is the third int.  Then the program will expect either a
** travel distance for each ant or a command.  To input a travel distance
** just enter 3 ints, ex. 10 3 4, this will tell the first ant to go 10 units
** the second ant 3 units and the third ant to go 4 units.  There are 3 commands
** you can give.  The three commands are status, squash and energy.  Status will
** tell you how far each ant is.  Squash will squash an ant and remove him from the race, ex
** squash 1, this will kill the first ant.  Energy will increase the maximum
** energy of an ant, ex. energy 2 10 where 2 is the ant and 10 is the amount
** to be incremented.
* 
**************************************************************/
import java.io.*;
import Ant.*;
import java.util.*;
//import java.lang.*;

public class HW2{
	
	
	public static void main(String[] args)throws IOException{
		
		double total_distance, tmp_met, tmp_agil, tmp_stam, tmp_speed, temp;
		boolean repeat = true;
		String input;
		Ant ant1 = new Ant();
		Ant ant2 = new Ant();
		Ant ant3 = new Ant();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		total_distance = Integer.parseInt(args[0]);
		
		//reading in the first three lines
		for(int i=1;i<4;i++){
			input = in.readLine();
			StringTokenizer tok = new StringTokenizer(input);
			tmp_met =(double) Integer.parseInt(tok.nextToken());
			tmp_agil =(double) Integer.parseInt(tok.nextToken());
			tmp_stam =(double) Integer.parseInt(tok.nextToken());
			if(i==1){
				ant1.setMet(tmp_met);
				ant1.setAgil(tmp_agil);
				ant1.setOStam(tmp_stam);
				ant1.setStam(tmp_stam);
			}
			else if(i==2){
				ant2.setMet(tmp_met);
				ant2.setAgil(tmp_agil);
				ant2.setOStam(tmp_stam);
				ant2.setStam(tmp_stam);
			}	
			else if(i==3){
				ant3.setMet(tmp_met);
				ant3.setAgil(tmp_agil);
				ant3.setOStam(tmp_stam);
				ant3.setStam(tmp_stam);
			}			
		}//end for loop
				
		while(repeat){
		outer:
			//if race is over then print status
			if(ant1.getDist() >= total_distance || ant2.getDist() >= total_distance || ant3.getDist() >= total_distance){
				
				if(ant1.getStatus()){
					System.out.println("Ant1 "+ant1.getDist());
				}
				else{
					System.out.println("Ant1 Squashed");
				}
				if(ant2.getStatus()){
					System.out.println("Ant2 "+ant2.getDist());
				}
				else{
					System.out.println("Ant2 Squashed");
				}
				if(ant3.getStatus()){
					System.out.println("Ant3 "+ant3.getDist());
				}
				else{
					System.out.println("Ant3 Squashed");
				}		
				repeat = false;
				break;
			}
			//race is still going	
			else {
		
				input = in.readLine();
				StringTokenizer tok = new StringTokenizer(input);
				//squash function
				if(input.startsWith("squas")){
					if(input.charAt(9) == '1')
						ant1.setStatus();//setStatus sets the status to false or squashed
					if(input.charAt(9) == '2')
						ant2.setStatus();
					if(input.charAt(9) == '3')
						ant3.setStatus();
				}
				//status function
				else if(input.startsWith("stat")){
					if(ant1.getStatus()){
						System.out.println("Ant1 "+ant1.getDist());
					}
					else{
						System.out.println("Ant1 Squashed");
					}
					if(ant2.getStatus()){
						System.out.println("Ant2 "+ant2.getDist());
					}
					else{
						System.out.println("Ant2 Squashed");
					}
					if(ant3.getStatus()){
						System.out.println("Ant3 "+ant3.getDist());
					}
					else{
						System.out.println("Ant3 Squashed");
					}
				}
				//energy function
				else if(input.startsWith("ener")){
						if(input.charAt(7) == '1'){
						temp = input.charAt(9) - 48;
						//if energy is to increment is more than 1 digit
						if(input.length() > 10)
							temp = temp*10 + input.charAt(10) -48;
						//if energy is to increment is more than 2 digits
						if(input.length() > 11)
							temp = temp*10 + input.charAt(10) -48;
						//if energy is to increment is more than 3 digits
						if(input.length() > 12)
							temp = temp*10 + input.charAt(10) -48;
						ant1.setOStam(temp + ant1.getOStam());
						
					}
					if(input.charAt(7) == '2'){
						temp = input.charAt(9) - 48;
						if(input.length() > 10)
							temp = temp*10 + input.charAt(10) -48;
						if(input.length() > 11)
							temp = temp*10 + input.charAt(10) -48;
						if(input.length() > 12)
							temp = temp*10 + input.charAt(10) -48;
						ant2.setOStam(temp + ant2.getOStam());
					}
					if(input.charAt(7) == '3'){
						temp = input.charAt(9) - 48;
						if(input.length() > 10)
							temp = temp*10 + input.charAt(10) -48;
						if(input.length() > 11)
							temp = temp*10 + input.charAt(10) -48;
						if(input.length() > 12)
							temp = temp*10 + input.charAt(10) -48;				
						ant3.setOStam(temp + ant3.getOStam());
					}
						
				}
				//travel a distance
				else{
					tmp_speed = Integer.parseInt(tok.nextToken());
					ant1.setDist(tmp_speed, ant1.getAgil());
					tmp_speed = Integer.parseInt(tok.nextToken());
					ant2.setDist(tmp_speed, ant2.getAgil());
					tmp_speed = Integer.parseInt(tok.nextToken());
					ant3.setDist(tmp_speed, ant3.getAgil());					
					if(ant1.getDist() >= total_distance){
								break outer;
					}
					ant1.redStam();
					if(ant1.getStam() <=0){
						ant1.penDist();
						ant1.setStam(ant1.getOStam());
					}
					if(ant2.getDist() >= total_distance){
								break outer;
					}					
					ant2.redStam();
					if(ant2.getStam() <= 0){
						ant2.penDist();
						ant2.setStam(ant2.getOStam());
					}					
					if(ant3.getDist() >= total_distance){
								break outer;
					}					
					ant3.redStam();
					if(ant3.getStam() <=0){
						ant3.penDist();
						ant3.setStam(ant3.getOStam());
					}					
				
				}
			}
		}//end while
	}
}
