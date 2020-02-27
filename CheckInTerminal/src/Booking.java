package CheckInTerminal;
public class Booking{
    public String booking_reference;
	public String first_name;
    public String last_name;
    public String flight_number;
    public boolean checked_in_status;
    public float baggage_weight;
    public float baggage_volume;

    public Booking(String new_booking_reference, String new_first_name, String new_last_name, String new_flight_number) {
        booking_reference = new_booking_reference;
        first_name = new_first_name;
        last_name = new_last_name;
        flight_number = new_flight_number;
        checked_in_status = false;
    }
    public String getReference() {
        return booking_reference;
    }
    public String getFirstName() {
        return first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public String getFlightCode() {
        return flight_number;
    }
    public boolean getCheckInStatus() {
        return checked_in_status;
    }
    public void setCheckInStatus(boolean new_check_in_status) {
        checked_in_status = new_check_in_status;
    }
    public void setBaggageInfo(float w, float v) {
        baggage_weight = w;
        baggage_volume = v;
    }
}

