package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

// public class Timer {
//     private long time;
//     private SimTime t;
//     public Timer(SimTime t){
//         this.t = t;
//     }
//     public synchronized void incr(){time += t.get();}
//     public long getTime(){
//         return time;
//     }
//     //TODO pass Timer as an argument in the constructor to each object that uses time 
// }

class Timer extends Thread implements Subject {
    private int time = 0;
    private boolean setting = false;
    private SimTime simTime;
    private List<Observer> registeredObservers = new LinkedList<Observer>();

    public Timer(SimTime simTime) {
        this.simTime = simTime;
    }

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
            // Switching the order of these 2 ^^^ statements and initializing time to 0 will
            // give an output that is more accurate to the time.
        }
    }

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

    public synchronized void setTime(int t) {
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
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