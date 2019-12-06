package com.udacity.vehicles.api;


import com.udacity.vehicles.domain.enums.*;
import com.udacity.vehicles.domain.customcar.CustomCar;
import com.udacity.vehicles.domain.customcar.CustomCarDetails;
import com.udacity.vehicles.domain.customcar.CustomLocation;
import com.udacity.vehicles.service.CustomCarService;
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

import java.net.URI;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CustomCarControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<CustomCar> json;

        @MockBean
        private CustomCarService customCarService;


        /**
         * Creates pre-requisites for testing, such as an example car.
         */
        @Before
        public void setup() {
            CustomCar customCar = getCar();
            customCar.setId(1L);
            given(customCarService.save(any())).willReturn(customCar);
            given(customCarService.findById(any())).willReturn(customCar);
            given(customCarService.list()).willReturn(Collections.singletonList(customCar));
        }

        /**
         * Tests for successful creation of new car in the system
         * @throws Exception when car creation fails in the system
         */
        @Test
        public void createCar() throws Exception {
            CustomCar customCar = getCar();
            mvc.perform(
                    post(new URI("/custom-car"))
                            .content(json.write(customCar).getJson())
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

            mvc.perform(get("/custom-car").header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.*.customCarList", hasSize(1)))
                    .andExpect(jsonPath("$.*.customCarList[0].id").value(1))
                    .andExpect(jsonPath("$.*.customCarList[0].details.model").value("RS6 C8"))
                    .andExpect(jsonPath("$.*.customCarList[0].customLocation.country").value("Australia"))
                    .andExpect(jsonPath("$.*.customCarList[0].currency").value("AUSTRALIAN_DOLLAR"))
                    .andExpect(jsonPath("$.*.customCarList[0].price").value(125000.0));
        }


        /**
         * Tests the read operation for a single car by ID.
         * @throws Exception if the read operation for a single car fails
         */
        @Test
        public void findCar() throws Exception {

            CustomCar customCar = getCar();

            mvc.perform(get("/custom-car/" + customCar.getId()).header("Content-Type",MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.details.model").value("RS6 C8"))
                    .andExpect(jsonPath("$.customLocation.country").value("Australia"))
                    .andExpect(jsonPath("$.currency").value("AUSTRALIAN_DOLLAR"))
                    .andExpect(jsonPath("$.price").value(125000.0));
        }

        /**
         * Tests the deletion of a single car by ID.
         * @throws Exception if the delete operation of a vehicle fails
         */
        @Test
        public void deleteCar() throws Exception {

            CustomCar customCar = getCar();

            mvc.perform(delete("/custom-car/" + customCar.getId()).header("Content-Type",
                    MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNoContent());
        }

        /**
         * Creates an example CustomCar object for use in testing.
         * @return an example CustomCar object
         */
        private CustomCar getCar() {
            CustomCar customCar = new CustomCar();

            CustomLocation customLocation = new CustomLocation();
            customLocation.setAddress("Australian Street 1 house no 12");
            customLocation.setCity("Melbourne");
            customLocation.setCountry("Australia");
            customLocation.setState("Australian State");
            customLocation.setZip("442-342");
            customCar.setCustomLocation(customLocation);

            CustomCarDetails customDetails = new CustomCarDetails();
            customCar.setCurrency(Currency.AUSTRALIAN_DOLLAR);
            customCar.setPrice(125000);
            customCar.setId(1L);
            customDetails.setManufacturer(Manufacturer.Audi);
            customDetails.setModel("RS6 C8");
            customDetails.setMileage(32280);
            customDetails.setExternalColor(Color.Black);
            customDetails.setBody(Body.Saloon);
            customDetails.setEngine("V10 5,0 l");
            customDetails.setFuelType(Fuel.Gasoline);
            customDetails.setModelYear(2013);
            customDetails.setProductionYear(2017);
            customDetails.setNumberOfDoors(4);
            customCar.setDetails(customDetails);
            customCar.setCondition(Condition.USED);

            return customCar;
        }
    }

