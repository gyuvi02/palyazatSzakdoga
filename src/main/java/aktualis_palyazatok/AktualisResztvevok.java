package aktualis_palyazatok;

import java.util.ArrayList;

public class AktualisResztvevok {
    String szakmaiVezeto;
    String projektmenedzser;
    String kezelo;
    ArrayList<String> resztvevoEmberek;

    public AktualisResztvevok(String szakmaiVezeto, String projektmenedzser,
                              String kezelo, ArrayList<String> resztvevoEmberek) {
        this.szakmaiVezeto = szakmaiVezeto;
        this.projektmenedzser = projektmenedzser;
        this.kezelo = kezelo;
        this.resztvevoEmberek = resztvevoEmberek;
    }

    public AktualisResztvevok() {
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
        return  "Szakmai vezető: " + szakmaiVezeto + "\n" +
                "Projektmenedzser: " + projektmenedzser + "\n" +
                "Kezelő: " + kezelo + "\n" +
                "Résztvevők: " + resztvevoEmberek + "\n";
    }

}
