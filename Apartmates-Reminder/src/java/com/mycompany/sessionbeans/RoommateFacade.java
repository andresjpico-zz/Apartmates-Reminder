/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.Roommate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
@Stateless
public class RoommateFacade extends AbstractFacade<Roommate> {

    @PersistenceContext(unitName = "ApartMates-Reminder-Group2")
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
    
}
