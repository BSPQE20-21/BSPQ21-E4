package bspq21_e4.ParkingManagment.Classes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import junit.framework.JUnit4TestAdapter;

public class SlotTest {
	
	private Parking P1;
	private PremiumUser PU1;
	private Slot S1;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	private static double standardFee = 0.04;
	
	final Logger logger = LoggerFactory.getLogger(SlotTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SlotTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		P1 = new Parking(1, "Getxo", 100, 50, 40, 2);
		S1 = new Slot(1, 165, 2, SlotAvailability.GREEN, P1.getId());
		PU1 = new PremiumUser("jonmaeztu@opendeusto.es", "8534 GHL", 300, S1.getPk(), "PayPal");
		logger.info("Leaving setUp");
	}
	
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void SlotClassTest() { 
		
		assertEquals(S1.getClass(), Slot.class);
		
	}
	
	@Test
	public void getSlotPKTest() { 
		
		assertEquals(1, S1.getPk());
		
	}
	
	@Test
	public void setSlotPKTest() { 
		
		S1.setPk(2);
		assertEquals(2, S1.getPk());
		
	}
	
	@Test
	public void getSlotIdTest() { 
		
		assertEquals(165, S1.getId());
		
	}
	
	@Test
	public void setSlotIdTest() { 
		
		S1.setPk(170);
		assertEquals(170, S1.getId());
		
	}
	
	@Test
	public void getSlotFloorTest() { 
		
		assertEquals(2, S1.getFloor());
		
	}
	
	@Test
	public void setSlotFloorTest() { 
		
		S1.setFloor(1);
		assertEquals(1, S1.getFloor());
		
	}
	
	@Test
	public void getSlotAvailabilityTest() { 
		
		assertEquals(SlotAvailability.GREEN, S1.getSl());
		
	}
	
	@Test
	public void setSlotAvailabilityTest() { 
		
		S1.setSl(SlotAvailability.RED);
		assertEquals(SlotAvailability.RED, S1.getSl());
		
	}
	
	@Test
	public void getSlotParkingTest() { 
		
		assertEquals(P1.getId(), S1.getIdParking());
		
	}
	
	@Test
	public void setSlotParkingTest() { 
		
		P1.setId(2);
		S1.setIdParking(P1.getId());
		assertEquals(P1.getId(), S1.getIdParking());
		
	}

	

}
