package part1;

/**
 * The type Booking object used to store details for each booking.
 *
 * @author Leo Kong
 * @author Marek Kujawa
 * @author Joshua Roe
 * @author Sean Katagiri
 * @author Randy Adjepong
 * @version %I%, %G%
 */
public class Booking{
    private String bookingCode;         /* booking code (reference/number) */
    private String firstName;           /* passenger first name */
    private String lastName;            /* passenger last name */
    private String flightCode;          /* flight code (reference/number) */
    private float baggageWeight;        /* passenger baggage weight */
    private float baggageLength;        /* passenger baggage length */
    private float baggageHeight;        /* passenger baggage height */
    private float baggageWidth;         /* passenger baggage width */
    private boolean checkInStatus;      /* passenger baggage length */
    private float excessFeeCharged = 0; /* excess fee passenger's been charged */
    private boolean missedFlight;       /* has the passenger missed the flight? */

    /**
     * Instantiates a new Booking object with given parameters as its Booking details.
     *
     * @param bookingCode   the booking code
     * @param firstName     the first name
     * @param lastName      the last name
     * @param flightCode    the flight code
     * @param baggageWeight the baggage weight
     * @param baggageLength the baggage length
     * @param baggageHeight the baggage height
     * @param baggageWidth  the baggage width
     */
    public Booking(String bookingCode, String firstName, String lastName, String flightCode, float baggageWeight, float baggageLength, float baggageHeight, float baggageWidth) {
        this.bookingCode = bookingCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightCode = flightCode;
        this.baggageWeight = baggageWeight;
        this.baggageLength = baggageLength;
        this.baggageHeight = baggageHeight;
        this.baggageWidth = baggageWidth;
        this.checkInStatus = false;
        this.missedFlight = false;
    }

    /**
     * @return the booking code (reference/number).
     */
    public String getReference() { return bookingCode; }
    
    /**
     * @return the passenger first name.
     */
    public String getFirstName() { return firstName; }
    
    /**
     * @return the passenger last name.
     */
    public String getLastName() { return lastName; }

    /**
     * @return the passenger full name.
     */
    public String getFullName() { return firstName +" "+ lastName; }

    /**
     * @return the flight code (reference/number).
     */
    public String getFlightCode() { return flightCode; }
    
    /**
     * @return the check-in status.
     */
    public boolean getCheckInStatus() { return checkInStatus; }

    /**
     * @return the passenger baggage weight
     */
    public float getBaggageWeight() { return baggageWeight; }

    /**
     * @return the passenger baggage length
     */
    public float getBaggageLength() { return baggageLength; }

    /**
     * @return the passenger baggage height
     */
    public float getBaggageHeight() { return baggageHeight; }

    /**
     * @return the passenger baggage width
     */
    public float getBaggageWidth() { return baggageWidth; }

    /**
     * Sets check in status of booking
     *
     * @param checkInStatus the new check in status for the booking
     */
    public void setCheckInStatus(boolean checkInStatus) { this.checkInStatus = checkInStatus; }

    /**
     * Sets baggage info for booking.
     *
     * @deprecated Legacy method for original GUI (part1.GUI.java)
     * @param w the weight of the baggage
     * @param v the volume of the baggage
     */
    public void setBaggageInfo(float w, float v) {
        //baggage_weight = w;
        //baggage_volume = v;
    }

    /**
     * Set the excess fee hat the passenger is being charged.
     *
     * @param fee the fee
     */
    public void setExcessFeeCharged(Float fee){
        this.excessFeeCharged = fee;
    }

    /**
     * Get the excess fee that the passenger has been charged.
     *
     * @return the float
     */
    public Float getExcessFeeCharged(){
        return this.excessFeeCharged;
    }

    /**
     * Get the missed flight (true = missed). Check if the passenger in the booking missed the flight.
     *
     * @return the boolean
     */
    public boolean getMissedFlight(){
        return this.missedFlight;
    }

    /**
     * Set Miss flight (to true). This indicates the passenger in the booking missed the flight.
     */
    public void missFlight(){
        this.missedFlight = true;
    }
}

