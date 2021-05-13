package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.persistence.CarDAO;
import lt.vu.rest.contracts.CarDTO;
import lt.vu.rest.contracts.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
@Path("/cars")
public class CarsController
{
    @Inject
    @Setter
    @Getter
    private CarDAO carsDAO;

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllCars()
    {
        List<Car> cars = carsDAO.getAllCars();
        if (cars.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<CarDTO> carDTOS = cars.stream()
                .map(Mapper::convertToCarDTO)
                .collect(Collectors.toList());
        return Response.ok(carDTOS).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCarByID(@PathParam("id") final Long id)
    {
        Car car = carsDAO.findCarByID(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Mapper.convertToCarDTO(car)).build();
    }

    @Path("/put/{id}/{description}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCarDetails(@PathParam("id") final Long carID, @PathParam("description") final
    String carDescription)
    {
        try {
            Car exixtingCar = carsDAO.findCarByID(carID);
            if (exixtingCar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            exixtingCar.setDescription(carDescription);
            carsDAO.updateCar(exixtingCar);
            return Response.ok(Response.Status.OK).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/post/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCar(@PathParam("description")final String description)
    {
        Car newCar = new Car();
        newCar.setDescription(description);
        carsDAO.persist(newCar);
        return  Response.ok(Response.Status.OK).build();
    }
}
