package part2;

public class Timer {
    private long time;
    private SimTime t;
    public Timer(SimTime t){
        this.t = t;
    }
    public void incr(){time += t.get();}
    public long getTime(){
        return time;
    }
    //TODO pass Timer to each object that uses time as an argument in the constructor
}