package bspq21_e4.ParkingManagement.Windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.client.gui.VentanaParking;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;

/**
 * @class VentanaParkingTest
 * Class to test the parking window
 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
 */
public class VentanaParkingTest {

	static Logger logger = Logger.getLogger(VentanaParkingTest.class.getName());
	
	private VentanaParking vParkingPremium, vParkingGuest;
	private PremiumUser premiumUser;
	private GuestUser guestUser;
	
	/**
	 * This set up of the test case creates the data to be used
	 * 
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Entering setUp");
			premiumUser = new PremiumUser("jon.churruca@gmail.com", "6884 DTX", 100, 1, "Paypal");
			vParkingPremium = new VentanaParking(premiumUser, "12:07:32");
			guestUser = new GuestUser("2524 HSV", "11:13:24", 2, "Paypal");
			vParkingGuest = new VentanaParking(guestUser, "11:13:24");
			logger.info("Leaving setUp");

			
		} catch (HeadlessException e) {
			// TODO: handle exception

		}

	}
	
	/**
	 * This method validates if the created window is from the same class as
	 * VentanaParking
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @throws Exception
	 */
	@Test
	public void testVentanaParking() throws Exception {
		try {
			logger.info("Starting testVentanaParking");
			assertEquals(vParkingPremium.getClass(), VentanaParking.class);
			assertEquals(vParkingGuest.getClass(), VentanaParking.class);
			logger.info("Finishing testVentanaParking");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * This method validates if the created window is visible
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVentanaParkingVisible() throws Exception {
		try {
			logger.info("Starting testVentanaParkingVisible");
//			assertFalse(vParkingPremium.isVisible());
			assertTrue(vParkingPremium.isVisible());
//			vParkingPremium.setVisible(true);
//			assertTrue(vParkingPremium.isVisible());
			assertTrue(vParkingGuest.isVisible());
//			vParkingGuest.setVisible(true);
//			assertTrue(vParkingGuest.isVisible());
			logger.info("Finishing testVentanaParkingVisible");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This method validates if the created window disposes on close
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVentanaParkingCloseOperation() throws Exception {
		try {
			logger.info("Starting testVentanaParkingCloseOperation");
			assertEquals(vParkingPremium.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			assertEquals(vParkingGuest.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testVentanaParkingCloseOperation");
		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

}
