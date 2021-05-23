package bspq21_e4.ParkingManagment.Util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.server.util.PrepareData;

public class PrepareDataTest {

	private PrepareData PD;
	
	@Before
	public void setUp() {
		
		PD = new PrepareData();
		
	}
	
	@Test
	public void testPrepareDataClass() {
		assertEquals(PD.getClass(), PrepareData.class);
	}
	
}
