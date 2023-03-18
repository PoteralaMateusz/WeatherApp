package pl.orange;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiReader {

    private final static Logger LOGGER = Logger.getLogger(WeatherApiReader.class);

    public final String GET_URL;
    private StringBuffer response = new StringBuffer();

    public WeatherApiReader(String GET_URL){
        this.GET_URL = GET_URL;
        readDataToString();
    }

    public StringBuffer response() {
        return response;
    }

    private void readDataToString() {

        try {

            URL apiURL = new URL(GET_URL);
            HttpURLConnection apiConnection = (HttpURLConnection) apiURL.openConnection();
            apiConnection.setRequestMethod("GET");

            if (apiConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
            } else {
                LOGGER.error("API CONNECTION ERROR:" + apiConnection.getResponseCode());
                response = new StringBuffer();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
