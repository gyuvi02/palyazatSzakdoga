package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import static com.mongodb.client.model.Filters.eq;

public class FelhivasConnectMongo {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    public void felhivasFeltolto(Felhivas ujFelhivas) {
        felhivasokColl.insertOne(ujFelhivas);
    }

    public void felhivasLetolto(String cim) {
        Felhivas keresettFelhivas = felhivasokColl.find((eq("felhivasCim", cim))).first();
        if (keresettFelhivas != null) {
            System.out.println(keresettFelhivas.toString());
        }else System.out.println("Nincs ilyen felhivas");
    }

    public void felhivasTorol(String torlendoFelhivas) {
        Bson filter = eq("felhivasCim", torlendoFelhivas);
        if (felhivasokColl.find(filter).first() != null){
            felhivasokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen felhívás");
    }

}
