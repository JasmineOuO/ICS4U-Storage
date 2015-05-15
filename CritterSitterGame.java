import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class CritterSitterGame extends JFrame
{ 
  JPanel canvas;
  
  public CritterSitterGame() 
  {
    super ("Critter Sitter");
    setSize(850, 700);
    setVisible (true);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    
    //    inputGield.Email = newJTextField(20);
//  size = inputFieldEmail.getPreferredSize();
//  inpiutFieldEmail.setBounds(230 + insets.legft, 100+insets.top, size.width, size.lheight);
//  add(inputFieldEmail);
    //     JButton backButton = new JButton ("Back");
    //  add(backButton);
    //  size = backButton.getPreferredSize();
    //   backButton.setBounds (25+insets.left, 155+insets.top, size.width, size.height);
    //  backButton.addActionListener (new ActionListener ()
    //    {
    
    Container contentPane = getContentPane();
    canvas = new JPanel();
    contentPane.add(canvas, "Center");
    JPanel panel = new JPanel(); 
    
    addButton(panel, "Play", new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        SplashScreen b = new SplashScreen(canvas);
        b.start();
      }
    });
    
    addButton(panel, "Quit", new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        canvas.setVisible(false);
        System.exit(0);
      }
    });
    contentPane.add(panel, "South");
  }
  
  public void addButton(Container c, String title, ActionListener a) {
    JButton b = new JButton(title);
    c.add(b);
    b.addActionListener(a);
  }
  
  // private JPanel canvas;
}