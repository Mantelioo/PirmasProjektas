package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
public class ManualCarsDAO extends CarDAO implements ICarsDAO
{
    @Inject
    EntityManager myEntityManager;

    @Override
    public List<Car> getAllCars()
    {
        return myEntityManager
                .createQuery("select c from Car c where c.gearbox='manual'", Car.class)
                .getResultList();
    }
}
