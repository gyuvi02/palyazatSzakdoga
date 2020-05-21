package palyazatkezelo;

import aktualis_palyazatok.AktualisPalyazat;
import aktualis_palyazatok.PalyazatiResztvevok;
import com.mongodb.client.model.Indexes;
import felhivasok.*;
import okatok.OktatoLekerdezes;
import regi_palyazatok.RegiPalyazat;
import regi_palyazatok.RegiPalyazatModosito;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
                "NTP-HHTDK-17", "2018", false, 0.0, 13840000.0, 13840000.0, "", aResztvevok,
                "Beadott");
        AktualisPalyazat torlendo = new AktualisPalyazat("Torlendo" , "A tehetséggondozó pályázat fő célja, hogy előkészítse a diákjaink sikeresebb OTDK szereplését, és formális keretek között, illetve magasabb színvonalon valósítsa meg a tehetséggondozással kapcsolatos feladatokat. Felkészítse a diákokat a tudományos munkára, megismertesse velük a tudományos karrier lehetséges állomásait, és mérsékelje azokat az induló hátrányokat, amelyekkel a hallgatóink a magasabb presztízsű egyetemi karokhoz képest rendelkeznek",
                "NTP-HHTDK-17", "2018", false, 0.0, 13840000.0, 13840000.0, "", aResztvevok,
                "Beadott");

//        torlendo.aktualisPalyazatFeltolto();
//        torlendo.aktualisPalyazatTorlo("Torlendo");
//        aktualisPalyazat.aktualisPalyazatTorlo();
//        aktualisPalyazat.aktualisPalyazatFeltolto();

        RegiPalyazat ujRegi = new RegiPalyazat("Valami pályázat");

        RegiPalyazat regiPalyazat = new RegiPalyazat("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán",
                "A tehetséggondozó pályázat fő célja, hogy előkészítse a diákjaink sikeresebb OTDK szereplését, és formális keretek között, illetve magasabb színvonalon valósítsa meg a tehetséggondozással kapcsolatos feladatokat. Felkészítse a diákokat a tudományos munkára, megismertesse velük a tudományos karrier lehetséges állomásait, és mérsékelje azokat az induló hátrányokat, amelyekkel a hallgatóink a magasabb presztízsű egyetemi karokhoz képest rendelkeznek",
                "NTP-HHTDK-17-0064", "2017", false, 0.0, 1200000.0, 1200000.0, "", aResztvevok, "lezárt", "9761", "NTP-HHTDK-17-0064"
                , kezdet, veg);

//        regiPalyazat.regiPalyazatFeltolto();
//        ujRegi.regiPalyazatFeltolto();
//        ujRegi.regiPalyazatTorlo("Valami pályázat");
//        regiPalyazat.regiPalyazatTorlo("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán");

//        Archivalt palyazatok:
//        System.out.println(regiPalyazat.toString());
//        ujRegi.regiPalyazatLetolto("Valami pályázat");
//        regiPalyazat.regiPalyazatLetolto("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán");
//        regiConnectMongo.regiPalyazatFeltolto(regiPalyazat);
//        regiModosito.regiModosito(1, "Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", "XXXXXXXXX");
//        regiModosito.regiPalyazatOnero("Komplex tehetségfejlesztés a Debreceni Egyetem Gyermeknevelési és Felnőttképzési Karán", 10000.0);

//        Uj felhivasok letoltese:

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

//        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
//        System.out.println(oktatoLekerdezes.oktatoListak("összes"));
//        System.out.println(oktatoLekerdezes.kutatasiTemaKereso("szociális munka"));
//        System.out.println(oktatoLekerdezes.oktatoKereso("Dr.Szabó Gyula"));
//        System.out.println(oktatoLekerdezes.kutatasiTemak("Szociológia"));
//        System.out.println(oktatoLekerdezes.oktatoiAktivitas("Dr. Szabó Gyula", "összes"));
//        System.out.println(oktatoLekerdezes.tanszekiAktivitas("Szociálpedagógia", "régi"));

        FelhivasLekerdezes felhivasLekerdezes = new FelhivasLekerdezes();
//        System.out.println(felhivasLekerdezes.felhivasListak());
//        System.out.println(felhivasLekerdezes.kiiroLekerdezes("Emberi Erőforrás Támogatáskezelő"));
//        for (Felhivas felhivas : felhivasLekerdezes.felhivasListak()) {
//            System.out.println(felhivas.getBeadasiHatarido());
//
//        System.out.println(felhivasLekerdezes.palyazatiKategoriaAlapjan("gyermek, ifjúság"));

//        kereses kulcsszavak segitsegevel:
        System.out.println(felhivasLekerdezes.kulcsszavakFelhivas("tehetséges"));


        //osszetett indexek letrehozasa
        //        felhivasokColl.createIndexes(Lists.newArrayList(
//                new IndexModel(Indexes.ascending("_id"),
//                        new IndexOptions().unique(false)),
//                new IndexModel(Indexes.compoundIndex(Indexes.text("kategoriak")),
//                        new IndexOptions().defaultLanguage("hu")
//                )));


        //indexek torlese
        //felhivasokColl.dropIndex("reszletesLeiras_text"); //az index nevet a createIndex altal visszaadott string mondja meg, a field neve + _text



        //Az uj felhivasok lekerdezese:

//        felhivasParser.felhivasKeszito();

    }

}
