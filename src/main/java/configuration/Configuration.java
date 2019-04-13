/**
 * RES - LABO SMTP
 * Authors: Da Cuhna Garcia Rafael, Edoardo Carpita
 * File: Configuration.java
 *
 */

package configuration;

import model.mail.Personne;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuration implements IConfiguration {
    private String serverAddress;
    private int serverPort;
    private int numberOfGroups;
    private List<Personne> victimesCC;
    private final List<Personne> victimes;
    private final List<String> messages;


    public Configuration() throws IOException{
        victimes = loadAdresse("src/main/java/configuration/config/victimes.utf8");
        messages = loadMessage("src/main/java/configuration/config/messages.utf8");
        loadProperties("src/main/java/configuration/config/config.properties");
    }

    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        this.serverAddress = properties.getProperty("smtpServerAddress");
        this.serverPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        this.victimesCC = new ArrayList<>();
        String CC = properties.getProperty("witnessesToCC");
        String[] CCadresses = CC.split(",");
        for (String adresse : CCadresses) {
            this.victimesCC.add(new Personne(adresse));
        }
    }


    private List<Personne> loadAdresse(String fileName) throws IOException {
        List<Personne> result;
        try(FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String adresse = reader.readLine();
                while(adresse != null) {
                    result.add(new Personne(adresse));
                    adresse = reader.readLine();
                }
            }
        }
        return result;
    }

    private List<String> loadMessage(String fileName) throws IOException {

        List<String> result;
        try(FileInputStream inp = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(inp, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String line = reader.readLine();

                while (line != null) {
                    StringBuilder body = new StringBuilder();
                    while ((line != null) && (!line.equals("=="))) {
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    
    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public int getNombreGroupes() {
        return numberOfGroups;
    }

    @Override
    public List<Personne> getVictimes() {
        return victimes;
    }

    @Override
    public List<Personne> getVictimesCC() {
        return victimesCC;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }
}