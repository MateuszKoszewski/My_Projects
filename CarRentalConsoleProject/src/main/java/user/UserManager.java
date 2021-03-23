package user;

import org.w3c.dom.ls.LSOutput;
import transactions.Transactions;
import transactions.TransactionsManager;
import user.User;
import user.UserValidator;
import utils.CarRentalUtils;
import utils.SortingManager;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserManager {
    private List<User> availableUsers;
    private List<User> deletedUsers;
    private int maxUserAge;
    private int minUserAge;
    private UserValidator userValidator;

    public UserManager() {
        availableUsers = new ArrayList<>();
        deletedUsers = new ArrayList<>();
        maxUserAge = 80;
        minUserAge = 18;
        userValidator = new UserValidator();

    }

    public List<User> getDeletedUsers() {
        return deletedUsers;
    }

    public int getMaxUserAge() {
        return maxUserAge;
    }

    public int getMinUserAge() {
        return minUserAge;
    }
    public void addUsers(){
//        File file = new File("src/main/resources/archiwum.txt");
//        if (!file.exists()) {
            availableUsers.add(new User("Jan","Kowalski", "75051403846"));
            availableUsers.add(new User("Zbigniew","Nowak", "87122400913"));
//        }
    }

    public void addUser() {
        System.out.println("Specify name of the user");
        String name = CarRentalUtils.StringIsNotBlank();
        System.out.println("Specify last name of the user");
        String lastName = CarRentalUtils.StringIsNotBlank();
        System.out.println("Specify ID number of the user");
        String ID = userValidator.IDNumberValidation(this);
        if (ID != null) {
            User newUser = new User(name, lastName, ID);
            if (availableUsers.contains(newUser)) {
                System.out.println("User already exists");
            } else {
                availableUsers.add(newUser);
            }
        }
    }

    public void setUserAgeMenu(TransactionsManager transactionsManager) {
        Scanner scanner = new Scanner(System.in);
        boolean enter = false;
        while (!enter) {
            System.out.println("The minimum user age is " + minUserAge + " and maximum user age is " + maxUserAge +
                    "\n1. Show list of users" +
                    "\n2. Delete user" +
                    "\n3. Restore deleted user" +
                    "\n4. Edit user" +
                    "\n5. Set minimum User age " +
                    "\n6. Set maximum User age" +
                    "\n7. Check how many users don't match new requirments" +
                    "\n8. Go to previous menu");
            if (scanner.hasNextInt()) {
                int chooice = scanner.nextInt();
                if (chooice >= 1 && chooice <= 8) {
                    switch (chooice) {
                        case 1:
                            if (availableUsers.size() == 0) {
                                System.out.println("there aren't any users");
                            } else {
                                showListOfUsers(new SortingManager());
                            }
                            break;
                        case 2:

                            List<User> usersCantBeDeleted = new ArrayList<>();
                            availableUsers.forEach(user -> {

                                if (user.getRentedCars().size() > 0) {
                                    usersCantBeDeleted.add(user);
                                    user.setCarOrCantBeDeleted("(can't be deleted)");
                                } else {
                                    user.setCarOrCantBeDeleted("");
                                }
                            });


                            if (usersCantBeDeleted.size() == availableUsers.size()) {
                                System.out.println("there not any users to delete - all of them are renting cars or there are not any added users");


                            } else {
                                System.out.println("Remember that you can't delete user which haven't returned a car yet");

                                List<User> listToPrint = availableUsers;
                                int positionFromList;
                                while (true) {

                                    for (int i = 0; i < listToPrint.size(); i++) {
                                        System.out.println(i + 1 + ". " + listToPrint.get(i) + " " + listToPrint.get(i).getCarOrCantBeDeleted());
                                    }
                                    System.out.println("Choose user to delete or press s to sort users");
                                    String choosedValue = CarRentalUtils.checkConditionsOrSort(listToPrint);

                                    if (!choosedValue.equals("s")) {
                                        positionFromList = Integer.parseInt(choosedValue);
                                        User userToDelete = listToPrint.get(positionFromList - 1);
                                        if (userToDelete.getCarOrCantBeDeleted().equals("(can't be deleted)")) {
                                            System.out.println("this user can't be deleted !");
                                        } else {
                                            deletedUsers.add(userToDelete);
                                            availableUsers.remove(userToDelete);

                                            System.out.println("User " + userToDelete.getName() + " " + userToDelete.getLast_name() + " has been deleted");
                                            break;
                                        }
                                    } else {
                                        listToPrint = sortingUsersWithoutMessage(availableUsers);
                                    }
                                }
                            }
                            break;
                        case 3:
                            int positionFromList;
                            List<User> listToPrint = deletedUsers;
                            if (deletedUsers.size() == 0) {
                                System.out.println("there aren't any users to restore");

                            } else {

                                while (true) {
                                    CarRentalUtils.printList(listToPrint);
                                    System.out.println("Choose user to restore or press s to sort users");
                                    String choosedValue = CarRentalUtils.checkConditionsOrSort(listToPrint);

                                    if (!choosedValue.equals("s")) {
                                        positionFromList = Integer.parseInt(choosedValue);
                                        User userToRestore = listToPrint.get(positionFromList - 1);
                                        availableUsers.add(userToRestore);
                                        deletedUsers.remove(userToRestore);

                                        System.out.println("User " + userToRestore.getName() + " " + userToRestore.getLast_name() + " has been restored");
                                        break;

                                    } else {
                                        listToPrint = sortingUsersWithoutMessage(deletedUsers);
                                    }

                                }
                            }

                            break;
                        case 4:
                            if (availableUsers.size() == 0) {
                                System.out.println("there aren't any users");
                            } else {
                                List<User> actualList = availableUsers;
                                boolean showlist = false;
                                while (!showlist) {

                                    CarRentalUtils.printList(actualList);
                                    System.out.println("choose user that you want to edit or press s to sort users");
                                    String numberOfChoosedUser = CarRentalUtils.checkConditionsOrSort(actualList);
                                    int numberOfUserToEdit;
                                    if (!numberOfChoosedUser.equals("s")) {
                                        if (Integer.parseInt(numberOfChoosedUser) > 0 && Integer.parseInt(numberOfChoosedUser) <= actualList.size()) {
                                            numberOfUserToEdit = Integer.parseInt(numberOfChoosedUser);
                                            User editedUser = actualList.get(numberOfUserToEdit - 1);
                                            System.out.println("you choosed " + editedUser.getName() + " " + editedUser.getLast_name() + " to edit");
                                            System.out.println("which parameter do you want to edit?");
                                            String newName = editedUser.getName();
                                            String newLastName = editedUser.getLast_name();
                                            String newId = editedUser.getID();
                                            boolean editUserMenu = false;
                                            while (!editUserMenu) {

                                                System.out.println("1. Edit name " +
                                                        "\n 2. Edit last name " +
                                                        "\n 3. Edit ID" +
                                                        "\n 4. go to previous menu" +
                                                        "\n 5. go to user menu");
                                                int choosedOption = (int) CarRentalUtils.checkConditions(choooice -> choooice > 0 && choooice <= 5, "choose option from the list", "invalid value");

                                                switch (choosedOption) {
                                                    case 1:
                                                        System.out.println("write new name");
                                                        newName = CarRentalUtils.StringIsNotBlank();
                                                        editedUser.setName(newName);

                                                        break;
                                                    case 2:
                                                        System.out.println("write new last name");
                                                        newLastName = CarRentalUtils.StringIsNotBlank();
                                                        editedUser.setLast_name(newLastName);
                                                        break;
                                                    case 3:
                                                        System.out.println("write new Id");
                                                        newId = userValidator.IDNumberValidation(this);
                                                        editedUser.setID(newId);
                                                        break;
                                                    case 4:

                                                        editUserMenu = true;
                                                        break;
                                                    case 5:
                                                        User newUser = new User(newName, newLastName, newId);
                                                        System.out.println(editedUser);
                                                        transactionsManager.getTransactions().stream().filter(transaction -> transaction.getUser().equals(editedUser)).forEach(transaction -> transaction.setUser(newUser));
                                                        System.out.println("Transactions data has been also changed");
                                                        editUserMenu = true;
                                                        showlist = true;
                                                }
                                            }
                                        } else {
                                            System.out.println("you must choose user from the list");
                                        }
                                    } else {
                                        actualList = sortingUsersWithoutMessage(availableUsers);
                                    }
                                }


                            }
                            break;

                        case 5:
                            System.out.println("put the minimum age you want to set");
                            int minAge = (int) CarRentalUtils.checkConditions(minimumAge -> minimumAge > 0 && minimumAge < maxUserAge, "put the correct age", "invalid value");
                            setMinUserAge(minAge);
                            System.out.println("minimum age of user is set to " + minAge);

                            break;
                        case 6:
                            System.out.println("put the maximum age you want to set");
                            int maxAge = (int) CarRentalUtils.checkConditions(maximumAge -> maximumAge > 0 && maximumAge > minUserAge, "put the correct age", "invalid value");
                            setMaxUserAge(maxAge);
                            System.out.println("maximum age of user is set to " + maxAge);
                            break;
                        case 7:
                            CarRentalUtils.printList(checkListByNewAge());
                            System.out.println("Do you want to remove these users from the list? yes or no ?");
                            removeOrNot();
                        case 8:
                            enter = true;
                            break;
                    }
                } else {
                    System.out.println("choose option from the list");
                    scanner.nextLine();
                }
            } else {
                System.out.println("put the correct value");
                scanner.nextLine();
            }
        }

    }

    private List<User> checkListByNewAge() {
        return availableUsers.stream().filter(user -> user.getAge() < minUserAge || user.getAge() > maxUserAge).collect(Collectors.toList());
    }

    private void setMaxUserAge(int maxUserAge) {
        this.maxUserAge = maxUserAge;
    }

    private void setMinUserAge(int minUserAge) {
        this.minUserAge = minUserAge;
    }

    public List<User> getAvailableUsers() {
        return availableUsers;
    }

    private void removeOrNot() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String chooice = scanner.nextLine();
            if (chooice.toLowerCase().equals("yes") || chooice.toLowerCase().equals("y")) {
                for (int i = 0; i < checkListByNewAge().size(); i++) {
                    if (availableUsers.contains(checkListByNewAge().get(i))) {
                        User userToRemove = availableUsers.get(i);
                        availableUsers.remove(i);
                        deletedUsers.add(userToRemove);
                    }
                }
                break;

            } else if (chooice.toLowerCase().equals("no") || chooice.toLowerCase().equals("n")) {
                break;
            } else {
                System.out.println("put the correct value");
            }
        }
    }

    public void showListOfUsers(SortingManager sortingManager) {
        sortingManager.sortingUsers(availableUsers);

    }

    private void showSortingOptions() {
        System.out.println("Sort By" +
                "\n1. Name" +
                "\n2. Last name" +
                "\n3. Age" +
                "\n4. Number of rented cars at this moment" +
                "\n5. Number of rented cars in total");
    }

    private Comparator<User> chooseSortingOption() {
        int chooice = (int) CarRentalUtils.checkConditions(choose -> choose > 0 && choose <= 4, "choose option from the list", "put the correct value");
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


    private void sortingUsers(List<User> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to sort this list press s, if you want to go to previus menu press enter");
        System.out.println();

        while (true) {
            String chooice = scanner.nextLine();

            if (chooice.isBlank()) {
                break;
            } else if (chooice.equals("s")) {
                CarRentalUtils.printList(sortingUsersWithoutMessage(list));
            } else {
                System.out.println("invalid value");
            }
        }
    }

    private List<User> sortingUsersWithoutMessage(List<User> list) {
        showSortingOptions();
        Comparator<User> choosedComparator = chooseSortingOption();
        System.out.println("How do you want to sort?" +
                "\n1. ascending" +
                "\n2. descending");
        int wayOfSorting = (int) CarRentalUtils.checkConditions(value -> value > 0 && value <= 2, "choose option from the list", "put the correct value");
        Comparator<User> toSort = (Comparator<User>) CarRentalUtils.function.apply(wayOfSorting, choosedComparator);
        return list.stream().sorted(toSort).collect(Collectors.toList());

    }
}
