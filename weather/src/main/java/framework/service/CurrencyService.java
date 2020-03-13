package framework.service;

import framework.bo.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import framework.logger.Log;

import static framework.config.Constants.CURRENCY_REQUEST;

public class CurrencyService {

    public static Currency getCurrencyByDate(String date) {
        String formattedRequest = String.format(CURRENCY_REQUEST, date).trim();
        String result = RestAssured.
                given().contentType(ContentType.JSON).
                when().get(formattedRequest).asString();
        ObjectMapper mapper = new ObjectMapper();
        Currency currency = new Currency();
        try {
            currency = mapper.readValue(result, Currency.class);
        } catch (JsonProcessingException e) {
            Log.error(e.getMessage());
        }
        Log.logAndReport(currency.toString());
        return currency;
    }
}
