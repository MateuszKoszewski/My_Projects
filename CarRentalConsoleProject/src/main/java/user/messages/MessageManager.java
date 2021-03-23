package user.messages;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<Message> listOfMessages;

    public MessageManager() {

        this.listOfMessages = new ArrayList<>();
    }

   public void printMessages(){

        listOfMessages.forEach(message -> System.out.println(message.createMessage()));
   }

    public List<Message> getListOfMessages() {

        return listOfMessages;
    }
}
