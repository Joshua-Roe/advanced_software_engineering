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
    private boolean open;

    //Change this variable to change time to close counter
    private int stopTime = 120;

    public CheckinCounter(int number, AllFlights flights, SimTime t, Timer timer) {
        this.counter_number = number;
        this.flights = flights;
        this.t = t;
        this.timer = timer;
        this.open = true;
    }

    public void closeCounter(){
        if (this.open){
            this.open = false;
            logMessage("Checkin counter "+this.counter_number+" closed");
            notifyObservers();
        }
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
                if(timer.getTime() > this.stopTime){
                    closeCounter();
                } 
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

    public boolean getIsOpen(){
        return this.open;
    }

    private synchronized void logMessage(String message){
        Log l = Log.INSTANCE;
        l.log(this.timer.getTimeString()+" "+message);
    }

    public synchronized void serveCustomer(){
        if(queue.size()>0 && this.open){
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
            logMessage("[Counter "+this.counter_number+"] "+this.passenger.getFullName()+" checked into flight "+this.passengerFlight.getFlightCode()+". Excess fee of £"+this.passenger.getExcessFeeCharged()+" charged.");
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