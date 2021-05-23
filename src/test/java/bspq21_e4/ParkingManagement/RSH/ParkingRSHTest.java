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
import bspq21_e4.ParkingManagement.RSH.*;


public class ParkingRSHTest {

	private static HttpServer server;
	private static ParkingRSH rsh;

	private static Parking cliente1, cliente2, cliente3;
	private List<Parking> clientesBD;

	@BeforeClass
	public static void setUp() {
		// start the server
		server = Server.startServer();
		rsh = ParkingRSH.getInstance();

		cliente1 = new Parking(1, "Getxo", 150, 80, 40, 2);
		cliente2 = new Parking(2, "Lamiako", 200, 150, 50, 3);
		cliente3 = new Parking(3, "Leioa", 300, 200, 800, 2);

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
		rsh.saveParking(cliente1);
		rsh.saveParking(cliente2);
		
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		clientesBD = rsh.checkParkings();
		for (Parking cl : clientesBD) {
			rsh.deleteParking(cl);
		}
	}

	@Test
	public void testVerClientes() {
		System.out.println(
				"================================================Test ver clientes================================================");

		clientesBD = rsh.checkParkings();
		assertEquals(clientesBD.size(), 5);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (Parking cliente : clientesBD) {
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
		rsh.saveParking(cliente3);
		clientesBD = rsh.checkParkings();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (Parking cliente : clientesBD) {
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
		rsh.modifyParking(cliente3);
		clientesBD = rsh.checkParkings();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (Parking cliente : clientesBD) {
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
		rsh.deleteParking(cliente1);
		clientesBD = rsh.checkParkings();
		assertEquals(clientesBD.size(), 2);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (Parking cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			} 
		}
		assertTrue(!cliente1_found && cliente2_found);
	}

}