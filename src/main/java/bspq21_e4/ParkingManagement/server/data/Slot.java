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
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

/**
 * @class Slot
 * Class which defines slots
 * @author BSPQ21-E4
 */
public class Slot{
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	int id;
	int floor;
	SlotAvailability sl;
	
	 @ForeignKey(deleteAction = ForeignKeyAction.NONE)
	Parking parking;
	
	public Slot(int id, int floor, SlotAvailability sl, Parking parking) {
		this.id = id;
		this.floor = floor;
		this.sl = sl;
		this.parking = parking;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public int getId() {
	    return id;
	  }
	
	public void setId(int newId) {
	    this.id = newId;
	  }
	
	public int getFloor() {
	    return floor;
	  }
	
	public void setFloor(int newFloor) {
	    this.floor = newFloor;
	  }
	
	public SlotAvailability getSl() {
	    return sl;
	  }
	
	public void setSl(SlotAvailability newSl) {
	    this.sl = newSl;
	  }
	


	@Override
	public String toString() {
		return "Slot [id=" + id + ", floor=" + floor + ", sl=" + sl + ", parking=" + parking + "]";
	}


	
}
