package mg.s5s3.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
    public static void verifyStringNotNullOrEmpty (String toVerify, String attributeName) throws Exception{
        if (toVerify == null || toVerify.isEmpty()) {
            throw new Exception("la valeur : "+ attributeName + " ne peut pas etre vide ");
        }
    }

    public static void verifyObjectNotNull (Object toVerify, String attributeName) throws Exception{
        if (toVerify == null) {
            throw new Exception("la valeur : "+ attributeName + " ne peut pas etre vide ");
        }
    }

    public static void verifyNumericPostive (double toVerify , String attributeName) throws Exception{
        if (toVerify < 0) {
            throw new Exception("la valeur : "+ attributeName + " ne peut pas etre negative ");
        }
    }

    public static Timestamp convertTimestampFromHtmlInput(String input) throws Exception {
        try {
            String dateTime = input.replace("T", " ");
            Timestamp rep = Timestamp.valueOf(dateTime);
            return rep;
        } catch (Exception e1) {
            try {
                String dateTime = input.replace("T", " ") + ":00";
                Timestamp rep = Timestamp.valueOf(dateTime);
                return rep;
            } catch (Exception e2) {
                try {
                    // Si format date-heure échoue, essai de format date seul
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(input);
                    return new Timestamp(parsedDate.getTime());
                } catch (ParseException e3) {
                    throw new Exception("Format de date invalide. Utilisez 'yyyy-MM-dd HH:mm:ss' ou 'yyyy-MM-dd'.", e3);
                }
            }
        }
    }

    public static java.sql.Date convertDateFromHtmlInput(String input) throws Exception {
        try {
            // Si format date-heure échoue, essai de format date seul
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(input);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e3) {
            throw new Exception("Format de date invalide. Utilisez 'yyyy-MM-dd HH:mm:ss' ou 'yyyy-MM-dd'.", e3);
        }
    }

    public static Time convertTimeFromHtmlInput (String input){
        return java.sql.Time.valueOf(input);
    }

    public static int convertIntFromHtmlInput (String input){
        return Integer.parseInt(input);
    }

    public static double convertDoubleFromHtmlInput (String input){
        return Double.parseDouble(input);
    }


    public static boolean convertBooleanFromCheckBox (String input){
        return input  == null ? false : true ;
    }

}
