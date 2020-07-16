package palyazatok;

import org.bson.BsonType;
import org.bson.conversions.Bson;
import palyazatok.Palyazat;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class PalyazatiResztvevok {
    private String szakmaiVezeto;
    private String projektmenedzser;
    private String kezelo; //A kezelo nem oktato, o nem szerepel az adatbazisban
    private ArrayList<String> resztvevoEmberek;

    public PalyazatiResztvevok(String szakmaiVezeto, String projektmenedzser,
                               String kezelo, ArrayList<String> resztvevoEmberek) {
        this.szakmaiVezeto = szakmaiVezeto;
        this.projektmenedzser = projektmenedzser;
        this.kezelo = kezelo;
        this.resztvevoEmberek = resztvevoEmberek;
    }

    public PalyazatiResztvevok() {
    }




    public String getSzakmaiVezeto() {
        return szakmaiVezeto;
    }

    public void setSzakmaiVezeto(String szakmaiVezeto) {
        this.szakmaiVezeto = szakmaiVezeto;
    }

    public String getProjektmenedzser() {
        return projektmenedzser;
    }

    public void setProjektmenedzser(String projektmenedzser) {
        this.projektmenedzser = projektmenedzser;
    }

    public String getKezelo() {
        return kezelo;
    }

    public void setKezelo(String kezelo) {
        this.kezelo = kezelo;
    }

    public ArrayList<String> getResztvevoEmberek() {
        return resztvevoEmberek;
    }

    public void setResztvevoEmberek(ArrayList<String> resztvevoEmberek) {
        this.resztvevoEmberek = resztvevoEmberek;
    }

    @Override
    public String toString() {
        return  "Szakmai vezető: " + getSzakmaiVezeto() + "\n" +
                "Projektmenedzser: " + getProjektmenedzser() + "\n" +
                "Kezelő: " + getKezelo() + "\n" +
                "Résztvevők: " + getResztvevoEmberek() + "\n";
    }

}
