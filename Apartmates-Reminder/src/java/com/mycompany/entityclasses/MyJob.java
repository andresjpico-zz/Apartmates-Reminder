/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.entityclasses;

import com.mycompany.sessionbeans.RoommateFacade;
import com.mycompany.sessionbeans.TaskFacade;
import com.mycompany.jsfclasses.EmailSender;
import java.util.List;
import javax.ejb.EJB;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.mycompany.entityclasses.Roommate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
public class MyJob implements org.quartz.Job {
    
    public MyJob() {
    }
    
    @EJB
    private TaskFacade taskFacade;
    private RoommateFacade roommateFacade;
    
    EmailSender emailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello!  Email is being sent.");
        
        Integer apartmentID;
        String roommateName;
        String email;
        String taskName;
        String currentTime = "2016-12-11 22:30:00.000";
        
        List<Task> tasks;
        tasks = taskFacade.findByReminderTime(currentTime);
        System.out.println("Hello again!");
        
        for(Task task : tasks) {
            taskName = task.getTaskName();
            apartmentID = task.getApartmentID();
            List<Roommate> roommates = roommateFacade.findByApartmentID(apartmentID);
        
            System.out.println("Task: " + taskName);
            for(Roommate roommate : roommates) {
                roommateName = roommate.getFirstName() + " " + roommate.getLastName();
                email = roommate.getEmail();
                emailSender.sendEmail(roommateName, email, taskName);
            }
        }
    }
}
