package framework.util;

import framework.bo.Weather;
import framework.logger.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {

    public static String getNextDayAfterDate(String thursday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(thursday));
        calendar.add(Calendar.DATE, 1);
        return sdf.format(calendar.getTime());
    }

    public static Boolean isThursday(Weather weather) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            cal.setTime(formatter.parse(weather.getDate()));
        } catch (ParseException e) {
            Log.error(e.getMessage());
        }
        return cal.get(Calendar.DAY_OF_WEEK) == 5; // 1 - Sunday, 5 - Thursday
    }
}
