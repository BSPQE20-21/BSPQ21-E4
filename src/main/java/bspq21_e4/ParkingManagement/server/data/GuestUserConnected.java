package bspq21_e4.ParkingManagement.server.data;

import java.util.ArrayList;

import java.util.List;

public class GuestUserConnected {
	/**
	 * @class GuestUserConnected Creates a list of guest users 
	 * and adds the logged guest users and gets them.
	 * @author BSPQ21-E4
	 */
	private static List<GuestUser> users = new ArrayList<GuestUser>();

	public static List<GuestUser> getConnectedUsers() {
		return users;
	}

	public static void addConnectedUsers(GuestUser u) {
		users.add(u);
	}

}
