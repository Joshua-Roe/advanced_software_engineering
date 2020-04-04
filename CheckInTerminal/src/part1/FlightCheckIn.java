package part1;

import java.util.Iterator;
import java.util.Map;

public class FlightCheckIn {
	public static void generateReport(AllFlights flights) {
		Iterator<Map.Entry<String, Flight>> it = flights.getAllFlights().entrySet().iterator();
        while(it.hasNext()) {
        	Map.Entry<String, Flight> MapElement = it.next();
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
		new GUI(reader.getBookings(), reader.getFlights());
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            generateReport(reader.getFlights());
	        }
	    }, "Shutdown-thread"));
	}
}
