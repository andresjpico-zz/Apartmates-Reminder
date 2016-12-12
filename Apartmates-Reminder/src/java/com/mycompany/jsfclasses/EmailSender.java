/*
 * Created by Osman Balci on 2016.05.21  *
 * Copyright © 2016 Osman Balci. All rights reserved. *
 */
package com.mycompany.jsfclasses;
 
import javax.inject.Named;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Balci
 */
@Named(value = "EmailSender")
@RequestScoped
public class EmailSender {
 
    // Create a new instance of EmailSender
    public EmailSender() {
    }

    /*
    ==================
    Instance Variables
    ==================
     */
    Properties emailServerProperties;   // java.util.Properties
    Session emailSession;               // javax.mail.Session
    MimeMessage htmlEmailMessage;       // javax.mail.internet.MimeMessage

    private String statusMessage;
    
    public String getStatusMessage() {
        return statusMessage;
    }
    
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    /*
    ======================================================
    Create Email Sesion and Transport Email in HTML Format
    ======================================================
     */
    public void sendEmail(String roommateName, String email, String taskName) {

        String emailTo = email;
        String emailCc = null;             // Contains comma separated multiple email addresses with no spaces
        String emailSubject = "Task reminder!";        
        String emailBody = "Hello " + roommateName + "! This is a reminder that the deadline for task " + taskName + " is approaching";
        
        // Set Email Server Properties
        emailServerProperties = System.getProperties();
        emailServerProperties.put("mail.smtp.port", "587");
        emailServerProperties.put("mail.smtp.auth", "true");
        emailServerProperties.put("mail.smtp.starttls.enable", "true");

        try {
            // Create an email session using the email server properties set above
            emailSession = Session.getDefaultInstance(emailServerProperties, null);

            /*
            Create a Multi-purpose Internet Mail Extensions (MIME) style email
            message from the MimeMessage class under the email session created.
             */
            htmlEmailMessage = new MimeMessage(emailSession);

            // Set the email TO field to emailTo, which can contain only one email address
            htmlEmailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

            if (emailCc != null && !emailCc.isEmpty()) {
                /*
                Using the setRecipients method (as opposed to addRecipient),
                the CC field is set to contain multiple email addresses
                separated by comma with no spaces in the emailCc string given.
                 */
                htmlEmailMessage.setRecipients(Message.RecipientType.CC, emailCc);
            }
            // It is okay for emailCc to be empty or null since the CC is optional

            // Set the email subject line
            htmlEmailMessage.setSubject(emailSubject);

            // Set the email body to the HTML type text
            htmlEmailMessage.setContent(emailBody, "text/html");

            // Create a transport object that implements the Simple Mail Transfer Protocol (SMTP)
            Transport transport = emailSession.getTransport("smtp");
            /*
            Connect to Gmail's SMTP server using the username and password provided.
            For the Gmail's SMTP server to accept the unsecure connection, the
            Cloud.Software.Email@gmail.com account's "Allow less secure apps" option is set to ON.
             */
            transport.connect("smtp.gmail.com", "Cloud.Software.Email@gmail.com", "csd@VT-1872");

            // Send the htmlEmailMessage created to the specified list of addresses (recipients)
            transport.sendMessage(htmlEmailMessage, htmlEmailMessage.getAllRecipients());

            // Close this service and terminate its connection
            transport.close();

            statusMessage = "Email message is sent!";

        } catch (AddressException ae) {
            statusMessage = "Email Address Exception Occurred!";

        } catch (MessagingException me) {
            statusMessage = "Email Messaging Exception Occurred! Internet Connection Required!";
        }
    }

}
