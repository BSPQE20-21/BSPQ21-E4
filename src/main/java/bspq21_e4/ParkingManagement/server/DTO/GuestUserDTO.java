package bspq21_e4.ParkingManagement.server.DTO;

import java.io.Serializable;
import java.util.Date;

public class GuestUserDTO extends UserDTO implements Serializable{
	
	Date entranceDate;

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	
	
	

}
