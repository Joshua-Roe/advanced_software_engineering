package CheckInTerminal;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class ManagementGUI {
	static JFrame frame;
    public ManagementGUI() {
        //create frame
        JFrame checkFrame = new JFrame("Management");
        checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkFrame.setSize(700, 170);
        checkFrame.setLocationRelativeTo(null);
        
        //Creating the panel at bottom and adding components
        JPanel queuePanel = new JPanel(); // the panel is not visible in output
        queuePanel.setBorder(createBorder("Queue Patrons"));
        JLabel queueLabel = new JLabel("Queue Patrons");
        queuePanel.add(queueLabel); // Components Added using Flow Layout
        
        JPanel desksPanel = new JPanel(); // the panel is not visible in output
        desksPanel.setBorder(createBorder("Check In Desks"));
        JLabel desksLabel = new JLabel("Check In Desks");
        desksPanel.add(desksLabel); // Components Added using Flow Layout

        JPanel flightsPanel = new JPanel(); // the panel is not visible in output
        flightsPanel.setBorder(createBorder("Flights"));
        JLabel flightsLabel = new JLabel("Flights");
        flightsPanel.add(flightsLabel); // Components Added using Flow Layout
        
        JPanel mainPanel = new JPanel(); // the panel is not visible in output
        mainPanel.setLayout(new GridLayout(0,3));
        mainPanel.add(queuePanel);
        mainPanel.add(desksPanel);
        mainPanel.add(flightsPanel);

        //Adding Components to the frame.
        checkFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        checkFrame.setVisible(true);

    }

    TitledBorder createBorder(String borderText) {
      TitledBorder border;
      border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), borderText);
      border.setTitleJustification(TitledBorder.CENTER);
      border.setTitlePosition(TitledBorder.DEFAULT_POSITION);
      return border;
    }
    public static void main(String[] args) {
      ManagementGUI g = new ManagementGUI();
    }
}
	
