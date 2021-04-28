package bspq21_e4.ParkingManagement.server.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.User;

@Path("user")
public class UserResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		List<User> users = DBManager.getInstance().getUsers();
		return users;
		
	}
	
	

	
	
	
}
