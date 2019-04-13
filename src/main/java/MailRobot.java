import configuration.Configuration;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class  MailRobot {
    public static void main(String[] args) {
        try {
            Configuration config = new Configuration();
            List<Prank> pranksList;
            PrankGenerator PG = new PrankGenerator(config);
            SmtpClient smtp = new SmtpClient(config.getServerAddress(), config.getServerPort());
            pranksList = PG.genererPranks();

            for (Prank prank: pranksList) {
                smtp.sendMail(prank.genererMail());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
