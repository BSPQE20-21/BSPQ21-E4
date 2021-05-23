package bspq21_e4.ParkingManagment.Classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.Windows.VentanaParkingTest;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.GuestUserConnected;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUserConnected;
import junit.framework.JUnit4TestAdapter;

public class PremiumUserConnectedTest {
	
	private PremiumUser PU1, PU2;
	private List<PremiumUser> listaPU;
	private PremiumUserConnected PUC;
	
	static Logger logger = Logger.getLogger(PremiumUserConnectedTest.class.getName());
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(PremiumUserConnectedTest.class);
	}
	
	@Before
	public void setUp() {
		
		PU1 = new PremiumUser("jon.churruca@gmail.com", "4835 NAS", 200, 3, "Paypal");
		PU2 = new PremiumUser("jonmaeztu@gmail.com", "2562 SAD", 100, 4, "Paypal");
		
		listaPU = new ArrayList<PremiumUser>();
		
		listaPU.add(PU1);
		listaPU.add(PU2);
		
		PUC = new PremiumUserConnected();
		
	}
	
	@Test
	public void testGuestUserConnectedClass() {
		
		assertEquals(PUC.getClass(), PremiumUserConnected.class);
		
	}
	
	@Test
	public void testGuestUserConnectedAddGet() {
		
		PUC.addConnectedUsers(PU1);
		PUC.addConnectedUsers(PU1);
		
		assertArrayEquals(PUC.getConnectedUsers().toArray(), listaPU.toArray());
		
		
		
	}

}
