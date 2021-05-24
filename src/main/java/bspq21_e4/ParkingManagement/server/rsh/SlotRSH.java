package bspq21_e4.ParkingManagement.server.rsh;

import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import bspq21_e4.ParkingManagement.server.data.Slot;

/**
 * @class SlotRSH Class which allows obtaining information of the slots from the
 *        server side
 * @author BSPQ21-E4
 * @see bspq21_e4.ParkingManagement.server.data.Slot
 */
public class SlotRSH {
	static SlotRSH instance = null;
	Client client;
	WebTarget target;

	/**
	 * Creating singleton pattern
	 */
	public static SlotRSH getInstance() {
		if (instance == null) {
			instance = new SlotRSH();
		}
		return instance;
	}

	/**
	 * Class constructor
	 */
	public SlotRSH() {
		client = ClientBuilder.newClient();
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		target = client.target("http://localhost:8080/myapp").path("slot"); // http://localhost:8080/myapp/parking
	}

	
	/**
	 * Asks the server side for the list of slots stored in the Database
	 */
	public List<Slot> checkSlots() {
		Invocation.Builder ib = target.request(); // Construir la petición
		Response response = ib.get(); // Realizar una petición GET
		List<Slot> slots = response.readEntity(new GenericType<List<Slot>>() { // Crear una lista de clientes
		});
		return slots;
	}

	/**
	 * Asks the server side to store a slot in the Database
	 */
	public Slot saveSlots(Slot slots) {
		Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/insert").request();
		Response response = ib.put(Entity.entity(slots, MediaType.APPLICATION_JSON));
		Slot slotId = response.readEntity(Slot.class);
		return slotId;
	}

	/**
	 * Asks the server side to modify a slot in the Database
	 */
	public Slot modifySlot(Slot slot) {
		Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/modify").request();

		Response response = ib.build("PATCH", Entity.json(slot)).invoke();

		Slot slotId = response.readEntity(Slot.class);
		return slotId;
	}

//	/**
//	 * Asks the server side to delete a slot from the Database
//	 */
//	public void deleteSlot(Slot slot) {
//		Invocation.Builder ib = target.path("/delete/" + slot.getId()).request();
//		ib.delete();
//	}

}
