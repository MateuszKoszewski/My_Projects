package transactions;

import car.Car;
import user.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Transactions {
    private double price;
    private User user;
    private Car car;
    private int days;
    private LocalDate date;
    private String dateofReturn;

    public Transactions(double price, User user, Car car, int days, LocalDate date) {
        this.price = price;
        this.user = user;
        this.car = car;
        this.days = days;
        this.date=date;
        this.dateofReturn = this.date.plus(Period.ofDays(this.days)).toString();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateofReturn() {
        return dateofReturn;
    }

    public void setDateofReturn(String dateofReturn) {
        this.dateofReturn = dateofReturn;
    }

    public double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Double.compare(that.price, price) == 0 &&
                days == that.days &&
                Objects.equals(user, that.user) &&
                Objects.equals(car, that.car) &&
                Objects.equals(date, that.date) &&
                Objects.equals(dateofReturn, that.dateofReturn);
    }


}
