/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.entityclasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "Apartment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartment.findAll", query = "SELECT a FROM Apartment a"),
    @NamedQuery(name = "Apartment.findByApartmentID", query = "SELECT a FROM Apartment a WHERE a.apartmentID = :apartmentID"),
    @NamedQuery(name = "Apartment.findByApartmentName", query = "SELECT a FROM Apartment a WHERE a.apartmentName = :apartmentName"),
    @NamedQuery(name = "Apartment.findByApartmentAddress", query = "SELECT a FROM Apartment a WHERE a.apartmentAddress = :apartmentAddress")})
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apartment_ID")
    private Integer apartmentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "apartmentName")
    private String apartmentName;
    @Size(max = 32)
    @Column(name = "apartmentAddress")
    private String apartmentAddress;

    public Apartment() {
    }

    public Apartment(Integer apartmentID) {
        this.apartmentID = apartmentID;
    }

    public Apartment(Integer apartmentID, String apartmentName) {
        this.apartmentID = apartmentID;
        this.apartmentName = apartmentName;
    }

    public Integer getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(Integer apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getApartmentAddress() {
        return apartmentAddress;
    }

    public void setApartmentAddress(String apartmentAddress) {
        this.apartmentAddress = apartmentAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apartmentID != null ? apartmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment other = (Apartment) object;
        if ((this.apartmentID == null && other.apartmentID != null) || (this.apartmentID != null && !this.apartmentID.equals(other.apartmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entityclasses.Apartment[ apartmentID=" + apartmentID + " ]";
    }
    
}
