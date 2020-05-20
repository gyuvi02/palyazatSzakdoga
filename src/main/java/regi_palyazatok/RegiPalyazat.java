package regi_palyazatok;

import aktualis_palyazatok.PalyazatiResztvevok;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import palyazatkezelo.Palyazat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.mongodb.client.model.Filters.eq;

public class RegiPalyazat extends Palyazat {
    private String DEazonosito;
    private String szerzodesSzam;
    private LocalDate kezdet;
    private LocalDate veg;

    public RegiPalyazat(String palyazatCim, String leiras, String felhivasKod, String beadasEve, Boolean kplusF,
                        Double onero, Double tervezettOsszkoltseg, Double igenyeltTamogatas, String megjegyzes,
                        PalyazatiResztvevok resztvevok, String aktualisFazis, String DEazonosito, String szerzodesSzam,
                        LocalDate kezdet, LocalDate veg) {
        super(palyazatCim, leiras, felhivasKod, beadasEve, kplusF, onero, tervezettOsszkoltseg, igenyeltTamogatas,
                megjegyzes, resztvevok, aktualisFazis);
        this.DEazonosito = DEazonosito;
        this.szerzodesSzam = szerzodesSzam;
        this.kezdet = kezdet;
        this.veg = veg;
    }

    public RegiPalyazat(String palyazatCim) {
        super(palyazatCim);
    }

    public RegiPalyazat() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RegiPalyazat> regiPalyazatokColl = palyazatDB.getCollection("RegiPalyazatok", RegiPalyazat.class);

    public void regiPalyazatFeltolto() {
        Bson filter = eq("palyazatCim", this.getPalyazatCim()); //csak a cimet ellenorzom, nem lehet 2 egyforma cimu palyazat
        if (regiPalyazatEllenorzo(regiPalyazatokColl.find(filter).first())) {
            System.out.println("Mar van ilyen palyazat");
        } else {
            regiPalyazatokColl.insertOne(this);
        }
    }

    public void regiPalyazatLetolto(String cim) {
        RegiPalyazat keresettRegiPalyazat = regiPalyazatokColl.find((eq("palyazatCim", cim))).first();
        if (regiPalyazatEllenorzo(keresettRegiPalyazat)) {
            try {
                System.out.println(keresettRegiPalyazat.toString());
            } catch (NullPointerException e) {
                System.out.println(e.fillInStackTrace());
            }
        }else System.out.println("Nincs ilyen pályázat");
    }

    public void regiPalyazatTorlo(String torlendoRegiPalyazat) {
        Bson filter = eq("palyazatCim", torlendoRegiPalyazat);
        if (regiPalyazatEllenorzo(regiPalyazatokColl.find(filter).first())){
            regiPalyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");
    }

    private boolean regiPalyazatEllenorzo(RegiPalyazat keresettRegiPalyazat) {
        System.out.println(keresettRegiPalyazat != null);
        return keresettRegiPalyazat != null;
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
    @Override
    public String toString() {
        return super.toString() + "Egyetemi azoosító: " + getDEazonosito() + "\n" +
                "Szerződésszám: " + getSzerzodesSzam() + "\n" +
                "A pályázat kezdete: " + getKezdet().format(formatters) + "\n" + //a datumok is NullPointer Exception-t adnak
                "A pályázat vége: " + getVeg().format(formatters) + "\n";
    }
}
