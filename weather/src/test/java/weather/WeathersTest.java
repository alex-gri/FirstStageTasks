package weather;

import bo.Weather;
import parser.WeatherHandler;
import bo.WeatherHistory;
import org.testng.annotations.Test;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import org.xml.sax.SAXException;

public class WeathersTest {

    private final static String WEATHER_REQUEST = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=7582e2fe0f5546cbaad144219202402&q=Homyel&format=xml&date=2017-01-01&enddate=2018-01-01";

    @Test(description = "Checks hypothesis that USD/BYN rate had been rising after every Thursday’s rain in Gomel in 2017")
    public void getWeather() throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        WeatherHandler weatherHandler = new WeatherHandler();
        saxParser.parse(WEATHER_REQUEST, weatherHandler);
        WeatherHistory history = weatherHandler.getWeatherHistory();

        for (Weather weather : history.getWeather()) {
            System.out.println(weather);
        }
    }
}
