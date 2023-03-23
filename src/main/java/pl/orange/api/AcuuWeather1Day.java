package pl.orange.api;

import org.apache.log4j.Logger;
import pl.orange.model.City;
import pl.orange.model.Data;
import pl.orange.model.Weather;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class AcuuWeather1Day {

    private final static Logger LOGGER = Logger.getLogger(AcuuWeather1Day.class);

    private WeatherDataDeserialization acuuWeatherData;
    private final String apiKey1 = "dQHpG5soGU4z6IGMbSYdA7UQVA9JySmR";
    private final String apiKey2 = "mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC";
    private Weather weather;

    public AcuuWeather1Day(City city) {
        weather = new Weather(city);
        if (!weather.getCity().getCityKey().contains("0")) {
            acuuWeatherData = new WeatherDataDeserialization(
                    new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                            + weather.getCity().getCityKey() + "?apikey= " + apiKey2 + "&language=pl&details=true&metric=true"));
            getTemperatureData();
            getWindData();
        } else {
            LOGGER.warn("API CONNECTION ERROR OR CITY DON'T EXIST");
        }

    }

    private void getTemperatureData() {

        var forecast = ((ArrayList<?>) acuuWeatherData.getApiData().get("DailyForecasts")).get(0);
        var temperature = ((LinkedHashMap) forecast).get("Temperature");
        var minimumTemperatureData = ((LinkedHashMap) temperature).get("Minimum");
        weather.setMinimalTemperature( (double) ((LinkedHashMap) minimumTemperatureData).get("Value") );

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
//        return cityCode != 0 ?
//                new StringBuilder("Aktualne dane pogodowe dla miasta " + URLDecoder.decode(cityName, StandardCharsets.UTF_8))
//                        .append(": \n")
//                        .append("Temperatura minimalna " + minimalTemperature + " °C, maksymalna " + maximumTemperature + " °C. \n")
//                        .append("Wiatr podczas dnia: " + windSpeedDay + " km/h, kierunek: " + windDirectionDay + ". \n")
//                        .append("Wiatr w nocy: " + windSpeedNight + " km/h, kierunek: " + windDirectionNight + ". \n")
//                        .toString() :
//                "Brak wczytanych danych";
        return weather.toString();
    }
}
