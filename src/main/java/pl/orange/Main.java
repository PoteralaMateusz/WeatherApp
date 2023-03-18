package pl.orange;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        WeatherApiReader accuWeather = new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/266375?apikey=mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC&language=pl&details=true");

        //System.out.println(accuWeather.response());

        WeatherDataDeserialization accuWeatherDataDeserialization = new WeatherDataDeserialization(accuWeather);
        WeatherDataDeserialization.getDataFromCategory(accuWeatherDataDeserialization.getApiData(),"Headline").entrySet()
                .forEach(v-> System.out.println(v.getKey()));

    }

    private void getTemperatureData(){

    }
}
