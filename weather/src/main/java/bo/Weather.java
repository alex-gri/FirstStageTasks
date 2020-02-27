package bo;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Weather {

    private String date;
    private Integer mintempC;
    private SortedSet<Float> precipMM;

    public Weather() {
        precipMM = new TreeSet<Float>(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
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

    public SortedSet<Float> getPrecipMM() {
        return precipMM;
    }

    public void addPrecipMM(Float precipMM) {
        this.precipMM.add(precipMM);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", mintempC=" + mintempC +
                ", precipMM=" + precipMM +
                '}';
    }
}
