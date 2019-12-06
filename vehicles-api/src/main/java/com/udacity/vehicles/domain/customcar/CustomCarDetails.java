package com.udacity.vehicles.domain.customcar;

import com.udacity.vehicles.domain.enums.Body;
import com.udacity.vehicles.domain.enums.Color;
import com.udacity.vehicles.domain.enums.Fuel;
import com.udacity.vehicles.domain.enums.Manufacturer;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

/**
 * Declares the additional detail variables for each Car object,
 * along with related methods for access and setting.
 * validation from enum classes
 */
@Embeddable
public class CustomCarDetails {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Body body;

    @NotNull
    private String model;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Color externalColor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Fuel fuelType;

    @NotNull
    @Min(1886)
    @Max(2019)
    private Integer productionYear;

    @NotNull
    @Min(1886)
    @Max(2019)
    private Integer modelYear;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer numberOfDoors;

    @NotNull
    private String engine;

    @NotNull
    @Min(0)
    @Max(999999)
    private Integer mileage;

    public Color getExternalColor() { return externalColor; }

    public void setExternalColor(Color externalColor) { this.externalColor = externalColor; }

    public Fuel getFuelType() { return fuelType; }

    public void setFuelType(Fuel fuelType) { this.fuelType = fuelType; }

    public Manufacturer getManufacturer() { return manufacturer; }

    public void setManufacturer(Manufacturer manufacturer) { this.manufacturer = manufacturer; }

    public Body getBody() { return body; }

    public void setBody(Body body) { this.body = body; }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

}
