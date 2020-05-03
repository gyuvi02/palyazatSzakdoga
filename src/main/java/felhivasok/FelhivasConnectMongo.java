package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FelhivasConnectMongo {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasok = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    public void felhivasLetolto(String cim) {
        System.out.println(Objects.requireNonNull(felhivasok.find(eq("felhivasCim", cim)).first()).toString());
    }

    public void felhivasFeltolto(Felhivas ujFelhivas) {
        felhivasok.insertOne(ujFelhivas);
    }

}
