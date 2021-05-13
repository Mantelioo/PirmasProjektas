package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Default
@ApplicationScoped
public class CarDAO implements ICarsDAO
{
    @Inject
    private EntityManager myEntityManager;

    public void setEntityManager(EntityManager manager)
    {
        this.myEntityManager = manager;
    }

    @Override
    public  List<Car> getAllCars()
    {
        return  myEntityManager.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    @Override
    public void persist(Car myCar)
    {
        this.myEntityManager.persist(myCar);
    }

    @Override
    public  Car findCarByID(Long id)
    {
        return  myEntityManager.find(Car.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Car updateCar(Car myCar)
    {
      myCar = myEntityManager.merge(myCar);
      myEntityManager.flush();
      return myCar;
    }
}
