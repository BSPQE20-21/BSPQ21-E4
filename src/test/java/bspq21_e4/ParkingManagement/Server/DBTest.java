package bspq21_e4.ParkingManagement.Server;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import junit.framework.JUnit4TestAdapter;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class DBTest {

	private static DBManager instance = null;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Transaction transaction;

	private DBManager db;
	private Parking P1, P1U, P2;
	private Slot S1, S2, S1U, S3, S4, S5;
	private PremiumUser PU1, PU2, PU1U;
	private GuestUser GU1, GU2, GU1U;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	final Logger logger = LoggerFactory.getLogger(DBTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(DBTest.class);
	}
	
	@Before
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		db = new DBManager();

		P1 = new Parking(1, "Parking Getxo", 40, 30, 10, 2);
		P1U = new Parking(1, "Parking Getxo", 50, 30, 20, 1);
		P2 = new Parking(2, "Parking Bilbao", 150, 100, 50, 3);

		S1 = new Slot(1, 1, 1, SlotAvailability.GREEN, 1);
		S2 = new Slot(2, 2, 1, SlotAvailability.YELLOW, 1);
		S1U = new Slot(3, 1, 2, SlotAvailability.RED, 2);
		S3 = new Slot(4, 3, 1, SlotAvailability.GREEN, 1);
		S4 = new Slot(5, 4, 1, SlotAvailability.GREEN, 1);
		S5 = new Slot(6, 5, 2, SlotAvailability.GREEN, 2);

		PU1 = new PremiumUser("jon.maeztu@opendeusto.es", "7823 GHJ", 200, 2, "Paypal");
		PU2 = new PremiumUser("mikel.arrieta@opendeusto.es", "5322 ASG", 150, 1, "Paypal");
		PU1U = new PremiumUser("jon.maeztu@opendeusto.es", "7823 GHJ", 300, 2, "VISA");

		GU1 = new GuestUser("7494 NVZ", sdfResult.parse("9:00"), 4, "Paypal");
		GU2 = new GuestUser("8156 BGZ", sdfResult.parse("10:00"), 5, "Paypal");
		GU1U = new GuestUser("7494 NVZ", sdfResult.parse("12:00"), 6, "Paypal");
		logger.info("Leaving setUp");
	}

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void getInstanceTest() throws InterruptedException {
		logger.info("Starting getInstanceTest");
		assertEquals(instance, db.getInstance());
		Thread.sleep(121);
		logger.debug("Finishing getInstanceTest");
	}

	// Para testearlo primero se mete, una vez metido se busca en la base de datos y
	// se saca. Al sacar se compara si son el mismo. Despues se borra, se busca y se
	// espera un null

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void userTest() throws InterruptedException {
		logger.info("Starting userTest");
		db.insertPremiumUser(PU1);
		db.insertPremiumUser(PU2);

		PremiumUser[] listaP = { PU1, PU2 };
		PremiumUser[] listaPU = { PU1U, PU2 };

		assertArrayEquals(listaP, db.getPremiumUsers().toArray());

		db.updatePremiumUser(PU1U);

		assertArrayEquals(listaPU, db.getPremiumUsers().toArray());

		db.deletePremiumUser(PU1U.getPlate());;
		db.deletePremiumUser(PU2.getPlate());

		db.insertGuestUser(GU1);
		db.insertGuestUser(GU2);

		GuestUser[] listaG = { GU1, GU2 };
		GuestUser[] listaGU = { GU1U, GU2 };

		assertArrayEquals(listaG, db.getGuestUsers().toArray());

		db.updateGuestUser(GU1U);
		
		assertArrayEquals(listaGU, db.getGuestUsers().toArray());

		db.deleteGuestUser(GU1U.getPlate());
		db.deleteGuestUser(GU2.getPlate());

		assertEquals(null, db.getPremiumUsers());
		assertEquals(null, db.getGuestUsers());
		
		Thread.sleep(121);
		logger.debug("Finishing userTest");
	}

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void slotTest() throws InterruptedException {
		logger.info("Starting slotTest");
		db.insertSlot(S1);
		db.insertSlot(S2);
		Slot[] listS = { S1, S2 };
		Slot[] listSU = { S1U, S2 };

		assertArrayEquals(listS, db.getSlots().toArray());

		db.updateSlot(S1U);

		assertArrayEquals(listSU, db.getSlots().toArray());

		db.deleteSlot(S1);
		db.deleteSlot(S2);

		assertEquals(null, db.getSlots());
		Thread.sleep(121);
		logger.debug("Finishing slotTest");
	}

	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void parkingTest() throws InterruptedException {
		logger.info("Starting parkingTest");
		db.insertParking(P1);
		db.insertParking(P2);
		Parking[] listP = { P1, P2 };

		assertEquals(P1, db.searchParking("1"));
		assertArrayEquals(listP, db.getParkings().toArray());

		db.updateParking(P1U);

		assertEquals(P1U, db.searchParking("1"));

		db.deleteParking(P1);
		db.searchParking("1");

		assertEquals(null, db.searchParking("1"));

		db.deleteParking(P2);
		db.searchParking("2");

		assertEquals(null, db.searchParking("2"));
		Thread.sleep(121);
		logger.debug("Finishing parkingTest");
	}

	
}
	