import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The CritterSitterApp is the driver class that creates CritterSitterGame as well
 * as executes the program.
 *
 * @author Jasmine Ou and Laura Wong
 * @version 1 01.28.15
 */
public class CritterSitterApp
{
  
  /** Description of main(String [] args)
    * This method calls the CritterSitterApp constructor to
    * create the application.
    *
    * @param args [ ]  String array that allows command line
    * parameters to be used when executing the program.
    */
  public static void main (String[] args)
  {
    JFrame frame = new CritterSitterGame();
    frame.show();
  }
}
