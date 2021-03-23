package car;

import transactions.Transactions;
import transactions.TransactionsManager;
import user.User;
import user.UserManager;
import user.messages.Message;
import user.messages.MessageManager;
import utils.CarRentalUtils;
import utils.SortingManager;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CarManager {
    private final List<Car> availableCars;
    private final List<Transactions> rentedCars;
    private final CarValidator carValidator;


    public CarManager() {
        this.availableCars = new ArrayList<>();
        rentedCars = new ArrayList<>();
        carValidator = new CarValidator();

    }

    public CarValidator getCarValidator() {
        return carValidator;
    }

    public List<Car> getAvailableCars() {
        return availableCars;
    }

    public List<Transactions> getRentedCars() {
        return rentedCars;
    }

    public void showListOfCars(SortingManager sortingManager) {

//        for (int i = 0; i < availableCars.size(); i++) {
//            System.out.println(i + 1 + ". " + availableCars.get(i));
//        }
//        System.out.println();

        sortingManager.sortingCars(availableCars);
    }

//    public void editRentalPrice() {
//
//        showListOfCars();
//        System.out.println("which car price would you like to change");
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            if (scanner.hasNextInt()) {
//                int choice = scanner.nextInt();
//                if (choice > 0 && choice <= availableCars.size()) {
//                    System.out.println("set your price for " + availableCars.get(choice - 1).getBrand() + " " + availableCars.get(choice - 1).getModel());
//                    availableCars.get(choice - 1).setPrice(carValidator.validatePrice());
//                    break;
//                } else {
//                    System.out.println("choose the car from the list");
//                }
//
//            } else {
//                System.out.println("put the number of car from the list above");
//                scanner.nextLine();
//            }
//        }
//    }

    public void addNewCar() {
        System.out.println("Specify Brand of the car");
        String brand = CarRentalUtils.StringIsNotBlank();
        System.out.println("Specify Model of the car");
        String model = CarRentalUtils.StringIsNotBlank();
        System.out.println("Specify registration number of the car");
        String registrationNumber = carValidator.validateRegistrationNumber();
        System.out.println("Specify Production year of the car");
        int productionYear = carValidator.validateProductionYear();
        System.out.println("Specify Price of the car");
        double price = carValidator.validatePrice();
        System.out.println("Specify Course of the car");
        int course = carValidator.validateCourse();
        Car newCar = new Car(brand, model, productionYear, price, course, registrationNumber);
        if (!availableCars.contains(newCar)) {
            availableCars.add(newCar);
        } else {
            System.out.println("car already exists");
        }
    }

    public void rentACar(List<User> availableUsers, List<Transactions> transactionsList, SortingManager sortingManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("select User");
        User choosedUser = sortingManager.sortListOrChooseTheUser(availableUsers, "choose");
        System.out.println("select Car");
        Car rentedCar = sortingManager.sortListOrChooseTheCar(availableCars, "choose");
        System.out.println("your choice is " + rentedCar.getBrand());
        System.out.println("for how many days?");
        int choiceOfDays = (int) CarRentalUtils.checkConditions((variable -> variable > 0), "number of days must be >0", "put the correct value");
        double price = choiceOfDays * rentedCar.getPrice();
        System.out.println("The rent will cost " + price + ". Are you sure? yes/no");
        String agreedOrNot = scanner.nextLine();
        if (agreedOrNot.toLowerCase().equals("yes") || agreedOrNot.toLowerCase().equals("y")) {
            choosedUser.getRentedCars().add(rentedCar);
            Transactions newTransaction = new Transactions(price, choosedUser, rentedCar, choiceOfDays, LocalDate.now());
            transactionsList.add(newTransaction);
            rentedCars.add(newTransaction);
            availableCars.remove(rentedCar);

            availableUsers.forEach(user -> {
                if (user.equals(choosedUser)) {
                    user.getTransactionsList().add(newTransaction);
                }
            });


        } else if (agreedOrNot.toLowerCase().equals("no") || agreedOrNot.toLowerCase().equals("n")) {

        } else {
            System.out.println("you must enter yes or no");
        }

    }

    public void listAllRentedCars() {
        CarRentalUtils.showTransactions(rentedCars);
    }


    public void returnACar(List<Transactions> transactionsList, List<Message> listOfMessages, UserManager userManager, SortingManager sortingCarManager) {

        System.out.println("Which car would you like to return? Choose the car or press s to sort the list");

        System.out.println();

        Transactions choosedTransaction = sortingCarManager.sortListOrChooseTheTransaction(rentedCars, "rent");
        Car returnedCar = choosedTransaction.getCar();
        availableCars.add(returnedCar);
        User choosedUser = choosedTransaction.getUser();
        userManager.getAvailableUsers().forEach(user -> {
            if (user.equals(choosedUser)) {
                user.getRentedCars().remove(returnedCar);
            }
        });
        Transactions transactionToRemove = rentedCars.stream().filter(transactions -> transactions.getCar().equals(returnedCar) && transactions.getUser().equals(choosedUser)).findFirst().orElseThrow(() -> new NullPointerException("Couldn't find car that you returned"));
        rentedCars.remove(transactionToRemove);
        Transactions transactionToChangeDateOfReturn = transactionsList.stream().filter(transaction -> transaction.equals(transactionToRemove)).findFirst().orElseThrow(() -> new NullPointerException("No transactionToRemove in transaction list"));
        transactionToChangeDateOfReturn.setDateofReturn("already returned");
        Message newMessage = new Message(transactionToRemove);
        for (int i = 0; i < listOfMessages.size(); i++) {
            if (listOfMessages.get(i).equals(newMessage)) {
                listOfMessages.remove(newMessage);
            }
        }


    }

    public void addCars() {
//        File file = new File("src/main/resources/archiwum.txt");
//        if (!file.exists()) {
        availableCars.add(new Car("Toyota", "Corolla", 2005, 140, 220000, "GKA18456"));
        availableCars.add(new Car("Honda", "Civic", 2007, 110, 205670, "GD1567R"));
        availableCars.add(new Car("Volkswagen", "Passat", 2010, 150, 178765, "GA105WC"));
        availableCars.add(new Car("Renault", "Laguna", 2009, 125, 201690, "GDA145654"));
        availableCars.add(new Car("Skoda", "Fabia", 2018, 200, 53678, "GD208RU"));
//        }
    }

    public void carManagerMenu(TransactionsManager transactionsManager, SortingManager sortingManager, UserManager userManager, MessageManager messageManager) {
        Scanner scanner = new Scanner(System.in);
        boolean carMenu = false;
        while (!carMenu) {
            System.out.println("1. List of available cars" +
                    "\n2. Add new car" +
                    "\n3. Remove car" +
                    "\n4. Edit car" +
                    "\n5. Rent car" +
                    "\n6. Return car" +
                    "\n7. List of all rented cars" +
                    "\n8. Set max mileage and min production year" +
                    "\n9. Back to previous menu");
            if (scanner.hasNextInt()) {
                int choosedOption = scanner.nextInt();
                if (choosedOption > 0 && choosedOption <= 9) {
                    switch (choosedOption) {
                        case 1:
                            System.out.println("you choosed 1. List available cars");
                            if (getAvailableCars().size() == 0) {
                                System.out.println("there aren't any available cars");
                            } else {
                                showListOfCars(sortingManager);
                            }
                            break;
                        case 2:
                            addNewCar();
                            break;
                        case 3:
                            if (getAvailableCars().size() == 0) {
                                System.out.println("there aren't any cars to remove. Add a car first");
                            } else {
                                Car carToRemove = sortingManager.sortListOrChooseTheCar(getAvailableCars(), "remove");
                                getAvailableCars().remove(carToRemove);
                                System.out.println(carToRemove.getBrand() + " " + carToRemove.getModel() + " has been removed");
                            }
                            break;
                        case 4:
                            if (getAvailableCars().size() == 0 && getRentedCars().size() == 0) {
                                System.out.println("there aren't any cars to edit. Add a car first");
                            } else {
                                List<Car> listOfAllCars = new ArrayList<>();
                                listOfAllCars.addAll(getAvailableCars());
                                getRentedCars().forEach(car -> listOfAllCars.add(car.getCar()));

                                Car carToEdit = sortingManager.sortListOrChooseTheCar(listOfAllCars, "edit");

                                boolean editCarmenu = false;
                                while (!editCarmenu) {
                                    System.out.println("1. Edit brand" +
                                            "\n2. Edit model" +
                                            "\n3. Edit production year" +
                                            "\n4. Edit price" +
                                            "\n5. Edit mileage" +
                                            "\n6. Back to previous menu");
                                    if (scanner.hasNextInt()) {
                                        int choosedNumberOfAction = scanner.nextInt();
                                        switch (choosedNumberOfAction) {
                                            case 1:
                                                System.out.println("set new brand for this car");
                                                String newBrand = CarRentalUtils.StringIsNotBlank();
                                                carToEdit.setBrand(newBrand);
                                                break;
                                            case 2:
                                                System.out.println("set new model for this car");
                                                String newModel = CarRentalUtils.StringIsNotBlank();
                                                carToEdit.setModel(newModel);
                                                break;
                                            case 3:
                                                System.out.println("set new production year for this car");
                                                int newProductionYear = getCarValidator().validateProductionYear();
                                                carToEdit.setProductionYear(newProductionYear);
                                                break;
                                            case 4:
                                                System.out.println("set new price for this car");
                                                double newPrice = getCarValidator().validatePrice();
                                                carToEdit.setPrice(newPrice);
                                                break;
                                            case 5:
                                                System.out.println("set new mileage for this car");
                                                int newMileage = getCarValidator().validateCourse();
                                                carToEdit.setCourse(newMileage);
                                                break;

                                            case 6:
                                                editCarmenu = true;
                                                transactionsManager.getTransactions().forEach(transaction -> {
                                                    if (transaction.getCar().equals(carToEdit)) {
                                                        transaction.setCar(carToEdit);
                                                    }
                                                });
                                        }
                                    }
                                }
                            }
                            break;
                        case 5:
                            rentACar(userManager.getAvailableUsers(), transactionsManager.getTransactions(), sortingManager);
                            break;
                        case 6:
                            returnACar(transactionsManager.getTransactions(), messageManager.getListOfMessages(), userManager, sortingManager);
                            break;
                        case 7:
                            System.out.println("you choosed 7. List all rented cars");
                            if (getRentedCars().size() == 0) {
                                System.out.println("There aren't any rented cars");
                            } else {
                                listAllRentedCars();
                            }
                            break;
                        case 8:
                            setMaxProductionYearAndMinMileage();
                            break;

                        case 9:
                            carMenu = true;
                    }
                }
            }
        }

    }

    public void setMaxProductionYearAndMinMileage() {
        System.out.println("max Mileage is " + carValidator.getMaxMileage() + " and min Production year is " + carValidator.getMinProductionYear());
        boolean setMenu = false;
        while (!setMenu) {
            System.out.println("1. Set max Mileage" +
                    "\n2. Set min Production year" +
                    "\n3. Show cars which don't match new requirments" +
                    "\n4. Back to previous menu");
            int choosedOptionOfSet = (int) CarRentalUtils.checkConditions(choooice -> choooice > 0 && choooice <= 4, "choose option from the list", "invalid value");
            switch (choosedOptionOfSet) {
                case 1:
                    System.out.println("set max Mileage now");
                    int newMileage = carValidator.validateCourse();
                    carValidator.setMaxMileage(newMileage);
                    break;
                case 2:
                    System.out.println("set min Production year now");
                    int newProductionYear = carValidator.validateProductionYear();
                    carValidator.setMinProductionYear(newProductionYear);
                    break;
                case 3:
                    availableCars.stream().filter(car -> car.getCourse() > carValidator.getMaxMileage() || car.getProductionYear() < carValidator.getMinProductionYear()).collect(Collectors.toList()).forEach(System.out::println);
                    System.out.println("Do you want to remove them?   press yes/no");
                    boolean answer = CarRentalUtils.yerOrNo();
                    if (answer) {
                        availableCars.stream().filter(car -> car.getCourse() > carValidator.getMaxMileage() || car.getProductionYear() < carValidator.getMinProductionYear()).collect(Collectors.toList()).forEach(availableCars::remove);
                    }
                    break;
                case 4:
                    setMenu = true;
                    break;

            }
        }
    }
}
