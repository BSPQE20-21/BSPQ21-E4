package bspq21_e4.ParkingManagement.server.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bspq21_e4.ParkingManagement.server.DAO.*;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class PrepareData {

	public static void main(String[] args) {

		String[] parkingName = { "Bilbao", "Vitoria", "San Sebastian", "Santander", "LogroÃ±o", "Pamplona", "Gijon",
				"Madrid", "Barcelona", "Oviedo", "Burgos", "Valencia", "Sevilla", "Cadiz", "Almeria", "Murcia",
				"Salamanca", "Zamora", "Biarritz", "Salou", "Granada" };

//		for (int i = 0; i < parkingName.length; i++) {
//
//			DBManager.getInstance().insertParking(new Parking(i + 1, parkingName[i], 300, 300, 0, 3));
//		}

		int floor = 0;
		for (int i = 1; i < 300; i++) {

			if (i <= 100) {
				floor = 1;
			}

			else if (i > 100 && i <= 200) {
				floor = 2;
			}

			else if (i > 200 && i <= 300) {
				floor = 3;
			}

			for (int j = 0; i < parkingName.length; i++) {
				DBManager.getInstance().insertSlot(
						new Slot(i, floor, SlotAvailability.GREEN, new Parking(i + 1, parkingName[i], 300, 300, 0, 3)));
			}

		}

	}
}