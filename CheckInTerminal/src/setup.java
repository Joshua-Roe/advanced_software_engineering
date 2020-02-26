import java.io.*; 
import java.util.Random;
import java.util.HashMap;
import java.util.zip.CRC32;
//import java.io.File;

class bookingData{
	public String FirstName; 
    public String LastName;  
    public String flightNumber;
    public boolean checkedIn;    
}

class flightData{
	public String airline;
	public String depart;
	public String destination;
	public String aircraft;
	public int capacity;
	public float maxSize;
	public float maxWeight;
}

public class setup { 
	public static void main(String[] args)throws Exception{ 

		File bookingCSV = new File("../../data/bookingDetails.csv"); 
		File flightCSV = new File("../../data/flightDetails.csv");
		
		BufferedReader bookingBR = new BufferedReader(new FileReader(bookingCSV)); 
		BufferedReader flightBR = new BufferedReader(new FileReader(flightCSV));
		
		HashMap<String, bookingData> bookingHash = new HashMap<String, bookingData>();
		HashMap<String, flightData> flightHash = new HashMap<String, flightData>();
		
		String st; 
		while ((st = bookingBR.readLine()) != null) {
			String[] temp = st.split(",", 0);

			bookingData bookingDetail = new bookingData();
			bookingDetail.FirstName = temp[1];
			bookingDetail.LastName = temp[2];
			bookingDetail.flightNumber = temp[3];
			if (temp[4] == "N")
				bookingDetail.checkedIn = false;
			else if (temp[4] == "Y")
				bookingDetail.checkedIn = true;
			
			bookingHash.put(temp[0], bookingDetail);
			
		}
		
		while ((st = flightBR.readLine()) != null) {
			String[] temp = st.split(",", 0);

			flightData flightDetail = new flightData();
			flightDetail.airline = temp[1];
			flightDetail.depart = temp[2];
			flightDetail.destination = temp[3];
			flightDetail.aircraft = temp[4];
			flightDetail.capacity = Integer.parseInt(temp[5]);
			flightDetail.maxSize = Float.parseFloat(temp[6]);
			flightDetail.maxWeight = Float.parseFloat(temp[7]);
			
			flightHash.put(temp[0], flightDetail);
		}
		
		bookingBR.close();
		flightBR.close();
		//System.out.println("input:"+ flightHash);
		
		//flightData x = flightHash.get("LM365");
		//System.out.println(x.destination);
		//System.out.println(bookingREF);
			//System.out.println(st); 
		} 
} 