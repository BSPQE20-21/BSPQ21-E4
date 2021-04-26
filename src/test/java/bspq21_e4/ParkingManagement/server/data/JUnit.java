package bspq21_e4.ParkingManagement.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class JUnit {
	
	private Parking P1;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUnit.class);
		}
	
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
