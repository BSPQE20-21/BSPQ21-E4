package bspq21_e4.ParkingManagment.Classes;

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
import junit.framework.JUnit4TestAdapter;

public class PremiumUserTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private Slot S1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;

	
	final Logger logger = LoggerFactory.getLogger(PremiumUserTest.class);
	static int iteration = 0;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(PremiumUserTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		S1 = new Slot(1, 165, 2, SlotAvailability.GREEN, 1);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, S1.getPk(), "PayPal");
		logger.info("Leaving setUp");
	}
	
	@Test
	public void PremiumUserClassTest() { // Test of all the GuestUser functions
		
		assertEquals(PU1.getClass(), PremiumUser.class);
		
	}
	
	@Test
	public void getPremiumUserPlateTest() { // Test of all the GuestUser functions
		
		assertEquals("6735 HGL", PU1.getPlate());
		
	}
	
	@Test
	public void setPremiumUserPlateTest() {
		
		PU1.setPlate("3785 NAS");
		assertEquals("3785 NAS", PU1.getPlate());
	}
	
	@Test
	public void getPremiumUserEmailTest() { // Test of all the GuestUser functions
		
		assertEquals("jonmaeztu@opendeusto.es", PU1.getEmail());
		
	}
	
	@Test
	public void setPremiumUserEmailTest() {
		
		PU1.setEmail("jon.churruca@opendeusto.es");
		assertEquals("jon.churruca@opendeusto.es", PU1.getEmail());
		
	}
	
	@Test
	public void getguestUserSlotTest() { // Test of all the GuestUser functions
		
		assertEquals(S1.getPk(), PU1.getSlotPk());
		
	}
	
	@Test
	public void setguestUserSlotTest() {
		
		Slot S2 = new Slot(2, 170, 2, SlotAvailability.GREEN, 1);
		PU1.setSlotPk(2);
		assertEquals(S2.getPk(), PU1.getSlotPk());
	}
	
	@Test
	public void getguestUserPaymentTest() { // Test of all the GuestUser functions
		
		assertEquals("Visa", PU1.getPaymentMethod());
				
	}
	
	@Test
	public void setguestUserPaymentTest() {
		
		PU1.setPaymentMethod("Paypal");
		assertEquals("Paypal", PU1.getPaymentMethod());
	}
	
	@Test
	public void getPremiumUserMonthFeeTest() { // Test of all the GuestUser functions
		
		assertEquals(300, PU1.getMonthfee());
				
	}
	
	@Test
	public void setPremiumUserMonthFeeTest() {
		
		PU1.setMonthfee(400);
		assertEquals(400, PU1.getMonthfee());
	}


}
