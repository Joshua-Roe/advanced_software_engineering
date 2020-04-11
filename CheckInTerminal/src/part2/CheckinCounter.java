package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import part1.Booking;
import part1.AllFlights;
import part1.Flight;
import part1.Flight.OverBaggageLimitException;

@SuppressWarnings("deprecation")
public class CheckinCounter extends Thread implements Subject {
    private int counter_number;
    private PassengerQueue queue;
    private Booking passenger;
    private Flight passengerFlight;
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
        this.passengerFlight = flights.getFlight(passenger.getFlightCode());
    }

    public Float getPassengerExcessFee(){
        return this.passenger.getExcessFeeCharged();
    }

    public synchronized void serveCustomer(){
        if(queue.size()>0){
            this.passenger = queue.dequeue();
            setPassengerFlight();
            //TODO: clear passenger details once queue has ended so GUI isnt stuck with last served passenger
            try{
                this.passengerFlight.checkBaggage(passenger.getBaggageWeight(), passenger.getBaggageLength(),passenger.getBaggageHeight(),passenger.getBaggageWidth());
            }
            catch(OverBaggageLimitException e){
                this.passenger.setExcessFeeCharged(this.passengerFlight.getExcessFeeCharge());
            }
            this.passengerFlight.addPassenger();
            if(this.passengerFlight.getFlightCode().equals("BA2503")){
                System.out.println("Checked one in, passenger number is now " + this.passengerFlight.getNumberOfPassengers());
            }
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