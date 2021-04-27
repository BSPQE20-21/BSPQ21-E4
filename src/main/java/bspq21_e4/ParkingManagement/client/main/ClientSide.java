package bspq21_e4.ParkingManagement.client.main;

import java.awt.EventQueue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bspq21_e4.ParkingManagement.client.gui.AuthWindow;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;



public class ClientSide {
	private Client client;
	private WebTarget webTarget;

	public ClientSide(String hostname, String port) {
		client = ClientBuilder.newClient();
		System.out.println(String.format("http://%s:%s/rest/server", hostname, port));
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
	}

	public boolean registerUser(String email, String plate) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		System.out.println("about to call server side");
		PremiumUser userData = new PremiumUser();
		userData.setEmail(email);
		userData.setPlate(plate);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
			return false;
		} else {
			System.out.println("User correctly registered");
			return true;
		}
	}
	
	public boolean logginUser(String email, String plate ) {
		WebTarget loginUserWebTarget = webTarget.path("loginUser");
		Invocation.Builder invocationBuilder = loginUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		PremiumUser userData = new PremiumUser();
		userData.setEmail(email);
		userData.setPlate(plate);
//		Response response = invocationBuilder.get(Entity.entity(userData, MediaType.APPLICATION_JSON));
		Response response =invocationBuilder.get();
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
			return false;
		} else {
			System.out.println("User correctly logged");
			return true;
		}

//	public void sayMessage(String plate, String email, String message) {
//		WebTarget sayHelloWebTarget = webTarget.path("sayMessage");
//		Invocation.Builder invocationBuilder = sayHelloWebTarget.request(MediaType.APPLICATION_JSON);
//
//		DirectedMessage directedMessage = new DirectedMessage();
//		UserData userData = new UserData();
//		userData.setLogin(login);
//		userData.setPassword(password);
//
//		directedMessage.setUserData(userData);
//
//		MessageData messageData = new MessageData();
//		messageData.setMessage(message);
//		directedMessage.setMessageData(messageData);
//
//		Response response = invocationBuilder.post(Entity.entity(directedMessage, MediaType.APPLICATION_JSON));
//		if (response.getStatus() != Status.OK.getStatusCode()) {
//			System.out.println("Error connecting with the server. Code: " + response.getStatus());
//		} else {
//			String responseMessage = response.readEntity(String.class);
//			System.out.println("* Message coming from the server: '" + responseMessage + "'");
//		}
//	}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];
		
	
		final ClientSide example = new ClientSide(hostname, port);
//		PremiumUser pm = new PremiumUser();
//		example.registerUser(pm);
		
		EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {
				try {
					AuthWindow auth = new AuthWindow(example);					
					auth.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		
	}
}
