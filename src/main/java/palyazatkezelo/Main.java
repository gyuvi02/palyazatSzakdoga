package palyazatkezelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import felhivasok.*;
import okatok.Oktato;
import okatok.OktatoConnectMongo;
import okatok.OktatoModosito;
import regi_palyazatok.RegiConnectMongo;
import regi_palyazatok.RegiPalyazat;
import regi_palyazatok.RegiPalyazatModosito;
import regi_palyazatok.RegiResztvevok;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
//        MongoAccess.closeDatabase();
        OktatoConnectMongo oktatok = new OktatoConnectMongo();
        FelhivasParser felhivasParser = new FelhivasParser();
        RSSParser rssParser = new RSSParser();
        FelhivasConnectMongo felhivasConnectMongo = new FelhivasConnectMongo();
        FelhivasModosito felhivasModosito = new FelhivasModosito();
        RegiConnectMongo regiConnectMongo = new RegiConnectMongo();
        RegiPalyazatModosito regiModosito = new RegiPalyazatModosito();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate kezdet = LocalDate.of(2017, 9, 1);
        LocalDate veg = LocalDate.of(2018, 6, 30);
        RegiResztvevok resztvevok = new RegiResztvevok("Dr. Bocsi Veronika",
                "Dr. Szabó Gyula", "Csőke Julianna",
                new ArrayList<String>(Arrays.asList("Dr. Gortka-Rákó Erzsébet")));

        RegiPalyazat regiPalyazat = new RegiPalyazat("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán",
                "9761", "NTP-HHTDK-17-0064", "A tehetséggondozó pályázat fő célja, hogy előkészítse a diákjaink sikeresebb OTDK szereplését, és formális keretek között, illetve magasabb színvonalon valósítsa meg a tehetséggondozással kapcsolatos feladatokat. Felkészítse a diákokat a tudományos munkára, megismertesse velük a tudományos karrier lehetséges állomásait, és mérsékelje azokat az induló hátrányokat, amelyekkel a hallgatóink a magasabb presztízsű egyetemi karokhoz képest rendelkeznek",
                "NTP-HHTDK-17", kezdet, veg, false, 0.0, 1200000.0,
                1200000.0, "", resztvevok);

//        System.out.println(regiPalyazat.toString());
//        regiConnectMongo.regiPalyazatLetolto("asas");
//        regiConnectMongo.regiPalyazatFeltolto(regiPalyazat);
//        regiModosito.regiModosito(1, "Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", "XXXXXXXXX");
//        regiModosito.regiPalyazatOnero("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", 10000.0);

//        felhivasParser.felhivasKeszito(rssParser.rssListaKeszito());

//        felhivasConnectMongo.felhivasLetolto("Az én koronás bakancslistám - hétköznapi vágyaim az önkarantén idején");
//        felhivasConnectMongo.felhivasTorol("Az én koronás bakancslistám - hétköznapi vágyaim az önkarantén idején");
//        felhivasModosito.felhivasUjCim("Private Horizons fotópályázat", "Saját horizont");
//        felhivasModosito.felhivasUjLink("Saját horizont", "http:\\mittomEn");
//        felhivasModosito.felhivasUjKategoriak("Saját horizont", new ArrayList<>(Arrays.asList("ösztöndíj", "közművelődés")));
//        felhivasModosito.felhivasUjResztvevok("Saját horizont",
//                new ArrayList<Oktato>(Arrays.asList(oktatok.oktatoLetolto("Dr. Szabó Gyula"),
//                oktatok.oktatoLetolto("Dr. Balázs-Földi Emese"))));

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

//        System.out.println(oktatok.oktatoLetolto("Dr. Szabó Gyula"));
//        oktatok.oktatoTorol("Valaki");
    }
}
