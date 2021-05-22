package bspq21_e4.ParkingManagment.Classes;

import static org.junit.Assert.*;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import junit.framework.JUnit4TestAdapter;

public class ParkingTest {
	
	private Parking P1, P2;
	
	final Logger logger = LoggerFactory.getLogger(SlotTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SlotTest.class);
	}
	
	@Before
	public void setUp() {
		
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		P2 = new Parking(2, "Leioa", 240, 175, 35, 2);
		
	}
	
	@Test
	public void parkingClassTest() {
		
		assertEquals(P1.getClass(), Parking.class);
		assertEquals(P2.getClass(), Parking.class);
	}
	
	@Test
	public void getParkingAvailableSlotsTest() {
		
		assertEquals(50, P1.getAvailableSlots());
		assertEquals(175, P2.getAvailableSlots());
	}
	
	@Test
	public void getParkingnSlotsTest() {
		
		assertEquals(100, P1.getnSlots());
		assertEquals(245, P2.getnSlots());
	}
	
	@Test
	public void getParkingFloorsTest() {
		
		assertEquals(2, P1.getFloors());
		assertEquals(2, P2.getFloors());
	}
	
	@Test
	public void getParkingIdTest() {
		
		assertEquals(1, P1.getId());
		assertEquals(2, P2.getId());
	}
	
	@Test
	public void getParkingNameTest() {
		
		assertEquals("Getxo", P1.getNombre());
		assertEquals("Leioa", P2.getNombre());
	}
	
	@Test
	public void getParkingOccupiedSlotsTest() {
		
		assertEquals(40, P1.getOccupiedSlots());
		assertEquals(35, P2.getOccupiedSlots());
	}
	
	@Test
	public void setParkingAvailableSlotsTest() {
		
		P1.setAvailableSlots(70);
		P2.setAvailableSlots(150);
		
		assertEquals(70, P1.getAvailableSlots());
		assertEquals(150, P2.getAvailableSlots());
	}
	
	@Test
	public void setParkingnSlotsTest() {
		
		P1.setnSlots(130);
		P2.setnSlots(270);
		
		assertEquals(130, P1.getnSlots());
		assertEquals(270, P2.getnSlots());
	}
	
	@Test
	public void setParkingFloorsTest() {
		
		P1.setFloors(1);
		P2.setFloors(3);
		
		assertEquals(1, P1.getFloors());
		assertEquals(3, P2.getFloors());
	}
	
	@Test
	public void setParkingIdTest() {
		
		P1.setId(3);
		P2.setId(4);
		
		assertEquals(3, P1.getId());
		assertEquals(4, P2.getId());
	}
	
	@Test
	public void setParkingNameTest() {
		
		P1.setNombre("Las Arenas");
		P2.setNombre("Santurtzi");
		
		assertEquals("Las Arenas", P1.getNombre());
		assertEquals("Santurtzi", P2.getNombre());
	}
	
	@Test
	public void setParkingOccupiedSlotsTest() {
		
		P1.setOccupiedSlots(30);
		P2.setOccupiedSlots(50);
		
		assertEquals(30, P1.getOccupiedSlots());
		assertEquals(50, P2.getOccupiedSlots());
	}


}
