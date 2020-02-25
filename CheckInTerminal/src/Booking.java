import java.io.*;
/*
 * @author      Marek Kujawa
 * @version     %I%, %G%
 * @since       1.0
 */
package checkin_package;

import checkin_package.*;

public class Booking{
    public String booking_reference;
	public String first_name;
    public String last_name;
    public String flight_number;
    public boolean checked_in_status;
    public int baggage_weight;
    public int baggage_volume;

    Booking(String new_booking_reference, String new_first_name, String new_last_name, String new_flight_number) {
        booking_reference_code = new_booking_reference;
        first_name = new_first_name;
        last_name = new_last_name;
        flight_number = new_flight_number;
        checked_in_status = false;
    }
    public String getReference() {
        return booking_reference;
    }
    public String getFirstName() {
        return firs_name;
    }
    public String getLastName() {
        return last_name;
    }
    public String getFlightNumber() {
        return flight_number;
    }
    public boolean getCheckInStatus() {
        return checked_in_status;
    }
    public void setCheckInStatus(boolean new_check_in_status) {
        checked_in_status = new_checked_in_status;
    }
    public void setBaggageInfo(int new_baggage_weight, int new_baggage_volume) {
        baggage_weight = new_baggage_weight;
        baggage_volume = new_baggage_volume;
    }
}
