package bspq21_e4.ParkingManagement.Server;

import static org.junit.Assert.*;

import java.awt.HeadlessException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.Windows.AuthWindowTest;
import bspq21_e4.ParkingManagement.client.gui.AuthWindow;
import bspq21_e4.ParkingManagement.server.main.Server;

public class ServerTest {

	static Logger logger = Logger.getLogger(AuthWindowTest.class.getName());
	
	Server server;
	
	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Entering setUp");
			server = new Server();
			logger.info("Leaving setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception

		}

	}
	
	@Test
	public void testServer() throws Exception {
		try {
			logger.info("Starting testServer");
			assertEquals(server.getClass(), Server.class);
			logger.info("Finishing testServer");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}
}
