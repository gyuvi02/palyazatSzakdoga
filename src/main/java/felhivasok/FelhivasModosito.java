package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import okatok.Oktato;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class FelhivasModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> felhivasok = palyazatDB.getCollection("Felhivasok", Oktato.class);

    public void felhivasUjCim(String cim, String ujCim) {
        Bson filter = eq("felhivasCim", cim);
        Bson ujElem = set("felhivasCim", ujCim);
        felhivasok.updateOne(filter, ujElem);
    }

    public void felhivasUjLink(String cim, String ujLink) {
        Bson filter = eq("felhivasCim", cim);
        Bson ujElem = set("felhivasLink", ujLink);
        felhivasok.updateOne(filter, ujElem);
    }

    public void felhivasUjKategoriak(String cim, ArrayList<String> ujKategoriak) {
        Bson filter = eq("felhivasCim", cim);
        Bson ujElem = set("kategoriak", ujKategoriak);
        felhivasok.updateOne(filter, ujElem);
    }

    public void felhivasUjResztvevok(String cim, ArrayList<Oktato> ujResztvevok) {
        Bson filter = eq("felhivasCim", cim);
        Bson ujElem = set("lehetsegesResztvevok", ujResztvevok);
        felhivasok.updateOne(filter, ujElem);
    }

    public void felhivasUjTorles(String cim, LocalDate ujDatum) {
        Bson filter = eq("felhivasCim", cim);
        Bson ujElem = set("torles", ujDatum);
        felhivasok.updateOne(filter, ujElem);
    }
}
