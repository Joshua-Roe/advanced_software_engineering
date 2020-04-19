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
    private Timer timer;
    private int currentTime;
    private String currentTimeString;
    private boolean open;

    public CheckinCounter(int number, AllFlights flights, Timer timer) {
        this.counter_number = number;
        this.flights = flights;
        this.timer = timer;
        this.open = true;
    }

    public synchronized void closeCounter(){
        
        this.open = !this.open;
        if(this.open){
            logMessage("Checkin counter "+this.counter_number+" opened.");
        }
        else logMessage("Checkin counter "+this.counter_number+" closed.");
    }

    public void run() {
        synchronized(timer){
            while (true) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTimeString = timer.getTimeString();
                currentTime = timer.getTime();
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

    public boolean getIsOpen(){
        return this.open;
    }

    private void logMessage(String message){
        Log l = Log.INSTANCE;
        l.log(this.currentTimeString+" "+message);
    }

    public synchronized void serveCustomer(){
        if(queue.size()>0 && this.open){
            //TODO: clear passenger details once queue has ended so GUI isnt stuck with last served passenger
            if(!this.queue.peek().getMissedFlight()){
                this.passenger = queue.dequeue();
                setPassengerFlight();
                if(this.passengerFlight.checkGateOpen(this.currentTime,this.currentTimeString)){
                    try{
                        this.passengerFlight.checkBaggage(passenger.getBaggageWeight(), passenger.getBaggageLength(),passenger.getBaggageHeight(),passenger.getBaggageWidth());
                    }
                    catch(OverBaggageLimitException e){
                        this.passenger.setExcessFeeCharged(this.passengerFlight.getExcessFeeCharge());
                    }
                    this.passengerFlight.addPassenger();
                    logMessage("[Counter "+this.counter_number+"] "+this.passenger.getFullName()+" checked into flight "+this.passengerFlight.getFlightCode()+". Excess fee of £"+this.passenger.getExcessFeeCharged()+" charged.");
                }
                else{
                    this.passenger.missFlight();
                    this.queue.enqueue(this.passenger);
                    logMessage("[Counter "+this.counter_number+"] "+this.passengerFlight.getFlightCode()+" has already departed, "+this.passenger.getFullName()+" has missed their flight and has joined the end of the queue.");
                }
                notifyObservers();
            }
            else{
                queue.moveToBackOfQueue();
                this.passenger = null;
                this.passengerFlight = null;
                notifyObservers();
            }
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