package bspq21_e4.ParkingManagement.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import bspq21_e4.ParkingManagement.server.data.CalculateFee;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class Main {

	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws ParseException {
		
		Parking p = new Parking(01, "Plaza Euskadi", 10, 10, 0, 1);
		System.out.println(p.toString());
		Slot s0 = new Slot(0, 0, SlotAvailability.GREEN, p);
		System.out.println(s0.toString());
		Slot s1 = new Slot(1, 0, SlotAvailability.GREEN, p);
		Slot s2 = new Slot(2, 0, SlotAvailability.GREEN, p);
		Slot s3 = new Slot(3, 0, SlotAvailability.GREEN, p);
		Slot s4 = new Slot(4, 0, SlotAvailability.GREEN, p);
		Slot s5 = new Slot(5, 0, SlotAvailability.GREEN, p);
		Slot s6 = new Slot(6, 0, SlotAvailability.GREEN, p);
		Slot s7 = new Slot(7, 0, SlotAvailability.GREEN, p);
		Slot s8 = new Slot(8, 0, SlotAvailability.GREEN, p);
		Slot s9 = new Slot(9, 0, SlotAvailability.GREEN, p);
		PremiumUser pu0 = new PremiumUser("gabriel.gdebaquedano@opendeusto.es", "BI-7404-CH", 50, s0, "Paypal");
		System.out.println(pu0.toString());
		s0.setSl(SlotAvailability.YELLOW);
		System.out.println(s0.toString());
		p.setAvailableSlots(p.getAvailableSlots()-1);
		p.setOccupiedSlots(p.getOccupiedSlots()+1);
		System.out.println(p.toString());
		GuestUser gu0 = new GuestUser("6884 DTX", sdfResult.parse("09:00"), s3, "Paypal");
		System.out.println(gu0.toString());
		p.setAvailableSlots(p.getAvailableSlots()-1);
		p.setOccupiedSlots(p.getOccupiedSlots()+1);
		System.out.println(p.toString());
		p.setAvailableSlots(p.getAvailableSlots()+1);
		p.setOccupiedSlots(p.getOccupiedSlots()-1);
		System.out.println(CalculateFee.calculateFee(CalculateFee.getDifferenceBetwenDates(gu0.getEntranceDate(), sdfResult.parse("10:00")))+"€");
		System.out.println(p.toString());
	}

}
