package regi_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import felhivasok.Felhivas;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import static com.mongodb.client.model.Filters.eq;

public class RegiConnectMongo {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RegiPalyazat> regiPalyazatokColl = palyazatDB.getCollection("RegiPalyazatok", RegiPalyazat.class);

    public void regiPalyazatFeltolto(RegiPalyazat ujRegi) {
        regiPalyazatokColl.insertOne(ujRegi);
    }

    public void regiPalyazatLetolto(String cim) {
        RegiPalyazat keresettRegiPalyazat = regiPalyazatokColl.find((eq("regiCim", cim))).first();
        if (keresettRegiPalyazat != null) {
            System.out.println(keresettRegiPalyazat.toString());
        }else System.out.println("Nincs ilyen pályázat");
    }

    public void regiPalyazatTorol(String torlendoRegiPalyazat) {
        Bson filter = eq("regiCim", torlendoRegiPalyazat);
        if (regiPalyazatokColl.find(filter).first() != null){
            regiPalyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");
    }






}
