public class change {

  public static void main(String [] args) {
    System.out.println(makeChangedyn(10, 25));
  }

  public static int makeChangedyn(int n, int d) {

      // Take care of simple cases.
      if (n < 0)
          return 0;
      else if ((n>=0) && (n < 5))
          return 1;
    
       // Build table here.
      else {

           int[] denominations = {1, 5, 10, 25};
           int[][] table = new int[4][n+1];

           // Initialize table
           for (int i=0; i<n+1;i++)
               table[0][i] = 1;
           for (int i=0; i<5; i++) {
               table[1][i] = 1;
               table[2][i] = 1;
               table[3][i] = 1;
           }
           for (int i=5;i<n+1;i++) {
               table[1][i] = 0;
               table[2][i] = 0;
               table[3][i] = 0;
           }

           // Fill in table, row by row. 
           for (int i=1; i<4; i++) {
                for (int j=5; j<n+1; j++) {
                    for (int k=0; k<=i; k++) {
                        if ( j >= denominations[k])
                             table[i][j] += table[k][j - denominations[k]];
                    } 
                }
            }        
            return table[lookup(d)][n]; 
       }
  }

  public static int lookup(int d) {
    if (d==1) return 0;
    if (d==5) return 1;
    if (d==10) return 2;
    if (d==25) return 3;
    return 0;
  }
}
