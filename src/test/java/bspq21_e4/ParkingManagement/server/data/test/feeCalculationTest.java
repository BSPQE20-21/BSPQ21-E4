package bspq21_e4.ParkingManagement.server.data.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.server.data.CalculateFee;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import static org.mockito.Mockito.*;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

public class feeCalculationTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private GuestUser GU1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;
	
	final Logger logger = LoggerFactory.getLogger(dbTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(feeCalculationTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Parking Getxo", 200, 150, 50, 2);
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, new Slot(165, 2, SlotAvailability.GREEN, P1), "PayPal");
		GU1 = new GuestUser("6735 HGL", sdfResult.parse("9:00"), new Slot(44, 1, SlotAvailability.GREEN, P1), "Visa");
		logger.info("Leaving setUp");
	}
	
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void testFeeCalculator() throws ParseException {
		logger.info("Starting testFeeCalculator");
		double expected = 60 * 0.04; // (Minutes from 9:00 to 10:00) * (Standard fee = 0.04) 
		//int minutes = CalculateFee.getDifferenceBetwenDates(GU1.getEntranceDate(), sdfResult.parse("10:00"));
		assertEquals(expected, CalculateFee.calculateFee(CalculateFee.getDifferenceBetwenDates(GU1.getEntranceDate(), sdfResult.parse("10:00"))), 0);
		Thread.sleep(121);
		logger.debug("Finishing testFeeCalculator");
	}
	
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void guestPlateValidation() throws ParseException {
		logger.info("Starting guestPlateValidation");
        String solution = GU1.getPlate();
        assertTrue(solution);
        Thread.sleep(121);
        logger.debug("Finishing guestPlateValidation");
    }

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
    public void premiumPlateValidation() throws ParseException {
		logger.info("Starting guestPlateValidation");
        String solution = PU1.getPlate();
        assertTrue(solution);
        Thread.sleep(121);
        logger.debug("Finishing guestPlateValidation");
    }
    
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
    public void emailValidation() throws ParseException {
		logger.info("Starting guestPlateValidation");
        String solution = GU1.getEmail();
        String right = PU1.getEmail();
        assertTrue(solution);
        assertTrue(right);
        Thread.sleep(121);
        logger.debug("Finishing guestPlateValidation");
        
    }
	
}
