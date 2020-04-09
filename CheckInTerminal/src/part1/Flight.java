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
	private String flightCode;
	private String destination;
	private String carrier;
	private int maxPassengers;
	private float maxBaggageWeight;
	private float maxBaggageLength;
	private float maxBaggageHeight;
	private float maxBaggageWidth;
	private float totalBaggageWeight;
	private float totalBaggageVolume;
	private float totalExcessFees;
	private int numberOfPassengers;
	private float excessFeeCharge;

	/**
	 * Instantiates a new Flight object with given parameters as its flight details.
	 *
	 * @param flightCode    the flight code
	 * @param destination   the flight destination
	 * @param carrier       the carrier airline
	 * @param maxPassengers the maximum number of passengers on board
	 * @param maxBagLength  the maximum available baggage length per person
	 * @param maxBagHeight  the maximum available baggage height per person
	 * @param maxBagWidth   the maximum available baggage width per person
	 * @param maxBagWeight  the maximum available baggage weight per person
	 * @param excessFee     the levy charged for baggage that exceed the baggage limitations
	 */
	public Flight(String flightCode, String destination, String carrier, int maxPassengers, float maxBagLength, float maxBagHeight, float maxBagWidth, float maxBagWeight, float excessFee){
		this.flightCode = flightCode;
		this.destination = destination;
		this.carrier = carrier;
		this.maxPassengers = maxPassengers;
		this.maxBaggageWeight = maxBagWeight;
		this.maxBaggageLength = maxBagLength;
		this.maxBaggageHeight = maxBagHeight;
		this.maxBaggageWidth = maxBagWidth;
		this.totalBaggageWeight = 0;
		this.totalBaggageVolume = 0;
		this.numberOfPassengers = 0;
		this.excessFeeCharge = excessFee;
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
     * @return the flight carrier.
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
     * Increments the number of checked in by 1.
     */
	public void addPassenger() { this.numberOfPassengers +=1; }
	
    /**
     * @return the number of checked in passengers.
     */
	public int getNumOfPassengers() { return this.numberOfPassengers; }

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
		this.totalBaggageWeight += weight;
		this.totalBaggageVolume += (length * height * width);
		if(weight > this.maxBaggageWeight || (length * height * width) > (this.maxBaggageLength * this.maxBaggageHeight * this.maxBaggageWidth)) {
			this.totalExcessFees += this.excessFeeCharge; 
			throw new OverBaggageLimitException("Baggage limit exceeded");
		}
	}

    /**
     * @return the levy charge for baggage that exceeds restrictions.
     */
	public float getBaggageLevy() {
		return this.excessFeeCharge;
	}
	
    /**
     * @return the total excess fees collected.
     */
	public float getCollectedFees() {
		return this.totalExcessFees;
	}

	public float getPassengerCapacity() {
		return this.numberOfPassengers/this.maxPassengers*100;
	}
}
