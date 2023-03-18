package pl.orange;

import org.apache.log4j.Logger;

public class AcuuWeather1Day {

    private final static Logger LOGGER = Logger.getLogger(AcuuWeather1Day.class);

    private WeatherDataDeserialization acuuWeatherData;
    private final int cityID;

    public AcuuWeather1Day(int cityID){
        this.cityID = cityID;
        acuuWeatherData = new WeatherDataDeserialization(
                new WeatherApiReader("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                         + cityID + "?apikey=mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC&language=pl&details=true"));

    }
}
