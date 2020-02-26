package bo;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class WeatherHistory {

    private SortedSet<Weather> weather;

    public WeatherHistory() {
        weather = new TreeSet<Weather>(new Comparator<Weather>() {
            @Override
            public int compare(Weather o1, Weather o2) {
                return 1;
            }
        });
    }

    public SortedSet<Weather> getWeather() {
        return weather;
    }

    public Weather getLastWeather() {
        return weather.last();
    }

    public void setWeather(Weather weather) {
        this.weather.add(weather);
    }

    @Override
    public String toString() {
        return "WeatherParser{" +
                "weather=" + weather +
                '}';
    }
}
