package com.udacity.vehicles.domain.car;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Details {

    @Transient
    private String manufacturer;

    @Transient
    private String model;

    @Transient
    private String color;

    @Transient
    private int modelYear;

    @Transient
    private String body;

    @Transient
    private int numberOfDoors;

    @Transient
    private String engine;


    public int getModelYear() { return modelYear; }

    public void setModelYear(int modelYear) { this.modelYear = modelYear; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public int getNumberOfDoors() { return numberOfDoors; }

    public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    public String getEngine() { return engine; }

    public void setEngine(String engine) { this.engine = engine; }

    public String getModel() { return model; }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
