package pl.orange;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(266375);
        //System.out.println("===================================");
        //System.out.println(acuuWeather1Day);
        //System.out.println("===================================");

        AccuCitySearch newCity = new AccuCitySearch("Warszawa");
        System.out.println(newCity.cityKey());
        AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(Integer.valueOf(newCity.cityKey()));

        System.out.println(acuuWeather1Day);

    }
}
