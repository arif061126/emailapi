package com.emailApi.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    public boolean sendEmail(String subject, String message, String to){

        boolean f = false;

        String from = "arif.061126@gmail.com";

        String host = "smtp.gmail.com";

        //get system properties:
        Properties properties = System.getProperties();

        //setting important information to property object

        //setting host:
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1: get session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //allow less secure app before config...with the following link:
                // https://www.google.com/settings/security/lesssecureapps
                return new PasswordAuthentication("arif.061126@gmail.com", "Arif061126");
            }
        });

        session.setDebug(true);

        //step 2: compose message
        MimeMessage mimeMessage = new MimeMessage(session);


        try {
            //set from
            mimeMessage.setFrom(from);

            //add recipient to
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //set subject
            mimeMessage.setSubject(subject);

            //set Text
            mimeMessage.setText(message);

            //step 3: send the message using transport class
            Transport.send(mimeMessage);

            System.out.println("Message sent successfully...");
            f = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return f;
    }
}
