package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.persistence.CarDAO;
import lt.vu.entities.Shop;
import lt.vu.persistence.ShopDAO;

@Model
public class CarShops implements Serializable
{
    @Inject
    private ShopDAO myShopDAO;

    @Inject
    private CarDAO myCarDAO;

    @Getter @Setter
    private Car myCar;

    @Getter @Setter
    private  Shop myShop = new Shop(); //creating a new shop

    @PostConstruct
    public void createObject()
    {
      Map<String, String> data = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long carID = Long.parseLong(data.get("carID"));
        this.myCar = myCarDAO.findCarByID(carID); //suranda 1 masina pagal jos ID
        createShops();
    }

    @Getter //getting all of the shops from DB
    private List<Shop> shopsFromDB;

    public void createShops()
    {
        this.shopsFromDB = myShopDAO.getAllShops();
    } //getting all the shops from DB

    @Transactional //Jei viduje atliekami keli veiksmai ir kazkuris is tu veiksmu nulus. visi pakeitinai RollBackins
    public String addShop()
    {
        myShop.getShopCars().add(this.myCar);
        myShopDAO.persist(myShop);
        return "carShops.xhtml?faces-redirect=true&carID=" + this.myCar.getCarID();
    }
}
