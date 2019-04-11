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
        victimes = loadAdresse("./config/victimes.utf8");
        messages = loadMessage("./config/messages.utf8");
        loadProperties("./config/config.properties");
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
    }

    private List<String> loadMessage(String s) {
    }


}