package regi_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class RegiPalyazatModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RegiPalyazat> regiPalyazatokColl = palyazatDB.getCollection("RegiPalyazatok", RegiPalyazat.class);

    RegiConnectMongo regiModositando = new RegiConnectMongo();

    public void regiPalyazatModosito(int mezo, String cim, String ujAdat) {
        String mezoStr;
        switch (mezo) {
            case 0:
                mezoStr = "regiCim";
                break;
            case 1:
                mezoStr = "DEazonosito";
                break;
            case 2:
                mezoStr = "szerzodesSzam";
                break;
            case 3:
                mezoStr = "leiras";
                break;
            case 4:
                mezoStr = "felhivasKod";
                break;
            case 5:
                mezoStr = "megjegyzes";
                break;
            default:
                mezoStr = "regiCim";
                break;
        }
    }
    private void multiModosito(String mezo, String cim, String ujAdat) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set(mezo, ujAdat);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatKezdet(String cim, Date ujKezdet) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("kezdet", ujKezdet);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }
    private void regiPalyazatVeg(String cim, Date ujVeg) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("veg", ujVeg);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatKplusF(String cim, boolean ujKplusF) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("KplusF", ujKplusF);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatOnero(String cim, Double ujOnero) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("onero", ujOnero);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatOsszkoltseg(String cim, Double ujOsszkoltseg) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("tervezettOsszkoltseg", ujOsszkoltseg);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatIgenyelt(String cim, Double ujIgenyelt) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujIgenyelt);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }

    private void regiPalyazatResztvevok(String cim, RegiPalyazat.Resztvevok ujResztvevok) {
        Bson filter = eq("regiCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujResztvevok);
        regiPalyazatokColl.updateOne(filter, ujElem);
    }












}
