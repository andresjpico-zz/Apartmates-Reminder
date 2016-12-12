/*
 * Created by Andres Pico on 2016.12.12  * 
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
@Table(name = "Roommate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roommate.findAll", query = "SELECT r FROM Roommate r"),
    @NamedQuery(name = "Roommate.findByRoommateID", query = "SELECT r FROM Roommate r WHERE r.roommateID = :roommateID"),
    @NamedQuery(name = "Roommate.findByPassword", query = "SELECT r FROM Roommate r WHERE r.password = :password"),
    @NamedQuery(name = "Roommate.findByFirstName", query = "SELECT r FROM Roommate r WHERE r.firstName = :firstName"),
    @NamedQuery(name = "Roommate.findByLastName", query = "SELECT r FROM Roommate r WHERE r.lastName = :lastName"),
    @NamedQuery(name = "Roommate.findByEmail", query = "SELECT r FROM Roommate r WHERE r.email = :email"),
    @NamedQuery(name = "Roommate.findBySecurityQuestion", query = "SELECT r FROM Roommate r WHERE r.securityQuestion = :securityQuestion"),
    @NamedQuery(name = "Roommate.findBySecurityAnswer", query = "SELECT r FROM Roommate r WHERE r.securityAnswer = :securityAnswer"),
    @NamedQuery(name = "Roommate.findByApartmentID", query = "SELECT r FROM Roommate r WHERE r.apartmentID = :apartmentID"),
    @NamedQuery(name = "Roommate.findByPoints", query = "SELECT r FROM Roommate r WHERE r.points = :points")})
public class Roommate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roommate_ID")
    private Integer roommateID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "security_question")
    private int securityQuestion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "security_answer")
    private String securityAnswer;
    @Column(name = "apartment_ID")
    private Integer apartmentID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "points")
    private int points;

    public Roommate() {
    }

    public Roommate(Integer roommateID) {
        this.roommateID = roommateID;
    }

    public Roommate(Integer roommateID, String password, String firstName, String lastName, String email, int securityQuestion, String securityAnswer, int points) {
        this.roommateID = roommateID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.points = points;
    }

    public Integer getRoommateID() {
        return roommateID;
    }

    public void setRoommateID(Integer roommateID) {
        this.roommateID = roommateID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Integer getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(Integer apartmentID) {
        this.apartmentID = apartmentID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roommateID != null ? roommateID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roommate)) {
            return false;
        }
        Roommate other = (Roommate) object;
        if ((this.roommateID == null && other.roommateID != null) || (this.roommateID != null && !this.roommateID.equals(other.roommateID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entityclasses.Roommate[ roommateID=" + roommateID + " ]";
    }
    
}
