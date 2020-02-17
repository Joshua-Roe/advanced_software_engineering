import javax.swing.*;
import java.awt.*;
class gui {
    public static void main(String args[]) {

        //create frame
        JFrame checkFrame = new JFrame("Check In");
        checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkFrame.setSize(400, 120);

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
        checkFrame.getContentPane().add(BorderLayout.NORTH, namePanel);
        checkFrame.getContentPane().add(BorderLayout.CENTER, refPanel);
        checkFrame.getContentPane().add(BorderLayout.SOUTH, confPanel);
        checkFrame.setVisible(true);
        
        
        
        
        //create frame
        JFrame bagFrame = new JFrame("Baggage");
        bagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bagFrame.setSize(400, 120);

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
        
        JPanel conf2Panel = new JPanel(); // the panel is not visible in output        
        JButton conf2Btn= new JButton("Confirm");
        conf2Panel.add(conf2Btn);
        //Adding Components to the frame.
        bagFrame.getContentPane().add(BorderLayout.NORTH, sizePanel);
        bagFrame.getContentPane().add(BorderLayout.CENTER, weightPanel);
        bagFrame.getContentPane().add(BorderLayout.SOUTH, conf2Panel);
        bagFrame.setVisible(true);
    }
}