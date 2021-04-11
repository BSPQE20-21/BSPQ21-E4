package bspq21_e4.ParkingManagement.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class JUnit {
	
	@Test
	public void Test() throws ParseException {
		int diferencia = 60;
		int totalMinutos = bspq21_e4.ParkingManagement.server.data.CalculateFee.getDifferenceBetwenDates(bspq21_e4.ParkingManagement.server.data.CalculateFee.sdfResult.parse("9:00"), bspq21_e4.ParkingManagement.server.data.CalculateFee.sdfResult.parse("10:00"));		
		double totalFee = 60 * 0.04;
		
		assertEquals(diferencia, totalMinutos);
		assertEquals(totalFee, bspq21_e4.ParkingManagement.server.data.CalculateFee.calculateFee(totalMinutos), 0);
		
	}

}
