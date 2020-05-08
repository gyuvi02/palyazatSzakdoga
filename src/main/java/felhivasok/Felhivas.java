package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import okatok.Oktato;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import regi_palyazatok.RegiPalyazat;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Felhivas {
    @BsonProperty(value = "felhivasCim")    //az elnevezesi szokasok ne keveredjenek
    String felhivasCim;
    @BsonProperty(value = "felhivasKiiro")
    String felhivasKiiro;
    @BsonProperty(value = "targymutato")
    String targymutato;
    @BsonProperty(value = "kiPalyazhat")
    String kiPalyazhat;
    @BsonProperty(value = "beadasiHatarido")
    String beadasiHatarido; //nem lehet datum, mert neha pl. annyi szerepel, hogy "folyamatos"
    @BsonProperty(value = "felhivasLink")
    String felhivasLink;
    @BsonProperty(value = "reszletesLeiras")
    String reszletesLeiras;
    @BsonProperty(value = "kategoriak")
    ArrayList<String> kategoriak;
    @BsonProperty(value = "lehetsegesResztvevok")
    ArrayList<String> lehetsegesResztvevok;

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

    public void felhivasLetolto(String cim) {
        Felhivas keresettFelhivas = felhivasokColl.find((eq("felhivasCim", cim))).first();
        if (felhivasEllenorzo(keresettFelhivas)) {
            System.out.println(keresettFelhivas.toString());
        }else System.out.println("Nincs ilyen felhivas");
    }

    public void felhivasTorol(String torlendoFelhivas) {
        Bson filter = eq("felhivasCim", torlendoFelhivas);
        if (felhivasEllenorzo(felhivasokColl.find(filter).first())){
            felhivasokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen felhívás");
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

