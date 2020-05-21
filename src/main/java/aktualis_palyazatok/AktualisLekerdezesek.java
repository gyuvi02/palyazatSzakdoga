package aktualis_palyazatok;

import com.github.davidmoten.guavamini.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import felhivasok.Felhivas;
import palyazatkezelo.MongoAccess;
import palyazatkezelo.Palyazat;

import java.util.ArrayList;
import java.util.HashSet;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class AktualisLekerdezesek{

    public AktualisLekerdezesek() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public ArrayList<Palyazat> rendezettLekerdezes(String rendezesAlapja) { //lehetne ugy, hogy valaszthassunk, az aktualis, a regi vagy az osszes palyazaton belul
        ArrayList<Palyazat> palyazatLista = new ArrayList<Palyazat>();
        FindIterable<AktualisPalyazat> iterPalyazat = aktualisPalyazatokColl.find();
        for (AktualisPalyazat aktualisPalyazat : iterPalyazat) {
            palyazatLista.add(aktualisPalyazat);
        }
        return palyazatLista;
    }

    public ArrayList<String> melyikEvbenKezdodott(String evszam) {
        HashSet<String> palyazatlista = new HashSet<>();
        FindIterable<AktualisPalyazat> iterable = aktualisPalyazatokColl.find(eq("beadasEve", evszam));
        MongoCursor<AktualisPalyazat> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            palyazatlista.add(cursor.next().getPalyazatCim());
        }
        return new ArrayList<>(palyazatlista);
    }


//    @Override
//    public ArrayList<AktualisPalyazat> rendezettLekerdezes(String rendezesAlapja) {
//        return super.rendezettLekerdezes(rendezesAlapja);
//    }

}
