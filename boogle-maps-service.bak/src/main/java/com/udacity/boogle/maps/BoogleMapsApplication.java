package com.udacity.boogle.maps;

import com.udacity.boogle.maps.model.Address;
import com.udacity.boogle.maps.repositories.AddressRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class BoogleMapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoogleMapsApplication.class, args);
	}
	@Bean
	ApplicationRunner init(AddressRepository repository) {
		return args -> LongStream
				.range(1,20).forEach(i -> repository.save(new Address(i,"Hola", "Soy", "Su", "Adress")));

	}


}
