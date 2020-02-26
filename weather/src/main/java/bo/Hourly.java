package bo;

public class Hourly {

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
