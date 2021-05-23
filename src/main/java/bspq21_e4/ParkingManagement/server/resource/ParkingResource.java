package bspq21_e4.ParkingManagement.server.resource;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import bspq21_e4.ParkingManagement.server.DAO.DBManager;
import bspq21_e4.ParkingManagement.server.data.Parking;

/**
 * @class ParkingResource Makes paths in order to communicate with the server
 *        side through the annotations which are in charge of making the
 *        requests regarding parkings
 * @see bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
 *      bspq21_e4.ParkingManagement.server.data.Parking
 * @author BSPQ21-E4
 */
@Path("parking")
public class ParkingResource {

	/**
	 * Makes the query to the server side to get the list of parkings from the
	 * database, that´s why we access @see
	 * bspq21_e4.ParkingManagement.server.DAO.DBManager and @see
	 * bspq21_e4.ParkingManagement.server.data.Parking
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Parking> getParkings() {
		List<Parking> parkings = DBManager.getInstance().getParkings();
		return parkings;

	}

	/**
	 * Makes the query to the server side to modify a parking in the database,
	 * that´s why we access @see bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * and @see bspq21_e4.ParkingManagement.server.data.Parking
	 */
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Parking modifyParking(Parking parking) {
		DBManager.getInstance().updateParking(parking);
		return parking;
	}

	/**
	 * Makes the query to the server side to insert the parking @see
	 * bspq21_e4.ParkingManagement.server.data.Parking
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Parking insertParking(Parking parking) {
		DBManager.getInstance().insertParking(parking);
		return parking;
	}

	/**
	 * Makes the query to the server side to delete the parking from the database,
	 * that´s why we access @see bspq21_e4.ParkingManagement.server.DAO.DBManager
	 * and @see bspq21_e4.ParkingManagement.server.data.Parking
	 */
	@DELETE
	@Path("delete/{parkingId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Parking deleteParking(Parking parking) {
		DBManager.getInstance().deleteParking(parking);
		return parking;
	}

}
