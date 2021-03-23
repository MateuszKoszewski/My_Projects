package utils;

import car.Car;
import transactions.Transactions;
import user.User;
import utils.CarRentalUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortingManager{

    public void sortingCars(List <Car> list) {
        Scanner scanner = new Scanner(System.in);

        List<Car> listToPrint = list;
        while (true) {

            CarRentalUtils.printList(listToPrint);
            System.out.println("If you want to sort this list press s, if you want to go to previous menu press enter");
            String chooice = scanner.nextLine();
            if (chooice.equals("s")) {
                listToPrint=sortingCarsWithoutMessage(list);

            } else if (chooice.isBlank()) {
                break;
            } else {
                System.out.println("put the correct value");
            }

        }
    }
    public Car sortListOrChooseTheCar (List<Car> list, String action){
        List<Car> listToPrint = list;
        while (true) {

            CarRentalUtils.printList(listToPrint);
            System.out.println("If you want to sort list press s, to " + action + " car choose it from the list");

            String listPosition = CarRentalUtils.checkConditionsOrSort(listToPrint);
            if (!listPosition.equals("s")) {
                int choosednumber = Integer.parseInt(listPosition);

                return listToPrint.get(choosednumber-1);

            } else {
                listToPrint = sortingCarsWithoutMessage(list);
            }

        }
    }
    public List<Car> sortingCarsWithoutMessage(List<Car> list) {
        showCarsSortingOptions();
        Comparator<Car> choosedComparator= chooseCarsSortingOption();
        System.out.println("How do you want to sort?" +
                "\n1. ascending" +
                "\n2. descending");
        int wayOfSorting = (int) CarRentalUtils.checkConditions(value -> value > 0 && value <= 2, "choose option from the list", "put the correct value");

        Comparator <Car> toSort = (Comparator<Car>) CarRentalUtils.function.apply(wayOfSorting, choosedComparator);
        return list.stream().sorted(toSort).collect(Collectors.toList());
    }
    private Comparator<Car> chooseCarsSortingOption() {
        int chooice = (int) CarRentalUtils.checkConditions(choose -> choose > 0 && choose <= 5, "choose option from the list", "put the correct value");
        switch (chooice) {

            case 1:
                return Comparator.comparing(Car::getBrand);
            case 2:
                return Comparator.comparing(Car::getModel);
            case 3:
                return Comparator.comparing(Car::getProductionYear);
            case 4:
                return Comparator.comparing(Car::getPrice);
            case 5:
                return Comparator.comparing(Car::getCourse);
            default:
                return null;
        }
    }

    private void showCarsSortingOptions() {
        System.out.println("Sort by" +
                "\n1. Brand" +
                "\n2. Model" +
                "\n3. Production Year" +
                "\n4. Price" +
                "\n5. Mileage");
    }

    private void showUsersSortingOptions() {
        System.out.println("Sort By" +
                "\n1. Name" +
                "\n2. Last name" +
                "\n3. Age" +
                "\n4. Number of rented cars at this moment" +
                "\n5. Number of rented cars in total");
    }

    private Comparator<User> chooseUserSortingOption() {
        int chooice = (int) CarRentalUtils.checkConditions(choose -> choose > 0 && choose <= 5, "choose option from the list", "put the correct value");
        switch (chooice) {
            case 1:
                return Comparator.comparing(User::getName);
            case 2:
                return Comparator.comparing(User::getLast_name);
            case 3:
                return Comparator.comparing(User::getAge);
            case 4:
                return Comparator.comparing(user -> user.getRentedCars().size());
            case 5:
                return Comparator.comparing(user -> user.getTransactionsList().size());
            default:
                return null;
        }
    }


    public void sortingUsers(List<User> list) {
        Scanner scanner = new Scanner(System.in);


List<User> listToPrint=list;

        while (true) {

            CarRentalUtils.printList(listToPrint);
            System.out.println("If you want to sort this list press s, if you want to go to previus menu press enter");
            String chooice = scanner.nextLine();

            if (chooice.isBlank()) {
                break;
            } else if (chooice.equals("s")) {
                listToPrint=sortingUsersWithoutMessage(list);
            } else {
                System.out.println("invalid value");
            }
        }
    }
    public User sortListOrChooseTheUser (List<User> list, String action) {
        List<User> listToPrint = list;
        while (true) {

            CarRentalUtils.printList(listToPrint);
            System.out.println("If you want to sort list press s, to " + action + " user choose it from the list");

            String listPosition = CarRentalUtils.checkConditionsOrSort(listToPrint);
            if (!listPosition.equals("s")) {
                int choosednumber = Integer.parseInt(listPosition);

                return listToPrint.get(choosednumber - 1);

            } else {
                listToPrint = sortingUsersWithoutMessage(list);
            }

        }
    }
    private List<User> sortingUsersWithoutMessage(List<User> list) {
        showUsersSortingOptions();
        Comparator<User> choosedComparator = chooseUserSortingOption();
        System.out.println("How do you want to sort?" +
                "\n1. ascending" +
                "\n2. descending");
        int wayOfSorting = (int) CarRentalUtils.checkConditions(value -> value > 0 && value <= 2, "choose option from the list", "put the correct value");
        Comparator<User> toSort = (Comparator<User>) CarRentalUtils.function.apply(wayOfSorting, choosedComparator);
        return list.stream().sorted(toSort).collect(Collectors.toList());

    }

    public void sortingTransactions(List <Transactions> list) {
        Scanner scanner = new Scanner(System.in);
        List<Transactions> listToPrint = list;


        while (true) {
            CarRentalUtils.showTransactions(listToPrint);
            System.out.println("If you want to sort this list press s, if you want to go to previous menu press enter");
            System.out.println();
            String chooice = scanner.nextLine();
            if (chooice.equals("s")) {
                showTransactionSortingOptions();
                Comparator<Transactions> choosedComparator = chooseTransactionsSortingOption();
                System.out.println("How do you want to sort?" +
                        "\n1. ascending" +
                        "\n2. descending");
                int wayOfSorting = (int) CarRentalUtils.checkConditions(value -> value > 0 && value <= 2, "choose option from the list", "put the correct value");

                Comparator<Transactions> toSort = (Comparator<Transactions>) CarRentalUtils.function.apply(wayOfSorting, choosedComparator);
                List<Transactions> sortedList = list.stream().sorted(toSort).collect(Collectors.toList());
                listToPrint=sortedList;

            } else if (chooice.isBlank()) {
                break;
            } else {
                System.out.println("put the correct value");
            }

        }
    }
    public Transactions sortListOrChooseTheTransaction (List<Transactions> list, String action){
        List<Transactions> listToPrint = list;
        while (true) {

            CarRentalUtils.showTransactions(listToPrint);
            System.out.println("If you want to sort list press s, to " + action + " transaction choose it from the list");

            String listPosition = CarRentalUtils.checkConditionsOrSort(listToPrint);
            if (!listPosition.equals("s")) {
                int choosednumber = Integer.parseInt(listPosition);

                return listToPrint.get(choosednumber-1);

            } else {
                listToPrint = sortingTransactionsWithoutMessage(list);
            }

        }
    }
    public List<Transactions> sortingTransactionsWithoutMessage(List<Transactions> list) {
        showTransactionSortingOptions();
        Comparator<Transactions> choosedComparator= chooseTransactionsSortingOption();
        System.out.println("How do you want to sort?" +
                "\n1. ascending" +
                "\n2. descending");
        int wayOfSorting = (int) CarRentalUtils.checkConditions(value -> value > 0 && value <= 2, "choose option from the list", "put the correct value");

        Comparator <Transactions> toSort = (Comparator<Transactions>) CarRentalUtils.function.apply(wayOfSorting, choosedComparator);
        return list.stream().sorted(toSort).collect(Collectors.toList());
    }
    private Comparator<Transactions> chooseTransactionsSortingOption() {
        int chooice = (int) CarRentalUtils.checkConditions(choose -> choose > 0 && choose <= 10, "choose option from the list", "put the correct value");
        switch (chooice) {

            case 1:
                return Comparator.comparing(transaction -> transaction.getUser().getName());
            case 2:
                return Comparator.comparing(transaction -> transaction.getUser().getLast_name());
            case 3:
                return Comparator.comparing(transaction -> transaction.getUser().getAge());
            case 4:
                return Comparator.comparing(transaction -> transaction.getCar().getBrand());
            case 5:
                return Comparator.comparing(transaction -> transaction.getCar().getModel());
            case 6:
                return Comparator.comparing(transaction -> transaction.getCar().getRegistrationNumber());
            case 7:
                return Comparator.comparing(Transactions::getDate);
            case 8:
                return Comparator.comparing(Transactions::getDays);
            case 9:
                return Comparator.comparing(Transactions::getDateofReturn);
            case 10:
                return Comparator.comparing(Transactions::getPrice);
            default:
                return null;
        }
    }

    private void showTransactionSortingOptions() {
        System.out.println("Sort by" +
                "\n1. Name of the user" +
                "\n2. Last name of the user" +
                "\n3. User age" +
                "\n4. Brand of the Car" +
                "\n5. Model of the Car" +
                "\n6. Registration number of the Car" +
                "\n7. Date of transaction" +
                "\n8. Days of transaction" +
                "\n9. Date of returning Car" +
                "\n10. Price of transaction");
    }

}
