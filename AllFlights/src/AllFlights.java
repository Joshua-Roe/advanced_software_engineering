import java.util.HashMap;
public class AllFlights {
	private int numOfFlights;
	private HashMap<String,Flight> flights;
	
	public AllFlights() {
		this.numOfFlights = 0;
		this.flights = new HashMap<String, Flight>();
	}
    /**
     * Adds a new <tt>Flight</tt> object with a given flight code to the flights <tt>HashMap</tt>. 
     * Throws <tt>IllegalStateException</tt> for new input element if an element with the same key
     * already exists in the <tt>HashMap</tt> (also throws exceptions with different descriptions
     * depending on whether it is a duplicate entry or just duplicate keys), or if given key is a 
     * blank.
     * 
     * @param key the flight code String to assign to the <tt>Flight</tt> object
     * @param value the <tt>Flight</tt> object to be added to the flights <tt>HashMap</tt>
     * @return 1 if <tt>Flight</tt> was successfully added to <tt>HashMap</tt>
     */
	public boolean addFlight(String key, Flight value) {
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		Flight output = flights.put(key, value);
		if(output != null) {
			if(output == value) {
				throw new IllegalStateException("Duplicate Entry found");
			}
			else {
				throw new IllegalStateException("Key already contained an object: " + output);
			}
		}
		this.numOfFlights +=1;
		return true;
	}
	
    /**
     * Returns the <tt>Flight</tt> object assigned to a given flight code.
     * 
     * @param key the flight code for the given flight object
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
}
