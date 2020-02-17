import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI {
	static JFrame frame;
    public GUI() {

        //create frame
        JFrame checkFrame = new JFrame("Check In");
        checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkFrame.setSize(700, 120);
        checkFrame.setLocationRelativeTo(null);
        
        //Creating the panel at bottom and adding components
        JPanel namePanel = new JPanel(); // the panel is not visible in output
        JLabel nameLabel = new JLabel("Surname");
        JTextField nameText = new JTextField(10); // accepts upto 10 characters
        namePanel.add(nameLabel); // Components Added using Flow Layout
        namePanel.add(nameText);
        
        JPanel refPanel = new JPanel(); // the panel is not visible in output
        JLabel refLabel = new JLabel("Booking Reference");
        JTextField refText = new JTextField(10); // accepts upto 10 characters
        refPanel.add(refLabel); // Components Added using Flow Layout
        refPanel.add(refText);
        
        JPanel bookingPanel = new JPanel(); // the panel is not visible in output
        bookingPanel.setLayout(new GridLayout(0,1));
        bookingPanel.add(namePanel);
        bookingPanel.add(refPanel);
        
        //Creating the panel at bottom and adding components
        JPanel sizePanel = new JPanel(); // the panel is not visible in output
        JLabel sizeLabel = new JLabel("Baggage Size");
        JTextField sizeText = new JTextField(10); // accepts upto 10 characters
        sizePanel.add(sizeLabel); // Components Added using Flow Layout
        sizePanel.add(sizeText);
        
        JPanel weightPanel = new JPanel(); // the panel is not visible in output
        JLabel weightLabel = new JLabel("Baggage Weight");
        JTextField weightText = new JTextField(10); // accepts upto 10 characters
        weightPanel.add(weightLabel); // Components Added using Flow Layout
        weightPanel.add(weightText);
        
        JPanel baggagePanel = new JPanel(); // the panel is not visible in output
        baggagePanel.setLayout(new GridLayout(0,1));
        baggagePanel.add(sizePanel);
        baggagePanel.add(weightPanel);
        
        JPanel confPanel = new JPanel(); // the panel is not visible in output        
        JButton confBtn= new JButton("Confirm");
        confBtn.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	  if(confirm(nameText.getText(),refText.getText(),sizeText.getText(),weightText.getText())) {
        		  nameText.setText("");
        		  refText.setText("");
        		  sizeText.setText("");
        		  weightText.setText("");
        	  }
          }
        });
        confPanel.add(confBtn);

        //Adding Components to the frame.
        checkFrame.getContentPane().add(BorderLayout.WEST, bookingPanel);
        checkFrame.getContentPane().add(BorderLayout.EAST, baggagePanel);
        checkFrame.getContentPane().add(BorderLayout.SOUTH, confPanel);
        checkFrame.setVisible(true);
    }
    public boolean confirm(String name, String ref, String size, String weight) {
		if(name.trim().length() == 0 || ref.trim().length() == 0 || size.trim().length() == 0 || weight.trim().length() == 0) {
			JOptionPane.showMessageDialog(frame, "Please Fill All Inputs");
		return false;
		}
		//check valid baggage input
		//get booking form hashmak using ref
		//confirm surname
		//set baggage and checkin
		//get baggage limit
		//compare
		//output baggage dialog if nessicary
		//confirm checkin dialog
		JOptionPane.showMessageDialog(frame, "Check In Confirmed. \r\n {PLACEHOLDER}");
		return true;
    }

}
class guiTest{
    public static void main(String[] args) {
    	GUI g = new GUI();
    }
    //before exit generate report
}
	
