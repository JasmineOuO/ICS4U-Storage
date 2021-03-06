import java.awt.print.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * The PrintHighScores class is used to print the high scores on a piece of paper  when the user when clicks the "Print" button in the high scores class.
 * 
 * @author Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: none.
 * Time Spent by Laura Wong: 4 hours.
 * 
 * <p>
 * <b>name: PrintHighScores.java </b>
 * 
 * <p>
 * <b>Instance Variables </b>
 * <p>
 * <b>pj </b> This is the PrinterJob variable used to get the printer job.
 * 
 */
public class PrintHighScores implements Printable
{
  PrinterJob pj = PrinterJob.getPrinterJob();
  
  /**
   * The PrintHighscores constructor is used to call the "acutalPrint" method. 
   */
  public PrintHighScores()
  {
    actualPrint();
  }
  
  /**
   * The print method is used to see if there is a printer connected or not, if not, it returns NO_SUCH_PAGE and if there is it returns PAGE_EXISTS.
   * It also formats how the printed page will look on the actual peice of paper.
   * The first if statement is used to see if pageNum is equal to larger than zero, if it is, it will return NO_SUCH_PAGE.
   * The first  loop is used to output the current scores and the current names, positions and the scores from the HighScores class in a certain location.
   * It also draws the CookieByte logo.
   * 
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>g2d </b> This is a Graphics2D variable that is used to tranlate the information to the printer with the specified format.
   * <p>
   * <b>num </b> This is a String variable that is used to indicate how many high scores are in the file.
   * <p>
   * <b>x </b> This is the variable that is used inside the loops.
   * <p>
   * <b>logo </b> This is the logo of the CookieByte company.
   * 
   * @return int value
   * 
   * @param g   This is the Graphics variable  the drawing on the printer.
   * @param format  This is the specific PageFormat that the high scores will be using.
   * @param pageNum  This is the number of pages.
   * 
   * @throws PrinterException  This is incase there is no printer available.
   */
  public int print (Graphics g, PageFormat format, int pageNum) throws PrinterException
  {
    Graphics2D g2d = (Graphics2D)g;
    
    if (pageNum > 0)
    {
      return NO_SUCH_PAGE; 
    }
    
    g2d.translate ((int) format.getImageableX(), (int) format.getImageableY());
    
    g.drawString ("The High Scores", 240, 100);
    g.drawString ("Position", 100, 150);
    g.drawString ("Username", 220, 150);
    g.drawString ("Score", 340, 150);
    g.drawString ("Difficulty", 460, 150);
    
    String num = CritterSitterApp.highScores.line;
    for (int x = 0; x < Integer.parseInt(num); x++)
    {
      g.drawString (CritterSitterApp.highScores.getCurrentScores(x), 220, 200 + (x * 25)); 
      g.drawString (CritterSitterApp.highScores.getCurrentNames(x), 340, 200 + (x * 25));
      g.drawString (CritterSitterApp.highScores.getCurrentDifficulty(x) + " days", 460, 200 + (x * 25));
      g.drawString("" + (x + 1), 115, 200 + (x *25));
    }
    
    Image logo = new ImageIcon ("images/HighScores/cookieLogo.png").getImage();
    g.drawImage(logo, 0, 0, null);
    
    return PAGE_EXISTS;
  }
  
  /**
   * The actualPrint method actual does the printing.
   * The if statement is used to indicate if ok is true or not.
   * The try block is used to make sure that the print will actually print, if not, it will catch the printerException.
   * 
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>print </b> This is a PrinterJob variable to get the PrinterJob
   * <p>
   * <b>ok </b> This is a boolean variable used to ensure that the printDialog is ok.
   * <p>
   * <b>PrinterException. </b> This is the exception that is made when the print job didn't work properly.
   */
  public void actualPrint()
  {
    PrinterJob print = PrinterJob.getPrinterJob();
    print.setPrintable(this);
    boolean ok = print.printDialog();
    if (ok) 
    {
      try 
      {
        print.print();
      } 
      catch (PrinterException ex) //print job did work right.
      {
      }
    }
  }
}
