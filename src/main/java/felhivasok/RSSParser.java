package felhivasok;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class RSSParser {
    static final ArrayList<String> relevansTemak = new ArrayList<>(Arrays.asList("gyermek", "gyermek, ifjúság", "ifjúság",
            "közművelődés", "művészet", "oktatás")); //csak ezeket a kategoriakat akarjuk tenylegesen letolteni
//    static final ArrayList<String> relevansTemak = new ArrayList<>(Arrays.asList("gyermek")); //csak ezeket a kategoriakat akarjuk tenylegesen letolteni

    SyndFeed feed;
    static final String cim = "http://www.pafi.hu/_pafi/palyazat.nsf/uj_palyazatok_tema.rss?OpenPage";

    public SyndFeed rssOlvaso() {
        try {
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
//        int bejegyzesekSzama = 3;
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
            if (rssEllenorzo(elemek)) {     //csak az kerul bele az ArrayListbe, amelyik relevans a kategoria besorolas alapjan
            feedLista.add(elemek);
            }
        }
        return feedLista;
    }

    private boolean rssEllenorzo(RssElemek elemek) {
        ArrayList<String> elem = elemek.getCategory(); //ket tomb metszetet vizsgalom majd meg
        if (elem.isEmpty()) {
            return true;
        }
        else
        elem.retainAll(relevansTemak);
         return !elem.isEmpty();

    }


}
