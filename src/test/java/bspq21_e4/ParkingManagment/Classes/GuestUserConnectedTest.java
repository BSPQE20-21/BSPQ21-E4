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
import junit.framework.JUnit4TestAdapter;

public class GuestUserConnectedTest {
	
	private GuestUser GU1, GU2, GU3;
	private List<GuestUser> listaGU;
	private GuestUserConnected GUC;
	
	static Logger logger = Logger.getLogger(VentanaParkingTest.class.getName());
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(GuestUserConnectedTest.class);
	}
	
	@Before
	public void setUp() {
		
		GU1 = new GuestUser("6735 HGL", "9:00", 44, "Visa");
		GU2 = new GuestUser("2546 AFD", "11:00", 45, "Paypal");
		
		listaGU = new ArrayList<GuestUser>();
		
		listaGU.add(GU1);
		listaGU.add(GU2);
		
		GUC = new GuestUserConnected();
		
	}
	
	@Test
	public void testGuestUserConnectedClass() {
		
		assertEquals(GUC.getClass(), GuestUserConnected.class);
		
	}
	
	@Test
	public void testGuestUserConnectedAddGet() {
		
		GUC.addConnectedUsers(GU1);
		GUC.addConnectedUsers(GU1);
		
		assertArrayEquals(GUC.getConnectedUsers().toArray(), listaGU.toArray());
		
		
		
	}

}
