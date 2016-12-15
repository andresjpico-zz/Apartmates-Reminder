package com.mycompany.entityclasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-15T04:48:06")
@StaticMetamodel(Roommate.class)
public class Roommate_ { 

    public static volatile SingularAttribute<Roommate, String> firstName;
    public static volatile SingularAttribute<Roommate, String> lastName;
    public static volatile SingularAttribute<Roommate, String> password;
    public static volatile SingularAttribute<Roommate, Integer> securityQuestion;
    public static volatile SingularAttribute<Roommate, String> securityAnswer;
    public static volatile SingularAttribute<Roommate, Integer> roommateID;
    public static volatile SingularAttribute<Roommate, String> email;
    public static volatile SingularAttribute<Roommate, Integer> apartmentID;
    public static volatile SingularAttribute<Roommate, Integer> points;

}