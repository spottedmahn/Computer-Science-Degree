import java.io.*;

public class Node {

     public String word;
     public Node next;

     public Node(String w) {
          word = w;
          next = null;
     }

     public Node(String w, Node n) {
          word = w;
          next = n;
     }
}
