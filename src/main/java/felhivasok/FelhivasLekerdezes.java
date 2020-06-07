package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import palyazatkezelo.MongoAccess;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lte;

public class FelhivasLekerdezes {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    //Az osszes felhivast visszaadja
    public ArrayList<Felhivas> felhivasListak() {
        return felhivasokColl.find().into(new ArrayList<>());
    }

    //visszaadja a keresett kiirohoz tartozo osszes felhivast
    public ArrayList<Felhivas> kiiroLekerdezes(String kiiro) {
        return felhivasokColl.find(eq("felhivasKiiro", kiiro)).into(new ArrayList<>());
    }

    //az osszes felhivas, amelyben szerepel az adott kategoria
    public ArrayList<Felhivas> palyazatiKategoriaAlapjan(String kategoria) {
        return felhivasokColl.find(eq("kategoriak", kategoria)).into(new ArrayList<>());
    }

    public ArrayList<Felhivas> kulcsszavakFelhivas(String kulcsszo) {
        return felhivasokColl.find(Filters.text(kulcsszo, new TextSearchOptions().language("hu"))).into(new ArrayList<>());
    }

    private ArrayList<Felhivas> hataridoRendezes(ArrayList<Felhivas> lista){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        lista.sort((o1, o2) -> {
                LocalDate date1 = parseDate(o1.getBeadasiHatarido());
                LocalDate date2 = parseDate(o2.getBeadasiHatarido());
            if (date1 != null) {
                return date1.compareTo(date2);
            } else {
                return 1;
            }
        });
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
    public ArrayList<Felhivas> kesobbiHataridok(LocalDate datum) {
        ArrayList<Felhivas> korabbiFelhivasok = new ArrayList<>();
        for (Felhivas felhivas : felhivasokColl.find().into(new ArrayList<>())) {
            if (datum.isBefore(parseDate(felhivas.getBeadasiHatarido()))){
                korabbiFelhivasok.add(felhivas);
            }
        }
        return korabbiFelhivasok;
    }


    public static LocalDate parseDate(String date) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy. MMMM dd.");
            return LocalDate.parse(date, inputFormat);
        } catch (Exception e) {
            return LocalDate.of(2030, 12, 31);
//            return null; //majd ez alapjan torlunk lte felhasznalasaval, inkabb egy kesobbi datumot adok meg
        }
    }

}
