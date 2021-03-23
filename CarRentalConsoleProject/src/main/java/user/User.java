package user;

import car.Car;
import transactions.Transactions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String last_name;
    private String ID;
    private int age;
    private List<Car> rentedCars;
    private UserValidator userValidator;
    private List<Transactions> transactionsList;
    private String carOrCantBeDeleted;


    public User(String name, String last_name, String ID) {
        userValidator = new UserValidator();
        this.name = name;
        this.last_name = last_name;
        this.ID = ID;
        this.age = LocalDate.now().getYear() - userValidator.parseIntoDate(ID).getYear();
        rentedCars = new ArrayList<>();
        transactionsList = new ArrayList<>();

    }

    public String getCarOrCantBeDeleted() {
        return carOrCantBeDeleted;
    }

    public void setCarOrCantBeDeleted(String carOrCantBeDeleted) {
        this.carOrCantBeDeleted = carOrCantBeDeleted;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(ID, user.ID) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, last_name, ID, age, rentedCars, userValidator, transactionsList, carOrCantBeDeleted);
    }

    @Override
    public String toString() {
       return name + " " + last_name + ", " + "ID: " + ID + ", Date of birth: " + userValidator.parseIntoDate(ID) + ". Cars rented at this moment: " + rentedCars.size() + " ,cars rented in total: " + transactionsList.size();

    }

    public String getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public List<Car> getRentedCars() {
        return rentedCars;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }


}
