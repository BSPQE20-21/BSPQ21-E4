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
    private Parking P1,P1U,P2;
    private Slot S1,S2,S1U,S3,S4,S5;
    private PremiumUser PU1,PU2,PU1U;
    private GuestUser GU1, GU2, GU1U;
    private static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	
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
		P1U = new Parking(1, "Parking Getxo", 50, 30, 20, 1);
        P2 = new Parking(2, "Parking Bilbao", 150, 100, 50, 3);

        S1 = new Slot(1, 1, SlotAvailability.GREEN, P1);
        S2 = new Slot(2, 1, SlotAvailability.YELLOW, P1);
        S1U = new Slot(1, 2, SlotAvailability.RED, P2);
        S3 = new Slot(3, 1, SlotAvailability.GREEN, P1);
        S4 = new Slot(4, 1, SlotAvailability.GREEN, P1);
        S5 = new Slot(5, 2, SlotAvailability.GREEN, P2);

        PU1 = new PremiumUser("jon.maeztu@opendeusto.es", "7823 GHJ", 200, S2, "Paypal");
        PU2 = new PremiumUser("mikel.arrieta@opendeusto.es", "5322 ASG", 150, S1, "Paypal");
        PU1U = new PremiumUser("jon.maeztu@opendeusto.es", "7823 GHJ", 300, S2, "VISA");

        GU1 = new GuestUser("7494 NVZ", sdfResult.parse("9:00"), S3, "Paypal");
        GU2 = new GuestUser("8156 BGZ", sdfResult.parse("10:00"), S4, "Paypal");
        GU1U = new GuestUser("7494 NVZ", sdfResult.parse("12:00"), S5, "Paypal");

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
	
	@Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
    public void userTest() { 
		logger.info("Starting userTest");
        db.insertPremiumUser(PU1);
        db.insertPremiumUser(PU2);

        PremiumUser[] listaP = {PU1, PU2};

        assertEquals(PU1, db.getUser(PU1.getPlate()));
        assertArrayEquals(listaP, db.getPremiumUsers().toArray());

        db.updatePremiumUser(PU1U);

        assertNotEquals(PU1, db.getUser(PU1.getPlate()));

        db.deletePremiumUser(PU1);
        db.deletePremiumUser(PU2);


        db.insertGuestUser(GU1);
        db.insertGuestUser(GU2);

        GuestUser[] listaG = {GU1, GU2};

        assertEquals(GU1, db.getUser(GU1.getPlate()));
        assertArrayEquals(listaG, db.getGuestUsers().toArray());

        db.updateGuestUser(GU1U);

        assertNotEquals(GU1, db.getUser(GU1.getPlate()));

        User[] listaU = {PU1,PU2,GU1,GU2};

        assertArrayEquals(listaU, db.getUsers().toArray());

        db.deletePremiumUser(PU1);
        db.deletePremiumUser(PU2);
        db.deleteGuestUser(GU1);
        db.deleteGuestUser(GU2);

        assertEquals(null, db.getPremiumUsers());
        assertEquals(null, db.getPremiumUsers());
        Thread.sleep(121);
        logger.debug("Finishing userTest");



    }
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
    public void slotTest() { 
		logger.info("Starting slotTest");
        db.insertSlot(S1);
        db.insertSlot(S2);
        Slot[] listS = {S1, S2};

        assertArrayEquals(listS, db.getSlots().toArray());

        db.updateSlot(S1U);

        assertNotEquals(listS, db.getSlots().toArray());

        db.deleteSlot(S1);
        db.deleteSlot(S2);

        assertEquals(null, db.getSlots());
        Thread.sleep(121);
        logger.debug("Finishing slotTest");

    }
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
    public void parkingTest() {
		logger.info("Starting parkingTest");
        db.insertParking(P1);
        db.insertParking(P2);
        Parking[] listP = {P1, P2};

        assertEquals(P1, db.searchParking("1"));
        assertArrayEquals(listP, db.getParkings().toArray());

        db.updateParking(P1U);

        assertNotEquals(P1, db.searchParking("1"));

        db.deleteParking(P1);
        db.searchParking("1");

        assertEquals(null, db.searchParking("1"));

        db.deleteParking(P2);
        db.searchParking("2");

        assertEquals(null, db.searchParking("2"));
        Thread.sleep(121);
        logger.debug("Finishing slotTest");
    }


}
