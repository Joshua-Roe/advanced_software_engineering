package part2;

import part1.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;


@SuppressWarnings({"serial","deprecation"})
public class ManagementGUI extends Thread implements Observer, ChangeListener {
  class PassengerComponent extends JPanel {

    public PassengerComponent(String flight, String passengerName, float bagWeight, String bagSize) {
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
    //   this.setMaximumSize(new Dimension(2000,20));
      this.setMaximumSize(new Dimension(Integer.MAX_VALUE,18));
    }
    // TODO setContents
  }

  class DeskComponent extends JPanel {
    JLabel bagDetails;
    JLabel feeDetails;
    public DeskComponent(int deskNumber) {
      this.setBorder(createBorder("Desk "+ deskNumber)); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      bagDetails = new JLabel("waiting for details");
      this.add(bagDetails);
      feeDetails = new JLabel("waiting for details");
      this.add(feeDetails);
    }
    public void setcontents(Booking currentBooking, int bagFee) {
      bagDetails.setText(currentBooking.getFullName() + " is dropping off 1 bag of " + currentBooking.getBaggage_weight() + "kg");
      String feeText = "";
      if (bagFee == 0) {
        feeText = "No baggage fee is due";
      } else {
        feeText = "A bagagge fee of £" + bagFee + " is due";
      }
      feeDetails.setText(feeText);
    }
    //TODO close counter
  }

  class FlightComponent extends JPanel {
    JLabel checkedIn;
    JLabel holdPercent;
    public FlightComponent(Flight currentFlight) {
      this.setBorder(createBorder(currentFlight.getFlightCode())); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      checkedIn = new JLabel("waiting for details");
      this.add(checkedIn);
      holdPercent = new JLabel("waiting for details");
      this.add(holdPercent);
      this.setcontents(currentFlight);
    }
    public void setcontents(Flight currentFlight) {
      checkedIn.setText(currentFlight.getNumOfPassengers() + " checked in of " + currentFlight.getMaxPassengers());
      holdPercent.setText("Hold is " + 50 + "% full");//TODO getHoldPercentFull
    }
    //TODO flight takes off
  }

  static JFrame frame;
  private JPanel queueContentPanel;
  private JPanel desksContentPanel;
  private JPanel flightsContentPanel;
  private JLabel clock;
  private Timer timer;
  private SimTime t;
  private String hours;
  private String minutes;
  private DeskComponent[] allDeskComponents;
  private HashMap<String,FlightComponent> allFlightComponents;
  

  public ManagementGUI(Timer timer, SimTime t, List<CheckinCounter> allCounters, HashMap<String,Flight> allFlights) {

    this.timer = timer;
    this.t = t;
    // create frame
    JFrame checkFrame = new JFrame("Management");
    checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    checkFrame.setSize(1400, 340);
    checkFrame.setLocationRelativeTo(null);

    // Column for Queue Patrons
    queueContentPanel = new JPanel(); // content panel to be scrolled
    queueContentPanel.setLayout(new BoxLayout(queueContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    JScrollPane queueScrollPane = new JScrollPane(queueContentPanel);// scrollpane object
    JPanel queuePanel = new JPanel(); // top level container panel for border
    queuePanel.setBorder(createBorder("Queue Patrons")); // set border
    queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS)); // set layout
    queuePanel.add(BorderLayout.CENTER, queueScrollPane); // add scrollpane to bordered panel

    // Column for Check In Desks
    desksContentPanel = new JPanel(); // content panel to be scrolled
    desksContentPanel.setLayout(new BoxLayout(desksContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    JScrollPane desksScrollPane = new JScrollPane(desksContentPanel);// scrollpane object
    JPanel desksPanel = new JPanel(); // top level container panel for border
    desksPanel.setBorder(createBorder("Check In Desks")); // set border
    desksPanel.setLayout(new BoxLayout(desksPanel, BoxLayout.PAGE_AXIS)); // set layout
    desksPanel.add(BorderLayout.CENTER, desksScrollPane); // add scrollpane to bordered panel
    allDeskComponents = new DeskComponent[allCounters.size()];
    for(CheckinCounter item : allCounters) allDeskComponents[item.getCounterNumber()-1] = new DeskComponent(item.getCounterNumber());
    for(DeskComponent desk : allDeskComponents){
        desksContentPanel.add(desk);
    }

    // Column for Flights
    flightsContentPanel = new JPanel(); // content panel to be scrolled
    flightsContentPanel.setLayout(new BoxLayout(flightsContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    JScrollPane flightsScrollPane = new JScrollPane(flightsContentPanel);// scrollpane object
    JPanel flightsPanel = new JPanel(); // top level container panel for border
    flightsPanel.setBorder(createBorder("Flights")); // set border
    flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.PAGE_AXIS)); // set layout
    flightsPanel.add(BorderLayout.CENTER, flightsScrollPane); // add scrollpane to bordered panel
    allFlightComponents = new HashMap<String, FlightComponent>();
    allFlights.forEach((key,value) -> allFlightComponents.put(key, new FlightComponent(value)));
    allFlightComponents.forEach((key,value) -> flightsContentPanel.add(value));

    JPanel mainPanel = new JPanel(); // the panel is not visible in output
    mainPanel.setLayout(new GridLayout(0, 3));
    mainPanel.add(queuePanel);
    mainPanel.add(desksPanel);
    mainPanel.add(flightsPanel);

    // Column for Play/Pause
    JPanel playControlPanel = new JPanel(); // panel for buttons
    playControlPanel.setLayout(new GridLayout(0, 2)); // set layout
    JButton playButton = new JButton("Play");
    playButton.setActionCommand("play");
    playButton.setEnabled(false);
    playControlPanel.add(playButton);
    JButton pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause");
    playControlPanel.add(pauseButton);
    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        t.resumeSim();
      }
    });
    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        t.pauseSim();
      }
    });


    // Column for speed Slider
    JPanel speedControlPanel = new JPanel(); // panel for slider
    speedControlPanel.setLayout(new GridLayout(2, 0)); // set layout
    //speedControlPanel.setLayout(new BoxLayout(speedControlPanel, BoxLayout.PAGE_AXIS)); // set layout
    JLabel speedSliderLabel = new JLabel("Simulation Speed", SwingConstants.CENTER);
    speedControlPanel.add(speedSliderLabel);
    JSlider speedSlider = new JSlider(0, 3, 0);
    speedSlider.setMajorTickSpacing(1);
    speedSlider.setSnapToTicks(true);
    speedSlider.setPaintTicks(true);
    Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
    sliderLabels.put(0, new JLabel("1x"));
    sliderLabels.put(1, new JLabel("2x"));
    sliderLabels.put(2, new JLabel("4x"));
    sliderLabels.put(3, new JLabel("8x"));
    speedSlider.setLabelTable(sliderLabels);
    speedSlider.setPaintLabels(true);
    speedSlider.addChangeListener(this);
    speedControlPanel.add(speedSlider);

    // Column for Sim Clock
    JPanel clockControlPanel = new JPanel(); // panel for clock
    this.clock = new JLabel("00:00");
    this.clock.setFont(clock.getFont().deriveFont(32.0f));
    clockControlPanel.add(clock);

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

  @Override
  public void update(Observable o, Object arg) {
    if(arg instanceof PassengerQueue) updateQueue(arg);
    else if(arg instanceof CheckinCounter) updateCounter(arg);
    else if(arg instanceof Flight) updateFlight(arg);
    // TODO else throw exception
  }

  /** Listen to the slider. */
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
        int sliderPos = (int)source.getValue();
        int simSpeed = -1;
        if (sliderPos == 0) simSpeed = 1;
        else if (sliderPos == 1) simSpeed = 2;
        else if (sliderPos == 2) simSpeed = 4;
        else if (sliderPos == 3) simSpeed = 8;
        t.set(simSpeed);
    }
  }

  private void updateQueue(Object arg) {
    PassengerQueue passengerQueue = (PassengerQueue)arg;
    Queue<Booking> bookingQueue = passengerQueue.getQueue();
    queueContentPanel.removeAll();
    for (Booking item: bookingQueue) {
      queueContentPanel.add(new PassengerComponent(item.getFlightCode(), item.getFullName(), item.getBaggage_weight(), Float.toString(item.getBaggage_volume())));
    }
    queueContentPanel.setVisible(false);// this forces update of the JPanel and its contents
    queueContentPanel.setVisible(true);
  }

  private void updateCounter(Object arg) {
    CheckinCounter checkinCounter = (CheckinCounter)arg;
    allDeskComponents[checkinCounter.getCounterNumber()-1].setcontents(checkinCounter.getBooking(), 10);// TODO set bagFee
  }

  private void updateFlight(Object arg) {
    Flight flight = (Flight)arg;
    allFlightComponents.get(flight.getFlightCode()).setcontents(flight);
    //TODO flight takes off
  }

  public void updateClock(){
    int min = this.timer.getTime() % 60;
    int hr = this.timer.getTime() / 60 % 24;
    this.minutes = (min<10) ? "0"+Integer.toString(min) : Integer.toString(min);
    this.hours = (hr<10) ? "0"+Integer.toString(hr) : Integer.toString(hr);
    this.clock.setText(this.hours+":"+this.minutes);
  }

  private void testFillGUI() {
    for (int i = 0; i < 20; i++) {
      queueContentPanel.add(new PassengerComponent("FLT" + i, "Passenger Name", i * 2, i + "x" + (i + 1) + "x" + (i + 2)));
    } // TEST to fill scroll panel

    //desksContentPanel.add(new DeskComponent("Desk 1", "Joshua Roe", 7, 12));
    //desksContentPanel.add(new DeskComponent("Desk 2", "Sean Katagiri", 4, 0));
    // TEST to fill scroll panel

    // for (int i = 0; i < 20; i++) {
    //   flightsContentPanel.add(new FlightComponent("FlightName:" + i, i, i + 10, i));
    // } // TEST to fill scroll panel
  }

  public void run() {
    synchronized(timer){
        while (true) {
          try {
            timer.wait();
            updateClock();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        }
    }
}
  public static void main(String[] args) {
    SimTime t = new SimTime();
    Timer timer = new Timer(t);

    List<CheckinCounter> counters = new LinkedList<CheckinCounter>();
    AllFlights flights = new AllFlights();
    Flight f1 = new Flight("AF1", "Edinburgh", "AirFrance", 200, 23, 30, 15);
    flights.addFlight(f1);
    CheckinCounter c1 = new CheckinCounter(1,flights,t,timer);
    counters.add(c1);
    CheckinCounter c2 = new CheckinCounter(2,flights,t,timer);
    counters.add(c2);
    //updateCounter(c1);

    ManagementGUI g = new ManagementGUI(timer,t,counters,flights.getAllFlights());
    g.testFillGUI();
    timer.start();
    g.start();
  }
}
	
