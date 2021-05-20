package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative //Alternative version. Working with cars that have automatic gearboxes
public class AutomaticCarsDAO extends CarDAO implements ICarsDAO
{
    @Inject
    EntityManager em;

   @Override
  public List<Car> getAllCars()
   {
       return  em.createQuery("select c from Car c where c.gearbox='automatic'", Car.class)
               .getResultList();
   }
}
