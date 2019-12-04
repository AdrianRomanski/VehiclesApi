package modelservice.enums;

import java.util.HashMap;
import java.util.Map;



public enum Models {
     PEUGOT_108(1, "108", "hatchback", "1.0 72hp", 2014, 3, "Peugot"),
     PEUGOT_208(2, "208", "hatchback", "1.2 82hp", 2012, 5, "Peugot"),
     PEUGOT_308(3, "308", "sedan", "2.0 143hp", 2011, 4, "Peugot"),
     PEUGOT_408(4, "408", "saloon", "2.0 147hp", 2010, 4, "Peugot"),
     PEUGOT_1007(5, "1007", "hatchback", "1.4 108hp", 2009, 3, "Peugot"),
     PORSCHE_911(6, "911", "coupe", "3.0 220hp", 2018, 2,"Porsche"),
     PORSCHE_CAYENNE(7,"Cayenne", "SUV", "3.0 182.8hp", 2018, 5,"Porsche"),
     PORSCHE_CAYENNE_TURBO(8,"Cayenne Turbo", "SUV", "4.0 243.9hp", 2018, 5,"Porsche"),
     PORSCHE_PANAMERA(9, "Panamera", "liftback", "3.0 296hp", 2016, 5,"Porsche"),
     PORSCHE_PANAMERA_TURBO_S(10, "Panamera Turbo S", "liftback", "4.8 542hp", 2016, 5, "Porsche"),
     BMW_E39(11, "E39", "sedan", "3.0 189hp", 2004, 4, "BMW"),
     BMW_Z8(12, "Z8", "roadster", "4.9 375hp", 2003, 2, "BMW"),
     BMW_M2(13, "M2", "coupe", "3.0 365hp", 2014, 2, "BMW"),
     BMW_X5(14, "X5", "SUV", "4.0 555hp", 2010, 5, "BMW"),
     BMW_I8(15, "I8", "roadster", "1.5 231 PS 228 hp", 2019, 2, "BMW");


     private final int number;
     private final String model;
     private final String body;
     private final String engine;
     private final int modelYear;
     private final int numberOfDoors;
     private final String manufacturer;


      Models(int number, String model, String body, String engine, int modelYear, int numberOfDoors, String manufacturer) {
         this.number = number;
         this.model = model;
         this.body = body;
         this.engine = engine;
         this.modelYear = modelYear;
         this.numberOfDoors = numberOfDoors;
          this.manufacturer = manufacturer;
      }

     private static final Map<Integer, Models> models = new HashMap<>();

     static {
         for (Models p : Models.values()) {
             models.put(p.number, p);
         }
     }

    public static Map<Integer, Models> getModels() { return Map.copyOf(models); }

    public String getManufacturer() { return manufacturer; }

    public int getNumber() { return number; }

    public String getModel() { return model; }

    public String getBody() { return body; }

    public String getEngine() { return engine; }

    public int getModelYear() { return modelYear; }

    public int getNumberOfDoors() { return numberOfDoors; }
}
