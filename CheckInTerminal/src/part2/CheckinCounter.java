package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import part1.Booking;
import part1.AllFlights;
import part1.Flight;

@SuppressWarnings("deprecation")
public class CheckinCounter extends Thread implements Subject {
    private int counter_number;
    private PassengerQueue queue;
    private Booking passenger;
    private Flight passenger_flight;
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    private AllFlights flights;
    private SimTime t;
    private Timer timer;

    public CheckinCounter(int number, AllFlights flights, SimTime t, Timer timer) {
        this.counter_number = number;
        this.flights = flights;
        this.t = t;
        this.timer = timer;
    }

    public void run() {
        synchronized(timer){
            while (queue.size()>0) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                serveCustomer();
            }
        }
    }

    public void setQueue(PassengerQueue queue){
        this.queue = queue;
    }

    public int getCounterNumber(){
        return this.counter_number;
    }

    public Booking getBooking(){
        return this.passenger;
    }

    public void setPassengerFlight(){
        this.passenger_flight = flights.getFlight(passenger.getFlightCode());
    }

    public synchronized void serveCustomer(){
        // notify PassengerQueue for update queue list
        //TODO Possibly better to notify GUI as an observer than the Passenger queue as an observer
        if(queue.size()>0){
            this.passenger = queue.dequeue();
            setPassengerFlight();
            // System.out.println("++Counter "+this.counter_number+" is now serving " + passenger.getFullName()+"++");
            // flight.checkBaggage(passenger.baggage_weight, passenger.baggage_volume);
            passenger_flight.addPassenger();
            // System.out.println("++"+passenger.getFullName() + " checked into Flight number " + passenger_flight.getFlightCode()+"++");
            notifyObservers();
        }
    }
    @Override
    public void registerObserver(Observer obs) {
        registeredObservers.add(obs);
        if(obs instanceof PassengerQueue) this.queue = (PassengerQueue) obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        registeredObservers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : registeredObservers) obs.update(null, this);
    }
}