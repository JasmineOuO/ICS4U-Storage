import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class SplashScreen extends Thread 
{
  JPanel box;
  int width = 30;
  int length = 30;
  int x = 150;
  int y = 150;
  
  public SplashScreen(JPanel b)
  {
    box = b;
    
    JLabel imgLabel = new JLabel(new ImageIcon("splashscreenbackground.jpg"));
    box.add(imgLabel);
  }
  
  public void draw() 
  {
    //Image inputImage;
    // Picture canvas = new Picture("640x480.jpg");
    // Picture jack = new Picture("splashscreenbackground.jpg");
    //Graphics g = box.getGraphics();
    //g.fillOval(x, y, width, length);
    // Image myImage = getImage(getCodeBase(), "laura1a\\splashscreenbackground.jpg");
    //  g.drawImage(myImage, 300,200,60,120,this);
// inputImage = Toolkit.getDefaultToolkit().getImage("splashscreenbackground.jpg");
    //MediaTracker mt = new MediaTracker (this);
// mt.addImage(inputImage,0);
//    BufferedImage myPicture = ImageIO.read(new File("splashscreenbackground.jpg"));
//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//this.add(picLabel);
   
  }
  
  public void run() 
  {
    draw();
  }
}
