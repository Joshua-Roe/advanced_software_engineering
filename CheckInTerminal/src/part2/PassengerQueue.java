package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import part1.*;

public class PassengerQueue extends Thread implements Observer{
    // private float time;
    public Queue<Booking> queue = new LinkedList<>();

    public PassengerQueue(AllBookings bookings, List<CheckinCounter> counters){
        // add all passengers to queue
        //TODO: possibly change to passengers are added to queue at given sim time, in which case move to run()
        queue.addAll(bookings.getAllBookings().values());

        //Add queue as an observer to all counters
        for (CheckinCounter counter : counters) counter.registerObserver(this);
    }

    public void enqueue(Booking booking){
        queue.add(booking);
    }

    public Booking dequeue(){
       return queue.remove(); 
    }

    public int size(){
        return queue.size(); 
    }
    
    public void run(){
        try{
            while(true){
                System.out.println("Waiting for desk to open up...");
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e ){
            System.out.println("InterruptedException!");
        }
        catch(Exception e){
            System.out.println("Unexpected exception!");
            e.printStackTrace();
        }
        
    }

	@Override
	public void update(Observable o, Object desk_number) {
        System.out.println("Desk " + desk_number + " is now available");
        System.out.println(queue.peek().getFullName() + " removed from queue.");
        System.out.println("----------------------------------");
		// display list of passengers in queue
        for (Booking b : queue){
            System.out.println(b.getFirstName() + " " + b.getLastName());
        }
    }
    
    public void updateQueue(){
        
    }
}