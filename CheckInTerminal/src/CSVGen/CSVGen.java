package CSVGen;
import java.io.*; 
import java.util.Random;
import java.util.zip.CRC32;
//import java.io.File;


public class CSVGen { 
	public static void main(String[] args)throws Exception{ 
		
		String[] flightNO = {"BA2503", "EZY484", "BE294", "EZY808",
							 "FR5160", "LM335", "FR6652", "LM397",
							 "EJU3386", "BE849", "EZS6917", "EZY6979",
							 "EJU2684", "LM347", "LM365", "EZY012",
							 "EZY236", "BE2107", "EZY426", "FR1049",
							 "LM385", "FR5834", "BA8857", "EZY488",
							 "FR815", "EZY2686", "BE851", "EK024"}; 
		Random rand = new Random();
		int randomNum = 0;
		File file = new File("../data/uk-500.csv"); 
		File export = new File("../data/bookingDetails.csv");
		//export.createNewFile();
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(export));
		
		//HashMap<String, String> bookingREF = new HashMap<String, String>();
		  
		String st; 
		br.readLine(); //skip the first line which is the instruction
		while ((st = br.readLine()) != null) {
			String[] temp = st.split(",", 0);
			randomNum = rand.nextInt(((flightNO.length-1) - 0) + 1) + 0;
			temp[0] = temp[0].replace("\"", "");
			temp[1] = temp[1].replace("\"", "");
			String key = temp[0]+temp[1]+flightNO[randomNum];
			//System.out.println(temp[0] + temp[1] + flightNO[randomNum]);
			//bookingREF.put(temp[1], flightNO[randomNum]);
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