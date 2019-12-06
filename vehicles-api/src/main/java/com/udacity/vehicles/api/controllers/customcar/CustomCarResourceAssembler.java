package com.udacity.vehicles.api.controllers.customcar;

import com.udacity.vehicles.domain.customcar.CustomCar;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Maps the CustomCarController to the CustomCar class using HATEOAS
 */
@Component
public class CustomCarResourceAssembler implements ResourceAssembler<CustomCar, Resource<CustomCar>> {

    @Override
    public Resource<CustomCar> toResource(CustomCar customCar) {
        return new Resource<>(customCar,
                linkTo(methodOn(CustomCarController.class).get(customCar.getId())).withSelfRel(),
                linkTo(methodOn(CustomCarController.class).list()).withRel("custom-car"));

    }
}
