/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.entityclasses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "Expense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expense.findAll", query = "SELECT e FROM Expense e"),
    @NamedQuery(name = "Expense.findByExpenseID", query = "SELECT e FROM Expense e WHERE e.expenseID = :expenseID"),
    @NamedQuery(name = "Expense.findByExpenseName", query = "SELECT e FROM Expense e WHERE e.expenseName = :expenseName"),
    @NamedQuery(name = "Expense.findByExpenseAmount", query = "SELECT e FROM Expense e WHERE e.expenseAmount = :expenseAmount"),
    @NamedQuery(name = "Expense.findByExpenseLender", query = "SELECT e FROM Expense e WHERE e.expenseLender = :expenseLender"),
    @NamedQuery(name = "Expense.findByExpenseLendees", query = "SELECT e FROM Expense e WHERE e.expenseLendees = :expenseLendees"),
    @NamedQuery(name = "Expense.findByExpenseLocation", query = "SELECT e FROM Expense e WHERE e.expenseLocation = :expenseLocation"),
    @NamedQuery(name = "Expense.findByExpenseDetails", query = "SELECT e FROM Expense e WHERE e.expenseDetails = :expenseDetails"),
    @NamedQuery(name = "Expense.findByExpenseTimestamp", query = "SELECT e FROM Expense e WHERE e.expenseTimestamp = :expenseTimestamp"),
    @NamedQuery(name = "Expense.findByExpenseIsSettled", query = "SELECT e FROM Expense e WHERE e.expenseIsSettled = :expenseIsSettled"),
    @NamedQuery(name = "Expense.findByApartmentID", query = "SELECT e FROM Expense e WHERE e.apartmentID = :apartmentID")})
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "expense_ID")
    private Integer expenseID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "expense_name")
    private String expenseName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expense_lender")
    private int expenseLender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "expense_lendees")
    private String expenseLendees;
    @Size(max = 128)
    @Column(name = "expense_location")
    private String expenseLocation;
    @Size(max = 128)
    @Column(name = "expense_details")
    private String expenseDetails;
    @Column(name = "expense_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expenseTimestamp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "expense_is_settled")
    private String expenseIsSettled;
    @Column(name = "apartment_ID")
    private Integer apartmentID;

    public Expense() {
    }

    public Expense(Integer expenseID) {
        this.expenseID = expenseID;
    }

    public Expense(Integer expenseID, String expenseName, BigDecimal expenseAmount, int expenseLender, String expenseLendees, String expenseIsSettled) {
        this.expenseID = expenseID;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseLender = expenseLender;
        this.expenseLendees = expenseLendees;
        this.expenseIsSettled = expenseIsSettled;
    }

    public Integer getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(Integer expenseID) {
        this.expenseID = expenseID;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public int getExpenseLender() {
        return expenseLender;
    }

    public void setExpenseLender(int expenseLender) {
        this.expenseLender = expenseLender;
    }

    public String getExpenseLendees() {
        return expenseLendees;
    }

    public void setExpenseLendees(String expenseLendees) {
        this.expenseLendees = expenseLendees;
    }

    public String getExpenseLocation() {
        return expenseLocation;
    }

    public void setExpenseLocation(String expenseLocation) {
        this.expenseLocation = expenseLocation;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(String expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    public Date getExpenseTimestamp() {
        return expenseTimestamp;
    }

    public void setExpenseTimestamp(Date expenseTimestamp) {
        this.expenseTimestamp = expenseTimestamp;
    }

    public String getExpenseIsSettled() {
        return expenseIsSettled;
    }

    public void setExpenseIsSettled(String expenseIsSettled) {
        this.expenseIsSettled = expenseIsSettled;
    }

    public Integer getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(Integer apartmentID) {
        this.apartmentID = apartmentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expenseID != null ? expenseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expense)) {
            return false;
        }
        Expense other = (Expense) object;
        if ((this.expenseID == null && other.expenseID != null) || (this.expenseID != null && !this.expenseID.equals(other.expenseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entityclasses.Expense[ expenseID=" + expenseID + " ]";
    }
    
}
