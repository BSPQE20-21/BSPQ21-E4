package bspq21_e4.ParkingManagement.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;





public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {
	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	
	private RemoteFacade() throws RemoteException {
		super();
	}
	
	public static RemoteFacade getInstance() {
		if (instance == null) {
			try {
				instance = new RemoteFacade();
			}catch (Exception ex) {
				System.err.println("# Error creando el RemoteFacade: " + ex);
			}
		}
		return instance;
	}

}