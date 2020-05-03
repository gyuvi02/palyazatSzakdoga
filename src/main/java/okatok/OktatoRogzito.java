package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.Arrays;

public class OktatoRogzito {

    protected Oktato oktatoOsszeallito() {
        String oNev = "Valaki";
        String oTanszek = "Szociálpedagógia";
        ArrayList<String> oKutatasiTema = new ArrayList<>(Arrays.asList("nincs", "ilyen"));
        String oEmail = "valaki@ped.unideb.hu";
        String oHonlap = "https://gygyk.unideb.hu/munkatars/3425";
        ArrayList<String> oPalyazatiTema = new ArrayList<>(Arrays.asList("ez", "sincs", "neki"));

        Oktato ujOktato = new Oktato(oNev, oTanszek, oKutatasiTema, oEmail, oHonlap, oPalyazatiTema);
        return ujOktato;
    }
}
