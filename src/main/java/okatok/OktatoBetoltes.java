package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.json.JsonWriterSettings;
import palyazatkezelo.MongoAccess;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class OktatoBetoltes {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatok = palyazatDB.getCollection("Oktatok", Oktato.class);

    public void oktatoFeltolto() {
        OktatoRogzito oktatoRogzito = new OktatoRogzito();
        oktatok.insertOne(oktatoRogzito.oktatoOsszeallito());
    }

    public void oktatoBeolvaso() {
        String kivalasztottoktato = "Valaki";
        Oktato keresettOktato = oktatok.find(eq("nev", kivalasztottoktato)).first();
        System.out.println(keresettOktato.getKutatasiTema());




    }

}
