package part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Loginfile {
    private  StringBuilder logfile;
    private Timer timer;

    public Loginfile(Timer timer){
        logfile = new StringBuilder();
        this.timer = timer;
        
    }

    public  void  log (String logstring ) {
        logfile.append(this.timer.getTimeString() + ": " + logstring);
        logfile.append(System.lineSeparator());
    }

    public  void savefile() {
        try {
            FileWriter logonscore =new FileWriter(new File("out.txt"),false);
            logonscore.write(logfile.toString());
            logonscore.close();
        }
        catch(IOException e) {
        }
    }
}