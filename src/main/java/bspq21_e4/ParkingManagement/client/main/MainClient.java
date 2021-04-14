package bspq21_e4.ParkingManagement.client.main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bspq21_e4.ParkingManagement.server.data.PremiumUser;



public class MainClient {
	private Client client;
	private WebTarget webTarget;

	public MainClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
	}

	public void registerUser(String plate, String email) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		PremiumUser userData = new PremiumUser();
		userData.setPlate(plate);
		userData.setEmail(email);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("User correctly registered");
		}
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

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		MainClient example = new MainClient(hostname, port);
		example.registerUser("8184DFF", "jonmaeztu@opendeusto.es");
		
	}
}
