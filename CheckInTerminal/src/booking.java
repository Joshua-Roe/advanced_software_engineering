public class Booking
{
	private String bookingRef;
	private String firstName;
	private String lastName;
	private String flightCode;
	private boolean checkedIn;
	private float baggageSize;
	private float baggageWeight;
	
	public Booking(String brf, String fName, String lName, String fCode) 
	{
		this.bookingRef = brf;
		this.firstName = fName;
		this.lastName = lName;
		this.flightCode = fCode;
		this.checkedIn = false;
	
	}
	
	public String getReferenceCode() {
		return bookingRef;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getflightCode() {
		return flightCode;
	}
	
	public boolean getCheckinStatus() {
		return checkedIn;
	}
	
	public void setCheckinStatus() {
		checkedIn = true;
	}
	
	public void setBaggageInfo(float bSize, float bWeight) {
		this.baggageSize = bSize;
		this.baggageWeight = bWeight;
	}
}