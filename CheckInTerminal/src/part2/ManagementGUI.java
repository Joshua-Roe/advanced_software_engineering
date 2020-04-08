package part2;

import part1.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class ManagementGUI implements Observer {
  class PassengerComponent extends JPanel {
    public PassengerComponent(String flight, String passengerName, int bagWeight, String bagSize) {
      this.setBorder(createBorder("")); // set border
      this.setLayout(new GridLayout(0, 4)); // set layout
      JLabel flightText = new JLabel(flight, SwingConstants.CENTER);
      this.add(flightText);
      JLabel nameText = new JLabel(passengerName, SwingConstants.CENTER);
      this.add(nameText);
      JLabel weightText = new JLabel(bagWeight + "kg", SwingConstants.CENTER);
      this.add(weightText);
      JLabel sizeText = new JLabel(bagSize, SwingConstants.CENTER);
      this.add(sizeText);
    }
    // TODO setContents
  }

  class DeskComponent extends JPanel {
    public DeskComponent(String title, String passengerName, int bagWeight, int bagFee) {
      this.setBorder(createBorder(title)); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      JLabel bagDetails = new JLabel(passengerName + " is dropping off 1 bag of " + bagWeight + "kg");
      this.add(bagDetails);
      String feeText = "";
      if (bagFee == 0) {
        feeText = "No baggage fee is due";
      } else {
        feeText = "A bagagge fee of £" + bagFee + " is due";
      }
      JLabel feeDetails = new JLabel(feeText);
      this.add(feeDetails);
    }
    // TODO setContents
  }

  class FlightComponent extends JPanel {
    public FlightComponent(String title, int passengerCount, int passengerCapacity, int holdLevel) {
      this.setBorder(createBorder(title)); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      JLabel checkedIn = new JLabel(passengerCount + " checked in of " + passengerCapacity);
      this.add(checkedIn);
      JLabel holdPercent = new JLabel("Hold is " + holdLevel + "% full");
      this.add(holdPercent);
    }
    // TODO setContents
  }

  static JFrame frame;

  public ManagementGUI() {
    // create frame
    JFrame checkFrame = new JFrame("Management");
    checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    checkFrame.setSize(700, 170);
    checkFrame.setLocationRelativeTo(null);

    // Column for Queue Patrons
    JPanel queueContentPanel = new JPanel(); // content panel to be scrolled
    queueContentPanel.setLayout(new BoxLayout(queueContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    for (int i = 0; i < 20; i++) {
      queueContentPanel
          .add(new PassengerComponent("FLT" + i, "Passenger Name", i * 2, i + "x" + (i + 1) + "x" + (i + 2)));
    } // TEST to fill scroll panel
    JScrollPane queueScrollPane = new JScrollPane(queueContentPanel);// scrollpane object
    JPanel queuePanel = new JPanel(); // top level container panel for border
    queuePanel.setBorder(createBorder("Queue Patrons")); // set border
    queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS)); // set layout
    queuePanel.add(BorderLayout.CENTER, queueScrollPane); // add scrollpane to bordered panel

    // Column for Check In Desks
    JPanel desksContentPanel = new JPanel(); // content panel to be scrolled
    desksContentPanel.setLayout(new BoxLayout(desksContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    desksContentPanel.add(new DeskComponent("Desk 1", "Joshua Roe", 7, 12));
    desksContentPanel.add(new DeskComponent("Desk 2", "Sean Katagiri", 4, 0));
    JScrollPane desksScrollPane = new JScrollPane(desksContentPanel);// scrollpane object
    JPanel desksPanel = new JPanel(); // top level container panel for border
    desksPanel.setBorder(createBorder("Check In Desks")); // set border
    desksPanel.setLayout(new BoxLayout(desksPanel, BoxLayout.PAGE_AXIS)); // set layout
    desksPanel.add(BorderLayout.CENTER, desksScrollPane); // add scrollpane to bordered panel

    // Column for Flights
    JPanel flightsContentPanel = new JPanel(); // content panel to be scrolled
    flightsContentPanel.setLayout(new BoxLayout(flightsContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    for (int i = 0; i < 20; i++) {
      flightsContentPanel.add(new FlightComponent("FlightName:" + i, i, i + 10, i));
    } // TEST to fill scroll panel
    JScrollPane flightsScrollPane = new JScrollPane(flightsContentPanel);// scrollpane object
    JPanel flightsPanel = new JPanel(); // top level container panel for border
    flightsPanel.setBorder(createBorder("Flights")); // set border
    flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.PAGE_AXIS)); // set layout
    flightsPanel.add(BorderLayout.CENTER, flightsScrollPane); // add scrollpane to bordered panel

    JPanel mainPanel = new JPanel(); // the panel is not visible in output
    mainPanel.setLayout(new GridLayout(0, 3));
    mainPanel.add(queuePanel);
    mainPanel.add(desksPanel);
    mainPanel.add(flightsPanel);

    // Column for Play/Pause
    JPanel playControlPanel = new JPanel(); // content panel to be scrolled
    JLabel playButton = new JLabel("I Am A PLAY BUTTON");
    playControlPanel.add(playButton);

    // Column for speed Slider
    JPanel speedControlPanel = new JPanel(); // content panel to be scrolled
    JLabel speedSlider = new JLabel("I Am A SPEED SLIDER");
    speedControlPanel.add(speedSlider);

    // Column for Sim Clock
    JPanel clockControlPanel = new JPanel(); // content panel to be scrolled
    JLabel simTime = new JLabel("I Am A CLOCK");
    clockControlPanel.add(simTime);

    JPanel simControlPanel = new JPanel(); // panel for holding simulation controls
    simControlPanel.setLayout(new GridLayout(0, 3));
    simControlPanel.add(playControlPanel);
    simControlPanel.add(speedControlPanel);
    simControlPanel.add(clockControlPanel);

    // Adding Components to the frame.
    checkFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    checkFrame.getContentPane().add(BorderLayout.SOUTH, simControlPanel);
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

  @Override
  public void update(Observable o, Object arg) {
    if(arg instanceof PassengerQueue) updateQueue(arg);
    else if(arg instanceof CheckinCounter) updateCounter(arg);
    else if(arg instanceof Flight) updateFlight(arg);
    // TODO else throw exception
  }

  private void updateQueue(Object arg) {
  }

  private void updateCounter(Object arg) {
  }

  private void updateFlight(Object arg) {
  }
}
	
