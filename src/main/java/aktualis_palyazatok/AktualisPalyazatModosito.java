package aktualis_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import regi_palyazatok.RegiResztvevok;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AktualisPalyazatModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public void aktualisModosito(int mezo, String cim, String ujAdat) {
        String mezoStr;
        switch (mezo) {
            case 0:
                mezoStr = "palyazatCim";
                break;
            case 1:
                mezoStr = "leiras";
                break;
            case 2:
                mezoStr = "felhivasKod";
                break;
            case 3:
                mezoStr = "megjegyzes";
                break;
            default:
                mezoStr = "palyazatCim";
                break;
        }
        multiModosito(mezoStr, cim, ujAdat);
    }
    private void multiModosito(String mezo, String cim, String ujAdat) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set(mezo, ujAdat);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }

    public void aktualisPalyazatKplusF(String cim, boolean ujKplusF) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("KplusF", ujKplusF);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }

    public void aktualisPalyazatOnero(String cim, Double ujOnero) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("onero", ujOnero);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }

    public void aktualisPalyazatOsszkoltseg(String cim, Double ujOsszkoltseg) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("tervezettOsszkoltseg", ujOsszkoltseg);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }

    public void aktualisPalyazatIgenyelt(String cim, Double ujIgenyelt) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujIgenyelt);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }

    public void aktualisPalyazatResztvevok(String cim, RegiResztvevok ujResztvevok) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("resztvevok", ujResztvevok);
        aktualisPalyazatokColl.updateOne(filter, ujElem);
    }
}
