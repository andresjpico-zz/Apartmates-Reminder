/*
 * Created by Andres Pico on 2016.12.12  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.Roommate;
import com.mycompany.entityclasses.Task;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author Andres
 */
@Stateless
public class RoommateFacade extends AbstractFacade<Roommate> {

    @PersistenceContext(unitName = "Apartmates-Reminder-Group2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoommateFacade() {
        super(Roommate.class);
    }
    
    /**
     * @param apartmentID is the apartmentID attribute (column) value of the Task
     * @return object reference of the Task entity whose apartmentID is apartmentID
     */
    public List<Roommate> findByApartmentID(int apartmentID) {
        if (em.createQuery("SELECT c FROM Roommate c WHERE c.apartmentID = :apartmentID")
                .setParameter("apartmentID", apartmentID)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (List<Roommate>) (em.createQuery("SELECT c FROM Roommate c WHERE c.apartmentID = :apartmentID")
                    .setParameter("apartmentID", apartmentID).getResultList());
        }
    }
    
    /**
     * @param apartmentID is the apartmentID attribute (column) value of the Task
     * @return object reference of the Task entity whose apartmentID is apartmentID
     */
    //ADD FILTER SO THAT IT ONLY RETRIEVES NOT COMPLETED TASKS
    public List<Task> findTaskByApartmentID(int apartmentID) {
        if (em.createQuery("SELECT c FROM Task c WHERE c.apartmentID = :apartmentID")
                .setParameter("apartmentID", apartmentID)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (List<Task>) (em.createQuery("SELECT c FROM Task c WHERE c.apartmentID = :apartmentID")
                    .setParameter("apartmentID", apartmentID).getResultList());
        }
    }
    
    /**
     * @param currentTime is the username attribute (column) value of the customer
     * @return object reference of the Customer entity whose username is username
     */
    public List<Task> findByReminderTime(Date currentTime) {
        
        List<Task> tasks = em.createQuery("SELECT c FROM Task c WHERE c.taskReminder1 = :currentTime")
                .setParameter("currentTime", currentTime)
                .getResultList();
        
        return tasks;
    }
    
    /**
     * @param taskIsComplete is the username attribute (column) value of the customer
     * @return object reference of the Customer entity whose username is username
     */
    public List<Task> findByTaskCompleteness(String taskIsComplete) {
        
        List<Task> tasks = em.createQuery("SELECT c FROM Task c WHERE c.taskIsComplete = :taskIsComplete")
                .setParameter("taskIsComplete", taskIsComplete)
                .getResultList();
        
        return tasks;
    }
    
    /**
     * @return object reference of the Customer entity whose username is username
     */
    public List<Task> findNotCompletedTasks() {
        
        List<Task> tasks = em.createQuery("SELECT c FROM Task c WHERE c.taskIsComplete = :taskIsComplete")
                .setParameter("taskIsComplete", "No")
                .getResultList();
        
        return tasks;
    }
    
}
