package bspq21_e4.ParkingManagement.Windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.client.gui.AuthWindow;
import bspq21_e4.ParkingManagement.client.gui.RegisterWindow;

/**
 * @class AuthWindowTest
 * Class to test the authorization window
 * @see bspq21_e4.ParkingManagement.client.gui.AuthWindow
 */
public class RegisterWindowTest {

	static Logger logger = Logger.getLogger(RegisterWindowTest.class.getName());
	
	RegisterWindow registerWindow;
	
	/**
	 * This set up of the test case creates the data to be used
	 * 
	 * @see bspq21_e4.ParkingManagement.client.gui.AuthWindow
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Entering setUp");
			registerWindow = new RegisterWindow();
			logger.info("Leaving setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception

		}

	}
	
	/**
	 * This method validates if the created window is from the same class as
	 * AuthWindow
	 * @see bspq21_e4.ParkingManagement.client.gui.AuthWindow
	 * @throws Exception
	 */
	@Test
	public void testAuthWindow() throws Exception {
		try {
			logger.info("Starting testAuthWindow");
			assertEquals(registerWindow.getClass(), AuthWindow.class);
			logger.info("Finishing testAuthWindow");

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
	public void testAuthWindowVisible() throws Exception {
		try {
			logger.info("Starting testAuthWindowVisible");
			assertFalse(registerWindow.isVisible());
			registerWindow.setVisible(true);
			assertTrue(registerWindow.isVisible());
			logger.info("Finishing testAuthWindowVisible");

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
	public void testAuthWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testAuthWindowCloseOperation");
			assertEquals(registerWindow.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testAuthWindowCloseOperation");
		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}


}


