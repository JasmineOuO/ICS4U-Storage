import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
/**
 * The Instructions class displays game instructions to the user using a tabbed pane.
 *
 * @author Jasmine Ou
 * @version 1 06.09.15
 * 
 * Time Spent by Jasmine Ou: 5 hours (for images too).
 * Time Spent by Laura Wong: 20 minutes.
 * 
 * <p>
 * <b>name: Instructions.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b> pageNum</b> An integer that stores the current page number being viewed.
 * <p>
 * <b>doneButton </b> This done button says that the user is done with viewing the high scores and returns to the main menu.
 */
public class Instructions extends JPanel implements MouseListener
{
  private int pageNum = 4;
  JButton doneButton = new JButton ("Done");
  
  /**
   * The Instructions constructor adds the MouseListener to the JPanel.
   */
  public Instructions()
  {
    addMouseListener(this);
  }
  
  /**
   * The setButtonsActionListener method adds an ActionListener to each button and sets their action commands. 
   * 
   * @param al is the ActionListener passed in to be added to the buttons.
   */
  public void setButtonsActionListener(ActionListener al)
  {
    doneButton.addActionListener(al);
    
    doneButton.setActionCommand("Done");
  }
  
  /**
   * The mouseClicked method listens for a mouse click.
   * If it is not the last page, increment pageNum by one, else, pageNum is back to 0.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  public void mouseClicked(MouseEvent e) 
  {
    if (pageNum!=12)
      pageNum++;
    else
      pageNum=4;
    repaint();
  }
  
  /**
   * The mousePressed method listens for a mouse press.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mousePressed(MouseEvent e) {}
  
  /**
   * The mouseEntered method listens for a mouse enter.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseEntered(MouseEvent e) {}
  
  /**
   * The mouseExited method listens for a mouse exit.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseExited(MouseEvent e) {}
  
  /**
   * The mouseReleased method listens for a mouse release.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseReleased(MouseEvent e)
  {
  }
  
  /**
   * Overrided paintComponent method of the Graphics class to draw the instructions pages.
   * It adds the done button.
   * 
   * @param g An instance of the Graphics class.
   */
  public void paintComponent(Graphics g)
  {
    g.drawImage(new ImageIcon("images/Instructions/instructionsBG.jpg").getImage(), 0, 0,this);
    g.drawImage(new ImageIcon("images/Instructions/"+pageNum+".png").getImage(), 127, 13,this);
    doneButton.setBounds(691, 490, 75, 40);
    add(doneButton);   
  }
}
