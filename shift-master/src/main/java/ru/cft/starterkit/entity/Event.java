package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Event {

    private Long id;
    //private String  type;
    private String  email;
    private String name;
    private String lastname;
    private String starts;
    private String ends;
    private boolean canceled;
    @JsonIgnore
    private UUID baz;

    public Event() {

    }

    public Event(/*String type, */String email, String name, String lastname, String starts, String ends, UUID baz) {
      //  this.type = type;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.starts = starts;
        this.ends = ends;
        this.canceled = false;
        this.baz = baz;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public String getType() {
        return this.type;
    }*/

   /* public void setType(String type) {
        this.type = type;
    }*/


    public void setName(String name) {this.name = name;}

    public String getName(){return  this.name;}

    public  void setEmail(String email) {this.email = email;}

    public  String getEmail(){return this.email;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public  String getLastname(){return  this.lastname;}

    public void setStarts( String starts) {this.starts = starts;}

    public  String getStarts () {return  this.starts;}

    public  void setEnds (String ends) {this.ends = ends;}

    public  boolean getCanceled () {return  this.canceled;}

    public  void Cancel () {this.canceled = true;}

    public UUID getBaz() {
        return baz;
    }

    public void setBaz(UUID baz) {
        this.baz = baz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event entity = (Event) o;
        return Objects.equals(id, entity.id) &&
             //  Objects.equals(type, entity.type) &&
               Objects.equals(email, entity.email) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(lastname, entity.lastname) &&
                Objects.equals(starts, entity.starts) &&
                Objects.equals(ends, entity.ends) &&
                Objects.equals(canceled, entity.canceled) &&
                Objects.equals(baz, entity.baz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, /*type,*/email, name,lastname, starts,ends,canceled, baz);
    }

    @Override
    public String toString() {
        return "Event{" +
               "id=" + id +
               //", type='" + type  +
               ", email=" + email +
                ", name=" + name +
                ", lastname=" + lastname +
                ", starts=" + starts +
                ", ends=" + ends +
                ", canceled=" + canceled +
               ", baz=" + baz +
               '}';
    }

}
  /*  import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class TestDateExample4 {

    public static void main(String[] argv) {

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        String dateInString = "Friday, Jun 7, 2013 12:10:56 PM";

        try {

            Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}*/