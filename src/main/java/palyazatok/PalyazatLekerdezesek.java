package palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.HashSet;

import static com.mongodb.client.model.Filters.eq;

public class PalyazatLekerdezesek {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);


//levalogatja a megadott fazisban levo palyazatok cimet
    public ArrayList<Palyazat> fazisLekerdezes(String rendezesAlapja) {
        ArrayList<Palyazat> palyazatLista = new ArrayList<Palyazat>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find(eq("aktualisFazis", rendezesAlapja));
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat);
        }
        return palyazatLista;
    }

    //ezt most nem tudom pontosan, mire akaratm hasznalni, de valamire jo lesz
    public ArrayList<Palyazat> rendezettLekerdezes(String rendezesAlapja) {
        ArrayList<Palyazat> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat);
        }
        return palyazatLista;
    }

    public ArrayList<String> menedzserKereso(String menedzser) {
        ArrayList<String> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find(eq("resztvevok.projektmenedzser", menedzser));
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    public ArrayList<String> osszesPalyazat() {
        ArrayList<String> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    public ArrayList<String> melyikEvbenKezdodott(String evszam) {
        HashSet<String> palyazatlista = new HashSet<>();
        FindIterable<Palyazat> iterable = palyazatokColl.find(eq("beadasEve", evszam));
        MongoCursor<Palyazat> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            palyazatlista.add(cursor.next().getPalyazatCim());
        }
        return new ArrayList<>(palyazatlista);
    }

}