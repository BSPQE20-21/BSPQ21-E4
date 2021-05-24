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
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.resource.PremiumUserResource;
import junit.framework.JUnit4TestAdapter;

public class PremiumUserResourceTest {

//	private PremiumUser PU1, PU2, PU3;
//	private PremiumUserResource PUR;
//	private List<PremiumUser> listPU, listPU1;
//
//	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
//
//	public static junit.framework.Test suite() {
//		return new JUnit4TestAdapter(PremiumUserResourceTest.class);
//	}
//
//	@Before
//	public void setUp() {
//
//		PU1 = new PremiumUser("jon.churruca@gmail.com", "4835 NAS", 200, 3, "Paypal");
//		PU2 = new PremiumUser("jonmaeztu@gmail.com", "6758 KSD", 150, 4, "Paypal");
//		PU3 = new PremiumUser("jon.churruca@gmail.com", "4835 NAS", 300, 3, "Visa");
//
//		listPU = new ArrayList<PremiumUser>();
//		listPU1 = new ArrayList<PremiumUser>();
//
//		listPU.add(PU1);
//		listPU.add(PU2);
//		
//		listPU1.add(PU3);
//		listPU1.add(PU2);
//
//		PUR = new PremiumUserResource();
//
//	}
//
//	@Test
//	public void premiumUserResourceClassTest() {
//
//		assertEquals(PUR.getClass(), PremiumUserResource.class);
//	}
//
//	@Test
//	public void premiumUserResourceInsertTest() {
//
//		assertEquals(PU1, PUR.insertPremiumUser(PU1));
//		assertEquals(PU2, PUR.insertPremiumUser(PU2));
//
//	}
//
//	@Test
//	public void premiumUserResourceGetTest() {
//
//		assertArrayEquals(listPU.toArray(), PUR.getPremiumUsers().toArray());
//
//	}
//
//	@Test
//	public void premiumUserResourceModifyTest() {
//
//		PUR.modifyPremiumUser(PU3);
//		assertArrayEquals(listPU1.toArray(), PUR.getPremiumUsers().toArray());
//
//	}
//
//	@Test
//	public void guestUserResourceDeleteTest() {
//
//		assertEquals("Deleted", PUR.deletePremiumUser("4835 NAS"));
//		assertEquals("Deleted", PUR.deletePremiumUser("6758 KSD"));
//
//	}

}
