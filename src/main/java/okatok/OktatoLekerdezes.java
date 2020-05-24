package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;
import palyazatok.Palyazat;
import palyazatok.PalyazatLekerdezesek;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class OktatoLekerdezes{

    public OktatoLekerdezes() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);

    //A kar összes oktatójának vagy egy tanszékhez tartozó oktatók lekérdezése – ennek igazából nincs túl sok gyakorlati jelentősége,
    //de mas metodusok felhasznalhatjak
    public ArrayList<Oktato> oktatoListak(String tanszek) {//meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        if (tanszek.equals("összes")) {
            return oktatokColl.find().into(new ArrayList<>());
        }
        return oktatokColl.find(eq("tanszek", tanszek)).into(new ArrayList<>());
    }

    //Egyes oktatók adatlapjának lekérése
    public ArrayList<Oktato> oktatoKereso(String oktato) {
        return oktatokColl.find(eq("nev", oktato)).into(new ArrayList<>());
    }

    //A kar vagy egy tanszék összes kutatási témájának kiíratása (mindegyik csak egyszer szerepeljen)
    public List<String> kutatasiTemak(String tanszek) { //meg kell adni (legordulo menu), hogy melyik tanszek, vagy az osszes
        HashSet<String> kutTemak = new HashSet<>();
        for (Oktato oktato : oktatoListak(tanszek)) {
            kutTemak.addAll(oktato.getKutatasiTema());  //a HasSetbe kiolvassuk a tombok osszes elemet
        }
        List<String> rendezettTemak = new ArrayList<String>(kutTemak); //hogy rendezett legyen, kenytelen vagyok listaba attenni
        Collections.sort(rendezettTemak);
        return rendezettTemak;
    }

    public void kulcsszavasLekerdezes(String kulcsszo) {

    }

    //Kutatási témák alapján keresés az oktatók között
    public ArrayList<Oktato> kutatasiTemaKereso(String tema) { //a tomb csak kutatasiTema vagy palyazatiTema lehet
        return oktatokColl.find(eq("kutatasiTema", tema)).into(new ArrayList<>());
    }

    //Az oktatók pályázati témái alapján – ezekből nincs túl sok, legördülő menüvel megoldható
    public ArrayList<Oktato> palyazatiTemaKereso(String tema) {
        return oktatokColl.find(eq("palyazatiTema", tema)).into(new ArrayList<>());
    }

    //Az egyes oktatók pályázati aktivitása – hány pályázatban vettek részt
    //3 katagoriabol valaszthatunk: "összes", "aktuális" (elkezdett es beadott), "régi" (lezart es elfogadott), "sikertelen" (elutasitott)
    public ArrayList<String> oktatoiAktivitas(String aktivOktato, String holKeressen) {
        PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();
        ArrayList<String> aktivitas = new ArrayList<>();
        HashSet<String> resztvevok = new HashSet<>();

        if (holKeressen.equals("aktuális") || holKeressen.equals("összes")) {
            for (Palyazat palyazat : palyazatLekerdezesek.fazisLekerdezes("elkezdett")) {
                if (resztvevoHash(palyazat, resztvevok).contains(aktivOktato))
                    aktivitas.add(palyazat.getPalyazatCim());
            }
            for (Palyazat palyazat : palyazatLekerdezesek.fazisLekerdezes("beadott")) {
                if (resztvevoHash(palyazat, resztvevok).contains(aktivOktato))
                    aktivitas.add(palyazat.getPalyazatCim());
            }
        }
        if (holKeressen.equals("régi") || holKeressen.equals("összes")) {
            for (Palyazat palyazat : palyazatLekerdezesek.fazisLekerdezes("lezart")) {
                if (resztvevoHash(palyazat, resztvevok).contains(aktivOktato))
                    aktivitas.add(palyazat.getPalyazatCim());
            }
            for (Palyazat palyazat : palyazatLekerdezesek.fazisLekerdezes("elfogadott")) {
                if (resztvevoHash(palyazat, resztvevok).contains(aktivOktato))
                    aktivitas.add(palyazat.getPalyazatCim());
            }
        }
        if (holKeressen.equals("sikertelen") || holKeressen.equals("összes")) {
            for (Palyazat palyazat : palyazatLekerdezesek.fazisLekerdezes("elutasitott")) {
                if (resztvevoHash(palyazat, resztvevok).contains(aktivOktato))
                    aktivitas.add(palyazat.getPalyazatCim());
            }
        }
        return aktivitas; //a palyazatok cimet adja vissza egy tombben, ez alapjan lehet visszakeresni
    }

    private HashSet<String> resztvevoHash(Palyazat palyazat, HashSet<String> resztvevok) {
        resztvevok.addAll(palyazat.getResztvevok().getResztvevoEmberek());
        resztvevok.add(palyazat.getResztvevok().getProjektmenedzser());
        resztvevok.add(palyazat.getResztvevok().getSzakmaiVezeto());
        resztvevok.add(palyazat.getResztvevok().getKezelo());
        return resztvevok;
    }

    //tanszeki szinten osszegzi az egyes oktatok aktivitasat
    public HashSet<String> tanszekiAktivitas(String tanszek, String holKeressen) {
        HashSet<String> erintettPalyazatok = new HashSet<>();
        for (Oktato oktato : oktatoListak(tanszek)) {
            erintettPalyazatok.addAll(oktatoiAktivitas(oktato.getNev(), holKeressen));
        }
        return erintettPalyazatok;
    }

    private ArrayList<Oktato> nevRendezo(ArrayList<Oktato> lista) {
        Collections.sort(lista, Comparator.comparing(Oktato::getNev)); //nev alapjan rendezve kuldi vissza
        return lista;
    }
}
