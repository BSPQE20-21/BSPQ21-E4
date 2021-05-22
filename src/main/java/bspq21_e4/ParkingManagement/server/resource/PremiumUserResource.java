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
	public PremiumUser modifyPremiumUser(PremiumUser user) {
		DBManager.getInstance().updatePremiumUser(user);;
		return user;
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public PremiumUser insertPremiumUser(PremiumUser user) {
		DBManager.getInstance().insertPremiumUser(user);;
		return user;	
	}
	
	@DELETE
    @Path("/delete/{userPlate}")
    @Produces(MediaType.TEXT_PLAIN)
	public String deletePremiumUser(@PathParam("userPlate") String user) {
		DBManager.getInstance().deletePremiumUser(user);
		return "Deleted";
	}

}
