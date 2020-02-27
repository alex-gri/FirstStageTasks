package util;

import bo.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeatherUtil {
    public static boolean precipIsAboveZero(Weather weather) {
        for (Float precipMM : weather.getPrecipMM()) {
            if (precipMM != 0.0) return true;
        }
        return false;
    }

    public static Boolean isThursday(Weather weather) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            cal.setTime(formatter.parse(weather.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.get(Calendar.DAY_OF_WEEK) == 5;
    }
}
