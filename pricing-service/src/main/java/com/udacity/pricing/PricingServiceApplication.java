package com.udacity.pricing;

import com.udacity.pricing.model.Currency;
import com.udacity.pricing.model.Price;
import com.udacity.pricing.repositories.PriceRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;


@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PriceRepository repository) {
        return args -> LongStream
                .range(1, 20).forEach(i -> repository.save(generate(i)));
    }

    private BigDecimal randomPriceInDollars() {
        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
    }

    private int getRandomInteger() {
        final int min = 1;
        final int max = Currency.getCurrencies().size();
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private Price generate(Long vehicleId) {
        final Map<Integer, Currency> currencies = Currency.getCurrencies();
        int randomInt = getRandomInteger();
        BigDecimal randomPrice = randomPriceInDollars();
        double exchangeRate = currencies.get(randomInt).getExchangeRate();
        String currency = currencies.get(randomInt).toString();
        double price = exchangeRate * randomPrice.doubleValue();
        double finalPrice = (double)Math.round(price * 100d) / 100d;
        return new Price(currency, new BigDecimal(finalPrice),vehicleId);
    }
}
