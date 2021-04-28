package bspq21_e4.ParkingManagement.server.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.GuestUser;

@Path("guestUser")
public class GuestUserResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GuestUser> getGuestUser(){
		List<GuestUser> users = DBManager.getInstance().getGuestUsers();
		return users;
		
	}
	
	
	@PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String modifyGuestUser(GuestUser user) {
		DBManager.getInstance().updateGuestUser(user);;
		return "Guest user updated";
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String insertGuestUser(GuestUser user) {
		DBManager.getInstance().insertGuestUser(user);;
		return "Guest user created";	
	}
	
	@DELETE
    @Path("/id/{userPlate}")
    @Produces(MediaType.TEXT_PLAIN)
	public String deleteGuestUser(@PathParam("userPlate")GuestUser user) {
		DBManager.getInstance().deleteGuestUser(user);
		return "Guest User deleted";
	}


}
