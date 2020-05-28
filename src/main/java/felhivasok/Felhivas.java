package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.annotations.BsonProperty;
import palyazatkezelo.MongoAccess;

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

    public Felhivas(String felhivasCim, String felhivasKiiro, String targymutato, String kiPalyazhat,
                    String beadasiHatarido, String felhivasLink, String reszletesLeiras,
                    ArrayList<String> kategoriak, ArrayList<String> lehetsegesResztvevok) {
        this.felhivasCim = felhivasCim;
        this.felhivasKiiro = felhivasKiiro;
        this.targymutato = targymutato;
        this.kiPalyazhat = kiPalyazhat;
        this.beadasiHatarido = beadasiHatarido;
        this.felhivasLink = felhivasLink;
        this.reszletesLeiras = reszletesLeiras;
        this.kategoriak = kategoriak;
        this.lehetsegesResztvevok = lehetsegesResztvevok;
    }

    public Felhivas() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    public void felhivasFeltolto() {
        felhivasokColl.insertOne(this);
    }

    public ArrayList<Felhivas> felhivasLetolto(String cim) {
        return felhivasokColl.find((eq("felhivasCim", cim))).into(new ArrayList<>()); //ha ures tombot ad vissza, akkor nem letezik
    }

    public long felhivasTorol(String torlendoFelhivas) {
        return felhivasokColl.deleteOne(eq("felhivasCim", torlendoFelhivas)).getDeletedCount(); //ha a visszadott ertek 0, akkor nem tortent semmi
    }

    private boolean felhivasEllenorzo(Felhivas keresettFelhivas) {
        if (keresettFelhivas != null) {
            return true;
        }
        return false;
    }



    public Felhivas(String felhivasCim) {
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

    @Override
    public String toString() {
        return  "A felhívás címe: " + felhivasCim + "\n" +
                "Kiíró: " + felhivasKiiro + "\n" +
                "Tárgymutato: " + targymutato + "\n" +
                "Ki pályázhat: " + kiPalyazhat + "\n" +
                "Beadási határidő: " + beadasiHatarido + "\n" +
                "Link: " + felhivasLink + "\n" +
                "Részletes leírás: " + reszletesLeiras + "\n" +
                "Kategóriák: " + kategoriak + "\n" +
                "Lehetséges résztvevők: " + lehetsegesResztvevok + "\n";
    }
}

