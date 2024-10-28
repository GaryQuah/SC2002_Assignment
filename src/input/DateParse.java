package input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParse {
    /** Static instance to the simple date formatter of a fixed string format */
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    /**
     * Returns a date object based on the string input for the date
     * @param source This is the string input for the date
     * @return Date
     */
    public static Date date(String source) {
        if (source.compareTo("-") == 0) return null; //inital login date = null
        try {
            return dateFormatter.parse(source);
        } catch (ParseException e) {
            System.out.println("Date format must be '" + dateFormatter.toPattern() + "'");
            return null;
        }
    }

    /**
     * Returns a string from a Date object input
     * @param date The date object input to convert
     * @return String
     */
    public static String string(Date date) {
        if (date == null) return "-"; //return a string of "-"
        return dateFormatter.format(date); //return a date in string format
    }
}