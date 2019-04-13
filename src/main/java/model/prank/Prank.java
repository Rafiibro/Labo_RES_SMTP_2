/**
 * RES - LABO SMTP
 * Authors: Da Cuhna Garcia Rafael, Edoardo Carpita
 * File: Prank.java
 *
 */

package model.prank;

import model.mail.Message;
import model.mail.Personne;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Prank {
    private Personne envoyeur;
    private final List<Personne> victimeTo= new ArrayList<>();
    private final List<Personne> victimeCC= new ArrayList<>();
    private String message;
    private String sujet;


    public Personne getEnvoyeur() {
        return envoyeur;
    }

    public void setEnvoyeur(Personne envoyeur) {
        this.envoyeur = envoyeur;
    }

    public ArrayList<Personne> getVictimeTo() {
        return new ArrayList<Personne>(victimeTo);
    }

    public void addVictimeTo(List<Personne> victimes) {
        victimeTo.addAll(victimes);
    }

    public ArrayList<Personne> getVictimeCC() {
        return new ArrayList<Personne>(victimeCC);
    }

    public void addVictimeCC(List<Personne> witness) {
        victimeCC.addAll(witness);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message genererMail() {
        Message message = new Message();

        String[] messageParts = this.message.split("\r\n", 2);
        message.setSujet(messageParts[0]);

        message.setCorps(messageParts[1] + "\n\n" + envoyeur.getPrenom() + " " + envoyeur.getNom());

        String[] to = victimeTo.stream().map(p -> p.getAdresse()).collect(Collectors.toList()).toArray(new String[]{});
        message.setTo(to);

        String[] cc = victimeCC.stream().map(p -> p.getAdresse()).collect(Collectors.toList()).toArray(new String[]{});
        message.setCc(cc);

        message.setFrom(envoyeur.getAdresse());

        return message;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
}
