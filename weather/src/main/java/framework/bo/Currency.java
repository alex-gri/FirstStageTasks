package framework.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"Cur_ID", "Cur_Scale", "Cur_Name"})
public class Currency {

    @JsonProperty ("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty ("Date")
    private String date;

    @JsonProperty ("Cur_OfficialRate")
    private Double officialRate;

    public Double getOfficialRate() {
        return officialRate;
    }

    public void setOfficialRate(Double officialRate) {
        this.officialRate = officialRate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date.substring(0, 10); // Length of date in format yyyy-MM-dd
    }

    @Override
    public String toString() {
        return "Currency{" +
                "abbreviation='" + abbreviation + '\'' +
                ", date='" + date + '\'' +
                ", officialRate=" + officialRate +
                '}';
    }
}
