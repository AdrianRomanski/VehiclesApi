package maps.model;

import java.util.HashMap;
import java.util.Map;

public enum Locations {
    BROCKTON_AVENUE_777(1,"777 Brockton Avenue", "Abington", "MA", "2351"),
    MEMORIAL_DRIVE_30(2, "30 Memorial Drive", "Avon", "MA", "2322"),
    HARTFROD_AVENUE_250(3, "250 Hartford Avenue", "Bellingham" , "MA", "2019"),
    OAK_STREET_900(4, "700 Oak Street", "Brockton", "MA", "2301"),
    PARKHURST_RD_66_4(5, "66-4 Parkhurst Rd", "Chelmsford", "MA", "1824");

    private final int id;
    private final String address;
    private final  String city;
    private final  String state;
    private final  String zipCode;

    Locations(int number, String address,
              String city, String state, String zipCode) {
        this.id = number;
        this.address = address;
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public static Map<Integer, Locations> getAdresses() {
        return adresses;
    }
}
