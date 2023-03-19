package pl.orange;

import org.apache.log4j.Logger;

public class AccuCitySearch {

    private final static Logger LOGGER = Logger.getLogger(AccuCitySearch.class);

    private WeatherApiReader apiReader;
    private final String apiKey1 = "dQHpG5soGU4z6IGMbSYdA7UQVA9JySmR";
    private final String apiKey2 = "mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC";
    private String city;

    public AccuCitySearch(String city) {
        this.city = city;
        this.apiReader = new WeatherApiReader("http://dataservice.accuweather.com/locations/v1/cities/PL/search?apikey="
                + apiKey2 + "&q=" + city);
        cityExisting();
    }

    private void cityExisting(){
        if (apiReader.response().compareTo(new StringBuffer("[]")) != 0 ){
            System.out.println("Jest oki");

        }else
        {
            LOGGER.warn("City don't exist");
        }
    }
}
