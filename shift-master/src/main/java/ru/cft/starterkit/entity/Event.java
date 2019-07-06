package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Objects;
import java.util.UUID;

public class Event implements Comparable<Event> {

    private Long id;
    private String  type;
    private String  email;
    private String name;
    private String lastname;
    private String phone;
    private String starts;
    private String ends;
    private boolean canceled;
    @JsonIgnore
    private UUID baz;


    public Event(String type, String email, String name, String lastname,String phone, String starts, String ends, UUID baz) {
        this.type = type;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.starts = starts;
        this.ends = ends;
        this.canceled = false;
        this.baz = baz;
    }

    public Event()  {
    }


    public Long getId() { return id; }
    public void setId(Long id) {this.id = id; }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public void setName(String name) {this.name = name;}
    public String getName(){return  this.name;}

    public  void setEmail(String email) {this.email = email;}
    public  String getEmail(){return this.email;}

    public void setLastname(String lastname) {this.lastname = lastname;}
    public  String getLastname(){return  this.lastname;}

    public void setPhone( String phone) {this.phone = phone;}
    public  String getPhone () {return  this.phone;}

    public void setStarts( String starts) {this.starts = starts;}
    public  String getStarts () { return this.starts;}

    private  DateTime getTimeStarts () {
        DateTimeFormatter pattern =  DateTimeFormat.forPattern("dd-MM-yyyy HH");
        DateTime date = DateTime.parse(this.starts,pattern);
        return  date;}

    public  void setEnds (String ends) {this.ends = ends;}
    public  String getEnds () {
        return  this.ends;}

    private  DateTime getTimeEnds () {
        DateTimeFormatter pattern =  DateTimeFormat.forPattern("dd-MM-yyyy HH");
        DateTime date = DateTime.parse(this.ends,pattern);
        return  date;}

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
             Objects.equals(type, entity.type) &&
               Objects.equals(email, entity.email) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(lastname, entity.lastname) &&
                Objects.equals(starts, entity.starts) &&
                Objects.equals(ends, entity.ends) &&
                Objects.equals(canceled, entity.canceled) &&
                Objects.equals(baz, entity.baz);
    }

    public boolean checkIfSoon() throws ParseException {

      if(this.getCanceled()) return false;
        DateTimeFormatter pattern =  DateTimeFormat.forPattern("dd-MM-yyyy HH");
       DateTime date = DateTime.parse(this.starts,pattern);
       DateTime startDate = DateTime.now();
       DateTime endDate = startDate.plusDays(7);
        if (date.isAfter(startDate)&&date.isBefore(endDate)) {return true;} else{ return false;}
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, /*type,*/email, name,lastname, starts,ends,canceled, baz);
    }


    @Override
    public String toString() {
        return "Event{" +
               "id=" + id +
               ", type='" + type  +
               ", email=" + email +
                ", name=" + name +
                ", lastname=" + lastname +
                ", starts=" + starts +
                ", ends=" + ends +
                ", canceled=" + canceled +
               ", baz=" + baz +
               '}';
    }

    public boolean checkstarttoend() {
        if (this.getTimeStarts().isAfter(this.getTimeEnds())) {
            return true;
        }
        return false;
    }

    public boolean checkCrossing(Event e)
    {
        if(this.getTimeStarts().isAfter(this.getTimeEnds())){return false;}
        if(this.getCanceled()) return false;
        if (this.getTimeStarts().isAfter(e.getTimeStarts())&&this.getTimeStarts().isBefore(e.getTimeEnds())) {return true;}
        if (this.getTimeEnds().isAfter(e.getTimeStarts())&&this.getTimeEnds().isBefore(e.getTimeEnds())) {return true;}
        if (this.getTimeStarts().isBefore(e.getTimeStarts())&&this.getTimeEnds().isAfter(e.getTimeEnds())) {return true;}
return  false;

    }



    @Override
    public int compareTo(Event o) {
        if(this.getTimeStarts().isAfter(o.getTimeStarts())) {return 1;}
        else {return -1;}
    }

    public boolean checkDay(String starts){
        if(this.getCanceled()) return false;
        DateTimeFormatter pattern =  DateTimeFormat.forPattern("dd-MM-yyyy");
        String noTimeStart = this.starts.substring(0,10);
        DateTime day = DateTime.parse(starts,pattern);
        DateTime event = DateTime.parse(noTimeStart,pattern);
        if (day.compareTo(event)==0) {return true;} else{ return false;}

    }
}

