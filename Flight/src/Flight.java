import Flight.OverWeightLimitException;

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
	
	public Flight(String fc, String dest, String carr, int maxP, float maxBagW, float maxBagV){
		this.flightCode = fc;
		this.destination = dest;
		this.carrier = carr;
		this.maxPassengers = maxP;
		this.maxBaggageWeight = maxBagW;
		this.maxBaggageVolume = maxBagV;
		this.totalBaggageWeight = 0;
		this.totalBaggageVolume = 0;
		this.numberOfPassengers = 0;
	}
	
	public String getFlightCode() {
		return this.flightCode;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public String getCarrier() {
		return this.carrier;
	}
	
	public int getMaxPassengers() {
		return this.maxPassengers;
	}
	
	public float getMaxBaggageWeight() {
		return this.maxBaggageWeight;
	}
	
	public float getMaxBaggageVolume() {
		return this.maxBaggageVolume;
	}
	
	public float getTotalBaggageWeight() {
		return this.totalBaggageWeight;
	}
	
	public float getTotalBaggageVolume() {
		return this.totalBaggageVolume;
	}
	
	public int getNumOfPassengers() {
		return this.numberOfPassengers;
	}
	
	public class OverWeightLimitException extends RuntimeException{
		public OverWeightLimitException(String errorMessage) {
		super(errorMessage);
		}
	}
	
	public class OverVolumeLimitException extends RuntimeException{
		public OverVolumeLimitException(String errorMessage) {
		super(errorMessage);
		}
	}
	
	public void checkBaggage(float weight, float volume) {
		if(weight > this.maxBaggageWeight) {
			throw new OverWeightLimitException("Baggage limit exceeded");
		}
		
	}
}
