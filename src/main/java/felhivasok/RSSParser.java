package felhivasok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.bson.Document;
import palyazatkezelo.MongoAccess;
import palyazatok.PalyazatiTemak;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class RSSParser {

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<RssElemek> regiLetoltesColl = palyazatDB.getCollection("RegiLetoltesek", RssElemek.class);
    MongoCollection<ArrayList> temaColl = palyazatDB.getCollection("Temak", ArrayList.class);

    ArrayList<String> relevansTemak = new PalyazatiTemak().temaLetolt(); //ennek a megvaltoztatatsa a GUI-bol lehetseges lesz
        // - vegul nem kezzel allithato lett, hanem automatikusan, az oktatok palyazati temaibol rakja ossze a listat

    SyndFeed feed;
    static final String cim = "http://www.pafi.hu/_pafi/palyazat.nsf/uj_palyazatok_tema.rss?OpenPage";

    public SyndFeed rssOlvaso() {
        try { //egy SSLException tortent, es
            URL url = new URL(cim);
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return feed;
    }

    public ArrayList<RssElemek> rssListaKeszito() {
        SyndFeed feed = rssOlvaso();
        ArrayList<RssElemek> feedLista = new ArrayList<>();
        int bejegyzesekSzama = feed.getEntries().size();

        try {

            for (int i = 0; i < bejegyzesekSzama; i++) {
                ArrayList<String> categories = new ArrayList<>();
                SyndEntry bejegyzes = feed.getEntries().get(i);
                int categorySzama = bejegyzes.getCategories().size();
                for (int j = 0; j < categorySzama; j++) {
                    categories.add(bejegyzes.getCategories().get(j).getName());
                }

                RssElemek elemek = new RssElemek(
                        bejegyzes.getUri(),
                        bejegyzes.getTitle(),
                        bejegyzes.getDescription().getValue(),
                        categories
                );

                if (rssEllenorzo(elemek.category)) {     //csak az kerul bele az ArrayListbe, amelyik relevans a kategoria besorolas alapjan
                    feedLista.add(elemek);
                }

            }
            if (elozoRSSEllenorzes(feedLista)) {
                regiLetoltesColl.insertOne(feedLista.get(0));
                return feedLista;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Hiba az RSS beolvasas soran\n" + e.getMessage());
            return null;
        }
    }
    //Az RSS-nek nincs azonositoja, nem tudom massal ellenorizni elozetesen, mint az elso elem cimevel, hogy ezt mar hasznaltam-e
    private boolean elozoRSSEllenorzes(ArrayList<RssElemek> feedLista) {
        if (!feedLista.isEmpty() && regiLetoltesColl.find().sort(new Document("_id", -1)).first() != null) { //Ha ures a feedLista, nincs szukseg az ellenorzesre
            RssElemek regiElsoElem = regiLetoltesColl.find().sort(new Document("_id", -1)).first();            //ha pedig nincs elmentve regi letoltott RSS elem, akkor nem lehetseges
            System.out.println("A regi elso elem cime: " + regiElsoElem.getTitle());
            System.out.println("Mostani feedLista elso elemenek cime: " + feedLista.get(0).getTitle().toString() + "\n");
            System.out.println(regiElsoElem.getTitle().equals(feedLista.get(0).getTitle()));
            if (regiElsoElem.getTitle().equals(feedLista.get(0).getTitle())) { //ha a ket cim megegyezik, felteszem, hogy ugyanaz az RSS
                feedLista.clear(); //uresen kuldjuk tovabb, igy nem tolti le meg egyszer a felhivasokat
                return false;

            } else {
                regiLetoltesColl.insertOne(feedLista.get(0));
                return true;
            }
        }
        return true;
    }

    //Letoltesi hiba eseten ezzel szurunk be egy ures dokumentumot, hogy ne vegye mar letoltottnek a hibat okozo RSS-t
    public void ureBeszuras() {
        regiLetoltesColl.insertOne(new RssElemek("", "a", "", new ArrayList<>()));
    }

    private boolean rssEllenorzo(ArrayList<String > kategoriak) {
        if (kategoriak.isEmpty()) {   //igy nem tudjuk eldonteni, hogy milyen palyazat, ezert inkabb letoltjuk
            return true;
        }
        else
        kategoriak.retainAll(relevansTemak);    //a kategoriak valtozoban a metszet marad
         return !kategoriak.isEmpty();
//        return false;
    }

}
