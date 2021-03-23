package View;

import car.Car;
import car.CarManager;
import history.Archiwum;
import transactions.TransactionsManager;
import user.UserManager;
import user.messages.MessageManager;
import utils.CarRentalUtils;
import utils.SortingManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Execute {


    public static void execute() {
        CarManager carManager = new CarManager();
        UserManager userManager = new UserManager();
        TransactionsManager transactionsManager = new TransactionsManager();
        MessageManager messageManager = new MessageManager();
        SortingManager sortingManager = new SortingManager();
        Archiwum archiwum = new Archiwum(carManager, userManager, transactionsManager, messageManager);
        archiwum.loadDataFromFile();
        archiwum.updateDataAfterLoad();

        System.out.println("Welcome to car rental. ");
        Scanner scanner = new Scanner(System.in);
        String tekstMenu =
                "1. List available cars" +
                        "\n2. Car Menu" +
                        "\n3. Add new car" +
                        "\n4. List of users" +
                        "\n5. Add user" +
                        "\n6. User menu" +
                        "\n7. Rent a car" +
                        "\n8. List all rented cars" +
                        "\n9. Return a car" +
                        "\n10. Display monthly report" +
                        "\n11. Display yearly report" +
                        "\n12. Display all transactions" +
                        "\n13. Exit" +
                        "\nChoose your option";
        boolean exit = false;

        while (!exit) {
            System.out.println(tekstMenu);
            messageManager.printMessages();
            if (scanner.hasNextInt()) {
                int chooice = scanner.nextInt();
                if (chooice > 0 && chooice <= 13) {
                    switch (chooice) {
                        case 1:
                            System.out.println("you choosed 1. List available cars");
                            if (carManager.getAvailableCars().size() == 0) {
                                System.out.println("there aren't any available cars");
                            } else {
                                carManager.showListOfCars(sortingManager);
                            }
                            break;
                        case 2:
                            carManager.carManagerMenu(transactionsManager, sortingManager, userManager, messageManager);

                            break;
                        case 3:
                            System.out.println("you choosed 3. Add new car");
                            carManager.addNewCar();
                            break;
                        case 4:
                            System.out.println("you choosed 4. List of users ");
                            if (userManager.getAvailableUsers().size() == 0) {
                                System.out.println("There aren't any active users");
                            } else {
                                userManager.showListOfUsers(sortingManager);

                            }
                            break;
                        case 5:
                            System.out.println("you choosed 5. Add user");
                            userManager.addUser();
                            break;
                        case 6:
                            System.out.println("you choosed 6. User menu");
                            userManager.setUserAgeMenu(transactionsManager);
                            break;
                        case 7:
                            System.out.println("you choosed 7. Rent car");
                            if (carManager.getAvailableCars().size() == 0) {
                                System.out.println("There aren't any available cars to rent");
                            } else if (userManager.getAvailableUsers().size() == 0) {
                                System.out.println("There aren't any active users. Add the user first");
                            } else {
                                carManager.rentACar(userManager.getAvailableUsers(), transactionsManager.getTransactions(), sortingManager);
                            }
                            break;
                        case 8:
                            System.out.println("you choosed 8. List all rented cars");
                            if (carManager.getRentedCars().size() == 0) {
                                System.out.println("There aren't any rented cars");
                            } else {
                                carManager.listAllRentedCars();
                            }
                            break;
                        case 9:
                            System.out.println("you choosed 9. Return a car");
                            if (carManager.getRentedCars().size() == 0) {
                                System.out.println("There aren't any cars to return");
                            } else {
                                carManager.returnACar(transactionsManager.getTransactions(), messageManager.getListOfMessages(), userManager, sortingManager);
                            }
                            break;
                        case 10:
                            System.out.println("you choosed 10. Display monthly report");
                            transactionsManager.displayMonthlyReport();
                            break;
                        case 11:
                            System.out.println("you choosed 11. Display yearly report");
                            transactionsManager.displayYearlyReport();
                            break;
                        case 12:
                            System.out.println("you choosed 12. Display all transactions");
                            transactionsManager.displayAllTransactions(sortingManager);
                            break;
                        case 13:
                            System.out.println("you choosed 13. Exit");
                            archiwum.saveToFile();
                            exit = true;
                            break;
                    }

                } else {
                    System.out.println("put the number from the list");
                    scanner.nextLine();
                }
            } else {
                System.out.println("you must put the number");
                scanner.nextLine();
            }
        }

    }

}
