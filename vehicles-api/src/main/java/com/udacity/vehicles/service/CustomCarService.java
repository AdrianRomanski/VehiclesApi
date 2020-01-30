package com.udacity.vehicles.service;


import com.udacity.vehicles.domain.customcar.CustomCar;
import com.udacity.vehicles.domain.customcar.CustomCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomCarService {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CustomCarRepository repository;



    public CustomCarService(CustomCarRepository repository) {
        this.repository = repository;

    }

    public List<CustomCar> list() {
        return repository.findAll();
    }


    public CustomCar findById(Long id) {

        Optional<CustomCar> optionalCar = repository.findById(id);
        return optionalCar.orElseThrow(CarNotFoundException::new);
    }


    public CustomCar save(CustomCar customCar) {
        if (customCar.getId() != null) {
            return repository.findById(customCar.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(customCar.getDetails());
                        carToBeUpdated.setCustomLocation(customCar.getCustomLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(customCar);
    }


    public void delete(Long id) {

        Optional<CustomCar> optionalCar = repository.findById(id);
        CustomCar customCar = optionalCar.orElseThrow(CarNotFoundException::new);

        repository.delete(customCar);

    }
}

