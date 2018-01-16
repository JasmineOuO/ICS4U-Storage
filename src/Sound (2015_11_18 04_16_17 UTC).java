import javax.sound.sampled.*;
/**
 * The Sound class loops the background music.
 * 
 * @author Jasmine Ou
 * @version 1 06.09.15
 * 
 * Time Spent by Jasmine Ou: 3 hours.
 * Time Spent by Laura Wong: none.
 * 
 * <p>
 * <b>name: Sound.java </b>
 *
 * <p>
 * <b>Instance variables: </b>
 * <p>
 * <b>clip </b> This creates an instance of the Clip class
 */

public class Sound 
{
  private Clip clip;
  
  /**
   * The Sound constructor opens up the music file to be stored as the clip. 
   * The try block is for opening the music file.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>a </b> This stores the AudioInputStream for the background music file.
   * <p>
   * <b>e </b> The exception to be caught from the try block for opening the music file.
   */
  public Sound () 
  {
    try 
    {
      AudioInputStream a = AudioSystem.getAudioInputStream(Sound.class.getResource("ukelele2.wav"));
      clip = AudioSystem.getClip();
      clip.open(a);
    } 
    catch (Exception e) {}
  }
  
  /**
   * The loop method loops the background music in a thread. 
   * Within the try block, a new Thread is created and its run method starts playing the clip from the beginning and loops it.
   * <b> e </b> The exception to be caught from the try block for creating the Thread.
   */
  public void loop() 
  {
    try 
    {
      new Thread() 
      {
        public void run() 
        {
          clip.stop();
          clip.setFramePosition(0);
          clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
      }.start();
    } 
    catch (Exception e) {}
  }
}
