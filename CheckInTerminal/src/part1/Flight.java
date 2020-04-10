package part1;

/**
 * The type Flight.
 *  <tt>Flight</tt> is an object used to store details for each flight.
 *  Calculation of baggage limits being exceeded is done in this class.
 *
 * @author Leo Kong
 * @author Marek Kujawa
 * @version %I%, %G%
 */

@SuppressWarnings("serial")

public class Flight {
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
      */
     public Flight(String flightCode, String destination, String carrier, int maxPassengers, float allowedBaggageWeight, float allowedBaggageLength, float allowedBaggageHeight, float allowedBaggageWidth, float excessFeeCharge) {
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
     * @return the percent (1 = 100%) of the aircraft baggage compartment being filled. It checks both weight and volume and return the bigger one.
     */
    public float getBaggagePercent() {
        return Math.max(this.totalBaggageWeight/this.maxBaggageWeightCapacity, this.totalBaggageVolume/this.maxBaggageVolumeCapacity);
    }

    /**
     * @return the percentage of the aircraft passenger capacity being filled.
     */
    public float getPassengerCapacity() { return (float)this.numberOfPassengers/this.maxPassengers*100; }

    /**
     * Increments the number of checked in by 1.
     */
    public void addPassenger() { this.numberOfPassengers +=1; }

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
        if(weight > this.allowedBaggageWeight || baggageVolume > this.allowedBaggageVolume) {
            this.totalExcessFees += this.excessFeeCharge;
            throw new OverBaggageLimitException("Baggage limit exceeded");
        }
    }
}
