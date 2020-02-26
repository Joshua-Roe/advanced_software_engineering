import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI {
	static JFrame frame;
	private AllBookings bookings;
	private AllFlights flights;
	private float w,v;
    public GUI(AllBookings bookings, AllFlights flights) {
    	this.bookings = bookings;
    	this.flights = flights;
        //create frame
        JFrame checkFrame = new JFrame("Check In");
        checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkFrame.setSize(700, 170);
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
        	  if(confirm(nameText.getText().trim(),refText.getText().trim(),sizeText.getText().trim(),weightText.getText().trim())) {
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
		if(name.length() == 0 || ref.length() == 0 || size.length() == 0 || weight.length() == 0) {
			JOptionPane.showMessageDialog(frame, "Please Fill All Inputs");
		return false;
		
		}
		//Check valid baggage info
		try {
			w = Float.parseFloat(weight);
			v = Float.parseFloat(size);
		}
		catch(NumberFormatException e) {
	    	JOptionPane.showMessageDialog(frame, "Please enter numbers without characters for baggage size and weight.");
	    	return false;
		}
	    Booking booking = bookings.getBooking(ref);
	    if(!booking.getLastName().equals(name)) {
	    	JOptionPane.showMessageDialog(frame, "Booking details not found. Please check your inputs again.");
	    	return false;
	    }
	    if(booking.getCheckInStatus()) {
	    	JOptionPane.showMessageDialog(frame, "You are already checked in");
	    	return false;
	    }
	    booking.setBaggageInfo(w,v);
	    String flightCode = booking.getFlightCode();
	    Flight flight = flights.getFlight(flightCode);
	    try {
	    	flight.checkBaggage(w, v);
	    }
	    catch(Flight.OverBaggageLimitException e) {
	    	JOptionPane.showMessageDialog(frame, "Baggage limit exceeded. \r\nYou have been charged £" + flight.getBaggageLevy() + "0.");
	    }
	    flight.addPassenger();
	    booking.setCheckInStatus(true);
			JOptionPane.showMessageDialog(frame, "Check In Confirmed.");
			return true;
	    }
}
	
