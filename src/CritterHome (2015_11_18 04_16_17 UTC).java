import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * The CritterHome class is the class that creates the home of the Critter which has a critter.
 * There is an clock to keep track of how much time passed by in the game.
 * The calendar shows what day the user is on and if hovered, the schedule is shown.
 * If the user clicks the door, they get to choose from a menu of options, where to go or what to see.
 * If the user hovers over the fridge, the fridge door opens and if scheduled, they can feed the food to the critter or dispose it.
 * The fullness bar on the bottom of the window indicates food group breakdown of each meal.
 * The piggy bank shows how many points the user has.
 * The miniature poster on the back can be enlargened by clicking and holding it.
 * The critter in the room will change physically depending on whether the user feeds it properly or not.
 * The critter will also talk to the user depending on the time of day and its physical state.
 *
 * @author Jasmine Ou
 * @version 5 06.09.15
 *
 * Time Spent by Jasmine Ou: 20 hours.
 * Time Spent by Laura Wong: 6 hours. 
 * 
 * <p>
 * <b>name: Critter Home.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>userName </b> This String variable is used to save the user's name.
 * <p>
 * <b>message </b> This String variable is used to store what the critter will say.
 * <p>
 * <b>points </b> This int variable is used to store the number of points of the user.
 * <p>
 * <b>bonus </b> This int variable is used to store the number of bonus points of the user.
 * <p>
 * <b>level </b> This int variable is used to store the user's selected level.
 * <p>
 * <b>selectedIndex </b> The int variable of the index of the food item's location in the fridge.
 * <p>
 * <b>day </b> This int variable is used to store the virtual day number which is initalized to 1.
 * <p>
 * <b>hour </b> This int variable is used to store the virtual hour number.
 * <p>
 * <b>minute </b> This int variable is used to store the virtual minute number.
 * <p>
 * <b>meal </b> This int variable is used to store the meal number where 0 is breakfast, 1 is lunch and 2 is dinner.
 * <p>
 * <b>fridgeDialog </b> This int variable is used to store the user's choice when they click on the fridge's food.
 * <p>
 * <b>hoverDoor </b> This boolean variable to store whether the user's mouse entered the picture of the door or not.
 * <p>
 * <b>hoverCritter </b> This boolean variable to store whether the user's mouse entered the picture of the critter or not.
 * <p>
 * <b>hoverCalendar </b> This boolean variable to store whether the user's mouse entered the picture of the calendar or not.
 * <p>
 * <b>hoverFridge </b> This boolean variable to store whether the user's mouse entered the picture of the fridge or not.
 * <p>
 * <b>pressPoster </b> This boolean variable to store whether the user's mouse pressed the poster or not.
 * <p>
 * <b>eaten </b> This boolean variable to store whether the critter just ate something or not.
 * <p>
 * <b>fridge </b> An ArrayList of Food objects in the fridge.
 * <p>
 * <b>selectedItem </b> The Food object that the user selected from their fridge.
 * <p>
 * <b>storeButton </b> This creates an instance of the JButton class with the string title "GroceryStore" passed in.
 * <p>
 * <b>recButton </b> This creates an instance of the JButton class with the string title "Play Outside" passed in.
 * <p>
 * <b>menuButton </b> This creates an instance of the JButton class with the string title "Main Menu" passed in.
 * <p>
 * <b>docButton </b> This creates an instance of the JButton class with the string title "Doctor" passed in.
 * <p>
 * <b>infoButton </b> This creates an instance of the JButton class with the string title "Pamphlet" passed in.
 * <p>
 * <b>nextDayButton </b> This creates an instance of the JButton class with the string title "Next Day" passed in.
 * <p>
 * <b>nextLevelButton </b> This creates an instance of the JButton class with the string title "Next Level" passed in.
 * <p>
 * <b>doneButton </b> This creates an instance of the JButton class with the string title "Done" passed in.
 * <p>
 * <b>resumeButton </b> This creates an instance of the JButton class with the string title "Resume" passed in.
 * <p>
 * <b>ptsLabel </b> This creates an instance of the JLabel class to display the user's points.
 * <p>
 * <b>messageLabel </b> This creates an instance of the JLabel class to display the critter's message.
 * <p>
 * <b>homeDialog </b> This creates an instance of the JDialog class for displaying a menu of choices to the user.
 * <p>
 * <b>myCritter </b> This creates an instance of the Critter class.
 * <p>
 * <b>clock </b> This creates an instance of the Timer class.
 * <p>
 * <b>scheduled </b> The boolean array with 4 rows and 12 columns to store the accessibility of items during certain hours of the day. The rows are store, doctor, fridge and recreation. The columns start from hour 9 and ends at hour 20.
 * <p>
 * <b>balancedArray </b> This String array is used to indicate if the array at each of the food groups is balance, too much or too little.
 * <p>
 * <b>percentages </b> This double array is used to save the percentages of each of the food group that the critter has eaten.
 *
 */
public class CritterHome extends JPanel implements MouseListener, MouseMotionListener
{
  private String userName;
  private String message = "Good Morning!";
  private int points, bonus, level, selectedIndex, hour, minute, meal;
  private int day = 1;
  private int fridgeDialog = -1;
  private boolean hoverDoor, hoverCritter, hoverCalendar, hoverFridge, pressPoster, eaten;
  private ArrayList < Food > fridge = new ArrayList < Food > ();
  private Food selectedItem = new Food ();
  JButton storeButton = new JButton ("Grocery Store");
  JButton recButton = new JButton ("Play Outside");
  JButton menuButton = new JButton ("Main Menu");
  JButton docButton = new JButton ("Doctor");
  JButton infoButton = new JButton ("Pamphlet");
  JButton nextDayButton = new JButton ("Next Day");
  JButton nextLevelButton = new JButton ("Next Level");
  JButton doneButton = new JButton ("Done");
  private JButton resumeButton = new JButton ("Resume");
  private JLabel ptsLabel;
  private JLabel messageLabel;
  JDialog homeDialog;
  Critter myCritter;
  Timer clock;
  private boolean[] [] scheduled={{true, true, true, true, true, false, false, false, true, true, false, true}, {false, false, true, false, false, true, true, true, false, false, false, false},{true, true, false, true, true, false, false, false, false, true, true, false}, {false, true, true, false, true, true, true, true, true, false, false, false}};
  String[] balancedArray = new String [6];
  double[] percentages = new double [6];
  
  /**
   * The CritterHome constructor creates a CritterHome with the specified userName and level.
   * It adds a MouseListener and a MouseMotionListener to the JPanel and sets the layout to null.
   * The ptsLabel and messageLabel is added.
   * The Timer, clock, has an ActionListener that increments time in minutes and hours.
   * If it is the end of one of the meal times, the meal is checked to see if it is healthy or not.
   * If it is lunch or dinner time, meal is incremented.
   * If it is 9 am, wakeUp method is called.
   * If it is 9 pm, the clock is stopped and if the critter is healthy, points is incremented by 10 points.
   * If it is the end of the last day, endLevel is called and level is incremented by one, else endDay is called.
   * If statement checks if minute is 59. If so, hour is incremented, else, minute is incremented.
   * Else, if the fourth if statement is not true, the next if statement checks if minute is 59.
   * If so, hour is incremented and else, minute is incremented.
   * If it is not 9pm, the next three if statements check if a either the grocery store, doctor or recreation is scheduled.
   * If so, they are set to be enabled, else, they are disabled buttons in the GPS dialog.
   * The clock is started.
   * An ActionListener is added to the nextDayButton and nextLevelButton and for the nextDayButton, day is incremented and the nextDay method of both CritterHome and Critter method is called.
   * For the nextDayButton, the nextDay method is called.
   * An ActionListener is added to the pauseButton so that it creates an option pane when pushed and calls the "pauseMethod" method, it also has a shortcut key of the alt + space keys.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> pauseButton </b> This is the pause button on the critter's home screen.
   *
   * @param userName This String variable is used to save the user's name.
   * @param difficulty This int variable is used to store the user's difficulty level.
   * @param critterColour This String variable is used to store the critter's colour.
   */
  public CritterHome (String userName, int difficulty, String critterColour)
  {
    addMouseListener (this);
    addMouseMotionListener (this);
    
    setLayout (null);
    
    
    JButton pauseButton = new JButton(new ImageIcon("images/Critter/pauseButton.jpg"));
    pauseButton.setBounds(685, 497, 66, 27);
    pauseButton.setMnemonic(KeyEvent.VK_SPACE);
    add(pauseButton);
    pauseButton.addActionListener (new ActionListener ()
                                     {
      public void actionPerformed (ActionEvent e)
      {
        pauseMethod();
      }
    }
    );
    
    this.userName = userName;
    level = difficulty;
    
    myCritter = new Critter (critterColour);
    giveAllowance ();
    
    ptsLabel = new JLabel ("$" + points);
    ptsLabel.setBounds (675, 387, 68, 27);
    add (ptsLabel);
    
    messageLabel = new JLabel ();
    messageLabel.setBounds (61, 382, 113, 61);
    add (messageLabel);
    hour = 9; //9
    
    clock = new Timer (500, new ActionListener ()
                         { //500
      public void actionPerformed (ActionEvent evt)
      {
        if ((hour == 11 || hour == 14 || hour == 20) && minute == 0)
        {
          checkHealthyMeal ();
        }
        if ((hour == 12 || hour == 18) && minute == 0)
          meal++;
        if (hour == 9 && minute == 0)
          wakeUp ();
        if (hour == 21)
        {
          clock.stop ();
          if (myCritter.getIsHealthy () == true)
          {
            points += 10;
            bonus+=10;
            JOptionPane.showMessageDialog (null, "You get 10 points for staying healthy at the end of the day!");
          }
          if (day == level + 2) //last day
          {
            endLevel ();
            level++;
          }
          else
            endDay ();
        }
        else
        {
          if (minute == 59)
          {
            minute = 0;
            hour++;
          }
          else
          {
            minute++;
          }
          repaint ();
        }
        if (hour != 21)
        {
          if (!scheduled [0] [hour - 9])
          {
            storeButton.setEnabled (false);
          }
          else
          {
            storeButton.setEnabled (true);
          }
          
          if (!scheduled [1] [hour - 9])
          {
            docButton.setEnabled (false);
          }
          else
          {
            docButton.setEnabled (true);
          }
          
          if (!scheduled [3] [hour - 9])
          {
            recButton.setEnabled (false);
          }
          else
          {
            if (CritterSitterApp.recreation.getDone() == true)
            {
              recButton.setEnabled(false);
            }
            else
            {
              recButton.setEnabled (true);
            }
          }
        }
      }
    }
    );
    clock.start ();
    
    nextDayButton.addActionListener (new ActionListener ()
                                       {
      public void actionPerformed (ActionEvent e)
      {
        myCritter.newDay ();
        day++;
        newDay ();
      }
    }
    );
    
    nextLevelButton.addActionListener (new ActionListener ()
                                         {
      public void actionPerformed (ActionEvent e)
      {
        newLevel ();
      }
    }
    );
    
    resumeButton.addActionListener (new ActionListener ()
                                      {
      public void actionPerformed (ActionEvent e)
      {
        clock.start ();
        homeDialog.dispose ();
      }
    }
    );
  }
  
  /**
   * the pauseMethod method is used to create the pause dialog JOptionPane that asks the user if they want to resume the game or go back to the main menu (also pauses the game).
   *
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> options </b> This is an array of JButtons for the pause method JOptionpane
   * <p> 
   * <b> pausePane </b> This is the JOptionPane that tells the user the game is paused and asks them if they want to resume or go to the main menu.
   */
  public void pauseMethod()
  {
    clock.stop ();
    JButton[] options = {resumeButton, menuButton};
    JOptionPane pausePane = new JOptionPane ("Game Paused", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options [0]);
    homeDialog = pausePane.createDialog (this, "Day " + day);
    homeDialog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);
    homeDialog.setVisible (true);
  }
  
  /**
   * The newLevel method properly refreshes the days and points back to the first value as well as reset critter variable values.
   */
  public void newLevel ()
  {
    day = 1;
    points = 0;
    myCritter.newLevel ();
    newDay ();
  }
  
  
  /**
   * The newDay method properly gives the daily allowance, resets the time to 9 am, resets meal to breakfast, starts the clock, repaints the panel and disposed of the dialog as well as sets the counter in recreation to 0 and sets the variable done in counter to false.
   */
  public void newDay ()
  {
    giveAllowance ();
    hour = 9; //9
    meal = 0;
    clock.start ();
    repaint ();
    homeDialog.dispose ();
    CritterSitterApp.recreation.setCounter(0);
    CritterSitterApp.recreation.setDone(false);
  }
  
  
  /**
   * The endDay method is for displaying a dialog of options to the user at the end of a day.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> options [] </b> An array of JButtons for the options of the endDayPane.
   * <p>
   * <b> endDayPane </b> An instance of the JOptionPane for the end of a day.
   */
  public void endDay ()
  {
    JButton[] options = {nextDayButton, menuButton};
    JOptionPane endDayPane = new JOptionPane ("End of Day " + day, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, new ImageIcon ("images/Critter/doneDay.png"), options);
    homeDialog = endDayPane.createDialog (this, "Day " + day);
    homeDialog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);
    homeDialog.setVisible (true);
  }
  
  
  
  /**
   * The endLevel method is for displaying a dialog of options to the user at the end of a level.
   * The first if statement checks if it the last level which then disables the next level button.
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> options [] </b> An array of JButtons for the options of the endLevelPane.
   * <p>
   * <b> endLevelPane </b> An instance of the JOptionPane for the end of a level.
   */
  public void endLevel ()
  {
    JButton[] options = {nextLevelButton, doneButton};
    if (level == 3)
      nextLevelButton.setEnabled (false);
    JOptionPane endLevelPane = new JOptionPane ("Congratulations " + userName + "!\nYou have completed Level " + level + ".\nYou have " + points + " points and " + bonus + " bonus points\nYour total score is: " + calcScore () + "\nPress 'Next Level' only if you want to proceed to the next level.", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, new ImageIcon ("images/Critter/yay.jpg"), options);
    homeDialog = endLevelPane.createDialog (this, "End of Level " + level);
    homeDialog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);
    homeDialog.setVisible (true);
    CritterSitterApp.highScores.updateScores(calcScore(), userName, level);
  }
  
  
  /**
   * The calcScore method calculates the user's score by adding their total points and bonus points together
   * @return an integer that is the sum of the points and bonus.
   *
   */
  private int calcScore ()
  {
    return points + bonus;
  }
  
  /**
   * The setButtonsActionListener method adds an ActionListener to each button and sets their action commands.
   *
   * @param al is the ActionListener passed in to be added to the buttons.
   */
  public void setButtonsActionListener (ActionListener al)
  {
    storeButton.addActionListener (al);
    recButton.addActionListener (al);
    menuButton.addActionListener (al);
    docButton.addActionListener (al);
    infoButton.addActionListener (al);
    doneButton.addActionListener (al);
    
    storeButton.setActionCommand ("Store");
    recButton.setActionCommand ("Recreation");
    menuButton.setActionCommand ("Menu");
    docButton.setActionCommand ("Doctor");
    infoButton.setActionCommand ("Pamphlet");
    doneButton.setActionCommand ("Done");
  }
  
  
  /**
   *
   * The addFood method adds food to the fridge by quantity and repaints the fridge graphics.
   * The for loop starts from 0, runs while less than quantity and increments by one each in order to add the correct amount of the food Object to the fridge.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> x </b> The integer for loop variable that starts from 0, runs while less than quantity and increments by one each in order to add the correct amount of the food Object to the fridge.
   *
   * @param food is the Food object to be added to the fridge
   * @param quantity is the integer amount of the Food object to be added to the fridge.
   */
  public void addFood (Food food, int quantity)
  {
    for (int x = 0 ; x < quantity ; x++)
    {
      fridge.add (food);
    }
    repaint ();
  }
  
  
  /**
   * The giveAllowance method increments points to the critter each day.
   * If the level is easy, their allowance per day is 20 points, else if the level is medium, they are given 15 points, ese they are given 10 points.
   */
  public void giveAllowance ()
  {
    if (level == 1)
    {
      points += 20;
    }
    else if (level == 2)
    {
      points += 15;
    }
    else
    {
      points += 10;
    }
  }
  
  
  /**
   * The checkHealthyMeal method checks to see if the user incorporated at least 3 food groups within the correct percentage range.
   * The for loop runs twice because the fruits and vegetables food group and the grains food group have the same percentage range.
   * If it is what the user fed to the critter is within the percentage range, then the balance variable is incremented by one.
   * The next four if statements check whether the next four food groups are fed within their specific percentage range and balance is incremented if so.
   * If balance is less than three (unbalanced meal), the critter's sickCounter is incremented by one and if it is already 3, the critter's state is set to sick and isHealthy is false.
   * Else, if the first if statement is false (balanced meal) the next if statement checks if balance is larger or equal to 4.
   * If so, the user gets a points bonus of 5, else, they get a points bonus of 2 for a healthy meal.
   * If the critter is already sick, the recoverCounter is incremented and if the recoverCounter is already 2 then the critter is healthy again.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> x </b> The integer for loop variable that starts from 0, runs while less than 2 and increments by one each time.
   * <p>
   * <b> percentage </b> The double that stores the percentage of a food group compared to the total foods fed.
   * <p>
   * <b> balance </b> The integer that stores how many food groups are in the correct range.
   *
   */
  public void checkHealthyMeal()
  {
    double percentage = 0;
    int balance = 0;
    for (int x = 0; x < 2; x++)
    {
      percentage = myCritter.calcPercentage(meal, x);
      percentages [x] = percentage;
      if (percentage >= 20 && percentage <= 40)
      {
        balance++;
        balancedArray[x] = "";
      }
      else if (percentage > 40)
      {
        balancedArray[x] = "too much";
      }
      else
        if (percentage < 20)
      {
        balancedArray[x] = "too little";
      }
    }
    percentage = myCritter.calcPercentage(meal, 2);
    percentages [2] = percentage;
    if (percentage >= 5 && percentage <= 25)
    {
      balance++;
      balancedArray[2] = "";
    }
    else if (percentage  > 25)
    {
      balancedArray[2] = "too much";
    }
    else
      if (percentage < 5)
    {
      balancedArray[2] = "too little";
    }
    percentage = myCritter.calcPercentage(meal, 3);
    percentages [3] = percentage;
    if (percentage >= 5 && percentage <= 20)
    {
      balance++;
      balancedArray[3] = "";
    }   
    else if (percentage  > 20)
    {
      balancedArray[3] = "too much";
    }
    else
      if (percentage < 5)
    {
      balancedArray[3] = "too little";
    }
    percentage = myCritter.calcPercentage(meal, 4);
    percentages [4] = percentage;
    if (percentage >= 0 && percentage <= 15)
    {
      balance++;
      balancedArray[4] = "";
    }
    else if (percentage  > 15)
    {
      balancedArray[4] = "too much";
    }
    else
    {
      balancedArray[4] = "too little";
    }
    percentage = myCritter.calcPercentage(meal, 5);
    percentages [5] = percentage;
    if (percentage > 0 && percentage <= 50)
    {
      balance++;
      balancedArray[5] = "";
    }
    else if (percentage  > 50)
    {
      balancedArray[5] = "too much";
    }
    else
    {
      balancedArray[5] = "too little";
    }
    if (balance < 3)//unbalanced meal
    {
      myCritter.setSickCounter(myCritter.getSickCounter()+1);
      if (myCritter.getSickCounter()==3)//if this is the third unbalanced meal
      {
        myCritter.setCritterState("Sick");
        myCritter.setIsHealthy(false);
      }
    }
    else //balanced meal
    {
      if (balance >= 4)
      {
        points += 5;
        bonus+=5;
        JOptionPane.showMessageDialog(null, "You get 5 points for a food group combo!");
      }
      else
      {
        points += 2;
        bonus+=2;
        JOptionPane.showMessageDialog(null, "You get 2 points for a healthy meal!");
      }
      if (myCritter.getIsHealthy() == false)//already sick
      {
        myCritter.setRecoverCounter(myCritter.getRecoverCounter()+1);
        if (myCritter.getRecoverCounter() == 2)//is this is the second healthy meal during sickness
        {
          myCritter.setCritterState("");
          myCritter.setIsHealthy(true);
          myCritter.setRecoverCounter(0);
          myCritter.setSickCounter(0);
        }
      }
    }
    grow();
    repaint();
  }
  
  
  /**
   * The grow method makes the critter grow if necessary.
   * The first if statement checks if the critter is not an adult (fully grown) and healthy
   * If so, the critter's growthCounter increments by one.
   * The next if statement checks if the growthCounter equals 2.
   * If the critter is a baby, then it becomes a teen and num is set to 15, else, it becomes an adult and num is set to 35.
   * Then, both points and bonus are incremented by num and a JOptionPane tells the user that they earned num points for growing.
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> num </b> The integer storing the number of points and bonuses to be added if needed.
   */
  public void grow ()
  {
    int num=0;
    if (!myCritter.getGrowthStage ().equals ("Adult") && myCritter.getIsHealthy () == true)
    {
      myCritter.setGrowthCounter (myCritter.getGrowthCounter () + 1);
      if (myCritter.getGrowthCounter () == 2)
      {
        myCritter.setGrowthCounter (0);
        if (myCritter.getGrowthStage ().equals ("Baby"))
        {
          num=15;
          myCritter.setGrowthStage ("Teen");
        }
        else
        {
          num=35;
          myCritter.setGrowthStage ("Adult");
        }
        points+=num;
        bonus+=num;
        JOptionPane.showMessageDialog (null, "You get "+num+" points for growing!");
      }
    }
    repaint();
  }
  
  
  /**
   * The wakeUp method is for every morning that the critter wakes up.
   * If the critter is healthy, the critter's state is set to "", else it is set to "Sick".
   */
  public void wakeUp ()
  {
    if (myCritter.getIsHealthy () == true)
    {
      myCritter.setCritterState ("");
    }
    else
      myCritter.setCritterState ("Sick");
  }
  
  
  /**
   * The getFridgeSpace method gets the remaining fridge space where its maximum capacity is 12 items.
   *
   * @return the integer storing the remaining fridge space.
   */
  public int getFridgeSpace ()
  {
    return 12 - fridge.size ();
  }
  
  
  /**
   * The getUserName method gets the user's name.
   *
   * @return userName the String variable of the user's name.
   */
  public String getUserName ()
  {
    return userName;
  }
  
  
  /**
   * The getLevel method gets the user's level.
   *
   * @return level the int variable of the user's level.
   */
  public int getLevel ()
  {
    return level;
  }
  
  
  /**
   * The getPoints method gets the user's points.
   *
   * @return points the int variable of the user's points.
   */
  public int getPoints ()
  {
    return points;
  }
  
  
  /**
   * The setPoints method sets the user's points.
   *
   * @param points This is the points that it will be set to.
   */
  public void setPoints (int points)
  {
    this.points = points;
  }
  
  
  /**
   * The getDay method gets the user's virtual day.
   *
   * @return day the int variable of the user's virtual day.
   */
  public int getDay ()
  {
    return day;
  }
  
  
  /**
   * The getHour method gets the user's virtual hour.
   *
   * @return hour the int variable of the user's virtual hour.
   */
  public int getHour ()
  {
    return hour;
  }
  
  
  /**
   * The getMinute method gets the user's virtual minute.
   *
   * @return minute the int variable of the user's virtual minute.
   */
  public int getMinute ()
  {
    return minute;
  }
  
  
  /**
   * The handleFridgeDialog method handles the user's choice in the fridge dialog.
   * The first if statement checks if the user did not close the dialog.
   * If true, the next if statement checks if the user chose to feed their critter and if the critter is too full to eat more food,
   * which means that the nutrient value of the food and the critter's fullness level exceeds 10.
   * If true, a JOptionPane tells the user that the critter is too full to eat anymore.
   * Else, if they select to feed the critter, the food is added to the stomach, the foodGroupCount is set and eaten is set to true.
   */
  public void handleFridgeDialog ()
  {
    if (fridgeDialog != JOptionPane.CLOSED_OPTION)
    {
      if (fridgeDialog == 0 && myCritter.getFullness (meal) + selectedItem.getNutrientValue () > 10) //meal
      {
        JOptionPane.showMessageDialog (this, "Your critter is too full to eat this!");
      }
      else
      {
        if (fridgeDialog == 0)
        {
          myCritter.addStomach (selectedItem, meal); //meal
          myCritter.setFoodGroupCount (selectedItem.getNutrientValue (), meal, selectedItem.getFoodGroup ());
          eaten = true;
        }
        fridge.remove (selectedIndex);
        repaint ();
      }
    }
  }
  
  
  /**
   * The fullnessBar method displays the bar that represents how full the critter is per meal.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> x </b> The integer for loop variable that starts from 0, runs while less than 6 and increments by one each time.
   * <p>
   * <b> colours </b> An array of Colors for the fullness bar.
   * <p>
   * <b> currentXPoint </b> The integer storing the x coordinate the fullness bar is currently reaching up to.
   * <p>
   * <b> width </b> The integer storing the width of each section of the bar.
   * <p>
   * <b> count </b> The integer storing how much of a food group is fed to the critter.
   *
   * @param g is the Graphics class to be used to display graphics.
   */
  public void fullnessBar (Graphics g)
  {
    Color[] colours = {new Color (151, 210, 103), new Color (248, 212, 84), new Color (131, 205, 242), new Color (255, 145, 166), new Color (126, 13, 98), new Color (118, 231, 176) };
    g.setFont (new Font (Font.SERIF, Font.BOLD, 12));
    double currentXPoint = 15.0;
    double width = 0.0;
    double count = 0.0;
    for (int x = 0 ; x < 6 ; x++)
    {
      if ((count = (myCritter.getFoodGroupCount (meal, x))) != 0)
      {
        g.setColor (colours [x]);
        width = count * 66;
        g.fillRect ((int) currentXPoint, 497, (int) width, 28);
        currentXPoint += width;
        g.setColor (Color.white);
        g.drawString (((int) (myCritter.calcPercentage (meal, x)) + "%"), (int) (currentXPoint - width / 2) - 10, 516); //47
      }
    }
  }
  
  
  /**
   * The mouseClicked method listens for a mouse release.
   *
   * The first if statement checks if the fridge is scheduled to be accessible.
   * The for loop starts from 0, runs while less than the size of the fridge times 63 and increments by 63 each time.
   * That for loop is for checking through every icon in the fridge.
   * The first if statement checks if the user clicked one of the food icons.
   * If true, the selectedItem and selectedIndex variables are assigned according to the Food item clicked.
   * The next if statement assigns the correct String to the number value of foodGroup where O is fruits and vegetables, 1 is grains, 2 is dairy, 3 is protein, 4 is sugars and fats and 5 is water.
   * Then the fridgeDialog is shown to ask if the user wants to feed or throw out the food.
   * The next if statement checks if the user clicked on the door.
   * If true, the leaveDialog asks the user where they want to go by showing a menu of options to choose from.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> fridgeOptions [] </b> Object of the options of the fridgeDialog.
   * <p>
   * <b> col </b> The integer loop variable that starts from 0, runs while less than the size of the fridge times 63 and increments by 63 each time.
   * <p>
   * <b> options [] </b> An array of instances of the JButton class for the options of the leaveDialog.
   * <p>
   * <b> leaveOptionPane </b> An instance of the JOptionPane that asks the user where they want to go.
   * <p>
   * <b> foodGroup </b> The String storing the food group of the food item.
   *
   * @param e MouseEvent that stores the user's mouse actions.
   */
  public void mouseClicked (MouseEvent e)
  {
    String foodGroup = "";
    if (scheduled [2] [hour - 9])
    {
      for (int col = 0 ; col < fridge.size () * 63 ; col += 63)
      {
        if (GroceryStore.correctCol (e.getX (), col + 8) && e.getY () >= 8 && e.getY () <= 68)
        {
          selectedItem = fridge.get (col / 63);
          selectedIndex = col / 63;
          
          if (selectedItem.getFoodGroup () == 0)
            foodGroup = "Fruits and Vegetables";
          else if (selectedItem.getFoodGroup () == 1)
            foodGroup = "Grains";
          else if (selectedItem.getFoodGroup () == 2)
            foodGroup = "Dairy";
          else if (selectedItem.getFoodGroup () == 3)
            foodGroup = "Meats and Alternatives";
          else if (selectedItem.getFoodGroup () == 4)
            foodGroup = "Sugars and Fats";
          else
          {
            if (selectedItem.getFoodGroup () == 5)
              foodGroup = "Water";
          }
          
          Object[] fridgeOptions = {"Feed Critter", "Throw Out"};
          fridgeDialog = JOptionPane.showOptionDialog (this, selectedItem.getName () + "\nFood Group: " + foodGroup
                                                         + "\nNutritional Value: " + selectedItem.getNutrientValue (),
                                                       "My Fridge",
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon (selectedItem.getIcon ()),
                                                       fridgeOptions, fridgeOptions [0]);
          handleFridgeDialog ();
        }
      }
    }
    if (e.getX () >= 26 && e.getX () <= 135 && e.getY () >= 275 && e.getY () <= 405)
    {
      JButton[] options = {storeButton, recButton, docButton, infoButton, menuButton};
      JOptionPane leaveOptionPane = new JOptionPane ("Where do you want to go?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, new ImageIcon ("images/Critter/gps.jpg"), options);
      homeDialog = leaveOptionPane.createDialog (this, "GPS Navigator Menu");
      homeDialog.setVisible (true);
    }
  }
  
  
  /**
   * The mouseMoved method listens for a mouse movement.
   * The first if statement checks if the user hovered over the door and if true, sets hoverDoor to true, else, false.
   * The next if statement checks if the user hovered over the critter and if true, sets hoverCritter to true, else, false.
   * The next if statement checks if the user hovered over the calendar and if true, sets hoverCalendar to true, else, false.
   * The next if statement checks if the user hovered over the fridge and if true, sets hoverFridge to true, else, false.
   *
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mouseMoved (MouseEvent e)
  {
    
    if (e.getX () >= 26 && e.getX () <= 135 && e.getY () >= 275 && e.getY () <= 405)
    {
      hoverDoor = true;
    }
    else
    {
      hoverDoor = false;
    }
    if (e.getX () >= 200 && e.getX () <= 540 && e.getY () >= 140 && e.getY () <= 470)
    {
      hoverCritter = true;
    }
    else
    {
      hoverCritter = false;
    }
    if (e.getX () >= 605 && e.getX () <= 760 && e.getY () >= 85 && e.getY () <= 260)
    {
      hoverCalendar = true;
    }
    else
    {
      hoverCalendar = false;
    }
    if (e.getX () >= 8 && e.getX () <= 763 && e.getY () >= 7 && e.getY () <= 70)
    {
      hoverFridge = true;
    }
    else
    {
      hoverFridge = false;
    }
    repaint ();
  }
  
  
  /**
   * The mousePressed method listens for a mouse press.
   * If the user presses the miniature nutrition poster, pressPoster is set to true in order to show the enlargened version of the poster.
   * Also, the ptsLabel is not visible.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mousePressed (MouseEvent e)
  {
    if (e.getX () >= 174 && e.getX () <= 266 && e.getY () >= 130 && e.getY () <= 194)
    {
      pressPoster = true;
      ptsLabel.setVisible (false);
    }
    repaint ();
  }
  
  
  /**
   * The mouseEntered method listens for a mouse enter.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mouseEntered (MouseEvent e)
  {
  }
  
  
  /**
   * The mouseExited method listens for a mouse exit.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mouseExited (MouseEvent e)
  {
  }
  
  
  /**
   * The mouseReleased method listens for a mouse release.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mouseReleased (MouseEvent e)
  {
    pressPoster = false;
    ptsLabel.setVisible (true);
    repaint ();
  }
  
  
  /**
   * The mouseDragged method listens for a mouse drag.
   * @param e MouseEvent that stores the user's mouse actions.
   */
  @ Override
  public void mouseDragged (MouseEvent e)
  {
  }
  
  
  /**
   * Paints the graphics on to the JPanel.
   * If hour is between 12 and 14, timeOfDay is Noon.
   * Else if hour is between 15 and 17, timeOfDay is Afternoon.
   * Else if hour is 18 or 19, timeOfDay is Evening.
   * Else, if hour is 20, timeOfDay is LateNight and critterType is Asleep.
   * If hoverDoor is true, the door is drawn with a glowing edge.
   * If hoverCritter is true, the speech bubble is drawn and the messageLabel is set to the text of message, else, a new message is generated.
   * If hoverCalendar is true, the schedule is drawn.
   * If eaten is true and the user chooses to feed the critter in the fridgeDialog, the speech bubble displays the food eaten.
   * If the hour more than 12, the normal clock hour is displayed instead of military time.
   * If minute has one digit, a leading zero image is drawn before the corresponding minute image.
   * Else, both minute digits are displayed with the correct image numbers
   * If hour has one digit, a leading zero image is drawn before the corresponding hour image.
   * Else, both hour digits are displayed with the correct image numbers
   * The for loop starts from 0, runs while less than the amount of food in the fridge and increments by one each time.
   * If it is not the 21st hour and the fridge is not hovered, then a fridge door is displayed.
   * A fridge door with a sticky is displayed if it is locked, else, a plain one is displayed.
   * If the user presses poster, the enlarged poster is displayed.
   *
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> x</b> The integer loop variable that starts from 0, runs while less than the amount of food in the fridge and increments by one each time in order to display all the food in the fridge.
   * <p>
   * <b>speechBubble</b> This creates an instance of the Image class to store the image of the speech bubble.
   * <p>
   * <b>timeOfDay </b>The String that stores the time of day.
   * <p>
   * <b>regHour </b> The integer that stores the hour for a twelve hour clock which is initialized to hour.
   */
  @ Override
  public void paintComponent (Graphics g)
  {
    Image speechBubble = new ImageIcon ("images/Critter/SpeechBubble.png").getImage ();
    String timeOfDay = "Morning";
    int regHour = hour;
    
    g.drawImage (new ImageIcon ("images/Critter/EmptyHomeBG.jpg").getImage (), 0, 0, this);
    
    ptsLabel.setText ("$" + points);
    
    g.setFont (new Font ("Cooper Black", Font.PLAIN, 30));
    g.drawString ("DAY", 650, 176);
    g.setFont (new Font ("Elephant", Font.PLAIN, 50));
    g.drawString ("" + day, 670, 228);
    
    if (hour >= 12 && hour <= 14)
    {
      timeOfDay = "Noon";
    }
    else if (hour >= 15 && hour <= 17)
    {
      timeOfDay = "Afternoon";
    }
    else if (hour == 18 || hour == 19)
    {
      timeOfDay = "Evening";
    }
    else
    {
      if (hour >= 20)
      {
        timeOfDay = "LateNight";
        if (minute == 30)
          myCritter.setCritterState ("Asleep");
      }
    }
    
    //draw critter
    g.drawImage (new ImageIcon ("images/Critter/" + myCritter.getColour () + "/" + myCritter.getCritterState () + myCritter.getColour () + myCritter.getGrowthStage () + ".png").getImage (), 200, 140, this);
    
    //draw window view
    g.drawImage (new ImageIcon ("images/Critter/" + timeOfDay + ".png").getImage (), 4, 70, this);
    
    //draw poster
    g.drawImage (new ImageIcon ("images/Critter/NutritionPoster.png").getImage (), 171, 127, this);
    
    if (hoverDoor)
    {
      g.drawImage (new ImageIcon ("images/Critter/GlowDoor.png").getImage (), 0, 250, this);
    }
    if (hoverCritter)
    {
      g.drawImage (speechBubble, 30, 355, this);
      g.setFont (new Font (Font.SERIF, Font.PLAIN, 12));
      messageLabel.setText (message);
      messageLabel.setVisible (true);
    }
    else
    {
      if (!hoverCritter)
      {
        message = myCritter.getMessage (hour, minute);
        messageLabel.setVisible (false);
      }
    }
    if (hoverCalendar)
    {
      g.drawImage (new ImageIcon ("images/Critter/Schedule.png").getImage (), 17, 85, this);
    }
    
    if (eaten == true && fridgeDialog == 0)
    {
      g.drawImage (speechBubble, 30, 355, this);
      g.drawImage (new ImageIcon ((myCritter.getStomach ()).getIcon ()).getImage (), 85, 385, this);
      eaten = false;
      fridgeDialog = -1;
    }
    
    fullnessBar (g);
    
    if (hour > 12)
    {
      regHour = hour - 12;
    }
    
    if (regHour < 10)
    {
      g.drawImage (new ImageIcon ("images/Critter/0.png").getImage (), 632, 289, this);
      g.drawImage (new ImageIcon ("images/Critter/" + regHour + ".png").getImage (), 656, 289, this);
    }
    else
    {
      g.drawImage (new ImageIcon ("images/Critter/" + hour / 10 + ".png").getImage (), 632, 289, this);
      g.drawImage (new ImageIcon ("images/Critter/" + hour % 10 + ".png").getImage (), 656, 289, this);
    }
    
    
    if (minute < 10)
    {
      g.drawImage (new ImageIcon ("images/Critter/0.png").getImage (), 696, 289, this);
      g.drawImage (new ImageIcon ("images/Critter/" + minute + ".png").getImage (), 720, 289, this);
    }
    else
    {
      g.drawImage (new ImageIcon ("images/Critter/" + minute / 10 + ".png").getImage (), 696, 289, this);
      g.drawImage (new ImageIcon ("images/Critter/" + minute % 10 + ".png").getImage (), 720, 289, this);
    }
    
    for (int x = 0 ; x < fridge.size () ; x++)
    {
      g.drawImage (new ImageIcon ((fridge.get (x)).getIcon ()).getImage (), x * 63 + 8, 8, this);
    }
    
    if (hour != 21 && hoverFridge == false)
    {
      if (scheduled [2] [hour - 9] == false)
        g.drawImage (new ImageIcon ("images/Critter/fridgeDoor.png").getImage (), 8, 7, this);
      else
        g.drawImage (new ImageIcon ("images/Critter/fridgeDoorNorm.png").getImage (), 8, 7, this);
    }
    if (pressPoster)
    {
      g.drawImage (new ImageIcon ("images/Critter/EatWellPlate.png").getImage (), 27, 92, this);
    }
  }
}
