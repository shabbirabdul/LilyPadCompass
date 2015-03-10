package cc.playmc.lilypadcompass;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class JCC
{
  public static void main (String [] args) throws FileNotFoundException, IOException
  {
    BufferedReader in = null;

    // A switch used to turn on and off debugging.
    boolean debug = false;

    // The total number of legitimate lines of code we have counted.
    int number_lines = 0;

    // The total number of legitimate comments we have counted.
    int number_comments = 0;

    // A flag indicating if we are inside of a comment or not.
    boolean inside_comment = false;

    // The current line of code we are examining.
    String s;

    // Construct a proper stream from which we can read information.
    
    if (args.length == 1)
      in = new BufferedReader(new FileReader(args[0]));
    else
      in = new BufferedReader(new InputStreamReader(System.in));

    // Read in lines of code from the stream until we reach the end
    // of the input.
  
    while ((s = in.readLine()) != null) {
      int pre_trim_length = s.length();
      int trim_length = (new String(s)).trim().length();

      if (debug) {
        System.err.println("Line read: " + s);
        System.err.println("\tpre_trim_length: " + pre_trim_length);
        System.err.println("\ttrim_length: " + trim_length);
      }
      
      // if line has "/*" followed by "*/" then bump # of comments
      if ((s.indexOf("/*") != -1) && 
          (s.indexOf("*/") != -1)) {
        if (debug)
          System.err.println("\t\tcontained comment");
        if (s.trim().length() > 6)
          if (debug) {
            System.err.println 
              ("\t\tcontained comment of proper length");
            number_comments ++;
          }
      } else if (s.indexOf("//") != -1) {
        // if line has "//" bump number of comments
        if (debug)
          System.err.println("\t\tdouble-slash comment");
        if (s.trim().length() > 3) {
          if (debug)
            System.err.println 
              ("\t\tdouble-slash comment of proper length");
          number_comments ++;
        }
      } else if ((s.indexOf("/*") != -1) && (s.indexOf("*/") == -1)) {
        // if we just see a "/*" then we are moving inside a comment
        inside_comment = true;
        if (debug)
          System.err.println("\t\tstarting comment");
        if (s.trim().length() > 3) {
          number_comments ++;
          if (debug)
            System.err.println("\t\tstarting comment of proper length");
        }
      } else if ((s.indexOf("/*") == -1) && (s.indexOf("*/") != -1)) {
        // if we are inside a comment and we see a "*/" then we end it
        if (debug)
          System.err.println("\t\tending a multi-line comment");
        inside_comment = false;
        if (s.trim().length() > 3) {
          if (debug)
            System.err.println("\t\tending a multi-line comment" +
                               " of proper length");
          number_comments ++;
        }
      } else if (inside_comment) {
        // number number of comments if we are inside a comment and not
        // on an all whitespace line
        if (debug)
          System.err.println("\t\tinside multi-line comment");
        if (s.trim().length() > 3) {
          number_comments ++;
          if (debug)
            System.err.println("\t\tinside multi-line comment " +
                               "of proper length");
        }
      }
      
      // bump number of lines only if we are not on a line of all whitespace
      
      if (s.trim().length() > 3) {
        number_lines ++;
        if (debug)
          System.err.println("\t\tline worth counting");
      } else if (debug)
        System.err.println("\t\tline NOT worth counting");
    }
    
    // print out total number of lines
    System.out.println("Total number of lines of code: " + number_lines);
    // print out total number of comments
    System.out.println("Total number of lines of comments: " + number_comments);
    // print out ratio comments/lines
    System.out.println("Ratio (comments/code): " + (int)((float)number_comments/number_lines*100+0.5) + "%");
  }
}
