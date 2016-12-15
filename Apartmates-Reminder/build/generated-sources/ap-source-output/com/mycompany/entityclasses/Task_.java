package com.mycompany.entityclasses;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-15T04:48:06")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, String> taskLocation;
    public static volatile SingularAttribute<Task, Date> taskDeadline;
    public static volatile SingularAttribute<Task, String> taskPriority;
    public static volatile SingularAttribute<Task, String> taskName;
    public static volatile SingularAttribute<Task, Date> taskReminder1;
    public static volatile SingularAttribute<Task, Date> taskReminder2;
    public static volatile SingularAttribute<Task, Date> taskReminder3;
    public static volatile SingularAttribute<Task, Integer> taskID;
    public static volatile SingularAttribute<Task, Integer> apartmentID;
    public static volatile SingularAttribute<Task, String> taskDetails;
    public static volatile SingularAttribute<Task, String> taskIsComplete;

}