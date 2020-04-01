package test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.Test;

class FlightTest {

	@Test
	void testFlight(){
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		assertEquals(flight1.getFlightCode(), "ABC15");
		assertEquals(flight1.getDestination(), "UK");
		assertEquals(flight1.getCarrier(), "AirFrance");
		assertEquals(flight1.getNumOfPassengers(), 0);
		assertEquals(flight1.getMaxPassengers(), 200);
		assertEquals(flight1.getBaggageLevy(), 15);
	}
	
	@Test
	void testAddPassenger() {
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		flight1.addPassenger();
		assertEquals(flight1.getNumOfPassengers(), 1);
	}
	
	@Test
	void testBaggage() {
		Flight flight1 = new Flight("ABC15","UK", "AirFrance", 200, 30, 50, 15);
		flight1.checkBaggage(20, 20);
		assertEquals(flight1.getTotalBaggageVolume(), 20);
		assertEquals(flight1.getTotalBaggageWeight(), 20);
		try {
			flight1.checkBaggage(40, 40);
		}
		catch(Exception e){
			assertTrue(e instanceof Flight.OverBaggageLimitException);
		}
		assertEquals(flight1.getCollectedFees(), flight1.getBaggageLevy());
	}
}
