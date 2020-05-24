package palyazatkezelo;

import felhivasok.*;
import okatok.OktatoLekerdezes;
import okatok.OktatoModosito;
import palyazatok.Palyazat;
import palyazatok.PalyazatLekerdezesek;
import palyazatok.PalyazatModosito;
import palyazatok.PalyazatiResztvevok;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
//        MongoAccess.closeDatabase();
//        Oktato oktato = new Oktato();
//        Oktato oktato = new Oktato("Dr. Szabó Gyula", "Szociálpedagógia",
//                new ArrayList<String>(Arrays.asList("projektmenedzsment", "közgazdaságtan","módszertan")), "szabogy@ped.unideb.hu",
//                "valami", new ArrayList<String>(Arrays.asList("gyermek", "ifjúság")));
//        oktato.oktatoFeltolto();

        FelhivasParser felhivasParser = new FelhivasParser();
        RSSParser rssParser = new RSSParser();
        FelhivasModosito felhivasModosito = new FelhivasModosito();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate kezdet = LocalDate.of(2017, 9, 1);
        LocalDate veg = LocalDate.of(2018, 6, 30);
        PalyazatiResztvevok resztvevok = new PalyazatiResztvevok("Dr. Bocsi Veronika",
                "Dr. Szabó Gyula", "Csőke Julianna",
                new ArrayList<String>(Arrays.asList("Dr. Gortka-Rákó Erzsébet")));


        PalyazatiResztvevok aResztvevok = new PalyazatiResztvevok("Dr. Bocsi Veronika", "Dr. Szabó Gyula", "", new ArrayList<>(Arrays.asList("Dr. Nemes Magdolna", "Dr. Gortka-Rákó Erzsébet")));

//        torlendo.aktualisPalyazatFeltolto();
//        torlendo.aktualisPalyazatTorlo("Torlendo");
//        aktualisPalyazat.aktualisPalyazatTorlo();
//        aktualisPalyazat.aktualisPalyazatFeltolto();


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
        OktatoModosito oktatoModosito = new OktatoModosito();
//        oktatoModosito.eltavolitTombbol("kutatasiTema", "Dr. Szabó Gyula", "gyermek");
//        oktatoModosito.tombModosito(true, "palyazatiTema", "Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("valami", "masvalami", "megvalami")));
        oktatoModosito.tombModosito(false, "palyazatiTema", "Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("valami", "masvalami", "megvalami")));
//        oktatoModosito.tombFrissito("kutatasiTema","Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("ifjúság", "gyermek", "közművelődés", "ösztöndíj", "oktatás")));
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

        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
//        System.out.println(oktatoLekerdezes.oktatoListak("Szociálpedagógia"));
//        System.out.println(oktatoLekerdezes.kutatasiTemaKereso("szociális munka"));
//        System.out.println(oktatoLekerdezes.palyazatiTemaKereso("ifjúság"));
//        System.out.println(oktatoLekerdezes.oktatoKereso("Dr. Szabó Gyula"));
//        System.out.println(oktatoLekerdezes.kutatasiTemak("Szociálpedagógia"));
//        System.out.println(oktatoLekerdezes.oktatoiAktivitas("Dr. Szabó Gyula", "összes"));
//        System.out.println(oktatoLekerdezes.tanszekiAktivitas("Szociálpedagógia", "aktuális"));

        FelhivasLekerdezes felhivasLekerdezes = new FelhivasLekerdezes();
//        System.out.println(felhivasLekerdezes.felhivasListak());
//        System.out.println(felhivasLekerdezes.kiiroLekerdezes("Emberi Erőforrás Támogatáskezelő"));
//        for (Felhivas felhivas : felhivasLekerdezes.felhivasListak()) {
//            System.out.println(felhivas.getBeadasiHatarido());
//        }
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = "2020-06-11";
//        Date ujDatum = sdf.parse(dateString);
//        System.out.println(felhivasLekerdezes.kesobbiHataridok(ujDatum));

//        System.out.println(felhivasLekerdezes.palyazatiKategoriaAlapjan("oktatás").size());

//        kereses kulcsszavak segitsegevel:
//        System.out.println(felhivasLekerdezes.kulcsszavakFelhivas("egyetem főiskola").size());

        //osszetett indexek letrehozasa
        //        felhivasokColl.createIndexes(Lists.newArrayList(
//                new IndexModel(Indexes.ascending("_id"),
//                        new IndexOptions().unique(false)),
//                new IndexModel(Indexes.compoundIndex(Indexes.text("kategoriak")),
//                        new IndexOptions().defaultLanguage("hu")
//                )));

        Palyazat palyazat = new Palyazat();
//        Palyazat probaPalyazat = new Palyazat("Valami nem stimmel", "elkezdett");
//        probaPalyazat.PalyazatFeltolto();
//        System.out.println(palyazat.PalyazatLetolto("Valami nem stimmel"));
//        System.out.println(palyazat.osszesPalyazat());
//        System.out.println(palyazatListabolObject(palyazat.menedzserKereso("Dr. Szabó Gyula")));

        PalyazatModosito palyazatModosito = new PalyazatModosito();
//        palyazatModosito.kplusFModosito("Valami nem stimmel", true);
//        palyazatModosito.oneroModosito("Valami nem stimmel", 0.0);
//        palyazatModosito.igenyeltOsszegModosito("Valami nem stimmel", 98000000.0);
//        palyazatModosito.adatModosito(0,"Valami nem stimmel", "Most mar stimmel");
//        palyazatModosito.pozicioHozzaad("Most mar stimmel", "resztvevoEmberek", "Ujabb valaki");
//        palyazatModosito.pozicioTorol("Valami nem stimmel", "resztvevoEmberek", "Ujabb valaki");
//        palyazatModosito.pozicioHozzaad("Most mar stimmel", "projektmenedzser", "Dr. Pornói Imre");
//        palyazatModosito.pozicioHozzaad("Most mar stimmel", "szakmaiVezeto", "Dr. Szabó Gyula");
//        palyazatModosito.pozicioTorol("Most mar stimmel", "kezelo", "Dr. Szabó Gyula");

        PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("resztvevoEmberek","Ujabb valaki"));
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("projektmenedzser", "Dr. Pornói Imre"));
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("összes", "Dr. Szabó Gyula"));
//        System.out.println(palyazatLekerdezesek.osszesPalyazat());
//        System.out.println(palyazatLekerdezesek.rendezettLekerdezes("Ez csak egy proba"));
//        System.out.println(palyazatLekerdezesek.melyikEvbenKezdodott("2018"));
//        System.out.println(palyazatLekerdezesek.kPlusFFelhivasok());
//        System.out.println(palyazatLekerdezesek.oneroNelkul());


//        palyazatModosito.pozicioHozzaad("Ez csak egy proba", "resztvevoEmberek", "valaki");



        //indexek torlese
        //felhivasokColl.dropIndex("reszletesLeiras_text"); //az index nevet a createIndex altal visszaadott string mondja meg, a field neve + _text



        //Az uj felhivasok lekerdezese:

//        felhivasParser.felhivasKeszito();

    }

    public static void felhivasListabolObject(List<String> lista) {
        Felhivas felhivas = new Felhivas();
        for (String cim : lista) {
            felhivas.felhivasLetolto(cim);
        }
    }

    public static ArrayList<Palyazat> palyazatListabolObject(List<String> lista) {
        ArrayList<Palyazat> palyazatLista = new ArrayList<>();
        Palyazat palyazat = new Palyazat();
        for (String cim : lista) {
            palyazatLista.add(palyazat.PalyazatLetolto(cim));
        }
        return palyazatLista;
    }
}
