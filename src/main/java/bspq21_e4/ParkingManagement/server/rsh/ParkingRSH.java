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

import bspq21_e4.ParkingManagement.server.data.Parking;

/**
 * @class ParkingRSH Class which allows obtaining information of the parkings
 *        from the server side
 * @author BSPQ21-E4
 * @see bspq21_e4.ParkingManagement.server.data.Parking
 */
public class ParkingRSH {
	static ParkingRSH instance = null;
	Client client;
	WebTarget target;

	/**
	 * Creating singleton pattern
	 */
	public static ParkingRSH getInstance() {
		if (instance == null) {
			instance = new ParkingRSH();
		}
		return instance;
	}
	
	/**
	 * Class constructor
	 */
	public ParkingRSH() {
		client = ClientBuilder.newClient();
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		target = client.target("http://localhost:8080/myapp").path("parking"); // http://localhost:8080/myapp/parking
	}

	/**
	 * Asks the server side for the list of parkings stored in the Database
	 */
	public List<Parking> checkParkings() {

		Invocation.Builder ib = target.request(); // Construir la petición
		Response response = ib.get(); // Realizar una petición GET
		List<Parking> parkings = response.readEntity(new GenericType<List<Parking>>() { // Crear una lista de clientes
		});
		return parkings;
	}

	/**
	 * Asks the server side to store a parking in the Database
	 */
	public Parking saveParking(Parking parking) {
//    	Invocation.Builder ib = target.path("/insert").request();
		Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
		Response response = ib.put(Entity.entity(parking, MediaType.APPLICATION_JSON));
		Parking parkingId = response.readEntity(Parking.class);
		return parkingId;
	}

	/**
	 * Asks the server side to modify a parking in the Database
	 */
	public Parking modifyParking(Parking parking) {
//    	Invocation.Builder ib = target.path("/modify").request();
		Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
		Response response = ib.build("PATCH", Entity.json(parking)).invoke();

		Parking parkingId = response.readEntity(Parking.class);
		return parkingId;
	}

	/**
	 * Asks the server side to delete a parking from the Database
	 */
	public void deleteParking(Parking parking) {
		Invocation.Builder ib = target.path("/delete/" + parking.getId()).request();
		ib.delete();
	}

}
