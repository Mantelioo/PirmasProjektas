package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Shop;
import lt.vu.persistence.ShopDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;



@RequestScoped
@Named
@Getter @Setter
public class UpdateCarShopDetails implements  Serializable // bandziau pakeisti name auto salono bet nesigavo
{
    private Shop carShop;

    @Inject
    private ShopDAO carShopDAO; //DAO klase

    @PostConstruct
    public void createObject()
    {
        Map<String, String> data = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long shopID = Long.parseLong(data.get("shopID")); // surandamas shop pagal jo ID
        this.carShop = carShopDAO.findShotByID(shopID); //Priskiriu objekta
    }

    @Transactional
    public  void updateShop(){ //atnaujinama shop informacija
            carShopDAO.updateShop(this.carShop);
    }

}
