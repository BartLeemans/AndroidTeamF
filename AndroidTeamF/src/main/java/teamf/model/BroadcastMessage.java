package teamf.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 12/03/13
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */


public class BroadcastMessage  {

    private int broadcastMessageID;

    private String msg;

    private String date;

    private Trip trip;

    public int getBroadcastMessageID() {
        return broadcastMessageID;
    }

    public void setBroadcastMessageID(int broadcastMessageID) {
        this.broadcastMessageID = broadcastMessageID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
