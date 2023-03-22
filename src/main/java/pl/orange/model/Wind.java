package pl.orange.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Wind {
    Data speed;
    Data direction;

    public Wind() {
        this.speed = new Data();
        this.direction = new Data();
    }
}
