package parser;


import bo.Hourly;
import bo.Weather;
import bo.WeatherHistory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WeatherHandler extends DefaultHandler {

    private static final String WEATHER = "weather";
    private static final String DATE = "date";
    private static final String MINTEMPC = "mintempC";
    private static final String HOURLY = "hourly";
    private static final String PRECIPMM = "precipMM";

    private WeatherHistory weatherHistory;
    private String elementValue;

    @Override
    public void startDocument() throws SAXException {
        weatherHistory = new WeatherHistory();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case WEATHER:
                weatherHistory.setWeather(new Weather());
                break;
            case HOURLY:
                weatherHistory.getLastWeather().setHourly(new Hourly());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case DATE:
                weatherHistory.getLastWeather().setDate(elementValue);
                break;
            case MINTEMPC:
                weatherHistory.getLastWeather().setMintempC(Integer.valueOf(elementValue));
                break;
            case PRECIPMM:
                weatherHistory.getLastWeather().getLastHourly().setPrecipMM(Float.parseFloat(elementValue));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length);
    }

    public WeatherHistory getWeatherHistory() {
        return weatherHistory;
    }
}
