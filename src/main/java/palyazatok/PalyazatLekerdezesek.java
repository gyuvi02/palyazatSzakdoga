package palyazatok;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import felhivasok.Felhivas;
import palyazatkezelo.MongoAccess;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static com.mongodb.client.model.Filters.*;

public class PalyazatLekerdezesek {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);


//levalogatja a megadott fazisban levo palyazatok cimet
    public ArrayList<Palyazat> fazisLekerdezes(String fazis) {
        return palyazatokColl.find(eq("aktualisFazis", fazis)).into(new ArrayList<>());
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
    //minden resztvevo szerepben kulon kereshetunk vele, parameterkent adjuk at, ha barmelyikben mezoben keresunk, akkor "osszes"
    public ArrayList<Palyazat> resztvevoKereso(String pozicio, String nev) {
        if (pozicio.equals("összes")) {
            return palyazatokColl.find(or(eq("resztvevok.kezelo", nev), eq("resztvevok.projektmenedzser", nev),
                    eq("resztvevok.szakmaiVezeto", nev), eq("resztvevok.resztvevoEmberek", nev)))
                    .into(new ArrayList<>());
        }
        return palyazatokColl.find(eq("resztvevok." + pozicio, nev)).into(new ArrayList<>());
    }

    //viszaadja az osszes palyazatot
    public ArrayList<Palyazat> osszesPalyazat() {
        ArrayList<String> palyazatLista = new ArrayList<>();
        return palyazatokColl.find().into(new ArrayList<>());
    }

    //levalogatja azokat a palyazatokat, amelyek az adott evben kezdodtek
    public ArrayList<Palyazat> melyikEvbenKezdodott(String evszam) {
        return palyazatokColl.find(eq("beadasEve", evszam)).into(new ArrayList<>());
    }

    //levalogatja a K+F palyazatokat
    public ArrayList<Palyazat> kPlusFFelhivasok() {
        return palyazatokColl.find(eq("KplusF", true)).into(new ArrayList<>());
    }

    //levalogatja azokat a palyazatokat, ahol nem kellett onero
    public ArrayList<Palyazat> oneroNelkul() {
        return palyazatokColl.find(eq("onero", 0)).into(new ArrayList<>());
    }

    public ArrayList<String> kezdoEvPeriodus(LocalDate min, LocalDate max) {
        ArrayList<String> felhivasLista = new ArrayList<>();
        FindIterable<Palyazat> iterable = palyazatokColl.find(gte("kezdet", min));
        MongoCursor<Palyazat> cursor = iterable.iterator();
        while (cursor.hasNext()) {

            felhivasLista.add(cursor.next().getPalyazatCim());
        }
        return felhivasLista;

    }




}