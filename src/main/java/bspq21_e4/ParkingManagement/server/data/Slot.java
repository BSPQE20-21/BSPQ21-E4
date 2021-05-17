package bspq21_e4.ParkingManagement.server.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(detachable = "true")

/**
 * @class Slot
 * Class which defines slots
 * @author BSPQ21-E4
 */
public class Slot{
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int id;
	int floor;
	SlotAvailability sl;
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
	
	public static void main(String[] args) {
		Parking p = new Parking(1,"Bilbao",100, 100, 0, 1);
		Slot s = new Slot(1, 1, SlotAvailability.GREEN,p);
		System.out.println(s.toString());
		System.out.println(s.getSl());
		s.setSl(SlotAvailability.RED);
		System.out.println(s.toString());
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", floor=" + floor + ", sl=" + sl + ", parking=" + parking + "]";
	}


	
}
