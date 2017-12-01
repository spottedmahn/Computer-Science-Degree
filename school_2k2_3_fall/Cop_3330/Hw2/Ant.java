/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 2

** Date:10/2/02

***************************************************************/


import java.io.*;

public class Ant{
	private double Met, Agil, Stam, Dist, OStam;
	private boolean Status;
	public Ant(){
			
		Met=0;
		Agil=0;
		Stam=0;
		Dist=0;
		OStam=0;
		Status=true;
	}
	
	public Ant(double Met2, double Agil2, double Stam2){
		Met=0;
		Agil=0;
		Stam=0;
		
	}
	public double getMet(){
		return Met;
	}
	public double getAgil(){
		return Agil;
	}
	public double getStam(){
		return Stam;
	}
	public double getDist(){
		return Dist;
	}
	public double getOStam(){
		return OStam;
	}
	public boolean getStatus(){
		return Status;
	}
	public void setMet(double Met3){
		Met = Met3;
	}
	public void setAgil(double Agil3){
		Agil = Agil3;
	}
	public void setStam(double Stam4){
		Stam = Stam4;
	}
	public void redStam(){
		Stam = Stam - Met;
	}
	public void setDist(double speed4, double agil4){
		Dist = Dist + speed4/agil4;
	}
	public void setOStam(double OStam4){
			  OStam = OStam4;
	}
	public void penDist(){
		Dist = Dist - 1;
	}
	public void setStatus(){
		Status = false;
	}
	
}
