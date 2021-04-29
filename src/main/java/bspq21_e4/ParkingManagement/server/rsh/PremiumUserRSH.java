package bspq21_e4.ParkingManagement.server.rsh;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.User;

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
        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }


    public PremiumUser modifyPremiumUser(PremiumUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        PremiumUser userPlate = response.readEntity(PremiumUser.class);
        return userPlate;
    }


    public void deletePremiumUser(PremiumUser user) {
        Invocation.Builder ib = target.path("/id/" + user.getPlate()).request();
        ib.delete();
    }
}
