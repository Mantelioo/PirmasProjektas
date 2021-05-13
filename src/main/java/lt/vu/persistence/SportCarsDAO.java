package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.inject.Specializes;

@Specializes
public class SportCarsDAO extends CarDAO
{
    @Override
    public Car updateCar(Car myCar)
    {
        myCar.setGearbox(myCar.getGearbox() + "Sport");
        return  super.updateCar(myCar);
    }
}
