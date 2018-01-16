import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * The Doctor class is the class that creates the doctor.
 * The user can get a health check up for their critter or buy medicine if the critter is ill.
 * Here, there will be information on the critter's nutrition if the user chooses to view them on the clipboard for the health check up.
 *
 * @author Laura Wong
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 30 minutes.
 * Time Spent by Laura Wong: 20 hours.
 * 
 * <p>
 * <b>name: Doctor.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>sick </b> This is a boolean variable that indicates if the critter is sick or not.
 * <p>
 * <b>askDialog </b> This is a JDialog that has the ask dialog to ask the user if they want to exit or et a check-up.
 * <p>
 * <b>okPushed </b> This boolean variable is used to indicate if the ok button was pushed or not.
 * <p>
 * <b>ok </b> This is the ok button used in the good bye dialog.
 * <p>
 * <b>talkingDoctor </b> This string variable is used to save the first part of the doctor's text.
 * <p>
 * <b>talkingDoctor2 </b> This string variable is used to save the second part of the doctor's text.
 * <p>
 * <b>talkingDoctor3 </b> This string variable is used to save the third part of the doctor's text.
 * <p>
 * <b>talkingDoctor4 </b> This string variable is used to save the fourth part of the doctor's text.
 * <p>
 * <b>counter </b> This int variable is used to indicate when the user pushes the ok button.
 * <p>
 * <b>yesButton </b> This button is used to indicate that the user wants to check the critter's health.
 * <p>
 * <b>exitButton </b> This button is used to indicate that the user wants to exit right after the have seen the critter is healthy or not.
 * <p>
 * <b>okButton </b> This button is used when the user has seen the information from the doctor's speech bubble.
 * <p>
 * <b>doneButton </b> This button is used when the user is officially out of the doctor's office.
 * <p>
 * <b>checkUpArray </b> This String 2D array stores the certain meal's percentages and the certain meal's value of "too little", "too much" or "".
 * <p>
 * <b>startAgain </b> This is a boolean variable used to check to see if the doctor should start again from the beginning.
 */
public class Doctor extends JPanel
{
  private boolean sick;
  JDialog askDialog;
  private boolean okPushed = false;
  JButton ok = new JButton ("Ok");
  private String talkingDoctor =  "";
  private String talkingDoctor2 = "";
  private String talkingDoctor3 = "";
  private String talkingDoctor4 = "";
  int counter = 0;
  private JButton yesButton = new JButton ("Check Health");
  private JButton exitButton = new JButton ("Exit");
  private JButton okButton = new JButton ("OK");
  private JButton doneButton = new JButton ("Done");
  private String [] [] checkUpArray = new String [6] [2];
  boolean startAgain = true;
  
  /**
   * The Doctor constructor adds the JLabel and runs the start method from the beginning.
   */
  public Doctor()
  {
    start();
  }
  
  /**
   * The start method is used to set the doctor's text to the first message he tells the user, it also resets counter to 0.
   */
  public void start()
  {
    talkingDoctor =  "Hello! My name is Dr.Java,";
    talkingDoctor2 = "welcome to my doctor's office!";
    talkingDoctor3 = "";
    talkingDoctor4 = "";
    counter = 0;
    remove(ok);
  }
  
  /**
   * The reportToPatient method is used to display the text on screen on speech bubble of doctor based on health check results.
   * The if statement is used to check to see if the critter is sick or not and create a different message depending on if they are sick or not.
   */
  public void reportToPatient()
  {
    if (sick == true)
    {
      talkingDoctor = "Sorry, your critter is sick.";
      talkingDoctor2 = "You need to feed it";
      talkingDoctor3 = "a healthier diet.";
      talkingDoctor4 = "";
    }
    else
    {
      talkingDoctor = "Good job! Your critter is";
      talkingDoctor2 = "Healthy! I'm proud of the healthy";
      talkingDoctor3 = "choices you've made.";
      talkingDoctor4 = "";
    }
  }
  
  /**
   * The askExit method is used to ask the user if they want to exit the game or get a check-up.
   * 
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>yesOrExit </b> This JButton array is used to store the 2 buttons that will be in the askOptionPane.
   * <p>
   * <b>askOptionPane </b> This JOptionPane is used to ask the user if they want to exit or to get a check-up.
   */
  public void askExit()
  {
    JButton [] yesOrExit = {yesButton, exitButton};
    JOptionPane askOptionPane = new JOptionPane("Would you like to check-up your pet's health or exit?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, new ImageIcon("images/DoctorOffice/exitSign.png"), yesOrExit);
    askDialog = askOptionPane.createDialog(this, "Wait!");
    askDialog.setVisible(true); 
    counter = 2;
    startAgain = true;
  }
  
  /**
   * The beforeCheckUP method is used to set the doctor's speech to something before the clipboard comes up.
   */
  public void beforeCheckUp()
  {
    talkingDoctor = "This clipboard displays your";
    talkingDoctor2 = "critter's percentages of";
    talkingDoctor3 = "the 5 food groups and water";
    talkingDoctor4 = "for the last scheduled meal.";
  }
  
  /**
   * The checkUpHealth method is used to load the the values from the balanced array in critter home to to checkup array with the percentages and whether it has been fed too little, too much or just enough.
   * The for loop is used to run 6 times to check all 6 meals.
   * The if statements are used to see if the balanced array is equal to "too little", "too much" or "".
   * 
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>x </b> This is the variable to be used inside the for loops.
   */
  public void checkUpHealth()
  {   
    for (int x = 0; x < 6; x++)
    {
      if (CritterSitterApp.home.balancedArray[x].equals("too little"))
      {
        checkUpArray [x] [1] = "too little";
      }
      else if (CritterSitterApp.home.balancedArray[x].equals("too much"))
      {
        checkUpArray [x] [1] = "too much";
      }
      else
        if (CritterSitterApp.home.balancedArray[x].equals (""))
      {
        checkUpArray [x] [1] = "";
      }
      checkUpArray [x] [0] = "" + CritterSitterApp.home.percentages[x];
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
    exitButton.addActionListener(al);
    ok.addActionListener(al);
    
    ok.setActionCommand("OK");
    yesButton.setActionCommand("Yes");
    exitButton.setActionCommand("Exit");
  }
  
  /**
   * The talkToPatient method is used if the critter is healthy and needs random insights of knowledge.
   * The first if statement is used to see if the critter is sick or not. 
   * If they are not sick, the doctor will give the critter random knowledge and "fun-facts".
   * This leads to the next if statement that is used to indicate if the random number genereated equals to any of those values and changes the doctor's text to something else to say.
   * If they are sick, it will ask them if they want to purchase instant medication.
   * This goes to the next if statement which indicates if the answer to the user's JOptionPane if they would like to buy medication is yes or no (0 being yes and 2 being no)
   * If they choose yes, another if statement is used to see if the user has more than or equal to 25 points to pay for the medication, if they do, the crittr is cured, if not then there is another JOptionPane telling them they don't have enough.
   * The last if is used to see if the user clicked the "No" button for buying medication, it will output yet another JOptionPane saying how to make their critter healthy again.
   *
   * <p>
   * <b>Local Variables </b>
   * <p>
   * <b>number </b> This int variable is used to create a random number.
   * <p>
   * <b>option </b> This is an int variable to store what the user clicks in the JOptionPane.
   */
  public void talkToPatient()
  {
    if (sick == false)
    {
      int number = (int) (Math.random() * 6);
      if (number == 1)
      {
        talkingDoctor = "Maybe feed your critter some";
        talkingDoctor2 = "more water! You know you";
        talkingDoctor3 = "need at least 7 cups";
        talkingDoctor4 = "a day!";
      }
      else if (number == 2)
      {
        talkingDoctor = "Don't be afraid to feed";
        talkingDoctor2 = "your critter some unhealthy";
        talkingDoctor3 = "food once in a while!";
        talkingDoctor4 = "It won't hurt.";
      }
      else if (number == 3)
      {
        talkingDoctor = "Don't forget to take your";
        talkingDoctor2 = "critter out for recreation!";
        talkingDoctor3 = "You can earn points and";
        talkingDoctor4 = "excercise is good.";
      }
      else if (number == 4)
      {
        talkingDoctor = "You may not give your pet";
        talkingDoctor2 = "night-time snacks, but try";
        talkingDoctor3 = "not to as the food will sit";
        talkingDoctor4 = "in their stomachs all night!";
      }
      else if (number == 5)
      {
        talkingDoctor = "Dairy products are essential";
        talkingDoctor2 = "for growing up to be strong.";
        talkingDoctor3 = "so drink some milk, eat some";
        talkingDoctor4 = "yogurt and enjoy!";
      }
      else 
        if (number == 6)
      {
        talkingDoctor = "Fruits and vegetables contain";
        talkingDoctor2 = "natural sugars, an easy way ";
        talkingDoctor3 = "to get those essential sugars!";
        talkingDoctor4 = "";
      }
    }
    else
      if (sick == true)
    {
      int option = JOptionPane.showConfirmDialog(null, "Would you like to buy instant medicine for $25?", "ATTENTION!", JOptionPane.YES_NO_OPTION);
      if (option == 0) //yes
      {
        if (CritterSitterApp.home.getPoints() >= 25)
        {
          JOptionPane.showMessageDialog(null, "Yay! Your critter is now healthy!");
          CritterSitterApp.home.myCritter.setCritterState("");
          CritterSitterApp.home.myCritter.setIsHealthy(true);
          CritterSitterApp.home.setPoints(CritterSitterApp.home.getPoints() - 25);
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Sorry, you don't have enough money. Please feed it 2 healthy meals for it to return healthy.");
        }   
      }
      else
        if (option == 2)//no
      {
        JOptionPane.showMessageDialog(null, "Alright! As long as you feed your critter 2 healthy meals, it will return healthy.");
      }
    }
  }
  
  /**
   * The drawExit method is used to create the done button in the clipboard part.
   */
  public void drawExit()
  {
    doneButton.setBounds (200, 400, 100, 30);
    add(doneButton);
    doneButton.addActionListener (new ActionListener ()
                                    {
      public void actionPerformed (ActionEvent e)
      {
        okPushed = false;
        exit();
        remove(doneButton);
        repaint();
      }
    }
    );
  }
  
  /**
   * The exit method is used to exit the game when the user is done their check-up.
   * The if statement is used to make sure that the exit method only appears once because counter is equal to 4.
   * 
   */
  public void exit()
  {
    if (counter == 4)
    {
      ok.setBounds(500, 90, 60, 30);
      add(ok);
      talkingDoctor = "Goodbye!";
      talkingDoctor2 = "Stay healthy!";
      talkingDoctor3 = "See you soon!";
      talkingDoctor4 = "";
      repaint();
      counter++;
    }
  }
  
  /**
   * The setStartAgain method is used to set the boolean variable of startAgain..
   * 
   * @param newStartAgain is the new boolean variable value to change startAgain to.
   */
  public void setStartAgain (boolean newStartAgain)
  {
    this.startAgain = newStartAgain; 
  }
  
  /**
   * The setCounter method is used to set the boolean variable of counter..
   * 
   * @param newCounter is the new boolean variable value to change counter to.
   */
  public void setCounter (int newCounter)
  {
    this.counter = newCounter; 
  }
  
  /**
   * This paintComponent method is an overriden method and it is used to draw the background images and draw the strings on the screen.
   * The first if statement is used to see if the critter is sick or not and draw a specific picture, the next if statement is used to save the sick variable as true or false.
   * The next if statment is inside the okButton inside it's actionPerformed is to see if the boolean variable startAgain is true or not to start the whole process over again.
   * The next if statement is also inside the okButton inside it's actionPerformed that has been added to the button. It checks to see what the counter variable is equal to and draws something different for each one.
   * The next if statement is used to see if okPushed is true or false because then it means the last ok button was pushed.
   * The for loop inside this if statement is used to draw the different squares on the clipboard.
   * The if statement inside the for loop is used to see if the value is equal to "too much", "too little" or "".
   * 
   * <p>
   * <b>Instance variables: </b>
   * <p>
   * <b>healthyImage </b> This image is used for the healthy critter.
   * <p>
   * <b>sickImage </b> This image is used for the sick critter.
   * <p>
   * <b>clipboard </b> This image is used to draw the clipboard.
   * <p>
   * <b>x </b> This is the variable to be used in the for loop.
   * 
   * @param g This is the graphics variable used to draw onto the JPanel screen.
   */
  public void paintComponent(Graphics g)
  { 
    Image sickImage = new ImageIcon ("images/DoctorOffice/Sick" + CritterSitterApp.askData.getCritter() + ".png").getImage();
    Image healthyImage = new ImageIcon ("images/DoctorOffice/" + CritterSitterApp.askData.getCritter() + ".png").getImage();
    if (CritterSitterApp.home.myCritter.getCritterState().equals("Sick"))
    {      
      g.drawImage(sickImage, 0, 0,this);
      sick = true;
    }
    else
    {
      g.drawImage(healthyImage, 0, 0,this);
      sick = false;
    }
    Image doctorSpeechBubble = new ImageIcon ("images/DoctorOffice/DoctorSpeech.png").getImage();
    g.drawImage(doctorSpeechBubble, 390, 7,this);
    g.setFont(new Font(Font.SERIF, Font.PLAIN, 13));
    g.drawString(talkingDoctor, 415, 40);
    g.drawString(talkingDoctor2, 415, 55);
    g.drawString(talkingDoctor3, 415, 70);
    g.drawString(talkingDoctor4, 415, 85);
    
    okButton.setBounds (500, 90, 60, 30);
    okButton.setEnabled(true);
    add(okButton);
    okButton.addActionListener (new ActionListener ()
                                  {
      public void actionPerformed (ActionEvent e)
      {
        if (startAgain == true)
        {
          if (counter == 0)
          {
            reportToPatient();
            repaint();
            counter++;
          }
          else if (counter == 1)
          {
            talkToPatient();
            repaint();
            counter++;
          }
          else if (counter == 2)
          {
            askExit();
            repaint();
            counter++;
          }
          else 
            if (counter == 3)
          {
            checkUpHealth();
            okPushed = true;
            repaint();
          }
        }
      }
    }
    );
    if (okPushed == true && counter == 3)
    {
      Image clipboard = new ImageIcon ("images/DoctorOffice/clipboard.png").getImage();
      g.drawImage(clipboard, 0, 0, this);
      checkUpHealth();
      okButton.setEnabled(false);
      for (int x = 0; x < 6; x++)
      {
        double num = Double.parseDouble(checkUpArray[x][0]);
        g.setColor (Color.BLACK);
        g.drawString((Math.round(num * 100) / 100) + "", 260, 190 + (x * 40));
        if (checkUpArray[x][1].equals ("too little"))
        {
          g.setColor(Color.RED);
          g.fillRect(332, 184 + (x * 37), 17, 23);
        }
        else if (checkUpArray[x][1].equals("too much"))
        {
          g.setColor(Color.RED);
          g.fillRect(387, 184 + (x * 37), 17, 23);
        }
        else
        {
          g.setColor(Color.GREEN); 
          g.fillRect(358, 184 + (x * 37), 17, 23);
        }
      }
      counter = 4;
      drawExit();
    }
  }
}
