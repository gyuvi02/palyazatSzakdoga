package regi_palyazatok;

import aktualis_palyazatok.AktualisPalyazat;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class RegiPalyazat {
    String regiCim;
    String DEazonosito;
    String szerzodesSzam;
    String leiras;
    String felhivasKod;
    LocalDate kezdet;
    LocalDate veg;
    Boolean KplusF;
    Double onero;
    Double tervezettOsszkoltseg;
    Double igenyeltTamogatas;
    String megjegyzes;
    RegiResztvevok resztvevok;

    public RegiPalyazat(String regiCim, String DEazonosito, String szerzodesSzam, String leiras,
                        String felhivasKod, LocalDate kezdet, LocalDate veg, Boolean kplusF, Double onero,
                        Double tervezettOsszkoltseg, Double igenyeltTamogatas, String megjegyzes,
                        RegiResztvevok resztvevok) {
        this.regiCim = regiCim;
        this.DEazonosito = DEazonosito;
        this.szerzodesSzam = szerzodesSzam;
        this.leiras = leiras;
        this.felhivasKod = felhivasKod;
        this.kezdet = kezdet;
        this.veg = veg;
        KplusF = kplusF;
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;
        this.megjegyzes = megjegyzes;
        this.resztvevok = resztvevok;
    }

    public RegiPalyazat() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RegiPalyazat> regiPalyazatokColl = palyazatDB.getCollection("RegiPalyazatok", RegiPalyazat.class);

    public void regiPalyazatFeltolto() {
        regiPalyazatokColl.insertOne(this);
    }

    public void regiPalyazatLetolto(String cim) {
        RegiPalyazat keresettRegiPalyazat = regiPalyazatokColl.find((eq("regiCim", cim))).first();
        if (regiPalyazatEllenorzo(keresettRegiPalyazat)) {
            System.out.println(keresettRegiPalyazat.toString());
        }else System.out.println("Nincs ilyen pályázat");
    }

    public void regiPalyazatTorol(String torlendoRegiPalyazat) {
        Bson filter = eq("regiCim", torlendoRegiPalyazat);
        if (regiPalyazatEllenorzo(regiPalyazatokColl.find(filter).first())){
            regiPalyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");

    }

    private boolean regiPalyazatEllenorzo(RegiPalyazat keresettRegiPalyazat) {
        if (keresettRegiPalyazat != null) {
            return true;
        }
        return false;
    }

    public String getRegiCim() {
        return regiCim;
    }

    public void setRegiCim(String regiCim) {
        this.regiCim = regiCim;
    }

    public String getDEazonosito() {
        return DEazonosito;
    }

    public void setDEazonosito(String DEazonosito) {
        this.DEazonosito = DEazonosito;
    }

    public String getSzerzodesSzam() {
        return szerzodesSzam;
    }

    public void setSzerzodesSzam(String szerzodesSzam) {
        this.szerzodesSzam = szerzodesSzam;
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

    public LocalDate getKezdet() {
        return kezdet;
    }

    public void setKezdet(LocalDate kezdet) {
        this.kezdet = kezdet;
    }

    public LocalDate getVeg() {
        return veg;
    }

    public void setVeg(LocalDate veg) {
        this.veg = veg;
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

    public RegiResztvevok getResztvevok() {
        return resztvevok;
    }

    public void setResztvevok(RegiResztvevok resztvevok) {
        this.resztvevok = resztvevok;
    }

    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy. MMMM dd. ");
    @Override
    public String toString() {
        return "Pályázat címe: " + regiCim + "\n" +
                "Egyetemi azonosító: " + DEazonosito + "\n" +
                "Szerződés száma: " + szerzodesSzam + "\n" +
                "Leírás: " + leiras + "\n" +
                "Felhíváskód: " + felhivasKod + "\n" +
                "Pályázat kezdete: " + kezdet.format(formatters) + "\n" +
                "Pályázat vége: " + veg.format(formatters) + "\n" +
                "K+F: " + KplusF + "\n" +
                "Önerő: " + onero + "\n" +
                "Tervezett összköltség: " + tervezettOsszkoltseg + "\n" +
                "Igényelt támogatás: " + igenyeltTamogatas + "\n" +
                "Megjegyzés: " + megjegyzes + "\n" +
                "Szakmai vezető: " + resztvevok.szakmaiVezeto + "\n"+
                "Projektmenedzser: " + resztvevok.projektmenedzser + "\n" +
                "A pályázat kezelője: " + resztvevok.kezelo + "\n" +
                "Résztvevő kutatók: " + resztvevok.resztvevoEmberek.toString() + "\n";
    }
}
