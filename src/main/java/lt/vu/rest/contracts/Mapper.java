package lt.vu.rest.contracts;

import lt.vu.entities.Car;
import lt.vu.entities.Shop;

import java.util.stream.Collectors;

public class Mapper
{
    public  static CarDTO convertToCarDTO(Car car)
    {
        if(car != null)
        {
            CarDTO carDTO = new CarDTO();
            carDTO.setDecription(car.getDescription());
            carDTO.setCarShops(
                    car.getCarShops().stream()
                    .map(Mapper::convertToCarshopDTO)
                    .collect(Collectors.toList())
            );
            return carDTO;
        }
    return  null;
    }

    public static CarshopDTO convertToCarshopDTO(Shop carshop)
    {
        if (carshop != null){
            CarshopDTO carshopDTO = new CarshopDTO();
            carshopDTO.setName(carshop.getName());
            return carshopDTO;
        }

        return null;
    }


}
