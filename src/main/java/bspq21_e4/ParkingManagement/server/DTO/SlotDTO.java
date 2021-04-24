package bspq21_e4.ParkingManagement.server.DTO;

import java.io.Serializable;

import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class SlotDTO implements Serializable{

	int id;
	int floor;
	SlotAvailability sl;
	ParkingDTO parking;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public SlotAvailability getSl() {
		return sl;
	}

	public void setSl(SlotAvailability sl) {
		this.sl = sl;
	}

	public ParkingDTO getParking() {
		return parking;
	}

	public void setParking(ParkingDTO parking) {
		this.parking = parking;
	}



}
