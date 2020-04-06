package part2;

public class SimTime {
    private long sim_speed;

    public SimTime(){
        this.sim_speed = 1000;
    }

    public void set(long new_sim_speed){
        this.sim_speed = new_sim_speed;
    }

    public long get(){
        return this.sim_speed;
    }
}