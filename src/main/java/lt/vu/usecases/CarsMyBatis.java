package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.model.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@LoggedInvocation
@Model//@Named and @RequestScoped
public class CarsMyBatis {
    @Inject
    private CarMapper carMapper;

    @Getter
    private List<Car> myCars; //getting all of the objects

    @Getter @Setter
    private Car myCar = new Car(); //creating a new object

    @PostConstruct
    public void init() {
        this.getCarsFromDB();
    }

    private void getCarsFromDB() {
        this.myCars = carMapper.selectAll();
    }

    @Transactional //Transakcijos gali nebuti. Jeigu feilints insertas, duomenys nesusigadins
    public String createCar() {
        carMapper.insert(myCar);
        return "carsMyBatis?faces-redirect=true";
    }
}
