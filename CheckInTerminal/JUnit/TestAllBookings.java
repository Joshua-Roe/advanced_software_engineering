import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAllBookings {

	@Test
	void test() {
	    AllBookings bookings = new AllBookings();
		assertEquals(bookings.getnumofBookings(), 0);
		bookings.addBooking(booking);
		assertEquals(bookings.getnumofBookings(), 1);
	}
}
