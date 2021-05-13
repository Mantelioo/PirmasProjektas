package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@LoggedInvocation
@Model
public class Cars
{
    @Inject
    private CarDAO myCarDAO;

    @Getter @Setter //Generating getter and setter auto
    private Car myCar = new Car();

    @Getter //getting all of the cars from DB
    private List<Car> carsFromDB;

    @PostConstruct
    public void createObject()
    {
        this.carsFromDB = myCarDAO.getAllCars();
    }

    @Transactional
    public String createCar(){
        this.myCarDAO.persist(myCar);
        return "index?faces-redirect=true";
    }

}
