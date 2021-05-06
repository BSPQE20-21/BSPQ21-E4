package bspq21_e4.ParkingManagement.server.data;

import java.util.ArrayList;
import java.util.List;

public class PremiumUserConnected {
	private static List<PremiumUser> users = new ArrayList<PremiumUser>();

	public static List<PremiumUser> getConnectedUsers() {
		return users;
	}

	public static void addConnectedUsers(PremiumUser u) {
		users.add(u);
	}

}
