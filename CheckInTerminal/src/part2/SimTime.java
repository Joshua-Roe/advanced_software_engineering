package part2;

public class SimTime {
    private long sim_speed;
    private boolean pause = false;
    
    public SimTime(){
        this.sim_speed = 1000;
    }

    public void set(int new_sim_speed_multiplier){
        this.sim_speed = 1000 / new_sim_speed_multiplier;
    }

    public long get(){
        return this.sim_speed;
    }

    public boolean getPause(){
        return this.pause;
    }
    public synchronized void pauseSim(){
        this.pause = true;

    }

    public synchronized void resumeSim(){
        this.pause = false;
    }
}