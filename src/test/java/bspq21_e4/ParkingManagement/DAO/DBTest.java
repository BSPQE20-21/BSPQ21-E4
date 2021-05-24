package bspq21_e4.ParkingManagement.DAO;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
	public void setUp() throws ParseException {
		logger.info("Entering setUp: {}", iteration++);
		
		if (instance == null) {
			instance = new DBManager();
		}

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

	

	}

	// Para testearlo primero se mete, una vez metido se busca en la base de datos y
	// se saca. Al sacar se compara si son el mismo. Despues se borra, se busca y se
	// espera un null
	@Test
	public void insertParkingTest() throws InterruptedException {
		logger.info("Starting insertParkingTest");
		DBManager.getInstance().insertParking(P1);
		DBManager.getInstance().insertParking(P2);

		List<Parking> lista = DBManager.getInstance().getParkings();
		for (Parking parking : lista) {
			if (parking.getId() == P1.getId()) {
				assertEquals(P1.getId(), parking.getId());
			} else if (parking.getId() == P2.getId()) {
				assertEquals(P2.getId(), parking.getId());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing insertParkingTest");
	}

	@Test
	public void updateParkingTest() throws InterruptedException {
		logger.info("Starting updateParkingTest");
		P1U.setNombre("Mundaka");
		DBManager.getInstance().updateParking(P1U);

		List<Parking> lista = DBManager.getInstance().getParkings();
		for (Parking parking : lista) {
			if (parking.getNombre().equals("Mundaka")) {
				assertEquals("Mundaka", parking.getNombre());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing updateparkingTest");

	}

//	@Test
//	public void deleteParkingTest() throws InterruptedException {
//		logger.info("Starting deleteParkingTest");
////		DBManager.getInstance().deleteParking(Integer.toString(P1.getId()));
//		
//
//		List<Parking> lista = DBManager.getInstance().getParkings();
//		for (Parking parking : lista) {
//
//			if ((parking.getId() == P1.getId())) {
//				DBManager.getInstance().deleteParking(Integer.toString(parking.getId()));
//				assertEquals(null, parking.getId());
//			}
//		}
//		for (Parking parking : lista) {
//			if ((parking.getId() == P2.getId())) {
//				DBManager.getInstance().deleteParking(Integer.toString(P2.getId()));
//				assertEquals(null, P2.getId());
//			}
//		}
//
//		Thread.sleep(121);
//		logger.debug("Finishing deleteparkingTest");
//	}

//	@Test
//	public void insertSlotTest() throws InterruptedException {
//		logger.info("Starting insertSlotTest");
//		DBManager.getInstance().insertSlot(S1);
//		DBManager.getInstance().insertSlot(S2);
//
//		List<Slot> lista = DBManager.getInstance().getSlots();
//		for (Slot slot : lista) {
//			if (slot.getPk() == S1.getPk()) {
//				assertEquals(S1.getPk(), slot.getPk());
//			} else if (slot.getPk() == S2.getPk()) {
//				assertEquals(S2.getPk(), slot.getPk());
//			}
//		}
//
//		Thread.sleep(121);
//		logger.debug("Finishing insertSlotTest");
//
//	}

	@Test
	public void updateSlotTest() throws InterruptedException {
		logger.info("Starting updateSlotTest");
		S1U.setSl(SlotAvailability.YELLOW);
		DBManager.getInstance().updateSlot(S1U);

		List<Slot> lista = DBManager.getInstance().getSlots();
		for (Slot slot : lista) {
			if (slot.getPk() == S1U.getPk()) {
				assertEquals(SlotAvailability.YELLOW, slot.getSl());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing updateSlotTest");
	}

//	@Test
//	public void deleteSlotTest() throws InterruptedException {
//		logger.info("Starting deleteSlotTest");
//		DBManager.getInstance().deleteSlot(Integer.toString(S1.getPk()));
//		DBManager.getInstance().deleteSlot(Integer.toString(S2.getPk()));
//
//		List<Slot> lista = DBManager.getInstance().getSlots();
//		for (Slot slot : lista) {
//			if (!(slot.getPk() == S1.getPk())) {
//				assertEquals(null, S1.getPk());
//			} else if (!(slot.getPk() == S2.getPk())) {
//				assertEquals(null, S2.getPk());
//			}
//		}
//
//		Thread.sleep(121);
//		logger.debug("Finishing deleteSlotTest");
//
//	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void insertPremiumUserTest() throws InterruptedException {
		logger.info("Starting insertPremiumUserTest");
		DBManager.getInstance().insertPremiumUser(PU1);
		DBManager.getInstance().insertPremiumUser(PU2);

		List<PremiumUser> lista = DBManager.getInstance().getPremiumUsers();
		for (PremiumUser user : lista) {
			if (user.getPlate().equals(PU1.getPlate())) {
				assertEquals(PU1.getPlate(), user.getPlate());
			} else if (user.getPlate().equals(PU2.getPlate())) {
				assertEquals(PU2.getPlate(), user.getPlate());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing insertPremiumUserTest");

	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void updatePremiumUserTest() throws InterruptedException {
		logger.info("Starting updatePremiumUserTest");
		PU1U.setEmail("Socorro");
		DBManager.getInstance().updatePremiumUser(PU1U);

		List<PremiumUser> lista = DBManager.getInstance().getPremiumUsers();
		for (PremiumUser user : lista) {
			if (user.getEmail().equals("Socorro")) {
				assertEquals("Socorro", user.getEmail());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing updatePremiumUserTest");
	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void deletePremiumUserTest() throws InterruptedException {
		logger.info("Starting deletePremiumUserTest");
		DBManager.getInstance().deletePremiumUser(PU1.getPlate());
		DBManager.getInstance().deletePremiumUser(PU2.getPlate());

		List<PremiumUser> lista = DBManager.getInstance().getPremiumUsers();
		for (PremiumUser user : lista) {
			if ((user.getPlate().equals(PU1.getPlate()))) {
				assertEquals(null, user.getPlate());
			} else if (!(user.getPlate().equals(PU2.getPlate()))) {
				assertEquals(null, user.getPlate());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing deletePremiumUserTest");

	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void insertGuestUserTest() throws InterruptedException {
		logger.info("Starting insertGuestUserTest");
//		DBManager.getInstance().insertGuestUser(GU1);
//		DBManager.getInstance().insertGuestUser(GU2);

		List<GuestUser> lista = DBManager.getInstance().getGuestUsers();
		for (GuestUser user : lista) {
			if (user.getPlate().equals(GU1.getPlate())) {
				assertEquals(GU1.getPlate(), user.getPlate());
			} else if (user.getPlate().equals(GU2.getPlate())) {
				assertEquals(GU2.getPlate(), user.getPlate());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing insertGuestUserTest");

	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void updateGuestUserTest() throws InterruptedException {
		logger.info("Starting updateGuestUserTest");
		GU1U.setPaymentMethod("Paypal");
		DBManager.getInstance().updateGuestUser(GU1U);

		List<GuestUser> lista = DBManager.getInstance().getGuestUsers();
		for (GuestUser user : lista) {
			if (user.getPaymentMethod().equals("Paypal")) {
				assertEquals("Paypal", user.getPaymentMethod());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing updateGuestUserTest");
	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 120, average = 30)
	public void deleteGuestUserTest() throws InterruptedException {
		logger.info("Starting deleteGuestUserTest");
		DBManager.getInstance().deleteGuestUser(GU1.getPlate());
		DBManager.getInstance().deleteGuestUser(GU2.getPlate());

		List<GuestUser> lista = DBManager.getInstance().getGuestUsers();
		for (GuestUser user : lista) {
			if (!(user.getPlate().equals(GU1.getPlate()))) {
				assertEquals(null, GU1.getPlate());
			} else if (!(user.getPlate().equals(GU2.getPlate()))) {
				assertEquals(null, GU2.getPlate());
			}
		}

		Thread.sleep(121);
		logger.debug("Finishing deleteGuestUserTest");

	}

}
