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
import bspq21_e4.ParkingManagement.server.data.PremiumUser;

@Path("premiumUser")
public class PremiumUserResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PremiumUser> getPremiumUsers(){
		List<PremiumUser> users = DBManager.getInstance().getPremiumUsers();
		return users;
		
	}
	
	
	@PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String modifyPremiumUser(PremiumUser user) {
		DBManager.getInstance().updatePremiumUser(user);;
		return "User updated";
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String insertPremiumUser(PremiumUser user) {
		DBManager.getInstance().insertPremiumUser(user);;
		return "Premium user created";	
	}
	
	@DELETE
    @Path("/id/{userPlate}")
    @Produces(MediaType.TEXT_PLAIN)
	public String deletePremiumUser(@PathParam("userPlate")PremiumUser user) {
		DBManager.getInstance().deletePremiumUser(user);
		return "Premium User deleted";
	}

}
