
package part2;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 * Timer threaded object.
 * It is also responsible for notifying other threads at a set interval to perform their tasks.
 * The class implements the {@code Subject} interface.
 *
 * @see part2.Subject
 * @author Sean Katagiri
 * @version %I%, %G%
 */
@SuppressWarnings("deprecation")
class Timer extends Thread implements Subject {
    private int time = 0;
    private boolean setting = false;
    private SimTime simTime;
    private List<Observer> registeredObservers = new LinkedList<Observer>();
    
    /**
    * Constructor.
    * 
    * @param simTime (required) {@code SimTime} object holding simulation speed.
    * @see part2.SimTime 
    */
    public Timer(SimTime simTime) {
        this.simTime = simTime;
    }

    /** 
     * Run method for the thread
     * Increments time by 1 and notifies its observers.
     * Thread will sleep for a duration determined by the simulation peed stored in the class <tt>SimTime</tt>.
     * It checks if {@code simTime} has its {@code pause} boolean set to true, if it is it remains in a infinite loop until that boolean changes.
     * 
     * @see java.lang.Thread#run()
     * @see part2.SimTime#getPause()
     */
    public void run() {
        while (true) {
            setTime(time + 1);
            notifyObservers();
            try {
                sleep(simTime.get());
                while (simTime.getPause()) {
                    // Works fine without sleep, required for RedHat JDK
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** 
     * Getter method for current time as an int.
     * It checks the boolean {@code setting}, and waits until setting of time is complete before returning. 
     * @return the current time
     */

    public synchronized int getTime() {
        while (setting) {
            try {
                wait(); // This will only be run on the off-chance that setTime is being run at the same
                        // time.
            } catch (InterruptedException e) {
            }
        }

        return this.time;
    }

    /**
     * Getter method for current time as a String.
     * It checks the boolean {@code setting}, and waits until setting of time is complete before creating a String
     * for the current time based on the integer {@code time} and returns the new String.
     * @return the current time as a String 
     */
    public synchronized String getTimeString() {
        while (setting) {
            try {
                wait(); // This will only be run on the off-chance that setTime is being run at the same
                        // time.
            } catch (InterruptedException e) {
            }
        }

        int min = this.time % 60;
        int hr = this.time / 60 % 24;
        String minutes = (min < 10) ? "0" + Integer.toString(min) : Integer.toString(min);
        String hours = (hr < 10) ? "0" + Integer.toString(hr) : Integer.toString(hr);
        String timeString = "[" + hours + ":" + minutes + "]";
        return timeString;
    }

    /**
     * Setter method for {@code time}.
     * Sets the {@code setting} as true while setting takes place, before setting it back to false after completion.
     * Also notifies all threads waiting on {@code Timer}.
     * @param t the new time to set {@code time} as.
     * @see java.lang.Object#notifyAll()
     */
    public synchronized void setTime(int t) {
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
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
        for (Observer obs : registeredObservers)
            obs.update(null, this);
    }
}