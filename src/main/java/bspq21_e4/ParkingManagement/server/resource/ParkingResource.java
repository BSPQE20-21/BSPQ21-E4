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
import bspq21_e4.ParkingManagement.server.data.Parking;

@Path("parking")
public class ParkingResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Parking> getParkings(){
		List<Parking> parkings = DBManager.getInstance().getParkings();
		return parkings;
		
	}
//	
//	
//	@Path("/modify")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//	public String modifyParking(Parking parking) {
//		DBManager.getInstance().updateParking(parking);
//		return "Parking updated";
//	}
//	
//	@PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//	public String insertParking(Parking parking) {
//		DBManager.getInstance().insertParking(parking);
//		return "Parking created";	
//	}
//	
//	@DELETE
//    @Path("delete/id/{parkingId}")
//    @Produces(MediaType.TEXT_PLAIN)
//	public String deleteParking(@PathParam("parkingId")Parking parking) {
//		DBManager.getInstance().deleteParking(parking);
//		return "Parking deleted";
//	}


}
