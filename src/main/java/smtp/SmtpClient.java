/**
 * RES - LABO SMTP
 * Authors: Da Cuhna Garcia Rafael, Edoardo Carpita
 * File: PrankGenerator.java
 *
 */

package smtp;

import model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{

   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

   private String smtpServerAdress;
   private int smtpServerPort = 25;
   private Socket socket;
   private PrintWriter writer;
   private BufferedReader reader;



   public SmtpClient(String smtpServerAddress, int port){

       this.smtpServerAdress = smtpServerAddress;
       this.smtpServerPort = port;

   }

    @Override
    public void sendMail(Message message) throws IOException {
    LOG.info("Sending message via SMTP");

    socket = new Socket(smtpServerAdress,smtpServerPort);
    writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
    reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

    readResponse();

    writeLine("EHLO localhost");
    LOG.info("We write the command : EHLO localhost");


    String contact = reader.readLine();
    if (!contact.startsWith("250")){
        throw new IOException("SMTP error: " + contact);
    }

    while (contact.startsWith("250-")) {
         contact = reader.readLine();
         LOG.info(contact);
    }

    LOG.info("We write the command (adding sender) : MAIL FROM: " + message.getFrom());
    writeLine("MAIL FROM: " + message.getFrom());
    readResponse();

        for (String to : message.getTo()) {
            LOG.info("We write the command (adding To victims) : RCPT TO: " + to);
            writeLine("RCPT TO: " + to);
            readResponse();
        }

        for (String cc : message.getCc()) {
            LOG.info("We write the command (adding Cc victimes) : RCPT TO: " + cc);
            writeLine("RCPT TO: " + cc);
            readResponse();
        }

        for (String bcc : message.getBcc()) {
            LOG.info("We write the command (adding Bcc victimes) : RCPT TO: " + bcc);
            writeLine("RCPT TO:" + bcc);
            readResponse();
        }


        LOG.info("We write the command (Telling we are starting de message): DATA");
        writeLine("DATA");
        readResponse();

        LOG.info("We write the message (too long to add it but you have an example before): ");

        writeMessage(message);

        writeLine(message.getCorps() + "\r\n" + "." + "\r\n");
        readResponse();

        LOG.info("We write the command to quit the test: QUIT");

        writeLine("QUIT");

        reader.close();
        writer.close();
        socket.close();
   }



   private void writeLine(String toWrite){
       writer.write(toWrite);
       writer.write("\r\n");
       writer.flush();

   }


    private void writeMessage(Message message){

        writer.write("From: " + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo()[0]);
        for (int length = 1; length < message.getTo().length; length++){
            writer.write(", " + message.getTo()[length]);
        }

        writer.write("\r\n");
        writer.write("Cc: " + message.getCc()[0]);
        for (int length = 1; length < message.getCc().length; length++){
            writer.write(", " + message.getCc()[length]);
        }
        writer.write("\r\n");

        writer.write(message.getSujet() + "\r\n");
        writer.write("Content-Type: text/plain: charset=\"utf-8\"\r\n");

        writer.flush();

    }

   private void readResponse() throws IOException {
       String reply = reader.readLine();
       LOG.info(reply);
   }
}
