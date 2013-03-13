package teamf.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 7/03/13
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */


public class Chat {

    private int chatID;

    private String msg;

    private Date date;


    private Trip trip;


    private User user;

    public Chat() {
    }

    public Chat(Trip trip, User user, String msg) {
        this.date = new Date();
        this.trip = trip;
        this.user = user;
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public void setDate(Date date) {
        this.date = date;
    }
}
