package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import part1.Booking;
import part1.AllFlights;
import part1.Flight;
import part1.Flight.OverBaggageLimitException;
/**
 * CheckinCounter threadded object.
 * The class takes passengers of type {@link part1.Booking} from a {@link part2.PassengerQueue} onject and proceeds to check them into their assigned flights.
 * It implements the {@code Subject} interface to make it observable.
 * 
 * @see part2.Subject
 *  
 * @author Sean Katagiri
 * @version %I%, %G%
 */
@SuppressWarnings("deprecation")
public class CheckinCounter extends Thread implements Subject {
    private int counterNumber;
    private PassengerQueue queue;
    private Booking passenger;
    private Flight passengerFlight;
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    private AllFlights flights;
    private Timer timer;
    private int currentTime;
    private String currentTimeString;
    private boolean open;

    /**
     * Constructor. 
     * 
     * @param number (required) The number that will be assigned to this counter
     * @param flights (required) A {@part1.AllFlights} object containing the list of flights
     * @param timer (required) A {@part2.Timer} object in charge of managing simulation time
     */
    public CheckinCounter(int number, AllFlights flights, Timer timer, PassengerQueue queue) {
        this.counterNumber = number;
        this.flights = flights;
        this.timer = timer;
        this.open = true;
        this.queue = queue;
    }

    /**
     * Method for opening and closing the counter.
     * Checks {@code open} to see the current counter state, and changes to the opposite state.
     * Logs that the counter has opened/closed, along with its counter number.
     * @see part2.CheckinCounter#logMessage(String)
     */
    public synchronized void openCloseCounter(){
        this.open = !this.open;
        if(this.open){
            logMessage("Checkin counter "+this.counterNumber+" opened.");
        }
        else logMessage("Checkin counter "+this.counterNumber+" closed.");
    }

    /** 
     * Run method for the thread
     * synchronised with {@link part2.Timer} object {@code timer}, waits to be notified before performing task each loop.
     * Updates {@code currentTimeString} and {code currentTime} each timer tick, and serves the passenger at head of queue.
     * 
     * @see java.lang.Thread#run()
     * @see java.lang.Thread#wait()
     * @see part2.Timer
     * @see part2.CheckinCounter#servePassenger()
     */
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
                servePassenger();
            }
        }
    }

    /**
     * Getter method that returns the counter number.
     * @return {@code counterNumber} integer.
     */
    public int getCounterNumber(){
        return this.counterNumber;
    }

    /**
     * Getter method that returns the passenger it is currently serving.
     * @return {@code passenger}, the current {@link part1.Booking} object it is serving.
     */
    public Booking getBooking(){
        return this.passenger;
    }

    /**
     * Stores the {@link part1.Flight} object the current passenger the counter is serving is assigned to.
     * @see part1.AllFlights#getFlight(String)
     * @see part1.Flight#getFlightCode()
     */
    public void setPassengerFlight(){
        this.passengerFlight = flights.getFlight(passenger.getFlightCode());
    }

    /**
     * A getter method for the amount the last customer served was charged for excess baggage fees.
     * @return {@code passenger}'s excess fees charged
     * @see part1.Booking#getExcessFeeCharged()
     */
    public Float getPassengerExcessFee(){
        return this.passenger.getExcessFeeCharged();
    }

    /**
     * Getter method for the counter's open/close state.
     * @return {@code open} boolean.
     */
    public boolean getIsOpen(){
        return this.open;
    }

    /**
     * A method to log a message.
     * Calls the instance of {@link part2.Log}, and calls its log method with {@code message}
     * @param message the message to be logged
     * @see part2.Log#log(String)
     */
    private void logMessage(String message){
        Log l = Log.INSTANCE;
        l.log(this.currentTimeString+" "+message);
    }

    /**
     * A method which handles serving of a passenger.
     * First checks that it is open, and that the queue isn't empty.
     * If above conditions are met, it then checks whether the passenger at the head of the queue has already missed their flight.
     * <p>If not, the passenger's assigned flight is stored and is checked to see if the gate is still open. If it is the baggage is checked and assigns fees accordingly, and passenger is added to the flight.
     * <p>If the flight has already departed, the passenger is notified that they have missed their flight.
     * Either case, an appropriate message is logged.
     * If a passenger who has already missed their flight returns to the front of the queue, they are sent to the back of the queue again.
     * <p> This is a temporary fix, as there are no collection to store the passengers who have missed their flight, nor is there the GUI component to show this. This would be another future work feature.
     * Finally, observers are notified to indicate that a change has occurred.
     * @see part2.PassengerQueue#peek()
     * @see part2.PassengerQueue#dequeue()
     * @see part1.Flight#checkGateOpen(int, String)
     * @see part1.Flight#checkBaggage(float, float, float, float)
     * @see part1.Flight#addPassenger()
     * 
     */
    public synchronized void servePassenger(){
        if(queue.size()>0 && this.open){
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
                    logMessage("[Counter "+this.counterNumber+"] "+this.passenger.getFullName()+" checked into flight "+this.passengerFlight.getFlightCode()+". Excess fee of \u00a3"+this.passenger.getExcessFeeCharged()+" charged.");
                }
                else{
                    this.passenger.missFlight();
                    this.queue.enqueue(this.passenger);
                    logMessage("[Counter "+this.counterNumber+"] "+this.passengerFlight.getFlightCode()+" has already departed, "+this.passenger.getFullName()+" has missed their flight and has joined the end of the queue.");
                }
            }
            else{
                queue.moveToBackOfQueue();
                this.passenger = null;
                this.passengerFlight = null;
            }
            notifyObservers();
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
        for(Observer obs : registeredObservers) obs.update(null, this);
    }
}