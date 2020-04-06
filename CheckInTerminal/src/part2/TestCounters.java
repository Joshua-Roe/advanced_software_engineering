package part2;

import java.util.LinkedList;
import java.util.List;

import part1.*;

public class TestCounters {
    // TODO: Remove once testing over
    public static void main(String[] args) {
        List<CheckinCounter> counters = new LinkedList<CheckinCounter>();
        AllBookings bookings = new AllBookings();
        AllFlights flights = new AllFlights();
        Flight f1 = new Flight("AF1", "Edinburgh", "AirFrance", 200, 23, 30, 15);
        flights.addFlight(f1);
        SimTime t = new SimTime();
        CheckinCounter c1 = new CheckinCounter(1,flights,t);
        counters.add(c1);
        // CheckinCounter c2 = new CheckinCounter(2,flights);
        // counters.add(c2);
        // CheckinCounter c3 = new CheckinCounter(3,flights);
        // counters.add(c3);
        Booking b1 = new Booking("123", "Sean", "Katagiri", "AF1");
        Booking b2 = new Booking("456", "Joshua", "Roe", "AF1");
        Booking b3 = new Booking("789", "Leo", "Kong", "AF1");
        Booking b4 = new Booking("111", "Marek", "Kurjawa", "AF1");
        Booking b5 = new Booking("222", "Randy", "Adjepong", "AF1");
        Booking b6 = new Booking("333", "Deo", "Kong", "AF1");
        Booking b7 = new Booking("444", "Bean", "Katagiri", "AF1");
        Booking b8 = new Booking("555", "Poshua", "Roe", "AF1");
        Booking b9 = new Booking("666", "Tarek", "Kurjawa", "AF1");
        Booking b10 = new Booking("777", "Dandy", "Adjepong", "AF1");
        bookings.addBooking(b1);
        bookings.addBooking(b2);
        bookings.addBooking(b3);
        bookings.addBooking(b4);
        bookings.addBooking(b5);
        bookings.addBooking(b6);
        bookings.addBooking(b7);
        bookings.addBooking(b8);
        bookings.addBooking(b9);
        bookings.addBooking(b10);
        PassengerQueue pq = new PassengerQueue(bookings, counters);
        pq.updateQueue();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pq.start();
        c1.start();
        // c2.start();
        // c3.start();
    }
}