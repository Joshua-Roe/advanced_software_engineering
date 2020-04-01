package part1;

import java.io.File;

public class pathTest {
    public static void main(String[] args){
        
        String sep = File.separator;
        String bookingCSVpath = new File("bookingDetails.csv").getAbsolutePath();
        System.out.println(bookingCSVpath);
    }
}