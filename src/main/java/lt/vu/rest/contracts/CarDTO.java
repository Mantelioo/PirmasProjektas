package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarDTO
{
    private String decription;
    private List<CarshopDTO> carShops;

}
