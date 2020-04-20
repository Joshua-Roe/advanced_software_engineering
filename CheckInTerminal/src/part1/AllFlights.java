package part1;

import java.util.HashMap;
/**
 * <tt>AllFlights</tt> holds <tt>Flight</tt> objects in a <tt>HashMap</tt>, while also keeping track of the
 * number of flights there are.
 * @see     HashMap
 * @see     Flight
 * @author Sean Katagiri
 */
public class AllFlights {
	private int numOfFlights;
	private HashMap<String,Flight> flights;
	
	/**
     * Constructs an empty <tt>HashMap</tt> with a String for its key and a <tt>Flight</tt> object for
     * its value
     */
	public AllFlights() {
		this.numOfFlights = 0;
		this.flights = new HashMap<String, Flight>();
	}
    /**
     * Adds a new <tt>Flight</tt> object to the flights <tt>HashMap</tt>. 
     * Throws <tt>IllegalStateException</tt> for new input element if an element with the same key
     * already exists in the <tt>HashMap</tt> or if given key is a blank.
     * 
     * @param flight the <tt>Flight</tt> object to be added
     * @throws IllegalStateException if duplicate key is found in the <tt>HashMap</tt>, or if key is an empty String
     * @return true if <tt>Flight</tt> was successfully added to <tt>HashMap</tt>
     */
	public boolean addFlight(Flight flight) {
		String key = flight.getFlightCode();
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		Flight output = flights.put(key, flight);
		if(output != null) {
			throw new IllegalStateException("Duplicate entry with identical flight code found");
		}
		this.numOfFlights +=1;
		return true;
	}
	
    /**
     * Returns the <tt>Flight</tt> object assigned to a given flight code.
     * 
     * @param key the flight code for the given flight object
     * @throws IllegalStateException if key is an empty String
     * @return the <tt>Flight</tt> object assigned to the flight code
     */
	
	public Flight getFlight(String key) {
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		return flights.get(key);
	}
	/**
     * Returns the total number of flights contained in flights <tt>HashMap</tt>
     * @return the total number of flights contained in flights <tt>HashMap</tt>
     */
	public int getTotalFlights() {
		return this.numOfFlights;
	}
	/**
     * Returns the flights <tt>HashMap</tt> collection
     * @return the flights <tt>HashMap</tt> collection
     */
	public HashMap<String, Flight> getAllFlights(){
		return flights;
   }
}
