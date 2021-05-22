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
	public GuestUser modifyGuestUser(GuestUser user) {
		DBManager.getInstance().updateGuestUser(user);;
		return user;
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public GuestUser insertGuestUser(GuestUser user) {
		DBManager.getInstance().insertGuestUser(user);;
		return user;	
	}
	
	@DELETE
    @Path("/delete/{userPlate}")
    @Produces(MediaType.TEXT_PLAIN)
	public String deleteGuestUser(@PathParam("userPlate") String user) {
		DBManager.getInstance().deleteGuestUser(user);
		return "Deleted";
	}
	
}
