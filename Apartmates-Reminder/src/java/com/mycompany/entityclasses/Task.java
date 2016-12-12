/*
 * Created by Andres Pico on 2016.12.11  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.mycompany.entityclasses;

import java.io.Serializable;
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
@Table(name = "Task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByTaskID", query = "SELECT t FROM Task t WHERE t.taskID = :taskID"),
    @NamedQuery(name = "Task.findByTaskName", query = "SELECT t FROM Task t WHERE t.taskName = :taskName"),
    @NamedQuery(name = "Task.findByTaskDetails", query = "SELECT t FROM Task t WHERE t.taskDetails = :taskDetails"),
    @NamedQuery(name = "Task.findByTaskLocation", query = "SELECT t FROM Task t WHERE t.taskLocation = :taskLocation"),
    @NamedQuery(name = "Task.findByTaskDeadline", query = "SELECT t FROM Task t WHERE t.taskDeadline = :taskDeadline"),
    @NamedQuery(name = "Task.findByTaskReminder1", query = "SELECT t FROM Task t WHERE t.taskReminder1 = :taskReminder1"),
    @NamedQuery(name = "Task.findByTaskReminder2", query = "SELECT t FROM Task t WHERE t.taskReminder2 = :taskReminder2"),
    @NamedQuery(name = "Task.findByTaskReminder3", query = "SELECT t FROM Task t WHERE t.taskReminder3 = :taskReminder3"),
    @NamedQuery(name = "Task.findByTaskPriority", query = "SELECT t FROM Task t WHERE t.taskPriority = :taskPriority"),
    @NamedQuery(name = "Task.findByTaskIsComplete", query = "SELECT t FROM Task t WHERE t.taskIsComplete = :taskIsComplete"),
    @NamedQuery(name = "Task.findByApartmentID", query = "SELECT t FROM Task t WHERE t.apartmentID = :apartmentID")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_ID")
    private Integer taskID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "task_name")
    private String taskName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "task_details")
    private String taskDetails;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "task_location")
    private String taskLocation;
    @Column(name = "task_deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskDeadline;
    @Column(name = "task_reminder1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskReminder1;
    @Column(name = "task_reminder2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskReminder2;
    @Column(name = "task_reminder3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskReminder3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "task_priority")
    private String taskPriority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "task_is_complete")
    private String taskIsComplete;
    @Column(name = "apartment_ID")
    private Integer apartmentID;

    public Task() {
    }

    public Task(Integer taskID) {
        this.taskID = taskID;
    }

    public Task(Integer taskID, String taskName, String taskDetails, String taskLocation, String taskPriority, String taskIsComplete) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.taskLocation = taskLocation;
        this.taskPriority = taskPriority;
        this.taskIsComplete = taskIsComplete;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskLocation() {
        return taskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        this.taskLocation = taskLocation;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Date getTaskReminder1() {
        return taskReminder1;
    }

    public void setTaskReminder1(Date taskReminder1) {
        this.taskReminder1 = taskReminder1;
    }

    public Date getTaskReminder2() {
        return taskReminder2;
    }

    public void setTaskReminder2(Date taskReminder2) {
        this.taskReminder2 = taskReminder2;
    }

    public Date getTaskReminder3() {
        return taskReminder3;
    }

    public void setTaskReminder3(Date taskReminder3) {
        this.taskReminder3 = taskReminder3;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskIsComplete() {
        return taskIsComplete;
    }

    public void setTaskIsComplete(String taskIsComplete) {
        this.taskIsComplete = taskIsComplete;
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
        hash += (taskID != null ? taskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskID == null && other.taskID != null) || (this.taskID != null && !this.taskID.equals(other.taskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entityclasses.Task[ taskID=" + taskID + " ]";
    }
    
}
