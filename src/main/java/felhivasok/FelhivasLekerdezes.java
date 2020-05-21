package felhivasok;

import com.github.davidmoten.guavamini.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.text;

import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FelhivasLekerdezes {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Felhivas> felhivasokColl = palyazatDB.getCollection("Felhivasok", Felhivas.class);

    //Az osszes felhivast visszaadja, a tobbi metodus hasznalja a lekerdezesekhez
    public ArrayList<Felhivas> felhivasListak() {
        ArrayList<Felhivas> felhivasLista = new ArrayList<>();
        FindIterable<Felhivas> iterFelhivas = felhivasokColl.find();
        for (Felhivas felhivas : iterFelhivas) {
                felhivasLista.add(felhivas);
        }
//        return hataridoRendezes(felhivasLista);
        return hataridoRendezes(felhivasLista);
    }

    public ArrayList<String> kiiroLekerdezes(String kiiro) {
        ArrayList<String> kiiroLista = new ArrayList<>();
        FindIterable<Felhivas> iterable = felhivasokColl.find(eq("felhivasKiiro", kiiro));
        MongoCursor<Felhivas> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            kiiroLista.add(cursor.next().getFelhivasCim());
        }
        return kiiroLista;
    }

    public ArrayList<String> palyazatiKategoriaAlapjan(String kategoria) {
        ArrayList<String> kategoriaLista = new ArrayList<>();
        FindIterable<Felhivas> iterable = felhivasokColl.find(eq("kategoriak", kategoria));
        MongoCursor<Felhivas> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            kategoriaLista.add(cursor.next().getFelhivasCim());
        }
        return kategoriaLista;
    }

    public ArrayList<String> kulcsszavakFelhivas(String kulcsszo) {
//        felhivasokColl.createIndexes(Lists.newArrayList(
//                new IndexModel(Indexes.ascending("_id"),
//                        new IndexOptions().unique(false)
//                ),
//                new IndexModel(Indexes.compoundIndex(Indexes.text("kategoriak"), Indexes.text("reszletesLeiras"),
//                        Indexes.text("kiPalyazhat"), Indexes.text("targymutato")),
//                        new IndexOptions().defaultLanguage("hu")
//                )));

        HashSet<String> kulcsszoTalalat = new HashSet<>(); //ha tobbszor is szerepel az adatbazisban, akkor is csak egyszer kapjuk vissza
        FindIterable<Felhivas> iterable = felhivasokColl.find(Filters.text(kulcsszo, new TextSearchOptions().language("hu")));
        for (Felhivas felhivas : iterable) {
            kulcsszoTalalat.add(felhivas.getFelhivasCim());
        }
        System.out.println(kulcsszoTalalat.size());
        //        felhivasokColl.dropIndex("kategoriak_text_reszletesLeiras_text_kiPalyazhat_text_targymutato_text"); //az index nevet a createIndex altal visszaadott string mondja meg, a field neve + _text
        return new ArrayList<>(kulcsszoTalalat);

    }



    private ArrayList<Felhivas> hataridoRendezes(ArrayList<Felhivas> lista){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        lista.sort((o1, o2) -> {
                Date date1 = parseDate(o1.getBeadasiHatarido());
                Date date2 = parseDate(o2.getBeadasiHatarido());
            if (date1 != null) {
                return date1.compareTo(date2);
            } else {
                return 1;
            }
        });
        return lista;
    }

    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy. MMMM dd.").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
