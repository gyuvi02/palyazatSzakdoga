package palyazatkezelo;

import okatok.Oktato;
import okatok.OktatoConnectMongo;
import okatok.OktatoModosito;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        OktatoModosito oktatoModosito = new OktatoModosito();

        oktatoModosito.oktatoUjPalyazati("Dr. Szabó Gyula", new ArrayList(Arrays.asList("ifjúság", "gyermek", "közművelődés", "ösztöndíj")));
//        oktatoModosito.oktatoUjKutatasi("Dr. Szabó Gyula", new ArrayList(Arrays.asList("szociológia", "közgazdaságtudomány", "foglalkoztatáspolitika", "projektmenedzsment")));
//        oktatoModosito.oktatoUjTanszek("Dr. Szabó Gyula", "Szociálpedagógia");
//        oktatoModosito.oktatoUjHonlap("Dr. Szabó Gyula", "https://gygyk.unideb.hu/munkatars/3425");
//        oktatoModosito.oktatoUjEmail("Dr. Szabó Gyula", "szabogy@ped.unideb.hu");
//        oktatoModosito.oktatoUjNev("szabo.gyula@unideb.hu", "Dr. Szabó Gyula");

//        OktatoConnectMongo oktatok = new OktatoConnectMongo();
//        ArrayList<String> oKutatasiTema = new ArrayList<>(Arrays.asList("projektmenedzsment", "pályázatírás",
//                "szociális munka", "cigányság", "ifjúság"));
//        ArrayList<String> oPalyazatiTema = new ArrayList<>(Arrays.asList("ifjúság", "média"));
//
//        oktatok.oktatoFeltolto(new Oktato("Kocsis Péter Csaba", "Szociálpedagógia",oKutatasiTema,
//                "kocspet@gmail.com", "https://gygyk.unideb.hu/hu/kocsis-peter-csaba-0#overlay-context=munkatars/6270", oPalyazatiTema));

//        oktatok.oktatoBeolvaso("Dr. Gortka-Rákó Erzsébet");
//        oktatok.oktatoTorol("Valaki");
    }
}
