// Arup Guha
// 3/27/03
// Solution to COP 3530 Spring 2003 Recitation Assignment #6
// Implements both a recursive and dynamic programming solution
// to the subset sum problem.

public class SubsetSum {

  public static boolean subsetSum(int[] values, int target) {
    return subsetSumHelp(values, target, 0);
  }

  public static boolean subsetSumHelp(int[] values, int target,
				      int startindex) {

    // Empty set's elements sum to 0 - return true.
    if (target == 0)
      return true;

    // Considering an empty set and our target is non-zero.
    if (startindex >= values.length)
      return false;
   
    // Check both possibilities of adding in values[startindex] and
    // not doing so into the subset.
    return subsetSumHelp(values, target, startindex+1) ||
	   subsetSumHelp(values, target-values[startindex], startindex+1);
 
  }

  public static boolean subsetSumDyn(int[] values, int target) {
  
    // Set up array storing which subsets are found.
    boolean[] subsets = new boolean[target+1];
    subsets[0] = true;
    for (int i=1; i<=target; i++)
      subsets[i] = false;

    // Consider each element in values one at a time.
    for (int i=0; i<values.length; i++) {

      // Update a subset sum found based on the current value.
      for (int j=target; j>=values[i]; j--) {
        if (subsets[j-values[i]])
          subsets[j] = true;
      }
    }
    return subsets[target];
  }
  
  public static void main(String[] args) {

    int [] vals = new int[5];
    vals[0] = 1; vals[1] = 3; vals[2] = 7; vals [3] = 30; vals[4] = 100;

    // Small test of both methods.
    for (int i=1; i<142; i++) {
      if (subsetSum(vals,i))
        System.out.print("Subset Rec found sum = "+i);
      if (subsetSumDyn(vals,i))
        System.out.println(" Subset Dyn found sum = "+i);
    }       
  }
}
