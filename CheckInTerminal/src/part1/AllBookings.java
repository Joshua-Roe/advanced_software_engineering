package part1;

import java.util.HashMap;
/**
 * <tt>AllBookings</tt> holds <tt>Booking</tt> objects in a <tt>HashMap</tt>, while also keeping track of the
 * number of bookings there are.
 * @see     HashMap
 * @see     Booking
 */
public class AllBookings {
	private int numOfBookings = 0;
	private HashMap<String, Booking> bookings;
	
	/**
     * Constructs an empty <tt>HashMap</tt> with a String for its key and a <tt>Booking</tt> object for
     * its value
     */
	public AllBookings() {
		this.bookings = new HashMap<String, Booking>();
	}
	
    /**
     * Adds a new <tt>Booking</tt> object to the flights <tt>HashMap</tt>. 
     * Throws <tt>IllegalStateException</tt> for new input element if an element with the same key
     * already exists in the <tt>HashMap</tt> or if given key is a blank.
     * 
     * @param booking the <tt>Booking</tt> object to be added
     * @throws IllegalStateException if duplicate key is found in the <tt>HashMap</tt>, or if key is an empty String
     * @return true if <tt>Booking</tt> was successfully added to <tt>HashMap</tt>
     */

	public boolean addBooking(Booking booking) {
		bookings.put(booking.getReference(), booking);
		numOfBookings +=1;
		return true;
	}
     
     /**
      * Returns the <tt>Booking</tt> object assigned to a given booking reference code.
      * 
      * @param reference the boking reference code for the given flight object
      * @throws IllegalStateException if reference is an empty String
      * @return the <tt>Booking</tt> object assigned to the booking reference code
      */
	public Booking getBooking(String reference) {
		if (reference.trim().length() == 0)
			throw new IllegalStateException("Cannot enter empty booking reference");
		
		return bookings.get(reference);
	} 
	
	/**
     * Returns the total number of bookings contained in bookings <tt>HashMap</tt>
     * @return the total number of bookings contained in bookings <tt>HashMap</tt>
     */
	public int getnumofBookings() {
		return numOfBookings;
     }
     
     /**
     * Returns the bookings <tt>HashMap</tt> collection
     * @return the bookings <tt>HashMap</tt> collection
     */
     public HashMap<String, Booking> getAllBookings(){
          return bookings;
     }
}
