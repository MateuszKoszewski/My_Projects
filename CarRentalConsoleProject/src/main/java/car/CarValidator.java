package car;

import utils.CarRentalUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CarValidator {
    private int minProductionYear;
    private int maxMileage;


    public CarValidator() {
minProductionYear = 1950;
maxMileage=350000;
    }

    public void setMinProductionYear(int minProductionYear) {
        this.minProductionYear = minProductionYear;
    }

    public void setMaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }

    public int getMinProductionYear() {
        return minProductionYear;
    }

    public int getMaxMileage() {
        return maxMileage;
    }

    public double validatePrice() {
        return CarRentalUtils.checkConditions(price -> price > 0, "price must be > 0", "put the correct price");
    }

    public int validateProductionYear() {
        return (int) CarRentalUtils.checkConditions(productionYear -> productionYear > minProductionYear && productionYear <= 2020, "production year must be greater than 1950 and less than 2020", "put the correct production year");
    }

    public int validateCourse() {
        return (int) CarRentalUtils.checkConditions(course -> course > 0 && course < maxMileage, "Too high course to add the Car.Car", "put the correct course");
    }

    public String validateRegistrationNumber() {
        Scanner scanner = new Scanner(System.in);
        String registrationNumber = "";
        while (true) {
            registrationNumber = scanner.nextLine();
            Pattern compile = Pattern.compile("^[A-Za-z]{2,3}\\d{3,5}[A-Za-z]{0,2}$");
            Matcher matcher = compile.matcher(registrationNumber);
            if (matcher.find()) {
                registrationNumber = matcher.group(0);
                break;
            } else {
                System.out.println("wrong format of registration number, try again");
            }
        }
        return registrationNumber.toUpperCase();
    }

}
