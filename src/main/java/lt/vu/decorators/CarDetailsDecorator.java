package lt.vu.decorators;

import lt.vu.usecases.IUpdateCarDetails;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class CarDetailsDecorator implements IUpdateCarDetails
{
    @Inject
    @Delegate
    @Any
    IUpdateCarDetails carDetails;

    @Override
    public String updateCarGearbox() throws InterruptedException
    {
        String result = carDetails.updateCarGearbox();
        return  result + "&decorated=yes";
    }
}
