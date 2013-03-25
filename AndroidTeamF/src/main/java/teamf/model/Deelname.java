package teamf.model;


import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/02/13
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */

public class Deelname implements Serializable {

    private int deelnameID;

    private Trip trip;

    private User user;

    private Collection<String> equipment;

    private Collection<Kost> kosten;

    private double lat;

    private double lng;


    public Deelname() {
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Deelname(Trip t, User u) {
        trip = t;
        user = u;
    }

    public int getDeelnameID() {
        return deelnameID;
    }

    public void setDeelnameID(int deelnameID) {
        this.deelnameID = deelnameID;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(Collection<String> userEquipment) {
        this.equipment = userEquipment;
    }

    public Collection<Kost> getKosten() {
        return kosten;
    }

    public void setKosten(Collection<Kost> kosten) {
        this.kosten = kosten;
    }
}
