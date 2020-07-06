package palyazatok;

import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import okatok.OktatoLekerdezes;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

    public ArrayList<String> resztvevoKeresoCim(String pozicio, String nev) {
        if (pozicio.equals("összes")) {
            return palyazatokColl.find(or(eq("resztvevok.kezelo", nev), eq("resztvevok.projektmenedzser", nev),
                    eq("resztvevok.szakmaiVezeto", nev), eq("resztvevok.resztvevoEmberek", nev))).map(Palyazat::getPalyazatCim)
                    .into(new ArrayList<>());
        }
        return palyazatokColl.find(eq("resztvevok." + pozicio, nev)).map(Palyazat::getPalyazatCim) .into(new ArrayList<>());
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

    //min es max - a ket datum kozott befejezodott palyazatokat adja vissza
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

    //megsem akarom szetvalasztani a kategoriakat, akkor eleg ennyi a resztvevok megkeresesehez
    public ArrayList<String> oktatoAktivitasCimek(String aktivOktato) {
        return palyazatokColl.find(or(eq("resztvevok.kezelo", aktivOktato),
                (eq("resztvevok.projektmenedzser", aktivOktato)),
                (eq("resztvevok.szakmaiVezeto", aktivOktato)),
                (eq("resztvevok.resztvevoEmberek", aktivOktato))))
                .map(Palyazat::getPalyazatCim).into(new ArrayList<>());
    }

    //elso korben az oktatoLekerdezes segitsegevel a tanszek oktatoit, azutan ezen a listan hasznaljuk az oktatoAktivitasCimek metodust
    public ArrayList<String> tanszekiAktivitasCimek(String tanszek) {
        HashSet<String> palyazatok = new HashSet<>();
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        for (String oktato : oktatoLekerdezes.oktatoNevsor(tanszek)) {
            palyazatok.addAll(oktatoAktivitasCimek(oktato));
        }
        ArrayList<String> rendezettPalyazatok = new ArrayList<>(palyazatok);
        return nevRendezo(rendezettPalyazatok);
    }

    //ezzel kerdezzuk le egy palyazat osszes resztvevojet
    public ArrayList<String> resztvevoHash(String palyazat) {
        PalyazatiResztvevok palyazatiResztvevok = palyazatokColl.find(eq("palyazatCim", palyazat))
                .map(Palyazat::getResztvevok).first();
        HashSet<String> resztvevok = new HashSet<>();
        resztvevok.add(palyazatiResztvevok.getKezelo());
        resztvevok.add(palyazatiResztvevok.getProjektmenedzser());
        resztvevok.add(palyazatiResztvevok.getSzakmaiVezeto());
        resztvevok.addAll(palyazatiResztvevok.getResztvevoEmberek());
        ArrayList<String> rendezettNevek = new ArrayList<>(resztvevok);
        return nevRendezo(rendezettNevek);
    }

    private ArrayList<String> nevRendezo(ArrayList<String> lista) {
        lista.sort(Comparator.comparing(String::trim)); //nev alapjan rendezve kuldi vissza
        return lista;
    }



}

