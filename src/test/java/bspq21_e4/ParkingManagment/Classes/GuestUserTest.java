package bspq21_e4.ParkingManagment.Classes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class GuestUserTest {

	private Parking P1;
	private String s;
	// private PremiumUser PU1;
	private GuestUser GU1;
	private Slot S1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;

	final Logger logger = LoggerFactory.getLogger(GuestUserTest.class);
	static int iteration = 0;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(GuestUserTest.class);
	}

	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		GU1 = new GuestUser("6735 HGL", "9:00", 1, "Visa");
		S1 = new Slot(1, 165, 2, SlotAvailability.GREEN, 1);
		s = "GuestUser [plate=" + "6735 HGL" + ", paymentMethod=" + "Visa" + ", slotPk=" + 1 + ", entranceDate=" + "9:00" + "]";
		logger.info("Leaving setUp");
	}

	@Test
	public void GuestUserClassTest() { // Test of all the GuestUser functions

		assertEquals(GU1.getClass(), GuestUser.class);

	}

	@Test
	public void getGuestUserPlateTest() { // Test of all the GuestUser functions

		assertEquals("6735 HGL", GU1.getPlate());

	}

	@Test
	public void setGuestUserPlateTest() {

		GU1.setPlate("3785 NAS");
		assertEquals("3785 NAS", GU1.getPlate());
		GU1.setPlate("6735 HGL");
	}

	@Test
	public void getGuestUserEntranceDateTest() throws ParseException { // Test of all the GuestUser functions

		assertEquals("9:00", GU1.getEntranceDate());

	}

	@Test
	public void setGuestUserEntranceDateTest() {

		GU1.setEntranceDate("10:00");
		assertEquals("10:00", GU1.getEntranceDate());

	}

	@Test
	public void getGuestUserSlotTest() { // Test of all the GuestUser functions

		assertEquals(S1.getPk(), GU1.getSlotPk());

	}

	@Test
	public void setGuestUserSlotTest() {

		Slot S2 = new Slot(2, 170, 2, SlotAvailability.GREEN, 1);
		GU1.setSlotPk(2);
		assertEquals(S2.getPk(), GU1.getSlotPk());
	}

	@Test
	public void getGuestUserPaymentTest() { // Test of all the GuestUser functions

		assertEquals("Visa", GU1.getPaymentMethod());

	}

	@Test
	public void setGuestUserPaymentTest() {

		GU1.setPaymentMethod("Paypal");
		assertEquals("Paypal", GU1.getPaymentMethod());
		GU1.setPaymentMethod("Visa");
	}
	
	@Test
	public void setSlotToStringTest() { 
		
		
		assertEquals(GU1.toString(), s);
		
	}

}
