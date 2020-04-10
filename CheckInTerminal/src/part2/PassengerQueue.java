package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import part1.*;

@SuppressWarnings("deprecation")
public class PassengerQueue extends Thread implements Subject {
    // private float time;
    private Queue<Booking> queue = new LinkedList<>();
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    private Timer timer;

    public PassengerQueue(Timer timer) {
        // TODO: possibly change to passengers are added to queue at given sim time, in
        this.timer = timer;
    }

    public synchronized void enqueue(Booking booking) {
        queue.add(booking);
        notifyObservers();
    }

    public synchronized Booking dequeue() {
        Booking queueHead = queue.remove();
        notifyObservers();
        return queueHead;
    }

    public int size() {
        return queue.size();
    }

    public synchronized Queue<Booking> getQueue() {
        return this.queue;
    }

    public void run() {
        synchronized(timer){
            while (queue.size() > 0){
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End of queue reached");
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