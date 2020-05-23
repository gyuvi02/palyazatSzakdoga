package palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import felhivasok.Felhivas;
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
    //barmilyen resztvevo szerepben keres
    public ArrayList<String> resztvevoKereso(String pozicio, String nev) {
        ArrayList<String> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find(eq("resztvevok." + pozicio, nev));
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
    //levalogatja a K+F palyazatokat
    public ArrayList<String> kPlusFFelhivasok() {
        ArrayList<String> felhivasLista = new ArrayList<>();
        FindIterable<Palyazat> iterable = palyazatokColl.find(eq("KplusF", true));
        MongoCursor<Palyazat> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            felhivasLista.add(cursor.next().getPalyazatCim());
        }
        return felhivasLista;
    }
    //levalogatja azokat a p;lyazatokat, ahol nem kellett onero
    public ArrayList<String> oneroNelkul() {
        ArrayList<String> felhivasLista = new ArrayList<>();
        FindIterable<Palyazat> iterable = palyazatokColl.find(eq("onero", 0));
        MongoCursor<Palyazat> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            felhivasLista.add(cursor.next().getPalyazatCim());
        }
        return felhivasLista;
    }




}