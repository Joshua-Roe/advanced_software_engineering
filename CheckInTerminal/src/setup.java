import java.io.*; 
import java.util.Random;
import java.util.HashMap;
import java.util.zip.CRC32;
//import java.io.File;

class Data{
	public String FirstName; 
    public String LastName;  
    public String flightNumber;
    //public String aircraft;
    public boolean checkedIn;
    //public float maxSize;
    //public float maxWeight;
    //public float currentSzie;
    //public float currentWeight;
    
}

public class booking { 
	public static void main(String[] args)throws Exception{ 

		File bookingCSV = new File("../../data/bookingDetails.csv"); 
		File flightCSV = new File("../../data/flightDetails.csv");

		//export.createNewFile();
		
		BufferedReader bookingBR = new BufferedReader(new FileReader(bookingCSV)); 
		BufferedReader flightBR = new BufferedReader(new FileReader(flightCSV));
		
		HashMap<String, Data> booking = new HashMap<String, Data>();
		  
		String st; 
		while ((st = bookingBR.readLine()) != null) {
			String[] temp = st.split(",", 0);
			//randomNum = rand.nextInt(((flightNO.length-1) - 0) + 1) + 0;
			//temp[0] = temp[0].replace("\"", "");
			//temp[1] = temp[1].replace("\"", "");
			//String key = temp[0]+temp[1]+flightNO[randomNum];
			//System.out.println(temp[0] + temp[1] + flightNO[randomNum]);
			Data detailData = new Data();
			detailData.FirstName = temp[1];
			detailData.LastName = temp[2];
			detailData.flightNumber = temp[3];
			if (temp[4] == "N")
				detailData.checkedIn = false;
			else if (temp[4] == "Y")
				detailData.checkedIn = true;
			
			booking.put(temp[0], detailData);
			//System.out.println("input:"+detailData);

			//CRC32 crc = new CRC32();
	        //crc.update(key.getBytes());
	        //long tempKey = crc.getValue();
	        //bw.write(Long.toHexString(tempKey) + "," + temp[0] + "," + temp[1] + "," + flightNO[randomNum] + "," + "N");
	        //bw.newLine();
	        
	        //System.out.println("CRC32:"+Long.toHexString(tempKey));
			
		}
		bookingBR.close();
		flightBR.close();
		System.out.println("input:"+booking);
		
		
		//System.out.println(bookingREF);
			//System.out.println(st); 
		} 
} 