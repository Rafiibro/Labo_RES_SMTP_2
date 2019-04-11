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
    Socket socket = new Socket(smtpServerAdress,smtpServerPort);
    writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
    reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    String line = reader.readLine();
    LOG.info(line);
    writer.println("EHLO localhost\r\n");
    line = reader.readLine();
    if (line.startsWith("250")){

        throw new IOException("SMTP error: " + line);

    }

    while (line.startsWith("250-")) {
        line = reader.readLine();
        LOG.info(line);
    }

    writer.write("MAIL FROM");
    writer.write(message.getFrom());
    writer.write("\r\n");
    writer.flush();
    line = reader.readLine();
    LOG.info(line);


        for (String to : message.getTo()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String to : message.getCc()) {
            writer.write("CC TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);

        }

/*
        for (String to : message.getBcc()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);

        }
*/


        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        writer.write("Content type: text/plain; charset=\" UTF-8 \" \r\n");
        writer.write("From: " + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo() + "\r\n");
        for (int length = 1; length < message.getTo().length; length++){
            writer.write(", " + message.getTo()[length]);
        }
        writer.write("\r\n");

        writer.write("Cc: " + message.getCc() + "\r\n");
        for (int length = 1; length < message.getCc().length; length++){
            writer.write(", " + message.getCc()[length]);
        }
        writer.write("\r\n");
        writer.flush();

        LOG.info(message.getCorps());
        writer.write(message.getCorps());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);
        writer.write("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
   }
}
