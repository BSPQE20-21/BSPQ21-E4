package bspq21_e4.ParkingManagement.server.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public abstract class User {
	@PrimaryKey
	String plate;
	String paymentMethod;
	Slot selectedSlot;
	public User(String plate, Slot selectedSlot, String paymentMethod) {
		this.plate = plate;
		this.selectedSlot = selectedSlot;
		this.paymentMethod = paymentMethod;
	}
	public User() {
		
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public Slot getSelectedSlot() {
		return selectedSlot;
	}
	public void setSelectedSlot(Slot selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
	

}
