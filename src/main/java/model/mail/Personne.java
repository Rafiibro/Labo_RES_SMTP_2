package model.mail;

public class Personne {

    private String prenom;
    private String nom;
    private String adresse;

    public Personne(String prenom, String nom, String adresse) {
        this.adresse = adresse;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String adresse) {
        this.adresse = adresse;

        String[] tab = adresse.split(".");
        this.prenom = tab[0];
        tab = tab[1].split("@");
        this.nom = tab[0];
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }
}
