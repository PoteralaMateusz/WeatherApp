package pl.orange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeatherDataDeserialization {

    private final static Logger LOGGER = Logger.getLogger(WeatherDataDeserialization.class);

    private WeatherApiReader apiReader;
    public Map<String, Object> apiData;
    private ObjectMapper mapper = new ObjectMapper();

    public WeatherDataDeserialization(WeatherApiReader apiReader) {
        this.apiReader = apiReader;
        getData();
    }

    public Map<String, Object> getApiData() {
        return apiData;
    }

    private void getData() {
        mapper = new ObjectMapper();
        try {
            apiData = mapper.readValue(apiReader.response().toString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
