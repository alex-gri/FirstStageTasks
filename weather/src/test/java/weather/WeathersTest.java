package weather;

import bo.Weather;
import io.restassured.RestAssured;
import parser.WeatherHandler;
import bo.WeatherHistory;
import org.testng.annotations.Test;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import java.util.SortedSet;

import org.xml.sax.SAXException;

import static util.WeatherUtil.isThursday;
import static util.WeatherUtil.precipIsAboveZero;

public class WeathersTest {

    private final static String WEATHER_REQUEST = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=7582e2fe0f5546cbaad144219202402&q=Homyel&format=xml&date=2018-09-01&enddate=2019-01-01";

    //API gives you information only about 35 days instead of whole year.
    @Test(description = "Checks hypothesis that USD/BYN rate had been rising after every Thursday’s rain in Gomel in [2018-09-01 | 2019-01-01]")
    public void getWeather() throws IOException, ParserConfigurationException, SAXException {
        RestAssured.get(WEATHER_REQUEST).then().log().all();
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        WeatherHandler weatherHandler = new WeatherHandler();
        saxParser.parse(WEATHER_REQUEST, weatherHandler);
        WeatherHistory history = weatherHandler.getWeatherHistory();

        for (Weather weather : history.getWeather()) {
            System.out.println(weather);
        }

        SortedSet<Weather> rainyThursdays = history.getWeather();
        rainyThursdays.removeIf(weather -> !(isThursday(weather) &&
                                           weather.getMintempC() > 0 &&
                                           precipIsAboveZero(weather)));
        System.out.println("-----------------------------");
        for (Weather weather : rainyThursdays) {
            System.out.println(weather);
        }
    }
}
