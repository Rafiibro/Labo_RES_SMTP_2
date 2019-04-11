package model.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonneTest {

    @Test
    void createPersonnne() {
        Personne p1 = new Personne("Rafael", "Garcia", "grcia.raf@gmail.com");

        assertEquals("grcia.raf@gmail.com", p1.getAdresse());
        assertEquals("Rafael", p1.getPrenom());
        assertEquals("Garcia", p1.getNom());

    }
}
