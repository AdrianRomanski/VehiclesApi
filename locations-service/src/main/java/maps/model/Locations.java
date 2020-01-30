package maps.model;

import java.util.HashMap;
import java.util.Map;

public enum Locations {
    BROCKTON_AVENUE_777(1,"Brockton Avenue", 777, "Abington", "MA", 2351);

    private final int id;
    private final String address;
    private final int houseNo;
    private final  String city;
    private final  String state;
    private final  int zipCode;

    Locations(int number, String address, int houseNo,
              String city, String state, int zipCode) {
        this.id = number;
        this.address = address;
        this.houseNo = houseNo;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    private static final Map<Integer, Locations> adresses = new HashMap<>();

    static {
        for (Locations a : Locations.values()) {
            adresses.put(a.id, a);
        }
    }

    public int getNumber() {
        return id;
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

    public static Map<Integer, Locations> getAdresses() {
        return adresses;
    }
}
