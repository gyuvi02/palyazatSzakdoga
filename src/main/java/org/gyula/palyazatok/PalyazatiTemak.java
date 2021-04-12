package org.gyula.palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.gyula.palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.Objects;

public class PalyazatiTemak {
    private ArrayList<String> temak;

    public PalyazatiTemak(ArrayList<String> temak) throws InterruptedException {
        this.temak = temak;
    }

    public PalyazatiTemak() throws InterruptedException {
    }
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<PalyazatiTemak> temaColl = palyazatDB.getCollection("Temak", PalyazatiTemak.class);
    MongoCollection<PalyazatiTemak> pafiTemaColl = palyazatDB.getCollection("PafiTemak", PalyazatiTemak.class);

    public void temafeltolt() {
        temaColl.insertOne(this);
    }

//    public void pafiTemafeltolt() {
//        pafiTemaColl.insertOne(this);
//    }

    //itt toltjuk le az aktualis listat azokrol a palyazati temakrol, amelyek a kart erdeklik
    public ArrayList<String> temaLetolt() {
//        return Objects.requireNonNull(temaColl.find().sort(new Document("_id", -1)).first()).temak;
        return temaColl.find().sort(new Document("_id", -1)).first().temak;
    }

    //ezzel erjuk el az osszes lehetseges palyazati temat, amit a pafi.hu hasznal
    public ArrayList<String> pafiTemaLetolt() {
        return Objects.requireNonNull(pafiTemaColl.find().sort(new Document("_id", -1)).first()).temak;
//        return pafiTemaColl.find().sort(new Document("_id", -1)).first().temak;
    }

    public ArrayList<String> getTemak() {
        return temak;
    }

    public void setTemak(ArrayList<String> temak) {
        this.temak = temak;
    }
}
