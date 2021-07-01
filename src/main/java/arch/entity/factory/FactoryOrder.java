package arch.entity.factory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FactoryOrder {
    private Integer id;
    private String description;
    private Float money;
}
