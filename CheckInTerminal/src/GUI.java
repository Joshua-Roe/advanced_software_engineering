import javax.swing.*;
import java.awt.*;
class gui {
    public static void main(String args[]) {

        //create frame
        JFrame frame = new JFrame("Check In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 120);

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
        
        JPanel confPanel = new JPanel(); // the panel is not visible in output        
        JButton confBtn= new JButton("Confirm");
        confPanel.add(confBtn);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, namePanel);
        frame.getContentPane().add(BorderLayout.CENTER, refPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, confPanel);
        frame.setVisible(true);
    }
}