package felhivasok;

import okatok.Oktato;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FelhivasParser {

    public void felhivasKeszito(ArrayList<RssElemek> feedLista) throws IOException {
        int[] elemek = {9, 11, 13, 17, 19};
        int k = 1;

        for (RssElemek elem : feedLista) {
            System.out.println("Elemek szama: " + k++);
            String[] adatok = new String[5]; //5 adatra lesz szuksegem, ezek helyet az oldalon belul tarolom majd ebben a tombben
            Document doc = Jsoup.connect(elem.link).get();
            Elements alapAdatok = doc.select("td");
            for (int i = 0; i < 5; i++) {                   // A tombben megadott helyekrol igy szedem ki a szovegeket
                adatok[i] = alapAdatok.get(elemek[i]).text();
            }

            String date = datumKeszito(adatok[2]);

            String reszletesLeiras = reszletesLeiras(doc.select("td").get(22).html());

            ArrayList<String> lehetsegesResztvevok = lehetsegesResztvevok(elem.category);

            Felhivas keszFelhivas = new Felhivas(adatok[0], adatok[1], adatok[3], adatok[4], date, elem.link,
                    reszletesLeiras, elem.category, lehetsegesResztvevok);
            keszFelhivas.felhivasFeltolto();
        }
    }

    private static String leirasParser(String html) {  //ez megoldja, hogy a <br> tageket atalakitsuk \n jelekke, es jol tagolja a szoveget
        if(html==null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\n");
        document.select("p").prepend("\n\n");
        String s = document.html().replaceAll("\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    private String reszletesLeiras(String get22) {
        return leirasParser(get22).trim();   //vissza is kaptam a jol tagolt szoveget
    }

    private String datumKeszito(String get2) {
        Date date;
        String datumStr;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); //eloszor a String adott formatumarol kell atalakitani
        SimpleDateFormat formatter2 = new SimpleDateFormat("YYYY. MMMM dd."); //majd mikor mar datum, akkor ugy formazon, ahogy akarom
        try {
            date = formatter.parse(get2);  //itt vigyazni kell, mert nem mindig datum van
            datumStr = formatter2.format(date);
        } catch (Exception e) {
            datumStr = get2; //ha pl. "folyamatosan" szerepel a szovegben
        }
        return datumStr;
    }

    private ArrayList<String> lehetsegesResztvevok(ArrayList<String> kategoriak) { //itt valogatjuk le, kinek a palyazati temaja egyezik a kategoriakkal
        Oktato oktato = new Oktato();
        ArrayList<String> lehetsegesOktatok = new ArrayList<>();
        for (Oktato iterOktato : oktato.osszesOktato()) {
            if (kategoriak.retainAll(iterOktato.getPalyazatiTema())) {//ha van kozos elem ay oktato es a felhivas kategoriai kozott
                lehetsegesOktatok.add(iterOktato.getNev());
                System.out.println(lehetsegesOktatok.toString()); //csak ellenorzes celjabol
            }
        }
        return lehetsegesOktatok;
    }
}
