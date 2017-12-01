// Arup Guha
// 9/26/03
// The methods below solve the longest common subsequence problem
// recursively and using dynamic programming
import java.io.*;

public class LCS {


  public static void main(String [] args) throws IOException {

    System.out.println("LCS = "+lcsrec("GTATATATATACC","GTTCCTAATA"));
    System.out.println("LCS = "+lcsrec2("GTATATATATACC","GTTCCTAATA"));
    System.out.println("LCS = "+lcsdyn("GTATATATATACC","GTTCCTAATA"));
  }


  // Precondition: Both x and y are non-empty strings.
  public static int lcsrec(String x, String y) {

    // If one of the strings has one character, search for that character
    // in the other string and return the appropriate answer.
    if (x.length() == 1) 
      return find(x.charAt(0), y);
    if (y.length() == 1)
      return find(y.charAt(0), x);
      
    // Solve the problem recursively.

    // Corresponding beginning characters match.
    if (x.charAt(0) == y.charAt(0))
      return 1+lcsrec(x.substring(1), y.substring(1));

    // Corresponding characters do not match.
    else 
      return max(lcsrec(x,y.substring(1)), lcsrec(x.substring(1),y));

  }

  // Note: This second recursive version, though more cumbersome in code
  //       because of how the substring method works more accurately
  //       mirrors the algorithms used in the dynamic programming method
  //       below, since this uses LCS's of prefixes to compute LCS's
  //       of longer strings.
  // Precondition: Both x and y are non-empty strings.
  public static int lcsrec2(String x, String y) {

    // If one of the strings has one character, search for that character
    // in the other string and return the appropriate answer.
    if (x.length() == 1) 
      return find(x.charAt(0), y);
    if (y.length() == 1)
      return find(y.charAt(0), x);
      
    // Solve the problem recursively.

    // Corresponding last characters match.
    if (x.charAt(x.length()-1) == y.charAt(y.length()-1))
      return 1+lcsrec2(x.substring(0,x.length()-1), 
		       y.substring(0,y.length()-1));

    // Corresponding last characters do not match.
    else 
      return max(lcsrec2(x,y.substring(0,y.length()-1)), 
		 lcsrec2(x.substring(0,x.length()-1),y));

  }

  // Precondition: Both x and y are non-empty strings.
  public static int lcsdyn(String x, String y) {

    int i,j;
    int lenx = x.length();
    int leny = y.length();
    int[][] table = new int[lenx+1][leny+1];

    // Initialize table that will store LCS's of all prefix strings.
    // This initialization is for all empty string cases.
    for (i=0; i<=lenx; i++) 
      table[i][0] = 0;
    for (i=0; i<=leny; i++)
      table[0][i] = 0;
    
    // Fill in each LCS value in order from top row to bottom row,
    // moving left to right.
    for (i = 1; i<=lenx; i++) {
      for (j = 1; j<=leny; j++) {

        // If last characters of prefixes match, add one to former value.
        if (x.charAt(i-1) == y.charAt(j-1))
          table[i][j] = 1+table[i-1][j-1];

        // Otherwise, take the maximum of the two adjacent cases.
        else
          table[i][j] = max(table[i][j-1], table[i-1][j]);
      }
    }
    return table[lenx][leny];
  }

  // Returns 1 if c is contained in x, and 0 otherwise.
  public static int find(char c, String x) {
    
    for (int i=0; i<x.length(); i++)
      if (c == x.charAt(i))
        return 1;

    return 0;
  }

  // Returns the greater of x and y.
  public static int max(int x, int y) {
    if (x > y)
      return x;
    else
      return y;
  }
}

