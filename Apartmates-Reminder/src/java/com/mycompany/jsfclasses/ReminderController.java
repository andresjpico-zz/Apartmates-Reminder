/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */

package com.mycompany.jsfclasses;

import com.mycompany.entityclasses.MyJob;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import org.quartz.JobDetail;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import org.quartz.Trigger;

/**
 *
 * @author Balci
 */
@Named(value = "ReminderController")
@RequestScoped
public class ReminderController {
 
    // Create a new instance of EmailSender
    public ReminderController() {
    }

    /*
    ==================
    Instance Variables
    ==================
     */
    private String statusMessage;

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

   
    /*
    "The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done
    to perform any initialization. This method MUST be invoked before the class is put into service." [Oracle]
    */
    @PostConstruct
    public void init() {
        
        statusMessage = "Please click button to start service";
    }
    
    public void startCourier() {
        
        statusMessage = "Service started";
        try {
            statusMessage = "";
            scheduler();
        }
        
        catch(SchedulerException se) {
            statusMessage = "Exception while running scheduler";
        }
    }
    
    
    //Code below is a sample from: http://www.quartz-scheduler.org/
    public void scheduler() throws SchedulerException {
        
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        
        // define the job and tie it to our MyJob class
        JobDetail job = newJob(MyJob.class)
            .withIdentity("job1", "group1")
            .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
            .withIdentity("trigger1", "group1")
            .startNow()
            .withSchedule(simpleSchedule()
//                    .withIntervalInHours(1)
                    .withIntervalInSeconds(10)
                    .repeatForever())
            .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }
}
