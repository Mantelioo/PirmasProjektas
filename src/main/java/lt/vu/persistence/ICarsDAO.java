package lt.vu.persistence;

import lt.vu.entities.Car;

import java.util.List;

public interface ICarsDAO
{
    List<Car> getAllCars();

    Car findCarByID(Long carID);

    void persist(Car createdCar);

    Car updateCar(Car car);
}
