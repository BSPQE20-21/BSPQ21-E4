package bspq21_e4.ParkingManagement.Server;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class slotTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private Slot S1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;
	
	final Logger logger = LoggerFactory.getLogger(premiumUserTest.class);
	static int iteration = 0;
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		S1 = new Slot(165, 2, SlotAvailability.GREEN, P1);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, new Slot(165, 2, SlotAvailability.GREEN, P1), "PayPal");
		logger.info("Leaving setUp");
	}
	
	@Test
	public void premiumUsertest() throws ParseException { // Test of all the GuestUser functions 
		
		assertEquals("8534 GHL", PU1.getPlate());
		assertEquals("jonmaeztu@opendeusto.es", PU1.getEmail());
		
		assertEquals(300, PU1.getMonthfee());
		
		assertEquals(S1, PU1.getSelectedSlot());
		
		assertEquals("Paypal", PU1.getPaymentMethod());
		
		
	}

}
