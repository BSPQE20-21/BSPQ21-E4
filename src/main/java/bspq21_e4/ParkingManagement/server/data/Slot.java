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
    int pk;
	int id;
	int floor;
	SlotAvailability sl;
	
	@ForeignKey(deleteAction = ForeignKeyAction.NONE)
	int idParking;
	
	public Slot(int pk, int id, int floor, SlotAvailability sl, int idParking) {
		this.pk = pk;
		this.id = id;
		this.floor = floor;
		this.sl = sl;
		this.idParking = idParking;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getIdParking() {
		return idParking;
	}

	public void setIdParking(int idParking) {
		this.idParking = idParking;
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
		return "Slot [pk=" + pk + ", id=" + id + ", floor=" + floor + ", sl=" + sl + ", idParking=" + idParking + "]";
	}
	



	
}
