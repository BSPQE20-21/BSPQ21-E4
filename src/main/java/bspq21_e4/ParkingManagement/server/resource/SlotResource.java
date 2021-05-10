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
import bspq21_e4.ParkingManagement.server.data.Slot;

@Path("slot")
public class SlotResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Slot> getSlots(){
		List<Slot> slots = DBManager.getInstance().getSlots();
		return slots;
		
	}
	
	
	@PATCH
	@Path("/modify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String modifySlot(Slot slot) {
		DBManager.getInstance().updateSlot(slot);
		return "Slot updated";
	}
	
	@PUT
	@Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String insertSlot(Slot slot) {
		DBManager.getInstance().insertSlot(slot);
		return "Slot created";	
	}
	
	@DELETE
    @Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSlot(Slot slot) {
		DBManager.getInstance().deleteSlot(slot);
		return "Slot deleted";
	}

}
