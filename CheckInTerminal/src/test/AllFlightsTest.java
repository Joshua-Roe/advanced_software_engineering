package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import part1.*;

class AllFlightsTest {

	@Test
	void testAddDuplicate() {
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		AllFlights flights = new AllFlights();
		flights.addFlight(flight1);
		try {
			flights.addFlight(flight1);
		}
		catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
//			System.out.println(e.getMessage());
		}
	}

	@Test
	void testAddFlight() {
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		Flight flight2 = new Flight("DEF14","Dubai", "RyanAir", 100, 10, 10, 50);
		AllFlights flights = new AllFlights();
		flights.addFlight(flight1);
		flights.addFlight(flight2);
		assertEquals(flights.getFlight("ABC15"), flight1);
		assertEquals(flights.getFlight("DEF14"), flight2);
		assertNotEquals(flights.getFlight("abc15"), flight1);
	}
	
	@Test
	void testGetTotalFlights() {
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		Flight flight2 = new Flight("DEF14","Dubai", "RyanAir", 100, 10, 10, 50);
		AllFlights flights = new AllFlights();
		assertEquals(flights.getTotalFlights(),0);
		flights.addFlight(flight1);
		assertEquals(flights.getTotalFlights(),1);
		flights.addFlight(flight2);
		assertEquals(flights.getTotalFlights(),2);
	}
}
