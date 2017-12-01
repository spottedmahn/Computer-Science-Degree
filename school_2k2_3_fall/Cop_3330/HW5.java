import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



// FrameExample inherits from JFrame
// through use of the extends keyword.
public class HW5 extends JFrame 
			implements ActionListener, WindowListener
  { 
  	BigNumber CurrentNum,PreviousNum;
  	String Opp="";  	
  	String line="";
  	String OppEquals="";
  	boolean IsNumber=false;
  	
    // Variables for objects
    JPanel buttonPanel;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton buttonBlank1;
    JButton button0;
    JButton buttonClear;
    
    JPanel buttonPane2;
    JButton buttonPlus;
    JButton buttonMinus;
    JButton buttonMultiply;
    JButton buttonFactorial;
    JButton buttonEquals;
       
    JPanel buttonPane3;
    JTextField TextFieldResult; 
    
    
    public HW5()
     {
       // Initialize the button and panel.
       buttonPanel = new JPanel();
       button1 = new JButton("1");
       button1.setFont(new java.awt.Font("Dialog", 1, 16));
       button1.setBorder(BorderFactory.createRaisedBevelBorder());
       button2 = new JButton("2");
       button2.setBorder(BorderFactory.createRaisedBevelBorder());
       button2.setFont(new java.awt.Font("Dialog", 1, 16));
       button3 = new JButton("3");
       button3.setBorder(BorderFactory.createRaisedBevelBorder());
       button3.setFont(new java.awt.Font("Dialog", 1, 16));
       button4 = new JButton("4");
       button4.setBorder(BorderFactory.createRaisedBevelBorder());
       button4.setFont(new java.awt.Font("Dialog", 1, 16));
       button5 = new JButton("5");
       button5.setBorder(BorderFactory.createRaisedBevelBorder());
       button5.setFont(new java.awt.Font("Dialog", 1, 16));
       button6 = new JButton("6");
       button6.setBorder(BorderFactory.createRaisedBevelBorder());
       button6.setFont(new java.awt.Font("Dialog", 1, 16));
       button7 = new JButton("7");
       button7.setBorder(BorderFactory.createRaisedBevelBorder());
       button7.setFont(new java.awt.Font("Dialog", 1, 16));
       button8 = new JButton("8");
       button8.setBorder(BorderFactory.createRaisedBevelBorder());
       button8.setFont(new java.awt.Font("Dialog", 1, 16));
       button9 = new JButton("9");
       button9.setBorder(BorderFactory.createRaisedBevelBorder());
       button9.setFont(new java.awt.Font("Dialog", 1, 16));
       button0 = new JButton("0");
       button0.setBorder(BorderFactory.createRaisedBevelBorder());
       button0.setFont(new java.awt.Font("Dialog", 1, 16));
       buttonClear = new JButton("Clear");
       buttonClear.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonClear.setFont(new java.awt.Font("Dialog", 1, 14));
       buttonBlank1 = new JButton("");
       buttonBlank1.setBorder(BorderFactory.createRaisedBevelBorder());
       
       
       buttonPane2 = new JPanel();
       buttonPlus = new JButton("+");
       buttonPlus.setPreferredSize(new Dimension(30, 30));
       buttonPlus.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonPlus.setFont(new java.awt.Font("Dialog", 1, 15));
       buttonMinus = new JButton("-");
       buttonMinus.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonMinus.setFont(new java.awt.Font("Dialog", 1, 15));
       buttonMultiply = new JButton("X");
       buttonMultiply.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonMultiply.setFont(new java.awt.Font("Dialog", 1, 15));
       buttonFactorial = new JButton("!");
       buttonFactorial.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonFactorial.setFont(new java.awt.Font("Dialog", 1, 15));
       buttonEquals = new JButton("=");
       buttonEquals.setBorder(BorderFactory.createRaisedBevelBorder());
       buttonEquals.setFont(new java.awt.Font("Dialog", 1, 15));
   
       buttonPane3 = new JPanel();
       TextFieldResult = new JTextField(1);
       
       // Add the button to the panel
       GridLayout panel = new GridLayout(4,3);
       panel.setHgap(20);
       panel.setVgap(20); 
       buttonPanel.setBorder(BorderFactory.createEmptyBorder (0,10,10,0));
       buttonPanel.setBackground(Color.blue);
       buttonPanel.setLayout(panel);
       buttonPanel.add(button1);
       button1.setForeground(new Color (139,0,0));
       button1.addActionListener(this);
       buttonPanel.add(button2);
       button2.setForeground(new Color (139,0,0));
       button2.addActionListener(this);
       buttonPanel.add(button3);
       button3.setForeground(new Color (139,0,0));
       button3.addActionListener(this);
       buttonPanel.add(button4);
       button4.setForeground(new Color (139,0,0));
       button4.addActionListener(this);
       buttonPanel.add(button5);
       button5.setForeground(new Color (139,0,0));
       button5.addActionListener(this);
       buttonPanel.add(button6);
       button6.setForeground(new Color (139,0,0));
       button6.addActionListener(this);
       buttonPanel.add(button7);
       button7.setForeground(new Color (139,0,0));
       button7.addActionListener(this);
       buttonPanel.add(button8);
       button8.setForeground(new Color (139,0,0));
       button8.addActionListener(this);
       buttonPanel.add(button9);
       button9.setForeground(new Color (139,0,0));
       button9.addActionListener(this);
       buttonPanel.add(buttonBlank1);
       buttonPanel.add(button0);
       button0.setForeground(new Color (139,0,0));
       button0.addActionListener(this);
       buttonClear.setForeground(new Color (255,0,0));
       buttonPanel.add(buttonClear);
       buttonClear.addActionListener(this);
       
       // Add the button to the pane2
       GridLayout pane2 = new GridLayout(5,1);
       pane2.setHgap(20);
       pane2.setVgap(10);
       buttonPane2.setBorder(BorderFactory.createEmptyBorder (30,8,30,8));
       buttonPane2.setBackground(Color.blue);
       buttonPane2.setLayout(pane2);
       buttonPane2.add(buttonPlus);
       buttonPlus.setForeground(new Color (0,0,128));
       buttonPlus.addActionListener(this);
       buttonPane2.add(buttonMinus);
       buttonMinus.setForeground(new Color (0,0,128));
       buttonMinus.addActionListener(this);
       buttonPane2.add(buttonMultiply);
       buttonMultiply.setForeground(new Color (0,0,128));
       buttonMultiply.addActionListener(this);
       buttonPane2.add(buttonFactorial);
       buttonFactorial.setForeground(new Color (0,0,128));
       buttonFactorial.addActionListener(this);
       buttonPane2.add(buttonEquals);
       buttonEquals.setForeground(new Color (0,0,128));
       buttonEquals.addActionListener(this);
       
       // Add the button to the pane3
       GridLayout pane3 = new GridLayout(1,1);
       pane3.setHgap(5);
       pane3.setVgap(5);
       buttonPane3.setBorder(BorderFactory.createEmptyBorder (20,30,20,30));
       buttonPane3.setBackground(Color.blue);
       buttonPane3.setLayout(pane3);
       buttonPane3.add(TextFieldResult);
       TextFieldResult.setHorizontalAlignment(JTextField.RIGHT);
       TextFieldResult.setText("");
       TextFieldResult.addActionListener(this);
      
       
       // Add the panel to the content pane
       getContentPane().add(buttonPanel, BorderLayout.CENTER);
       getContentPane().add(buttonPane2, BorderLayout.EAST);
       getContentPane().add(buttonPane3, BorderLayout.NORTH);
       
       addWindowListener(this);
       // Sets the  of ttitlehe frame.  
       setTitle("BigNumber Calculator");
       // Sets the width and height of the frame.  
       setSize(325, 325);
       setResizable(false);
       setVisible(true);
		Clear();
       
      }
      
      	// Action Listener
   		public void actionPerformed(ActionEvent e) 
   		{
   		
     		if (e.getSource() == buttonPlus)
     		{
     			Add();
       		}
       		else if (e.getSource() == buttonClear)
     		{
     			Clear();
     			//IsFirstNum=true;
     			
       		}
       		else if (e.getSource() == buttonEquals)
     		{
     			Equals();
       		}
       		else if (e.getSource() == buttonMinus)
     		{
     			Subtract();
     		}	
       		else if (e.getSource() == buttonMultiply)
     		{
     			Multiply();
       		}
       		else if (e.getSource() == buttonFactorial)
     		{
     			Factorial();
       		}
       		else 
     		{
     			/*if (line.equals("0"))
     			{
     			System.out.println("line equals 0");
     			line=((JButton) e.getSource()).getText();
       			TextFieldResult.setText(line);
       			}*/
       			if (IsNumber==false || line.equals("0"))
       			{	
       			PreviousNum = new BigNumber(TextFieldResult.getText());
       			System.out.println("Not a number");
       			line=((JButton) e.getSource()).getText();
       			TextFieldResult.setText(line);
       			}
       			else if(IsNumber=true && !line.equals("0"))
       			{	
       			System.out.println("line does not equal 0");
       			line=TextFieldResult.getText() + ((JButton) e.getSource()).getText();
       			TextFieldResult.setText(line);
       			}
       			IsNumber=true;
       		
   			}
   		}

  		// Window Listener
  		public void windowClosing(WindowEvent e) 
  		{
    		System.exit(0);
  		}
  		
  		public void windowOpened(WindowEvent e) {}
  		public void windowIconified(WindowEvent e) {}
  		public void windowDeiconified(WindowEvent e) {}
  		public void windowClosed(WindowEvent e) {}
  		public void windowActivated(WindowEvent e) {}
  		public void windowDeactivated(WindowEvent e) {}
	
      
      	public static void main(String args[])
      	{
        	JFrame HW5 = new HW5();
      	} 
      
      	public void Add()
      	{
      	System.out.println("Add");
      	CurrentNum = new BigNumber(line);
      	System.out.println("PreviousNum"+PreviousNum.getTheString());
      	System.out.println("CurrentNum"+CurrentNum.getTheString());
      	if(PreviousNum.getTheString() == "0" && IsNumber == true) {
      		System.out.println("*************** 1st case ********************");
            PreviousNum = CurrentNum;
            //CurrentNum = new BigNumber("0");
        }

        //If the operator was called more than one time in a row without entering in another number
        else if(!IsNumber && !OppEquals.equals("=")) {
        	System.out.println("*************** 2rd case ********************");
            if (PreviousNum.getLength() >CurrentNum.getLength()) 
                    CurrentNum=(PreviousNum.add(CurrentNum));
                else
                    CurrentNum=(CurrentNum.add(PreviousNum));
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }
        else if (OppEquals.equals("="))
        {
         	System.out.println("last opp was =");
       	 	PreviousNum = new BigNumber(TextFieldResult.getText());	
        }

        //If a number was entered then an operator was called then another number was entered
        else {
        	System.out.println("*************** 3rd case ********************");
            BigNumber temp = PreviousNum;
            PreviousNum = CurrentNum;
           if (temp.getLength() >CurrentNum.getLength()) 
                    CurrentNum=(temp.add(CurrentNum));
                else
                    CurrentNum=(CurrentNum.add(temp));
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }

        Opp = "+";
        OppEquals = "null";
        IsNumber = false;
        line=CurrentNum.getTheString();
        TextFieldResult.setText(line);
        
    }

      	public void Multiply()
      	{
      	System.out.println("Multiply");
      	CurrentNum = new BigNumber(line);
      	System.out.println("PreviousNum"+PreviousNum.getTheString());
      	System.out.println("CurrentNum"+CurrentNum.getTheString());
      	if(PreviousNum.getTheString() == "0" && IsNumber == true) {
      		System.out.println("*************** 1st case ********************");
            PreviousNum = CurrentNum;
            //CurrentNum = new BigNumber("0");
        }

        //If the operator was called more than one time in a row without entering in another number
        else if(!IsNumber && !OppEquals.equals("=")) {
        	System.out.println("*************** 2rd case ********************");
            if (PreviousNum.getLength() >CurrentNum.getLength()) 
                    CurrentNum=(PreviousNum.multiply(CurrentNum));
                else
                    CurrentNum=(CurrentNum.multiply(PreviousNum));
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }
        
         else if (OppEquals.equals("="))
        {
         	System.out.println("last opp was =");
       	 	PreviousNum = new BigNumber(TextFieldResult.getText());	
        }

        //If a number was entered then an operator was called then another number was entered
        else {
        	System.out.println("*************** 3rd case ********************");
            BigNumber temp = PreviousNum;
            PreviousNum = CurrentNum;
           if (temp.getLength() >CurrentNum.getLength()) 
                    CurrentNum=(temp.multiply(CurrentNum));
                else
                    CurrentNum=(CurrentNum.multiply(temp));
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }

        Opp = "X";
        OppEquals = "null";
        IsNumber = false;
        line=CurrentNum.getTheString();
        TextFieldResult.setText(line);

      	}
      	
      	public void Subtract()
      	{
      	System.out.println("Subtract");
      	CurrentNum = new BigNumber(line);
      	System.out.println("PreviousNum"+PreviousNum.getTheString());
      	System.out.println("CurrentNum"+CurrentNum.getTheString());
      	if(PreviousNum.getTheString() == "0" && IsNumber == true) {
      		System.out.println("*************** 1st case ********************");
            PreviousNum = CurrentNum;
           // CurrentNum = new BigNumber("0");
        }

        //If the operator was called more than one time in a row without entering in another number
        else if(!IsNumber) {
        	System.out.println("*************** 2rd case ********************");
            if (PreviousNum.getLength() >CurrentNum.getLength()) 
            {
            	System.out.println("p>c");
                    CurrentNum=(PreviousNum.subtract(CurrentNum));
                }
                else
                {
                	System.out.println("c>p");
                    CurrentNum=(CurrentNum.subtract(PreviousNum));
                }
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }
		 else if (OppEquals.equals("="))
        {
         	System.out.println("last opp was =");
       	 	PreviousNum = new BigNumber(TextFieldResult.getText());	
        }
        //If a number was entered then an operator was called then another number was entered
        else {
        	System.out.println("*************** 3rd case ********************");
            BigNumber temp = PreviousNum;
            PreviousNum = CurrentNum;
            
           
            CurrentNum=(temp.subtract(PreviousNum));
                
            System.out.println("***************"+CurrentNum.getTheString()+"********************");
        }

        Opp = "-";
        OppEquals = "null";
        IsNumber = false;
        line=CurrentNum.getTheString();
        TextFieldResult.setText(line);
	}
      	public void Factorial()
      	{
      		CurrentNum = new BigNumber(line);
      		System.out.println(CurrentNum.getTheString());
      		System.out.println(PreviousNum.getTheString());
      		PreviousNum = CurrentNum;
        	CurrentNum = (PreviousNum.factorial2(PreviousNum));
        	Opp = "!";
        	IsNumber = false;
        	line=CurrentNum.getTheString();
        	TextFieldResult.setText(line);
        	OppEquals = "null";

      	}
      	public void Equals()
      	{
      		if (Opp.equals( "+")) 
      		{
      			Add();
      		}
      		else if (Opp.equals( "X")) 
      		{
      			Multiply();
      		}
      		else if (Opp.equals( "-")) 
      		{
      			Subtract();
      		}
      		
      		OppEquals = "=";
      		PreviousNum = new BigNumber("0");
      	}
      	
      	public void Clear()
      	{
      			CurrentNum = new BigNumber("0");
        		PreviousNum = new BigNumber("0");
        		Opp = "";
        		OppEquals = "null";
        		IsNumber = false;
        		line=CurrentNum.getTheString();
        		System.out.println("**************"+line+"***********************");
        		TextFieldResult.setText(line);
        		
      	
      	}
      	
      
   }
