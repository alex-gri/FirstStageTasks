package test;

import framework.bo.Currency;
import framework.bo.Weather;
import framework.parser.ParserService;
import framework.bo.WeatherHistory;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.SortedSet;
import framework.service.WeatherOfCurrencyService;
import framework.util.WeatherUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.lessThan;

public class WeatherTest {

    //API gives you information only about 35 days instead of whole year.
    @Test(description = "Checks hypothesis that USD/BYN rate had been rising after every Thursday’s rain in Gomel in [2018-09-01 | 2019-01-01]")
    public void currencyRateGrowsAfterThursdayRainTest() {
        WeatherHistory historyOfAllWeather = ParserService.parseWeatherHistory();
        SortedSet<Weather> rainyThursdays = WeatherUtil.filterRainyThursdays(historyOfAllWeather);
        Map<Currency, Currency> currenciesOfRainyThursdayAndNextFriday = WeatherOfCurrencyService
                .mapRainyThursdaysToNextFridays(rainyThursdays);
        Double hypothesisSuccessRate = WeatherOfCurrencyService.conclusionMessage(currenciesOfRainyThursdayAndNextFriday);

        assertThat("Hyphothesis is proven in less than 50% of cases",
                hypothesisSuccessRate, is(not(lessThan(50.0))));
    }
}
