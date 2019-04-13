/**
 * RES - LABO SMTP
 * Authors: Da Cuhna Garcia Rafael, Edoardo Carpita
 * File: IConfiguration.java
 *
 */

package configuration;

import model.mail.Personne;

import java.util.List;

public interface IConfiguration {
    List<String> getMessages();

    int getNombreGroupes();

    List<Personne> getVictimes();

    List<Personne> getVictimesCC();
}
