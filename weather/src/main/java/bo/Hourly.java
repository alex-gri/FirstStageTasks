package bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "hourly")
@JsonIgnoreProperties({"time", "tempC", "tempF", "windspeedMiles", "windspeedKmph", "winddirDegree", "winddir16Point",
"weatherCode", "weatherIconUrl", "weatherDesc", "precipInches", "humidity", "visibility", "visibilityMiles", "pressure",
"pressureInches", "cloudcover", "HeatIndexC", "HeatIndexF", "DewPointC", "DewPointF", "WindChillC", "WindChillF",
"WindGustMiles", "WindGustKmph", "FeelsLikeC", "FeelsLikeF", "uvIndex"})
public class Hourly {

    @JacksonXmlProperty(localName="precipMM")
    private Float precipMM;

    public Float getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(Float precipMM) {
        this.precipMM = precipMM;
    }

    @Override
    public String toString() {
        return "Hourly{" +
                "precipMM=" + precipMM +
                '}';
    }
}
