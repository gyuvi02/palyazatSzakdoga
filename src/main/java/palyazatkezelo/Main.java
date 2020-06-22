package palyazatkezelo;

import com.github.davidmoten.guavamini.Lists;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import felhivasok.*;
import okatok.Oktato;
import okatok.OktatoLekerdezes;
import okatok.OktatoModosito;
import palyazatok.*;

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
//        Felhivas felhivas = new Felhivas("Ez itt egy felhivas", "Magyar Tudomanyos Akademia", "tudomanyos palyazat", "mindenki",
//                "06/05/2020", "http://www.pafi.hu/_pafi/palyazat.nsf/767f6c3e957e6df9c12572e7004a1842/e95f3a94e56ed0b9c12585610062ed1e?OpenDocument",
//                "Ez lenne itt a reszletes leiras", new ArrayList<>(Arrays.asList("dij", "tudomany")),
//                new ArrayList<>(Arrays.asList("Dr. Szabó Gyula")), LocalDate.of(2020, 5, 1));
//        felhivas.felhivasFeltolto();
        Oktato oktato = new Oktato();
//        Oktato oktato = new Oktato("Dr. Szabó Gyula", "Szociálpedagógia",
//                new ArrayList<String>(Arrays.asList("projektmenedzsment", "közgazdaságtan","módszertan")), "szabogy@ped.unideb.hu",
//                "valami", new ArrayList<String>(Arrays.asList("gyermek", "ifjúság")));
//        oktato.oktatoFeltolto();

        FelhivasParser felhivasParser = new FelhivasParser();
        RSSParser rssParser = new RSSParser();
        FelhivasModosito felhivasModosito = new FelhivasModosito();

//        PalyazatiTemak palyazatiTemak = new PalyazatiTemak(new ArrayList<>(Arrays.asList("gyermek", "gyermek, ifjúság", "ifjúság", "kapcsolatok",
//                "közművelődés", "kutatás-fejlesztés", "művészet", "oktatás", "ösztöndíj", "sport", "szociális")));
//        palyazatiTemak.temafeltolt();
        PalyazatiTemak palyazatiTemak = new PalyazatiTemak();
//        System.out.println(palyazatiTemak.temaLetolt());

        //a legutobb letoltott felhivasok reszletes lekerdezese
//        for (String cim : felhivasParser.legutobbiFelhivasok()) {
//            System.out.println(felhivas.felhivasLetolto(cim));
//        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate kezdet = LocalDate.of(2017, 9, 1);
        LocalDate veg = LocalDate.of(2018, 6, 30);
//        PalyazatiResztvevok resztvevok = new PalyazatiResztvevok("Dr. Bocsi Veronika",
//                "Dr. Szabó Gyula", "Csőke Julianna",
//                new ArrayList<String>(Arrays.asList("Dr. Gortka-Rákó Erzsébet")));


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
//        System.out.println(felhivas.felhivasLetolto("EcoSim Open verseny").size());

//        Oktatoi adatok modositasa:
        OktatoModosito oktatoModosito = new OktatoModosito();
//        oktatoModosito.eltavolitTombbol("kutatasiTema", "Dr. Szabó Gyula", "gyermek");
//        oktatoModosito.tombModosito(true, "palyazatiTema", "Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("valami", "masvalami", "megvalami")));
//        oktatoModosito.tombModosito(false, "palyazatiTema", "Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("valami", "masvalami", "megvalami")));
//        oktatoModosito.tombFrissito("kutatasiTema","Dr. Szabó Gyula", new ArrayList<String>(Arrays.asList("ifjúság", "gyermek", "közművelődés", "ösztöndíj", "oktatás")));
//        oktatoModosito.oktatoUjKutatasi("Dr. Szabó Gyula", new ArrayList(Arrays.asList("szociológia", "közgazdaságtudomány", "foglalkoztatáspolitika", "projektmenedzsment")));
//        oktatoModosito.oktatoUjTanszek("Dr. Szabó Gyula", "Szociálpedagógia");
//        oktatoModosito.oktatoUjHonlap("Dr. Szabó Gyula", "https://gygyk.unideb.hu/munkatars/3425");
//        oktatoModosito.oktatoUjEmail("Dr. Szabó Gyula", "szabogy@ped.unideb.hu");
//        System.out.println(oktato.oktatoLetolto("Dr. Szabó Gyul"));
//        System.out.println(oktato.oktatoTorol("sss"));
//        System.out.println(oktato.oktatoEmailEllenorzes("nemletezo"));

//        ArrayList<String> oKutatasiTema = new ArrayList<>(Arrays.asList("projektmenedzsment", "pályázatírás",
//                "szociális munka", "cigányság", "ifjúság"));
//        ArrayList<String> oPalyazatiTema = new ArrayList<>(Arrays.asList("ifjúság", "média"));
////        oktatok.oktatoFeltolto(new Oktato("Kocsis Péter Csaba", "Szociálpedagógia",oKutatasiTema,
//                "kocspet@gmail.com", "https://gygyk.unideb.hu/hu/kocsis-peter-csaba-0#overlay-context=munkatars/6270", oPalyazatiTema));
//        System.out.println(oktato.oktatoLetolto("Dr. Szabó Gyula"));
//        oktato.oktatoTorol("Dr. Pornói Imre");

        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
//        System.out.println(oktatoLekerdezes.oktatoListak("Szociálpedagógia"));
//        System.out.println(oktatoLekerdezes.kutatasiTemaKereso("szociális munka"));
//        System.out.println(oktatoLekerdezes.palyazatiTemaKereso("ifjúság"));
//        System.out.println(oktatoLekerdezes.oktatoKereso("Dr. Szabó Gyula"));
//        System.out.println(oktatoLekerdezes.kutatasiTemak("összes"));
//        System.out.println(oktatoLekerdezes.oktatoiAktivitas("Dr. Szabó Gyula", "összes"));
//        System.out.println(oktatoLekerdezes.oktatoiAktivitas("Dr. Bocsi Veronika", "regi"));
//        System.out.println(oktatoLekerdezes.tanszekiAktivitas("Szociálpedagógia", "összes"));
//        oktatoLekerdezes.kutatasiTemak("Szociálpedagógia");

        FelhivasLekerdezes felhivasLekerdezes = new FelhivasLekerdezes();

//        felhivasLekerdezes.egyszerimodositas();
//        System.out.println(felhivasLekerdezes.felhivasListak());
//        System.out.println(felhivasLekerdezes.kiiroLekerdezes("Emberi Erőforrás Támogatáskezelő"));
//        for (Felhivas felhivas : felhivasLekerdezes.felhivasListak()) {
//            System.out.println(felhivas.getBeadasiHatarido());
//        }
//        LocalDate ujDatum = LocalDate.of(2020,5, 28 );
//        System.out.println(felhivasLekerdezes.kesobbiHataridok(ujDatum).size());

//        System.out.println(felhivasLekerdezes.palyazatiKategoriaAlapjan("oktatás").size());

//        kereses kulcsszavak segitsegevel:
//        System.out.println(felhivasLekerdezes.kulcsszavakFelhivas("ifjúság").size());

        //osszetett indexek letrehozasa - Felhivasok
        //        felhivasokColl.createIndexes(Lists.newArrayList(
//                new IndexModel(Indexes.ascending("_id"),
//                        new IndexOptions().unique(false)),
//                new IndexModel(Indexes.compoundIndex(Indexes.text("kategoriak")),
//                        new IndexOptions().defaultLanguage("hu")
//                )));

        //osszetett indexek letrehozasa - Palyazatok
//        palyazatokColl.createIndexes(Lists.newArrayList(
//                new IndexModel(Indexes.ascending("_id"),
//                        new IndexOptions().unique(false)),
//                new IndexModel(Indexes.compoundIndex(Indexes.text("palyazatCim"),
//                        Indexes.text("leiras"), Indexes.text("megjegyzes")),
//                        new IndexOptions().defaultLanguage("hu")
//                )));



        Palyazat palyazat = new Palyazat();
//        Palyazat probaPalyazat = new Palyazat("Kitalált Tehetség pályázat", "elkezdett");
//        probaPalyazat.PalyazatFeltolto();
//        System.out.println(palyazat.PalyazatLetolto("Valami nem stimmel"));
//        System.out.println(palyazat.osszesPalyazat());
//        System.out.println(palyazatListabolObject(palyazat.menedzserKereso("Dr. Szabó Gyula")));

        PalyazatModosito palyazatModosito = new PalyazatModosito();
//        palyazatModosito.kplusFModosito("Kitalált Tehetség pályázat", false);
//        palyazatModosito.oneroModosito("Kitalált Tehetség pályázat", 0.0);
//        System.out.println(palyazatModosito.igenyeltOsszegModosito("Kitalált Tehetség pályázat", 98000000.0));
//        palyazatModosito.adatModosito(0,"Most mar stimmel", "Kitalált Tehetség pályázat");
//        System.out.println(palyazatModosito.adatModosito(2,"Kitalált Tehetség pályázat", "Főiskolai és egyetemi hallgatók számára kiírt, tehetséggondozás"));
//        System.out.println(palyazatModosito.pozicioHozzaad("Kitalált Tehetség pályázat", "resztvevoEmberek", "Ujabb valaki"));
//        System.out.println(palyazatModosito.pozicioTorol("Kitalált Tehetség pályázat", "resztvevoEmberek", "Ujabb valaki"));
//        System.out.println(palyazatModosito.pozicioHozzaad("Kitalált Tehetség pályázat", "projektmenedzser", "Dr. Pornói Imre"));
//        System.out.println(palyazatModosito.pozicioHozzaad("Kitalált Tehetség pályázat", "szakmaiVezeto", "Dr. Szabó Gyula"));
//        System.out.println(palyazatModosito.pozicioTorol("Kitalált Tehetség pályázat", "kezelo", "Dr. Szabó Gyula"));
//        System.out.println(palyazatModosito.kezdetModosito("Kitalált Tehetség pályázat", kezdet));
//        System.out.println(palyazatModosito.vegModosito("Kitalált Tehetség pályázat", veg));
//        System.out.println(palyazatModosito.osszkoltsegModosito("Kitalált Tehetség pályázat", 1200000.0));
//        System.out.println(palyazatModosito.pozicioHozzaad("Ez csak egy proba", "resztvevoEmberek", "valaki"));


        PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();
//        System.out.println(palyazatLekerdezesek.fazisLekerdezes("elkezdett"));
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("resztvevoEmberek","Ujabb valaki"));
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("projektmenedzser", "Dr. Pornói Imre"));
//        System.out.println(palyazatLekerdezesek.resztvevoKereso("összes", "Dr. Szabó Gyula"));
//        System.out.println(palyazatLekerdezesek.osszesPalyazat());
//        System.out.println(palyazatLekerdezesek.rendezettLekerdezes("Ez csak egy proba"));
//        System.out.println(palyazatLekerdezesek.melyikEvbenKezdodott("2018"));
//        System.out.println(palyazatLekerdezesek.kPlusFFelhivasok());
//        System.out.println(palyazatLekerdezesek.oneroNelkul());
//        System.out.println(palyazatLekerdezesek.kulcsszavakPalyazat("egyetemi"));
        LocalDate ujKezdet = LocalDate.of(2017, 8, 1);
        LocalDate ujVeg = LocalDate.of(2017, 9, 30);
//        System.out.println(palyazatLekerdezesek.kezdoEvPeriodus(ujKezdet, ujVeg));
//        System.out.println(palyazatLekerdezesek.osszegHatarok("onero", 0.0, 1200001.0));


        //indexek torlese
        //felhivasokColl.dropIndex("reszletesLeiras_text"); //az index nevet a createIndex altal visszaadott string mondja meg, a field neve + _text



        //Az uj felhivasok lekerdezese:

//        felhivasParser.felhivasKeszito(new RSSParser().rssListaKeszito());
        felhivasLekerdezes.automatikusTorles();

    }

    //eredetileg String tomboket adtak vissza a metodusok, ezekeknek az atalakitasahoz kellett, de a MongoDB-ben egyszerubb dokumentumokat visszakapni a lekerdezesekkel
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
