package palyazatkezelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import felhivasok.*;
import okatok.Oktato;
import okatok.OktatoConnectMongo;
import okatok.OktatoModosito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws IOException {
        MongoAccess.closeDatabase();
        OktatoConnectMongo oktatok = new OktatoConnectMongo();
        FelhivasParser felhivasParser = new FelhivasParser();
        RSSParser rssParser = new RSSParser();
        FelhivasConnectMongo felhivasConnectMongo = new FelhivasConnectMongo();
        FelhivasModosito felhivasModosito = new FelhivasModosito();

//        felhivasParser.felhivasKeszito(rssParser.rssListaKeszito());

//        felhivasConnectMongo.felhivasLetolto("Az én koronás bakancslistám - hétköznapi vágyaim az önkarantén idején");
//        felhivasConnectMongo.felhivasTorol("Az én koronás bakancslistám - hétköznapi vágyaim az önkarantén idején");
//        felhivasModosito.felhivasUjCim("Private Horizons fotópályázat", "Saját horizont");
//        felhivasModosito.felhivasUjLink("Saját horizont", "http:\\mittomEn");
//        felhivasModosito.felhivasUjKategoriak("Saját horizont", new ArrayList<>(Arrays.asList("ösztöndíj", "közművelődés")));
        felhivasModosito.felhivasUjResztvevok("Saját horizont",
                new ArrayList<Oktato>(Arrays.asList(oktatok.oktatoLetolto("Dr. Szabó Gyula"),
                oktatok.oktatoLetolto("Dr. Balázs-Földi Emese"))));

//        OktatoModosito oktatoModosito = new OktatoModosito();

//        oktatoModosito.oktatoUjPalyazati("Dr. Szabó Gyula", new ArrayList(Arrays.asList("ifjúság", "gyermek", "közművelődés", "ösztöndíj")));
//        oktatoModosito.oktatoUjKutatasi("Dr. Szabó Gyula", new ArrayList(Arrays.asList("szociológia", "közgazdaságtudomány", "foglalkoztatáspolitika", "projektmenedzsment")));
//        oktatoModosito.oktatoUjTanszek("Dr. Szabó Gyula", "Szociálpedagógia");
//        oktatoModosito.oktatoUjHonlap("Dr. Szabó Gyula", "https://gygyk.unideb.hu/munkatars/3425");
//        oktatoModosito.oktatoUjEmail("Dr. Szabó Gyula", "szabogy@ped.unideb.hu");

//        ArrayList<String> oKutatasiTema = new ArrayList<>(Arrays.asList("projektmenedzsment", "pályázatírás",
//                "szociális munka", "cigányság", "ifjúság"));
//        ArrayList<String> oPalyazatiTema = new ArrayList<>(Arrays.asList("ifjúság", "média"));
//
//        oktatok.oktatoFeltolto(new Oktato("Kocsis Péter Csaba", "Szociálpedagógia",oKutatasiTema,
//                "kocspet@gmail.com", "https://gygyk.unideb.hu/hu/kocsis-peter-csaba-0#overlay-context=munkatars/6270", oPalyazatiTema));

//        oktatok.oktatoBeolvaso("Dr. Szabó Gyula");
//        oktatok.oktatoTorol("Valaki");
    }
}
