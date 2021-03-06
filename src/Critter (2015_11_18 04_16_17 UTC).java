import java.util.*;
/**
 * The Critter class is the class that creates the actual critter.
 *
 * @author Jasmine Ou
 * @version 5 06.09.15
 * 
 * Time Spent by Jasmine Ou: 8 hours.
 * Time Spent by Laura Wong: 1 hour.
 * 
 * <p>
 * <b>name: Critter.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>stomach</b>An ArrayList of Food objects that stores the food that the critter is fed.
 * <p>
 * <b>fullness[]</b>An array of 3 doubles that stores the fullness level of the critter for each of the three meals.
 * <p>
 * <b>foodGroupCount[][]</b>A 2D array of 3 rows and 5 columns that stores the total nutrient value of a certain food group consumed for a certain meal.
 * <p>
 * <b>colour</b> This String variable is used to store the critter's colour.
 * <p>
 * <b>critterState </b> This String variable is used to store the critter's state of either healthy (""), "Sick" or "Asleep".
 * <p>
 * <b>growthStage </b> This String variable is used to store the level of growth the critter is at currently of either "Baby", "Teen" or "Adult".
 * <p>
 * <b>isHealthy </b> This boolean variable is used to see if the critter is healthy or not.
 * <p>
 * <b>sickCounter </b> This int variable is used count up to 3 unhealthy meals until the critter gets sick.
 * <p>
 * <b>recoverCounter </b> This int variable is used count up to 2 healthy meals for the critter to recover.
 * <p>
 * <b>growthCounter </b> This int variable is used count when the critter will grow next. The critter grows for every two meals it is healthy.
 */
public class Critter
{
  private ArrayList<Food>stomach=new ArrayList<Food>();
  private double []fullness=new double [3];
  private double [][]foodGroupCount=new double [3][6];
  private String colour,critterState,growthStage;
  private boolean isHealthy = true;
  private int sickCounter,recoverCounter,growthCounter;
  
  /**
   * The Critter constructor creates a critter sitter with the specified colour.
   * 
   * @param colour This String variable is used to store the critter's colour.
   */
  public Critter (String colour)
  {
    this.colour = colour;
    critterState="";
    growthStage="Baby";
  }
  
  /**
   * The getMessage method gets what the critter will say depending on the time and its state.
   * If randNum is 0.
   * If the critter is asleep, message is set to "zZzZ".
   * Else, if it is breakfast time and they did not eat anything for breakfast yet, message is set to "Feed me breakfast!".
   * Else, if it is lunch time and they did not eat anything for lunch yet, message is set to "Feed me lunch!".
   * Else, if it is dinner time and they did not eat anything for dinner yet, message is set to "Feed me dinner!".
   * Else, if it is recreation time, message is set to "I want to play outside!".
   * If the first if statement is false, it checks if randNum is 1.
   * If so, the next if statement checks if the critter is starting to recover or slowly getting sick in which message is set to the appropriate message.
   * The last else (if randNum is 3), has an if statement that checks if it is the morning and message is set to "Good Morning!".
   * Else, if it is night, message is set to "I'm sleepy...".
   * 
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> message </b> The String of the message that the critter will say
   * <p>
   * <b> randNum </b> The integer storing a randum number to generate a randum message.
   * 
   * @param hour is the integer to store the hour number.
   * @param minute is the integer to store the minute number.
   * @return message is the String that the critter will say.
   */
  public String getMessage(int hour,int minute)
  {
    String message="<html>\u266C Clap along if you know what happiness is to you \u266C</html>";
    // (int)(Math.random() * (max - min) + 1) + min;
    int randNum=(int)(Math.random()*3);
    if (critterState.equals("Asleep"))
      message="zZzZ";
    else if (randNum==0)
    {
      if ((hour==9||hour==10)&&fullness[0]==0)
        message= "Feed me breakfast!";
      else if ((hour==12||hour==13)&&fullness[1]==0)
        message= "Feed me lunch!";
      else if ((hour==18||hour==19)&&fullness[2]==0)
        message= "Feed me dinner!";
      else 
      {
        if ((hour==10||hour==11)||hour>=13&&hour<=17)
          message= "<html>I want to play outside!</html>";
      }
    }
    else if (randNum==1)
    {
      if (recoverCounter==1)
        message="<html>I'm starting to feel a little better.</html>"; 
      else if (sickCounter==2)
        message="<html>Please feed me well!</html>";
      else if (sickCounter==1)
        message="<html>I have to eat healthy to grow!</html>";
      else
        message="<html>\u266B I'm fluffy and I know it \u266B</html>";
    }
    else
    {
      if (hour>=9&&hour<=11)
        message= "Good Morning!";
      else 
      {
        if (hour==20&&minute<30)
          message="I'm sleepy...";
      }
    }
    return message;
  }
  
  /**
   * The newDay method refreshes the variables for a new day.
   * The first for loop starts from 0, runs while less than 3 and increments by one each time. 
   * The second for loop starts from 0, runs while less than 6 and increments by one each time. 
   * Those two for loops is to assign 0 to all the locations of the 2D array foodGroupCount.
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> x </b> The integer loop variable that starts from 0, runs while less than 3 and increments by one each time. 
   * <p>
   * <b> y </b> The integer loop variable that starts from 0, runs while less than 6 and increments by one each time. 
   */
  public void newDay()
  {
    for (int x=0;x<3;x++)
    {
      fullness[x]=0;
      for (int y=0;y<6;y++)
      {
        foodGroupCount[x][y]=0;
      }
    }
  }
  
  /**
   * The newLevel method resets variabes to start a new level.
   */
  public void newLevel()
  {
    newDay();
    critterState="";
    growthStage="Baby";
  }
  
  /**
   * The calcPercentage method calculates the percentage of consumption for a foodGroup compared to the their fullness for the meal.
   * 
   * <p>
   * <b>Local Variable Dictionary: </b>
   * <p>
   * <b> percentage </b> The double value of the percentage of the foodGroupCount to the total fullness.
   * 
   * @param meal is the integer that stores the meal number where 0 is breakfast, 1 is lunch and 2 is dinner.
   * @param foodGroup is the integer that stores the foodGroup number where O is fruits and vegetables, 1 is grains, 2 is dairy, 3 is protein, 4 is sugars and fats and 5 is water.
   * @return percentage which is the double value of the percentage.
   */ 
  public double calcPercentage(int meal, int foodGroup)
  {
    double percentage=0;
    if (fullness[meal]!=0)
    {
      percentage=foodGroupCount[meal][foodGroup]/fullness[meal]*100;
    }
    return percentage;
  }
  
  /**
   * The getFoodGroupCount method returns the integer foodGroupCount for the specified meal and food group.
   * 
   * @param meal is the integer that stores the meal number where 0 is breakfast, 1 is lunch and 2 is dinner.
   * @param foodGroup is the integer that stores the foodGroup number where O is fruits and vegetables, 1 is grains, 2 is dairy, 3 is protein, 4 is sugars and fats and 5 is water.
   * @return foodGroupCount[][] which is the double that stores the total nutrient value of a foodGroup consumed for one specific meal. 
   */
  public double getFoodGroupCount(int meal, int foodGroup)
  {
    return foodGroupCount[meal][foodGroup];
  }
  
  /**
   * The setFoodGroupCount method adds nutrientValue to the foodGroupCount for a specific meal and foodGroup.
   * 
   * @param nutrientValue is the double value that stores how much the food item will fill the critter up.
   * @param meal is the integer that stores the meal number where 0 is breakfast, 1 is lunch and 2 is dinner.
   * @param foodGroup is the integer that stores the foodGroup number where O is fruits and vegetables, 1 is grains, 2 is dairy, 3 is protein, 4 is sugars and fats and 5 is water..
   */
  public void setFoodGroupCount(double nutrientValue,int meal, int foodGroup)
  {
    foodGroupCount[meal][foodGroup]+=nutrientValue;
  }
  
  /**
   * The getStomach method gets the Food stored in the stomach at a specific index. 
   * 
   * @return the Food object that was last fed to the critter.
   */
  public Food getStomach()
  {
    return stomach.get(stomach.size()-1);
  }
  
  /**
   * The addStomach method adds food the the stomach and increments their fullness of a specific meal.
   * 
   * @param food is the Food object to be added to the stomach.
   * @param meal is the integer represetning the meal that the nutrientValue should be added to.
   */
  public void addStomach(Food food, int meal)
  {
    stomach.add(food);
    fullness[meal]+=food.getNutrientValue();
  }
  
  /**
   * The getFullness method gets the double of the fullness for a specific meal.
   * 
   * @param meal is the integer that stores the meal number where 0 is breakfast, 1 is lunch and 2 is dinner.
   * @return an double of the fullness of the critter for a specific meal. 
   */
  public double getFullness(int meal)
  {
    return fullness[meal];
  }
  
  /**
   * The getGrowthStage method is used to get the critter's growth stage.
   * 
   * @return growthStage the String variable of the critter's growth stage.
   */
  public String getGrowthStage()
  {
    return growthStage;
  }
  
  /**
   * The setGrowthStage method is used to set the critter's new growth stage.
   * 
   * @param growthStage This is the String of the new growth stage for the critter.
   */
  public void setGrowthStage(String growthStage)
  {
    this.growthStage = growthStage;
  }
  
  /**
   * The getColour method is used to get the critter's colour.
   * 
   * @return colour the String variable of the critter's colour.
   */
  public String getColour()
  {
    return colour;
  }
  
  /**
   * The getCritterState method is used to get the critter's state.
   * 
   * @return critterState This is the String variable of the critter's state.
   */
  public String getCritterState()
  {
    return critterState;
  }
  
  /**
   * The setCritterState method is used to set the critter's new critter state.
   * 
   * @param critterState This is the String variable of the critter's state.
   */
  public void setCritterState(String critterState)
  {
    this.critterState = critterState;
  }
  
  /**
   * The getSickCounter method is used to get the sick counter's value.
   * 
   * @return sickCounter This is the int variable of the sick counter.
   */
  public int getSickCounter()
  {
    return sickCounter;
  }
  
  /**
   * The setSickCounter method is used to set the sick counter's value.
   * 
   * @param count This is the int variable of the count.
   */
  public void setSickCounter(int count)
  {
    sickCounter = count;
  }
  
  /**
   * The getRecoverCounter method is used to get the recovery counter's value.
   * 
   * @return recoverCounter This is the int variable of the recovery counter.
   */
  public int getRecoverCounter()
  {
    return recoverCounter;
  }
  
  /**
   * The setRecoverCounter method is used to set the recovery counter's value.
   * 
   * @param count This is the int variable of the count.
   */
  public void setRecoverCounter(int count)
  {
    recoverCounter = count;
  }
  
  /**
   * The getGrowthCounter method is used to get the growth counter's value.
   * 
   * @return growthCounter This is the int variable of the growth counter.
   */
  public int getGrowthCounter()
  {
    return growthCounter;
  }
  
  /**
   * The setGrowthCounter method is used to set the growth counter's value.
   * 
   * @param count This is the int variable of the growth count.
   */
  public void setGrowthCounter(int count)
  {
    growthCounter = count;
  }
  /**
   * The getIsHealthy method is used to get whether the critter is healthy or not.
   * 
   * @return isHealthy This is the boolean variable of whether the critter is healthy or not.
   */
  public boolean getIsHealthy()
  {
    return isHealthy;
  }
  
  /**
   * The setIsHealthy method is used to set whether the critter is healthy or not.
   * 
   * @param isHealthy This is the boolean variable of whether the critter is healthy or not.
   */
  public void setIsHealthy(boolean isHealthy)
  {
    this.isHealthy = isHealthy;
  }
}
