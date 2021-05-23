package bspq21_e4.ParkingManagement.server.resource;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;

/**
 * @class PremiumUserResource Makes paths in order to communicate with the
 *        server side through the annotations which are in charge of making the
 *        requests regarding premium users
 * @see bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
 *      bspq21_e4.ParkingManagement.server.data.PremiumUser
 * @author BSPQ21-E4
 */
@Path("premiumUser")
public class PremiumUserResource {

	/**
	 * Makes the query to the server side to get the list of premium users from the
	 * database, that´s why we access @see
	 * bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
	 * bspq21_e4.ParkingManagement.server.data.PremiumUser
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PremiumUser> getPremiumUsers() {
		List<PremiumUser> users = DBManager.getInstance().getPremiumUsers();
		return users;

	}

	/**
	 * Makes the query to the server side to modify a premium user in the database,
	 * that´s why we access @see bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * and @see bspq21_e4.ParkingManagement.server.data.PremiumUser
	 */
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PremiumUser modifyPremiumUser(PremiumUser user) {
		DBManager.getInstance().updatePremiumUser(user);
		return user;
	}

	/**
	 * Makes the query to the server side to insert the premium user @see
	 * bspq21_e4.ParkingManagement.server.data.PremiumUser
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PremiumUser insertPremiumUser(PremiumUser user) {
		DBManager.getInstance().insertPremiumUser(user);
		return user;
	}

	/**
	 * Makes the query to the server side to delete the premium user from the
	 * database, that´s why we access @see
	 * bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * bspq21_e4.ParkingManagement.server.data.PremiumUser
	 */
	@DELETE
	@Path("/delete/{userPlate}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePremiumUser(@PathParam("userPlate") String user) {
		DBManager.getInstance().deletePremiumUser(user);
		return "Deleted";
	}

}
