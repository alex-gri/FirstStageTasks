package bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Set;

@JacksonXmlRootElement(localName = "weather")
@JsonIgnoreProperties({"astronomy", "maxtempC", "maxtempF", "mintempF", "uvIndex", "totalSnow_cm", "sunHour",
"avgtempC", "avgtempF"})
public class Weather {

    @JacksonXmlProperty(localName = "date")
    private String date;

    @JacksonXmlProperty(localName = "mintempC")
    private Integer mintempC;

    @JacksonXmlElementWrapper(localName="hourly")
    private Set<Hourly> hourly;

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

    public Set<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(Set<Hourly> hourly) {
        this.hourly = hourly;
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
