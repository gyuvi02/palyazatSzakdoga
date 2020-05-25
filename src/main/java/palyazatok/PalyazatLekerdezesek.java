package palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

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

    //min es max - a ket datum kozott kezdodott palyazatokat adja vissza
    public ArrayList<Palyazat> kezdoEvPeriodus(LocalDate min, LocalDate max) {
        return palyazatokColl.aggregate(Arrays.asList(
                Aggregates.match(Filters.gte("kezdet", min)),
                Aggregates.match(Filters.lte("kezdet", max)))).into(new ArrayList<>());

    }

    //min es max - a ket datum kozott kezdodott palyazatokat adja vissza
    public ArrayList<Palyazat> vegeEvPeriodus(LocalDate min, LocalDate max) {
        return palyazatokColl.aggregate(Arrays.asList(
                Aggregates.match(Filters.gte("veg", min)),
                Aggregates.match(Filters.lte("veg", max))))
                .into(new ArrayList<>());

    }

    //3 adatbol lehet valasztani (onero, igenyeltTamogatas, tervezettOsszkoltseg - legordulo menu)
    //a ket megadott osszeg koze eso palyazatokat adja vissza
    public ArrayList<Palyazat> osszegHatarok(String kategoria, double min, double max) {
        return palyazatokColl.aggregate(Arrays.asList(
                Aggregates.match(Filters.gte(kategoria, min)),
                Aggregates.match(Filters.lte(kategoria, max))))
                .into(new ArrayList<>());
    }

    //kulcsszavak alapjan keres a palyazat cimeben, a leirasban es a megjegyzesekben
    public ArrayList<Palyazat> kulcsszavakPalyazat(String kulcsszo) {
        return palyazatokColl.find(Filters.text(kulcsszo, new TextSearchOptions().language("hu"))).into(new ArrayList<>());
    }

}

