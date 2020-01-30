package maps;

import maps.model.Location;
import maps.model.Locations;
import maps.repositories.LocationsRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.stream.LongStream;

@SpringBootApplication
@EnableEurekaClient
public class LocationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsServiceApplication.class, args);
    }
    @Bean
    ApplicationRunner init(LocationsRepository repository) {
        return args -> LongStream
                .range(1,5).forEach(i -> repository.save(generateLocation((int)i)));
    }

    private Location generateLocation(int id) {
        Map<Integer, Locations> addresses = Locations.getAdresses();
        Location location = new Location();
        Locations enumLocation = addresses.get(id);
        location.setAddress(enumLocation.getAddress());
        location.setCity(enumLocation.getCity());
        location.setState(enumLocation.getState());
        location.setZip(enumLocation.getZipCode());
        return location;
    }
}
