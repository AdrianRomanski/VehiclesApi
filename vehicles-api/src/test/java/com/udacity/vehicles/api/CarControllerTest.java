package com.udacity.vehicles.api;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.car.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.enums.Condition;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Implements testing of the CarController class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockBean
    private CarService carService;



    /**
     * Creates pre-requisites for testing, such as an example car.
     */
    @Before
    public void setup() {
        Car car = getCar();
        car.setId(1L);
        given(carService.save(any())).willReturn(car);
        given(carService.findById(any())).willReturn(car);
        given(carService.list()).willReturn(Collections.singletonList(car));
    }

    /**
     * Tests for successful creation of new car in the system
     * @throws Exception when car creation fails in the system
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    /**
     * Tests if the read operation appropriately returns a list of vehicles.
     * @throws Exception if the read operation of the vehicle list fails
     */
    @Test
    public void listCars() throws Exception {

        mvc.perform(get("/cars").header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.carList", hasSize(1)))
                .andExpect(jsonPath("$.*.carList[0].id").value(1))
                .andExpect(jsonPath("$.*.carList[0].details.model").value("Multipla"))
                .andExpect(jsonPath("$.*.carList[0].location.state").value("Ohio"))
                .andExpect(jsonPath("$.*.carList[0].currency").value("US Dollars"))
                .andExpect(jsonPath("$.*.carList[0].price").value("4200"));
    }

    /**
     * Tests the read operation for a single car by ID.
     * @throws Exception if the read operation for a single car fails
     */
    @Test
    public void findCar() throws Exception {

        Car car = getCar();

        mvc.perform(get("/cars/" + car.getId()).header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.details.model").value("Multipla"))
                .andExpect(jsonPath("$.location.state").value("Ohio"))
                .andExpect(jsonPath("$.currency").value("US Dollars"))
                .andExpect(jsonPath("$.price").value("4200"));
    }

    /**
     * Tests the deletion of a single car by ID.
     * @throws Exception if the delete operation of a vehicle fails
     */
    @Test
    public void deleteCar() throws Exception {

        Car car = getCar();

        mvc.perform(delete("/cars/" + car.getId()).header("Content-Type",
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNoContent());
    }


    @Test
    public void updateCar() throws Exception {

        Car car = getCar();
        mvc.perform(get("/cars/" + car.getId()).header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(1))
                 .andExpect(jsonPath("$.condition").value("NEW"))
                 .andExpect(jsonPath("$.price").value("4200"))
                 .andExpect(jsonPath("$.currency").value("US Dollars"))
                 .andExpect(jsonPath("$.location.state").value("Ohio"));

                 car.setCondition(Condition.USED);
                 car.setPrice("3800");
                 car.setCurrency("Euro");
                 car.getLocation().setState("New York");

                 given(carService.save(any())).willReturn(car);

        mvc.perform(put("/cars/" + car.getId()).header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json.write(car).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.condition").value("USED"))
                .andExpect(jsonPath("$.price").value("3800"))
                .andExpect(jsonPath("$.currency").value("Euro"))
                .andExpect(jsonPath("$.location.state").value("New York"));


    }

    /**
     * Creates an example Car object for use in testing.
     * @return an example Car object
     */
    private Car getCar() {

        Car car = new Car();

        Location location = new Location();

        location.setState("Ohio");
        location.setAddress("Warm street 22 house no 4");
        location.setCity("Cleveland");
        location.setZip("4442");
        car.setLocation(location);

        Details details = new Details();
        details.setModelYear(2004);
        details.setModel("Multipla");
        details.setColor("Violet");
        details.setBody("Mini van");
        details.setEngine("1.6 128hp");
        details.setNumberOfDoors(5);
        details.setManufacturer("Fiat");
        car.setDetails(details);

        car.setCondition(Condition.NEW);
        car.setCurrency("US Dollars");
        car.setPrice("4200");
        car.setId(1L);

        return car;
    }
}