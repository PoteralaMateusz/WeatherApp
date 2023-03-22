package pl.orange.model;

import lombok.Getter;
import lombok.Setter;

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
    }

}
