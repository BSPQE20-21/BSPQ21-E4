package bspq21_e4.ParkingManagement.server.data;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)

/**
 * @class PremiumUser
 * Class which defines premium users
 * @see bspq21_e4.ParkingManagement.server.data.User
 * @author BSPQ21-E4
 */
public class PremiumUser{
	
	String email;
	int monthfee;
	
	@PrimaryKey
	//@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	String plate;
	String paymentMethod;
	
	@ForeignKey(deleteAction = ForeignKeyAction.NONE)
	Slot selectedSlot;
	


	public PremiumUser(String email, String plate, int monthfee, Slot selectedSlot, String paymentMethod) {
		this.plate = plate;
		this.selectedSlot = selectedSlot;
		this.paymentMethod = paymentMethod;
		this.email = email;
		this.monthfee = monthfee;
	}
	
	public PremiumUser() {
		
	}



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

	@Override
	public String toString() {
		return "PremiumUser [email=" + email + ", plate=" + plate + ", monthfee=" + monthfee + ", selectedSlot="
				+ selectedSlot + "]";
	}

}
