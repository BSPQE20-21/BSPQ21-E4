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
