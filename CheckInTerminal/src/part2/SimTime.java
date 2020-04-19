package part2;
/**
 * SimTime object class
 * Holds the current simulation speed, as well as providing methods to alter or retrive this speed.
 * @author Sean Katagiri
 * @version %I%, %G%
 */
public class SimTime {
    private long simSpeed;
    private boolean pause = false;
    
    /**
     * Constructor.
     * Sets {@code simSpeed} to 1000 ms by default.
     */
    public SimTime(){
        this.simSpeed = 1000;
    }

    /**
     * Setter method for {@code simSpeed}.
     * It takes an integer value and divides 1000 by it before setting it as the new {@code simSpeed}.
     * Result is a simulation sped up by "newSpeedMultiplier".
     * @param newSpeedMultiplier the new multiplier for simulation speed
     */
    public void set(int newSpeedMultiplier){
        this.simSpeed = 1000 / newSpeedMultiplier;
    }

    /**
     * Getter for {@code simSpeed}
     * @return the current {@code simSpeed}
     */
    public long get(){
        return this.simSpeed;
    }

    /**
     * Getter for {@code pause}
     * This variable indicates whether the simulation is paused or not.
     * @return {@code pause} boolean.
     */
    public boolean getPause(){
        return this.pause;
    }

    /**
     * Setter for {@code pause}.
     * Sets boolean to true.
     */
    public synchronized void pauseSim(){
        this.pause = true;

    }

    /**
     * Setter for {@code pause}.
     * Sets boolean to false
     */
    public synchronized void resumeSim(){
        this.pause = false;
    }
}