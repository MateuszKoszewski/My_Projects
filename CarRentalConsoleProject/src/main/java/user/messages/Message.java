package user.messages;

import transactions.Transactions;

import java.util.Objects;

public class Message {
    private Transactions transaction;

    public Message(Transactions transaction) {
        this.transaction = transaction;
    }

    public String createMessage(){
        return " $$$$$$$$$$$$$$$$$$$$$$$$$$$" +
                "\n     ATTENTION ATTENTION" +
                "\n $$$$$$$$$$$$$$$$$$$$$$$$$$$" +
                "\n User: " + transaction.getUser() + " haven't returned the car yet." +
                "\n He rented " + transaction.getCar() + " on " + transaction.getDate() +
                "\n and should return on " + transaction.getDateofReturn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(transaction, message.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction);
    }
}
