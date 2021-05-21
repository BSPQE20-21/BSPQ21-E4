package bspq21_e4.ParkingManagement.server.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

/**
 * @class GuestUser Class which defines guest users
 * @see bspq21_e4.ParkingManagement.server.data.User
 * @author BSPQ21-E4
 */
public class GuestUser {

	@PrimaryKey
	//@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	String plate;
	String paymentMethod;

	@ForeignKey(deleteAction = ForeignKeyAction.NONE)
	Slot selectedSlot;

	Date entranceDate;

	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	public GuestUser(String plate, Date entranceDate, Slot selectedSlot, String paymentMethod) {

		this.plate = plate;
		this.entranceDate = entranceDate;
		this.selectedSlot = selectedSlot;
		this.paymentMethod = paymentMethod;

	}

	public GuestUser() {

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

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public static SimpleDateFormat getSdfResult() {
		return sdfResult;
	}

	public static void setSdfResult(SimpleDateFormat sdfResult) {
		GuestUser.sdfResult = sdfResult;
	}

	@Override
	public String toString() {
		return "GuestUser [plate=" + plate + ", entranceDate=" + entranceDate + ", selectedSlot=" + selectedSlot + "]";
	}

}
