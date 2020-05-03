package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class OktatoModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatok = palyazatDB.getCollection("Oktatok", Oktato.class);

    OktatoConnectMongo modositando = new OktatoConnectMongo();

    public void oktatoUjNev(String nev, String ujNev) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("nev", ujNev);
        oktatok.updateOne(filter, ujElem);
    }

    public void oktatoUjTanszek(String nev, String ujTanszek) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("tanszek", ujTanszek);
        oktatok.updateOne(filter, ujElem);
    }

    public void oktatoUjEmail(String nev, String ujEmail) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("email", ujEmail);
        oktatok.updateOne(filter, ujElem);
    }

    public void oktatoUjHonlap(String nev, String ujHonlap) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("honlap", ujHonlap);
        oktatok.updateOne(filter, ujElem);
    }

    public void oktatoUjKutatasi(String nev, ArrayList<String> ujKutatasi) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("kutatasiTema", ujKutatasi);
        oktatok.updateOne(filter, ujElem);
    }

    public void oktatoUjPalyazati(String nev, ArrayList<String> ujPalyazati) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("palyazatiTema", ujPalyazati);
        oktatok.updateOne(filter, ujElem);
    }





}
