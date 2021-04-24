package bspq21_e4.ParkingManagement.server.assembler;

import bspq21_e4.ParkingManagement.server.DTO.UserDTO;
import bspq21_e4.ParkingManagement.server.data.User;


public class UserAssembler {
	
	private static UserAssembler instance;

	private UserAssembler() { }
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}

	public UserDTO entityToDTO(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setPaymentMethod(user.getPaymentMethod());
		dto.setPlate(user.getPlate());
		dto.setSelectedSlot(user.getSelectedSlot());

		return dto;
	}

}
