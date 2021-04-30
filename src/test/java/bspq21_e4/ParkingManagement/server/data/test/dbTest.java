package bspq21_e4.ParkingManagement.server.data.test;

import static org.junit.Assert.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.Parking;

public class dbTest {
	
	private static DBManager instance = null;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Transaction transaction;
	private DBManager db;
	private Parking P1;
	
	@Before
	public void setUp() {
		db = new DBManager();
		P1 = new Parking(1, "Parking Getxo", 40, 30, 10, 2);
	}

	@Test
	public void getInstanceTest() {
		assertEquals(instance, db.getInstance());
	}
	
	// Para testearlo primero se mete, una vez metido se busca en la base de datos y se saca. Al sacar se compara si son el mismo. Despues se borra, se busca y se espera un null
	@Test
	public void parkingTest() { 
		db.insertParking(P1);
		assertEquals(P1, db.searchParking("1"));
		db.deleteParking(P1);
		db.searchParking("1");
		assertEquals(null, db.searchParking("1"));
		
	}


}
