/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.Apartment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
@Stateless
public class ApartmentFacade extends AbstractFacade<Apartment> {

    @PersistenceContext(unitName = "ApartMates-Reminder-Group2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApartmentFacade() {
        super(Apartment.class);
    }
    
}
