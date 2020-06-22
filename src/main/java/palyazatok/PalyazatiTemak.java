package palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.Arrays;

public class PalyazatiTemak {
    private ArrayList<String> temak;

    public PalyazatiTemak(ArrayList<String> temak) {
        this.temak = temak;
    }

    public PalyazatiTemak() {
    }
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<PalyazatiTemak> temaColl = palyazatDB.getCollection("Temak", PalyazatiTemak.class);

    public void temafeltolt() {
        temaColl.insertOne(this);
    }

    public ArrayList<String> temaLetolt() {
        return temaColl.find().sort(new Document("_id", -1)).first().temak;
    }


    public ArrayList<String> getTemak() {
        return temak;
    }

    public void setTemak(ArrayList<String> temak) {
        this.temak = temak;
    }
}
