package CheckInTerminal;

import java.util.Iterator;
import java.util.Map;

public class FlightCheckIn {
	public static void generateReport(AllFlights flights) {
		Iterator it = flights.flights.entrySet().iterator();
        while(it.hasNext()) {
        	Map.Entry MapElement = (Map.Entry)it.next();
        	System.out.println("Flight Code: " + MapElement.getKey());
        	Flight flight = (Flight) MapElement.getValue();
        	System.out.println("Number of checked-in passengers: " + flight.getNumOfPassengers() + " / " + flight.getMaxPassengers());
        	System.out.println("Total baggage weight: " + flight.getTotalBaggageWeight());
        	System.out.println("Total baggage volume: " + flight.getTotalBaggageVolume());
        	System.out.println("Total excess fees collected: " + flight.getCollectedFees());
        	String cap = (flight.getNumOfPassengers() > flight.getMaxPassengers()) ? "Y" : "N";
        	System.out.println("Over capacity?: " + cap);
        	System.out.println();
        }
        System.out.println("End of report.");
	}
	public static void main(String[] args) {
		CSVReader reader = new CSVReader();
		GUI g = new GUI(reader.getBookings(), reader.getFlights());
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            generateReport(reader.getFlights());
	        }
	    }, "Shutdown-thread"));
	}
}
