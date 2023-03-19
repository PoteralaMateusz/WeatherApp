package pl.orange;

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

    public AcuuWeather1Day(int cityID) {
        this.cityID = cityID;
        acuuWeatherData = new WeatherDataDeserialization(
                new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                        + cityID + "?apikey= " + apiKey1 + "&language=pl&details=true&metric=true"));
        getTemperatureData();

    }

    public void getTemperatureData() {

        var forecast = acuuWeatherData.getApiData().get("DailyForecasts");
        forecast = ((ArrayList<?>)forecast).get(0);
        var temperature = ((LinkedHashMap)forecast).entrySet().toArray()[4];
        temperature = ((Map.Entry) temperature).getValue();
        var minimumTemperatureData = ((Map.Entry) ((LinkedHashMap)temperature).entrySet().toArray()[0]).getValue();
        minimalTemperature =(double) ((Map.Entry)((LinkedHashMap) minimumTemperatureData).entrySet().toArray()[0]).getValue();

        var maximumTemperatureData = ((Map.Entry) ((LinkedHashMap)temperature).entrySet().toArray()[1]).getValue();
        maximumTemperature = (double) ((Map.Entry)((LinkedHashMap) maximumTemperatureData).entrySet().toArray()[0]).getValue();


    }
}
