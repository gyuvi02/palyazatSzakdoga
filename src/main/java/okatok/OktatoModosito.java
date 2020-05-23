package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.setOnInsert;

public class OktatoModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);

    public void oktatoUjNev(String nev, String ujNev) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("nev", ujNev);
        oktatokColl.updateOne(filter, ujElem);
    }

    public void oktatoUjTanszek(String nev, String ujTanszek) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("tanszek", ujTanszek);
        oktatokColl.updateOne(filter, ujElem);
    }

    public void oktatoUjEmail(String nev, String ujEmail) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("email", ujEmail);
        oktatokColl.updateOne(filter, ujElem);
    }

    public void oktatoUjHonlap(String nev, String ujHonlap) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set("honlap", ujHonlap);
        oktatokColl.updateOne(filter, ujElem);
    }

    public void tombFrissito(String mezoNev, String nev, ArrayList<String> ujTomb) {
        Bson filter = eq("nev", nev);
        Bson ujElem = set(mezoNev, ujTomb);
        oktatokColl.updateOne(filter, ujElem);
    }

    //ha a boolean true, akkor hozzadunk, ha false, akkor torlunk a tombbol
    //tombben adjuk at a temakat, hogy egyszerre tobbet is tudjunk
    public void tombModosito(boolean hozzaad, String tomb, String nev, ArrayList<String > modositando) {//a tomb csak kutatasiTema vagy palyazatiTema lehet csak (legordulo menu)
        Oktato oktato = new Oktato();
        ArrayList<String> kutatasi;
        if (tomb.equals("kutatasiTema")) {
            kutatasi = oktato.oktatoLetolto(nev).getKutatasiTema();
        }else{
            kutatasi = oktato.oktatoLetolto(nev).getPalyazatiTema();
        }
        if (hozzaad) {
            kutatasi.addAll(modositando);
        } else {
            kutatasi.removeAll(modositando);
        }
        tombFrissito(tomb, nev, kutatasi);
    }







}
