package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private final List<Personne> membres= new ArrayList<>();

    public void ajouterMembre(Personne personne) {
        membres.add(personne);
    }

    public List<Personne> getMembres() {
        return new ArrayList<>(membres);
    }
}
