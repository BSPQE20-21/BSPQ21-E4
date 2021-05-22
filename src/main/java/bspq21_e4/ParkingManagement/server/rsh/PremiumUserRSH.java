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

import bspq21_e4.ParkingManagement.server.data.PremiumUser;


/**
 * @class PremiumUserRSH Class which allows obtaining information of the premium users from the
 *        server side
 * @author BSPQ21-E4
 * @see bspq21_e4.ParkingManagement.server.data.PremiumUser
 */
public class PremiumUserRSH {
	static PremiumUserRSH instance = null;
    Client client;
    WebTarget target;

	/**
	 * Creating singleton pattern
	 */
    public static PremiumUserRSH getInstance() {
        if (instance == null) {
            instance = new PremiumUserRSH();
        }
        return instance;
    }

	/**
	 * Class constructor
	 */
    public PremiumUserRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("premiumUser"); 
    }

	/**
	 * Asks the server side for the list of premium users stored in the Database
	 */
    public List<PremiumUser> checkPremiumUsers() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<PremiumUser> users = response.readEntity(new GenericType<List<PremiumUser>>() { 
        });
        return users;
    }

	/**
	 * Asks the server side to store a premium user in the Database
	 */
    public PremiumUser savePremiumUsers(PremiumUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/insert").request();

        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }

	/**
	 * Asks the server side to modify a premium user in the Database
	 */
    public PremiumUser modifyPremiumUser(PremiumUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/modify").request();    	
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }

	/**
	 * Asks the server side to delete a premium user from the Database
	 */
    public void deletePremiumUser(PremiumUser u) {
        Invocation.Builder ib = target.path("/delete/" + u.getPlate()).request();
        ib.delete();
    }
}
