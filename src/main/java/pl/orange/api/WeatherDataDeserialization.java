package pl.orange.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.util.Map;

public class WeatherDataDeserialization {

    private final static Logger LOGGER = Logger.getLogger(WeatherDataDeserialization.class);

    private WeatherApiReader apiReader;
    public Map<String, Object> apiData;
    private ObjectMapper mapper = new ObjectMapper();

    public WeatherDataDeserialization(WeatherApiReader apiReader) {
        this.apiReader = apiReader;
        if (apiReader.responseCode() == HttpURLConnection.HTTP_OK) {
            getData();
        }
    }

    public Map<String, Object> getApiData() {
        return apiData;
    }

    private void getData() {
        mapper = new ObjectMapper();
        try {
            apiData = mapper.readValue(apiReader.responseData().toString(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            LOGGER.error("JSON DESERIALIZATION ERROR");
            e.printStackTrace();
        }

    }
}
