package pl.orange.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartialData {
    private String description;
    private double speed;
    private String direction;
    private int precipitationProbability;

    public PartialData() {
    }
}
