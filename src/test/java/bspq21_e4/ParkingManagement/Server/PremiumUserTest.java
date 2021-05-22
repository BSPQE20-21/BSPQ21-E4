package bspq21_e4.ParkingManagement.Server;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import bspq21_e4.ParkingManagement.server.data.User;
import junit.framework.JUnit4TestAdapter;

public class PremiumUserTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private Slot S1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;
	private User U1;
	
	final Logger logger = LoggerFactory.getLogger(PremiumUserTest.class);
	static int iteration = 0;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(PremiumUserTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		S1 = new Slot(165, 2, SlotAvailability.GREEN, P1);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, S1, "PayPal");
		logger.info("Leaving setUp");
	}
	
	@Test
	public void premiumUsertest() throws ParseException { // Test of all the GuestUser functions 
		
		assertEquals("8534 GHL", PU1.getPlate());
		PU1.setPlate("3785 NAS");
		assertEquals("3785 NAS", PU1.getPlate());
		
		assertEquals("jonmaeztu@opendeusto.es", PU1.getEmail());
		PU1.setEmail("jon.churruca@opendeusto.es");
		assertEquals("jon.churruca@opendeusto.es", PU1.getEmail());
		
		
		assertEquals(300, PU1.getMonthfee());
		PU1.setMonthfee(200);
		assertEquals(200, PU1.getMonthfee());
		
		assertEquals(S1, PU1.getSelectedSlot());
		Slot S2 = new Slot(170, 2, SlotAvailability.GREEN, P1);
		PU1.setSelectedSlot(S2);
		assertEquals(S2, PU1.getSelectedSlot());
		
		assertEquals("Paypal", PU1.getPaymentMethod());
		PU1.setPaymentMethod("Visa");
		assertEquals("Visa", PU1.getPaymentMethod());
		
		
		
	}


}
