/*
 * Created by Osman Balci on 2016.05.30  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import java.util.List;

/* 
 * An instance of javax.persistence.EntityManager represents an Entity Manager.
 * An Entity Manager manages JPA Entities. 
 * Each Entity Manager instance is associated with a persistence context.
 * A persistence context is a set of managed entity instances. 
 */
import javax.persistence.EntityManager;

/**
 * The AbstractFacade.java is an abstract Facade class providing a generic interface to the Entity Manager.
 *
 * @author Balci
 * @param <T> refers to the Class Type
 */
public abstract class AbstractFacade<T> {

    // An instance variable pointing to a class object of type T
    private Class<T> entityClass;

    /* This is the constructor method called by the subclass CompanyFacade.java class's
     * constructor method by passing the Company.class as a parameter.
     * Company.class returns an object reference to the Company class, which is set 
     * as the value of the entityClass instance variable.
     * The class type T is set to the Company entity class. So, T = Company
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /* This method is overridden in CompanyFacade.java, which is the 
     * concrete Facade subclass providing the actual implementation. 
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Stores the newly CREATED Company (entity) object into the database
     * @param entity contains object reference of the Company
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Stores the EDITED Company (entity) object into the database
     * @param entity contains object reference of the Company
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Deletes the given Company (entity) object from the database
     *
     * @param entity contains object reference of the Company
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Finds and returns a Company in the database by using its Primary Key (id)
     *
     * @param id refers to the Primary Key of the Company
     * @return contains object reference of the Company found
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * @return a list of object references of all of the companies in the database
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Finds and returns a list of companies within a certain id range.
     * 
     * Returns a List of Company objects in a range from the database. 
     * The range is specified by the range parameter of type integer array.
     * The 1st element of the range array = index number of the first Company object to retrieve.
     * The 2nd element of the range array = index number of the last Company object to retrieve.
     *
     * @param range The id range
     * @return a list of companies within a certain id range
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Obtains and returns the total number of companies in the database
     *
     * @return the number of companies
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
