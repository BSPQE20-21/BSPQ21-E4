package bspq21_e4.ParkingManagement.server.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Parking {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	int id;
	String nombre;
	int nSlots;
	int availableSlots;
	int occupiedSlots;
	int floors;

	public Parking(int id, String nombre,int nSlots, int availableSlots, int occupiedSlots, int floors) {
		this.id = id;
		this.nombre = nombre;
		this.nSlots = nSlots;
		this.availableSlots = availableSlots;
		this.occupiedSlots = occupiedSlots;
		this.floors = floors;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", nSlots=" + nSlots + ", availableSlots=" + availableSlots + ", occupiedSlots="
				+ occupiedSlots + ", floors=" + floors + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getnSlots() {
		return nSlots;
	}

	public void setnSlots(int nSlots) {
		this.nSlots = nSlots;
	}

	public int getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}

	public int getOccupiedSlots() {
		return occupiedSlots;
	}

	public void setOccupiedSlots(int occupiedSlots) {
		this.occupiedSlots = occupiedSlots;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	



}
