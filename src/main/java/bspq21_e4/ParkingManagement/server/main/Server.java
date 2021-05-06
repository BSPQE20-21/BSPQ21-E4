package bspq21_e4.ParkingManagement.server.main;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;



import bspq21_e4.ParkingManagement.client.gui.AuthWindow;


import java.util.ResourceBundle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server{

	public static final String BASE_URI = "http://127.0.0.1:8077/myapp/";
	public static Logger logger = LoggerFactory.getLogger(Server.class);

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {

		final ResourceConfig rc = new ResourceConfig().packages(true, "bspq21_e4", "bspq21_e4.ParkingManagement",
				"bspq21_e4.ParkingManagement.server", "bspq21_e4.ParkingManagement.server.resource");

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

		final ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());

		logger.info(resourceBundle.getString("client_side"));
		if (args.length == 1 && args[0].equals("--server")) {
			// Para ejecturar el servidor " mvn exec:java -Dexec.args="--server" "
			// En power shell es " mvn exec:java "-Dexec.args='--server'" "
			// Si args esta vacio lanzamos el servidor

			final HttpServer server = startServer();
			logger.info(resourceBundle.getString("server_on"));
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

						AuthWindow frame = new AuthWindow();
						frame.setVisible(true);
						logger.info(resourceBundle.getString("login_window_on"));
					} catch (Exception e) {
					}
				}
			});
		}
	}
}
