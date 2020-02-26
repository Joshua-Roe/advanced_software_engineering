
public class FlightCheckIn {
	public static void main(String[] args) {
		setup maps = new setup();
		GUI g = new GUI(maps.getBookings(), maps.getFlights());
	}
}
