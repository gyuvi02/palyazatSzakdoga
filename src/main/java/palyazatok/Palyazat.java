package palyazatok;

import palyazatkezelo.PalyazatiResztvevok;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

//csak a teszteleskor adok hozza teljes palyazatot
public class Palyazat {
    private String palyazatCim;
    private String aktualisFazis; //ez lehet elkezdett, beadott, lezart, elfogadott, elutasitott
    private String leiras;
    private String felhivasKod;
    private String beadasEve;
    private Boolean KplusF;
    private Double onero;
    private Double tervezettOsszkoltseg;
    private Double igenyeltTamogatas;
    private String megjegyzes;
    private PalyazatiResztvevok resztvevok = new PalyazatiResztvevok();
    private String DEazonosito;
    private String szerzodesSzam;
    private LocalDate kezdet;
    private LocalDate veg;

    public Palyazat(String palyazatCim, String aktualisFazis, String leiras, String felhivasKod, String beadasEve,
                    Boolean kplusF, Double onero, Double tervezettOsszkoltseg, Double igenyeltTamogatas,
                    String megjegyzes, PalyazatiResztvevok resztvevok, String DEazonosito, String szerzodesSzam,
                    LocalDate kezdet, LocalDate veg) {
        this.palyazatCim = palyazatCim;
        this.aktualisFazis = aktualisFazis;
        this.leiras = leiras;
        this.felhivasKod = felhivasKod;
        this.beadasEve = beadasEve;
        KplusF = kplusF;
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;
        this.megjegyzes = megjegyzes;
        this.resztvevok = resztvevok;
        this.DEazonosito = DEazonosito;
        this.szerzodesSzam = szerzodesSzam;
        this.kezdet = kezdet;
        this.veg = veg;
    }
    //Alapbol ezzel hozzuk letre, a tobbi mezot utolag adjuk hozza
    public Palyazat(String palyazatCim, String aktualisFazis) {
        this.palyazatCim = palyazatCim;
        this.aktualisFazis = aktualisFazis;
        this.kezdet = LocalDate.now();
        this.veg = LocalDate.now();
        this.resztvevok = new PalyazatiResztvevok("", "", "", new ArrayList<String>()); //ezt a NullPointerException elkerulese miatt adjuk hozza
    }

    public Palyazat() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);


    public void PalyazatFeltolto() {
        Bson filter = eq("palyazatCim", this.getPalyazatCim()); //csak a cimet ellenorzom, nem lehet 2 egyforma cimu palyazat
        if (palyazatEllenorzo(palyazatokColl.find(filter).first())) {
            System.out.println("Mar van ilyen palyazat");
        } else {
            palyazatokColl.insertOne(this);
        }
    }

    public Palyazat PalyazatLetolto(String palyazatCim) {
        return palyazatokColl.find((eq("palyazatCim", palyazatCim))).first(); //Ha ureset kuld vissza, akkor nem leteik a palyazat

    }

    public void PalyazatTorlo(String palyazatcim) {
        Bson filter = eq("palyazatCim", palyazatcim); //Mivel a cim egyedi, ezt ellenorzom torlesnel is
        if (palyazatEllenorzo(palyazatokColl.find(filter).first())){
            palyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");
    }

    public ArrayList<String> osszesPalyazat() {
        ArrayList<String> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    public ArrayList<String> menedzserKereso(String menedzser) {
        ArrayList<String> palyazatLista = new ArrayList<>();
            FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    private boolean palyazatEllenorzo(Palyazat keresettPalyazat) {
        return keresettPalyazat != null;
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

    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy. MMMM dd. ");
    public void setAktualisFazis(String aktualisFazis) {
        this.aktualisFazis = aktualisFazis;
    }
    @Override
    public String toString() {
        return "Pályázat címe: " + getPalyazatCim() + "\n" +
                "A pályázat állapota: " + getAktualisFazis() + "\n" +
                "Leírás: " + getLeiras() + "\n" +
                "Felhíváskód: " + getFelhivasKod() + "\n" +
                "K+F: " + getKplusF() + "\n" +
                "Önerő: " + getOnero() + "\n" +
                "Tervezett összköltség: " + getTervezettOsszkoltseg() + "\n" +
                "Igényelt támogatás: " + getIgenyeltTamogatas() + "\n" +
                "Megjegyzés: " + getMegjegyzes() + "\n" +
                "Szerződésszám: " + getSzerzodesSzam() + "\n" +
                "A pályázat kezdete: " + getKezdet().format(formatters) + "\n" + //a datumok is NullPointer Exception-t adnak
                "A pályázat vége: " + getVeg().format(formatters) + "\n" +
                "Szakmai vezető: " + getResztvevok().getSzakmaiVezeto() + "\n"+     //ha nincs megadva, akkor NullPointerException lesz
                "Projektmenedzser: " + getResztvevok().getSzakmaiVezeto() + "\n" +
                "A pályázat kezelője: " + getResztvevok().getKezelo() + "\n" +
                "Résztvevő kutatók: " + getResztvevok().getResztvevoEmberek().toString() + "\n\n"
                 ;
    }

}
