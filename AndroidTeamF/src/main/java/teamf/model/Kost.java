package teamf.model;

/**
 * Created with IntelliJ IDEA.
 * User: Jorne
 * Date: 5/03/13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */

public class Kost {

    private int kostId;


    private double prijs;


    private String beschrijving;


    private Deelname deelname;

    public Kost() {
    }

    public int getKostId() {
        return kostId;
    }

    public void setKostId(int id) {
        this.kostId = id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public Deelname getDeelname() {
        return deelname;
    }

    public void setDeelname(Deelname deelname) {
        this.deelname = deelname;
    }
}
