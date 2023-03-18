package pl.orange;

public class Main {

    public static void main(String[] args) {

        WeatherApiReader accuWeather = new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/266375?apikey=mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC&language=pl");

        System.out.println(accuWeather.response());

    }
}
