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

        String[] parts = adresse.split("[.@]+");
        this.prenom = parts[0];
        this.nom = parts[1];
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
