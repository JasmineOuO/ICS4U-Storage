import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The MainMenu class is the main menu screen where the user can choose to either play the game, read the instructions, see the high scores or quit the game. 
 * In each case different windows or screens will appear asking for user input to continue back to the main menu or throughout the game.
 * 
 * @author Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 40 minutes.
 * Time Spent by Laura Wong: 4 hours.
 * 
 * <p>
 * <b>name: MainMenu.java </b>
 * 
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>playButton </b> This is a play button, that when pushed, plays the game.
 * <p>
 * <b>highScoresButton </b> This is a high scores button, that when pushed, shows the high scores.
 * <p>
 * <b>instructionsButton </b> This is an instructions button, that when pushed, shows the instructions of the game.
 */
public class MainMenu extends JPanel
{ 
  JButton playButton = new JButton ("Play");
  JButton highScoresButton = new JButton ("High Scores");
  JButton instructionsButton = new JButton ("Instructions");
  
  /**
   * The MainMenu constructor creates and adds the introduction label, play button, instructions button, high scores button and quit button to the panel.
   * The layout is set to null and each button has its own ActionListener.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>introductionLabel </b> This is the introduction label.
   * <p>
   * <b>quitButton </b> This is a quit button, that when pushed, quits the game. 
   */
  public MainMenu()
  {
    setLayout (null);
    
    JLabel introductionLabel = new JLabel ("Welcome to Critter Sitter! An educational and fun game to learn about nutrition! Please choose an option: ");
    JButton quitButton = new JButton ("Quit");
    
    quitButton.addActionListener (new ActionListener ()
                                    {
      public void actionPerformed (ActionEvent e)
      {
        onExit();
      }
    }
    );
    
    playButton.setBounds(170,160,230,90);
    highScoresButton.setBounds(170,260,230,60);
    instructionsButton.setBounds(170,330,230,60);
    quitButton.setBounds(170,400,230,60);
    
    add(introductionLabel);
    add(playButton);
    add(highScoresButton);
    add(instructionsButton);
    add(quitButton);
  }
  
  /**
   * The onExit method asks the user if they want to quit their unfinished game before closing the window.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>confirmDialog </b> This stores the user's option in the JOptionPane and is initialized to 0.
   */
  public void onExit()
  {
    int confirmDialog = 0;
    if (CritterSitterApp.home != null)
      confirmDialog = JOptionPane.showOptionDialog(null, "Are you sure you want to quit your unfinished game?","Quit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    if (confirmDialog == 0) 
    {
      JOptionPane.showMessageDialog(null, "Thank you for playing and come again soon! Bye!");
      System.exit(0);
    }
  }
  
  /**
   * The setButtonsActionListener method adds an ActionListener to each button and sets their action commands. 
   * 
   * @param al is the ActionListener passed in to be added to the buttons.
   */
  public void setButtonsActionListener(ActionListener al)
  {
    playButton.addActionListener(al);
    highScoresButton.addActionListener(al);
    instructionsButton.addActionListener(al);
    
    playButton.setActionCommand("Play");
    highScoresButton.setActionCommand("HighScores");
    instructionsButton.setActionCommand("Instructions");
  }
  
  /**
   * The paintComponent method is used to create graphics on the menu which is the pink background and the doctor.
   * 
   * @param g This is the Graphics variable that is used to paint onto the panel.
   */  
  public void paintComponent(Graphics g)
  {
    Image bg = new ImageIcon ("images/mainMenuBG.png").getImage();
    g.drawImage(bg, 0, 0,this);
  }
}
