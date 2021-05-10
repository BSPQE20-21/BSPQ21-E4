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

public class ParkingRSH {
	static ParkingRSH instance = null;
    Client client;
    WebTarget target;

    public static ParkingRSH getInstance() {
        if (instance == null) {
            instance = new ParkingRSH();
        }
        return instance;
    }

    public ParkingRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("parking"); // http://localhost:8080/myapp/parking
    }


    public List<Parking> checkParkings() {
    	
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Parking> parkings = response.readEntity(new GenericType<List<Parking>>() { // Crear una lista de clientes
        });
        return parkings;
    }


    public Parking saveParking(Parking parking) {
    	Invocation.Builder ib = target.path("/insert").request();
//        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(parking, MediaType.APPLICATION_JSON));
        Parking parkingId = response.readEntity(Parking.class);
        return parkingId;
    }


    public Parking modifyParking(Parking parking) {
    	Invocation.Builder ib = target.path("/modify").request();

//        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(parking)).invoke();

        Parking parkingId = response.readEntity(Parking.class);
        return parkingId;
    }


    public void deleteParking(Parking parking) {
        Invocation.Builder ib = target.path("/delete/" + parking.getId()).request();
        ib.delete();
    }

}
