package modelservice;

import modelservice.enums.Models;
import modelservice.model.Model;
import modelservice.enums.Color;
import modelservice.repositories.ModelRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import java.util.Map;
import java.util.Random;
import java.util.stream.LongStream;


@SpringBootApplication
@EnableEurekaClient
public class ModelsServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ModelsServiceApplication.class, args);
    }
    @Bean
    ApplicationRunner init(ModelRepository repository) {
        return args -> LongStream
                .range(1,20).forEach(i -> repository.save(getRandomModel(i)));
    }


    private String getRandomColor() {
        final Map colors = Color.getColors();
        final int randomInteger = getRandomInteger(colors.size());
        return colors.get(randomInteger).toString();
    }

    private Model getRandomModel(Long id) {
        Model model = new Model();
        final Map models = modelservice.enums.Models.getModels();
        final int randomInteger = getRandomInteger(models.size());
        Models modelsObject = (Models) models.get(randomInteger);
        model.setBody(modelsObject.getBody());
        model.setColor(getRandomColor());
        model.setEngine(modelsObject.getEngine());
        model.setManufacturer(modelsObject.getManufacturer());
        model.setModelYear(modelsObject.getModelYear());
        model.setNumberOfDoors(modelsObject.getNumberOfDoors());
        model.setName(modelsObject.getModel());
        model.setVehicleId(id);
        return model;
    }

    private int getRandomInteger(int size) {
        final int min = 1;
        Random r = new Random();
        return r.nextInt((size - min) + 1) + min;
    }
}
