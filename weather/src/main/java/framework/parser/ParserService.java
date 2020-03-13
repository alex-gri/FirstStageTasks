package framework.parser;

import framework.bo.WeatherHistory;
import framework.logger.Log;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;

import static framework.config.Constants.WEATHER_REQUEST;

public class ParserService {

    public static WeatherHistory parseWeatherHistory() {
        WeatherHistory historyOfAllWeather = null;
        WeatherHandler weatherHandler = new WeatherHandler();
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            Log.logAndReport("Getting weather using SAX framework.parser...");
            saxParser = saxFactory.newSAXParser();
            saxParser.parse(WEATHER_REQUEST, weatherHandler);
            historyOfAllWeather = weatherHandler.getWeatherHistory();
            if (historyOfAllWeather.getWeather().size() != 0) {
                Log.logAndReport(historyOfAllWeather.getWeather().size() + " weather records were parsed successfully");
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Log.logAndReport(e.getMessage());
        }
        return historyOfAllWeather;
    }
}
