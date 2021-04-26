package bspq21_e4.ParkingManagement.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class JUnit {
	
	private Parking P1;
	
	@Before
	public void setUp() {
		P1 = new Parking(1, "Getxo", 200, 150, 50, 2);
	}

	
	@Test
	public void Test() {
		Parking expected = new Parking(1, "Getxo", 200, 150, 50, 2);
		assertEquals(expected, P1);
		
	}

}
