package bspq21_e4.ParkingManagement.Windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.client.gui.AuthWindow;
import bspq21_e4.ParkingManagement.client.gui.PaymentWindow;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;

/**
 * @class PaymentWindowTest
 * Class to test the Payment window
 * @see bspq21_e4.ParkingManagement.client.gui.PaymentWindow
 */
public class PaymentWindowTest {

	static Logger logger = Logger.getLogger(AuthWindowTest.class.getName());
	
	PaymentWindow paymentWindowPremium, paymentWindowGuest;
	PremiumUser premiumUser;
	GuestUser guestUser;
	
	/**
	 * This set up of the test case creates the data to be used
	 * 
	 * @see bspq21_e4.ParkingManagement.client.gui.PaymentWindow
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Entering setUp");
			premiumUser = new PremiumUser("jon.churruca@gmail.com", "6884 DTX", 100, 1, "Paypal");
			paymentWindowPremium = new PaymentWindow(premiumUser, "12:07:32");
			guestUser = new GuestUser("2524 HSV", "11:13:24", 2, "Paypal");
			paymentWindowGuest = new PaymentWindow(guestUser, "11:13:24");
			logger.info("Leaving setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception

		}

	}
	
	/**
	 * This method validates if the created window is from the same class as
	 * PaymentWindow
	 * @see bspq21_e4.ParkingManagement.client.gui.PaymentWindow
	 * @throws Exception
	 */
	@Test
	public void testPaymentWindow() throws Exception {
		try {
			logger.info("Starting testPaymentWindow");
			assertEquals(paymentWindowPremium.getClass(), PaymentWindow.class);
			assertEquals(paymentWindowGuest.getClass(), PaymentWindow.class);
			logger.info("Finishing testPaymentWindow");

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
	public void testPaymentWindowVisible() throws Exception {
		try {
			logger.info("Starting testPaymentWindowVisible");
			assertFalse(paymentWindowPremium.isVisible());
			paymentWindowPremium.setVisible(true);
			assertTrue(paymentWindowPremium.isVisible());
			assertFalse(paymentWindowGuest.isVisible());
			paymentWindowGuest.setVisible(true);
			assertTrue(paymentWindowGuest.isVisible());
			logger.info("Finishing testPaymentWindowVisible");

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
	public void testPaymentWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testPaymentWindowCloseOperation");
			assertEquals(paymentWindowPremium.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			assertEquals(paymentWindowGuest.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testPaymentWindowCloseOperation");
		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

}
