// Arup Guha
// 1/28/03
// COP 3530 Spring 2003
// Algorithm Analysis Assignment #1 
// Description: The method MostOnes determines which row in the 2
//              dimensional binary array parameter binarytable contains
//		the most 1s in it.

import java.io.*;
import java.util.Random;

public class Ones {

  // Preconditions: binarytable is not null, and stores a valid 2
  //                dimensional integer array with only 0s and 1s,
  // 		    such that on each row all the 1s appear before
  //		    all of the 0s.
  // Postcondition: Will return the index corresponding to the row
  //                with the most number of 1s.
  public static int MostOnes(int[][] binarytable) {

    int width = binarytable[0].length; // Length of each row.
    int height = binarytable.length; // Number of rows to consider.

    int cur_row = 0; // Row currently being examined.
    int high = 0; // Max. number of 1s currently seen.
    int highi = 0; // Index where max. number of 1s is stored.

    // Loop through each row of the table.
    while (cur_row < height) {

      // Search for the last one in the current row.
      // Loop never runs if the number of 1s is less than or
      // equal to the current maximum seen.
      while (high < width && binarytable[cur_row][high] == 1) {
        high++; // Found a new maximum.
        highi = cur_row; // Store the row in which it occurs.
      }

      cur_row++; // Go to the next row.
    }
    return highi; // Return the appropriate row index.
  }

  // Preconditions: Both height and width must be positive.
  // Postconditions: Returns a two dimensional array with dimensions
  //                 heightxwidth with a random number of 1s, followed
  //                 by 0s in each row.
  public static int[][] fillarray(int height, int width) {

    int[][] temp = new int[height][width]; // Array to return.

    Random r = new Random(); // Used to create random values

    // Loop through each row.
    for (int i=0; i<height; i++) {

      // Create a randome number in between 0 and width.
      int numones = Math.abs(r.nextInt())%(width+1);

      // Fill the current row.
      for (int j=0; j<numones; j++)
        temp[i][j] = 1;
      for (int j=numones; j<width; j++)
        temp[i][j] = 0;
    }
    return temp;
  }

  // Precondition: none
  // Postcondition: Prints out the array, labelling each row.
  public static void printarray(int[][] values) {

    for (int i=0; i<values.length; i++) {
      System.out.print("L "+i+"\t");
      for (int j=0; j<values[i].length; j++)
        System.out.print(values[i][j]+" ");
      System.out.println();
    }
  }

  public static void main(String[] args) throws IOException {

    BufferedReader stdin = new BufferedReader
		(new InputStreamReader(System.in));

    // Get array dimensions from user.
    System.out.println("Enter the height of your array.");
    int h = Integer.parseInt(stdin.readLine());

    System.out.println("Enter the width of your array.");
    int w = Integer.parseInt(stdin.readLine());

    // Create the array.
    int[][] one_array = fillarray(h,w);

    // Test the MostOne method.
    printarray(one_array);
    System.out.print("The line with the most ones is ");
    System.out.println(MostOnes(one_array));
  }

}
