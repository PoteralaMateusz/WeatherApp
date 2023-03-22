package pl.orange.view;

import pl.orange.model.City;
import pl.orange.model.CityDAO;
import pl.orange.api.AccuCitySearch;
import pl.orange.api.AcuuWeather1Day;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private int type = 0;

    public Menu() {
    }

    public void initMenu() {
        System.out.println("====================MENU=====================");
        System.out.println("1. Dodaj miasto do bazy .....................");
        System.out.println("2. Usuń miasto z bazy .......................");
        System.out.println("3. Wyświetl miasta z bazy ...................");
        System.out.println("4. Wyświetl pogodę dla miast z bazy .........");
        System.out.println("0. Zamknij program ..........................");
        separator();
        do {
            System.out.println("Podaj twój wybór: ...........................");
            Scanner scanner = new Scanner(System.in);
            type = scanner.nextInt();
        }while (type != 1 && type != 2 && type != 3 & type != 4 && type != 0 );

        switch (type){

            case 1 -> addCity();
            case 2 -> removeCityFromDB();
            case 3 -> showCityFromDB();
            case 4 -> showWeatherForCityFromDB();
            case 0 -> System.exit(0);
        }
    }

    private void removeCityFromDB() {
        separator();
        System.out.println("Podaj id miasta którą chcesz usunąć z bazy");
        Scanner scanner = new Scanner(System.in);
        Long cityID = Long.parseLong(scanner.nextLine());

        CityDAO cityDAO = new CityDAO();
        cityDAO.deleteByID(cityID);
        initMenu();
    }

    private void showWeatherForCityFromDB() {
        separator();
        CityDAO cityDAO = new CityDAO();
        List<City> cityList = cityDAO.findAll();

        for (City city: cityList) {
            AcuuWeather1Day acuuWeather1Day = new AcuuWeather1Day(city.getName(),city.getCityKey());
            System.out.println(acuuWeather1Day);
            separator();
        }
        separator();
        initMenu();
    }

    private void showCityFromDB() {
        separator();
        System.out.println("Miasta z bazy danych: ......................");
        CityDAO cityDAO = new CityDAO();
        cityDAO.findAll().forEach(city -> System.out.println("Nazwa: " + URLDecoder.decode(city.getName(), StandardCharsets.UTF_8) + ", klucz: " + city.getCityKey()));
        separator();
        initMenu();
    }

    private void addCity() {
        separator();
        System.out.println("Podaj nazwę miasta którą chcesz dodać do bazy");
        Scanner scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();

        AccuCitySearch newCityToSave = new AccuCitySearch(cityName);

        if (newCityToSave.cityExist){
            CityDAO cityDAO = new CityDAO();
            cityDAO.save(new City(newCityToSave.cityName(), newCityToSave.cityKey()));
        }

        initMenu();
    }


    private void separator(){
        System.out.println("=============================================");

    }


}
