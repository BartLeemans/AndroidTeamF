package teamf.model;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 12/02/13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */


public class TripCategorie {

    private int tripCategorieId;

    private String tripCategorieName;


    private Trip trip;

    public int getTripCategorieId() {
        return tripCategorieId;
    }

    public void setTripCategorieId(int tripCategorieId) {
        this.tripCategorieId = tripCategorieId;
    }

    public String getTripCategorieName() {
        return tripCategorieName;
    }

    public void setTripCategorieName(String tripCategorieName) {
        this.tripCategorieName = tripCategorieName;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
