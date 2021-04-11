package bspq21_e4.ParkingManagement.server.main;

import java.rmi.Naming;
import java.rmi.Remote;

import bspq21_e4.ParkingManagement.server.remote.IRemoteFacade;
import bspq21_e4.ParkingManagement.server.remote.RemoteFacade;


public class App 
{
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemoteFacade remoteFacade = RemoteFacade.getInstance();
			Naming.rebind(name, (Remote) remoteFacade);
			IRemoteFacade rf= RemoteFacade.getInstance();
			Naming.rebind(name, rf);
			System.out.println(" * ParkingManagement Server '" + name + "' ha comenzado.");
			

		} catch (Exception ex) {
			System.err.println(" # ParkingManagement Server Excepccion: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
