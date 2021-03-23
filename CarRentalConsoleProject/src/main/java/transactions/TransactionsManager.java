package transactions;

import utils.CarRentalUtils;
import utils.SortingManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsManager {
private List<Transactions> transactions;

public TransactionsManager(){
    transactions = new ArrayList<>();
}

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void displayAllTransactions(SortingManager sortingManager){
        sortingManager.sortingTransactions(transactions);

    }

    public void displayMonthlyReport(){
        System.out.println("enter number of month");
        int month = (int) CarRentalUtils.checkConditions(months -> months>0 && months<=12, "put the correct number of month","you must put the number");
        System.out.println("Here is monthly report for " + chooseMonth(month));
        countMonthlyReport(month);
    }

    public void displayYearlyReport(){
        System.out.println("Here is Yearly Report for " + LocalDate.now().getYear());
        for (int i=1; i<=12; i++){
            countMonthlyReport(i);
        }
    }
    private String chooseMonth(int month){
        switch(month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "bad month";
        }
    }
    private void countMonthlyReport(int month){
        String choosedMonth = chooseMonth(month);
        List <Transactions> listInChoosedMonth = transactions.stream().filter(transaction -> transaction.getDate().getMonth().getValue()==month).collect(Collectors.toList());
//        double value = listInChoosedMonth.stream().reduce(0,(input, output) -> output.getPrice() + input.getPrice());
        double summary = listInChoosedMonth.stream().mapToDouble(Transactions::getPrice).sum();
        System.out.println("Earnings for " + choosedMonth + ": " + "Total number of rentals: " + listInChoosedMonth.size() + ". Income: " + summary);
    }
}
