package bspq21_e4.ParkingManagment.Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bspq21_e4.ParkingManagement.server.data.CalculateFee;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import junit.framework.JUnit4TestAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeeCalculationTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private GuestUser GU1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.5;
	private int minutes;
	private double expectation;
	private CalculateFee c;
	
	final Logger logger = LoggerFactory.getLogger(FeeCalculationTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(FeeCalculationTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Parking Getxo", 200, 150, 50, 2);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, 165, "PayPal");
		GU1 = new GuestUser("6735 HGL", sdfResult.parse("9:00"), 44, "Visa");
		minutes = CalculateFee.getDifferenceBetwenDates(GU1.getEntranceDate(), sdfResult.parse("10:00"));
		c = new CalculateFee();
		logger.info("Leaving setUp");
	}
	
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 200, average = 130)
	public void testFeeCalculatorClass() throws InterruptedException {
		logger.info("Starting testFeeCalculator");
		assertEquals(c.getClass(), CalculateFee.class);
		Thread.sleep(121);
		logger.debug("Finishing testFeeCalculator");
	}
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 200, average = 130)
	public void testFeeCalculator() throws InterruptedException {
		logger.info("Starting testFeeCalculator");
		expectation = standardFee * minutes;
		try {
		assertEquals(expectation, CalculateFee.calculateFee(CalculateFee.getDifferenceBetwenDates(GU1.getEntranceDate(), sdfResult.parse("10:00"))), 0);
		}catch (Exception e) {
			System.out.println("Error in calculating fee");
		}
		Thread.sleep(121);
		logger.debug("Finishing testFeeCalculator");
	}
	
}
