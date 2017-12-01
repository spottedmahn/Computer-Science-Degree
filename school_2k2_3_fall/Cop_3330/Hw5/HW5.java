/***************************************************************

** Michael DePouw

** COP3330

** Assignment Number: 5

** Date:12/01/02

***************************************************************/

/****************Program Description***************************

** This program is suppose to be a calculator but I was
** unable to get it to work.  Matter a fact I didn't get
** that far with the program.

**************************************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HW5 extends JFrame implements WindowListener, ActionListener {
	//the result
	JTextField tfResult;
	//the result panel
	JPanel resultPanel;
	//number buttons & clear
	JButton num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, clear;
	//number panel
   JPanel numPanel;
	//operator buttons
	JButton butPlus, butMinus, butMult, butEquals;
	//operator panel
	JPanel opPanel;
	
	public HW5 (){	
		//number panel
		numPanel = new JPanel();
		num1 = new JButton(new ImageIcon("num1.gif"));
		num2 = new JButton(new ImageIcon("num2.gif"));
		num3 = new JButton(new ImageIcon("num3.gif"));
		num4 = new JButton(new ImageIcon("num4.gif"));
		num5 = new JButton(new ImageIcon("num5.gif"));
		num6 = new JButton(new ImageIcon("num6.gif"));
		num7 = new JButton(new ImageIcon("num7.gif"));
		num8 = new JButton(new ImageIcon("num8.gif"));
		num9 = new JButton(new ImageIcon("num9.gif"));
		num0 = new JButton(new ImageIcon("num0.gif"));
		clear = new JButton(new ImageIcon("clear.gif"));
		//adding numButtons & clear to panel
		GridLayout Panel = new GridLayout(4,3);
		numPanel.setLayout(Panel);
		numPanel.add(num1);
		num1.addActionListener(this);
		numPanel.add(num2);
		num2.addActionListener(this);
		numPanel.add(num3);
		num3.addActionListener(this);
		numPanel.add(num4);
		num4.addActionListener(this);
		numPanel.add(num5);
		num5.addActionListener(this);
		numPanel.add(num6);
		num6.addActionListener(this);
		numPanel.add(num7);
		num7.addActionListener(this);
		numPanel.add(num8);
		num8.addActionListener(this);
		numPanel.add(num9);
		num9.addActionListener(this);
		numPanel.add(num0);
		num0.addActionListener(this);
		numPanel.add(clear);
		clear.addActionListener(this);
		//
		setSize(350, 350);
		setResizable(false);
		setVisible(true);
		
	}
	
	// Action Listener
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == butPlus)
  			Add();
		else if(e.getSource() == butMinus)
			Subtract();
		else if(e.getSource() == butMult)
			Multiply();
		else if(e.getSource() == butEquals)
			Equals();
		else if(e.getSource() == clear)
			Clear();
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
	//main
	public static void main(String[] args){
		JFrame frame = new HW5();
	}
