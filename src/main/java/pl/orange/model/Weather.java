package pl.orange.model;

import lombok.Getter;
import lombok.Setter;

import java.net.URLDecoder;

@Setter
@Getter
public class Weather {

    private City city;
    private double minimalTemperature;
    private double maximumTemperature;
    private Wind windDay;
    private Wind windNight;

    public Weather(){

    }

    public Weather(City city){
        this.city = city;
        windDay = new Wind();
        windNight = new Wind();

    }

    @Override
    public String toString() {
        return new StringBuilder("Aktualne dane pogodowe dla miasta " + URLDecoder.decode(city.getName()))
                .append("\nTemperatura minimalna ")
                .append(minimalTemperature + "°C, maksymalna ")
                .append(maximumTemperature + "°C.")
                .append("\nWiatr podczas dnia: ")
                .append(windDay.getSpeed()  + "km/h, ")
                .append("kierunek: ")
                .append( windDay.direction + ".")
                .append("\nWiatr w nocy: ")
                .append(windNight.getSpeed() + "km/h, ")
                .append("kierunek: ")
                .append( windNight.direction + ".")
                .toString();
    }
}
