public class QSort
{
public static int [] a = { 3, 1, 0, 23, 6, 5, 9, 8, 15, 4, 2 } ;

public static void main( String[] args )
{
System.out.print( "Before: " ) ;
for ( int i = 0 ; i < a.length ; i++ )
System.out.print( a[i] + " " ) ;
System.out.println() ;
quicksort( a ) ;
System.out.print( "After: " ) ;
for ( int i = 0 ; i < a.length ; i++ )
System.out.print( a[i] + " " ) ;
System.out.println() ;
}

private static void quicksort(int[] a, int low, int high) 
{
int swap, tempSwap;
int tempHigh = high; int tempLow = low;

            // Terminating statement
if( low >= high ) 
return;

            // Partition in place method
while(tempLow != tempHigh) 
{
while (a[tempLow] <= a[high]) 
tempLow += 1;
while (a[tempHigh] >= a[high]) 
tempHigh -= 1;
if (tempHigh > tempLow) {
tempSwap = a[tempLow];
a[tempLow] = a[tempHigh];
a[tempHigh] = tempSwap;
}
}
swap = a[high];
a[high] = a[tempLow];
a[tempLow] = swap;

            // Quicksort left and right
quicksort(a,0,tempLow-1);
quicksort(a,tempLow+1,high);
}

public static void quicksort(int[] a){        
quicksort(a,0,a.length-1);
}
}
