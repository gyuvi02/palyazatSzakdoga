package regi_palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;
import palyazatkezelo.Palyazat;

import java.util.ArrayList;

public class RegiLekerdezesek {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RegiPalyazat> regiPalyazatokColl = palyazatDB.getCollection("RegiPalyazatok", RegiPalyazat.class);

    public ArrayList<Palyazat> rendezettLekerdezes(String rendezesAlapja) {
        ArrayList<Palyazat> palyazatLista = new ArrayList<Palyazat>();
        FindIterable<RegiPalyazat> iterPalyazat = regiPalyazatokColl.find();
        for (RegiPalyazat regiPalyazat : iterPalyazat) {
            palyazatLista.add(regiPalyazat);
        }
        return palyazatLista;
    }




}
