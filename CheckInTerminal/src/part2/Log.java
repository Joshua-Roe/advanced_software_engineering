package part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Enum-based singleton

public enum Log {

    INSTANCE;

    private  StringBuilder logfile;

    private Log(){
        logfile = new StringBuilder();
    }

    public  void  log (String logstring ) {
        logfile.append(logstring);
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