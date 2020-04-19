package part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.Random;

import part1.*;

/**
 * PassengerQueue threaded object.
 * Holds {@code Queue<Booking>}, to which it adds and removes {@code Booking} objects from.
 * It implements the {@code Subject} interface to make it observable.
 * 
 * @see java.util.Queue
 * @see part1.Booking
 * @see part2.Subject
 *  
 * @author Sean Katagiri
 * @version %I%, %G%
 */
@SuppressWarnings("deprecation")
public class PassengerQueue extends Thread implements Subject {
    private Queue<Booking> queue = new LinkedList<>();
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    private List<Booking> bookingList = new ArrayList<Booking>();
    private Timer timer;
    private Random generator = new Random();
    private Boolean finishedEnqueue = false;

    /** 
    * Constructor.
    * 
    * @param timer (required) {@code Timer} object in charge of managing simulation timer.
    * @param bookings (required) {@link AllBookings} object containing all {@link Booking}.
    * @see part2.Timer
    * @see part1.AllBookings
    */
    public PassengerQueue(Timer timer, AllBookings bookings) {
        this.timer = timer;
        this.bookingList.addAll(bookings.getAllBookings().values());
    }

    /**
     * Method for moving an object to the back of the queue.
     * Removes the head object of {@code queue} and adds it back at the end.
     * Currently only use is to move {@Booking} objects that have missed their flights to the end of the queue repeatedly.
     * Future work would be to create a separate collection to hold these {@Booking} objects.
     * 
     * @see java.util.Queue#remove()
     * @see java.util.Queue#add(Object)
     */
    public synchronized void moveToBackOfQueue(){
        Booking temp = queue.remove();
        queue.add(temp);
    }

    /**
     * Method for adding a random {@code Booking} object to the {@code queue}.
     * While the {@code bookingList} is not empty, it removes an {@code Booking} element at random, and adds it to the {@code queue}.
     * Notifies its observers when added.
     * Also logs a message each time this task is performed.
     * When {@code bookingList} is empty, sets {@code finishedEnqueue} to true to prevent repeatedly logging end of queue message.
     * 
     * @see java.util.Queue#add(Object)
     * @see java.util.List#remove(int)
     * @see part2.PassengerQueue#logMessage(String)
     * @see part2.PassengerQueue#notifyObservers()
     */
    public synchronized void enqueueRandom(){
        if(!finishedEnqueue){
            if(bookingList.size()>0){
                Booking booking = bookingList.remove(generator.nextInt(bookingList.size()));
                queue.add(booking);
                logMessage(booking.getFullName()+" joined the queue.");
                notifyObservers();
            }
            else{
                finishedEnqueue = true;
                logMessage("All passengers have joined the queue");
            }
        }
        
    }

    /**
     * A method to enqueue a {@code Booking} object to {@code queue}.
     * A non random version of {@link part2.PassengerQueue#enqueueRandom()}.
     * Also notifies its observers when task complete, and logs who has joined the queue.
     * 
     * @param booking {@link Booking} object to be enqueue'd
     * @see part1.Booking#getFullName()
     * @see part2.PassengerQueue#logMessage(String)
     * @see part2.PassengerQueue#notifyObservers()
     */
    public synchronized void enqueue(Booking booking) {
        queue.add(booking);
        logMessage(booking.getFullName()+" joined the queue.");
        notifyObservers();
    }

    /**
     * A method that removes and returns the head object of {@code queue} 
     * Logs that the passenger has left the queue, and notifies observers.
     * 
     * @return the head object of {@code queue}.
     * @see java.util.Queue#remove()
     * @see part2.PassengerQueue#logMessage(String)
     * @see part2.PassengerQueue#notifyObservers()
     */
    public synchronized Booking dequeue() {
        Booking queueHead = queue.remove();
        logMessage(queueHead.getFullName()+" left the queue.");
        notifyObservers();
        return queueHead;
    }

    /**
     * A simple method to return the size of {@code queue}
     * @see java.util.Queue#size()
     */
    public int size() {
        return queue.size();
    }

    /**
     * A simple method to peek the head of {@code queue}
     * @see java.util.Queue#peek()
     */
    public Booking peek(){
        return queue.peek();
    }

    /**
     * Returns the {@code queue} object of type {@link java.util.Queue}.
     * @return {@code queue}
     */
    public synchronized Queue<Booking> getQueue() {
        return this.queue;
    }

    /**
     * A method to log a message.
     * Calls the instance of {@link part2.Log}, and calls its log method with {@code message}
     * @param message the message to be logged
     * @see part2.Log#log(String)
     */
    private synchronized void logMessage(String message){
        Log l = Log.INSTANCE;
        l.log(this.timer.getTimeString()+" "+message);
    }

    /** 
     * Run method for the thread
     * synchronised with {@link part2.Timer} object {@code timer}, waits to be notified before performing task each loop.
     * Initially adds 6 passengers to the queue, and adds an additional 6 every timer tick.
     * 
     * @see java.lang.Thread#run()
     * @see java.lang.Thread#wait()
     * @see part2.Timer
     * @see part2.PassengerQueue#enqueueRandom()
     */
    public void run() {
        enqueueRandom();
        enqueueRandom();
        enqueueRandom();
        enqueueRandom();
        enqueueRandom();
        enqueueRandom();
        synchronized(timer){
            while (queue.size() > 0){
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<6;i++){
                    enqueueRandom();
                }
            }
        }
    }

    /**
     * A method for registering observers.
     * Adds an {@code Observer} object to its list of {@code registeredObservers}.
     * @see java.util#Observer 
     */
    @Override
    public void registerObserver(Observer obs) {
        registeredObservers.add(obs);
    }

    /**
     * A method for removing observers.
     * Removes an {@code Observer} object from its list of {@code registeredObservers}.
     * @see java.util.Observer 
     */
    @Override
    public void removeObserver(Observer obs) {
        registeredObservers.remove(obs);
    }

    /**
     * A method for notifying registered observers.
     * Notifies all {@code Observer} object in its {@code registeredObservers} list.
     * @see java.util.Observer 
     * @see java.util.Observer#update(java.util.Observable, Object)
     */
    @Override
    public void notifyObservers() {
        for (Observer obs : registeredObservers)
            obs.update(null, this);
    }
}