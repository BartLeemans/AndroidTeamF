package teamf.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 7/02/13
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */


//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")

public class Trip {

    private int tripId;

    private String tripName;

    private String tripDescription;

    private String notification;

    private Date startDate;

    private Date endDate;

    private String startLocation;

    private Collection<String> equipment;

    private boolean visible = true;

    private String fontcolorTitle = "#9CFF00";

    private String fontcolorContent = "#D4D4D4";

    private String bgcolor = "#1C263C";

    private boolean showMap = false;

    private boolean showRoute = true;

    private String travelType;


    private User organiser;


    private TripType tripType;


    private Collection<StopPlaats> stopPlaatsen;


    private Collection<TripCategorie> tripCategorieen;


    private Collection<Deelname> deelnames;


    private Collection<Chat> chats;

    public Collection<Chat> getChats() {
        return chats;
    }

    public void setChats(Collection<Chat> chats) {
        this.chats = chats;
    }

    public Trip() {
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getOrganiser() {
        return organiser;
    }

    public void setOrganiser(User organiser) {
        this.organiser = organiser;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public Collection<StopPlaats> getStopPlaatsen() {
        return stopPlaatsen;
    }

    public void setStopPlaatsen(Collection<StopPlaats> stopPlaatsen) {
        this.stopPlaatsen = stopPlaatsen;
    }

    public Collection<TripCategorie> getTripCategorieen() {
        return tripCategorieen;
    }

    public void setTripCategorieen(Collection<TripCategorie> tripCategorieen) {
        this.tripCategorieen = tripCategorieen;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public Collection<Deelname> getDeelnames() {
        return deelnames;
    }

    public void setDeelnames(Collection<Deelname> deelnames) {
        this.deelnames = deelnames;
    }

    public String getFontcolorTitle() {
        return fontcolorTitle;
    }

    public String getFontcolorContent() {
        return fontcolorContent;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setFontcolorTitle(String fontcolorTitle) {
        this.fontcolorTitle = fontcolorTitle;
    }

    public void setFontcolorContent(String fontcolorContent) {
        this.fontcolorContent = fontcolorContent;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Collection<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(Collection<String> equipment) {
        this.equipment = equipment;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getShowMap() {
        return showMap;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public boolean getShowRoute() {
        return showRoute;
    }

    public void setShowRoute(boolean showRoute) {
        this.showRoute = showRoute;
    }


}
