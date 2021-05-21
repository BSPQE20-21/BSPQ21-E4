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

		String[] parkingName = { "Bilbao", "Vitoria", "San Sebastian", "Santander", "Logrono", "Pamplona", "Gijon",
				"Madrid", "Barcelona", "Oviedo", "Burgos", "Valencia", "Sevilla", "Cadiz", "Almeria", "Murcia",
				"Salamanca", "Zamora", "Biarritz", "Salou", "Granada" };

		int floor = 0;
		int counter = 1;
		for (int i = 0; i < parkingName.length; i++) {
			Parking p = new Parking(i + 1, parkingName[i], 300, 300, 0, 3);
			DBManager.getInstance().insertParking(p);
			for (int j = 1; j <= 300; j++) {
				if (j < 100) {
					floor = 1;
				} else if (j < 200) {
					floor = 2;
				} else if (j < 300) {
					floor = 3;
				}
				DBManager.getInstance().insertSlot(new Slot(counter, j, floor, SlotAvailability.GREEN, p.getId()));
				// System.out.println("PK" + counter + "Id plaza " + j + "Planta " + floor +
				// "Nombre parking " + parkingName[i]);
				counter++;
			}

		}

	}
}