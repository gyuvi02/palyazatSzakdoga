package palyazatkezelo;

import aktualis_palyazatok.PalyazatiResztvevok;

public abstract class Palyazat {
    private String palyazatCim;
    private String leiras;
    private String felhivasKod;
    private String beadasEve;
    private Boolean KplusF;
    private Double onero;
    private Double tervezettOsszkoltseg;
    private Double igenyeltTamogatas;
    private String megjegyzes;
    private PalyazatiResztvevok resztvevok;
    private String aktualisFazis;

    public Palyazat(String palyazatCim, String leiras, String felhivasKod, String beadasEve,
                    Boolean kplusF, Double onero, Double tervezettOsszkoltseg,
                    Double igenyeltTamogatas, String megjegyzes, PalyazatiResztvevok resztvevok,
                    String aktualisFazis) {
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

    public Palyazat(String palyazatCim) {
        this.palyazatCim = palyazatCim;
    }

    public Palyazat() {
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

    public String getBeadasEve() {
        return beadasEve;
    }

    public void setBeadasEve(String beadasEve) {
        this.beadasEve = beadasEve;
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
        return "Pályázat címe: " + getPalyazatCim() + "\n" +
                "Leírás: " + getLeiras() + "\n" +
                "Felhíváskód: " + getFelhivasKod() + "\n" +
                "K+F: " + getKplusF() + "\n" +
                "Önerő: " + getOnero() + "\n" +
                "Tervezett összköltség: " + getTervezettOsszkoltseg() + "\n" +
                "Igényelt támogatás: " + getIgenyeltTamogatas() + "\n" +
                "Megjegyzés: " + getMegjegyzes() + "\n" +
                "Szakmai vezető: " + getResztvevok().getSzakmaiVezeto() + "\n"+     //ha nincs megadva, akkor NullPointerException lesz
                "Projektmenedzser: " + getResztvevok().getSzakmaiVezeto() + "\n" +
                "A pályázat kezelője: " + getResztvevok().getKezelo() + "\n" +
                "Résztvevő kutatók: " + getResztvevok().getResztvevoEmberek().toString() + "\n" +
                "A pályázat állapota: " + getAktualisFazis() + "\n";
    }


}
