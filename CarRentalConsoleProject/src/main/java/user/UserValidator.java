package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

public UserValidator(){

}
    public String IDNumberValidation(UserManager userManager) {
        Scanner scanner = new Scanner(System.in);
        String pesel = "";
        while (true) {
            pesel = scanner.nextLine();
            Pattern compile = Pattern.compile("^\\d{11}$");
            Matcher matcher = compile.matcher(pesel);
            if (matcher.find()) {
                pesel = matcher.group(0);
                if (Integer.parseInt(pesel.substring(2, 4)) > 12) {
                    System.out.println("wrong month number");
                    break;
                } else if (Integer.parseInt(pesel.substring(4, 6)) > 31) {
                    System.out.println("wrong day number");
                    break;
                }
                int userAge = LocalDate.now().getYear() - parseIntoDate(pesel).getYear();
                if (userAge >= userManager.getMinUserAge() && userAge <= userManager.getMaxUserAge()) {
                    return pesel;
                } else if (userAge > 100) {
                    System.out.println("Are you sure? invalid year of birth");
                } else {
                    System.out.println("User is too young or too old to rent a car");
                    break;
                }
            } else {
                System.out.println("put the correct number");
            }
        }
        return null;
    }


    public  LocalDate parseIntoDate (String ID){
        String date = ID.substring(0,6);
        LocalDate formatedDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        if (Integer.parseInt(date.substring(0,2))<LocalDate.now().getYear()-2000){
            formatedDate = LocalDate.parse(date,formatter);
        }
        else{
            String newDate = "19"+date;
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            formatedDate = LocalDate.parse(newDate,formatter2);
        }
        return formatedDate;
    }


}
