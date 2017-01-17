/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author freek
 */
public class Email {

    private static String from = "is109groep4fys@gmail.com";
    private static String password = "conciergeRemco";
    private static String host = "smtp.gmail.com";
    private static String port = "587";
    private static Session sesh;

    public static void sendMessage(String recipient)
            throws MessagingException {

        //Set the host smtp address
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);

        boolean check = true;
        //
        try {
            InternetAddress e = new InternetAddress(from);
            e.validate();
        } catch (AddressException e) {
            e.getStackTrace();
            check = false;
        }

        if (check) {
            sesh = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
        }

        String subject = "Corendon: Your baggage has been found!";
        String content = "Best traveller, \n\n"
                + "Today whe have found your baggage.\n"
                + "At this moment we are sending your baggage right to the address you registered at the time.\n" 
                + "If this is incorrect or you moved at the time, please contact us immediatly with the number: 0639573906\n\n"
                + "Best of regards, Corendon";

        try {
            Message m = new MimeMessage(sesh);
            m.setFrom(new InternetAddress(from));
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            m.setSubject(subject);
            m.setSentDate(new Date());
            m.setContent(content, "text/plain");

            Transport.send(m);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } // end try/catch //
    }
}
