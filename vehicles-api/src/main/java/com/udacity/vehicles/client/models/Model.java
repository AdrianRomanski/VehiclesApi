package com.udacity.vehicles.client.models;

/**
 * Declares a class to store an manufacturer, name, color, modelYear, body,
 * numberOfDoors, engine and vehicleId
 */
public class Model {

    private String manufacturer;
    private String name;
    private String color;
    private int modelYear;
    private String body;
    private int numberOfDoors;
    private String engine;
    private Long vehicleId;


    public Model() {
    }

    public int getModelYear() { return modelYear; }

    public void setModelYear(int modelYear) { this.modelYear = modelYear; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public int getNumberOfDoors() { return numberOfDoors; }

    public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    public String getEngine() { return engine; }

    public void setEngine(String engine) { this.engine = engine; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
