package part1;

import java.awt.Dimension;
import java.io.*;
import java.util.Random;

import javax.swing.*;

public class CSVReader { 
	private AllFlights flights;
	private AllBookings bookings;
	public CSVReader() {
		try {
		    String sep = File.separator;
		    String bookingCSVpath = new File("data"+sep+"bookingDetails.csv").getAbsolutePath();
		    String flightCSVpath = new File("data"+sep+"flightDetails.csv").getAbsolutePath();
		   
			File flightCSV = new File(flightCSVpath);
			File bookingCSV = new File(bookingCSVpath);
			BufferedReader bookingBR = new BufferedReader(new FileReader(bookingCSV)); 
			BufferedReader flightBR = new BufferedReader(new FileReader(flightCSV));
			
			bookings = new AllBookings();
			flights = new AllFlights();
			
			String st; 
			while ((st = bookingBR.readLine()) != null) {
				String[] temp = st.split(",", 0);

				Random rand1 = new Random();
				Random rand2 = new Random();
				Random rand3 = new Random();
				Random rand4 = new Random();
				float min = 1;
				float maxWeight = 60;
				float maxLH = 150;
				float maxW = 100;
				float ranWeight = rand1.nextFloat() * (maxWeight - min) + min;
				ranWeight = Math.round(ranWeight*10.0f)/10.0f;
				float ranL = rand2.nextFloat() * (maxLH - min) + min;
				ranL = Math.round(ranL*10.0f)/10.0f;
				float ranH = rand3.nextFloat() * (maxLH - min) + min;
				ranH = Math.round(ranH*10.0f)/10.0f;
				float ranW = rand4.nextFloat() * (maxW - min) + min;
				ranW = Math.round(ranW*10.0f)/10.0f;
				//Booking example = new Booking(bookingCode, firstName, lastName, flightCode, baggageWeight, baggageLength, baggageHeight, baggageWidth)
				Booking booking = new Booking(temp[0], temp[1], temp[2], temp[3], ranWeight, ranL, ranH, ranW);
				bookings.addBooking(booking);
			}
			
			while ((st = flightBR.readLine()) != null) {
				String[] temp = st.split(",", 0);
				String[] tempTime = temp[11].split(":");
				int h = Integer.parseInt(tempTime[0]);
        		int m = Integer.parseInt(tempTime[1]);
				int time = h*60 + m;
				//Flight example = new Flight(flightCode, destination, carrier, maxPassengers, allowedBaggageWeight, allowedBaggageLength, allowedBaggageHeight, allowedBaggageWidth, excessFeeCharge, time)
				Flight flight = new Flight(temp[0], temp[3], temp[1], Integer.parseInt(temp[5]), Float.parseFloat(temp[6]), Float.parseFloat(temp[7]), Float.parseFloat(temp[8]), Float.parseFloat(temp[9]), Float.parseFloat(temp[10]), time);
				flights.addFlight(flight);
			}
			
			bookingBR.close();
			flightBR.close();
		}
		catch(FileNotFoundException e) {
			generatePopUp();
		}
		catch(IOException e) {
			System.out.println("IO exception experienced.");
		}
	} 
	
	public AllBookings getBookings() {
		return bookings;
	}
	
	public AllFlights getFlights() {
		return flights;
	}

	public void generatePopUp(){
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Data folder not found, ensure it is in its original directory.", 
				"Alert", JOptionPane.WARNING_MESSAGE);
		System.exit(404);
	}
} 