package bspq21_e4.ParkingManagement.server.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)

/**
 * @class PremiumUser
 * Class which defines premium users
 * @see bspq21_e4.ParkingManagement.server.data.User
 * @author BSPQ21-E4
 */
public class PremiumUser extends User{
	
	String email;
	int monthfee;
	


	public PremiumUser(String email, String plate, int monthfee, Slot selectedSlot, String paymentMethod) {
		super(plate, selectedSlot, paymentMethod);
		this.email = email;
		this.monthfee = monthfee;
	}
	
	public PremiumUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getMonthfee() {
		return monthfee;
	}

	public void setMonthfee(int monthfee) {
		this.monthfee = monthfee;
	}

	public Slot getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(Slot newSelectedSlot) {
		this.selectedSlot = newSelectedSlot;
	}

	@Override
	public String toString() {
		return "PremiumUser [email=" + email + ", plate=" + plate + ", monthfee=" + monthfee + ", selectedSlot="
				+ selectedSlot + "]";
	}

}
