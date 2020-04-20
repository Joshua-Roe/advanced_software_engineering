package part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Enum-based singleton Log class
 * Provides methods for storing log messages and outputting stored messages to a log file
 * @author Randy Adjepong
 * @version %I%, %G%
 */
public enum Log {

    INSTANCE;

    private  StringBuilder logfile;

    private Log(){
        logfile = new StringBuilder();
    }
/**
*Stringbuilder is used in this case because of the length of characters to simplify the code
*/
    
    public  void  log (String logstring ) {
        logfile.append(logstring);
        logfile.append(System.lineSeparator());
    }
/**
*lineSepator is used to read each file on a different line
*/
    
    public  void savefile() {
        try {
            FileWriter logonscore =new FileWriter(new File("last_simulation_log.txt"),false);
            logonscore.write(logfile.toString());
            logonscore.close();
        }
        catch(IOException e) {
        }
    }
}
