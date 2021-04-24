package bspq21_e4.ParkingManagement.server.assembler;

import bspq21_e4.ParkingManagement.server.DTO.GuestUserDTO;
import bspq21_e4.ParkingManagement.server.DTO.PremiumUserDTO;
import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;

public class PremiumUserAssembler {
	private static PremiumUserAssembler instance;

	private PremiumUserAssembler() { }

	public static PremiumUserAssembler getInstance() {
		if (instance == null) {
			instance = new PremiumUserAssembler();
		}

		return instance;
	}

	public PremiumUserDTO entityToDTO(PremiumUser user) {
		PremiumUserDTO dto = new PremiumUserDTO();

		dto.setEmail(user.getEmail());
		dto.setMonthfee(user.getMonthfee());
		dto.setPaymentMethod(user.getPaymentMethod());
		dto.setPlate(user.getPlate());
		dto.setSelectedSlot(user.getSelectedSlot());

		return dto;
	}
}
