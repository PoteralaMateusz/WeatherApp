package pl.orange.weather;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiReader {

    private final static Logger LOGGER = Logger.getLogger(WeatherApiReader.class);

    public final String GET_URL;
    private StringBuffer responseData = new StringBuffer();
    private int responseCode;

    public WeatherApiReader(String GET_URL){
        this.GET_URL = GET_URL;
        readDataToString();
    }

    public StringBuffer responseData() {
        return responseData;
    }

    public int responseCode(){
        return responseCode;
    }


    private void readDataToString() {

        try {

            URL apiURL = new URL(GET_URL);
            HttpURLConnection apiConnection = (HttpURLConnection) apiURL.openConnection();
            apiConnection.setRequestMethod("GET");

            if ((responseCode = apiConnection.getResponseCode()) == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    responseData.append(inputLine);
                }
                reader.close();
            } else {
                LOGGER.error("API CONNECTION ERROR:" + apiConnection.getResponseCode());
                responseData = new StringBuffer();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
