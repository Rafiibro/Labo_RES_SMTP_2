package model.prank;

import model.mail.Message;
import model.mail.Personne;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PrankTest {
    @Test
    void creationPrank() {
        Prank prank = new Prank();
        Message message = new Message();
        String[] toList = new String[2];
        toList[0] = "bobby.1@gmail.com";
        toList[1] = "bobby.2@gmail.com";
        String[] ccList = new String[2];
        ccList[0] = "bobby.3@gmail.com";
        ccList[1] = "bobby.4@gmail.com";

        message.setFrom("bobby.jerry@gmail.com");
        message.setTo(toList);
        message.setSujet("new mail");
        message.setCc(ccList);
        message.setCorps("Test d'un nouveau mail\n\nbobby jerry");


        List<Personne> victimeList = new ArrayList<>();
        victimeList.add(new Personne(toList[0]));
        victimeList.add(new Personne(toList[1]));
        List<Personne> victimeListcc = new ArrayList<>();
        victimeListcc.add(new Personne(ccList[0]));
        victimeListcc.add(new Personne(ccList[1]));

        prank.addVictimeCC(victimeListcc);
        prank.setSujet("new mail");
        prank.addVictimeTo(victimeList);
        prank.setEnvoyeur(new Personne("bobby.jerry@gmail.com"));
        prank.setMessage("Test d'un nouveau mail");

        Message message2 = prank.genererMail();
        assertEquals(message.getFrom(), message2.getFrom());

        String s1;
        String s2;
        String[] s11 = message.getCc();
        String[] s12 = message2.getCc();

        for (int i = 0; i < ccList.length; ++i) {
            assertEquals(s11[i], s12[i]);
        }

        s11 = message.getTo();
        s12 = message2.getTo();

        for (int i = 0; i < ccList.length; ++i) {
            assertEquals(s11[i], s12[i]);
        }

        assertEquals(message.getSujet(), message2.getSujet());
        assertEquals(message.getCorps(), message2.getCorps());
    }
}
