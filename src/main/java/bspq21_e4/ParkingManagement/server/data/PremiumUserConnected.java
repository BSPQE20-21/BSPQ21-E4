package bspq21_e4.ParkingManagement.server.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @class PremiumUserConnected Creates a list of premium users 
 * and adds the logged premium users and gets them.
 * @author BSPQ21-E4
 */
public class PremiumUserConnected {
	private static List<PremiumUser> users = new ArrayList<PremiumUser>();

	public static List<PremiumUser> getConnectedUsers() {
		return users;
	}

	public static void addConnectedUsers(PremiumUser u) {
		users.add(u);
	}

}
