import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
/**
 * The Recreation class is the class that lets the user play the 'mini-game' and each difficulty has a different and unique input screen.
 *
 * @author Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 20 minutes.
 * Time Spent by Laura Wong: 18 hours.
 * 
 * <p>
 * <b>name: Recreation.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>quiz </b> This creates a 2D array of the quiz questions and answers.
 * <p>
 * <b>possibleQuestions </b> This string array is used to store ALL of the questions and answers in side the file.
 * <p>
 * <b>isCorrect </b> This creates a boolean variable that indicates if the user's answer is correct or not.
 * <p>
 * <b>counter </b> This int variable is used to count which questions of the quiz to use of the random 3 generated.
 * <p>
 * <b>askQuestion </b> This is a JLabel to ask the user the question.
 * <p>
 * <b>option1 </b> This is a JLabel for the first option.
 * <p>
 * <b>option2 </b> This is a JLabel for the second option.
 * <p>
 * <b>option3 </b> This is a JLabel for the third option.
 * <p>
 * <b>answer  </b> This String variable is used to save the user's answer.
 * <p>
 * <b>entered </b> This boolean variable is used to store if it has saved the random quiz questions or not.
 * <p>
 * <b>yesButton </b> This button is used if the user wants to play again.
 * <p>
 * <b>noButton </b> This button is used if the user does not want to play again.
 * <p>
 * <b>askDialog </b> This dialog is used to ask the user if they want to play again.
 * <p>
 * <b>okButtonDialog </b> This dialog is used to ask the user if they are ok with exitting the recreation.
 * <p>
 * <b>okButton </b> This button is used to ensure the user is fine with exiting recreation.
 * <p>
 * <b>askAnswer </b> This JLabel is used to ask the user for an answer.
 * 
 */
public class Recreation extends JPanel implements MouseListener
{
  private String [] [] possibleQuestions = new String [10] [6];
  private String [] [] quiz = new String [3] [6];
  int counter;
  private String answer;
  boolean entered;
  private JLabel askQuestion = new JLabel ("");
  private JLabel option1 = new JLabel ("");
  private JLabel option2 = new JLabel ("");
  private JLabel option3 = new JLabel ("");
  private JButton yesButton = new JButton ("Yes");
  private JButton noButton = new JButton ("No");
  JDialog askDialog;
  private JButton okButton = new JButton ("Got it!");
  JDialog okButtonDialog;
  private JLabel askAnswer = new JLabel ("Please enter the letter of the answer you think is correct: ");
  private boolean done = false;
  
  
  /**
   * The Recreation constructor is used to ensure the comupter listens for the mouse listener.
   */
  public Recreation()
  {
    addMouseListener(this);
    setVisible(true);
  }
  
  /**
   * the getDone method is used to get the boolean variable done's value.
   * 
   * @return done a boolean variable that indicates the variable done's value.
   */
  public boolean getDone()
  {
    return done; 
  }
  
  /**
   * the setDone method is used to set the boolean variable done's value.
   * 
   * @param value a boolean variable that the variable done is set to.
   */
  public void setDone(boolean value)
  {
    this.done = value; 
  }
  
  /**
   * the setCounter method is used to set the int variable counter's value.
   * 
   * @param value a int variable that the variable counter is set to.
   */
  public void setCounter(int value)
  {
    this.counter = value; 
  }
  
  /**
   * The play method is used to play the game.
   * The first if structure is used to determine which difficulty the user chose, and then doing a different "game" or input method for each one.
   * The first action listener is used for 'ok' which is a button.
   * The if structure in the action listener is used to ensure the user enters an a, b or c and checks to see if the user got the answer correct or not, if they did a congrtulations message comes up and if not an error window that says the correct answer comes up.
   * The next if (the nested if) ensures that the difficulty number equals a 5.
   * In this if structure there are 3 different action listeners for each of the three different options the user can choose and they all create a window to say if the user got the question right or not.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>userInput </b> This is the label that asks the user for their answer.
   * <p>
   * <b>ok </b> This is a the ok button that submits the user's answer and saves it.
   * <p>
   * <b>option1Button </b> This is the JButton that shows the first option to the user.
   * <p>
   * <b>option2Button </b> This is the JButton that shows the second option to the user.
   * <p>
   * <b>option3Button </b> This is the JButton that shows the third option to the user.
   * <p>
   * <b>size </b> This is the dimension variable for positioning and getting the size of the buttons and the label.
   */
  public void play ()
  {
    setLayout(null);
    Dimension size;
    JTextField userInput = new JTextField (20);
    
    askQuestion.setText(quiz[counter] [0]);
    askQuestion.setFont (new Font ("Helvetica", Font.BOLD, 15));
    if (CritterSitterApp.askData.getDifficultyNum() == 1)
    {
      askQuestion.setBounds (70, 330, 500, 15);
      
      option1.setText(quiz[counter] [1]);
      option2.setText(quiz[counter] [2]);
      option3.setText(quiz[counter] [3]);
      option1.setFont (new Font ("Helvetica", Font.BOLD, 15));
      option2.setFont (new Font ("Helvetica", Font.BOLD, 15));
      option3.setFont (new Font ("Helvetica", Font.BOLD, 15));
      askQuestion.setBounds (35, 150, 500, 15);
      option1.setBounds (150, 403, 500, 15);
      option2.setBounds (350, 403, 500, 15);
      option3.setBounds (550, 403, 500, 15);
      add(option1);
      add(option2);
      add(option3); 
    }
    else if (CritterSitterApp.askData.getDifficultyNum() == 2)
    {      
      askAnswer.setFont (new Font ("Serif", Font.PLAIN, 15));
      option1.setText(quiz[counter] [1]);
      option2.setText(quiz[counter] [2]);
      option3.setText(quiz[counter] [3]);
      option1.setFont (new Font ("Helvetica", Font.BOLD, 15));
      option2.setFont (new Font ("Helvetica", Font.BOLD, 15));
      option3.setFont (new Font ("Helvetica", Font.BOLD, 15));
      askQuestion.setBounds (35, 150, 600, 15);
      option1.setBounds (35, 180, 500, 15);
      option2.setBounds (35, 200, 500, 15);
      option3.setBounds (35, 220, 500, 15);
      add(option1);
      add(option2);
      add(option3); 
      
      size = askAnswer.getPreferredSize();
      askAnswer.setBounds (250, 65, size.width, size.height);
      size = userInput.getPreferredSize();
      userInput.setBounds (250, 95, size.width, size.height);    
      add (askAnswer);
      add (userInput);
      
      JButton ok = new JButton ("Ok");
      add(ok);
      size = ok.getPreferredSize();
      ok.setBounds (505, 95, size.width, size.height); 
      ok.addActionListener (new ActionListener ()
                              {
        public void actionPerformed (ActionEvent e)
        {
          if (userInput.getText().length() == 0 )
          {
            JOptionPane.showMessageDialog(null, "Please input an answer!");
            userInput.setText ("");
            userInput.requestFocusInWindow();
          }
          else if (!userInput.getText().equalsIgnoreCase("a") && !userInput.getText().equalsIgnoreCase("b") && !userInput.getText().equalsIgnoreCase("c") )
          {         
            JOptionPane.showMessageDialog(null, "Please input either 'a', 'b' or 'c'.");
            userInput.setText ("");
            userInput.requestFocusInWindow();
          }
          else
          {
            answer = userInput.getText(); 
            isCorrect();
            userInput.setText ("");
            userInput.requestFocusInWindow();
          }
        }
      }
      );
    }
    else 
      if (CritterSitterApp.askData.getDifficultyNum() == 3)
    {
      askQuestion.setBounds (35, 350, 800, 15);
      JButton option1Button = new JButton (quiz[counter] [1]);
      JButton option2Button = new JButton (quiz[counter] [2]);
      JButton option3Button = new JButton (quiz[counter] [3]);
      
      option1Button.setBackground(new Color(255, 0, 128));
      option2Button.setBackground(new Color(255, 255, 128));
      option3Button.setBackground(new Color(74, 210, 225));
      
      add(option1Button);
      size = option1Button.getPreferredSize();
      option1Button.setBounds (100, 425, 200, 30); 
      option1Button.addActionListener (new ActionListener ()
                                         {
        public void actionPerformed (ActionEvent e)
        {
          answer = quiz[counter] [1];
          isCorrect();
        }
      }
      );
      add(option2Button);
      size = option2Button.getPreferredSize();
      option2Button.setBounds (300, 425, 200, 30); 
      option2Button.addActionListener (new ActionListener ()
                                         {
        public void actionPerformed (ActionEvent e)
        {
          answer = quiz[counter] [2];
          isCorrect();
        }
      }
      );
      add(option3Button);
      size = option3Button.getPreferredSize();
      option3Button.setBounds (500, 425, 200, 30); 
      option3Button.addActionListener (new ActionListener ()
                                         {
        public void actionPerformed (ActionEvent e)
        {
          answer = quiz[counter] [3];
          isCorrect();
        }
      }
      );
    }
    add(askQuestion);
  }
  
  /**
   * The saveQA method is used to save all the questions and answers from the text files.
   * The first if statement is used to determine the difficulty level the user chose, and then determining which text file with questions to choose.
   * The try block is to try and read in from the text file.
   * The for loop is used to go through 10 times, and the next for loop to go through the loop another 6 times to fill the array)
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>fileName </b> This String variable is used to store the file name depending on the difficulty the user chose.
   * <p>
   * <b>Exception e </b> IOException e when an error occurs with file io where the file cannot be read.
   * <p>
   * <b>x </b> This is the int variable in the for loop.
   * <p>
   * <b>y </b> This is the int variable inside the second for loop.
   */
  public void saveQA ()
  {
    String fileName = "";    
    /*return random row from 2D quiz array as question to be asked*/
    if (CritterSitterApp.askData.getDifficultyNum()== 1)
    {
      fileName = "text/easyQuestions.txt";
    }
    else if (CritterSitterApp.askData.getDifficultyNum() == 2)
    {
      fileName = "text/mediumQuestions.txt";
    }
    else
      if(CritterSitterApp.askData.getDifficultyNum() == 3)
    {
      fileName = "text/hardQuestions.txt";
    }
    try
    {
      BufferedReader input = new BufferedReader (new FileReader (fileName));
      for (int x = 0; x < 10; x++)
      {
        for (int y = 0; y < 6; y++)
        {
          possibleQuestions[x] [y] = input.readLine();
        }
      }
    }
    catch (IOException e)
    {
    }
  }
  
  /**
   * The setButtonsActionListener method adds an ActionListener to each button and sets their action commands. 
   * 
   * @param al is the ActionListener passed in to be added to the buttons.
   */
  public void setButtonsActionListener(ActionListener al)
  {
    yesButton.addActionListener(al);
    noButton.addActionListener(al);
    okButton.addActionListener(al);
    
    yesButton.setActionCommand("Yes");
    noButton.setActionCommand("No");
    okButton.setActionCommand("Got it!");
  }
  
  /**
   * The generateQuestion method is used to come up with the 3 random questions to be asked, it also sets the entered variable to true because it created the random questions.
   * The for loop is to make the loop run 3 times .
   * The next for loop is to o another 3 times to check the values of the numbers already chosen (no repeated questions!).
   * The if statement is used to see if the numbers that were already chosen don't equal the current number and it is the last number in the group, if this is true the current question will be added to the list.
   * The last if statement is used to check to see if the current value of number equals any chosen number, if so, it will generate another random number.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>number </b> This is the random number that is generated.
   * <p>
   * <b>chosenNumbers </b> This is an integer array used to store the random numbers that have already been chosen.
   * <p>
   * <b>x </b> This is the int variable in the for loop.
   * <p>
   * <b>y </b> This is the int variable inside the second for loop.
   */
  public void generateQuestion ()
  {
    int number;
    int [] chosenNumbers = new int [3];
    for (int x = 0; x < 3; x++)
    {
      number = (int) (Math.random() * 11);
      for (int y = 0; y < 3; y++)
      {
        if (chosenNumbers[y] != number && y == 2)
        {
          quiz[x] = possibleQuestions [number - 1];
          chosenNumbers[y] = number - 1;
        }
        if (chosenNumbers[y] == number)
        {
          number = (int) (Math.random() * 11);
          y = 0;
        }
      }
    }
    entered = true;
  }
  
  /**
   * The getIsCorrect method is used to determine if the user's answer was correct or not, if it was correct it has an option pane with a conratulations and if not it tells the user what the answer was supposed to be.
   * The if statement is used to determine if the answer is equal to what the user entered and the nested if is for if the answer DOES NOT equal to what the user answered.
   * The second if statement is used for the counter to ask the user if they want to play another game of recreation or not. 
   * The next if statement is used to see if the value of counter is less than 2 because it will ask if the user wants to play the game again.
   * 
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>yesNo </b> This JButton array is used for the two options in JOptionPane
   * <p>
   * <b>askOptionPane </b> This JOptionPane is used to ask the user if they want to play again.
   * <p>
   * <b>oneButton </b> This JButton array is for the Ok button telling the user that they have played all their games for the day.
   * <p>
   * <b>donePane </b> This donePane is the JOptionPane used to tell the user that their game of recreation is over. 
   */
  public void isCorrect()
  {
    if (answer.equalsIgnoreCase(quiz[counter][4]))
    {
      JOptionPane.showMessageDialog(null, "Congratulations, you got it correct! \nYou earn 5 points!");
      CritterSitterApp.home.setPoints(CritterSitterApp.home.getPoints() + 5);
    }
    else
      if (!answer.equalsIgnoreCase(quiz[counter][4]))
    {
      JOptionPane.showMessageDialog(null, "Sorry, you didn't get it right. \nThe correct answer is: " + quiz[counter][4]);
    }
    if (counter  < 2)
    {
      JButton [] yesNo = {yesButton, noButton};
      JOptionPane askOptionPane = new JOptionPane("Would you like to play again? \nYou have: " + (2 - counter) + " games left!", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, yesNo);
      askDialog = askOptionPane.createDialog(this, "Wait!");
      askDialog.setVisible(true);
    }
    else 
      if (counter == 2)
    {
      JButton [] oneButton = {okButton};
      JOptionPane donePane = new JOptionPane ("You have no more games left! Bye! ", JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_OPTION, null, oneButton);
      okButtonDialog = donePane.createDialog(this, "Sorry!");
      done = true;
      okButtonDialog.setVisible(true);
    }
    counter++;
  }
  
  /**
   * The mouseClicked method coordinates the proper response to the user's mouse clicks. 
   * The if statement is used to see which box the user pushed, and depending on which one they pushed, it will indicate if their option was correct or not.
   * <p>
   * <b>Local Variables: </b>
   * 
   * @param e MouseEvent that stores the user's mouse actions.
   */
  public void mouseClicked(MouseEvent e) 
  {
    if (e.getX() > 147 && e.getX() < 207 && e.getY() > 420 && e.getY() < 480)
    {
      answer = quiz[counter] [1];
      isCorrect();
    }
    else if (e.getX() > 354 && e.getX() < 414 && e.getY() > 420 && e.getY() < 480)
    {
      answer = quiz[counter] [2];
      isCorrect();
    }
    else
      if (e.getX() > 561 && e.getX() < 621 && e.getY() > 420 && e.getY() < 480)
    {
      answer = quiz[counter] [3];
      isCorrect();
    }
  }
  
  /**
   * The mousePressed method listens for a mouse press.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mousePressed(MouseEvent e) 
  {
  }
  
  /**
   * The mouseEntered method listens for a mouse enter.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseEntered(MouseEvent e) 
  {
  }
  
  /**
   * The mouseExited method listens for a mouse exit.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseExited(MouseEvent e) 
  {
  }
  
  /**
   * The mouseReleased method listens for a mouse release.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @Override
  public void mouseReleased(MouseEvent e) 
  {
  }
  
  /**
   * This paintComponent method is an overriden method and it is used to draw the background images and draw the strings on the screen.
   * The first if statement is used to see if the boolean entered variable is false or not because if it IS false, then it will generate and save the questions, if not it won't create anymore random questions.
   * The second if statement is used to determine which difficulty the user put in (either 3, 4 or 5 days) and it also draws different pictures and creates 3 distinct and unique user input screens for each difficulty.
   * 
   * <p>
   * <b>Instance variables: </b>
   * <p>
   * <b>image </b> This is the background image that is drawn for each of the different difficulty screens.
   * <p>
   * <b>pic1 </b> This image is used for the easy level of the first option.
   * <p>
   * <b>pic2 </b> This image is used for the easy level of the second option.
   * <p>
   * <b>pic3 </b> This image is used for the easy level of the third option.
   * 
   * @param g This is the graphics variable used to draw onto the JPanel screen.
   */
  public void paintComponent(Graphics g)
  { 
    if (entered == false)
    {
      saveQA();
      generateQuestion();
    }
    if (CritterSitterApp.askData.getDifficultyNum() == 1)
    {      
      Image easyBG = new ImageIcon ("images/Recreation/recreationBackroundEasy.jpg").getImage();
      g.drawImage(easyBG, 0, 0,this);
      Image pic1 = new ImageIcon("images/Recreation/" + quiz[counter][1] + ".png").getImage();
      g.drawImage(pic1, 147, 420,this);
      Image pic2 = new ImageIcon("images/Recreation/" + quiz[counter][2] + ".png").getImage();
      g.drawImage(pic2, 354, 420,this);
      Image pic3 = new ImageIcon("images/Recreation/" + quiz[counter][3] + ".png").getImage();
      g.drawImage(pic3, 561, 420,this);
      
      g.setColor(Color.BLACK);
      g.setFont (new Font ("Helvetica", Font.BOLD, 15));
      g.drawString ("Please click the image that you think is correct!", 170, 515);
    }
    else if (CritterSitterApp.askData.getDifficultyNum() == 2)
    {
      Image mediumBG = new ImageIcon ("images/Recreation/recreationBackroundMedium.jpg").getImage();
      g.drawImage(mediumBG, 0, 0,this);
      g.setColor(Color.BLACK);
      g.setFont (new Font ("Helvetica", Font.BOLD, 25));
      g.drawString ("a", 150, 303);
      g.drawString ("b", 365, 290);
      g.drawString ("c", 570, 290);
    }
    else
    {
      Image hardBG = new ImageIcon ("images/Recreation/recreationBackroundHard.jpg").getImage();
      g.drawImage(hardBG, 0, 0,this);
    }
    play();
  }
}
