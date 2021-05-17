package bspq21_e4.ParkingManagement.server.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @class CalculateFee
 * Class to calculate the fee that each user has to pay
 * @author BSPQ21-E4
 */
public class CalculateFee {

public static SimpleDateFormat sdf = new SimpleDateFormat("h:mm", Locale.US);
public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);
public static SimpleDateFormat sdfResultMinutos = new SimpleDateFormat("m", Locale.US);
public static double standardFee = 0.04;


/**
 * Method which calculates the time between two given dates
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
 * @param minutes
 */
public static double calculateFee(int minutes) {

    double amount = minutes * standardFee;
    return amount;
}

public static void main(String[] args) throws ParseException {

    int differenceMin = getDifferenceBetwenDates(sdfResult.parse("09:00"), sdfResult.parse("16:00"));
    double amount = calculateFee(differenceMin);
    System.out.println(amount);
    


}
}