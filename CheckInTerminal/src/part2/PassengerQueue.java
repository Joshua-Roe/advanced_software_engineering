package part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.Random;

import part1.*;

@SuppressWarnings("deprecation")
public class PassengerQueue extends Thread implements Subject {
    // private float time;
    private Queue<Booking> queue = new LinkedList<>();
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    private List<Booking> bookingList = new ArrayList<Booking>();
    private Timer timer;
    private Random generator = new Random();
    private Boolean finishedEnqueue = false;

    public PassengerQueue(Timer timer, AllBookings bookings) {
        this.timer = timer;
        this.bookingList.addAll(bookings.getAllBookings().values());
    }

    public synchronized void moveToBackOfQueue(){
        Booking temp = queue.remove();
        queue.add(temp);
    }

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

    public synchronized void enqueue(Booking booking) {
        queue.add(booking);
        logMessage(booking.getFullName()+" joined the queue.");
        notifyObservers();
    }

    public synchronized Booking dequeue() {
        Booking queueHead = queue.remove();
        logMessage(queueHead.getFullName()+" left the queue.");
        notifyObservers();
        return queueHead;
    }

    public int size() {
        return queue.size();
    }

    public Booking peek(){
        return queue.peek();
    }

    public synchronized Queue<Booking> getQueue() {
        return this.queue;
    }

    private synchronized void logMessage(String message){
        Log l = Log.INSTANCE;
        l.log(this.timer.getTimeString()+" "+message);
    }
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