package framework.util;

import framework.bo.Weather;
import framework.bo.WeatherHistory;
import framework.logger.Log;

import java.util.SortedSet;

import static framework.util.DateUtil.isThursday;

public class WeatherUtil {

    public static boolean precipIsAboveZero(Weather weather) {
        for (Float precipMM : weather.getPrecipMM()) {
            if (precipMM != 0.0) return true;
        }
        return false;
    }

    public static SortedSet<Weather> filterRainyThursdays(WeatherHistory historyOfAllWeather) {
        SortedSet<Weather> rainyThursdays = historyOfAllWeather.getWeather();
        Log.logAndReport("Removing everything but Thursdays from weather history");
        rainyThursdays.removeIf(weather -> !(isThursday(weather) &&
                                           weather.getMintempC() > 0 &&
                                           precipIsAboveZero(weather)));
        Log.logAndReport(rainyThursdays.size() + " Thursdays were filtered out of the parsed history");
        return rainyThursdays;
    }
}
