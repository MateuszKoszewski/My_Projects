package history;

import car.Car;
import car.CarManager;
import org.w3c.dom.ls.LSOutput;
import transactions.Transactions;
import transactions.TransactionsManager;
import user.User;
import user.UserManager;
import user.messages.Message;
import user.messages.MessageManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archiwum {


    private CarManager carManager;
    private UserManager userManager;
    private TransactionsManager transactionsManager;
    private MessageManager messageManager;


    public Archiwum(CarManager carManager, UserManager userManager, TransactionsManager transactionsManager, MessageManager messageManager) {
        this.carManager = carManager;
        this.userManager = userManager;
        this.transactionsManager = transactionsManager;
        this.messageManager = messageManager;
    }

    public void saveToFile() {
        createFile(true);
        createFile(false);
        List<String> newListOfCars = carManager.getAvailableCars().stream().map(cars -> cars.toString().replaceFirst("", "Car: ")).collect(Collectors.toList());
        List<String> newListOfUsers = userManager.getAvailableUsers().stream().map(users -> "User: " + users.getName() + " " + users.getLast_name() + " " + users.getID()).collect(Collectors.toList());
        List<String> newListOfTransactions = transactionsManager.getTransactions().stream()
                .map(transaction -> "Transactions: " + transaction.getUser().getName() + " " + transaction.getUser().getLast_name() + " " +
                        transaction.getUser().getID() + " " + transaction.getCar().toString() + " " + transaction.getDays() + " " + transaction.getDate() + " " + transaction.getDateofReturn())
                .collect(Collectors.toList());
        try {

            Files.write(Paths.get("src/main/resources/archiwum.txt"), newListOfCars, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            Files.write(Paths.get("src/main/resources/archiwum.txt"), newListOfUsers, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            Files.write(Paths.get("src/main/resources/archiwum.txt"), newListOfTransactions, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("blad");
        }
    }

    public void loadDataFromFile() {
        createFile(false);
        List<String> listOfData = null;

        try {
            listOfData = Files.readAllLines(Paths.get("src/main/resources/archiwum.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("blad czytania");
        }
        if (listOfData.size()==0){
            carManager.addCars();
            userManager.addUsers();
        }

        for (int i = 0; i < listOfData.size(); i++) {
            String[] element = listOfData.get(i).split(" ");

            if (listOfData.get(i).startsWith("Car")) {

                Car newCar = new Car(element[1], element[2], Integer.parseInt(element[4]), Double.parseDouble(element[5]), Integer.parseInt(element[6]), element[3]);
                if (!carManager.getAvailableCars().contains(newCar)) {
                    carManager.getAvailableCars().add(newCar);
                }
            } else if (listOfData.get(i).startsWith("User")) {

                User newUser = new User(element[1], element[2], element[3]);
                userManager.getAvailableUsers().add(newUser);


            } else if (listOfData.get(i).startsWith("Transactions")) {
                Car car = new Car(element[4], element[5], Integer.parseInt(element[7]), Double.parseDouble(element[8]), Integer.parseInt(element[9]), element[6]);
                User user = new User(element[1], element[2], element[3]);
//                User userReference = userManager.getAvailableUsers().stream().filter(newUser -> newUser.equals(user)).findFirst().orElseThrow(() -> new RuntimeException(" problem with User in Archiwum"));
                Transactions transaction = new Transactions(Double.parseDouble(element[8]), user, car, Integer.parseInt(element[10]), LocalDate.parse(element[11], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                if (element.length == 14) {
                    transaction.setDateofReturn(element[12] + " " + element[13]);
                } else {
                    transaction.setDateofReturn(element[12]);
                }

                transactionsManager.getTransactions().add(transaction);

            }
        }
    }

    public void updateDataAfterLoad() {
        for (int i = 0; i < transactionsManager.getTransactions().size(); i++) {
            Transactions checkedTransaction = transactionsManager.getTransactions().get(i);
            Period period = Period.ofDays(checkedTransaction.getDays());
            LocalDate dateOfReturningCar = checkedTransaction.getDate().plus(period);

            userManager.getAvailableUsers().forEach(user ->{
                if (user.equals(checkedTransaction.getUser())){
                    user.getTransactionsList().add(checkedTransaction);
                    checkedTransaction.setUser(user);
                }

            });
            if (!userManager.getAvailableUsers().contains(checkedTransaction.getUser()) && !userManager.getDeletedUsers().contains(checkedTransaction.getUser())){
                userManager.getDeletedUsers().add(checkedTransaction.getUser());
            }

            userManager.getDeletedUsers().forEach(user -> {
                if (user.equals(checkedTransaction.getUser())){
                    user.getTransactionsList().add(checkedTransaction);
                }
            });
            if (!checkedTransaction.getDateofReturn().equals("already returned")) {
                carManager.getRentedCars().add(checkedTransaction);
                userManager.getAvailableUsers().forEach(user -> {
                        if (user.equals(checkedTransaction.getUser())){
                            user.getRentedCars().add(checkedTransaction.getCar());
                        }
                });
//                checkedTransaction.getUser().getRentedCars().add(checkedTransaction.getCar());
                if (dateOfReturningCar.isBefore(LocalDate.now()) || dateOfReturningCar.isEqual(LocalDate.now())) {

                    Message newMessage = new Message(checkedTransaction);
                    messageManager.getListOfMessages().add(newMessage);

                }
            }

        }
    }

    private boolean createFile(boolean delete) {
        boolean ifExists = false;
        File file = new File("src/main/resources/archiwum.txt");
        try {

            ifExists = file.createNewFile();
            if (delete) {
                file.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("blad");
        }

        return ifExists;
    }


}


