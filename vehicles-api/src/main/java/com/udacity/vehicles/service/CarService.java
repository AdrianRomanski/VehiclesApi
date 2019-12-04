package com.udacity.vehicles.service;


import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.models.ModelClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.car.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import com.udacity.vehicles.domain.car.Details;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CarRepository repository;
    private final WebClient pricing;
    private final WebClient maps;
    private final WebClient models;
    private final PriceClient priceClient;
    private final MapsClient mapsClient;
    private final ModelClient modelClient;

    public CarService(CarRepository repository, @Qualifier("pricing") WebClient pricing, @Qualifier("maps") WebClient maps,
                      @Qualifier("models") WebClient models, PriceClient priceClient, MapsClient mapsClient, ModelClient modelClient) {
        this.repository = repository;
        this.pricing = pricing;
        this.maps = maps;
        this.models = models;
        this.priceClient = priceClient;
        this.mapsClient = mapsClient;
        this.modelClient = modelClient;
    }

    public List<Car> list() {
        return repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {

        Details details = new Details();
        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);

        String price = priceClient.getPrice(id).getPrice().toString();
        car.setPrice(price);
        String currency = priceClient.getPrice(id).getCurrency();
        car.setCurrency(currency);
        Location location = mapsClient.getAddress(car.getLocation());
        car.setLocation(location);

        details.setColor(modelClient.getModel(id).getColor());
        details.setModel(modelClient.getModel(id).getName());
        details.setManufacturer(modelClient.getModel(id).getManufacturer());
        details.setBody(modelClient.getModel(id).getBody());
        details.setEngine(modelClient.getModel(id).getEngine());
        details.setModelYear(modelClient.getModel(id).getModelYear());
        details.setNumberOfDoors(modelClient.getModel(id).getNumberOfDoors());

        car.setDetails(details);
        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {

        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);

        repository.delete(car);


    }
}