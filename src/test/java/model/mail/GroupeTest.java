package model.mail;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeTest {
    @Test
    void groupeTest() {
        Groupe groupe = new Groupe();
        Personne p1 = new Personne("bobby.jerry@gmail.com");
        Personne p2 = new Personne("bobby.bobby@gmail.com");

        groupe.ajouterMembre(p1);
        groupe.ajouterMembre(p2);

        List<Personne> listPersonne = groupe.getMembres();
        assertEquals(p1.getPrenom(), listPersonne.get(0).getPrenom());
        assertEquals(p1.getNom(), listPersonne.get(0).getNom());
        assertEquals(p1.getAdresse(), listPersonne.get(0).getAdresse());
        assertEquals(p2.getPrenom(), listPersonne.get(1).getPrenom());
        assertEquals(p2.getNom(), listPersonne.get(1).getNom());
        assertEquals(p2.getAdresse(), listPersonne.get(1).getAdresse());
    }

}
