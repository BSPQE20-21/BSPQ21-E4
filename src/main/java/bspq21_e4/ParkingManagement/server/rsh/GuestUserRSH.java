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

import bspq21_e4.ParkingManagement.server.data.GuestUser;

/**
 * @class ParkingRSH Class which allows obtaining information of the parkings
 *        from the server side
 * @author BSPQ21-E4
 * @see import bspq21_e4.ParkingManagement.server.data.GuestUser;
 */
public class GuestUserRSH {
	static GuestUserRSH instance = null;
    Client client;
    WebTarget target;

	/**
	 * Creating singleton pattern
	 */
    public static GuestUserRSH getInstance() {
        if (instance == null) {
            instance = new GuestUserRSH();
        }
        return instance;
    }

	/**
	 * Class constructor
	 */
    public GuestUserRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("guestUser"); 
    }

	/**
	 * Asks the server side for the list of guest users stored in the Database
	 */
    public List<GuestUser> checkGuestUsers() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<GuestUser> users = response.readEntity(new GenericType<List<GuestUser>>() { 
        });
        return users;
    }


	/**
	 * Asks the server side to store a guest user in the Database
	 */
    public GuestUser saveGuestUsers(GuestUser user) {
//    	Invocation.Builder ib = target.path("/insert").request();

        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        GuestUser userPlate = response.readEntity(GuestUser.class);
        return userPlate;
        

    }

	/**
	 * Asks the server side to modify a guest user in the Database
	 */
    public GuestUser modifyGuestUser(GuestUser user) {
//    	Invocation.Builder ib = target.path("/modify").request();

        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        GuestUser userPlate = response.readEntity(GuestUser.class);
        return userPlate;
    }

	/**
	 * Asks the server side to delete a guest user from the Database
	 */
    public void deleteGuestUser(GuestUser user) {
        Invocation.Builder ib = target.path("/delete/" + user.getPlate()).request();
        ib.delete();
    }
}
