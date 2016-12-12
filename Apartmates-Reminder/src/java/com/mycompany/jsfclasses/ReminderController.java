/*
 * Created by Andres Pico on 2016.12.12  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.jsfclasses;

import com.mycompany.entityclasses.Roommate;
import com.mycompany.entityclasses.Task;
import com.mycompany.sessionbeans.RoommateFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Andres
 */
@Named("ReminderController")
@SessionScoped

public class ReminderController implements Serializable {

    @EJB
    private com.mycompany.sessionbeans.RoommateFacade roommateFacade;

    
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

    private RoommateFacade getRoommateFacade() {
        return roommateFacade;
    }

    public void RoommateFacade(RoommateFacade roommateFacade) {
        this.roommateFacade = roommateFacade;
    }
    
    
    
    public ReminderController() {
    
    }
    
    public void startCourier() {
        
        statusMessage = "Service started";
        
        while(true) {
            try {
                
                System.out.println("Hello!  Email is being sent.");
        
                Integer apartmentID;
                String roommateName;
                String email;
                String taskName;
                String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String date = "2016-12-11 22:30:00.000";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateAndTime;
                String notificationDateAndTime;
                List<Task> tasks = new ArrayList<>();
                List<Task> tasksNotComplete;
                
                // Compares current date and hour with the notification's date and hour
                // If they are equal then the task under observation is added to the lisr 'tasks'
                tasksNotComplete = roommateFacade.findNotCompletedTasks();
                for(Task task : tasksNotComplete) {
                    
                    Date timestamp = task.getTaskReminder1();
                    String notificationTimestamp = formatter.format(timestamp);
                    
                    currentDateAndTime = currentTimestamp.substring(0, 13);
                    notificationDateAndTime = notificationTimestamp.substring(0, 13);
//                    notificationDateAndTime = currentTimestamp.substring(0, 13);
                    
                    if(currentDateAndTime.equals(notificationDateAndTime)) {
                        tasks.add(task);
                    }
                }


                // Sends an email to every roommate who lives under the apartment that owns the task
                for(Task task : tasks) {
                    taskName = task.getTaskName();
                    apartmentID = task.getApartmentID();
                    List<Roommate> roommates = roommateFacade.findByApartmentID(apartmentID);

                    for(Roommate roommate : roommates) {
                        roommateName = roommate.getFirstName() + " " + roommate.getLastName();
                        email = roommate.getEmail();
                        sendEmail(roommateName, email, taskName);
                    }
                }
                
                // Sleep 1 hour
                Thread.sleep(1000 * 60 * 60);
            }
            
            catch(InterruptedException ex) {
                statusMessage = "Something went wrong!";
            }
        }
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

