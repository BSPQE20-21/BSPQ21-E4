package bspq21_e4.ParkingManagement.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class JUnit {
	
	private Parking P1;
	private PremiumUser PU1;
	private GuestUser GU1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(JUnit.class);
		}
	
	@Before
	public void setUp() throws ParseException {
		
		P1 = new Parking(1, "Parking Getxo", 200, 150, 50, 2);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, new Slot(165, 2, SlotAvailability.GREEN, P1), "PayPal");
		GU1 = new GuestUser("6735 HGL", sdfResult.parse("9:00"), new Slot(44, 1, SlotAvailability.GREEN, P1), "Visa");
		
	}
	
	@Test
	public void TestFeeCalculator() throws ParseException {
		double expected = 60 * 0.04; // (Minutes from 9:00 to 10:00) * (Standard fee = 0.04) 
		int minutes = CalculateFee.getDifferenceBetwenDates(GU1.getEntranceDate(), sdfResult.parse("10:00"));
		assertEquals(expected, CalculateFee.calculateFee(minutes), 0);
	}
	
	@Test
	public void Test() {
		assertTrue(true);
		
	}

}
