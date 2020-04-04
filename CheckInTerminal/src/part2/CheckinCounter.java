package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import part1.Booking;

public class CheckinCounter extends Thread implements Subject {
    private int desk_number;
    private PassengerQueue queue;
    private Booking passenger;
    private List<Observer> registeredObservers = new LinkedList<Observer>();

    public CheckinCounter(int number) {
        this.desk_number = number;
    }

    public void run() {
        while (true) {
            serveCustomer();
        }
    }

    public void serveCustomer(){
        if(queue.size()>0){
            this.passenger = queue.dequeue();
            System.out.println("Serving " + passenger.getFullName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Interrupt Exception");
            }
            // notify PassengerQueue for update queue list
            notifyObservers();
        }
    }
    @Override
    public void registerObserver(Observer obs) {
        registeredObservers.add(obs);
        this.queue = (PassengerQueue) obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        registeredObservers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : registeredObservers) obs.update(null, desk_number);
    }
}