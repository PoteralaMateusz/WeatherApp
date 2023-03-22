package pl.orange.model;

import lombok.Getter;
import lombok.Setter;

import java.net.URLDecoder;

@Setter
@Getter
public class Weather {

    private City city;
    private Data minimalTemperature;
    private Data maximumTemperature;
    private Wind windDay;
    private Wind windNight;

    public Weather(){

    }

    public Weather(City city){
        this.city = city;
        minimalTemperature = new Data();
        maximumTemperature = new Data();
        windDay = new Wind();
        windNight = new Wind();

    }

    @Override
    public String toString() {
        return new StringBuilder("Aktualne dane pogodowe dla miasta " + URLDecoder.decode(city.getName()))
                .append("\nTemperatura minimalna ")
                .append(minimalTemperature.getValue() + " ")
                .append(minimalTemperature.getUnity() + ", ")
                .append(minimalTemperature.getValue() + " ")
                .append(minimalTemperature.getUnity() + ", ")
                .append("\nWiatr podczas dnia: ")
                .append(windDay.getSpeed().getValue() + windDay.getSpeed().getUnity() + ", ")
                .append("kierunek: ")
                .append( windDay.direction.getUnity() + ".")
                .append("\nWiatr w nocy: ")
                .append(windNight.getSpeed().getValue() + windNight.getSpeed().getUnity() + ", ")
                .append("kierunek: ")
                .append( windNight.direction.getUnity() + ".")
                .toString();
    }
}
