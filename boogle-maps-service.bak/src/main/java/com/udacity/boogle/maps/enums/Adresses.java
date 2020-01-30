package com.udacity.boogle.maps.enums;

import java.util.HashMap;
import java.util.Map;

public enum Adresses {
    BROCKTON_AVENUE_777(1,"Brockton Avenue", 777, "Abington", "MA", 2351);

    private final int number;
    private final String address;
    private final int houseNo;
    private final  String city;
    private final  String state;
    private final  int zipCode;

    Adresses(int number, String address, int houseNo,
             String city, String state, int zipCode) {
        this.number = number;
        this.address = address;
        this.houseNo = houseNo;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    private static final Map<Integer, Adresses> adresses = new HashMap<>();

    static {
        for (Adresses a : Adresses.values()) {
            adresses.put(a.number, a);
        }
    }

    public int getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public static Map<Integer, Adresses> getAdresses() {
        return adresses;
    }
}
