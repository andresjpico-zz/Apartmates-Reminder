/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.Task;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
@Stateless
public class TaskFacade extends AbstractFacade<Task> {

    @PersistenceContext(unitName = "ApartMates-Reminder-Group2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
    
    /**
     * @param currentTime is the username attribute (column) value of the customer
     * @return object reference of the Customer entity whose username is username
     */
    public List<Task> findByReminderTime(String currentTime) {
        
        List<Task> tasks = em.createQuery("SELECT c FROM Task c WHERE c.taskReminder1 = :currentTime")
                .setParameter("currentTime", currentTime)
                .getResultList();
        
        return tasks;
    }
    
    /**
     * @param apartmentID is the apartmentID attribute (column) value of the Task
     * @return object reference of the Task entity whose apartmentID is apartmentID
     */
    public List<Task> findByApartmentID(int apartmentID) {
        if (em.createQuery("SELECT c FROM Task c WHERE c.apartmentID = :apartmentID")
                .setParameter("apartmentID", apartmentID)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (List<Task>) (em.createQuery("SELECT c FROM Task c WHERE c.apartmentID = :apartmentID")
                    .setParameter("apartmentID", apartmentID).getResultList());
        }
    }
    
}
