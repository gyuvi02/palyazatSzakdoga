package okatok;

import com.mongodb.Function;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import palyazatok.Palyazat;
import palyazatok.PalyazatiResztvevok;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.*;

public class OktatoLekerdezes{

    public OktatoLekerdezes() {
    }

    static MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    static MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);

    //A kar összes oktatójának vagy egy tanszékhez tartozó oktatók lekérdezése – ennek igazából nincs túl sok gyakorlati jelentősége,
    //de mas metodusok felhasznalhatjak
    public ArrayList<String> oktatoNevsor(String tanszek) {//meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        if (tanszek.equals("összes")) {
            return nevRendezo(oktatokColl.find().projection((fields(include("nev"), //a projection hasznalata nem lenne feltetlenul szukseges, csak kiserletkent szerepel itt, hatha gyorsabb lesz a lekerdezes
                    excludeId()))).map(Oktato::getNev).into(new ArrayList<>()));
        }
        return nevRendezo(oktatokColl.find(eq("tanszek", tanszek)).projection((fields(include("nev"),
                excludeId()))).map(Oktato::getNev).into(new ArrayList<>()));
    }

    //Ha nem csak a nevekre van szukseg, hanem a teljes dokumentumra
    public ArrayList<Oktato> oktatoTeljesDok(String tanszek) {//meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        if (tanszek.equals("Minden tanszék")) {
            return oktatokColl.find().into(new ArrayList<>());
        }
        return oktatokColl.find(eq("tanszek", tanszek)).into(new ArrayList<>());
    }

    //A kar vagy egy tanszék összes kutatási témájának kiíratása (mindegyik csak egyszer szerepeljen)
    //Az atadottTomb tartalmazza, hogy kutatasi vagy palyazati temakban keresunk
    public ArrayList<String> kutatasiTemak(String tanszek, String atadottTomb) { //meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        Function<Oktato, ArrayList<String>> valami;
        if (atadottTomb.equals("kutatas")) {
            valami = Oktato::getKutatasiTema;
        } else {
            valami = Oktato::getPalyazatiTema;
        }
        ArrayList<ArrayList<String>> kutatasokTomb;
        HashSet<String> kutTemak = new HashSet<>();
        if (tanszek.equals("Minden tanszék")) {
            kutatasokTomb = oktatokColl.find()
                    .map(valami).into(new ArrayList<>());
        } else {
            kutatasokTomb = oktatokColl.find(eq("tanszek", tanszek))
                    .map(valami).into(new ArrayList<>());
        }
        for (ArrayList<String> tombok : kutatasokTomb) {
            kutTemak.addAll(tombok);
        }
        ArrayList<String> rendezettTemak = new ArrayList<String>(kutTemak); //hogy rendezett legyen, kenytelen vagyok listaba attenni
        Collections.sort(rendezettTemak);
        return rendezettTemak;
    }

    //Az oktatók pályázati témái alapján leválogatva – ezekből nincs túl sok, legördülő menüvel megoldható
    public static ArrayList<String> palyazatiTemaKereso(String tema) {
        return oktatokColl.find(eq("palyazatiTema", tema)).map(Oktato::getNev).into(new ArrayList<>());
    }

    //feleslegesen bonyolitja, ha a palyazati kategoriakban is akarunk keresni, nincs gyakorlati jelentosege
    //helyette hasznaljuk a PalyazatLekerdezes osztalyban az oktatoAktivitasCimek metodust
////    Az egyes oktatók pályázati aktivitása – hány pályázatban vettek részt, mindegy, hogy milyen szerepben
////    4 katagoriabol valaszthatunk: "összes", "aktuális" (elkezdett es beadott), "regi" (lezart es elfogadott), "sikertelen" (elutasitott)
//    public ArrayList<Palyazat> oktatoiAktivitas(String aktivOktato, String holKeressen) {
//        ArrayList<Palyazat> aktivitas = new ArrayList<>();
//        ArrayList<Palyazat> palyazatLista = new ArrayList<>();
//        if (holKeressen.equals("aktualis") || holKeressen.equals("összes")) {
//            palyazatLista.addAll(palyazatokColl.find(or(eq("aktualisFazis", "elkezdett"), //a palyazatListaba csak azokat a palyazatokat gyujtjuk ossze, amelyek megfelelnek a feltetelnek
//                    (eq("aktualisFazis", "beadott")))).into(new ArrayList<>()));
//        }
//        if (holKeressen.equals("regi") || holKeressen.equals("összes")) {
//            palyazatLista.addAll(palyazatokColl.find(or(eq("aktualisFazis", "lezart"),
//                    (eq("aktualisFazis", "elfogadott")))).into(new ArrayList<>()));
//        }
//        if (holKeressen.equals("aktuális") || holKeressen.equals("összes")) {
//            palyazatLista.addAll(palyazatokColl.find(eq("aktualisFazis", "elutasitott")).into(new ArrayList<>()));
//        }
//        for (Palyazat palyazat : palyazatLista) {
//            if (resztvevoHash(palyazat).contains(aktivOktato)) { //ha a resztvevok kozott szerepel a keresett oktato, akkor hozzaadjuk
//                aktivitas.add(palyazat);
//            }
//        }
//        return aktivitas;
//    }


//     Erre a valosagban nincs szukseg
//    //Az egyes oktatók pályázati aktivitása – hány pályázatban vettek részt, feladat szerint keresve
//    public ArrayList<Palyazat> oktatoiAktivitasSzerepek(String aktivOktato, String szerep) {
//        if (szerep.equals("barmelyik")) {
//            return palyazatokColl.find(or(eq("resztvevok.kezelo", aktivOktato),
//                    (eq("resztvevok.projektmenedzser", aktivOktato)),
//                    (eq("resztvevok.szakmaiVezeto", aktivOktato)),
//                    (eq("resztvevok.resztvevoEmberek", aktivOktato))))
//                    .into(new ArrayList<>());
//        } else {
//            return palyazatokColl.find(eq("resztvevok." + szerep, aktivOktato)).into(new ArrayList<>());
//        }
//    }


    //felesleges a palyazati kategoriakat szetvalasztani, ezt helyettesiti a PalyazatLekerdezes osztaly tanszekiAktivitasCimek metodusa
//    //tanszeki szinten osszegzi az egyes oktatok aktivitasat
//    public ArrayList<String> tanszekiAktivitas(String tanszek, String holKeressen) {
//        //elso korben a tanszek oktatoit kerdezzuk le a egy tombbe, ezutan hasznaljuk az oktatoAktivitasCimek metodust minden neven
//        oktatoNevsor(tanszek);
//            HashSet<String> erintettPalyazatok = new HashSet<>();
//        for (Oktato oktato : oktatokColl.find(eq("tanszek", tanszek)).into(new ArrayList<>())) {
//            ArrayList<Palyazat> oktatoPalyazatai = oktatoiAktivitas(oktato.getNev(), holKeressen); //az oktatoiAktivitast nem hasznalom masra, meg lehetne irni egyszerubben
//            for (Palyazat palyazat : oktatoPalyazatai) {
//                erintettPalyazatok.add(palyazat.getPalyazatCim());
//            }
//        }
//        ArrayList<String> rendezettPalyazatok = new ArrayList<String>(erintettPalyazatok); //hogy rendezett legyen, kenytelen vagyok listaba attenni
//        Collections.sort(rendezettPalyazatok);
//        return rendezettPalyazatok;
//    }


    private ArrayList<String> nevRendezo(ArrayList<String> lista) {
        lista.sort(Comparator.comparing(String::trim)); //nev alapjan rendezve kuldi vissza
        return lista;
    }
}
