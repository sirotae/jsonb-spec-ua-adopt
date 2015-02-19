package myexamples.domain;

public class Product {
    private String name;
    private Double price;
    private String Description;
    private String pathToPict;

    private Brand brand;

    public String getPathToPict() {
        return pathToPict;
    }

    public void setPathToPict(String pathToPict) {
        this.pathToPict = pathToPict;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}