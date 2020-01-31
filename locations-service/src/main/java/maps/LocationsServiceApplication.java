package maps;

import maps.model.Location;
import maps.model.Locations;
import maps.repositories.LocationsRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

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
                .range(1,200).forEach(i -> repository.save(generateLocation((int)i)));
    }

    private Location generateLocation(int id) {
        Location location = Locations.getRandom();
        location.setId((long)id);
        return location;
    }
}
