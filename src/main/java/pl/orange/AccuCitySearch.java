package pl.orange;

import org.apache.log4j.Logger;

public class AccuCitySearch {

    private final static Logger LOGGER = Logger.getLogger(AccuCitySearch.class);

    private WeatherApiReader apiData;

    private final String apiKey1 = "dQHpG5soGU4z6IGMbSYdA7UQVA9JySmR";
    private final String apiKey2 = "mn4i7Pi5bymsiuqfhiBbXL2BCJbl43MC";
    private String city;
    private String cityKey = "";

    public AccuCitySearch(String city) {
        this.city = city;
        this.apiData = new WeatherApiReader("http://dataservice.accuweather.com/locations/v1/cities/PL/search?apikey="
                + apiKey1 + "&q=" + city);
        cityExisting();
    }

    public String cityKey(){
        return cityKey;
    }

    private void cityExisting(){
        if (apiData.response().compareTo(new StringBuffer("[]")) != 0 ){
            LOGGER.info("City: " + city + " exist in weather API");
            getCityKey();
        }else
        {
            LOGGER.warn("City: " + city + " don't exist in weather API");
        }
    }

    private void getCityKey(){
        String data = apiData.response().toString();
        int keyStartIndex = data.indexOf("Key") + 6;
        int keyEndIndex = keyStartIndex + (data.substring(keyStartIndex)).indexOf(",") - 1;
        cityKey = data.substring(keyStartIndex,keyEndIndex);

    }
}
