/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 4

** Date:10/25/02

***************************************************************/

/**************** Description***************************

**
**
**
**

**************************************************************/


import java.io.*;
import java.util.*;

public class HW4_COP3330_Matrix{
	
	private double [][] a;
	private int rows, cols;
	
	public HW4_COP3330_Matrix(int N, int M){
		rows = N;
		cols = M;
		a = new double[N][M];
	}
	public int getRows(){
		return rows;
	}
	public int getCols(){
		return cols;
	}
	public double getPosition(int i,int j){
		return a[i][j];
	}
	public void setPosition(int i, int j, double value){
		a[i][j] = value;
	}	
	//add method
	public HW4_COP3330_Matrix add(HW4_COP3330_Matrix I, HW4_COP3330_Matrix J){
		HW4_COP3330_Matrix c = new HW4_COP3330_Matrix(I.rows, I.cols);
		//adding matrices
		if(I.rows == J.rows && I.cols == J.cols){
			for(int i=0;i < I.rows;i++){
				for(int j=0;j < I.cols;j++){
				c.a[i][j] = I.a[i][j] + J.a[i][j];
				}
			}
		}
		else{
			return null;
		}
		return c;
	}//end add
	//scalar method
	public HW4_COP3330_Matrix scalar(HW4_COP3330_Matrix I, double d){
		HW4_COP3330_Matrix c = new HW4_COP3330_Matrix(I.rows, I.cols);
		for(int i = 0;i < I.rows;i++){
			for(int j = 0;j < I.cols;j++){
				c.a[i][j] = I.a[i][j] * d;
			}
		}
		return c;
	}//end scalar
	//print method in matrix
	public void Print() {
		for(int i=0;i < a.length;i++){
			for(int j=0;j < a[0].length;j++){
				System.out.print(a[i][j] +" ");
			}
		}
	}
	//multiply method
	public HW4_COP3330_Matrix multiply(HW4_COP3330_Matrix I, HW4_COP3330_Matrix J){
		if(I.cols != J.rows){
			return null;
		}		
		HW4_COP3330_Matrix c = new HW4_COP3330_Matrix(I.rows, J.cols);
		//filling resultant matrix with 0's
		for(int m = 0;m < I.rows;m++){
			for(int n = 0;n < J.cols;n++){
				c.a[m][n] = 0;
			}
		}
		//multiplying matrix
		for(int i = 0;i < I.rows;i++){
			for(int j = 0; j < J.cols;j++){
				for(int k = 0;k < I.cols;k++){
					c.a[i][j] += I.a[i][k] * J.a[k][j];	
				}
			}
		}
	return c;
	}
}