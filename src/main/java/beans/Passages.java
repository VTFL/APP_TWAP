package beans;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by thiba on 08/11/2016.
 */
public class Passages {
    private int id;
    private int id_user;
    private int id_arret;
    private String date;
    private String direction;

    public Passages(int id,int id_user,int id_arret,String date,String direction) {
        this.id =id;
        this.id_arret = id_user;
        this.id_arret = id_arret;
        this.date = date;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_arret() {
        return id_arret;
    }

    public void setId_arret(int id_arret) {
        this.id_arret = id_arret;
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
                ", id_user=" + id_user +
                ", id_arret=" + id_arret +
                ", date='" + date + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
