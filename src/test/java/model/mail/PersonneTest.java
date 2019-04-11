package model.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonneTest {

    @Test
    public void createOnePersonnne() {
        Personne p1 = new Personne("Rafael", "Garcia", "grcia.raf@gmail.com");
        Personne p2 = new Personne("bobby.jerry@gmail.com");

        assertEquals("grcia.raf@gmail.com", p1.getAdresse());
        assertEquals("Rafael", p1.getPrenom());
        assertEquals("Garcia", p1.getNom());

        assertEquals("bobby.jerry@gmail.com", p2.getAdresse());
        assertEquals("bobby", p2.getPrenom());
        assertEquals("jerry", p2.getNom());
    }
}
