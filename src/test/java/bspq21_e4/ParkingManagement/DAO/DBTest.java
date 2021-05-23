package bspq21_e4.ParkingManagement.DAO;

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

/**
 * @class DBTest to test the database management
 * @see bspq21_e4.ParkingManagement.server.DAO.DBManager
 * @author BSPQ21-E4
 */
public class DBTest {

	private static DBManager instance = null;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Transaction transaction;


	private Parking P1, P1U, P2;
	private Slot S1, S2, S1U, S3, S4, S5;
	private PremiumUser PU1, PU2, PU1U;
	private GuestUser GU1, GU2, GU1U;
	private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	final Logger logger = LoggerFactory.getLogger(DBTest.class);
	static int iteration = 0;
	
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(DBTest.class);
	}
	
	/**
	 * This set up of the test case creates the data to be used
	 * 
	 * @see bspq21_e4.ParkingManagement.server.data.Parking
	 * @see bspq21_e4.ParkingManagement.server.data.Slot
	 * @see bspq21_e4.ParkingManagement.server.data.PremiumUser
	 * @see bspq21_e4.ParkingManagement.server.data.GuestUser
	 * @throws ParseException
	 */
	@Before
	public void setUp() {
		logger.info("Entering setUp: {}", iteration++);


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

		GU1 = new GuestUser("7494 NVZ", "9:00", 4, "Paypal");
		GU2 = new GuestUser("8156 BGZ", "10:00", 5, "Paypal");
		GU1U = new GuestUser("7494 NVZ", "12:00", 6, "Paypal");
		logger.info("Leaving setUp");
		
		if(instance == null) {
			instance = new DBManager();
		}
		
	}
	
	/**
	 * This method validates if the instance is correct
	 * AuthWindow
	 * @see bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * @throws InterruptedException
	 */
	@Test
	public void getInstanceTest() throws InterruptedException {
		logger.info("Starting getInstanceTest");
	
		assertEquals(instance, DBManager.getInstance());
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
		DBManager.getInstance().insertPremiumUser(PU1);
		DBManager.getInstance().insertPremiumUser(PU2);

		PremiumUser[] listaP = { PU1, PU2 };
		PremiumUser[] listaPU = { PU1U, PU2 };

		assertArrayEquals(listaP, DBManager.getInstance().getPremiumUsers().toArray());

		DBManager.getInstance().updatePremiumUser(PU1U);

		assertArrayEquals(listaPU, DBManager.getInstance().getPremiumUsers().toArray());

		DBManager.getInstance().deletePremiumUser(PU1U.getPlate());;
		DBManager.getInstance().deletePremiumUser(PU2.getPlate());

		DBManager.getInstance().insertGuestUser(GU1);
		DBManager.getInstance().insertGuestUser(GU2);

		GuestUser[] listaG = { GU1, GU2 };
		GuestUser[] listaGU = { GU1U, GU2 };

		assertArrayEquals(listaG, DBManager.getInstance().getGuestUsers().toArray());

		DBManager.getInstance().updateGuestUser(GU1U);
		
		assertArrayEquals(listaGU, DBManager.getInstance().getGuestUsers().toArray());

		DBManager.getInstance().deleteGuestUser(GU1U.getPlate());
		DBManager.getInstance().deleteGuestUser(GU2.getPlate());

		assertEquals(null, DBManager.getInstance().getPremiumUsers());
		assertEquals(null, DBManager.getInstance().getGuestUsers());
		
		Thread.sleep(121);
		logger.debug("Finishing userTest");
	}

	@Test
	public void slotTest() throws InterruptedException {
		logger.info("Starting slotTest");
		DBManager.getInstance().insertSlot(S1);
		DBManager.getInstance().insertSlot(S2);
		Slot[] listS = { S1, S2 };
		Slot[] listSU = { S1U, S2 };

		assertArrayEquals(listS, DBManager.getInstance().getSlots().toArray());

		DBManager.getInstance().updateSlot(S1U);

		assertArrayEquals(listSU, DBManager.getInstance().getSlots().toArray());

		DBManager.getInstance().deleteSlot(S1);
		DBManager.getInstance().deleteSlot(S2);

		assertEquals(null, DBManager.getInstance().getSlots());
		Thread.sleep(121);
		logger.debug("Finishing slotTest");
	}

	@Test
	public void parkingTest() throws InterruptedException {
		logger.info("Starting parkingTest");
		DBManager.getInstance().insertParking(P1);
		DBManager.getInstance().insertParking(P2);
		Parking[] listP = { P1, P2 };

		assertEquals(P1, DBManager.getInstance().searchParking(1));
		assertArrayEquals(listP, DBManager.getInstance().getParkings().toArray());

		DBManager.getInstance().updateParking(P1U);

		assertEquals(P1U, DBManager.getInstance().searchParking(1));

		DBManager.getInstance().deleteParking(P1);
		DBManager.getInstance().searchParking(1);

		assertEquals(null, DBManager.getInstance().searchParking(1));

		DBManager.getInstance().deleteParking(P2);
		DBManager.getInstance().searchParking(2);

		assertEquals(null, DBManager.getInstance().searchParking(2));
		Thread.sleep(121);
		logger.debug("Finishing parkingTest");
	}
	
	
}
	