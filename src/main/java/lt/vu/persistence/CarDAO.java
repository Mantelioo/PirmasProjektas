package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class CarDAO
{
    @Inject
    private EntityManager myEntityManager;

    public void setEntityManager(EntityManager manager)
    {
        this.myEntityManager = manager;
    }
    public  List<Car> getAllCars()
    {
        return  myEntityManager.createNamedQuery("Car.findAll", Car.class).getResultList();
    }
    public void persist(Car myCar)
    {
        this.myEntityManager.persist(myCar);
    }
    public  Car findCarByID(long id)
    {
        return  myEntityManager.find(Car.class, id);
    }
    public Car updateCar(Car myCar)
    {
        return myEntityManager.merge(myCar);
    }
}
