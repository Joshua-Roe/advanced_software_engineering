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
        
        //Column for Queue Patrons
        JPanel queueContentPanel = new JPanel(); //content panel to be scrolled
        queueContentPanel.setLayout(new BoxLayout(queueContentPanel, BoxLayout.PAGE_AXIS)); //set layout
        for (int i=0; i<20; i++) {queueContentPanel.add(new JLabel("Test: "+ i));} //TEST to fill scroll panel
        JScrollPane queueScrollPane = new JScrollPane(queueContentPanel);//scrollpane object
        JPanel queuePanel = new JPanel(); //top level container panel for border
        queuePanel.setBorder(createBorder("Queue Patrons")); //set border
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS)); //set layout
        queuePanel.add(BorderLayout.CENTER, queueScrollPane); //add scrollpane to bordered panel

        //Column for Check In Desks
        JPanel desksContentPanel = new JPanel(); //content panel to be scrolled
        desksContentPanel.setLayout(new BoxLayout(desksContentPanel, BoxLayout.PAGE_AXIS)); //set layout
        for (int i=0; i<20; i++) {desksContentPanel.add(new JLabel("Test: "+ i));} //TEST to fill scroll panel
        JScrollPane desksScrollPane = new JScrollPane(desksContentPanel);//scrollpane object
        JPanel desksPanel = new JPanel(); //top level container panel for border
        desksPanel.setBorder(createBorder("Check In Desks")); //set border
        desksPanel.setLayout(new BoxLayout(desksPanel, BoxLayout.PAGE_AXIS)); //set layout
        desksPanel.add(BorderLayout.CENTER, desksScrollPane); //add scrollpane to bordered panel

        //Column for Flights
        JPanel flightsContentPanel = new JPanel(); //content panel to be scrolled
        flightsContentPanel.setLayout(new BoxLayout(flightsContentPanel, BoxLayout.PAGE_AXIS)); //set layout
        for (int i=0; i<20; i++) {flightsContentPanel.add(new JLabel("Test: "+ i));} //TEST to fill scroll panel
        JScrollPane flightsScrollPane = new JScrollPane(flightsContentPanel);//scrollpane object
        JPanel flightsPanel = new JPanel(); //top level container panel for border
        flightsPanel.setBorder(createBorder("Flights")); //set border
        flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.PAGE_AXIS)); //set layout
        flightsPanel.add(BorderLayout.CENTER, flightsScrollPane); //add scrollpane to bordered panel
        
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
	
