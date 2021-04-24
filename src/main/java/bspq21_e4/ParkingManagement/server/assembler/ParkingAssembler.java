package bspq21_e4.ParkingManagement.server.assembler;

import java.util.ArrayList;
import java.util.List;

import bspq21_e4.ParkingManagement.server.DTO.ParkingDTO;
import bspq21_e4.ParkingManagement.server.data.Parking;



public class ParkingAssembler {
	private static ParkingAssembler instance;

	private ParkingAssembler() { }
	
	public static ParkingAssembler getInstance() {
		if (instance == null) {
			instance = new ParkingAssembler();
		}
		
		return instance;
	}

	public ParkingDTO entityToDTO(Parking parking) {
		ParkingDTO dto = new ParkingDTO();
		dto.setAvailableSlots(parking.getAvailableSlots());
		dto.setFloors(parking.getFloors());
		dto.setId(parking.getId());
		dto.setNombre(parking.getNombre());
		dto.setnSlots(parking.getnSlots());
		dto.setOccupiedSlots(parking.getOccupiedSlots());

		return dto;
	}

	public List<ParkingDTO> entityToDTO(List<Parking> parkings) {		
		List<ParkingDTO> dtos = new ArrayList<>();
		
		for (Parking parking : parkings) {
			dtos.add(this.entityToDTO(parking));
		}
		
		return dtos;
	}
}
