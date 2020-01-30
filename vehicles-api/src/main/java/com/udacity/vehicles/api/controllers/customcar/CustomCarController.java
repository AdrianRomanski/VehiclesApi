package com.udacity.vehicles.api.controllers.customcar;

import com.udacity.vehicles.domain.customcar.CustomCar;
import com.udacity.vehicles.service.CustomCarService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/custom-car")
public class CustomCarController {

    private final CustomCarService customCarService;
    private final CustomCarResourceAssembler customCarAssembler;

    public CustomCarController(CustomCarService customCarService, CustomCarResourceAssembler customCarAssembler) {
        this.customCarService = customCarService;
        this.customCarAssembler = customCarAssembler;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
    @GetMapping
    Resources<Resource<CustomCar>> list() {
        List<Resource<CustomCar>> resources = customCarService.list().stream().map(customCarAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CustomCarController.class).list()).withSelfRel());
    }

    /**
     * Gets information of a specific car by ID.
     * All the information's will be provided by user
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @GetMapping("/{id}")
    Resource<CustomCar> get(@PathVariable Long id) {

        CustomCar customCar = customCarService.findById(id);
        return customCarAssembler.toResource(customCar);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param customCar A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody CustomCar customCar) throws URISyntaxException {

        customCar = customCarService.save(customCar);
        Resource<CustomCar> resource = customCarAssembler.toResource(customCar);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param customCar The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody CustomCar customCar) {

        customCar.setId(id);
        customCar = customCarService.save(customCar);
        Resource<CustomCar> resource = customCarAssembler.toResource(customCar);
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {

        customCarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
