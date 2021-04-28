package bspq21_e4.ParkingManagement.client.main;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import org.glassfish.jersey.server.ResourceConfig;

import bspq21_e4.ParkingManagement.client.gui.AuthWindow;



public class ClientSide {
	
	
	
	
	public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     * 
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig().packages(true, "bspq21_e4", "bspq21_e4.ParkingManagement", "bspq21_e4.ParkingManagement.resource");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
 
    }

    /**
     * Main method.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Lanzar la ventana si se ejecuta desde el jar normal
        // Lanzar el servidor si se ejecuta desde la consola
    	
    	final ClientSide client = new ClientSide();
        if (args.length == 1 && args[0].equals("--server")) {
            // Para ejecturar el servidor " mvn exec:java -Dexec.args="--server" "
            // En power shell es " mvn exec:java "-Dexec.args='--server'" "
            // Si args esta vacio lanzamos el servidor

            final HttpServer server = startServer();
            System.out.println(String.format(
                    "Jersey app started with WADL available at " + "%sapplication.wadl\n Hit enter to stop it...",
                    BASE_URI));
            System.in.read();
            server.shutdownNow();
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        // VentanaCliente frame = new VentanaCliente();
                        AuthWindow frame = new AuthWindow(client);
                        frame.setVisible(true);
                    } catch (Exception e) {
                    }
                }
            });
        }
    }
	
	
	
	
	
	
	
//	private Client client;
//	private WebTarget webTarget;

//	public ClientSide(String hostname, String port) {
//		client = ClientBuilder.newClient();
//		System.out.println(String.format("http://%s:%s/rest/server", hostname, port));
//		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
//	}
//
//	public boolean registerUser(String email, String plate) {
//		WebTarget registerUserWebTarget = webTarget.path("register");
//		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
//		
//		System.out.println("about to call server side");
//		PremiumUser userData = new PremiumUser();
//		userData.setEmail(email);
//		userData.setPlate(plate);
//		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
//		if (response.getStatus() != Status.OK.getStatusCode()) {
//			System.out.println("Error connecting with the server. Code: " + response.getStatus());
//			return false;
//		} else {
//			System.out.println("User correctly registered");
//			return true;
//		}
//	}
//	
//	public boolean logginUser(String email, String plate ) {
//		WebTarget loginUserWebTarget = webTarget.path("loginUser");
//		Invocation.Builder invocationBuilder = loginUserWebTarget.request(MediaType.APPLICATION_JSON);
//		
//		PremiumUser userData = new PremiumUser();
//		userData.setEmail(email);
//		userData.setPlate(plate);
////		Response response = invocationBuilder.get(Entity.entity(userData, MediaType.APPLICATION_JSON));
//		Response response =invocationBuilder.get();
//		
//		if (response.getStatus() != Status.OK.getStatusCode()) {
//			System.out.println("Error connecting with the server. Code: " + response.getStatus());
//			return false;
//		} else {
//			System.out.println("User correctly logged");
//			return true;
//		}

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

//	public static void main(String[] args) {
//		if (args.length != 2) {
//			System.out.println("Use: java Client.Client [host] [port]");
//			System.exit(0);
//		}
//
//		String hostname = args[0];
//		String port = args[1];
//		
//	
//		final ClientSide example = new ClientSide(hostname, port);
////		PremiumUser pm = new PremiumUser();
////		example.registerUser(pm);
//		
//		EventQueue.invokeLater(new Runnable() {
//
//			@Override
//
//			public void run() {
//				try {
//					AuthWindow auth = new AuthWindow(example);					
//					auth.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//		});
//		
//		
//	}

