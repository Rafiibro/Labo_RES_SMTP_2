package model.prank;

import configuration.Configuration;
import configuration.IConfiguration;
import model.mail.Groupe;
import model.mail.Personne;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;

public class PrankGenerator {
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private IConfiguration configurationManager;

    public PrankGenerator(Configuration config) {
        this.configurationManager = config;
    }

    public List<Prank> genererPranks() {
        List<Prank> pranks = new ArrayList<>();

        List<String> messages = configurationManager.getMessages();
        int messageIndex = 0;

        int nombreGroupes = configurationManager.getNombreGroupes();
        int nombreVictimes = configurationManager.getVictimes().size();

        if (nombreVictimes / nombreGroupes< 3) {
            nombreGroupes = nombreVictimes / 3;
            LOG.warning("Pas assez de victimes pour avoir ce nombre de groupe, on ne peut seulement generer " + nombreGroupes + "groupes avec ces victimes");
        }

        List<Groupe> groupes = genererGroupes(configurationManager.getVictimes(), nombreGroupes);

        for (Groupe groupe: groupes) {
            Prank prank = new Prank();

            List<Personne> victimes = groupe.getMembres();
            Collections.shuffle(victimes);
            Personne envoyeur = victimes.remove(0);
            prank.setEnvoyeur(envoyeur);
            prank.addVictimeTo(victimes);

            prank.addVictimeCC(configurationManager.getVictimesCC());

            String message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % messages.size();
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    public List<Groupe> genererGroupes(List<Personne> victimes, int nombreGroupes) {
        List<Personne> victimesDisponible= new ArrayList<>(victimes);
        Collections.shuffle(victimesDisponible);
        List<Groupe> groupes = new ArrayList<>();

        for (int i = 0; i < nombreGroupes; ++i) {
            Groupe groupe = new Groupe();
            groupes.add(groupe);
        }

        int turn = 0;
        Groupe groupeCible;
        while(victimesDisponible.size() > 0) {
            groupeCible= groupes.get(turn);
            turn = (turn + 1) % groupes.size();
            Personne victime = victimesDisponible.remove(0);
            groupeCible.ajouterMembre(victime);
        }
        return groupes;
    }
}
