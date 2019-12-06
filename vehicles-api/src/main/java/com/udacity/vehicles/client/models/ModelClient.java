package com.udacity.vehicles.client.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ModelClient {

    private static final Logger log = LoggerFactory.getLogger(ModelClient.class);

    private final WebClient client;

    public ModelClient(WebClient models) {
        this.client = models;
    }

    public Model getModel(Long vehicleId) {
        try {
            Model model = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("models/" + vehicleId)
                            .build()
                    )
                    .retrieve().bodyToMono(Model.class).block();

            assert model != null;
            return model;
        } catch (Exception e) {
            log.error("Unexpected error retrieving price for vehicle {}", vehicleId, e);
        }
        return null;
    }
}
