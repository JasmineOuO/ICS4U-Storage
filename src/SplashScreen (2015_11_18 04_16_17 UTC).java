import javax.swing.JWindow;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * The SplashScreen class is the class that displays the splash screen animation when the program first executes.
 *
 * @author Jasmine Ou 
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 5 hours (image of the gif).
 * Time Spent by Laura Wong: none.
 * 
 * <p>
 * <b>name: SplashScreen.java </b>
 */
public class SplashScreen extends JWindow
{
  
  /**
   * The SplashScreen constructor sets the size to 778 by 556 pixels, sets the location relative to null and makes it visible. 
   * The try block is to use Thread.sleep for waiting 12 seconds. 
   * After a delay of 12 seconds, the JWindow is disposed of. 
   * <p>
   * <b>e</b>The exception to be caught when using Thread. sleep() in a try block
   */
  public SplashScreen()
  {
    setSize(778,556);
    setLocationRelativeTo(null);
    setVisible(true);
    try
    {
      Thread.sleep(12000);
    }
    catch(Exception e){}
    dispose();
  }
  
  /**
   * This paintComponent method is an overriden method used to draw the gif animation to the JWindow.
   * @param g This is the graphics variable used to draw onto the JWindow.
   */
  public void paint(Graphics g)
  {
    g.drawImage(new ImageIcon ("images/SplashScreen/OriginalRainbow.gif").getImage(), 0, 0,this);
  }
}
