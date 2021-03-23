package utils;

import transactions.Transactions;
import user.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CarRentalUtils  {

    public static String StringIsNotBlank() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = scanner.nextLine();
            if (string.isBlank()) {
                System.out.println("you haven't written anything");

            } else {
                return string;
            }
        }
    }
    public static double checkConditions(Predicate<Double> predicate, String answer1, String answer2) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (predicate.test(value)) {
                    return value;
                } else {
                    System.out.println(answer1);
                    scanner.nextLine();
                }
            } else {
                System.out.println(answer2);
                scanner.nextLine();
            }
        }

    }

    public static <E> String checkConditionsOrSort (List<E> list){
        Scanner scanner = new Scanner(System.in);
        while (true){
           if (scanner.hasNextInt()){
               int value = scanner.nextInt();
               if (value>0 && value <=list.size()){
                   return Integer.toString(value);
               }
               else {
                   System.out.println("Choose option from the list");
               }
           }
           else {
               String value = scanner.nextLine();
               if (value.equals("s")){
                   return value;
               }
               else {
                   System.out.println("invalid value");
               }
            }
        }
    }

    public static <E> void printList(List<E> list){
        for (int i=0;i<list.size();i++){
            System.out.println(i+1 + ". " + list.get(i));
        }
    }

    public static final BiFunction<Integer, Comparator<?>, Comparator<?>> function = (integer, comparator) -> integer == 1 ? comparator : comparator.reversed();

    public static void showTransactions(List<Transactions> list){

        String format = "%d. %s %s - %s %s %s, date of rent: %s, time of rental: %d days, date of returning car: %s, price per day: %.2f";
        for (int i = 0; i < list.size(); i++) {
                String positionInList = String.format(format, i + 1, list.get(i).getUser().getName(), list.get(i).getUser().getLast_name(), list.get(i).getCar().getBrand(), list.get(i).getCar().getModel(),list.get(i).getCar().getRegistrationNumber(), list.get(i).getDate(), list.get(i).getDays(), list.get(i).getDateofReturn(), list.get(i).getPrice());
            System.out.println(positionInList);
        }
    }
public static boolean yerOrNo (){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")){
                return true;
            }
            else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")){
                return false;
            }
            else {
                System.out.println("invalid value");
            }
        }


}
}
