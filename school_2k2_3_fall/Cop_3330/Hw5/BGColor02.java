import java.awt.*;
import java.awt.event.*;
public class BGColor02 extends Frame implements ActionListener, WindowListener, ComponentListener{
   private Button b1 = new Button("Red"), b2 = new Button("Green"), 
	b3 = new Button("Blue");
   private TextArea log = new TextArea(6,120);
   private int eventCount = 0;
   private int FRAMEWIDTH=800;  private int FRAMEHEIGHT=300;
   public BGColor02() {
    // Set the title of the frame, Frame size, Set layout to FlowLayout
    setTitle("BGColor-02");  setBounds(0,0,FRAMEWIDTH,FRAMEHEIGHT);
    setLayout(new FlowLayout());
    // ARRANGE GUI
    add(b1);  add(b2);  add(b3);  add(log); add(new ImageIcon("1.gif"));
    // ADD LISTENERS
    b1.addActionListener(this);b2.addActionListener(this);b3.addActionListener(this);
    addComponentListener(this); addWindowListener(this);
   }
   public static void main(String args[]) {
     Frame f = new BGColor02();
     f.show();
   }

   public void log_it(AWTEvent e) {
            eventCount++;
     log.append("Event "+eventCount+": "+e.toString()+"\n");
   }

   // Action Listener
   public void actionPerformed(ActionEvent e) {
     log_it(e);
     if (e.getSource() == b1)
       setBackground(Color.red);
     else if (e.getSource() == b2)
       setBackground(Color.green);
     else if (e.getSource() == b3)
       setBackground(Color.blue);
     repaint();
   }

   // Component Listener
   public void componentMoved(ComponentEvent e) { log_it(e); }
   public void componentHidden(ComponentEvent e) { log_it(e); }
   public void componentShown(ComponentEvent e) { log_it(e); }
   public void componentResized(ComponentEvent e) {
     log_it(e);
     setBounds(0,0,FRAMEWIDTH,FRAMEHEIGHT);
   }
  // Window Listener
  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }
  public void windowOpened(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
}

