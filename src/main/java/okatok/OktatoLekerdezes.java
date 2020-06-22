package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import palyazatok.Palyazat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

public class OktatoLekerdezes{

    public OktatoLekerdezes() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);

    //A kar összes oktatójának vagy egy tanszékhez tartozó oktatók lekérdezése – ennek igazából nincs túl sok gyakorlati jelentősége,
    //de mas metodusok felhasznalhatjak
    public ArrayList<Oktato> oktatoListak(String tanszek) {//meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        if (tanszek.equals("összes")) {
            return nevRendezo(oktatokColl.find().into(new ArrayList<>()));
        }
        return nevRendezo(oktatokColl.find(eq("tanszek", tanszek)).into(new ArrayList<>()));
    }

    public ArrayList<String> oktatoNevsor(String tanszek) {
        ArrayList<String> nevsor = new ArrayList<>();
        for (Oktato oktato : oktatoListak(tanszek)) {
            nevsor.add(oktato.getNev());
        }
        return nevsor;
    }

    public ArrayList<String> oktatoNevek(String tanszek) {
        ArrayList<String> oktatok = new ArrayList<>();
        for (Oktato oktato : oktatoListak(tanszek)) {
            oktatok.add(oktato.getNev());
        }
        return oktatok;
    }

    //Egyes oktatók adatlapjának lekérése
    public ArrayList<Oktato> oktatoKereso(String oktato) {
        return oktatokColl.find(eq("nev", oktato)).into(new ArrayList<>());
    }

    //A kar vagy egy tanszék összes kutatási témájának kiíratása (mindegyik csak egyszer szerepeljen)
    public ArrayList<String> kutatasiTemak(String tanszek) { //meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        HashSet<String> kutTemak = new HashSet<>();
        for (Oktato oktato : oktatoListak(tanszek)) {
            kutTemak.addAll(oktato.getKutatasiTema());  //a HasSetbe kiolvassuk a tombok osszes elemet
        }
        ArrayList<String> rendezettTemak = new ArrayList<String>(kutTemak); //hogy rendezett legyen, kenytelen vagyok listaba attenni
        Collections.sort(rendezettTemak);
        return rendezettTemak;
    }

    //Kutatási témák alapján keresés az oktatók között
    public ArrayList<Oktato> kutatasiTemaKereso(String tema) { //a tomb csak kutatasiTema vagy palyazatiTema lehet
        return oktatokColl.find(eq("kutatasiTema", tema)).into(new ArrayList<>());
    }

    //Az oktatók pályázati témái alapján – ezekből nincs túl sok, legördülő menüvel megoldható
    public ArrayList<Oktato> palyazatiTemaKereso(String tema) {
        return oktatokColl.find(eq("palyazatiTema", tema)).into(new ArrayList<>());
    }

    //Az egyes oktatók pályázati aktivitása – hány pályázatban vettek részt, mindegy, hogy milyen szerepben
    //4 katagoriabol valaszthatunk: "összes", "aktuális" (elkezdett es beadott), "regi" (lezart es elfogadott), "sikertelen" (elutasitott)
    public ArrayList<Palyazat> oktatoiAktivitas(String aktivOktato, String holKeressen) {
        ArrayList<Palyazat> aktivitas = new ArrayList<>();
        ArrayList<Palyazat> palyazatLista = new ArrayList<>();
        if (holKeressen.equals("aktualis") || holKeressen.equals("összes")) {
            palyazatLista.addAll(palyazatokColl.find(or(eq("aktualisFazis", "elkezdett"), //a palyazatListaba csak azokat a palyazatokat gyujtjuk ossze, amelyek megfelelnek a feltetelnek
                    (eq("aktualisFazis", "beadott")))).into(new ArrayList<>()));
        }
        if (holKeressen.equals("regi") || holKeressen.equals("összes")) {
            palyazatLista.addAll(palyazatokColl.find(or(eq("aktualisFazis", "lezart"),
                    (eq("aktualisFazis", "elfogadott")))).into(new ArrayList<>()));
        }
        if (holKeressen.equals("aktuális") || holKeressen.equals("összes")) {
            palyazatLista.addAll(palyazatokColl.find(eq("aktualisFazis", "elutasitott")).into(new ArrayList<>()));
        }
        for (Palyazat palyazat : palyazatLista) {
            if (resztvevoHash(palyazat).contains(aktivOktato)) { //ha a resztvevok kozott szerepel a keresett oktato, akkor hozzaadjuk
                aktivitas.add(palyazat);
            }
        }
        return aktivitas;
    }

    //ezzel kerdezzuk le egy palyazat osszes resztvevojet
    private HashSet<String> resztvevoHash(Palyazat palyazat) {
        HashSet<String> resztvevok = new HashSet<>();
        resztvevok.add(palyazat.getResztvevok().getProjektmenedzser());
        resztvevok.add(palyazat.getResztvevok().getSzakmaiVezeto());
        resztvevok.add(palyazat.getResztvevok().getKezelo());
        resztvevok.addAll(palyazat.getResztvevok().getResztvevoEmberek());
        return resztvevok;
    }

    //Az egyes oktatók pályázati aktivitása – hány pályázatban vettek részt, feladat szerint keresve
    public ArrayList<Palyazat> oktatoiAktivitasSzerepek(String aktivOktato, String szerep) {
        if (szerep.equals("összes")) {
            return palyazatokColl.find(or(eq("resztvevok.kezelo", aktivOktato),
                    (eq("resztvevok.projektmenedzser", aktivOktato)),
                    (eq("resztvevok.szakmaiVezeto", aktivOktato)),
                    (eq("resztvevok.resztvevoEmberek", aktivOktato))))
                    .into(new ArrayList<>());
        } else {
            return palyazatokColl.find(eq("resztvevok." + szerep, aktivOktato)).into(new ArrayList<>());
        }
    }

    //tanszeki szinten osszegzi az egyes oktatok aktivitasat
    public HashSet<String> tanszekiAktivitas(String tanszek, String holKeressen) {
            HashSet<String> erintettPalyazatok = new HashSet<>();
        for (Oktato oktato : oktatokColl.find(eq("tanszek", tanszek)).into(new ArrayList<>())) {
            ArrayList<Palyazat> oktatoPalyazatai = oktatoiAktivitas(oktato.getNev(), holKeressen);
            for (Palyazat palyazat : oktatoPalyazatai) {
                erintettPalyazatok.add(palyazat.getPalyazatCim());
            }
        }
        return erintettPalyazatok;
    }

    private ArrayList<Oktato> nevRendezo(ArrayList<Oktato> lista) {
        lista.sort(Comparator.comparing(Oktato::getNev)); //nev alapjan rendezve kuldi vissza
        return lista;
    }
}
