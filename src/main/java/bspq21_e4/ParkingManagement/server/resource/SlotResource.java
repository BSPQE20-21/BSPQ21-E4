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
	/**
	 * @class SlotResource Makes the query to the server side to get
	 * 		  the list of slots from the database, that´s why we access @see
	 *        bspq21_e4.ParkingManagement.server.DAO.DBManager  
	 *        and @see bspq21_e4.ParkingManagement.server.data.Slot
	 * @author BSPQ21-E4
	 */
	public List<Slot> getSlots(){
		List<Slot> slots = DBManager.getInstance().getSlots();
		return slots;
		
	}
	
	
	@PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	/**
	 * @class SlotResource Makes the query to the server side to get
	 * 		  the slot that has been modified in the process from the database,
	 * 		  that´s why we access @see
	 *        bspq21_e4.ParkingManagement.server.DAO.DBManager
	 *        and @see bspq21_e4.ParkingManagement.server.data.Slot
	 * @author BSPQ21-E4
	 */
	public Slot modifySlot(Slot slot) {
		DBManager.getInstance().updateSlot(slot);
		return slot;
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	/**
	 * @class SlotResource Makes the query to the server side to get
	 * 		  the slot @see bspq21_e4.ParkingManagement.server.data.Slot * @author BSPQ21-E4
	 */
	public Slot insertSlot(Slot slot) {
		DBManager.getInstance().insertSlot(slot);
		return slot;	
	}
	
	@DELETE
    @Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Slot deleteSlot(Slot slot) {
		DBManager.getInstance().deleteSlot(slot);
		return slot;
	}

}
