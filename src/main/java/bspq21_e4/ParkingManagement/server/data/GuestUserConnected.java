package bspq21_e4.ParkingManagement.server.data;

import java.util.ArrayList;
import java.util.List;

public class GuestUserConnected {
	private static List<GuestUser> users = new ArrayList<GuestUser>();

	public static List<GuestUser> getConnectedUsers() {
		return users;
	}

	public static void addConnectedUsers(GuestUser u) {
		users.add(u);
	}

}
