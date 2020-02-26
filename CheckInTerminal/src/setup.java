import java.io.*; 
import java.util.Random;
import java.util.HashMap;
import java.util.zip.CRC32;
//import java.io.File;

public class setup { 
	private AllFlights flights;
	private AllBookings bookings;

	public setup() {
		try {
			File bookingCSV = new File("../data/bookingDetails.csv"); 
			File flightCSV = new File("../data/flightDetails.csv");
			
			BufferedReader bookingBR = new BufferedReader(new FileReader(bookingCSV)); 
			BufferedReader flightBR = new BufferedReader(new FileReader(flightCSV));
			
			bookings = new AllBookings();
			flights = new AllFlights();
			
			String st; 
			while ((st = bookingBR.readLine()) != null) {
				String[] temp = st.split(",", 0);
				Booking booking = new Booking(temp[0], temp[1], temp[2], temp[3]);
				bookings.addBooking(booking);
			}
			
			while ((st = flightBR.readLine()) != null) {
				String[] temp = st.split(",", 0);
				Flight flight = new Flight(temp[0], temp[3], temp[1], Integer.parseInt(temp[5]), Float.parseFloat(temp[6]), Float.parseFloat(temp[7]), Float.parseFloat(temp[8]));
				flights.addFlight(flight);
			}
			
			bookingBR.close();
			flightBR.close();
			//System.out.println("input:"+ flightHash);
			
			//flightData x = flightHash.get("LM365");
			//System.out.println(x.destination);
			//System.out.println(bookingREF);
				//System.out.println(st); 
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch(IOException e) {
			System.out.println("IO exception experienced.");
		}
	} 
	
	public AllBookings getBookings() {
		return bookings;
	}
	
	public AllFlights getFlights() {
		return flights;
	}
} 