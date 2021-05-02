package bspq21_e4.ParkingManagement.server.data.test;

import static org.junit.Assert.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.Parking;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

public class dbTest {
	
	private static DBManager instance = null;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Transaction transaction;
	private DBManager db;
	private Parking P1;
	
	final Logger logger = LoggerFactory.getLogger(dbTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(dbTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Entering setUp: {}", iteration++);
		db = new DBManager();
		P1 = new Parking(1, "Parking Getxo", 40, 30, 10, 2);
		logger.info("Leaving setUp");
	}

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void getInstanceTest() {
		logger.info("Starting getInstanceTest");
		assertEquals(instance, db.getInstance());
		Thread.sleep(121);
		logger.debug("Finishing getInstanceTest");
	}
	
	// Para testearlo primero se mete, una vez metido se busca en la base de datos y se saca. Al sacar se compara si son el mismo. Despues se borra, se busca y se espera un null
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void parkingTest() { 
		logger.info("Starting parkingTest");
		db.insertParking(P1);
		assertEquals(P1, db.searchParking("1"));
		db.deleteParking(P1);
		db.searchParking("1");
		assertEquals(null, db.searchParking("1"));
		Thread.sleep(121);
		logger.debug("Finishing parkingTest");
		
	}


}
