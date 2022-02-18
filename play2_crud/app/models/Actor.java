package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import play.data.validation.Constraints;
import play.db.ebean.*;
import java.util.*;
import play.db.*;
import java.sql.*;


@Entity

public class Actor extends Model {
    Connection connection = DB.getConnection();
    @Id
    @GeneratedValue
    public Long id;
    @Constraints.Required
    public String firstname;
    @Constraints.Required
    public String lastname;
    public Integer age;
    public String role;


    public Actor() {

    }


    public String toString()

    {

        return "id: " + id + " firstname:" + firstname + " lastname:" + lastname + " age:" + age + " role:" + role;

    }


    // -- Queries

    public static Model.Finder<Long,Actor> find = new Model.Finder(Long.class, Actor.class);



    // Find by id:

    public static Actor findById( Long _id )

    {

        return find.where()

                .eq( "id", _id )

                .findUnique();

    }

    // Find all:

    public static List<Actor> findAll() {

        return find.all();

    }




}
