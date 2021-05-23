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
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import jakarta.ws.rs.PATCH;

/**
 * @class GuestUserResource Makes paths in order to communicate with the server
 *        side through the annotations which are in charge of making the
 *        requests regarding guest users
 * @see bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
 *      bspq21_e4.ParkingManagement.server.data.GuestUser
 * @author BSPQ21-E4
 */
@Path("guestUser")
public class GuestUserResource {

	/**
	 * Makes the query to the server side to get the list of guest users from the
	 * database, that´s why we access @see
	 * bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
	 * bspq21_e4.ParkingManagement.server.data.GuestUser
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GuestUser> getGuestUser() {
		List<GuestUser> users = DBManager.getInstance().getGuestUsers();
		return users;

	}

	/**
	 * Makes the query to the server side to modify a user, that´s why we
	 * access @see bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
	 * bspq21_e4.ParkingManagement.server.data.GuestUser
	 */
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GuestUser modifyGuestUser(GuestUser user) {
		DBManager.getInstance().updateGuestUser(user);
		;
		return user;
	}

	/**
	 * Makes the query to the server side to insert the guest user. @see
	 * bspq21_e4.ParkingManagement.server.data.GuestUser
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GuestUser insertGuestUser(GuestUser user) {
		DBManager.getInstance().insertGuestUser(user);
		;
		return user;
	}

	/**
	 * Makes the query to the server side to delete a guest user from the database,
	 * that´s why we access @see bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * and @see bspq21_e4.ParkingManagement.server.data.GuestUser
	 */
	@DELETE
	@Path("/delete/{userPlate}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteGuestUser(@PathParam("userPlate") String user) {
		DBManager.getInstance().deleteGuestUser(user);
		return "Deleted";
	}

}
