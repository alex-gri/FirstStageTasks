package bo;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Weather {

    private String date;

    private Integer mintempC;

    private SortedSet<Hourly> hourly;

    public Weather() {
        hourly = new TreeSet<Hourly>(new Comparator<Hourly>() {
            @Override
            public int compare(Hourly o1, Hourly o2) {
                return 1;
            }
        });
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMintempC() {
        return mintempC;
    }

    public void setMintempC(Integer mintempC) {
        this.mintempC = mintempC;
    }

    public SortedSet<Hourly> getHourly() {
        return hourly;
    }

    public Hourly getLastHourly() {
        return hourly.last();
    }

    public void setHourly(Hourly hourly) {
        this.hourly.add(hourly);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", mintempC=" + mintempC +
                ", hourly=" + hourly +
                '}';
    }
}
