package bspq21_e4.ParkingManagement.client.remote;



import java.rmi.Naming;


import bspq21_e4.ParkingManagement.server.remote.IRemoteFacade;

public class ServiceLocator {

	private IRemoteFacade service;

	public void setService(String ip, String port, String serverName) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String URL = "//" + ip + ":" + port + "/" + serverName;
			this.service = (IRemoteFacade) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println(" # Error locaing remote façade: " + ex);
			ex.printStackTrace();
		}
	}

	public IRemoteFacade getService() {
		return this.service;
	}
} 

