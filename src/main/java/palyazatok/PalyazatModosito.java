package palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class PalyazatModosito {
    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);

    public void adatModosito(int mezo, String cim, String ujAdat) {
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
        multiModosito(mezoStr, cim, ujAdat);
    }
    private void multiModosito(String mezo, String cim, String ujAdat) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set(mezo, ujAdat);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void kezdetModosito(String cim, Date ujKezdet) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("kezdet", ujKezdet);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void vegModosito(String cim, Date ujVeg) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("veg", ujVeg);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void kplusFModosito(String cim, boolean ujKplusF) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("KplusF", ujKplusF);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void oneroModosito(String cim, Double ujOnero) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("onero", ujOnero);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void osszkoltsegModosito(String cim, Double ujOsszkoltseg) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("tervezettOsszkoltseg", ujOsszkoltseg);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public void igenyeltOsszegModosito(String cim, Double ujIgenyelt) {
        Bson filter = eq("palyazatCim", cim);
        Bson ujElem = set("igenyeltTamogatas", ujIgenyelt);
        palyazatokColl.updateOne(filter, ujElem);
    }

    public boolean pozicioHozzaad(String cim, String pozicio, String nev) {
        Palyazat palyazat = new Palyazat();
        if (!palyazat.palyazatEllenorzo(palyazat.PalyazatLetolto(cim))) {
            return false;
        }
        Bson ujElem = null;
        ArrayList<String> resztvevoEmberek= palyazat.PalyazatLetolto(cim).getResztvevok().getResztvevoEmberek();
        if (resztvevoEmberek.contains(nev)) {
            System.out.println("Mar szerepel a szakertok kozott");
            return false;
        }
        Bson filter = eq("palyazatCim", cim);
        if (pozicio.equals("resztvevoEmberek")) {
            resztvevoEmberek.add(nev);
            ujElem = set("resztvevok.resztvevoEmberek", resztvevoEmberek);
        } else {
            ujElem = set("resztvevok." + pozicio, nev);
        }
        palyazatokColl.updateOne(filter, ujElem);
        return true;
    }
    public boolean pozicioTorol(String cim, String pozicio, String nev) {
        Palyazat palyazat = new Palyazat();
        if (!palyazat.palyazatEllenorzo(palyazat.PalyazatLetolto(cim))) {//ellenorzom, hogy letezik-e a palyazat, kulonben kivetelt dob
            System.out.println("nincs ilyen palyazat");
            return false;
        }
        Bson ujElem = null;
        ArrayList<String> resztvevoEmberek= palyazat.PalyazatLetolto(cim).getResztvevok().getResztvevoEmberek();
        Bson filter = eq("palyazatCim", cim);
        if (pozicio.equals("resztvevoEmberek")) {
            if (resztvevoEmberek.contains(nev)) {
                resztvevoEmberek.remove(nev);
                ujElem = set("resztvevok.resztvevoEmberek", resztvevoEmberek);
            } else {
                return false;
            }
        } else {
            ujElem = set("resztvevok." + pozicio, "");
        }
        palyazatokColl.updateOne(filter, ujElem);
        return true;
    }






}
