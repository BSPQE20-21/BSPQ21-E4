package bspq21_e4.ParkingManagment.Resources;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.DAO.DBTest;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.resource.ParkingResource;
import junit.framework.JUnit4TestAdapter;

public class ParkingResourceTest {

//	private Parking P1, P2, P3;
//	private ParkingResource PR;
//	private List<Parking> listP, listP1;
//
//	public static junit.framework.Test suite() {
//		return new JUnit4TestAdapter(ParkingResourceTest.class);
//	}
//
//	@Before
//	public void setUp() {
//
//		P1 = new Parking(1, "Getxo", 100, 50, 40, 1);
//		P2 = new Parking(2, "Leioa", 200, 100, 80, 2);
//		P3 = new Parking(1, "Getxo", 150, 75, 50, 2);
//
//		listP = new ArrayList<Parking>();
//		listP1 = new ArrayList<Parking>();
//
//		listP.add(P1);
//		listP.add(P2);
//		
//		listP1.add(P3);
//		listP1.add(P2);
//
//		PR = new ParkingResource();
//
//	}
//
//	@Test
//	public void premiumUserResourceClassTest() {
//
//		assertEquals(PR.getClass(), ParkingResource.class);
//	}
//
//	@Test
//	public void premiumUserResourceInsertTest() {
//
//		assertEquals(P1, PR.insertParking(P1));
//		assertEquals(P2, PR.insertParking(P2));
//
//	}
//
//	@Test
//	public void premiumUserResourceGetTest() {
//
//		assertArrayEquals(listP.toArray(), PR.getParkings().toArray());
//
//	}
//
//	@Test
//	public void premiumUserResourceModifyTest() {
//
//		PR.modifyParking(P3);
//		assertArrayEquals(listP1.toArray(), PR.getParkings().toArray());
//
//	}
//
//	@Test
//	public void guestUserResourceDeleteTest() {
//
//		assertEquals("Deleted", PR.deleteParking(P1));
//		assertEquals("Deleted", PR.deleteParking(P2));
//
//	}

}
