package aktualis_palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;
import palyazatkezelo.Palyazat;

import java.util.ArrayList;

public class AktualisLekerdezesek{

    public AktualisLekerdezesek() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public ArrayList<Palyazat> rendezettLekerdezes(String rendezesAlapja) { //lehetne ugy, hogy valaszthassunk, az aktualis, a regi vagy az osszes palyazaton belul
        ArrayList<Palyazat> palyazatLista = new ArrayList<Palyazat>();
        FindIterable<AktualisPalyazat> iterPalyazat = aktualisPalyazatokColl.find();
        for (AktualisPalyazat aktualisPalyazat : iterPalyazat) {
            palyazatLista.add(aktualisPalyazat);
        }
        return palyazatLista;
    }


//    @Override
//    public ArrayList<AktualisPalyazat> rendezettLekerdezes(String rendezesAlapja) {
//        return super.rendezettLekerdezes(rendezesAlapja);
//    }

}
