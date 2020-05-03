package okatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import static com.mongodb.client.model.Filters.eq;

public class OktatoConnectMongo {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatok = palyazatDB.getCollection("Oktatok", Oktato.class);

    public void oktatoFeltolto(Oktato ujOktato) {
        if (oktatoEmailEllenorzes(ujOktato.getEmail())) {
            oktatok.insertOne(ujOktato);
        }else
            System.out.println("Ezzel az email cimmel mar regisztráltal oktatót");
    }

    public Oktato oktatoLetolto(String oktato) {
        Oktato keresettOktato = oktatok.find(eq("nev", oktato)).first();
        if (keresettOktato == null){
            System.out.println("Nincs ilyen oktató");
            return null;
        }
        else
            return keresettOktato;
    }

    public void oktatoTorol(String torlendoOktato) {
        Bson filter = eq("nev", torlendoOktato);
        if (oktatok.find(filter).first() != null){
            System.out.println(oktatok.deleteOne(filter));
        }
        else System.out.println("Nincs ilyen oktató");
    }

    private boolean oktatoEmailEllenorzes(String email) { //csak ezt ellenorzom
        FindIterable<Oktato> iterable = oktatok.find();
        for (Oktato oktato : iterable) {
            if (oktato.email.toString().equals(email)) {
                return false;
            }
        }
        return true;
    }


}
