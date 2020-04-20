package part2;

import java.util.Observer;
/**
 * The Subject interface 
 * This is a replacement for the Java Observable class, and is used by threaded classes.
 * @author Sean Katagiri
 * @version %I%, %G%
 */
@SuppressWarnings("deprecation")
public interface Subject {
    public void registerObserver(Observer obs);
    public void removeObserver(Observer obs);
    public void notifyObservers();
}