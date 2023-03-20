package pl.orange;

import pl.orange.weather.AccuCitySearch;
import pl.orange.weather.AcuuWeather1Day;

public class Main {

    public static void main(String[] args) {

        //AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(266375);
        //System.out.println("===================================");
        //System.out.println(acuuWeather1Day);
        //System.out.println("===================================");

        AccuCitySearch newCity = new AccuCitySearch("Jajjjj");
        AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(Integer.valueOf(newCity.cityKey()));

        System.out.println(acuuWeather1Day);

    }
}
