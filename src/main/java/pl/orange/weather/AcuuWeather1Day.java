package pl.orange.weather;

import org.apache.log4j.Logger;


import java.util.*;


public class AcuuWeather1Day {

    private final static Logger LOGGER = Logger.getLogger(AcuuWeather1Day.class);

    private WeatherDataDeserialization acuuWeatherData;
    private final String apiKey1 = "dQHpG5soGU4z6IGMbSYdA7UQVA9JySmR";
    private final String apiKey2 = "mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC";
    private final int cityID;
    private double minimalTemperature;
    private double maximumTemperature;
    private double windSpeedDay;
    private String windDirectionDay;
    private double windSpeedNight;
    private String windDirectionNight;

    public AcuuWeather1Day(int cityID) {
        this.cityID = cityID;
        if (this.cityID != 0) {
            acuuWeatherData = new WeatherDataDeserialization(
                    new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                            + cityID + "?apikey= " + apiKey2 + "&language=pl&details=true&metric=true"));
            getTemperatureData();
            getWindData();
        }else {
            LOGGER.warn("API CONNECTION ERROR OR CITY DON'T EXIST");
        }

    }

    private void getTemperatureData() {
        var forecast = ((ArrayList<?>) acuuWeatherData.getApiData().get("DailyForecasts")).get(0);
        var temperature = ((LinkedHashMap) forecast).entrySet().toArray()[4];
        temperature = ((Map.Entry) temperature).getValue();
        var minimumTemperatureData = ((Map.Entry) ((LinkedHashMap) temperature).entrySet().toArray()[0]).getValue();
        minimalTemperature = (double) ((Map.Entry) ((LinkedHashMap) minimumTemperatureData).entrySet().toArray()[0]).getValue();

        var maximumTemperatureData = ((Map.Entry) ((LinkedHashMap) temperature).entrySet().toArray()[1]).getValue();
        maximumTemperature = (double) ((Map.Entry) ((LinkedHashMap) maximumTemperatureData).entrySet().toArray()[0]).getValue();

    }

    private void getWindData() {
        var forecast = ((ArrayList<?>) acuuWeatherData.getApiData().get("DailyForecasts")).get(0);
        var dayData = ((Map.Entry) ((LinkedHashMap) forecast).entrySet().toArray()[10]).getValue();
        var dayWindData = ((Map.Entry) ((LinkedHashMap) dayData).entrySet().toArray()[10]).getValue();
        windSpeedDay = (double) ((Map.Entry) ((LinkedHashMap) ((Map.Entry) ((LinkedHashMap) dayWindData).entrySet().toArray()[0]).getValue()).entrySet().toArray()[0]).getValue();
        windDirectionDay = ((Map.Entry) ((LinkedHashMap) ((Map.Entry) ((LinkedHashMap) dayWindData).entrySet().toArray()[1]).getValue()).entrySet().toArray()[1]).getValue().toString();

        var nightData = ((Map.Entry) ((LinkedHashMap) forecast).entrySet().toArray()[11]).getValue();
        var nightWindData = ((Map.Entry) ((LinkedHashMap) dayData).entrySet().toArray()[11]).getValue();

        windSpeedNight = (double) ((Map.Entry) ((LinkedHashMap) ((Map.Entry) ((LinkedHashMap) nightWindData).entrySet().toArray()[0]).getValue()).entrySet().toArray()[0]).getValue();
        windDirectionNight = ((Map.Entry) ((LinkedHashMap) ((Map.Entry) ((LinkedHashMap) nightWindData).entrySet().toArray()[1]).getValue()).entrySet().toArray()[1]).getValue().toString();
    }


    @Override
    public String toString() {
        return cityID != 0 ?
                new StringBuilder("Aktualne dane pogodowe: \n")
                .append("Temperatura minimalna " + minimalTemperature + " °C, maksymalna " + maximumTemperature + " °C. \n")
                .append("Wiatr podczas dnia: " + windSpeedDay + " km/h, kierunek: " + windDirectionDay + ". \n")
                .append("Wiatr w nocy: " + windSpeedNight + " km/h, kierunek: " + windDirectionNight + ". \n")
                .toString() :
                "Brak wczytanych danych";
    }
}
