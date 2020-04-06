package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import part1.*;

@SuppressWarnings("deprecation")
public class PassengerQueue extends Thread implements Observer, Subject {
    // private float time;
    private Queue<Booking> queue = new LinkedList<>();
    private List<Observer> registeredObservers = new LinkedList<Observer>();

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

    public Queue<Booking> getQueue() {
        return this.queue;
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
    public void update(Observable o, Object counter) {
        // TODO replace this with GUI update
        CheckinCounter current_counter = (CheckinCounter) counter;
        System.out.println("***Counter " + current_counter.getCounterNumber() + " is now available***");
    }

    public synchronized void updateQueue() {
        // TODO replace this with GUI update
        // display list of passengers in queue
        System.out.println("Number of passengers in qeueue: " + this.size());
        System.out.println("----------------------------------");
        for (Booking b : queue) {
            System.out.println(b.getFullName());
        }
        System.out.println("----------------------------------");
    }

    @Override
    public void registerObserver(Observer obs) {
        registeredObservers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        registeredObservers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : registeredObservers)
            obs.update(null, this);
    }
}