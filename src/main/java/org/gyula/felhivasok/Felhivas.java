package org.gyula.felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.gyula.palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class Felhivas {
    @BsonProperty(value = "felhivasCim")    //az elnevezesi szokasok ne keveredjenek
    private String felhivasCim;
    @BsonProperty(value = "felhivasKiiro")
    private String felhivasKiiro;
    @BsonProperty(value = "targymutato")
    private String targymutato;
    @BsonProperty(value = "kiPalyazhat")
    private String kiPalyazhat;
    @BsonProperty(value = "beadasiHatarido")
    private String beadasiHatarido; //nem lehet datum, mert neha pl. annyi szerepel, hogy "folyamatos"
    @BsonProperty(value = "felhivasLink")
    private String felhivasLink;
    @BsonProperty(value = "reszletesLeiras")
    private String reszletesLeiras;
    @BsonProperty(value = "kategoriak")
    private ArrayList<String> kategoriak;
    @BsonProperty(value = "lehetsegesResztvevok")
    private ArrayList<String> lehetsegesResztvevok;
    @BsonProperty(value = "torles")
    private LocalDate torles;

    public Felhivas(String felhivasCim, String felhivasKiiro, String targymutato, String kiPalyazhat,
                    String beadasiHatarido, String felhivasLink, String reszletesLeiras,
                    ArrayList<String> kategoriak, ArrayList<String> lehetsegesResztvevok, LocalDate torles ) throws InterruptedException {
        this.felhivasCim = felhivasCim;
        this.felhivasKiiro = felhivasKiiro;
        this.targymutato = targymutato;
        this.kiPalyazhat = kiPalyazhat;
        this.beadasiHatarido = beadasiHatarido;
        this.felhivasLink = felhivasLink;
        this.reszletesLeiras = reszletesLeiras;
        this.kategoriak = kategoriak;
        this.lehetsegesResztvevok = lehetsegesResztvevok;
        this.torles = torles;
    }

    public Felhivas() throws InterruptedException {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    public void felhivasFeltolto() {
        felhivasokColl.insertOne(this);
    }

    public ArrayList<Felhivas> felhivasLetolto(String cim) {
        return felhivasokColl.find((eq("felhivasCim", cim))).into(new ArrayList<>()); //ha ures tombot ad vissza, akkor nem letezik
    }

    //ellenoriznem kell, hogy a legutobbiFelhivasok collection-bol is kitoroljuk a torolt felhivast
    public long felhivasTorol(String torlendoFelhivas) throws InterruptedException {
        LegutobbiFelhivasok legutobbiFelhivasok = new LegutobbiFelhivasok().legutobbiTeljes(); //letoltjuk az aktualis dokumentkumot
        ArrayList<String> lista = legutobbiFelhivasok.legutobbiLekerdezes(); //a dokumentumbol kivesszuk a listat
        if (lista.contains(torlendoFelhivas)) {
            lista.remove(torlendoFelhivas);
            legutobbiFelhivasok.setLegutobbi(lista); //az uj listat hozzaadjuk a regi dokumentumhoz
            legutobbiFelhivasok.legutobbiListaFeltoltes(); //es visszatoltjuk a modositott dokumentumot
        }
        return felhivasokColl.deleteOne(eq("felhivasCim", torlendoFelhivas)).getDeletedCount(); //ha a visszadott ertek 0, akkor nem tortent semmi
    }

    private boolean felhivasEllenorzo(Felhivas keresettFelhivas) {
        return keresettFelhivas != null;
    }



    public Felhivas(String felhivasCim) throws InterruptedException {
        this.felhivasCim = felhivasCim;
    }

    public String getFelhivasCim() {
        return felhivasCim;
    }

    public void setFelhivasCim(String felhivasCim) {
        this.felhivasCim = felhivasCim;
    }

    public String getFelhivasKiiro() {
        return felhivasKiiro;
    }

    public void setFelhivasKiiro(String felhivasKiiro) {
        this.felhivasKiiro = felhivasKiiro;
    }

    public String getTargymutato() {
        return targymutato;
    }

    public void setTargymutato(String targymutato) {
        this.targymutato = targymutato;
    }

    public String getKiPalyazhat() {
        return kiPalyazhat;
    }

    public void setKiPalyazhat(String kiPalyazhat) {
        this.kiPalyazhat = kiPalyazhat;
    }

    public String getBeadasiHatarido() {
        return beadasiHatarido;
    }

    public void setBeadasiHatarido(String beadasiHatarido) {
        this.beadasiHatarido = beadasiHatarido;
    }

    public String getFelhivasLink() {
        return felhivasLink;
    }

    public void setFelhivasLink(String felhivasLink) {
        this.felhivasLink = felhivasLink;
    }

    public String getReszletesLeiras() {
        return reszletesLeiras;
    }

    public void setReszletesLeiras(String reszletesLeiras) {
        this.reszletesLeiras = reszletesLeiras;
    }

    public ArrayList<String> getKategoriak() {
        return kategoriak;
    }

    public void setKategoriak(ArrayList<String> kategoriak) {
        this.kategoriak = kategoriak;
    }

    public ArrayList<String> getLehetsegesResztvevok() {
        return lehetsegesResztvevok;
    }

    public void setLehetsegesResztvevok(ArrayList<String> lehetsegesResztvevok) {
        this.lehetsegesResztvevok = lehetsegesResztvevok;
    }

    public LocalDate getTorles() {
        return torles;
    }

    public void setTorles(LocalDate torles) {
        this.torles = torles;
    }

    public static String toStingHelyettFelhivas(ArrayList<Felhivas> felhivas) {
        StringBuilder str = new StringBuilder();
        for (Felhivas hivas : felhivas) {
            ArrayList<String> rendezett = new ArrayList<>(FelhivasLekerdezes.nevRendezo(hivas.lehetsegesResztvevok));
            str.append("A felhívás címe: ").append(hivas.getFelhivasCim()).append("\n\n")
                    .append("Kiíró: ").append(hivas.getFelhivasKiiro()).append("\n\n")
                    .append("Tárgymutato: ").append(hivas.getTargymutato()).append("\n\n")
                    .append("Ki pályázhat: ").append(hivas.getKiPalyazhat()).append("\n\n")
                    .append("Beadási határidő: ").append(hivas.getBeadasiHatarido()).append("\n\n")
                    .append("Link: ").append(hivas.getFelhivasLink()).append("\n\n")
                    .append("Részletes leírás: ").append(hivas.getReszletesLeiras()).append("\n\n")
                    .append("Kategóriák: ").append(String.join(", ", hivas.getKategoriak())).append("\n\n")
                    .append("Lehetséges résztvevők a karon: ").append(hivas.getLehetsegesResztvevok().isEmpty() ? "-" :
                        "\n" + String.join(", \n", rendezett)).append("\n\n")
                    .append("--------------------------------------------------------------------").append("\n\n")
            ;
        }
        return str.toString();
    }

//    @Override
//    public String toString() {      //StringBuilder? hogy szebb legyen
//        return  "A felhívás címe: " + felhivasCim + "\n" +
//                "Kiíró: " + felhivasKiiro + "\n" +
//                "Tárgymutato: " + targymutato + "\n" +
//                "Ki pályázhat: " + kiPalyazhat + "\n" +
//                "Beadási határidő: " + beadasiHatarido + "\n" +
//                "Link: " + felhivasLink + "\n" +
//                "Részletes leírás: " + reszletesLeiras + "\n\n"
//                +
//                "Kategóriák: " + String.join(", ", kategoriak) + "\n\n" +
//                "Lehetséges résztvevők a karon: " + String.join(", ", lehetsegesResztvevok) + "\n\n\n\n"
//                ;
//    }
}

