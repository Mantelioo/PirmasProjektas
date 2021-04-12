package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.model.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CarMyBatis
{
    @Inject
    private CarMapper carMapper;

    @Getter
    private List<Car> myCars;

    @Getter @Setter
    private Car myCar = new Car();

    @PostConstruct
    public void createObjects()
    {
        this.myCars = carMapper.selectAll();
    }

   @Transactional //transaction service. Atlieka keleta vaiksmu. Gali atlikti verslo logikos operacijas.
    public String createCar()
    {
        carMapper.insert(myCar);
        return "/myBatis/carMyBatis?faces-redirect=true";
    }
}