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
 * @class RegisterWindowTest
 * Class to test the register window window
 * @see bspq21_e4.ParkingManagement.client.gui.RegisterWindow
 */
public class RegisterWindowTest {

	static Logger logger = Logger.getLogger(RegisterWindowTest.class.getName());
	
	RegisterWindow registerWindow;
	
	/**
	 * This set up of the test case creates the data to be used
	 * 
	 * @see bspq21_e4.ParkingManagement.client.gui.RegisterWindow
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
	 * RegisterWindow
	 * @see bspq21_e4.ParkingManagement.client.gui.RegisterWindow
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindow() throws Exception {
		try {
			logger.info("Starting testRegisterWindow");
			assertEquals(registerWindow.getClass(), RegisterWindow.class);
			logger.info("Finishing testRegisterWindow");

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
	public void testRegisterWindowVisible() throws Exception {
		try {
			logger.info("Starting testRegisterWindowVisible");
			assertFalse(registerWindow.isVisible());
			registerWindow.setVisible(true);
			assertTrue(registerWindow.isVisible());
			logger.info("Finishing testRegisterWindowVisible");

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
	public void testRegisterWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testRegisterWindowCloseOperation");
			assertEquals(registerWindow.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testRegisterWindowCloseOperation");
		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}


}


