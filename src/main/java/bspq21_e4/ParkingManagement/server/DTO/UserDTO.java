package bspq21_e4.ParkingManagement.server.DTO;

import java.io.Serializable;

import bspq21_e4.ParkingManagement.server.data.Slot;

public class UserDTO implements Serializable{
	String plate;
	String paymentMethod;
	Slot selectedSlot;
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Slot getSelectedSlot() {
		return selectedSlot;
	}
	public void setSelectedSlot(Slot selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
	
	

}
