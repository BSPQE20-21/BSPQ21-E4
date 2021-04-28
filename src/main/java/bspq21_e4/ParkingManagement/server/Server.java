package bspq21_e4.ParkingManagement.server;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bspq21_e4.ParkingManagement.client.gui.AuthWindow;
import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.User;

//@Path("/server")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)

public class Server {
	
	
	


//	private int cont = 0;
//	private static PersistenceManagerFactory persistentManagerFactory;
//
//	private static DBManager instance = null;
//	private  PersistenceManager persistentManager= null;
//	private  Transaction transaction= null;
//
//	public Server() {
//		 persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//		this.persistentManager = persistentManagerFactory.getPersistenceManager();
//		this.transaction = persistentManager.currentTransaction();
//
//	}
//	
//	public static DBManager getInstance() {
//		if (instance == null) {
//			instance = new DBManager();
//		}
//		return instance;
//	}
//
////	@POST
////	@Path("/sayMessage")
////	public Response sayMessage(PremiumUser directedMessage) {
////		User user = null;
////		try {
////			tx.begin();
////			System.out.println("Creating query ...");
////
////			Query<User> q = pm.newQuery("SELECT	 FROM " + User.class.getName() + " WHERE login == \""
////					+ directedMessage.getUserData().getLogin() + "\" &&  password == \""
////					+ directedMessage.getUserData().getPassword() + "\"");
////			q.setUnique(true);
////			user = (User) q.execute();
////
////			System.out.println("User retrieved: " + user);
////			if (user != null) {
////				Message message = new Message(directedMessage.getMessageData().getMessage());
////				user.getMessages().add(message);
////				pm.makePersistent(user);
////			}
////			tx.commit();
////		} finally {
////			if (tx.isActive()) {
////				tx.rollback();
////			}
////		}
////
////		if (user != null) {
////			cont++;
////			System.out.println(" * Client number: " + cont);
////			MessageData messageData = new MessageData();
////			messageData.setMessage(directedMessage.getMessageData().getMessage());
////			return Response.ok(messageData).build();
////		} else {
////			return Response.status(Status.BAD_REQUEST)
////					.entity("Login details supplied for message delivery are not correct").build();
////		}
////	}
//
//	@POST
//	@Path("/registerUser")
//	public Response registerUser(PremiumUser userData) {
//		persistentManager = persistentManagerFactory.getPersistenceManager();
//		transaction = persistentManager.currentTransaction();
//
//		transaction.begin();
//		persistentManager.makePersistent(userData);
////		transaction.commit();
////		try {
////			transaction.begin();
////			System.out.println("Checking whether the user already exits or not: '" + userData.getPlate() + "'");
////			userData = null;
////			// User user = null;
////			try {
////				userData = pm.getObjectById(PremiumUser.class, userData.getPlate());
////			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
////				System.out.println("Exception launched: " + jonfe.getMessage());
////			}
////			System.out.println("User: " + userData);
////			if (userData != null) {
////				System.out.println("Setting email user: " + userData);
////				userData.setEmail(userData.getEmail());
////				System.out.println("Password set user: " + userData);
////			} else {
////				System.out.println("Creating user: " + userData);
////				userData = new PremiumUser();
////				pm.makePersistent(userData);
////				System.out.println("User created: " + userData.getEmail() + ", " + userData.getPlate());
////			}
////			tx.commit();
//			return Response.ok().build();
//
//	}
//
//	@GET
//	@Path("/loginUser")
//	public Response logginUser(String plate, String email) {
//		persistentManager = persistentManagerFactory.getPersistenceManager();
//		transaction = persistentManager.currentTransaction();
//		PremiumUser u= null;
//		
//		try {
//			transaction.begin();
//
//			@SuppressWarnings("unchecked")
//			Query<User> userQuery = persistentManager
//					.newQuery("SELECT FROM " + User.class.getName() + " WHERE plate =='" + plate + "'");
//
//			userQuery.execute();
//
//			transaction.commit();
//		} catch (Exception ex) {
//			System.err.println("* Exception obtaining user data from DB: " + ex.getMessage());
//		} finally {
//			if (transaction.isActive()) {
//				transaction.rollback();
//			}
//
//			persistentManager.close();
//		}
//		return Response.ok(u).build();
//		
////		DBManager db = new DBManager();
////		PremiumUser userData = new PremiumUser();
////		userData.setEmail(email);
////		userData.setPlate(plate);
////		
////		db.getUser(userData.getPlate());
////		try {
////			tx.begin();
////			System.out.println("Checking if the user  exits or not: '" + userData.getPlate() + "'");
////			userData = null;
////			// User user = null;
////			try {
////				userData = pm.getObjectById(PremiumUser.class, userData.getPlate());
////			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
////				System.out.println("Exception launched: " + jonfe.getMessage());
////			}
////			System.out.println("User: " + userData);
////			if (userData != null) {
////				System.out.println("Setting email user: " + userData);
////				userData.setEmail(userData.getEmail());
////				System.out.println("Password set user: " + userData);
////			} else {
////				System.out.println("Creating user: " + userData);
////				userData = new PremiumUser();
////				pm.makePersistent(userData);
////				System.out.println("User created: " + userData.getEmail() + ", " + userData.getPlate());
////			}
////			tx.commit();
////			return Response.ok().build();
////		} finally {
////			if (tx.isActive()) {
////				tx.rollback();
////			}
////
////		}
//
//	}
//
//	// insert parking, insert slot, insertguestUser, create parkings (DE MOMENTO)
//
//	@GET
//	@Path("/hello")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response sayHello() {
//		return Response.ok("Hello world!").build();
//
//	}
	
	
	
}
