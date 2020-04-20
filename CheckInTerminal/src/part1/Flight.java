package part1;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import part2.*;

/**
 * The type Flight.
 *  <tt>Flight</tt> is an object used to store details for each flight.
 *  Calculation of baggage limits being exceeded is done in this class.
 *
 * @author Leo Kong
 * @author Marek Kujawa
 * @version %I%, %G%
 */

@SuppressWarnings({"serial","deprecation"})
public class Flight implements Subject {
    private String flightCode;              /* flight code (reference/number) */
    private String destination;             /* destination airport code */
    private String carrier;                 /* carrier airline code */
    private int maxPassengers;              /* max passengers number allowed in the aircraft */
    private float allowedBaggageWeight;     /* personal baggage allowance in terms of weight */
    private float allowedBaggageLength;     /* personal baggage allowance in terms of length */
    private float allowedBaggageHeight;     /* personal baggage allowance in terms of height */
    private float allowedBaggageWidth;      /* personal baggage allowance in terms of width */
    private float allowedBaggageVolume;     /* personal baggage allowance in terms of volume */
    private float maxBaggageWeightCapacity; /* max capacity (weight) of the baggage compartment for the whole aircraft */
    private float maxBaggageVolumeCapacity; /* max capacity (volume) of the baggage compartment for the whole aircraft */
    private float totalBaggageWeight;       /* current total baggage weight */
    private float totalBaggageVolume;       /* current total baggage volume */
    private float totalExcessFees;          /* current total sum of baggage excess fees (in USD) */
    private int numberOfPassengers;         /* current total number of checked-in passengers */
    private float excessFeeCharge;          /* excess baggage fee (in USD) */
    private float departureTime;            /* departure time of the flight */
    private boolean gateOpen;
    private List<Observer> registeredObservers = new LinkedList<Observer>();

    /**
     * Instantiates a new Flight object with given parameters as its Flight details.
     *
     * @param flightCode           the flight code
     * @param destination          the destination airport
     * @param carrier              the carrier airline
     * @param maxPassengers        the max passengers allowed in the aircraft
     * @param allowedBaggageWeight the allowed baggage weight per person
     * @param allowedBaggageLength the allowed baggage length per person
     * @param allowedBaggageHeight the allowed baggage height per person
     * @param allowedBaggageWidth  the allowed baggage width per person
     * @param excessFeeCharge      the excess fee charge
     * @param time                 the time of departure
      */
     public Flight(String flightCode, String destination, String carrier, int maxPassengers, float allowedBaggageWeight, float allowedBaggageLength, float allowedBaggageHeight, float allowedBaggageWidth, float excessFeeCharge, int time) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.carrier = carrier;
        this.maxPassengers = maxPassengers;
        this.allowedBaggageWeight = allowedBaggageWeight;
        this.allowedBaggageLength = allowedBaggageLength;
        this.allowedBaggageHeight = allowedBaggageHeight;
        this.allowedBaggageWidth = allowedBaggageWidth;
        this.allowedBaggageVolume = this.allowedBaggageLength * this.allowedBaggageHeight * this.allowedBaggageWidth;
        this.excessFeeCharge = excessFeeCharge;
        this.maxBaggageWeightCapacity = this.allowedBaggageWeight * this.maxPassengers;
        this.maxBaggageVolumeCapacity = this.allowedBaggageVolume * this.maxPassengers;
        this.totalBaggageWeight = 0;
        this.totalBaggageVolume = 0;
        this.totalExcessFees = 0;
        this.numberOfPassengers = 0;
        this.departureTime = time;
        this.gateOpen = true;
    }

    /**
     * @return the flight code.
     */
    public String getFlightCode() { return this.flightCode; }

    /**
     * @return the flight destination.
     */
    public String getDestination() { return this.destination; }

    /**
     * @return the flight carrier code.
     */
    public String getCarrier() { return this.carrier; }
    
    /**
     * @return the maximum number of passengers.
     */
    public int getMaxPassengers() { return this.maxPassengers; }
    
    /**
     * @return the total baggage weight on the flight.
     */
    public float getTotalBaggageWeight() { return this.totalBaggageWeight; }
    
    /**
     * @return the total baggage volume on the flight.
     */
    public float getTotalBaggageVolume() { return this.totalBaggageVolume; }

    /**
     * @return the allowed baggage weight per person.
     */
    public float getAllowedBaggageWeight() { return allowedBaggageWeight; }

    /**
     * @return the allowed baggage length per person.
     */
    public float getAllowedBaggageLength() { return allowedBaggageLength; }

    /**
     * @return the allowed baggage height per person.
     */
    public float getAllowedBaggageHeight() { return allowedBaggageHeight; }

    /**
     * @return the allowed baggage width per person.
     */
    public float getAllowedBaggageWidth() { return allowedBaggageWidth; }

    /**
     * @return the allowed baggage volume per person.
     */
    public float getAllowedBaggageVolume() { return allowedBaggageVolume; }

    /**
     * @return the max weight capacity of the baggage compartment for the whole aircraft
     */
    public float getMaxBaggageWeightCapacity() { return maxBaggageWeightCapacity; }

    /**
     * @return the max volume capacity of the baggage compartment for the whole aircraft
     */
    public float getMaxBaggageVolumeCapacity() { return maxBaggageVolumeCapacity; }

    /**
     * @return the total excess fees collected
     */
    public float getTotalExcessFees() { return totalExcessFees; }

    /**
     * @return the number of checked in passengers
     */
    public int getNumberOfPassengers() { return numberOfPassengers; }

    /**
     * @return the levy charge for baggage that exceeds restrictions.
     */
    public float getExcessFeeCharge() { return excessFeeCharge; }

    /**
     * @return the percentage (100 = 100%) of the aircraft baggage compartment being filled. It checks both weight and volume and return the bigger one.
     */
    public float getBaggagePercent() {
        return Math.round(10.0f*Math.max(100f*this.totalBaggageWeight/this.maxBaggageWeightCapacity, 100*this.totalBaggageVolume/this.maxBaggageVolumeCapacity))/10.0f;
    }

    /**
     * @return the percentage (100 = 100%) of the aircraft passenger capacity being filled.
     */
    public float getPassengerCapacity() { return (float)this.numberOfPassengers/this.maxPassengers*100; }

    /**
     * Increments the number of checked in by 1.
     */
    public void addPassenger() {
		this.numberOfPassengers +=1;
		this.notifyObservers();
	}

    /**
     * Custom exception for baggage limit being exceeded
     */
    public class OverBaggageLimitException extends RuntimeException {
        public OverBaggageLimitException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Checks baggage against baggage restrictions, and applies excess fee if limits are
     * exceeded.
     *
     * @param weight the weight of the baggage being checked in.
     * @param length the length of the baggage being checked in.
     * @param height the height of the baggage being checked in.
     * @param width  the width of the baggage being checked in.
     * @throws OverBaggageLimitException if baggage weight or volume exceeds the limitation.
     */
    public void checkBaggage(float weight, float length, float height, float width) {
        float baggageVolume = length * height * width;
        this.totalBaggageWeight += weight;
        this.totalBaggageVolume += baggageVolume;
        /* if the checked weight or volume is greater than the maximum allowed ... */
        if(weight > this.allowedBaggageWeight || baggageVolume > this.allowedBaggageVolume) {
            /* ... do the following */
            this.totalExcessFees += this.excessFeeCharge;
            throw new OverBaggageLimitException("Baggage limit exceeded");
        }
	}

    /**
     * Check baggage volume against baggage volume restrictions, and applies excess fee if limits are
     * exceeded.
     *
     * @deprecated Legacy method for original GUI (part1.GUI.java)
     * @param weight        the weight
     * @param baggageVolume the baggage volume
     */
    public void checkBaggageByVolume(float weight, float baggageVolume) {
        this.totalBaggageWeight += weight;
        this.totalBaggageVolume += baggageVolume;
        if(weight > this.allowedBaggageWeight || baggageVolume > this.allowedBaggageVolume) {
            this.totalExcessFees += this.excessFeeCharge;
            throw new OverBaggageLimitException("Baggage limit exceeded");
        }
    }

    /**
     * Check if the gate is open and update gateOpen field.
     *
     * @param currentTime       the current time
     * @param currentTimeString the current time string
     * @return the boolean
     */
    public boolean checkGateOpen(int currentTime, String currentTimeString) {
        /* if the airplane has already taken off and gate is open ... */
        if (currentTime >= this.departureTime && this.gateOpen){
            /* ... do the following */
            this.gateOpen = false;
            notifyObservers();
            logMessage("Flight "+this.flightCode+" has taken off.", currentTimeString);
        }
        return this.gateOpen;
    }

    /**
     * Check if the gate is open.
     *
     * @return the boolean
     */
    public boolean getGateOpen(){
        return this.gateOpen;
    }

    /**
     * helper method for logging.
     *
     * @param message           the log message
     * @param currentTimeString the current time string
     */
    private void logMessage(String message, String currentTimeString){
        Log l = Log.INSTANCE;
        l.log(currentTimeString+" "+message);
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
        for(Observer obs : registeredObservers) obs.update(null, this);
    }
}
