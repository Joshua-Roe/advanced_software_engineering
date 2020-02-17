import java.io.*; 
import java.util.Random;
import java.util.HashMap;
import java.util.zip.CRC32;
//import java.io.File;


public class booking { 
	public static void main(String[] args)throws Exception{ 

		File bookingCSV = new File("../data/uk-500.csv"); 
		File flightCSV = new File("../data/bookingDetails.csv");
		//export.createNewFile();
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(export));
		
		HashMap<String, String> bookingREF = new HashMap<String, String>();
		  
		String st; 
		br.readLine(); //skip the first line which is the instruction
		while ((st = br.readLine()) != null) {
			String[] temp = st.split(",", 0);
			randomNum = rand.nextInt(((flightNO.length-1) - 0) + 1) + 0;
			temp[0] = temp[0].replace("\"", "");
			temp[1] = temp[1].replace("\"", "");
			String key = temp[0]+temp[1]+flightNO[randomNum];
			//System.out.println(temp[0] + temp[1] + flightNO[randomNum]);
			bookingREF.put(temp[1], flightNO[randomNum]);
			CRC32 crc = new CRC32();
	        crc.update(key.getBytes());
	        System.out.println("input:"+key);
	        long tempKey = crc.getValue();
	        bw.write(Long.toHexString(tempKey) + "," + temp[0] + "," + temp[1] + "," + flightNO[randomNum] + "," + "N");
	        bw.newLine();
	        
	        System.out.println("CRC32:"+Long.toHexString(tempKey));
			
		}
		bw.close();
		br.close();
		//System.out.println(bookingREF);
			//System.out.println(st); 
		} 
} 