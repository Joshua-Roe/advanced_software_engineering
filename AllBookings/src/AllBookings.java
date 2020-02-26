/**
 * @author ra124
 *
 */
import java.util.HashMap;

public class AllBookings {
	private int numOfBookings = 0;
	private HashMap<String, Booking> bookings;

	public AllBookings() {
		this.bookings = new HashMap<String, Booking>();
	}
	
	public boolean addBooking(Booking booking) {
		//add elements to hashmap
		bookings.put(booking.getReference(), booking);
		numOfBookings +=1;
		return true;
	}
	
	//TODO: getBooking() & getNumOfBookings()
	public Booking getBooking(String reference) {
		if (reference.trim().length() == 0)
			throw new IllegalStateException("error");
		
		return bookings.get(reference);
	} 
	
	public int getnumofBookings() {
		return numOfBookings;
	}
}
