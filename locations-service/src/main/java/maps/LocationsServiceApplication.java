package maps;

import maps.model.Location;
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
                .range(1,20).forEach(i -> repository.save(new Location(i,"Hola", "Soy", "Su", "Adress")));

    }
}
