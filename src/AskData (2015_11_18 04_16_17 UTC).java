import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

/**
 * The AskData class is the class that extends the JPanel and ask the user for their name.
 *
 * @author Jasmine Ou and Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 4 hours.
 * Time Spent by Laura Wong: 4 hours.
 * 
 * <p>
 * <b>name: AskName.java </b>
 * 
 * <p>
 * <b>Instance Variables: </b>
 * <p>
 * <b>name </b>This is the string variable to store the user's name.
 * <p>
 * <b>difficultyNum </b>This is an int variable that keeps track of the number of virtual days.
 * <p>
 * <b>critter </b>This is a string variable that keeps track of the colour of the user's critter.
 * <p>
 * <b>userName </b>This is the user name text field where the user enters their user name.
 * <p>
 * <b>okButton </b>This is the button for the user to enter all their data.
 * <p>
 * <b>easyButton </b> This is is the easy button for the easy level.
 * <p>
 * <b>mediumButton </b> This is is the medium button for the medium level.
 * <p>
 * <b>hardButton </b> This is is the hard button for the hard level.
 * <p>
 * <b>redButton </b>This is the button for the red critter.
 * <p>
 * <b>blueButton </b>This is the button for the blue critter.
 * <p>
 * <b>purpleButton </b>This is the button for the purple critter.
 * <p>
 * <b>orangeButton </b>This is the button for the orange critter.
 * <p>
 * <b>yellowButton </b>This is the button for the yellow critter.
 */
public class AskData extends JPanel
{
  String name;
  private int difficultyNum = 1;
  private String critter="Red";
  JTextField userName = new JTextField (50);
  JButton okButton = new JButton ("Ok");
  JRadioButton easyButton,mediumButton,hardButton,redButton, blueButton, purpleButton, orangeButton, yellowButton;
  
  /**
   * The AskData constructor is used to create the fields, buttons and labels for the user to enter their information.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>askTheName </b> This is the label that asks the user for their name.
   * <p>
   * <b>difficultyLabel </b> This is the difficulty label that asks the user for it's difficulty.
   * <p>
   * <b>insets </b> This is the inset variable for positioning the buttons and label.
   * <p>
   * <b>size </b> This is the dimension variable for positioning and getting the size of the buttons and the label.
   * <p>
   * <b>difficultyGroup </b> This is a group of buttons for the difficulty radio buttons.
   * <p>
   * <b>radioPanel </b> This is a radio panel where the radio buttons are added on to.
   * <p>
   * <b>colorGroup </b> This is a group of buttons for the different coloured critter radio button options.
   */
  public AskData()
  {
    setLayout(null);
    Insets insets = getInsets();
    Dimension size;
    
    JLabel askTheName = new JLabel ("Please enter your name below: ");
    askTheName.setFont (new Font ("Serif", Font.PLAIN, 15));
    size = askTheName.getPreferredSize();
    askTheName.setBounds (25 + insets.left, 100 + insets.top, size.width, size.height); 
    JLabel askCritter = new JLabel ("Please choose your critter:");
    askCritter.setBounds(489, 105, 190, 20);
    size = userName.getPreferredSize();
    userName.setBounds (25 + insets.left, 120 + insets.top, 420,size.height);  
    add(askCritter);
    add (askTheName);
    add (userName);
    
    JLabel difficultyLabel = new JLabel ("Please select a level: ");
    difficultyLabel.setFont (new Font ("Serif", Font.PLAIN, 15));
    size = difficultyLabel.getPreferredSize();
    difficultyLabel.setBounds (25 + insets.left, 180 + insets.top, size.width, size.height);
    add(difficultyLabel);
    
    //Create the radio buttons.
    easyButton = new JRadioButton("Easy (3 Days)");
    easyButton.setMnemonic(KeyEvent.VK_1);
    easyButton.setSelected(true);
    
    mediumButton = new JRadioButton("Medium (4 Days)");
    mediumButton.setMnemonic(KeyEvent.VK_2);
    
    hardButton = new JRadioButton("Hard (5 Days)");
    hardButton.setMnemonic(KeyEvent.VK_3);
    
    //Group the radio buttons.
    ButtonGroup difficultyGroup = new ButtonGroup();
    difficultyGroup.add(easyButton);
    difficultyGroup.add(mediumButton);
    difficultyGroup.add(hardButton);
    
    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
    radioPanel.add(easyButton);
    radioPanel.add(mediumButton);
    radioPanel.add(hardButton);
    add(radioPanel, BorderLayout.LINE_START);
    size = radioPanel.getPreferredSize();
    radioPanel.setBounds (25 + insets.left, 210 + insets.top, size.width, size.height); 
    
    //Create the radio buttons.
    redButton = new JRadioButton("Red");
    redButton.setBounds(498, 180, 79, 20);
    redButton.setSelected(true);
    
    blueButton = new JRadioButton("Blue");
    blueButton.setBounds(498, 250, 79, 20);
    
    purpleButton = new JRadioButton("Purple");
    purpleButton.setBounds(498, 319, 79, 20);
    
    orangeButton = new JRadioButton("Orange");
    orangeButton.setBounds(498, 387, 79, 20);
    
    yellowButton = new JRadioButton("Yellow");
    yellowButton.setBounds(498, 455, 79, 20);
    
    
    //Group the radio buttons.
    ButtonGroup colourGroup = new ButtonGroup();
    colourGroup.add(redButton);
    colourGroup.add(blueButton);
    colourGroup.add(purpleButton);
    colourGroup.add(orangeButton);
    colourGroup.add(yellowButton);
    
    add(redButton);
    add(blueButton);
    add(purpleButton);
    add(orangeButton);
    add(yellowButton);
    
    okButton.setBounds(335, 493, 60, 30);
    add(okButton);
  }
  
  /**
   * The setButtonsActionListener method is used to add action listener to the all the radio buttons and the ok button.
   * An appropriate action command is set to the components with the action listener.
   * 
   * @param al This is the ActionListener varaible.
   */
  public void setButtonsActionListener(ActionListener al)
  {
    okButton.addActionListener(al);
    okButton.setActionCommand("Ok");
    redButton.addActionListener(al);
    blueButton.addActionListener(al);
    purpleButton.addActionListener(al);
    orangeButton.addActionListener(al);
    yellowButton.addActionListener(al);
    easyButton.addActionListener(al);
    mediumButton.addActionListener(al);
    hardButton.addActionListener(al);
    yellowButton.setActionCommand("Yellow");
    orangeButton.setActionCommand("Orange");
    purpleButton.setActionCommand("Purple");
    blueButton.setActionCommand("Blue");
    redButton.setActionCommand("Red");
    easyButton.setActionCommand("easy");
    mediumButton.setActionCommand("medium");
    hardButton.setActionCommand("hard");
  }
  
  /**
   * This getName method is used to return the user's name.
   * 
   * @return name This is the user's String name.
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * This getDifficultyNum method is used to return the user's difficulty choice. 
   * @return difficultyNum This is the user's int difficulty number.
   */
  public int getDifficultyNum()
  {
    return difficultyNum;
  }
  
  /**
   * This setDifficultyNum method is used to set the user's difficulty choice. 
   * If difficulty is easy, difficultyNum is 1, if medium, it is 2, else if hard, it is 3.
   * @param difficulty This is the user's String difficulty choice.
   */
  public void setDifficultyNum(String difficulty)
  {
    if (difficulty.equals ("easy"))
    {
      difficultyNum = 1;
    }
    else if (difficulty.equals ("medium"))
    {
      difficultyNum = 2;
    }
    else
    {
      if (difficulty.equals ("hard"))
      {
        difficultyNum = 3;
      }
    }
  }
  
  /**
   * This getCritter method is used to return the user's critter colour choice.
   * 
   * @return critter This is the String color of the critter.
   */
  public String getCritter()
  {
    return critter;
  }
  
  /**
   * This setCritter method is used to set the user's critter colour choice.
   * 
   * @param colour This is the String color of the critter.
   */
  public void setCritter(String colour)
  {
    critter=colour;
  }
  
  /**
   * The checkUsername method checks if something was input as a username. 
   * If nothing was input, false is returned.
   * Else, the input is set to the name variable and it returns true.
   * @return true if a username was input and false if not.
   */
  public boolean checkUsername()
  {
    if (userName.getText().length() == 0 )
    {
      return false;
    }
    else
    {
      name = userName.getText(); 
      return true;
    }
  }
  
  /**
   * The paintComponent method is used to create the graphics.
   * 
   * @param g this is the graphics variable that will be used to create the background colour for screen.
   */
  @Override
  public void paintComponent (Graphics g)
  {
    g.drawImage(new ImageIcon ("images/AskDataBG.png").getImage(), 0, 0,this);
    g.drawImage(new ImageIcon ("images/Critter/Red/RedCritter.png").getImage(), 590, 150,this);
    g.drawImage(new ImageIcon ("images/Critter/Blue/BlueCritter.png").getImage(), 590, 221,this);
    g.drawImage(new ImageIcon ("images/Critter/Purple/PurpleCritter.png").getImage(), 590, 290,this);
    g.drawImage(new ImageIcon ("images/Critter/Orange/OrangeCritter.png").getImage(), 590, 360,this);
    g.drawImage(new ImageIcon ("images/Critter/Yellow/YellowCritter.png").getImage(), 590, 435,this);
  }
}

