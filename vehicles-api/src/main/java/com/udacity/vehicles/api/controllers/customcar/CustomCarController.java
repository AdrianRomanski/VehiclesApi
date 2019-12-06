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


@RestController
@RequestMapping("/custom-car")
public class CustomCarController {

    private final CustomCarService customCarService;
    private final CustomCarResourceAssembler customCarAssembler;

    public CustomCarController(CustomCarService customCarService, CustomCarResourceAssembler customCarAssembler) {
        this.customCarService = customCarService;
        this.customCarAssembler = customCarAssembler;
    }

    @GetMapping
    Resources<Resource<CustomCar>> list() {
        List<Resource<CustomCar>> resources = customCarService.list().stream().map(customCarAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CustomCarController.class).list()).withSelfRel());
    }

    @GetMapping("/{id}")
    Resource<CustomCar> get(@PathVariable Long id) {

        CustomCar customCar = customCarService.findById(id);
        return customCarAssembler.toResource(customCar);
    }

    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody CustomCar customCar) throws URISyntaxException {

        customCar = customCarService.save(customCar);
        Resource<CustomCar> resource = customCarAssembler.toResource(customCar);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody CustomCar customCar) {

        customCar.setId(id);
        customCar = customCarService.save(customCar);
        Resource<CustomCar> resource = customCarAssembler.toResource(customCar);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {

        customCarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
