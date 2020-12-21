package edu.umb.cs681.hw21;

public class VideoCard {
     private String name;
     private String GPU;
     private String manufacturer;
     private float price;

     public VideoCard(String name, String GPU, String manufacturer, float price) {
         this.name = name;
         this.GPU = GPU;
         this.manufacturer = manufacturer;
         this.price = price;

     }
     public String getName() {
         return name;
     }

    public String getGPU() {
        return GPU;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getPrice() {
        return price;
    }
}
