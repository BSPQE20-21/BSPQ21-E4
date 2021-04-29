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

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.User;

public class GuestUserRSH {
	static GuestUserRSH instance = null;
    Client client;
    WebTarget target;

    public static GuestUserRSH getInstance() {
        if (instance == null) {
            instance = new GuestUserRSH();
        }
        return instance;
    }

    public GuestUserRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("user"); 
    }


    public List<GuestUser> checkUsers() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<GuestUser> users = response.readEntity(new GenericType<List<GuestUser>>() { 
        });
        return users;
    }


    public GuestUser saveGuestUsers(GuestUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        GuestUser userPlate = response.readEntity(GuestUser.class);
        return userPlate;
    }


    public GuestUser modifyGuestUser(GuestUser user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        GuestUser userPlate = response.readEntity(GuestUser.class);
        return userPlate;
    }


    public void deleteGuestUser(GuestUser user) {
        Invocation.Builder ib = target.path("/id/" + user.getPlate()).request();
        ib.delete();
    }
}
