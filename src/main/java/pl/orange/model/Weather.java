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
    private PartialData day;
    private PartialData night;

    public Weather() {

    }

    public Weather(City city) {
        this.city = city;
        day = new PartialData();
        night = new PartialData();

    }

    @Override
    public String toString() {
        return new StringBuilder("Aktualne dane pogodowe dla miasta " + URLDecoder.decode(city.getName()))
                .append("\nTemperatura minimalna ")
                .append(minimalTemperature + "°C, maksymalna ")
                .append(maximumTemperature + "°C.")
                .append("\nDzień: " + day.getDescription() + ".")
                .append("\nPrawdopodobieństwo opadów: " + day.getPrecipitationProbability() + "%.")
                .append("\nWiatr: ")
                .append(day.getSpeed() + "km/h, ")
                .append("kierunek: ")
                .append(day.getDirection() + ".")
                .append("\nNoc: " + night.getDescription() + ".")
                .append("\nPrawdopodobieństwo opadów: " + night.getPrecipitationProbability() + "%.")
                .append("\nWiatr: ")
                .append(night.getSpeed() + "km/h, ")
                .append("kierunek: ")
                .append(night.getDirection() + ".")
                .toString();
    }
}
