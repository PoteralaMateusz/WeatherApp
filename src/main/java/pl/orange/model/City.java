package pl.orange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cityKey;

    public City(String name, String cityKey) {
        this.name = name;
        this.cityKey = cityKey;
    }

    public City(String name) {
        this.name = URLEncoder.encode(name, StandardCharsets.UTF_8);
    }

}
