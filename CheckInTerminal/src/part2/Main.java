package part2;

import java.util.LinkedList;
import java.util.List;

import part1.*;

public class Main {
    public static void main(String[] args) {
        List<CheckinCounter> counters = new LinkedList<CheckinCounter>();
        CSVReader csvReader = new CSVReader();
        AllBookings bookings = csvReader.getBookings();
        AllFlights flights = csvReader.getFlights();
        SimTime t = new SimTime();
        Timer timer = new Timer(t);
        CheckinCounter c1 = new CheckinCounter(1,flights,timer);
        counters.add(c1); 
        CheckinCounter c2 = new CheckinCounter(2,flights,timer);
        counters.add(c2);
        CheckinCounter c3 = new CheckinCounter(3,flights,timer);
        counters.add(c3);
        CheckinCounter c4 = new CheckinCounter(4,flights,timer);
        counters.add(c4);
        CheckinCounter c5 = new CheckinCounter(5,flights,timer);
        counters.add(c5);
        CheckinCounter c6 = new CheckinCounter(6,flights,timer);
        counters.add(c6);
        PassengerQueue pq = new PassengerQueue(timer);
        ManagementGUI gui = new ManagementGUI(timer,t,counters,flights.getAllFlights());
        
        pq.registerObserver(gui);
        flights.getAllFlights().forEach((key,value) -> value.registerObserver(gui));
        bookings.getAllBookings().forEach((key,value) -> pq.getQueue().add(value));

        for(CheckinCounter c : counters){
            c.registerObserver(gui);
            c.setQueue(pq);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.start();
        gui.start();
        pq.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
                Log log = Log.INSTANCE;
                log.savefile();
	        }
	    }, "Shutdown-thread"));
    }
}