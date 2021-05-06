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

import bspq21_e4.ParkingManagement.server.data.User;

public class UserRSH {
	static UserRSH instance = null;
    Client client;
    WebTarget target;

    public static UserRSH getInstance() {
        if (instance == null) {
            instance = new UserRSH();
        }
        return instance;
    }

    public UserRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("user"); // http://localhost:8080/myapp/parking
    }


    public List<User> checkUsers() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<User> users = response.readEntity(new GenericType<List<User>>() { 
        });
        return users;
    }


    public User saveUsers(User user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(user, MediaType.APPLICATION_JSON));
        User userPlate = response.readEntity(User.class);
        return userPlate;
    }


    public User modifyUser(User user) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(user)).invoke();

        User userPlate = response.readEntity(User.class);
        return userPlate;
    }


    public void deleteUser(User user) {
        Invocation.Builder ib = target.path("/id/" + user.getPlate()).request();
        ib.delete();
    }
}
