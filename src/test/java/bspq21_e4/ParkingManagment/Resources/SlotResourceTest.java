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
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import bspq21_e4.ParkingManagement.server.resource.SlotResource;
import junit.framework.JUnit4TestAdapter;


public class SlotResourceTest {

	private Slot S1, S2, S3;
	private SlotResource SR;
	private List<Slot> listS, listS1;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SlotResourceTest.class);
	}

	@Before
	public void setUp() {

		S1 = new Slot(1, 23, 1, SlotAvailability.GREEN, 1);
		S2 = new Slot(2, 134, 2, SlotAvailability.GREEN, 2);
		S3 = new Slot(1, 24, 1, SlotAvailability.YELLOW, 1);

		listS = new ArrayList<Slot>();
		listS1 = new ArrayList<Slot>();

		listS.add(S1);
		listS.add(S2);
		
		listS1.add(S3);
		listS1.add(S2);

		SR = new SlotResource();

	}

	@Test
	public void slotResourceClassTest() {

		assertEquals(SR.getClass(), SlotResource.class);
	}

	@Test
	public void slotResourceInsertTest() {

		assertEquals(S1, SR.insertSlot(S1));
		assertEquals(S2, SR.insertSlot(S2));

	}

	@Test
	public void slotResourceGetTest() {

		assertArrayEquals(listS.toArray(), SR.getSlots().toArray());

	}

	@Test
	public void slotResourceModifyTest() {

		SR.modifySlot(S3);
		assertArrayEquals(listS1.toArray(), SR.getSlots().toArray());

	}

	@Test
	public void slotResourceDeleteTest() {

		assertEquals("Deleted", SR.deleteSlot(S1));
		assertEquals("Deleted", SR.deleteSlot(S2));

	}

}
