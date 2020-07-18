package felhivasok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import palyazatkezelo.MongoAccess;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class FelhivasLekerdezes {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    //Az osszes felhivas cimet visszaadja
    public ArrayList<String> felhivasListak() {
        return felhivasokColl.find().map(Felhivas::getFelhivasCim).into(new ArrayList<>());
    }

    public ArrayList<String> felhivasListaLimited(int oldalszam, int limit) {
        ArrayList<String> cimLista = new ArrayList<>();
        return nevRendezo(felhivasokColl.find().skip(oldalszam*limit).limit(limit).map(Felhivas::getFelhivasCim).into(new ArrayList<>()));
        //Ha az egyes adagokat beadasi hatarido szerint akarjuk felsorolni, az egy kicsit bonyolultabb:
//       ArrayList<Felhivas> felhivasLista = hataridoRendezes(felhivasokColl.find().skip(oldalszam*limit).limit(limit).into(new ArrayList<>()));
//        for (Felhivas felhivas : felhivasLista) {
//            cimLista.add(felhivas.getFelhivasCim());
//        }
//        return cimLista;
        //bar valoszinuleg gyorsabb lenne, ha csak egy LocalDate tombot kerseznek le, es azt kuldenem at sorbarendezesre, de nincs gyakorlati jelentosege
    }

    public long felhivasokSzama() {
        return felhivasokColl.countDocuments();
    }

    public HashSet<String>  felhivasCimekHash() {
        return felhivasokColl.find().projection((fields(include("felhivasCim"),
                excludeId()))).map(Felhivas::getFelhivasCim).into(new HashSet<>());
    }

//    //visszaadja a keresett kiirohoz tartozo osszes felhivast - a gyakorlatban nincs jelentosege
//    public ArrayList<Felhivas> kiiroLekerdezes(String kiiro) {
//        return felhivasokColl.find(eq("felhivasKiiro", kiiro)).into(new ArrayList<>());
//    }

    //az osszes felhivas, amelyben szerepel az adott kategoria
    public ArrayList<String> palyazatiKategoriaAlapjan(String kategoria) {
        return felhivasokColl.find(eq("kategoriak", kategoria)).map(Felhivas::getFelhivasCim).into(new ArrayList<>());
    }

    //az osszes felhivas, amelyben erintett lehet a megadott oktato
    public ArrayList<String> resztvevokAlapjan(String nev) {
       ArrayList<String> rendezettFelhivasok = new ArrayList<>
           (felhivasokColl.find(eq("lehetsegesResztvevok", nev)).map(Felhivas::getFelhivasCim).into(new HashSet<>()));
        return nevRendezo(rendezettFelhivasok);
    }

    public ArrayList<String> kulcsszavakFelhivas(String kulcsszo) {
        return felhivasokColl.find(Filters.text(kulcsszo, new TextSearchOptions().language("hu"))).map(Felhivas::getFelhivasCim).into(new ArrayList<>());
    }

    private ArrayList<Felhivas> hataridoRendezes(ArrayList<Felhivas> lista){ //egyszerubb, ha a torles datum alapjan csinaljuk
        lista.sort(((o1, o2) -> {
            LocalDate date1 = o1.getTorles();
            LocalDate date2 = o2.getTorles();
            return date1.compareTo(date2);
        }));

//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        lista.sort((o1, o2) -> {
//                LocalDate date1 = parseDate(o1.getBeadasiHatarido());
//                LocalDate date2 = parseDate(o2.getBeadasiHatarido());
//            if (date1 != null) {
//                return date1.compareTo(date2);
//            } else {
//                return 1;
//            }
//        });
        return lista;
    }

//    A felhivas dokumentumokban torles neven tarolt datumot nezzuk vegig
//    ha ez kevesebb, mint a mai datum, akkor toroljuk az adatbazisbol
    public void automatikusTorles() {
        System.out.println(felhivasokColl.deleteMany(lte("torles", LocalDate.now())));
    }

    //ez a modosito utolag adta hozza a beadasi hataridobol kiszamitott torlesi idopontot a regi felhivasokhoz
    //updateMany kell, mert vannak egyforma cimu palyazatok
//    public void egyszerimodositas() {
//        for (Felhivas felhivas : felhivasokColl.find().into(new ArrayList<>())) {
//        felhivasokColl.updateMany(eq("felhivasCim", felhivas.getFelhivasCim()),
//                new Document("$set", new Document("torles", parseDate(felhivas.getBeadasiHatarido()).plusDays(14))));
//        }
//    }

    //az atkuldott datum utani beadasi hatarideju felhivasok listaja, azert ilyen bonyolult, mert nem tudom datumkent tarolni az elofordulo szovegek miatt
    //a torles mezo bevezetesevel annak a hasznalata egyszerubb, csak kivonok 2 hetet a torles datumabol, es az a hatarido
    //bar a szoveges hataridoknel egy kitalalt beadasi hatarido jon igy ki
    public ArrayList<String> kesobbiHataridok(LocalDate datum) {
        return felhivasokColl.find(gte("torles", datum.minusDays(14))).map(Felhivas::getFelhivasCim).into(new ArrayList<>());

//        ArrayList<Felhivas> korabbiFelhivasok = new ArrayList<>();
//        for (Felhivas felhivas : felhivasokColl.find().into(new ArrayList<>())) {
//            if (datum.isBefore(parseDate(felhivas.getBeadasiHatarido()))){
//                korabbiFelhivasok.add(felhivas);
//            }
//        }
    }


    public static LocalDate parseDate(String date) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy. MMMM dd.");
            return LocalDate.parse(date, inputFormat);
        } catch (Exception e) {
//            return LocalDate.of(2025, 12, 31);
            return LocalDate.now().plusDays(365 * 5);//inkabb nem egy fix datumot adunk at, hanem 5 evvel kesobbi datumot
        }
    }
    public static ArrayList<String> nevRendezo(ArrayList<String> lista) {
        lista.sort(Comparator.comparing(String::trim)); //nev alapjan rendezve kuldi vissza
        return lista;
    }
}
