package pl.orange;

import pl.orange.model.City;
import pl.orange.model.CityDAO;
import pl.orange.model.DataAccess;
import pl.orange.weather.AccuCitySearch;
import pl.orange.weather.AcuuWeather1Day;

public class Main {

    public static void main(String[] args) {

        AccuCitySearch city1 = new AccuCitySearch("Nidzica");
        System.out.println(city1.cityKey());
        AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(city1.cityKey());
        System.out.println(acuuWeather1Day);
        CityDAO cityData = new CityDAO();
        cityData.save(new City(city1.cityName(), city1.cityKey()));

        System.out.println("=================");
        System.out.println("Miasta z bazy danych: ");
        cityData.findAll().forEach(city -> System.out.println("Nazwa: " + city.getName() + ", klucz: " + city.getCityKey()));

    }
}
