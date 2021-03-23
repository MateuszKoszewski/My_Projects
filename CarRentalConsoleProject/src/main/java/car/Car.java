package car;

import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private int productionYear;
    private double price;
    private int course;
    private String registrationNumber;

    public Car(String brand, String model, int productionYear, double price, int course, String registrationNumber) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.price = price;
        this.course = course;
        this.registrationNumber=registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getCourse() {
        return course;
    }

    public int getProductionYear() {
        return productionYear;
    }

    @Override
    public String toString() {
        return brand + " " + model + " " + registrationNumber + " " + productionYear + " " + price + " " + course;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return
        Objects.equals(registrationNumber.toLowerCase(), car.registrationNumber.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, productionYear, price, course, registrationNumber);
    }
}
