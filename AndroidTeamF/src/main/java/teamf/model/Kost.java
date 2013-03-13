package teamf.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Jorne
 * Date: 10/03/13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class Kost implements Serializable {
    private int kostId;

    private double prijs;

    private String beschrijving;


    private Deelname deelname;

    public Kost() {
    }

    public int getKostId() {
        return kostId;
    }

    public void setKostId(int kostId) {
        this.kostId = kostId;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Deelname getDeelname() {
        return deelname;
    }

    public void setDeelname(Deelname deelname) {
        this.deelname = deelname;
    }
}
