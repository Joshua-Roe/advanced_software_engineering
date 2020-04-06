package part1;
/**
 * <tt>Booking</tt> is an object used to store details for each booking.
 */
public class Booking{
    public String booking_reference;
	public String first_name;
    public String last_name;
    public String flight_number;
    public boolean checked_in_status;
    public float baggage_weight;
    public float baggage_L;
    public float baggage_H;
    public float baggage_W;
	/**
     * Constructs a <tt>Booking</tt> object with given parameters as its booking details.
     * @param new_booking_reference the booking reference code.
     * @param new_first_name the first name of the booking.
     * @param new_last_name the last name of the booking.
     * @param new_flight_number the flight code for the flight for this booking.
     */
    public Booking(String new_booking_reference, String new_first_name, String new_last_name, String new_flight_number, float new_baggage_weight, float new_baggage_L, float new_baggage_H, float new_baggage_W) {
        booking_reference = new_booking_reference;
        first_name = new_first_name;
        last_name = new_last_name;
        flight_number = new_flight_number;

        baggage_weight = new_baggage_weight;
        baggage_L = new_baggage_L;
        baggage_H = new_baggage_H;
        baggage_W = new_baggage_W;

        checked_in_status = false;
    }
    
    /**
     * @return the booking reference code.
     */
    public String getReference() {
        return booking_reference;
    }
    
    /**
     * @return the first name.
     */
    public String getFirstName() {
        return first_name;
    }
    
    /**
     * @return the last name.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @return the last name.
     */
    public String getFullName() {
        return first_name+" "+last_name;
    }
    /**
     * @return the flight code.
     */
    public String getFlightCode() {
        return flight_number;
    }
    
    /**
     * @return the check in status.
     */
    public boolean getCheckInStatus() {
        return checked_in_status;
    }
    
    /**
     * Sets check in status of booking 
     * @param new_check_in_status the new check in status for the booking
     */
    public void setCheckInStatus(boolean new_check_in_status) {
        checked_in_status = new_check_in_status;
    }
    
    /**
     * Sets baggage info for booking
     * @param w the weight of the baggage
     * @param v the volume of the baggage
     */
    public void setBaggageInfo(float w, float v) {
        //baggage_weight = w;
        //baggage_volume = v;
    }
}

