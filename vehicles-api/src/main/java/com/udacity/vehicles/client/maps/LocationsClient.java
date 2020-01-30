package com.udacity.vehicles.client.maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements a class to interface with the Maps Client for location data.
 */
@Component
public class LocationsClient {

    private static final Logger log = LoggerFactory.getLogger(LocationsClient.class);


    private final WebClient client;

    public LocationsClient(WebClient locations) {
        this.client = locations;
    }

//    private final WebClient client;
//    private final ModelMapper mapper;
//
//    public MapsClient(WebClient maps,
//            ModelMapper mapper) {
//        this.client = maps;
//        this.mapper = mapper;
//    }

    /**
     * Gets an address from the Maps client, given latitude and longitude.
     * @return An updated location including street, city, state and zip,
     *   or an exception message noting the Maps service is down
     */
    public Address getAddress(Long vehicleId) {
        try {
            Address address = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("locations/" + vehicleId)
                            .build()
                    )
                    .retrieve().bodyToMono(Address.class).block();


            assert address != null;
            return address;
        } catch (Exception e) {
            log.error("Locations service is down");
        }
        return null;
    }
}