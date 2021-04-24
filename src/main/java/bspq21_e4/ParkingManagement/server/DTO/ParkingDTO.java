package bspq21_e4.ParkingManagement.server.DTO;

import java.io.Serializable;

public class ParkingDTO implements Serializable{
	
	int id;
	String nombre;
	int nSlots;
	int availableSlots;
	int occupiedSlots;
	int floors;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
