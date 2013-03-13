package teamf.model;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Jorne
 * Date: 8/02/13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */

public class StopPlaats {


    private int stopPlaatsID;
    private String adres;
    private boolean vrijgegeven = true;
    private String informatie;
    private String type;
    private String naam;
    private String vraag;
    private String correctAntwoord;

    private Collection<String> antwoorden;

    private Trip trip;

    public int getStopPlaatsID() {
        return stopPlaatsID;
    }

    public void setStopPlaatsID(int stopPlaatsID) {
        this.stopPlaatsID = stopPlaatsID;
    }

    public boolean isVrijgegeven() {
        return vrijgegeven;
    }

    public void setVrijgegeven(boolean vrijgegeven) {
        this.vrijgegeven = vrijgegeven;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {

        this.trip = trip;
    }

    public String getInformatie() {
        return informatie;
    }

    public void setInformatie(String informatie) {
        this.informatie = informatie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public String getCorrectAntwoord() {
        return correctAntwoord;
    }

    public void setCorrectAntwoord(String antwoord) {
        this.correctAntwoord = antwoord;
    }

    public Collection<String> getAntwoorden() {
        return antwoorden;
    }

    public void setAntwoorden(Collection<String> antwoorden) {
        this.antwoorden = antwoorden;
    }
}
