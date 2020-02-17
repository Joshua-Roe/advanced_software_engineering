import java.util.HashMap;
public class AllFlights {
	int numOfFlights;
	HashMap<String,String> flights;
	
	public AllFlights() {
		this.numOfFlights = 0;
		this.flights = new HashMap<String, String>();
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
	public int addFlight(String key, String value) {
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		String output = flights.put(key, value);
		if(output != null) {
			if(output == value) {
				throw new IllegalStateException("Duplicate Entry found");
			}
			else {
				throw new IllegalStateException("Key already contained an object: " + output);
			}
		}
		return 1;
	}
	
    /**
     * Returns the <tt>Flight</tt> object assigned to a given flight code.
     * 
     * @param key the flight code for the given flight object
     * @return the <tt>Flight</tt> object assigned to the flight code
     */
	
	public String getFlight(String key) {
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		return flights.get(key);
	}
}
