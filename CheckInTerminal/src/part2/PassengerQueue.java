package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import part1.*;

public class PassengerQueue extends Thread implements Observer {
    // private float time;
    public Queue<Booking> queue = new LinkedList<>();

    public PassengerQueue(AllBookings bookings, List<CheckinCounter> counters) {
        // add all passengers to queue
        // TODO: possibly change to passengers are added to queue at given sim time, in
        // which case move to run()
        queue.addAll(bookings.getAllBookings().values());

        // Add queue as an observer to all counters
        for (CheckinCounter counter : counters)
            counter.registerObserver(this);
    }

    public synchronized void enqueue(Booking booking) {
        queue.add(booking);
    }

    public synchronized Booking dequeue() {
        return queue.remove();
    }

    public int size() {
        return queue.size();
    }

    public void run() {

        while (queue.size() > 0)
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        System.out.println("End of queue reached");
    }

	@Override
	public void update(Observable o, Object desk_number) {
        //TODO replace this with GUI update
        System.out.println("***Desk " + desk_number + " is now available***");
    }
    
    public synchronized void updateQueue(){
        //TODO replace this with GUI update
        // display list of passengers in queue
        System.out.println("Number of passengers in qeueue: " + this.size());
        System.out.println("----------------------------------");
        for (Booking b : queue){
            System.out.println(b.getFirstName() + " " + b.getLastName());
        }
        System.out.println("----------------------------------");
    }
}