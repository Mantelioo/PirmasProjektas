package lt.vu.persistence;

import lt.vu.entities.Car;
import lt.vu.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShopDAO
{
    @Inject
    private EntityManager myEntityManager;

    public void persist(Shop carShop)
    {
        this.myEntityManager.persist(carShop);
    }

    public Shop findShotByID(long id)
    {
        return myEntityManager.find(Shop.class, id);
    }

    public Shop updateShop(Shop carShop)
    {
        return  myEntityManager.merge(carShop);
    }

    public List<Shop> getAllShops()
    {
        return  myEntityManager.createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }


}
