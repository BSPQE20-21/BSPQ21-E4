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


public class SlotRSH {
	static SlotRSH instance = null;
    Client client;
    WebTarget target;

    public static SlotRSH getInstance() {
        if (instance == null) {
            instance = new SlotRSH();
        }
        return instance;
    }

    public SlotRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("slot"); // http://localhost:8080/myapp/parking
    }


    public List<Slot> checkSlots() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Slot> slots = response.readEntity(new GenericType<List<Slot>>() { // Crear una lista de clientes
        });
        return slots;
    }


    public Slot saveSlots(Slot slots) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(slots, MediaType.APPLICATION_JSON));
        Slot slotId = response.readEntity(Slot.class);
        return slotId;
    }


    public Slot modifySlot(Slot slot) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(slot)).invoke();

        Slot slotId = response.readEntity(Slot.class);
        return slotId;
    }


    public void deleteSlot(Slot slot) {
        Invocation.Builder ib = target.path("/id/" + slot.getId()).request();
        ib.delete();
    }

}
