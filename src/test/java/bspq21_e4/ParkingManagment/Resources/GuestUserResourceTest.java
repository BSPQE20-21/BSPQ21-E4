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
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.resource.GuestUserResource;
import junit.framework.JUnit4TestAdapter;

public class GuestUserResourceTest {

	private GuestUser GU1, GU2, GU3;
	private GuestUserResource GUR;
	private List<GuestUser> listGU;

	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(GuestUserResourceTest.class);
	}

	@Before
	public void setUp() {

		GU1 = new GuestUser("7494 NVZ", "9:00", 4, "Paypal");
		GU2 = new GuestUser("8156 BGZ", "10:00", 5, "Paypal");
		GU3 = new GuestUser("7494 NVZ", "12:00", 4, "Visa");

		listGU = new ArrayList<GuestUser>();

		listGU.add(GU1);
		listGU.add(GU2);

		GUR = new GuestUserResource();

	}

	@Test
	public void guestUserResourceClassTest() {

		assertEquals(GUR.getClass(), GuestUserResource.class);
	}

	@Test
	public void guestUserResourceInsertTest() {

		assertEquals(GU1, GUR.insertGuestUser(GU1));
		assertEquals(GU2, GUR.insertGuestUser(GU2));

	}

	@Test
	public void guestUserResourceGetTest() {

		assertArrayEquals(listGU.toArray(), GUR.getGuestUser().toArray());

	}

	@Test
	public void guestUserResourceModifyTest() {

		assertEquals(GU3, GUR.modifyGuestUser(GU3));
		assertEquals(GU2, GUR.insertGuestUser(GU2));

	}

	@Test
	public void guestUserResourceDeleteTest() {

		assertEquals("Deleted", GUR.deleteGuestUser("8156 BGZ"));
		assertEquals("Deleted", GUR.deleteGuestUser("7494 NVZ"));

	}

}
