/*
 * SimpleIO.java
 *
 */

/**
 *
 * @author  Unknown User
 * @version 
 */
import java.util.*;
import java.io.*;

public class SimpleIO{

    /** Creates new SimpleIO */
    public SimpleIO() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) throws IOException{       

      BufferedReader inf = new BufferedReader(new FileReader(args[0]));
      PrintWriter outf = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));

      String input = inf.readLine();
      //now the current line from the file is in the string, so you can do 
      //processing on it like ususal
      int i =0;
      while ( input != null ) {	

        outf.println("the line of text on line "+i+" is: "+input);
	i++;
        input = inf.readLine();
      }

      outf.close();
    }

}
