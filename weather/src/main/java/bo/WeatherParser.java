package bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Set;

@JacksonXmlRootElement(localName = "data")
@JsonIgnoreProperties({"request"})
public class WeatherParser {

    @JacksonXmlElementWrapper(localName="data")
    @JacksonXmlProperty(localName="weather")
    private Set<Weather> weather;

    @Override
    public String toString() {
        return "WeatherParser{" +
                "weather=" + weather +
                '}';
    }
}
