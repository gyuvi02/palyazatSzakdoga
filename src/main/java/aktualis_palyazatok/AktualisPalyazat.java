package aktualis_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import regi_palyazatok.RegiPalyazat;

import java.time.LocalDate;

import static com.mongodb.client.model.Filters.eq;

public class AktualisPalyazat {
    String palyazatCim;
    String leiras;
    String felhivasKod;
    String beadasEve;
    Boolean KplusF;
    Double onero;
    Double tervezettOsszkoltseg;
    Double igenyeltTamogatas;
    String megjegyzes;
    PalyazatiResztvevok resztvevok;
    String aktualisFazis;

    public AktualisPalyazat(String palyazatCim, String leiras, String felhivasKod, String beadasEve,
                            Boolean kplusF, Double onero, Double tervezettOsszkoltseg,
                            Double igenyeltTamogatas, String megjegyzes,
                            PalyazatiResztvevok resztvevok, String aktualisFazis) {
        this.palyazatCim = palyazatCim;
        this.leiras = leiras;
        this.felhivasKod = felhivasKod;
        this.beadasEve = beadasEve;
        KplusF = kplusF;
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;
        this.megjegyzes = megjegyzes;
        this.resztvevok = resztvevok;
        this.aktualisFazis = aktualisFazis;
    }

    public AktualisPalyazat() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public void aktualisPalyazatFeltolto() {
        aktualisPalyazatokColl.insertOne(this);
    }

    public void aktualisPalyazatTorlo(String palyazatCim) {
        Bson filter = eq("palyazatCim", palyazatCim);
        if (aktualisPalyazatEllenorzo(aktualisPalyazatokColl.find(filter).first())){
            aktualisPalyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");
    }

    private boolean aktualisPalyazatEllenorzo(AktualisPalyazat keresettAktualisPalyazat) {
        if (keresettAktualisPalyazat != null) {
            return true;
        }
        return false;
    }

    //ennek a metodusnak a segitsegevel a futo palyazatokat nehany adat megadasaval at lehet tenni a regiek koze, es azutan torolni
    public RegiPalyazat aktualisbolRegi(AktualisPalyazat aktualis, String deAzonosito, String szerzodesSzam, LocalDate kezdet,
                                LocalDate veg) {
        RegiPalyazat ujRegi = new RegiPalyazat(aktualis.palyazatCim, deAzonosito, szerzodesSzam, aktualis.leiras,
                aktualis.felhivasKod, aktualis.beadasEve , kezdet, veg,  aktualis.KplusF, aktualis.onero, aktualis.tervezettOsszkoltseg,
                aktualis.igenyeltTamogatas, aktualis.megjegyzes, aktualis.resztvevok, aktualis.aktualisFazis);
        aktualisPalyazatTorlo(aktualis.palyazatCim);
        return ujRegi;
    }

    public String getPalyazatCim() {
        return palyazatCim;
    }

    public void setPalyazatCim(String palyazatCim) {
        this.palyazatCim = palyazatCim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getFelhivasKod() {
        return felhivasKod;
    }

    public void setFelhivasKod(String felhivasKod) {
        this.felhivasKod = felhivasKod;
    }

    public Boolean getKplusF() {
        return KplusF;
    }

    public void setKplusF(Boolean kplusF) {
        KplusF = kplusF;
    }

    public Double getOnero() {
        return onero;
    }

    public void setOnero(Double onero) {
        this.onero = onero;
    }

    public Double getTervezettOsszkoltseg() {
        return tervezettOsszkoltseg;
    }

    public void setTervezettOsszkoltseg(Double tervezettOsszkoltseg) {
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
    }

    public Double getIgenyeltTamogatas() {
        return igenyeltTamogatas;
    }

    public void setIgenyeltTamogatas(Double igenyeltTamogatas) {
        this.igenyeltTamogatas = igenyeltTamogatas;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    public PalyazatiResztvevok getResztvevok() {
        return resztvevok;
    }

    public void setResztvevok(PalyazatiResztvevok resztvevok) {
        this.resztvevok = resztvevok;
    }

    public String getAktualisFazis() {
        return aktualisFazis;
    }

    public void setAktualisFazis(String aktualisFazis) {
        this.aktualisFazis = aktualisFazis;
    }

    @Override
    public String toString() {
        return "Pályázat címe: " + palyazatCim + "\n" +
                "Leírás: " + leiras + "\n" +
                "Felhíváskód: " + felhivasKod + "\n" +
                "K+F: " + KplusF + "\n" +
                "Önerő: " + onero + "\n" +
                "Tervezett összköltség: " + tervezettOsszkoltseg + "\n" +
                "Igényelt támogatás: " + igenyeltTamogatas + "\n" +
                "Megjegyzés: " + megjegyzes + "\n" +
                "Szakmai vezető: " + resztvevok.szakmaiVezeto + "\n"+
                "Projektmenedzser: " + resztvevok.projektmenedzser + "\n" +
                "A pályázat kezelője: " + resztvevok.kezelo + "\n" +
                "Résztvevő kutatók: " + resztvevok.resztvevoEmberek.toString() + "\n" +
                "A pályázat állapota: " + aktualisFazis + "\n";
    }


}
