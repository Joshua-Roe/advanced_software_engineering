package part1;

/**
 * <tt>Flight</tt> is an object used to store details for each flight.
 * Calculation of baggage limits being exceeded is done in this class.
 */
@SuppressWarnings("serial")
public class Flight {
	private String flightCode;
	private String destination;
	private String carrier;
	private int maxPassengers;
	private float maxBaggageWeight;
	private float maxBaggageVolume;
	private float totalBaggageWeight;
	private float totalBaggageVolume;
	private float totalExcessFees;
	private int numberOfPassengers;
	private float excessFeeCharge;
	
	/**
     * Constructs a <tt>Flight</tt> object with given parameters as its flight details.
     * @param fc the flight code.
     * @param dest the flight destination.
     * @param carr the carrier airline.
     * @param maxP the maximum number of passengers on board.
     * @param maxBagW the maximum available baggage weight per person.
     * @param maxBagV the maximum available baggage volume per person.
     * @param excessFee the levy charged for baggage that exceed the baggage limitations. 
     */
	public Flight(String fc, String dest, String carr, int maxP, float maxBagW, float maxBagV, float excessFee){
		this.flightCode = fc;
		this.destination = dest;
		this.carrier = carr;
		this.maxPassengers = maxP;
		this.maxBaggageWeight = maxBagW;
		this.maxBaggageVolume = maxBagV;
		this.totalBaggageWeight = 0;
		this.totalBaggageVolume = 0;
		this.numberOfPassengers = 0;
		this.excessFeeCharge = excessFee;
	}
	
    /**
     * @return the flight code.
     */
	public String getFlightCode() {
		return this.flightCode;
	}

    /**
     * @return the flight destination.
     */
	public String getDestination() {
		return this.destination;
	}
	
    /**
     * @return the flight carrier.
     */
	public String getCarrier() {
		return this.carrier;
	}
	
    /**
     * @return the maximum number of passengers.
     */
	public int getMaxPassengers() {
		return this.maxPassengers;
	}
	
    /**
     * @return the total baggage weight on the flight.
     */
	public float getTotalBaggageWeight() {
		return this.totalBaggageWeight;
	}
	
    /**
     * @return the total baggage volume on the flight.
     */
	public float getTotalBaggageVolume() {
		return this.totalBaggageVolume;
	}
	
    /**
     * Increments the number of checked in by 1.
     */
	public void addPassenger() {
		this.numberOfPassengers +=1;
	}
	
    /**
     * @return the number of checked in passengers.
     */
	public int getNumOfPassengers() {
		return this.numberOfPassengers;
	}
	
    /**
     * Custom exception for baggage limit being exceeded
     */
	public class OverBaggageLimitException extends RuntimeException{
		public OverBaggageLimitException(String errorMessage) {
		super(errorMessage);
		}
	}
	
    /**
     * Checks baggage against baggage restrictions, and applies excess fee if limits are
     * exceeded.
     * 
     * @param weight the weight of the baggage being checked in.
     * @param volume the volume of the baggage being checked in.
     * @throws OverBaggageLimitException if baggage weight or volume exceeds the limitation.
     */
	public void checkBaggage(float weight, float volume) {
		this.totalBaggageWeight += weight;
		this.totalBaggageVolume += volume;
		if(weight > this.maxBaggageWeight || volume > this.maxBaggageVolume) {
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
}
