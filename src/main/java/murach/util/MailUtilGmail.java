package murach.util;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(
            String to,
            String from,
            String subject,
            String body,
            boolean bodyIsHTML)
            throws MessagingException {

        // 1. Mail server configuration
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", "465");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props);

        session.setDebug(true);

        // 2. Create email
        Message message = new MimeMessage(session);

        message.setSubject(subject);

        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=UTF-8");
        } else {
            message.setText(body);
        }

        // 3. Set sender and receiver
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);

        message.setFrom(fromAddress);
        message.setRecipient(
                Message.RecipientType.TO,
                toAddress
        );

        // 4. Send email
        Transport transport = session.getTransport();

        transport.connect(
                "ngothethinh7364@gmail.com",
                "qchk zjhn xapu zvgj"
        );

        transport.sendMessage(
                message,
                message.getAllRecipients()
        );

        transport.close();
    }
}