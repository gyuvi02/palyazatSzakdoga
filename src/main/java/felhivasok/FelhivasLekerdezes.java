package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import palyazatkezelo.MongoAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

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
    //az atkuldott datum utani beadasi hatarideju felhivasok listaja, azert ilyen bonyolult, mert nem tudom datumkent tarolni az elofordulo szovegek miatt
    public ArrayList<Felhivas> kesobbiHataridok(Date datum) {
        ArrayList<Felhivas> felhivasLista = felhivasokColl.find().into(new ArrayList<>());
        ArrayList<Felhivas> korabbiFelhivasok = new ArrayList<>();
        for (Felhivas felhivas : felhivasLista) {
            if (datum.before(parseDate(felhivas.getBeadasiHatarido()))){
                korabbiFelhivasok.add(felhivas);
            }
        }
        return korabbiFelhivasok;
    }


    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy. MMMM dd.").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
