import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * The HighScores class is the class that generates and outputs the high scores of the top 10 scores to an output file.
 * The user can also print out a copy of the high scores. 
 *
 * @author Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: none.
 * Time Spent by Laura Wong: 4 hours.
 * 
 * <p>
 * <b>name: HighScores.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>currentScores </b> This is a String array that stores the high scores.
 * <p>
 * <b>currentNames </b> This is a String array that stores the names.
 * <p>
 * <b>currentDifficulty </b> This is a String array that stores the difficulties.
 * <p>
 * <b>input </b> This is a BufferedReader variable that allows reading files to be done.
 * <p>
 * <b>line </b> This String variable is used to see if there are any scores stored in the file.
 * <p>
 * <b>printButton </b> This print button is used to print the high scores.
 * <p>
 * <b>doneButton </b> This done button says that the user is done with viewing the high scores and returns to the main menu.
 */
public class HighScores extends JPanel
{
  private String [] currentScores = new String [11];
  private String [] currentNames = new String [11];
  private String [] currentDifficulty = new String [11];
  BufferedReader input;
  String line;
  JButton doneButton = new JButton ("Done");
  JButton printButton = new JButton ("Print");
  
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
   * The displayScores method reads the high scores from the file HighScores.txt, ensures that there are scores in there and displays them.
   * The try block is used to try and read into the high scores file.
   * The if statement is used to check to see if the input.readLine() (the first line) is null or not.
   * The for loop is used to draw the number of scores onto the JPanel.
   * The printButton has an ActionListener with it that makes a new PrintHighScores when it is pushed.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>numScores </b> This is an int variable that counts the number of scores in the file.
   * <p>
   * <b>bg </b> This is the image variable for the backround image for the high scores.
   * <p>
   * <b>x </b> This is the image variable for the backround image for the high scores.
   * <p>
   * <b>IOException </b> This is when an error occurs with file io where the file cannot be read.
   * 
   * @param g This is the Graphics variable to draw the text onto the panel.
   */
  public void displayScores (Graphics g)
  {
    int numberOfScores = 0;
    
    Image bg = new ImageIcon ("images/HighScores/highscoresOutput.png").getImage();
    g.drawImage(bg, 0, 0, this);
    
    try
    {
      input = new BufferedReader (new FileReader ("text/HighScores.txt"));
      line = input.readLine();
      if (line != null)
      {
        numberOfScores = Integer.parseInt (line);
      }
      for (int x = 0 ; x < numberOfScores ; x++)
      {      
        currentNames[x] = input.readLine();
        currentScores[x] = input.readLine();
        currentDifficulty[x] = input.readLine();
        g.setColor(Color.BLACK);
        g.setFont (new Font ("Helvetica", Font.BOLD, 20));
        g.drawString(currentNames[x], 235, 180 + (x * 20));
        g.drawString("" + (x + 1), 100, 180 + (x * 20));
        g.drawString(currentDifficulty[x], 455, 180 + (x * 20));
        g.drawString(currentScores[x], 575, 180 + (x * 20));
      }
    }
    catch (IOException e)
    {
    }
    
    add(printButton);
    printButton.setBounds(205, 403, 70, 33);
    add(printButton);
    printButton.addActionListener (new ActionListener ()
                                     {
      public void actionPerformed (ActionEvent e)
      {
        new PrintHighScores();
      }
    }
    );
  }
  
  /**
   * the getCurrentScores method is used to get the current scores at location x.
   * 
   * @param x is the int variable to get the score at x
   * @return currentScores[x] the score in the array at position x.
   * 
   */
  public String getCurrentScores (int x)
  {
    return currentScores[x];
  }
  
  /**
   * the getCurrentNames method is used to get the current names at location x.
   * 
   * @param x is the int variable to get the name at x
   * @return currentNames[x] the name in the array at position x.
   * 
   */
  public String getCurrentNames (int x)
  {
    return currentNames[x];
  }
  
  /**
   * the getCurrentDifficulty method is used to get the current difficulties at location x.
   * 
   * @param x is the int variable to get the difficulty at x.
   * @return currentDifficulty[x] the difficulty in the array at position x.
   * 
   */
  public String getCurrentDifficulty (int x)
  {
    return currentDifficulty[x];
  }
  
  /**
   * The updateScores method processes and updates the scores by sorting them.
   * The first try block is used to attempt to read into the file.
   * The next try block is used to add all the values in the file into the array currentScores
   * The first two if statements are used to check to see if there are any scores in file, if there are it will check to see if there are 11 or not.
   * The for loop is used to add all of the scores into the arrays.
   * The next two for loops are used for sorting the numbers in the array in descending order.
   * The try block is used to try and output to the file.
   * The if statement is used to see if there are 11 scores or 10 (becuse it only outputs 10).
   * The next if statement is for displaying one score if there is only one score. 
   * Else, the last for loop is used to output all of the scores in the arrays.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>numScores </b> This is an int variable that counts the number of scores in the file.
   * <p>
   * <b>playerName </b> This is a String array that keeps track of the player names
   * <p>
   * <b>scoreArray </b> This is an int array that keeps track of the player's scores.
   * <p>
   * <b>difficultyArray </b> This is an int array that keeps track of the player's difficulty.
   * <p>
   * <b>tempDiff </b> This is an integer number that keeps track of the temp difficulty.
   * <p>
   * <b>output </b> This is a PrintWriter variable that lets printing to files allowed.
   * <p>
   * <b>currentScore </b> This is the current score that is being worked with in the for loop.
   * <p>
   * <b>tempName </b> This is the name that is associated with the current score.
   * <p>
   * <b>x </b> This variable is being used throughout the processing of the sorting.
   * <p>
   * <b>w </b> This is the integer variable used in the first for loop.
   * <p>
   * <b>j </b> This is the integer variable used in the second for loop.
   * <p>
   * <b>q </b> This is the integer variable used in the last for loop.
   * <p>
   * <b> IOException </b> This is when an error occurs with file io where the file cannot be read.
   * 
   * @param score This is the score that will be used for the highscorers (if in the top 10).
   * @param name This is the String storing the username.
   * @param level This is the int storing the level.
   */
  public void updateScores (int score, String name, int level)
  {
    int numScores = 1;
    String [] playerName = new String [11]; 
    int [] scoreArray = new int [11];
    int [] difficultyArray = new int [11];
    PrintWriter output;
    int currentScore;
    String tempName;
    int tempDiff;
    int x;
    
    try
    {
      input = new BufferedReader (new FileReader ("text/HighScores.txt"));
      line = input.readLine ();
      if (line != null)
      {
        if (!line.equals ("11"))
          numScores += Integer.parseInt (line);
        else
          numScores = 11;
      }
      for (int w = 0 ; w < numScores - 1; w++)
      {
        playerName[w] = input.readLine();
        scoreArray[w] = Integer.parseInt (input.readLine ());
        difficultyArray[w] = Integer.parseInt (input.readLine ());
      }
      
      
      playerName[numScores-1] = name;
      scoreArray[numScores-1] = score; 
      difficultyArray[numScores-1] = level;
      
      for (int j = 1; j < numScores; j++)    
      {
        currentScore = scoreArray[j];
        tempName = playerName[j];
        tempDiff = difficultyArray[j]; 
        
        for(x = j - 1; (x >= 0) && (scoreArray[x] < currentScore); x--)
        {
          scoreArray[x + 1] = scoreArray[x];
          playerName[x + 1] = playerName[x];
          difficultyArray[x + 1] = difficultyArray[x];
        }
        scoreArray[x + 1] = currentScore; 
        playerName[x + 1] = tempName;
        difficultyArray[x + 1] = tempDiff;
      }
    }
    catch (IOException e)
    {
    }
    
    try
    {
      output = new PrintWriter (new FileWriter ("text/HighScores.txt"));
      if (numScores == 11)
      {
        output.println (10);
        numScores = 10;
      }
      else
      {
        output.println (numScores);
      }
      if (numScores==1)
      {
        output.println (name);
        output.println (score);
        output.println (level);
      }
      else
      {
        for (int q = 0; q < numScores; q++)
        {
          output.println (playerName [q]);
          output.println (scoreArray [q]);
          output.println (difficultyArray [q]);
        }
      }
      output.close ();
    }
    catch (IOException e)
    {
    }    
    
  }
  
  /**
   * The paintComponent overriden method is used to control the highscores.
   * 
   * @param g This Graphics variable is used to draw things on the desired panel.
   */
  public void paintComponent(Graphics g)
  {
    displayScores(g);
    doneButton.setBounds(446, 403, 70, 33);
    add(doneButton);   
  }
}
