package felhivasok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import palyazatkezelo.MongoAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
