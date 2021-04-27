package bspq21_e4.ParkingManagement.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class JUnit {
	
	private Parking P1;
	private PremiumUser PU1;
	private GuestUser GU1;
	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	
	@Before
	public void setUp() throws ParseException {
		
		P1 = new Parking(1, "Parking Getxo", 200, 150, 50, 2);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, new Slot(165, 2, SlotAvailability.GREEN, P1), "PayPal");
		GU1 = new GuestUser("6735 HGL", sdfResult.parse("9:00"), new Slot(44, 1, SlotAvailability.GREEN, P1), "Visa");
		
	}
	
	@Test
	public void calculatefee() {
		
	}
	
	@Test
	public void Test() {
		assertTrue(true);
		
	}

}
