package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import part1.*;
public class TestCounters{
    //TODO: Remove once testing over
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<CheckinCounter> counters = new LinkedList<CheckinCounter>();
        CheckinCounter c1 = new CheckinCounter(1); counters.add(c1);
        CheckinCounter c2 = new CheckinCounter(2); counters.add(c2);
        CheckinCounter c3 = new CheckinCounter(3); counters.add(c3);
        Booking b1 = new Booking("123", "Sean", "Katagiri", "AF1");
        Booking b2 = new Booking("456", "Joshua", "Roe", "AF1");
        Booking b3 = new Booking("789", "Leo", "Kong", "AF1");
        AllBookings bookings = new AllBookings();
        bookings.addBooking(b1);
        bookings.addBooking(b2);
        bookings.addBooking(b3);
        PassengerQueue pq = new PassengerQueue(bookings, counters);
        pq.start();
        c1.start();
    }
}