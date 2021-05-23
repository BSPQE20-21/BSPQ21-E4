package bspq21_e4.ParkingManagement.RSH;

import static org.junit.Assert.*;

import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import bspq21_e4.ParkingManagement.server.main.Server;
import bspq21_e4.ParkingManagement.server.data.*;
import bspq21_e4.ParkingManagement.server.rsh.ParkingRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.SlotRSH;
import bspq21_e4.ParkingManagement.RSH.*;


public class SlotRSHTest {

	private static HttpServer server;
	private static SlotRSH rsh;

	private static Slot cliente1, cliente2, cliente3;
	private List<Slot> clientesBD;

	@BeforeClass
	public static void setUp() {
		// start the server
		server = Server.startServer();
		rsh = SlotRSH.getInstance();

		cliente1 = new Slot(1, 165, 2, SlotAvailability.GREEN, 1);
		cliente2 = new Slot(2, 167, 2, SlotAvailability.GREEN, 2);
		cliente3 = new Slot(3, 169, 2, SlotAvailability.GREEN, 1);

	}

	@AfterClass
	public static void tearDown() throws Exception {
		server.shutdownNow();

	}

	@Before
	public void PrepareData() {
		// Store test
		System.out.println(
				"================================================Creating data ...================================================");
		rsh.saveSlots(cliente1);
		rsh.saveSlots(cliente2);
		
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		clientesBD = rsh.checkSlots();
		for (Slot cl : clientesBD) {
			rsh.deleteSlot(cl);
		}
	}

	@Test
	public void testVerClientes() {
		System.out.println(
				"================================================Test ver clientes================================================");

		clientesBD = rsh.checkSlots();
		assertEquals(clientesBD.size(), 5);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (Slot cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			}  
		}
		assertTrue(cliente1_found && cliente2_found);
	}

	@Test
	public void testSubirClientes() {
		System.out.println(
				"================================================Test subir clientes================================================");
		rsh.saveSlots(cliente3);
		clientesBD = rsh.checkSlots();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (Slot cliente : clientesBD) {
			if (cliente.equals(cliente3)) {
				cliente3_found = true;
			}
		}
		assertTrue(cliente3_found);

	}
	
	@Test
	public void testModifyClientes() {
		System.out.println(
				"================================================Test subir clientes================================================");
		rsh.modifySlot(cliente3);
		clientesBD = rsh.checkSlots();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (Slot cliente : clientesBD) {
			if (!cliente.equals(cliente3)) {
				cliente3_found = true;
			}
		}
		assertTrue(cliente3_found);

	}

	@Test
	public void testEliminarCliente() {
		System.out.println(
				"================================================Test eliminar clientes================================================");
		rsh.deleteSlot(cliente1);
		clientesBD = rsh.checkSlots();
		assertEquals(clientesBD.size(), 2);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (Slot cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			} 
		}
		assertTrue(!cliente1_found && cliente2_found);
	}

}