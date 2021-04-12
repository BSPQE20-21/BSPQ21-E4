package bspq21_e4.ParkingManagement.server.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class GuestUser extends User {

	Date entranceDate;

	
	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	
	public GuestUser(String plate, Date entranceDate, Slot selectedSlot, String paymentMethod) {
		super(plate, selectedSlot, paymentMethod);
		
		this.entranceDate = entranceDate;
		
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public Slot getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(Slot selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
	
	

	@Override
	public String toString() {
		return "GuestUser [plate=" + plate + ", entranceDate=" + entranceDate + ", selectedSlot=" + selectedSlot + "]";
	}

	
}
