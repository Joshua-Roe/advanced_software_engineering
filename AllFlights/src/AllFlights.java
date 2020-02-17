import java.util.HashMap;
public class AllFlights {
	int numOfFlights;
	HashMap<String,String> flights;
	
	public AllFlights() {
		this.numOfFlights = 0;
		this.flights = new HashMap<String, String>();
	}
	
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
	
	public String getFlight(String key) {
		if(key.trim().length()==0) {
			throw new IllegalStateException("Cannot have a blank key");
		}
		return flights.get(key);
	}
}
