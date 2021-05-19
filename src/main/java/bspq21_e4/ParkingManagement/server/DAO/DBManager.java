package bspq21_e4.ParkingManagement.server.DAO;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.User;

/**
 * @class DBManager
 * Window which allows database interaction
 * @author BSPQ21-E4
 */
public class DBManager {
	private static DBManager instance = null;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Logger logger = Logger.getLogger(DBManager.class.getName());
	private static Transaction transaction;
	

	/**
     * Constructor of the window just receives the user logged
     * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
     * @param User
     */
	public DBManager() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
	}

	/**
	 * Method that creates a DBManager instance if needed and returns it.
	 * @return DBManager instance
	 */
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		logger.info("DBManager instance returned");
		return instance;
	}

	/**
	 * Stores the parking in the DB 
	 * @param parking
	 */
	public void insertParking(Parking parking) {
		transaction.begin();
		persistentManager.makePersistent(parking);
		transaction.commit();

	}

	/**
	 * Updates the parking from the DB 
	 * @param parking
	 */
	public void updateParking(Parking parking) {
		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<Parking> parkingQuery = persistentManager
					.newQuery("SELECT FROM " + Parking.class.getName() + " WHERE id=='" + parking.getId() + "'");

			parkingQuery.execute();

			System.out.println("- updated parking from db: " + parking.getNombre());
			parking.setNombre(parking.getNombre());

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception updating data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

	}

	/**
	 * Deletes the parking from the DB 
	 * @param parking
	 */
	public void deleteParking(Parking parking) {
		// Delete data using Extent
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<Parking> parkingQuery = persistentManager
					.newQuery("SELECT FROM " + Parking.class.getName() + " WHERE id=='" + parking.getId() + "'");

			parkingQuery.execute();
			System.out.println("- Deleted parking from db: " + parking.getNombre());
			persistentManager.deletePersistent(parking);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception deleting data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

	}

	/**
	 * Stores the slot in the DB 
	 * @param slot
	 */
	public void insertSlot(Slot slot) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		transaction.begin();
		persistentManager.makePersistent(slot);
		transaction.commit();
	}

	/**
	 * Updates the slot from the DB 
	 * @param slot
	 */
	public void updateSlot(Slot slot) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<Slot> slotQuery = persistentManager
					.newQuery("SELECT FROM " + Slot.class.getName() + " WHERE id==" + slot.getId());

			slotQuery.execute();
			System.out.println("- updated slot from db: " + slot.getId());

			slot.setFloor(slot.getFloor());
			slot.setParking(slot.getParking());
			slot.setSl(slot.getSl());

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception updating data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Deletes the slot from the DB 
	 * @param slot
	 */
	public void deleteSlot(Slot slot) {

		// Delete data using Extent
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<Slot> slotQuery = persistentManager
					.newQuery("SELECT FROM " + Slot.class.getName() + " WHERE id==" + slot.getId());

			slotQuery.execute();

			System.out.println("- Deleted slot from db: " + slot.getId());
			persistentManager.deletePersistent(slot);

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception deleting data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

	}

	/**
	 * Stores the premium user in the DB 
	 * @param user
	 */
	public void insertPremiumUser(PremiumUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
//
//		transaction.begin();
//		persistentManager.makePersistent(user);
//		transaction.commit();
		

        try {
            transaction.begin();
   
            persistentManager.makePersistent(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (transaction != null && transaction.isActive()) {
            	transaction.rollback();
            }

            persistentManager.close();
        }
	}

	/**
	 * Updates the premium user from the DB 
	 * @param user
	 */
	public void updatePremiumUser(PremiumUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<PremiumUser> userQuery = persistentManager
					.newQuery("SELECT FROM " + PremiumUser.class.getName() + " WHERE plate=='" + user.getPlate() + "'");

			userQuery.execute();

			System.out.println("- updated user from db: " + user.getPlate());
			user.setEmail(user.getEmail());
			user.setMonthfee(user.getMonthfee());
			user.setPaymentMethod(user.getPaymentMethod());
			user.setSelectedSlot(user.getSelectedSlot());

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception updating data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Deletes the premium user from the DB 
	 * @param user
	 */
	public void deletePremiumUser(PremiumUser user) {

		// Delete data using Extent
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<PremiumUser> userQuery = persistentManager
					.newQuery("SELECT FROM " + PremiumUser.class.getName() + " WHERE plate=='" + user.getPlate() + "'");

			userQuery.execute();

			System.out.println("- Deleted user from db: " + user.getPlate());
			persistentManager.deletePersistent(user);

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception deleting data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Stores the guest user in the DB 
	 * @param user
	 */
	public void insertGuestUser(GuestUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		transaction.begin();
		persistentManager.makePersistent(user);
		transaction.commit();
	}

	/**
	 * Updates the guest user from the DB 
	 * @param user
	 */
	public void updateGuestUser(GuestUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<GuestUser> userQuery = persistentManager
					.newQuery("SELECT FROM " + GuestUser.class.getName() + " WHERE plate=='" + user.getPlate() + "'");

			userQuery.execute();

			System.out.println("- updated user from db: " + user.getPlate());
			user.setEntranceDate(user.getEntranceDate());
			user.setPaymentMethod(user.getPaymentMethod());
			user.setSelectedSlot(user.getSelectedSlot());

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception updating data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Deletes the guest user from the DB 
	 * @param user
	 */
	public void deleteGuestUser(GuestUser user) {

		// Delete data using Extent
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<GuestUser> userQuery = persistentManager
					.newQuery("SELECT FROM " + GuestUser.class.getName() + " WHERE plate=='" + user.getPlate() + "'");

			userQuery.execute();

			System.out.println("- Deleted user from db: " + user.getPlate());
			persistentManager.deletePersistent(user);

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception deleting data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}
	
	/**
	 * Creating and inserting parkings to the DB
	 */
	public void createParkings() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		String[] parkingName = { "Bilbao", "Vitoria", "San Sebastian", "Santander", "LogroÃ±o", "Pamplona", "Gijon",
				"Madrid", "Barcelona", "Oviedo", "Burgos", "Valencia", "Sevilla", "Cadiz", "Almeria", "Murcia",
				"Salamanca", "Zamora", "Biarritz", "Salou", "Granada" };

		for (int i = 0; i < parkingName.length; i++) {
			transaction.begin();
			persistentManager.makePersistent(new Parking(i + 1, parkingName[i], 300, 300, 0, 3));
			transaction.commit();
		}
	}

	/**
	 * Getting a user from the DB given its cars plates
	 * @param plate
	 */
	public User getUser(String plate) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		User u = null;
		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<User> userQuery = persistentManager
					.newQuery("SELECT FROM " + User.class.getName() + " WHERE plate =='" + plate + "'");

			userQuery.execute();

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception obtaining user data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return u;
	}

	/**
	 * Getting the list of parkings
	 */
	public List<Parking> getParkings() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		List<Parking> parkings = new ArrayList<>();

		try {
			System.out.println("Searching parkings...");
			transaction.begin();

			Extent<Parking> parkingExtent = persistentManager.getExtent(Parking.class, true);

			for (Parking parking : parkingExtent) {
				persistentManager.makeTransient(parking);
				parkings.add(parking);
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error obtaining parkings: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return parkings;

	}
	
	/**
	 * Getting the list of slots
	 */
	public List<Slot> getSlots() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		List<Slot> slots = new ArrayList<>();

		try {
			System.out.println("Searching slots...");
			transaction.begin();

			Extent<Slot> slotExtent = persistentManager.getExtent(Slot.class, true);

			for (Slot slot : slotExtent) {
				persistentManager.makeTransient(slot);
				slots.add(slot);
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error obtaining slots: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return slots;

	}

	/**
	 * Getting the list of users
	 */
	public List<User> getUsers() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		List<User> users = new ArrayList<>();

		try {
			System.out.println("Searching users...");
			transaction.begin();

			Extent<User> userExtent = persistentManager.getExtent(User.class, true);

			for (User user : userExtent) {
				persistentManager.makeTransient(user);
				users.add(user);
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error obtaining users: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return users;

	}

	/**
	 * Getting the list of premium users
	 */
	public List<PremiumUser> getPremiumUsers() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		List<PremiumUser> users = new ArrayList<>();

		try {
			System.out.println("Searching users...");
			transaction.begin();

			Extent<PremiumUser> userExtent = persistentManager.getExtent(PremiumUser.class, true);

			for (PremiumUser user : userExtent) {
				persistentManager.makeTransient(user);
				users.add(user);
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error obtaining users: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return users;

	}

	/**
	 * Getting the list of guest users
	 */
	public List<GuestUser> getGuestUsers() {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		List<GuestUser> users = new ArrayList<>();

		try {
			System.out.println("Searching users...");
			transaction.begin();

			Extent<GuestUser> userExtent = persistentManager.getExtent(GuestUser.class, true);

			for (GuestUser user : userExtent) {
				persistentManager.makeTransient(user);
				users.add(user);
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error obtaining users: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return users;

	}

	/**
	 * Getting a parking given its id
	 */
	public Parking searchParking(String id) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		Parking parking = null;
		try {
			transaction.begin();

			@SuppressWarnings("unchecked")
			Query<Parking> parkingQuery = persistentManager
					.newQuery("SELECT FROM " + Parking.class.getName() + " WHERE id =='" + id + "'");

			parkingQuery.execute();

			transaction.commit();
		} catch (Exception ex) {
			System.err.println("* Exception obtaining parking data from DB: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return parking;
	}
}
