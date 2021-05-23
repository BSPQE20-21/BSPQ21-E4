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
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.RSH.*;


public class PremiumUserRSHTest {

	private static HttpServer server;
	private static PremiumUserRSH rsh;

	private static PremiumUser cliente1, cliente2, cliente3;
	private List<PremiumUser> clientesBD;

	@BeforeClass
	public static void setUp() {
		// start the server
		server = Server.startServer();
		rsh = PremiumUserRSH.getInstance();

		cliente1 = new PremiumUser("jon.churruca@gmail.com", "7494 NVZ", 300, 4, "Paypal");
		cliente2 = new PremiumUser("jonmaeztu@gmail.com", "5467 JHD", 200, 5, "Paypal");
		cliente3 = new PremiumUser("ggdebaquedano@gmail.com", "2858 ASF", 150, 6, "Paypal");

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
		rsh.savePremiumUsers(cliente1);
		rsh.savePremiumUsers(cliente2);
		
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		clientesBD = rsh.checkPremiumUsers();
		for (PremiumUser cl : clientesBD) {
			rsh.deletePremiumUser(cl);
		}
	}

	@Test
	public void testVerClientes() {
		System.out.println(
				"================================================Test ver clientes================================================");

		clientesBD = rsh.checkPremiumUsers();
		assertEquals(clientesBD.size(), 5);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (PremiumUser cliente : clientesBD) {
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
		rsh.savePremiumUsers(cliente3);
		clientesBD = rsh.checkPremiumUsers();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (PremiumUser cliente : clientesBD) {
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
		rsh.modifyPremiumUser(cliente3);
		clientesBD = rsh.checkPremiumUsers();
		assertEquals(clientesBD.size(), 3);

		boolean cliente3_found = false;

		for (PremiumUser cliente : clientesBD) {
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
		rsh.deletePremiumUser(cliente1);
		clientesBD = rsh.checkPremiumUsers();
		assertEquals(clientesBD.size(), 2);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		

		for (PremiumUser cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			} 
		}
		assertTrue(!cliente1_found && cliente2_found);
	}

}