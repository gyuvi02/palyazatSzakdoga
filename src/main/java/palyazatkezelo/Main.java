package palyazatkezelo;

import aktualis_palyazatok.AktualisPalyazat;
import aktualis_palyazatok.PalyazatiResztvevok;
import felhivasok.*;
import okatok.OktatoModosito;
import regi_palyazatok.RegiPalyazat;
import regi_palyazatok.RegiPalyazatModosito;
import regi_palyazatok.RegiResztvevok;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
//        MongoAccess.closeDatabase();
//        Oktato oktato = new Oktato();
//        Oktato oktato = new Oktato("Dr. Pornói Imre", "Szociálpedagógia",
//                new ArrayList<String>(Arrays.asList("pedagógií","módszertan")), "pornoi.imre@ped.unideb.hu",
//                "https://gygyk.unideb.hu/hu/node/292#overlay-context=", new ArrayList<String>(Arrays.asList("gyermek", "ifjúság")));
//        oktato.oktatoFeltolto();

        FelhivasParser felhivasParser = new FelhivasParser();
        RSSParser rssParser = new RSSParser();
        FelhivasModosito felhivasModosito = new FelhivasModosito();


        RegiPalyazatModosito regiModosito = new RegiPalyazatModosito();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate kezdet = LocalDate.of(2017, 9, 1);
        LocalDate veg = LocalDate.of(2018, 6, 30);
        PalyazatiResztvevok resztvevok = new PalyazatiResztvevok("Dr. Bocsi Veronika",
                "Dr. Szabó Gyula", "Csőke Julianna",
                new ArrayList<String>(Arrays.asList("Dr. Gortka-Rákó Erzsébet")));


        PalyazatiResztvevok aResztvevok = new PalyazatiResztvevok("Dr. Bocsi Veronika", "Dr. Szabó Gyula", "", new ArrayList<>(Arrays.asList("Dr. Nemes Magdolna", "Dr. Gortka-Rákó Erzsébet")));
        AktualisPalyazat aktualisPalyazat = new AktualisPalyazat("Roma szakkollégium", "A tehetséggondozó pályázat fő célja, hogy előkészítse a diákjaink sikeresebb OTDK szereplését, és formális keretek között, illetve magasabb színvonalon valósítsa meg a tehetséggondozással kapcsolatos feladatokat. Felkészítse a diákokat a tudományos munkára, megismertesse velük a tudományos karrier lehetséges állomásait, és mérsékelje azokat az induló hátrányokat, amelyekkel a hallgatóink a magasabb presztízsű egyetemi karokhoz képest rendelkeznek",
                "NTP-HHTDK-17", false, 0.0, 13840000.0, 13840000.0, "", aResztvevok,
                "Beadott");
//        aktualisPalyazat.aktualisPalyazatFeltolto();

        RegiPalyazat regiPalyazat = new RegiPalyazat("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán",
                "9761", "NTP-HHTDK-17-0064", "A tehetséggondozó pályázat fő célja, hogy előkészítse a diákjaink sikeresebb OTDK szereplését, és formális keretek között, illetve magasabb színvonalon valósítsa meg a tehetséggondozással kapcsolatos feladatokat. Felkészítse a diákokat a tudományos munkára, megismertesse velük a tudományos karrier lehetséges állomásait, és mérsékelje azokat az induló hátrányokat, amelyekkel a hallgatóink a magasabb presztízsű egyetemi karokhoz képest rendelkeznek",
                "NTP-HHTDK-17", kezdet, veg, false, 0.0, 1200000.0,
                1200000.0, "", resztvevok, "Lezárt");
//        regiPalyazat.regiPalyazatFeltolto();

//        Archivalt palyazatok:
//        System.out.println(regiPalyazat.toString());
//        regiConnectMongo.regiPalyazatLetolto("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán");
//        regiConnectMongo.regiPalyazatFeltolto(regiPalyazat);
//        regiModosito.regiModosito(1, "Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", "XXXXXXXXX");
//        regiModosito.regiPalyazatOnero("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", 10000.0);

//        Uj felhivasok letoltese:
        felhivasParser.felhivasKeszito();

//        Felhivasok modositasa:
//        felhivasModosito.felhivasUjCim("Private Horizons fotópályázat", "Saját horizont");
//        felhivasModosito.felhivasUjLink("Saját horizont", "http:\\mittomEn");
//        felhivasModosito.felhivasUjKategoriak("Saját horizont", new ArrayList<>(Arrays.asList("ösztöndíj", "közművelődés")));
//        felhivasModosito.felhivasUjResztvevok("Saját horizont",
//                new ArrayList<Oktato>(Arrays.asList(oktatok.oktatoLetolto("Dr. Szabó Gyula"),
//                oktatok.oktatoLetolto("Dr. Balázs-Földi Emese"))));

//        Oktatoi adatok modositasa:
//        OktatoModosito oktatoModosito = new OktatoModosito();
//        oktatoModosito.oktatoUjPalyazati("Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("ifjúság", "gyermek", "közművelődés", "ösztöndíj", "oktatás")));
//        oktatoModosito.oktatoUjKutatasi("Dr. Szabó Gyula", new ArrayList(Arrays.asList("szociológia", "közgazdaságtudomány", "foglalkoztatáspolitika", "projektmenedzsment")));
//        oktatoModosito.oktatoUjTanszek("Dr. Szabó Gyula", "Szociálpedagógia");
//        oktatoModosito.oktatoUjHonlap("Dr. Szabó Gyula", "https://gygyk.unideb.hu/munkatars/3425");
//        oktatoModosito.oktatoUjEmail("Dr. Szabó Gyula", "szabogy@ped.unideb.hu");

//        ArrayList<String> oKutatasiTema = new ArrayList<>(Arrays.asList("projektmenedzsment", "pályázatírás",
//                "szociális munka", "cigányság", "ifjúság"));
//        ArrayList<String> oPalyazatiTema = new ArrayList<>(Arrays.asList("ifjúság", "média"));
////        oktatok.oktatoFeltolto(new Oktato("Kocsis Péter Csaba", "Szociálpedagógia",oKutatasiTema,
//                "kocspet@gmail.com", "https://gygyk.unideb.hu/hu/kocsis-peter-csaba-0#overlay-context=munkatars/6270", oPalyazatiTema));
//        System.out.println(oktatok.oktatoLetolto("Dr. Szabó Gyula"));
//        oktatok.oktatoTorol("Dr. Pornói Imre");
    }
}
