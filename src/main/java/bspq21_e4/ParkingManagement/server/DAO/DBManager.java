package bspq21_e4.ParkingManagement.server.DAO;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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

/**
 * @class DBManager Window which allows database interaction
 * @author BSPQ21-E4
 */
public class DBManager {
	private static DBManager instance = null;
	private static ResourceBundle resourceBundle;
	private static PersistenceManagerFactory persistentManagerFactory;
	private static PersistenceManager persistentManager;
	private static Logger logger = Logger.getLogger(DBManager.class.getName());
	private static Transaction transaction;
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	/**
	 * Constructor of the window just receives the user logged
	 * 
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
	 * 
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
	 * 
	 * @param parking
	 */
	public void insertParking(Parking parking) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			persistentManager.makePersistent(parking);
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("parking"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

	}

	/**
	 * Updates the parking from the DB
	 * 
	 * @param parking
	 */
	public void updateParking(Parking parking) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Parking> e = persistentManager.getExtent(Parking.class, true);
			Iterator<Parking> iter = e.iterator();
			while (iter.hasNext()) {
				Parking parkingModify = (Parking) iter.next();
				if (parkingModify.getId() == parking.getId()) {
					parkingModify.setAvailableSlots(parking.getAvailableSlots());
					parkingModify.setOccupiedSlots(parking.getOccupiedSlots());
				}
			}
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("updatep"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

//	/**
//	 * Deletes the parking from the DB
//	 * 
//	 * @param idParking
//	 */
//	public void deleteParking(String idParking) {
//		// Delete data using Extent
//		persistentManager = persistentManagerFactory.getPersistenceManager();
//		transaction = persistentManager.currentTransaction();
//		try {
//			transaction.begin();
//			Extent<Parking> e = persistentManager.getExtent(Parking.class, true);
//			Iterator<Parking> iter = e.iterator();
//			while (iter.hasNext()) {
//				Parking parking = (Parking) iter.next();
//				
//				if (Integer.toString(parking.getId()) == null ? idParking == null : Integer.toString(parking.getId())== idParking) {
//					persistentManager.deletePersistent(parking);
//					
//				}
//			}
//
//			transaction.commit();
//		} catch (Exception ex) {
//			logger.warn(getResourceBundle().getString("deleteP"), ex);
//		} finally {
//			if (transaction != null && transaction.isActive()) {
//				transaction.rollback();
//			}
//			persistentManager.close();
//		}
//		
//		
//
//	}

	/**
	 * Stores the slot in the DB
	 * 
	 * @param slot
	 */
	public void insertSlot(Slot slot) {

		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			persistentManager.makePersistent(slot);
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("insertS"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

	}

	/**
	 * Updates the slot from the DB
	 * 
	 * @param slot
	 */
	public void updateSlot(Slot slot) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Slot> e = persistentManager.getExtent(Slot.class, true);
			Iterator<Slot> iter = e.iterator();
			while (iter.hasNext()) {
				Slot slotModify = (Slot) iter.next();
				if (slotModify.getPk() == slot.getPk()) {
					slotModify.setSl(slot.getSl());

				}
			}
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("updateS"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

//	/**
//	 * Deletes the slot from the DB
//	 * 
//	 * @param slotPk
//	 */
//	public void deleteSlot(String slotPk) {
//
//		// Delete data using Extent
//		persistentManager = persistentManagerFactory.getPersistenceManager();
//		transaction = persistentManager.currentTransaction();
//		try {
//			transaction.begin();
//			Extent<Slot> e = persistentManager.getExtent(Slot.class, true);
//			Iterator<Slot> iter = e.iterator();
//			while (iter.hasNext()) {
//				Slot slot = (Slot) iter.next();
//				
//				if (Integer.toString(slot.getPk()) == null ? slotPk == null : Integer.toString(slot.getPk())== slotPk) {
//					persistentManager.deletePersistent(slot);
//					
//				}
//			}
//
//			transaction.commit();
//		} catch (Exception ex) {
//			logger.warn(getResourceBundle().getString("deleteP"), ex);
//		} finally {
//			if (transaction != null && transaction.isActive()) {
//				transaction.rollback();
//			}
//			persistentManager.close();
//		}
//		
//
//	}

	/**
	 * Stores the premium user in the DB
	 * 
	 * @param user
	 */
	public void insertPremiumUser(PremiumUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			persistentManager.makePersistent(user);
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("insertP"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Updates the premium user from the DB
	 * 
	 * @param user
	 */
	public void updatePremiumUser(PremiumUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<PremiumUser> e = persistentManager.getExtent(PremiumUser.class, true);
			Iterator<PremiumUser> iter = e.iterator();
			while (iter.hasNext()) {
				PremiumUser userModified = (PremiumUser) iter.next();
				if (userModified.getPlate() == null ? user.getPlate() == null
						: userModified.getPlate().equals(user.getPlate())) {
					userModified.setSlotPk(user.getSlotPk());
					userModified.setPaymentMethod(user.getPaymentMethod());
					user.setPaymentMethod(user.getPaymentMethod());
					userModified.setMonthfee(user.getMonthfee());

				}
			}
			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error updating: " + ex.getMessage());
			logger.warn(getResourceBundle().getString("updateP"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Deletes the premium user from the DB
	 * 
	 * @param user
	 */
	public void deletePremiumUser(String plate) {

		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();
			Extent<PremiumUser> e = persistentManager.getExtent(PremiumUser.class, true);
			Iterator<PremiumUser> iter = e.iterator();
			while (iter.hasNext()) {
				PremiumUser user = (PremiumUser) iter.next();
				if (user.getPlate() == null ? plate == null : user.getPlate().equals(plate)) {
					persistentManager.deletePersistent(user);
				}
			}

			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("deleteP"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			persistentManager.close();
		}
	}

	/**
	 * Stores the guest user in the DB
	 * 
	 * @param user
	 */
	public void insertGuestUser(GuestUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			persistentManager.makePersistent(user);
			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("insertG"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Updates the guest user from the DB
	 * 
	 * @param user
	 */
	public void updateGuestUser(GuestUser user) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<GuestUser> e = persistentManager.getExtent(GuestUser.class, true);
			Iterator<GuestUser> iter = e.iterator();
			while (iter.hasNext()) {
				GuestUser userModified = (GuestUser) iter.next();
				if (userModified.getPlate() == null ? user.getPlate() == null
						: userModified.getPlate().equals(user.getPlate())) {
					userModified.setSlotPk(user.getSlotPk());
					userModified.setEntranceDate(user.getEntranceDate());
					userModified.setPaymentMethod(user.getPaymentMethod());
				

				}
			}
			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error updating: " + ex.getMessage());
			logger.warn(getResourceBundle().getString("updateG"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
	}

	/**
	 * Deletes the guest user from the DB
	 * 
	 * @param user
	 */
	public void deleteGuestUser(String plate) {

		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();
			Extent<GuestUser> e = persistentManager.getExtent(GuestUser.class, true);
			Iterator<GuestUser> iter = e.iterator();
			while (iter.hasNext()) {
				GuestUser user = (GuestUser) iter.next();
				if (user.getPlate() == null ? plate == null : user.getPlate().equals(plate)) {
					persistentManager.deletePersistent(user);
				}
			}

			transaction.commit();
		} catch (Exception ex) {
			logger.warn(getResourceBundle().getString("deleteG"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			persistentManager.close();
		}
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
			logger.warn(getResourceBundle().getString("parkinget"), ex);
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
			logger.warn(getResourceBundle().getString("slotget"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return slots;

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
			logger.warn(getResourceBundle().getString("premiumget"), ex);
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
			logger.warn(getResourceBundle().getString("guestget"), ex);
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return users;

	}


}
