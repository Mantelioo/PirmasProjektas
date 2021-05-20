package lt.vu.usecases;


import lt.vu.entities.Car;
import lt.vu.persistence.CarDAO;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@LoggedInvocation
@RequestScoped
@Named
@Getter @Setter
public class UpdateCarDetails implements Serializable, IUpdateCarDetails {

    private Car myCar;

    @Getter @Setter
    private   String errorMSG = "Message";

    @Inject
    private CarDAO  carDAO;

    @PostConstruct // suveiks sukurus objekta
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long carID = Long.parseLong(requestParameters.get("carID"));
        this.myCar = carDAO.findCarByID(carID);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String updateCarDescription() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        try {
            carDAO.updateCar(this.myCar);
        } catch (OptimisticLockException e) {
            errorMSG = e.getMessage();
            return "/carShops.xhtml?carID=" + this.myCar.getCarID() + "&faces-redirect=true" + "&error=optimistic-lock-exception";
        }
        return "/carShops.xhtml?carID=" + this.myCar.getCarID() + "&faces-redirect=true";
    }


    @Transactional
    public String updateCarGearbox() {
            carDAO.updateCar(this.myCar);
            return "/carShops.xhtml?faces-redirect=true&carID=" + this.myCar.getCarID() + "&error=optimistic-lock-exception";
    }
}
