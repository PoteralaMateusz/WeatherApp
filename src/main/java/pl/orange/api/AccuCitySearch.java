package pl.orange.api;

import org.apache.log4j.Logger;
import pl.orange.model.City;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AccuCitySearch {

    private final static Logger LOGGER = Logger.getLogger(AccuCitySearch.class);

    private WeatherApiReader apiData;
    private final String apiKey1 = "dQHpG5soGU4z6IGMbSYdA7UQVA9JySmR";
    private City city;
    public boolean cityExist;

    public AccuCitySearch(String city) {
        this.city = new City();
        this.city.setName(URLEncoder.encode(city, StandardCharsets.UTF_8));
        this.apiData = new WeatherApiReader("http://dataservice.accuweather.com/locations/v1/cities/PL/search?apikey="
                + apiKey1 + "&q=" + this.city.getName());
        cityExisting();
    }

    public String cityName(){
        return city.getName();
    }

    public String cityKey(){
        return city.getCityKey();
    }


    private void cityExisting() {
        if (apiData.responseData().compareTo(new StringBuffer("[]")) != 0) {
            LOGGER.info("City: " + URLEncoder.encode(city.getName()) + " exist in weather API");
            cityExist = true;
            getCityKey();
        } else {
            cityExist = false;
            city.setCityKey("0");
            LOGGER.warn("City: " + URLEncoder.encode(city.getName()) + " don't exist in weather API");
        }
    }

    private void getCityKey() {
        apiData.setResponseData(new StringBuffer("{\"City\":" + apiData.responseData() + "}"));
        WeatherDataDeserialization dataDeserialization = new WeatherDataDeserialization(apiData);
        var cityData = ((ArrayList<?>) dataDeserialization.apiData.get("City")).get(0);
        city.setCityKey(((LinkedHashMap) cityData).get("Key").toString());

    }
}
