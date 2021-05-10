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

public class PremiumUserRSH {
	static PremiumUserRSH instance = null;
    Client client;
    WebTarget target;

    public static PremiumUserRSH getInstance() {
        if (instance == null) {
            instance = new PremiumUserRSH();
        }
        return instance;
    }

    public PremiumUserRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("premiumUser"); 
    }


    public List<PremiumUser> checkPremiumUsers() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<PremiumUser> users = response.readEntity(new GenericType<List<PremiumUser>>() { 
        });
        return users;
    }


    public PremiumUser savePremiumUsers(PremiumUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/insert").request();

        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }


    public PremiumUser modifyPremiumUser(PremiumUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
//    	Invocation.Builder ib = target.path("/modify").request();    	
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }


    public void deletePremiumUser(PremiumUser user) {
        Invocation.Builder ib = target.path("/delete/" + user.getPlate()).request();
        ib.delete();
    }
}
