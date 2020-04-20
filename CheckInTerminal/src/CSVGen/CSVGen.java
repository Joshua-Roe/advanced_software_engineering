package CSVGen;
import java.io.*; 
import java.util.Random;
import java.util.zip.CRC32;
//import java.io.File;

/*
This is the CSV generator
It reads the sample CSV file which comes eith 500 name smaples and used same pre-set flight nuumber to randomly generate a new CSV file.
Each line of the new CSV file contains:
	1. unique booking reference
	2. first name of passenger
	3. last name of passenger
	4. flight number of thier upcoming travel
	5. status of check-in
*/

public class CSVGen { 
	public static void main(String[] args)throws Exception{ 
		
		String[] flightNO = {"BA2503", "EZY484", "BE294", "EZY808", //here are the pre-set flight numbers and they match the flight number of the flight details CSV
							 "FR5160", "LM335", "FR6652", "LM397",
							 "EJU3386", "BE849", "EZS6917", "EZY6979",
							 "EJU2684", "LM347", "LM365", "EZY012",
							 "EZY236", "BE2107", "EZY426", "FR1049",
							 "LM385", "FR5834", "BA8857", "EZY488",
							 "FR815", "EZY2686", "BE851", "EK024"}; 
		Random rand = new Random(); //a ransom number for assigning passenger a flight number randomly
		int randomNum = 0; 
		File file = new File("../data/uk-500.csv"); //the path of the sample csv file
		File export = new File("../data/bookingDetails.csv"); ////the path of the output (the new) csv file
		
		BufferedReader br = new BufferedReader(new FileReader(file)); //prepare to read 
		BufferedWriter bw = new BufferedWriter(new FileWriter(export)); //prepare to write
		
		  
		String st; 
		br.readLine(); //skip the first line which is the instruction
		while ((st = br.readLine()) != null) { //read the sample file line by line until the end
			String[] temp = st.split(",", 0); //split the sample csv file to seperate components 
			randomNum = rand.nextInt(((flightNO.length-1) - 0) + 1) + 0; //random number is going to be bewteen 0 (min number of the array of string) an 27 (max number of the array of string)
			temp[0] = temp[0].replace("\"", ""); //get rid of the symbol found from the name 
			temp[1] = temp[1].replace("\"", ""); //get rid of the symbol found from the name 
			String key = temp[0]+temp[1]+flightNO[randomNum]; //conbine first name, last name and flight number to be a string and use it to generate a unique reference number 
			CRC32 crc = new CRC32(); //use crc32 hash method the get the hash key of the aformationed string 
	        crc.update(key.getBytes()); //put the string into the function for getting the hash key
	        long tempKey = crc.getValue(); //store the result 
	        bw.write(Long.toHexString(tempKey) + "," + temp[0] + "," + temp[1] + "," + flightNO[randomNum] + "," + "N"); //print all components onto the new csv file
	        bw.newLine(); //and go to new new when it finishes one passenger
		}
		//close both files when finish
		bw.close();
		br.close();
		} 
} 