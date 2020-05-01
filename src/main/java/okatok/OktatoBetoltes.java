package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;

public class OktatoBetoltes {

    public void oktatoBeolvaso() {
        String keresettNev = "Dr. Szabó Gyula";
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
        MongoCollection<Document> oktatok = palyazatDB.getCollection("Oktatok");
        Document kivalasztottOktato = oktatok.find(new Document("nev", keresettNev)).first();
        Oktato oktato = new Oktato(kivalasztottOktato.getString("nev"), kivalasztottOktato.getString("tanszek"),
                new ArrayList<String>(kivalasztottOktato.get("kutatasiTema", ArrayList.class)),
                kivalasztottOktato.getString("email"),kivalasztottOktato.getString("honlap"));
        System.out.println(oktato.getKutatasiTema());



    }

}
