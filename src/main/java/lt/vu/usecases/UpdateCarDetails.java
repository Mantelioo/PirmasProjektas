package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.persistence.CarDAO;

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
public class UpdateCarDetails implements Serializable {

    private Car myCar;

    @Inject
    private CarDAO  carDAO;

    @PostConstruct // suveiks sukurus objekta
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long carID = Long.parseLong(requestParameters.get("carID"));
        this.myCar = carDAO.findCarByID(carID);
    }

    @Transactional
    public String updateCar() {
            carDAO.updateCar(this.myCar);
            return "/carShops.xhtml?faces-redirect=true&carID=" + this.myCar.getCarID() + "&error=optimistic-lock-exception";
    }
}
