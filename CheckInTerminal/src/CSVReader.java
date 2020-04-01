package CheckInTerminal;
import java.io.*;
import java.util.Random;

public class CSVReader { 
	private AllFlights flights;
	private AllBookings bookings;

	public CSVReader() {
		try {
		    String sep = File.separator;
		    String bookingCSVpath = new File(".."+sep+"data"+sep+"bookingDetails.csv").getAbsolutePath();
		    String flightCSVpath = new File(".."+sep+"data"+sep+"flightDetails.csv").getAbsolutePath();
		   
			File flightCSV = new File(flightCSVpath);
			File bookingCSV = new File(bookingCSVpath);
			BufferedReader bookingBR = new BufferedReader(new FileReader(bookingCSV)); 
			BufferedReader flightBR = new BufferedReader(new FileReader(flightCSV));
			
			bookings = new AllBookings();
			flights = new AllFlights();
			
			String st; 
			while ((st = bookingBR.readLine()) != null) {
				String[] temp = st.split(",", 0);

				Random rand1 = new Random();
				Random rand2 = new Random();
				float min = 0;
				float maxWeight = 60;
				float maxVolume = 500;
				float ranWeight = rand1.nextFloat() * (maxWeight - min) + min;;
				float ranVolume = rand2.nextFloat() * (maxVolume - min) + min;;
				Booking booking = new Booking(temp[0], temp[1], temp[2], temp[3], ranWeight, ranVolume);
				bookings.addBooking(booking);
			}
			
			while ((st = flightBR.readLine()) != null) {
				String[] temp = st.split(",", 0);
				Flight flight = new Flight(temp[0], temp[3], temp[1], Integer.parseInt(temp[5]), Float.parseFloat(temp[6]), Float.parseFloat(temp[7]), Float.parseFloat(temp[8]));
				flights.addFlight(flight);
			}
			
			bookingBR.close();
			flightBR.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found. " + e);
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