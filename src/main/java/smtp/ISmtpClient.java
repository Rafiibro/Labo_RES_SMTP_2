package smtp;

import model.mail.Message;
import java.io.IOException;

public interface ISmtpClient {
    public void sendMail(Message message) throws IOException;
}
