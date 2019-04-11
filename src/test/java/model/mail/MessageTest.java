package model.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void createMessage() {
        Message message = new Message();
        String[] toList = new String[2];
        toList[0] = "bobby.1@gmail.com";
        toList[1] = "bobby.2@gmail.com";

        message.setFrom("bobby.jerry@gmail.com");
        message.setTo(toList);
        message.setSujet("Test");
        message.setCorps("Test d'un nouveau mail");
    }
}
