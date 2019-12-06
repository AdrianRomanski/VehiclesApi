package models;

import modelservice.ModelsServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ModelsServiceApplication.class)
@AutoConfigureMockMvc
public class ModelsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getRandomModel() throws Exception {
        mvc.perform(get("/models/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.manufacturer").isString())
                .andExpect(jsonPath("$.color").isString())
                .andExpect(jsonPath("$.vehicleId").isNumber())
                .andExpect(jsonPath("$.body").isString())
                .andExpect(jsonPath("$.engine").isString())
                .andExpect(jsonPath("$.modelYear").isNumber())
                .andExpect(jsonPath("$.numberOfDoors").isNumber());
    }
}