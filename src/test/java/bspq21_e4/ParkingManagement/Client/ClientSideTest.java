package bspq21_e4.ParkingManagement.Client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.client.main.ClientSide;

public class ClientSideTest {
	
	private ClientSide cs;
	
	@Before
	public void setUp() {
		
		cs = new ClientSide();
	}

	@Test
	public void test() {
		
		assertEquals(cs.getClass(), ClientSide.class);
	}

}
