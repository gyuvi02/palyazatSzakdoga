package regi_palyazatok;

import java.util.ArrayList;
import java.util.Date;

public class RegiPalyazat {
    String regiCim;
    String DEazonosito;
    String szerzodesSzam;
    String leiras;
    String felhivasKod;
    Date kezdet;
    Date veg;
    Boolean KplusF;
    Double onero;
    Double tervezettOsszkoltseg;
    Double igenyeltTamogatas;
    String megjegyzes;
    Resztvevok resztvevok;

    public RegiPalyazat(String regiCim, String DEazonosito, String szerzodesSzam, String leiras,
                        String felhivasKod, Date kezdet, Date veg, Boolean kplusF, Double onero,
                        Double tervezettOsszkoltseg, Double igenyeltTamogatas, String megjegyzes,
                        Resztvevok resztvevok) {
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

    protected class Resztvevok {
        String szakmaiVezeto;
        String projektmenedzser;
        String kezelo;
        ArrayList<String> resztvevoEmberek;
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

    public Date getKezdet() {
        return kezdet;
    }

    public void setKezdet(Date kezdet) {
        this.kezdet = kezdet;
    }

    public Date getVeg() {
        return veg;
    }

    public void setVeg(Date veg) {
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

    public Resztvevok getResztvevok() {
        return resztvevok;
    }

    public void setResztvevok(Resztvevok resztvevok) {
        this.resztvevok = resztvevok;
    }

    @Override
    public String toString() {
        return "Pályázat címe: " + regiCim + "\n" +
                "Egyetemi azonosító: " + DEazonosito + "\n" +
                "Szerződés száma: " + szerzodesSzam + "\n" +
                "Leírás: " + leiras + "\n" +
                "Felhíváskód: " + felhivasKod + "\n" +
                "Pályázat kezdete: " + kezdet + "\n" +
                "Pályázat vége: " + veg + "\n" +
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
