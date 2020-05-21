package aktualis_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;
import palyazatkezelo.Palyazat;
import regi_palyazatok.RegiPalyazat;

import java.time.LocalDate;

import static com.mongodb.client.model.Filters.eq;

public class AktualisPalyazat extends Palyazat {
    public AktualisPalyazat(String palyazatCim, String leiras, String felhivasKod, String beadasEve,
                            Boolean kplusF, Double onero, Double tervezettOsszkoltseg,
                            Double igenyeltTamogatas, String megjegyzes, PalyazatiResztvevok resztvevok,
                            String aktualisFazis) {
        super(palyazatCim, leiras, felhivasKod, beadasEve, kplusF, onero, tervezettOsszkoltseg,
                igenyeltTamogatas, megjegyzes, resztvevok, aktualisFazis);
    }

    public AktualisPalyazat(String palyazatCim) {
        super(palyazatCim);
    }

    public AktualisPalyazat() {
        super();
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public void aktualisPalyazatFeltolto() {
        Bson filter = eq("palyazatCim", this.getPalyazatCim()); //csak a cimet ellenorzom, nem lehet 2 egyforma cimu palyazat
        if (palyazatEllenorzo(aktualisPalyazatokColl.find(filter).first())) {
            System.out.println("Mar van ilyen palyazat");
        } else {
            aktualisPalyazatokColl.insertOne(this);
        }
    }

    public void aktualisPalyazatokLetolto(String cim) {
        AktualisPalyazat keresettPalyazat = aktualisPalyazatokColl.find((eq("palyazatCim", cim))).first();
            System.out.println(keresettPalyazat.toString());
    }

    public void aktualisPalyazatTorlo(String palyazatcim) {
        Bson filter = eq("palyazatCim", palyazatcim); //Mivel a cim egyedi, ezt ellenorzom torlesnel is
        if (palyazatEllenorzo(aktualisPalyazatokColl.find(filter).first())){
            aktualisPalyazatokColl.deleteOne(filter);
        }
        else System.out.println("Nincs ilyen pályázat");
    }

    public boolean palyazatEllenorzo(AktualisPalyazat keresettPalyazat) {
        return keresettPalyazat != null;
    }

    //ennek a metodusnak a segitsegevel a futo palyazatokat nehany adat megadasaval at lehet tenni a regiek koze, es azutan torolni
    public RegiPalyazat aktualisbolRegi(AktualisPalyazat aktualis, String deAzonosito, String szerzodesSzam, LocalDate kezdet,
                                LocalDate veg) {
        RegiPalyazat ujRegi = new RegiPalyazat(aktualis.getPalyazatCim(), aktualis.getLeiras(), aktualis.getFelhivasKod(),
                aktualis.getBeadasEve(), aktualis.getKplusF(), aktualis.getOnero(), aktualis.getTervezettOsszkoltseg(),
                aktualis.getIgenyeltTamogatas(), aktualis.getMegjegyzes(), aktualis.getResztvevok(), aktualis.getAktualisFazis(),
                deAzonosito, szerzodesSzam, kezdet, veg);
        aktualis.aktualisPalyazatTorlo(aktualis.getPalyazatCim());
        return ujRegi;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
