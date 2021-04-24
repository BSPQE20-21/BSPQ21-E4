package bspq21_e4.ParkingManagement.server.assembler;

import bspq21_e4.ParkingManagement.server.DTO.GuestUserDTO;
import bspq21_e4.ParkingManagement.server.data.GuestUser;

public class GuestUserAssembler {
	
	private static GuestUserAssembler instance;

	private GuestUserAssembler() {
	}

	public static GuestUserAssembler getInstance() {
		if (instance == null) {
			instance = new GuestUserAssembler();
		}

		return instance;
	}

	public GuestUserDTO entityToDTO(GuestUser user) {
		GuestUserDTO dto = new GuestUserDTO();

		dto.setEntranceDate(user.getEntranceDate());
		dto.setPaymentMethod(user.getPaymentMethod());
		dto.setPlate(user.getPlate());
		dto.setSelectedSlot(user.getSelectedSlot());

		return dto;
	}

}
