package palyazatok;

import palyazatkezelo.PalyazatiResztvevok;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class PalyazatModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);

    public void adatModosito(int mezo, String cim, String ujAdat) {
        String mezoStr = switch (mezo) {
            case 0 -> "palyazatCim";
            case 1 -> "aktualisFazis";
            case 2 -> "leiras";
            case 3 -> "felhivasKod";
            case 4 -> "beadasEve";
            case 5 -> "megjegyzes";
            case 6 -> "DEazonosito";
            case 7 -> "szerzodesSzam";

            default -> "regiCim";
        };
        multiModosito(mezoStr, cim, ujAdat);
    }
    private void multiModosito(String mezo, String cim, String ujAdat) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set(mezo, ujAdat);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void kezdetModosito(String cim, Date ujKezdet) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("kezdet", ujKezdet);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void vegModosito(String cim, Date ujVeg) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("veg", ujVeg);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void kplusFModosito(String cim, boolean ujKplusF) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("KplusF", ujKplusF);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void oneroModosito(String cim, Double ujOnero) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("onero", ujOnero);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void osszkoltsegModosito(String cim, Double ujOsszkoltseg) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("tervezettOsszkoltseg", ujOsszkoltseg);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void igenyeltOsszegModosito(String cim, Double ujIgenyelt) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujIgenyelt);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void regiPalyazatResztvevok(String cim, PalyazatiResztvevok ujResztvevok) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("resztvevok", ujResztvevok);
        palyazatokColl.updateOne(filter, ujElem);
    }




}
