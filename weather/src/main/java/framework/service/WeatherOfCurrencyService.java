package framework.service;

import framework.bo.Currency;
import framework.bo.Weather;
import framework.logger.Log;
import org.decimal4j.util.DoubleRounder;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicReference;

import static framework.service.CurrencyService.getCurrencyByDate;
import static framework.util.DateUtil.getNextDayAfterDate;

public class WeatherOfCurrencyService {

    public static Map<Currency, Currency> mapRainyThursdaysToNextFridays(SortedSet<Weather> rainyThursdays) {
        Map<Currency, Currency> currenciesOfRainyThursdayAndNextFriday = new HashMap<>();
        Log.logAndReport("Getting currencies of Fridays after rainy Thursdays");
        for (Weather weather : rainyThursdays) {
            String thursday = weather.getDate();
            String friday = null;
            try {
                friday = getNextDayAfterDate(thursday);
            } catch (ParseException e) {
                Log.error(e.getMessage());
            }
            currenciesOfRainyThursdayAndNextFriday.put(getCurrencyByDate(thursday), getCurrencyByDate(friday));
        }
        return currenciesOfRainyThursdayAndNextFriday;
    }

    public static Double conclusionMessage(Map<Currency, Currency> currenciesOfRainyThursdayAndNextFriday) {
        Integer numberOfRainyThursdays = currenciesOfRainyThursdayAndNextFriday.size();
        Log.logAndReport("Rainy Thursdays found: " + numberOfRainyThursdays);

        AtomicReference<Integer> numberOfCasesOfGrownCurrencyRate =
                getNumberOfCasesOfGrownCurrencyRate(currenciesOfRainyThursdayAndNextFriday);

        Log.logAndReport("Number of them that made USD rate grow: " + numberOfCasesOfGrownCurrencyRate);
        Double percent = DoubleRounder.round(numberOfCasesOfGrownCurrencyRate.get().doubleValue() * 100 / numberOfRainyThursdays, 2);
        Log.logAndReport("It means " + percent + "% of cases are correct according to the hypothesis");
        return percent;
    }

    private static AtomicReference<Integer> getNumberOfCasesOfGrownCurrencyRate(Map<Currency, Currency> currenciesOfRainyThursdayAndNextFriday) {

        // Automatically generated code for forEach lambda expression.
        AtomicReference<Integer> numberOfCasesOfGrownCurrencyRate = new AtomicReference<>(0);
        currenciesOfRainyThursdayAndNextFriday.forEach((currencyThursday, currencyFriday) -> {
            if (currencyThursday.getOfficialRate() < currencyFriday.getOfficialRate()) {
                numberOfCasesOfGrownCurrencyRate.getAndSet(numberOfCasesOfGrownCurrencyRate.get() + 1);
            }
        });
        return numberOfCasesOfGrownCurrencyRate;
    }
}
