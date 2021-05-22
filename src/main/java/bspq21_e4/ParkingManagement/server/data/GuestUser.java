package bspq21_e4.ParkingManagement.server.data;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
	String plate;
	String paymentMethod;

	@ForeignKey(deleteAction = ForeignKeyAction.NONE)
	int slotPk;

	String entranceDate;

	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	public GuestUser(String plate, String entranceDate, int slotPk, String paymentMethod) {

		this.plate = plate;
		this.entranceDate = entranceDate;
		this.slotPk = slotPk;
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

	public int getSlotPk() {
		return slotPk;
	}

	public void setSlotPk(int slotPk) {
		this.slotPk = slotPk;
	}

	public String getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(String entranceDate) {
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
		return "GuestUser [plate=" + plate + ", entranceDate=" + entranceDate + ", selectedSlot=" + slotPk + "]";
	}

}
