package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import static com.mongodb.client.model.Filters.eq;

public class FelhivasConnectMongo {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasok = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    public void felhivasFeltolto(Felhivas ujFelhivas) {
        felhivasok.insertOne(ujFelhivas);
    }

    public void felhivasLetolto(String cim) {
        Felhivas keresettFelhivas = felhivasok.find((eq("felhivasCim", cim))).first();
        if (keresettFelhivas != null) {
            System.out.println(keresettFelhivas.toString());
        }else System.out.println("Nincs ilyen felhivas");

    }

    public void felhivasTorol(String torlendoFelhivas) {
        Bson filter = eq("felhivasCim", torlendoFelhivas);
        if (felhivasok.find(filter).first() != null){
            felhivasok.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen felhívás");
    }


}
