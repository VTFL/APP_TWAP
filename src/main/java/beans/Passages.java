package beans;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Passages {
    private int id;
    private String passager;
    private String arret;
    private String date;
    private String direction;

    public Passages(int id,String passager,String arret,String date,String direction) {
        this.id =id;
        this.passager = passager;
        this.arret = arret;
        this.date = date;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassager() {
        return passager;
    }

    public void setPassager(String passager) {
        this.passager = passager;
    }

    public String getArret() {
        return arret;
    }

    public void setArret(String arret) {
        this.arret = arret;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Passages{" +
                "id=" + id +
                ", passager='" + passager + '\'' +
                ", arret='" + arret + '\'' +
                ", date='" + date + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
