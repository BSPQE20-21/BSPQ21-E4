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
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.RSH.*;


public class GuestUserRSHTest {

//	private static HttpServer server;
//	private static GuestUserRSH rsh;
//
//	private static GuestUser cliente1;
//	private static GuestUser cliente2;
//	private static GuestUser cliente3;
//	private List<GuestUser> clientesBD;
//
//	@BeforeClass
//	public static void setUp() {
//		// start the server
//		server = Server.startServer();
//		rsh = GuestUserRSH.getInstance();
//
//		cliente1 = new GuestUser("7494 NVZ", "9:00", 4, "Paypal");
//		cliente2 = new GuestUser("5467 JHD", "9:00", 5, "Paypal");
//		cliente3 = new GuestUser("2858 ASF", "9:00", 6, "Paypal");
//
//	}
//
//	@AfterClass
//	public static void tearDown() throws Exception {
//		server.shutdownNow();
//
//	}
//
//	@Before
//	public void PrepareData() {
//		// Store test
//		System.out.println(
//				"================================================Creating data ...================================================");
//		rsh.saveGuestUsers(cliente1);
//		rsh.saveGuestUsers(cliente2);
//		
//	}
//
//	@After
//	public void Clean() {
//		System.out.println(
//				"================================================Cleaning data ...================================================");
//		clientesBD = rsh.checkGuestUsers();
//		for (GuestUser cl : clientesBD) {
//			rsh.deleteGuestUser(cl);
//		}
//	}
//
//	@Test
//	public void testVerClientes() {
//		System.out.println(
//				"================================================Test ver clientes================================================");
//
//		clientesBD = rsh.checkGuestUsers();
//		assertEquals(clientesBD.size(), 5);
//
//		boolean cliente1_found = false;
//		boolean cliente2_found = false;
//		
//
//		for (GuestUser cliente : clientesBD) {
//			if (cliente.equals(cliente1)) {
//				cliente1_found = true;
//			} else if (cliente.equals(cliente2)) {
//				cliente2_found = true;
//			}  
//		}
//		assertTrue(cliente1_found && cliente2_found);
//	}
//
//	@Test
//	public void testSubirClientes() {
//		System.out.println(
//				"================================================Test subir clientes================================================");
//		rsh.saveGuestUsers(cliente3);
//		clientesBD = rsh.checkGuestUsers();
//		assertEquals(clientesBD.size(), 3);
//
//		boolean cliente3_found = false;
//
//		for (GuestUser cliente : clientesBD) {
//			if (cliente.equals(cliente3)) {
//				cliente3_found = true;
//			}
//		}
//		assertTrue(cliente3_found);
//
//	}
//	
//	@Test
//	public void testModifyClientes() {
//		System.out.println(
//				"================================================Test subir clientes================================================");
//		rsh.modifyGuestUser(cliente3);
//		clientesBD = rsh.checkGuestUsers();
//		assertEquals(clientesBD.size(), 3);
//
//		boolean cliente3_found = false;
//
//		for (GuestUser cliente : clientesBD) {
//			if (!cliente.equals(cliente3)) {
//				cliente3_found = true;
//			}
//		}
//		assertTrue(cliente3_found);
//
//	}
//
//	@Test
//	public void testEliminarCliente() {
//		System.out.println(
//				"================================================Test eliminar clientes================================================");
//		rsh.deleteGuestUser(cliente1);
//		clientesBD = rsh.checkGuestUsers();
//		assertEquals(clientesBD.size(), 2);
//
//		boolean cliente1_found = false;
//		boolean cliente2_found = false;
//		
//
//		for (GuestUser cliente : clientesBD) {
//			if (cliente.equals(cliente1)) {
//				cliente1_found = true;
//			} else if (cliente.equals(cliente2)) {
//				cliente2_found = true;
//			} 
//		}
//		assertTrue(!cliente1_found && cliente2_found);
	
//	}

}