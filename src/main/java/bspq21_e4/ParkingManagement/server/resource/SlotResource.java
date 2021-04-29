package bspq21_e4.ParkingManagement.server.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String modifySlot(Slot slot) {
		DBManager.getInstance().updateSlot(slot);
		return "Slot updated";
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String insertSlot(Slot slot) {
		DBManager.getInstance().insertSlot(slot);
		return "Slot created";	
	}
	
	@DELETE
    @Path("/id/{slotId}")
    @Produces(MediaType.TEXT_PLAIN)
	public String deleteSlot(@PathParam("slotId")Slot slot) {
		DBManager.getInstance().deleteSlot(slot);
		return "Slot deleted";
	}

}
