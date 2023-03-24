package pl.orange.api;

import org.apache.log4j.Logger;
import pl.orange.model.City;
import pl.orange.model.Weather;


import java.util.*;


public class AcuuWeather1Day {

    private final static Logger LOGGER = Logger.getLogger(AcuuWeather1Day.class);

    private WeatherDataDeserialization acuuWeatherData;
    private final String apiKey = "mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC";
    private Weather weather;

    public AcuuWeather1Day(City city) {
        weather = new Weather(city);
        String cityKey = weather.getCity().getCityKey();
        if (!cityKey.equals("0")) {
            acuuWeatherData = new WeatherDataDeserialization(
                    new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                            + weather.getCity().getCityKey() + "?apikey= " + apiKey + "&language=pl&details=true&metric=true"));
            getTemperatureData();
            getWindData();
        } else {
            LOGGER.warn("CITY KEY PROBLEM");
        }

    }

    private void getTemperatureData() {

        var forecast = ((ArrayList<?>) acuuWeatherData.getApiData().get("DailyForecasts")).get(0);
        var temperature = ((LinkedHashMap) forecast).get("Temperature");
        var minimumTemperatureData = ((LinkedHashMap) temperature).get("Minimum");
        weather.setMinimalTemperature((double) ((LinkedHashMap) minimumTemperatureData).get("Value"));

        var maximumTemperatureData = ((LinkedHashMap) temperature).get("Maximum");
        weather.setMaximumTemperature((double) ((LinkedHashMap) maximumTemperatureData).get("Value"));


    }

    private void getWindData() {

        var forecast = ((ArrayList<?>) acuuWeatherData.getApiData().get("DailyForecasts")).get(0);
        var dayData = ((LinkedHashMap) forecast).get("Day");
        var dayWindData = ((LinkedHashMap) dayData).get("Wind");
        var windSpeedData = ((LinkedHashMap) dayWindData).get("Speed");
        weather.getWindDay().setSpeed((double) ((LinkedHashMap) windSpeedData).get("Value"));

        var windDirectionDayData = ((LinkedHashMap) dayWindData).get("Direction");
        weather.getWindDay().setDirection((((LinkedHashMap) windDirectionDayData).get("Localized")).toString());

        var nightData = ((LinkedHashMap) forecast).get("Night");
        var nightWindData = ((LinkedHashMap) nightData).get("Wind");
        var nightSpeedData = ((LinkedHashMap) nightWindData).get("Speed");
        weather.getWindNight().setSpeed((double) ((LinkedHashMap) nightSpeedData).get("Value"));

        var windDirectionNightData = ((LinkedHashMap) dayWindData).get("Direction");
        weather.getWindNight().setDirection((((LinkedHashMap) windDirectionNightData).get("Localized")).toString());
    }


    @Override
    public String toString() {
        return weather.getCity().getCityKey().equals("0") ?
                "Brak wczytanych danych" :
                weather.toString();
    }
}
