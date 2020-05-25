package palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class PalyazatModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);

    public long adatModosito(int mezo, String cim, String ujAdat) {
        String mezoStr = switch (mezo) {
            case 0 -> "palyazatCim";
            case 1 -> "aktualisFazis";
            case 2 -> "leiras";
            case 3 -> "felhivasKod";
            case 4 -> "beadasEve";
            case 5 -> "megjegyzes";
            case 6 -> "DEazonosito";
            case 7 -> "szerzodesSzam";

            default -> "regiCim";
        };
        return multiModosito(mezoStr, cim, ujAdat);
    }
    private long multiModosito(String mezo, String cim, String ujAdat) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set(mezo, ujAdat);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long kezdetModosito(String cim, LocalDate ujKezdet) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("kezdet", ujKezdet);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long vegModosito(String cim, LocalDate ujVeg) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("veg", ujVeg);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long kplusFModosito(String cim, boolean ujKplusF) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("KplusF", ujKplusF);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long oneroModosito(String cim, Double ujOnero) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("onero", ujOnero);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long osszkoltsegModosito(String cim, Double ujOsszkoltseg) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("tervezettOsszkoltseg", ujOsszkoltseg);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long igenyeltOsszegModosito(String cim, Double ujIgenyelt) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujIgenyelt);
        return palyazatokColl.updateOne(filter, ujElem).getModifiedCount();
    }

    public long pozicioHozzaad(String cim, String pozicio, String nev) {
        Bson filter = eq("palyazatCim", cim);
        if (palyazatokColl.find(filter).into(new ArrayList<>()).size() == 0){
            System.out.println("Nincs ilyen palyazat");
            return 0;
        }
        Palyazat palyazat = palyazatokColl.find(filter).first();
        if (palyazat.getResztvevok().getResztvevoEmberek().contains(nev)) {
            System.out.println("Mar szerepel a szakertok kozott ");
            return 0;
        }
        if (pozicio.equals("resztvevoEmberek")) {
            palyazat.getResztvevok().getResztvevoEmberek().add(nev);
            return palyazatokColl.updateOne(filter, set("resztvevok.resztvevoEmberek", palyazat.getResztvevok().getResztvevoEmberek())).getModifiedCount();
        } else {
            return palyazatokColl.updateOne(filter, set("resztvevok." + pozicio, nev)).getModifiedCount();
        }
    }

    public long pozicioTorol(String cim, String pozicio, String nev) {
        Bson filter = eq("palyazatCim", cim);
        if (palyazatokColl.find(filter).into(new ArrayList<>()).size() == 0){
            System.out.println("Nincs ilyen palyazat");
            return 0;
        }
        Palyazat palyazat = palyazatokColl.find(filter).first();
        if (pozicio.equals("resztvevoEmberek")) {
            ArrayList<String> resztvevoEmberek = palyazat.getResztvevok().getResztvevoEmberek();
            if (resztvevoEmberek.contains(nev)) {
                resztvevoEmberek.remove(nev);
                return palyazatokColl.updateOne(filter, set("resztvevok.resztvevoEmberek", resztvevoEmberek)).getModifiedCount();
            } else {
                return 0;
            }
        } else {
           return palyazatokColl.updateOne(filter, set("resztvevok." + pozicio, "")).getModifiedCount();
        }
    }






}
