package part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Loginfile {
    private  StringBuilder logfile;

    public Loginfile(){
        logfile = new StringBuilder();
        
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

    public  void  log (String logstring ) {
        logfile.append(logstring);
        logfile.append(System.lineSeparator());
    }
}