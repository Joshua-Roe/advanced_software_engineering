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
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/** 
 * @author Joshua Roe
 */
@SuppressWarnings({"serial","deprecation"})//deprecation warning when compiling with vesions after 1.8
public class ManagementGUI implements Observer, ChangeListener {
  class PassengerComponent extends JPanel {
    /**
     * Creates a new passenger component from a given booking for the GUI and adds it to the content panel
     *
     * @param currentBooking       the booking object to populate the component
     */
    public PassengerComponent(Booking currentBooking) {
      this.setBorder(createBorder("")); // set border
      this.setLayout(new GridLayout(0, 4)); // set layout
      JLabel flightText = new JLabel(currentBooking.getFlightCode(), SwingConstants.CENTER);//create label for FlightCode
      this.add(flightText);
      JLabel nameText = new JLabel(currentBooking.getFullName(), SwingConstants.CENTER);//create label for Full Name
      this.add(nameText);
      JLabel weightText = new JLabel(currentBooking.getBaggageWeight() + "kg", SwingConstants.CENTER);//create label for baggage weight
      this.add(weightText);
      JLabel sizeText = new JLabel("L:"+currentBooking.getBaggageLength()+" W:"+currentBooking.getBaggageWidth()+" H:"+currentBooking.getBaggageHeight(), SwingConstants.CENTER); //label for baggage size eg L7.2 W4.4 H8.1
      this.add(sizeText);
      this.setMaximumSize(new Dimension(Integer.MAX_VALUE,18));//limit size to one row in scrollpanel
      this.setToolTipText("<html>" + "Name: " + currentBooking.getFullName() +"<br>" + "Booking Reference: " + currentBooking.getReference().toUpperCase() + "</html>");
      if(currentBooking.getMissedFlight()){
        flightText.setForeground(Color.red);
        this.setToolTipText("<html>" + "Name: " + currentBooking.getFullName() +"<br>" + "Booking Reference: " + currentBooking.getReference().toUpperCase() +"<br>" + "MISSED FLIGHT"+ "</html>");
        this.setEnabled(false);
      }
    }
  }

  class DeskComponent extends JPanel {
    JLabel bagDetails;
    JLabel feeDetails;
    CheckinCounter counter;
    /**
     * Creates a new Desk component from a given counter object for the GUI and adds it to the content panel
     *
     * @param deskNumber    the Number to assign to the desk
     * @param counter       the counter object to populate the component
     */
    public DeskComponent(int deskNumber, CheckinCounter counter) {
      this.counter = counter;
      this.setBorder(createBorder("Desk "+ deskNumber)); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      bagDetails = new JLabel("waiting for details"); //create placeholder label and add to component
      this.add(bagDetails);
      feeDetails = new JLabel("waiting for details"); //create placeholder label and add to component
      this.add(feeDetails);
      JButton counterButton = new JButton("Close counter");
      this.add(counterButton);
      counterButton.addActionListener(new ActionListener() {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
          counter.toggleCounter();
          if(counter.getIsOpen()){
            counterButton.setText("Close Counter");
          }
          else{
            closeCounter();
            counterButton.setText("Open Counter");
          }
        }
      });
      
    }
    /**
     * Sets the contents of the Desk component for a given booking
     *
     * @param currentBooking       the booking object to populate the component
     */
    public void setcontents(Booking currentBooking) {
      this.setEnabled(true);// enable desk if it was previously closed
      if(currentBooking != null){
        bagDetails.setText(currentBooking.getFullName() + " is dropping off 1 bag of " + currentBooking.getBaggageWeight() + "kg");//set text for passenger details
        String feeText = "";
        Float bagFee = currentBooking.getExcessFeeCharged();
        if (bagFee == 0) {//create string representation of luggage charge
          feeText = "No baggage fee is due";
        } else {
          feeText = "A bagagge fee of \u00a3" + bagFee + " is due";
        }
        feeDetails.setText(feeText);
      }
      else{
        bagDetails.setText("Currently not serving a customer");
        feeDetails.setText(" ");
      }
    }
    /**
     * Closes the desk
     */
    public void closeCounter() {
      this.setEnabled(false);//close desk and gray gui element
      bagDetails.setText("Counter Closed");
      feeDetails.setText(" ");
    }
  }

  class FlightComponent extends JPanel {
    JLabel checkedIn;
    JLabel holdPercent;
    /**
     * Creates a new flight component from a given flight for the GUI and adds it to the content panel
     *
     * @param currentFlight       the Flight object to populate the component
     */
    public FlightComponent(Flight currentFlight) {
      this.setBorder(createBorder(currentFlight.getFlightCode())); // set border
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // set layout
      checkedIn = new JLabel("waiting for details"); //create placeholder label and add to component
      this.add(checkedIn);
      holdPercent = new JLabel("waiting for details");//create placeholder label and add to component
      this.add(holdPercent);
      this.setcontents(currentFlight);//populate labels
      this.setToolTipText("<html>" + "Flight: " + currentFlight.getFlightCode() +"<br>" + "Carrier: " + currentFlight.getCarrier() +"<br>" + "Destination: " + currentFlight.getDestination() +"<br>"+"Total fees collected: "+currentFlight.getTotalExcessFees()+ "</html>");// set text for cursor hover
    }
    /**
     * Sets the contents of the flight component for a given flight
     *
     * @param currentFlight      the Flight object to populate the component
     */
    public void setcontents(Flight currentFlight) {
      checkedIn.setText(currentFlight.getNumberOfPassengers() + " checked in of " + currentFlight.getMaxPassengers());//set text for passenger info
      holdPercent.setText("Hold is " + currentFlight.getBaggagePercent() + "% full");//set text for luggage info
      this.setToolTipText("<html>" + "Flight: " + currentFlight.getFlightCode() +"<br>" + "Carrier: " + currentFlight.getCarrier() +"<br>" + "Destination: " + currentFlight.getDestination() +"<br>"+"Total fees collected: \u00a3"+currentFlight.getTotalExcessFees()+ "</html>");// set text for cursor hover
      if(!currentFlight.getGateOpen()){
        this.setBorder(createBorder(currentFlight.getFlightCode() + " DEPARTED")); // set border text when flight has departed
        this.setEnabled(false);//disable component
    }
    }
  }

  static JFrame frame;
  private JPanel queueContentPanel;
  private JPanel desksContentPanel;
  private JPanel flightsContentPanel;
  private JLabel clock;
  private SimTime t;
  private DeskComponent[] allDeskComponents;
  private HashMap<String,FlightComponent> allFlightComponents;
  
  /**
   * Create the ManagementGUI object
   *
   * @param t      the SimTime object to pass to the GUI
   * @param allCounters      Hashmap of Counters to pass to GUI
   * @param allFlights      Hashmap of Flights to pass to GUI
   */
  public ManagementGUI(SimTime t, List<CheckinCounter> allCounters, HashMap<String,Flight> allFlights) {
    ToolTipManager.sharedInstance().setInitialDelay(0);//tooltips show immediately
    this.t = t;
    // create frame
    JFrame checkFrame = new JFrame("Management");
    checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    checkFrame.setSize(1400, 340);

    // Column for Queue Patrons
    queueContentPanel = new JPanel(); // content panel to be scrolled
    queueContentPanel.setLayout(new BoxLayout(queueContentPanel, BoxLayout.PAGE_AXIS)); // set layout
    JScrollPane queueScrollPane = new JScrollPane(queueContentPanel);// scrollpane object
    queueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
    JPanel queuePanel = new JPanel(); // top level container panel for border
    queuePanel.setBorder(createBorder("Queue Patrons")); // set border
    queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS)); // set layout
    queuePanel.add(BorderLayout.CENTER, queueScrollPane); // add scrollpane to bordered panel

    // Column for Check In Desks
    desksContentPanel = new JPanel(); // content panel to be scrolled
    desksContentPanel.setLayout(new GridLayout(allCounters.size(),0)); // set layout
    JScrollPane desksScrollPane = new JScrollPane(desksContentPanel);// scrollpane object
    desksScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
    JPanel desksPanel = new JPanel(); // top level container panel for border
    desksPanel.setBorder(createBorder("Check In Desks")); // set border
    desksPanel.setLayout(new BoxLayout(desksPanel, BoxLayout.PAGE_AXIS)); // set layout
    desksPanel.add(BorderLayout.CENTER, desksScrollPane); // add scrollpane to bordered panel
    allDeskComponents = new DeskComponent[allCounters.size()];
    for(CheckinCounter item : allCounters) allDeskComponents[item.getCounterNumber()-1] = new DeskComponent(item.getCounterNumber(),item);
    for(DeskComponent desk : allDeskComponents){
        desksContentPanel.add(desk);
    }

    // Column for Flights
    flightsContentPanel = new JPanel(); // content panel to be scrolled
    flightsContentPanel.setLayout(new GridLayout(allFlights.size(),0)); // set layout
    JScrollPane flightsScrollPane = new JScrollPane(flightsContentPanel);// scrollpane object
    flightsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
    JPanel flightsPanel = new JPanel(); // top level container panel for border
    flightsPanel.setBorder(createBorder("Flights")); // set border
    flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.PAGE_AXIS)); // set layout
    flightsPanel.add(BorderLayout.CENTER, flightsScrollPane); // add scrollpane to bordered panel
    allFlightComponents = new HashMap<String, FlightComponent>();
    allFlights.forEach((key,value) -> allFlightComponents.put(key, new FlightComponent(value)));
    allFlightComponents.forEach((key,value) -> flightsContentPanel.add(value));

    JPanel mainPanel = new JPanel(); // the panel is not visible in output
    mainPanel.setLayout(new GridBagLayout()); // GridBagLayout used over GridLayout for better control of its component sizes
    //GridBagConstraints object and the parameters for ensuring the right size ratio for the three main GUI components,
    //queuePanel, desksPanel and flightsPanel
    GridBagConstraints c = new GridBagConstraints(); 
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    c.gridheight = 1;
    c.gridwidth = 1;
    mainPanel.add(queuePanel,c);
    c.weightx = 0.5;
    mainPanel.add(desksPanel,c);
    c.weightx = 0.25;
    mainPanel.add(flightsPanel,c);
    // Column for Play/Pause
    JPanel playControlPanel = new JPanel(); // panel for buttons
    playControlPanel.setLayout(new GridLayout(0, 2)); // set layout
    JButton playButton = new JButton("Play"); //play button
    playButton.setActionCommand("play");
    playButton.setEnabled(false);//disabled because simulation starts playing
    playControlPanel.add(playButton);
    JButton pauseButton = new JButton("Pause");//pause button
    pauseButton.setActionCommand("pause");
    playControlPanel.add(pauseButton);
    playButton.addActionListener(new ActionListener() {//action listener for play
      @Override
      public void actionPerformed(ActionEvent e) {//swap buttons
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        t.resumeSim();//play simulation
      }
    });
    pauseButton.addActionListener(new ActionListener() {//action listener for pause
      @Override
      public void actionPerformed(ActionEvent e) {//swap buttons
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        t.pauseSim();//pause simulation
      }
    });


    // Column for speed Slider
    JPanel speedControlPanel = new JPanel(); // panel for slider
    speedControlPanel.setLayout(new GridLayout(2, 0)); // set layout
    JLabel speedSliderLabel = new JLabel("Simulation Speed", SwingConstants.CENTER);
    speedControlPanel.add(speedSliderLabel);
    JSlider speedSlider = new JSlider(0, 3, 0);//slider for sim speed
    speedSlider.setMajorTickSpacing(1);
    speedSlider.setSnapToTicks(true);//have slider click into place
    speedSlider.setPaintTicks(true);
    Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();//hastable for staring custom slider labels
    sliderLabels.put(0, new JLabel("1x"));
    sliderLabels.put(1, new JLabel("2x"));
    sliderLabels.put(2, new JLabel("4x"));
    sliderLabels.put(3, new JLabel("8x"));
    speedSlider.setLabelTable(sliderLabels);
    speedSlider.setPaintLabels(true);
    speedSlider.addChangeListener(this);//add changelistener for slider
    speedControlPanel.add(speedSlider);//add to controll panel

    // Column for Sim Clock
    JPanel clockControlPanel = new JPanel(); // panel for clock
    this.clock = new JLabel("[00:00]");
    this.clock.setFont(clock.getFont().deriveFont(32.0f));//set clock font
    clockControlPanel.add(clock);

    JPanel simControlPanel = new JPanel(); // panel for holding simulation controls
    simControlPanel.setLayout(new GridLayout(0, 3));//set layout and add controls
    simControlPanel.add(playControlPanel);
    simControlPanel.add(speedControlPanel);
    simControlPanel.add(clockControlPanel);

    // Adding Components to the frame.
    checkFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    checkFrame.getContentPane().add(BorderLayout.SOUTH, simControlPanel);
    // checkFrame.setMinimumSize(new Dimension(100,400));
    checkFrame.setVisible(true);
    checkFrame.setLocationRelativeTo(null);
    
  }
  /**
   * Create a titled border
   *
   * @param borderText      the text to display on the border
   * 
   * @return returns titled border.
   */
  TitledBorder createBorder(String borderText) {
    TitledBorder border;
    border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), borderText);//create the titled border
    border.setTitleJustification(TitledBorder.CENTER);//centre title within label
    border.setTitlePosition(TitledBorder.DEFAULT_POSITION);//default position
    return border;
  }
  /**
   * update a part of the GUI depending on object type
   *
   * @param o      observable object
   * @param arg      the object to update
   */
  @Override
  public void update(Observable o, Object arg) {//run specific method depending on object type, if unrecognised then do nothing
    if(o instanceof PassengerQueue) updateQueue(o, arg);
    else if(arg instanceof CheckinCounter) updateCounter(arg);
    else if(arg instanceof Flight) updateFlight(arg);
    else if(arg instanceof Timer) updateClock(arg);
  }

  /**
   * listen to the slider
   *
   * @param e      slider changeEvent
   */
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {//wait for slider to stop moving
        int sliderPos = (int)source.getValue();
        int simSpeed = -1;
        if (sliderPos == 0) simSpeed = 1;//set simspeed depending on slider position
        else if (sliderPos == 1) simSpeed = 2;
        else if (sliderPos == 2) simSpeed = 4;
        else if (sliderPos == 3) simSpeed = 8;
        t.set(simSpeed);
    }
  }
  /**
   * update passenger queue column
   *
   * @param arg      PassengerQueue object
   */
  private void updateQueue(Observable o, Object arg) {
    if(arg!=null) queueContentPanel.add(new PassengerComponent((Booking) arg));
    else queueContentPanel.remove(0);
    queueContentPanel.revalidate(); //update JPanel contents
  }
  /**
   * update Counter component
   *
   * @param arg      Counter object
   */
  private void updateCounter(Object arg) {
    CheckinCounter checkinCounter = (CheckinCounter)arg;//cast to CheckinCounter object
    if (checkinCounter.getIsOpen()){
      allDeskComponents[checkinCounter.getCounterNumber()-1].setcontents(checkinCounter.getBooking());//set countents of component
    }
    else {allDeskComponents[checkinCounter.getCounterNumber()-1].closeCounter();}//close counter
  }
  /**
   * update Flight component
   *
   * @param arg      Flight object
   */
  private void updateFlight(Object arg) {
    Flight flight = (Flight)arg;//cast to Flight object
    allFlightComponents.get(flight.getFlightCode()).setcontents(flight);//set contents of flight component
  }
  /**
   * update the clock
   *
   * @param arg      Timer object
   */
  public void updateClock(Object arg){
    Timer timer = (Timer) arg;//cast to Timer object
    this.clock.setText(timer.getTimeString());//set clock text
  }
}
	
