package bspq21_e4.ParkingManagement.server.assembler;

import java.util.ArrayList;
import java.util.List;

import bspq21_e4.ParkingManagement.server.DTO.ParkingDTO;
import bspq21_e4.ParkingManagement.server.DTO.SlotDTO;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.Slot;

public class SlotAssembler {

	private static SlotAssembler instance;

	private SlotAssembler() {
	}

	public static SlotAssembler getInstance() {
		if (instance == null) {
			instance = new SlotAssembler();
		}

		return instance;
	}

	public SlotDTO entityToDTO(Slot slot) {
		SlotDTO dto = new SlotDTO();

		dto.setFloor(slot.getFloor());
		dto.setId(slot.getId());
		dto.setParking(ParkingAssembler.getInstance().entityToDTO(slot.getParking()));
		dto.setSl(slot.getSl());

		return dto;
	}

	public List<SlotDTO> entityToDTO(List<Slot> slots) {
		List<SlotDTO> dtos = new ArrayList<>();

		for (Slot slot : slots) {
			dtos.add(this.entityToDTO(slot));
		}

		return dtos;
	}

}
