package bspq21_e4.ParkingManagement.server.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @class CalculateFee Class to calculate the fee that each user has to pay
 * @author BSPQ21-E4
 */
public class CalculateFee {

	public static SimpleDateFormat sdf = new SimpleDateFormat("h:mm", Locale.US);
	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
	public static SimpleDateFormat sdfResultMinutos = new SimpleDateFormat("m", Locale.US);
	public static double standardFee = 0.04;
	public static double dailyFee = 20.0;

	/**
	 * Method which calculates the time between two given dates
	 * 
	 * @param dateInicio
	 * @param dateFinal
	 */
	public static int getDifferenceBetwenDates(Date dateInicio, Date dateFinal) {
		long milliseconds = dateFinal.getTime() - dateInicio.getTime();
		int minutes = (int) ((milliseconds / 1000) / 60);
		System.out.println(minutes + " minutes");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, minutes);
		return minutes;
	}

	/**
	 * Method which calculates the the fee to pay out of the spent time
	 * 
	 * @param minutes
	 */
	public static double calculateFee(String entrada) {
		double amount;
		String[] separador = entrada.split(":");

		String hora = separador[0];
		String minuto = separador[1];
		String segundo = separador[2];

		float entradaMinutos = (Integer.parseInt(hora) * 60 + Integer.parseInt(minuto)
				+ (Integer.parseInt(segundo) / 60));

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

		LocalDateTime salida = LocalDateTime.now();
		String formatoSalida = dtf.format(salida);
		String[] separadorSalida = formatoSalida.split(":");
		String horaSalida = separadorSalida[0];
		String minutoSalida = separadorSalida[1];
		String segundoSalida = separadorSalida[2];

		float salidaMinutos = (Integer.parseInt(horaSalida) * 60 + Integer.parseInt(minutoSalida)
				+ (Integer.parseInt(segundoSalida) / 60));

		double minutes = salidaMinutos - entradaMinutos;

		if (minutes < 0) {
			amount = dailyFee;

		} else {
			amount = minutes * standardFee;
		}

		return amount;
	}

}