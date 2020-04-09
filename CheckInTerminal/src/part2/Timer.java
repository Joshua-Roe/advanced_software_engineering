package part2;

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

class Timer extends Thread{
    private int time = 0;
    private boolean setting = false;
    private SimTime simTime;

    public Timer(SimTime simTime){
        this.simTime = simTime;
    }

    public void run(){
        while(true){
            setTime(time + 1);
            try {
                sleep(simTime.get());
                while(simTime.getPause()){
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
        }
    }
    public synchronized int getTime(){
        while(setting){
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }

        return time;
    }
    public synchronized void setTime(int t){
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }

}