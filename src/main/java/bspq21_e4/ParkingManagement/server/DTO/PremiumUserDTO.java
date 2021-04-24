package bspq21_e4.ParkingManagement.server.DTO;

import java.io.Serializable;


public class PremiumUserDTO extends UserDTO implements Serializable {
	
	String email;
	int monthfee;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMonthfee() {
		return monthfee;
	}
	public void setMonthfee(int monthfee) {
		this.monthfee = monthfee;
	}
	
	
}
